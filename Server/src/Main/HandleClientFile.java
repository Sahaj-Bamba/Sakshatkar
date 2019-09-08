package Main;//MessageServer is receiving file

import Constant.Request;
import Constant.RequestFile;
import RequestClasses.Response;
import Utilities.SaveFile;
import Utilities.SqlQueryExecuter;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HandleClientFile extends Thread{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private int type;

    public HandleClientFile(Socket socket) {
        System.out.println("In the constructor of HandleClientFile");
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println("Data Input stream created at server");
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Object Output stream created at server");
            System.out.println("File Server socket created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        System.out.println("In run method of File Client Handler");

        while(true) {

            try {

                type = dataInputStream.readInt();

                process(type);

                System.out.println("Response sent");

            } catch (IOException e) {
                System.out.println("File Server disconnected");
                break;
//                e.printStackTrace();
            }

        }
    }

    private void process(int type) throws IOException {

        if (type == RequestFile.SETPROFILEPICTURE.ordinal()) {
            _saveProfilePicture();
        }
        else if(type == RequestFile.GETPROFILEPICTURE.ordinal()) {
            _getProfilePicture();
       }

    }


    private void _saveProfilePicture(){

        String fileName;
        try {

            fileName = dataInputStream.readUTF();
            String dirName = "src/ProfilePictures";
            new File(dirName).mkdir();
            new SaveFile(dataInputStream).saveFile(dirName, fileName);

            String fileNameWithoutExtension = fileName.substring(0,fileName.indexOf("."));
            String extension = fileName.substring(fileName.lastIndexOf(".")+1);

            //SQL Query to update ImageLocation on MessageServer
            Main.SQLQUERYEXECUTER.update("UPDATE user SET extension = " + "'" + extension +"'" + " WHERE userID = "+ "'" + fileNameWithoutExtension + "';");

            dataOutputStream.writeInt(type);
            dataOutputStream.writeBoolean(true);
            dataOutputStream.flush();

        } catch (IOException e) {
//            e.printStackTrace();
        }
    }


    private void _getProfilePicture() {

        //This picture will always exist
        String profilePictureWithExtension;
        String extension = null;
        String filePath;

        try {

            profilePictureWithExtension = dataInputStream.readUTF();

            dataOutputStream.writeInt(type);
            dataOutputStream.writeBoolean(true);

//            SQL Query to get the extension
//            ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT extension FROM user WHERE userID = '" + userID +"';");
//            while(rs.next()) {
//                extension = rs.getString("extension");
//            }

            filePath = "src/ProfilePictures/" + profilePictureWithExtension;
            File myFile = new File(filePath);
//            System.out.println(myFile.getAbsolutePath());
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            //bis.read(mybytearray, 0, mybytearray.length);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            dataOutputStream.writeUTF(profilePictureWithExtension);
            dataOutputStream.writeLong(mybytearray.length);
            dataOutputStream.write(mybytearray);
            dataOutputStream.flush();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
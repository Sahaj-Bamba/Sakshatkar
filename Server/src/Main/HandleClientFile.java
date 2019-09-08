package Main;//MessageServer is receiving file

import Constant.RequestFile;
import RequestClasses.Response;
import Utilities.SaveFile;
import Utilities.SqlQueryExecuter;

import java.io.*;
import java.net.Socket;

public class HandleClientFile extends Thread{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private int type;
    private String fileName;

    public HandleClientFile(Socket socket) {
        System.out.println("In the constructor of HandleClientFile");
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println("Data Input stream created at server");
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Object Output stream created at server");
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Object input stream created at FileClient");
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Object output stream created at FileClient");
            System.out.println("File Server socket created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        System.out.println("In run method of File Client Handler");

        while(true) {


            try {

                fileName = dataInputStream.readUTF();

//                String extension = dataInputStream.readUTF();
//                System.out.println("Extension = " + extension);

                type = dataInputStream.readInt();


                System.out.println(type);
                System.out.println(fileName);

                Object response = (Object) process();

//                objectOutputStream.writeObject(response);
//                objectOutputStream.flush();

                System.out.println("Response sent");

            } catch (IOException e) {
                System.out.println("File Server disconnected");
                break;
//                e.printStackTrace();
            }

        }
    }

    private Object process() throws IOException {

        if(type == RequestFile.PROFILEPICTURE.ordinal()){
            return _saveProfilePicture();
        }

        return new Response(1,"Error in storing on server. Try again later.");
    }

    private Object _saveProfilePicture() throws IOException {

        String dirName = "src/ProfilePictures";
        String filePath = dirName + "/" + fileName;
        Boolean doesFileExist = new File(dirName).mkdir();
        try{
            new SaveFile(dataInputStream).saveFile(dirName, fileName);
        }
        catch(Exception e){
            return new Response(1, "Error in storing image on server");
        }

        String fileNameWithoutExtension = fileName.substring(0,fileName.indexOf("."));
        String extension = fileName.substring(fileName.lastIndexOf(".")+1);

        //SQL Query to update ImageLocation on MessageServer
        Main.SQLQUERYEXECUTER.update("UPDATE user SET extension = " + "'" + extension +"'" + " WHERE userID = "+ "'" + fileNameWithoutExtension + "';");

        return new Response(0,"");
    }

}
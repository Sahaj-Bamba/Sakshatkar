package Main;

import RequestClasses.Response;
import Utilities.SaveFile;

import java.net.*;
import java.io.*;

public class FileClient {

    private String ip;
    private int port;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public FileClient(String ip, int port) {

        this.port = port;
        this.ip = ip;

        try {
            this.socket = new Socket(ip, port);
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Data output stream created");
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println("Data input stream created");
            System.out.println("File Client socket created");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendFile(String filePath, int type, String fileName) throws IOException {

        File myFile = new File(filePath);
        byte[] mybytearray = new byte[(int) myFile.length()];

        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        //bis.read(mybytearray, 0, mybytearray.length);

        DataInputStream dis = new DataInputStream(bis);
        dis.readFully(mybytearray, 0, mybytearray.length);

        //Sending file name and file size to the server

        dataOutputStream.writeInt(type);
        dataOutputStream.writeUTF(fileName);
        dataOutputStream.writeLong(mybytearray.length);
        dataOutputStream.write(mybytearray, 0, mybytearray.length);
        dataOutputStream.flush();

        //Closing socket
//        sock.close();
    }

    public void sendFileResponse(int type, String string) throws IOException {
        dataOutputStream.writeInt(type);
        dataOutputStream.writeUTF(string);
        dataOutputStream.flush();
    }

    public void receiveFile(String dirName, String fileName, int type) throws IOException {
        int type1 = dataInputStream.readInt();
        boolean status = dataInputStream.readBoolean();
        String fileName1 = dataInputStream.readUTF();
        fileName = (fileName == null)? fileName1 : fileName;
        if(type1 == type && status == true) {
            new SaveFile(dataInputStream).saveFile(dirName, fileName);
        }
        else {
            System.out.println("Error");
        }
    }

    public int receiveFileResponse() throws IOException {
        int type = dataInputStream.readInt();
        boolean status = dataInputStream.readBoolean();
        return status ? type : -1;
    }

}
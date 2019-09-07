//Client is sending file

package Main;

import RequestClasses.Response;

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

//        System.out.println("Type "+type);
        //Send file

        File myFile = new File(filePath);
        byte[] mybytearray = new byte[(int) myFile.length()];

        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        //bis.read(mybytearray, 0, mybytearray.length);

        DataInputStream dis = new DataInputStream(bis);
        dis.readFully(mybytearray, 0, mybytearray.length);

        //Sending file name and file size to the server

        dataOutputStream.writeUTF(fileName);
        dataOutputStream.writeInt(type);
        dataOutputStream.writeLong(mybytearray.length);
        dataOutputStream.write(mybytearray, 0, mybytearray.length);
        dataOutputStream.flush();

        //Closing socket
//        sock.close();
    }

    public DataInputStream receiveObjectResponse() throws IOException, ClassNotFoundException {
        return dataInputStream;

    }

}
package Main;//Server is receiving file

import Constant.RequestFile;

import java.io.*;
import java.net.Socket;

public class HandleClientFile extends Thread{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private Boolean state;
    private String description;

    public HandleClientFile(Socket socket) {
//        System.out.println("In the constructor of HandleClientFile");
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        System.out.println("In run method of File Client Handler");

        while(true) {

            int bytesRead;
            String fileName = null;
            try {

                fileName = dataInputStream.readUTF();
                long size = dataInputStream.readLong();
                int type = dataInputStream.readInt();

                OutputStream output = new FileOutputStream(fileName);
                System.out.println(size);
                System.out.println(type);
                byte[] buffer = new byte[1024];
                while (size > 0 && (bytesRead = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)
                {
                    output.write(buffer, 0, bytesRead);
                    size -= bytesRead;
                }
                //File stored on server

//                //Now, sending the boolean status to client
//                System.out.println("Hello");
                process(type);
                dataOutputStream.writeBoolean(state);
                dataOutputStream.writeUTF(description);
                dataOutputStream.flush();
                System.out.println("Response sent");

            } catch (IOException e) {
//                e.printStackTrace();
            }

        }
    }

    private void process(int type) {
        if(type == RequestFile.PROFILEPICTURE.ordinal()){
            storeImage();
        }
    }

    private Boolean storeImage() {
        return true;
    }

}
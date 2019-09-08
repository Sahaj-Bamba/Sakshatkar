package Utilities;

import DataClasses.Chat;
import DataClasses.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class FileSystem {

    private String userID;
    private String base = "test";


    public FileSystem(String base) throws IOException {
        this.base = base;
        fileSystemSetUp();
    }

    private void fileSystemSetUp() throws IOException {

        System.out.println("hi");

        String basePath = System.getProperty("user.home");

        if (! new File(basePath+"/"+base).exists()) {
            startingNewSystem();
        }

    }

    private void startingNewSystem() throws IOException {

        String basePath = System.getProperty("user.home");
        new File(basePath+"/"+base).mkdir();
        basePath += "/"+base+"/";
        new File(basePath+"login").createNewFile();
        BufferedWriter bw = new BufferedWriter(new PrintWriter(basePath+"login"));
        bw.write("0");
        bw.flush();
        bw.close();

        new File(basePath+"users").mkdir();

        System.out.println("New System Started");

    }

    public void newUser(Client client) throws IOException {
		String userId = client.getUserID();
        this.userID = userId;

        String basePath = System.getProperty("user.home");
        String basePath1;
        basePath += "/"+base+"/users";

        if (! new File(basePath+"/"+userId).exists()){
            new File(basePath+"/"+userId).mkdir();
        }else{
            return;
        }

        basePath += "/" + userId + "/";

        new File(basePath+"chattedUsers").createNewFile();
        new File(basePath+"local").mkdir();
        new File(basePath+"delayed").mkdir();

        basePath1 = basePath ;
        basePath += "/local";

        new File(basePath+"/chat").mkdir();
        new File(basePath+"/media").mkdir();

        basePath = basePath1;
        basePath += "/delayed";

        new File(basePath+"/chat").mkdir();
        new File(basePath+"/media").mkdir();

		login(client);
    }

    public Client isLoggedIn() throws IOException {

        String basePath = System.getProperty("user.home");
        basePath += "/"+base+"/";
        BufferedReader br = new BufferedReader(new FileReader(basePath+"login"));
        String line;
        line = br.readLine().trim();
        if (Integer.parseInt(line) == 0) {
            return null;
        }else {
            line = br.readLine();
            String[] content = line.split("#");
            br.close();
            return (new Client(content[0],Integer.parseInt(content[1]),content[2],content[3],Integer.parseInt(content[4]),content[5],content[6]));
        }

    }

    public void logout() throws IOException {

        String basePath = System.getProperty("user.home");
        basePath += "/"+base+"/";
        BufferedWriter bw = new BufferedWriter(new PrintWriter(basePath+"login"));
        bw.write("0");
        bw.close();

    }

    public void login(Client client) throws IOException {

        String basePath = System.getProperty("user.home");
        basePath += "/"+base+"/";
        BufferedWriter bw = new BufferedWriter(new PrintWriter(basePath+"login"));
        bw.write("1");
        bw.newLine();
        bw.write(" "+client.toString());
        bw.newLine();
        bw.close();

    }

    public ArrayList<Client> getChattedList() throws IOException {

        ArrayList<Client> clients = new ArrayList<>();
        String basePath = System.getProperty("user.home");
        basePath += "/"+base+"/users/"+this.userID ;

        BufferedReader br = new BufferedReader(new FileReader(basePath+"chattedUsers"));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        Client c;
        while ((line = br.readLine()) != null) {
            String[] content = line.split("#");
            clients.remove(new Client(content[0],Integer.parseInt(content[1]),content[2],content[3],Integer.parseInt(content[4]),content[5],content[6]));
            clients.add(new Client(content[0],Integer.parseInt(content[1]),content[2],content[3],Integer.parseInt(content[4]),content[5],content[6]));
        }
        br.close();

        Collections.reverse(clients);

        BufferedWriter bw = new BufferedWriter(new FileWriter(basePath+"chattedUsers"));

        for (Client X : clients) {
            bw.write(X.toString());
            bw.newLine();
        }

        bw.close();

        return clients;

    }

    private StringBuffer fileToStringBuffer(String add) throws IOException {

        BufferedReader file = new BufferedReader(new FileReader(add));
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        while ((line = file.readLine()) != null) {
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();

        return inputBuffer;
    }

            /*          type = 0 means normal       type = 1 means delayed          */

    void addChat(Chat chat, int type) throws IOException {

        String basePath2;
        String basePath = System.getProperty("user.home");
        basePath += "/"+base+"/users/";
        if (chat.getLevel()==0){
            basePath += chat.getFrom();
        }else{
            basePath += chat.getTo();
        }
        basePath += "/";
        basePath2 = basePath;
        if (type == 0){
            basePath += "local/";
        }else{
            basePath += "delayed/";
        }
        basePath += "chat/";
        if (chat.getLevel()==0){
            basePath += chat.getTo();
        }else{
            basePath += chat.getFrom();
        }
        System.out.println(basePath);
        if (!(new File(basePath).exists())){
            new File(basePath).createNewFile();
        }

        /*      base path set       */

        BufferedWriter bw = new BufferedWriter(new FileWriter(basePath,true));
        bw.append(chat.toString());
        bw.close();
        bw = new BufferedWriter(new FileWriter(basePath2+"chattedUsers",true));
        if (chat.getLevel()==0){
            bw.append(chat.getTo());
        }else{
            bw.append(chat.getTo());
        }
        bw.newLine();
        bw.close();

    }


    /*          from is running the app and to is the user at other end .               */
    public ArrayList<Chat> getChatUser(String from,String to) throws IOException {

        ArrayList<Chat> chats = new ArrayList<Chat>();

        String basePath = System.getProperty("user.home");
        basePath += "/"+base+"/users/"+from+"/local/"+to+"/";

        /*      base path set       */

        BufferedReader br = new BufferedReader(new FileReader(basePath));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        Client c;
        while ((line = br.readLine()) != null) {
            String[] content = line.split("#");
//            chats.remove(new Chat(from,to,Integer.parseInt(content[0]),content[2],Integer.parseInt(content[1])));
            chats.add(new Chat(from,to,Integer.parseInt(content[0]),content[2],Integer.parseInt(content[1]),Integer.parseInt(content[3])));
        }
        br.close();
        return chats;

    }

    public ArrayList<Chat> getDelayedChat(String from){

        ArrayList<Chat> chats = new ArrayList<Chat>();

        String basePath = System.getProperty("user.home");
        basePath += "/"+base+"/users/"+from+"/delayed/chat";

//        /*      base path set       */
//
//        for (File X: new File(basePath).listFiles()) {
//
//        }
//
//        BufferedReader br = new BufferedReader(new FileReader(basePath));
//        StringBuffer inputBuffer = new StringBuffer();
//        String line;
//        Client c;
//        while ((line = br.readLine()) != null) {
//            String[] content = line.split("#");
////            chats.remove(new Chat(from,to,Integer.parseInt(content[0]),content[2],Integer.parseInt(content[1])));
//            chats.add(new Chat(from,to,Integer.parseInt(content[0]),content[2],Integer.parseInt(content[1]),Integer.parseInt(content[3])));
//        }
//        br.close();
        return chats;

    }

    public ArrayList<Chat> getDelayedChat(String from,String to) throws IOException {

        ArrayList<Chat> chats = new ArrayList<Chat>();

        String basePath = System.getProperty("user.home");
        basePath += "/"+base+"/users/"+from+"/local/"+to+"/";

        /*      base path set       */

        BufferedReader br = new BufferedReader(new FileReader(basePath));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        Client c;
        while ((line = br.readLine()) != null) {
            String[] content = line.split("#");
//            chats.remove(new Chat(from,to,Integer.parseInt(content[0]),content[2],Integer.parseInt(content[1])));
            chats.add(new Chat(from,to,Integer.parseInt(content[0]),content[2],Integer.parseInt(content[1]),Integer.parseInt(content[3])));
        }
        br.close();
        return chats;

    }

//    public File getProfilePicture(){
//
//    }

}
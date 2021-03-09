package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ClientHandler implements Runnable{
    BufferedReader br;
    PrintWriter pw;
    BlockingQueue<String> allMessage;
    String name;
public ClientHandler(String name, BufferedReader br, PrintWriter pw, BlockingQueue<String> allMessage){
    this.br = br;
    this.pw = pw;
    this.allMessage = allMessage;
    this.name = name;
}



    public void protocol() throws IOException{
        String valg = br.readLine();
        String[] valgSplit = valg.split("#");
        while(!valg.equalsIgnoreCase("bye")){
            switch(valgSplit[0]){
                //case "CONNECT":connecter(valgSplit[1]);break;
                case "SEND":sendMessage(valgSplit[1], valgSplit[2]);break;
                case "CLOSE": close(valgSplit[1]); break;
            }
        }
    }

    private void close(String user) {
        //users.remove(user);
       // pw.println("ONLINE#" + users);

    }

    private void sendMessage(String user, String message) {


    }

    /*private void connecter(String user) {
        // TODO:
        users.add(user);
        pw.println("ONLINE#" + users);
    }*/

    @Override
    public void run() {
        try {
            protocol();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

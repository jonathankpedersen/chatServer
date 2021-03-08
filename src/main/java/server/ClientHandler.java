package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread{
    BufferedReader br;
    PrintWriter pw;
    ArrayList<String> users = new ArrayList<>();

    public void protocol() throws IOException{
        String valg = br.readLine();
        String[] valgSplit = valg.split("#");
        while(!valg.equalsIgnoreCase("bye")){
            switch(valgSplit[0]){
                case "CONNECT":connecter(valgSplit[1]);break;
                case "SEND":sendMessage(valgSplit[1], valgSplit[2]);break;
                case "CLOSE": close(); break;
            }
        }
    }

    private void close() {
        users.remove(/*ved ikke lige nu*/);
        pw.println("ONLINE#" + users);

    }

    private void sendMessage(String user, String message) {


    }

    private void connecter(String user) {
        // TODO:
        users.add(user);
        pw.println("ONLINE#" + users);
    }

}

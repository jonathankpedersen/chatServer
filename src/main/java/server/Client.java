package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer();

    }

    private void connectToServer() {
        //Create socket connection to server
        try {
            Socket socket = new Socket("localhost", 8088);
            //Instantiate reader and writer
            PrintWriter writeToServer = new PrintWriter(socket.getOutputStream());
            BufferedReader readFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Send : CONNECT#Louise
            writeToServer.println("CONNECT#Louise");
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }



    }

}

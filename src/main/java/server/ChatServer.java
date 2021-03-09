package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ChatServer {
BlockingQueue<String> allMessages = new ArrayBlockingQueue<String>(250);

    //Call server with arguments like this: 0.0.0.0 8088 logfile.log
    public static void main(String[] args) throws UnknownHostException {
        //TODO: Lav client-klasse


        ChatServer chatserver = new ChatServer();
        try {
            chatserver.runProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void runProgram() throws IOException {
        ServerSocket server = new ServerSocket(8088);
        while(true){
            Socket client = server.accept();
            //Get reader og writer til client
            PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //CONNECT#kurt client skiver dette
            //Skal bruge navnet kurt til at gemme clientsocket i et hashmap
            String name = "kurt";
            String input = br.readLine();
            System.out.println(input);
            ClientHandler clienthandler = new ClientHandler(name,br,pw,allMessages);
            client.close();
        }
    }


}

package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ChatServer {
    BlockingQueue<String> allMessages = new ArrayBlockingQueue<String>(250);
    HashMap<String, Socket> users = new HashMap<String, Socket>();
    HashMap<String, PrintWriter> allWriters = new HashMap<String, PrintWriter>();


    //Call server with arguments like this: 0.0.0.0 8088 logfile.log
    public static void main(String[] args) throws UnknownHostException {


        ChatServer chatserver = new ChatServer();
        try {
            chatserver.runProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void runProgram() throws IOException {
        ServerSocket server = new ServerSocket(8188);
        Dispatcher dp = new Dispatcher(allMessages,allWriters);
        dp.start();
        while (true) {
            System.out.println("Waiting for client");
            Socket client = server.accept();
            //Get reader og writer til client
            PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //CONNECT#kurt client skriver dette
            //Skal bruge navnet kurt til at gemme clientsocket i et hashmap
            String valg = br.readLine();
            System.out.println(valg);
            String[] valgSplit = valg.split("#");
            if (valgSplit[0].contains("CONNECT")) {
                String name = valgSplit[1];
                users.put(name, client);
                allWriters.put(name, pw);
                ClientHandler cl = new ClientHandler(name, br, pw, allMessages);
                pw.println(users.keySet());
                System.out.println("User connected " + name);
                Thread t = new Thread(cl);
                t.start();

            }


            //String name = "kurt";
            //String input = br.readLine();
            //System.out.println(input);

            //client.close();
        }
    }


}

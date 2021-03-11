package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Dispatcher extends Thread{
    BlockingQueue<String> allMessages;
    HashMap<String, PrintWriter> allWriters;

    public Dispatcher(BlockingQueue<String> allMessages, HashMap<String, PrintWriter> allWriters){
        this.allMessages = allMessages;
        this.allWriters = allWriters;
    }

    public void run(){
        while (true){
            try {
                String input = allMessages.take();
                handleMessage(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleMessage(String input) {
        //SEND#kurt,Lone#hej med dig
        //TODO: find lones printwriter i allWriters mappet
        //TODO: konstruer følgende besked: MESSAGE#kurt#hej med dig og send til lones printwriter
        String[] inputArray = input.split("#");
        String[] modtager = inputArray[1].split(",");
        PrintWriter pw = getPrintWriterByName(modtager[1]);
        String message = "MESSAGE#" + modtager[0] + "#" + inputArray[2];
        pw.println(message);

    }
    private PrintWriter getPrintWriterByName(String name){
        return allWriters.get(name);
    }
}

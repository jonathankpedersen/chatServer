package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {
    ClientHandler cl;
    BlockingQueue<String> allMessage;
    Thread testThread;

    @BeforeEach
    void setUp() {
        String name = "kurt";
        allMessage = new ArrayBlockingQueue<>(250);
        String userInput = "SEND#Lone#hej fra kurt";
        allMessage.add(userInput);
        PrintWriter pw = new PrintWriter(System.out,true);
        BufferedReader br = new BufferedReader(new StringReader(userInput));

        cl = new ClientHandler(name,br,pw,allMessage);
        testThread = new Thread(cl);
    }

    @Test
    void testClientSendMsg() throws InterruptedException {
        testThread.start();
        //testThread.join();
        System.out.println(allMessage);
    }

}
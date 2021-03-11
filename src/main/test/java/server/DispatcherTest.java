package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class DispatcherTest {
    BlockingQueue<String> allMessage;
    HashMap<String, PrintWriter> allwriters;
    ClientHandler ch;
    ClientHandler ch2;

    @BeforeEach
    void setUp() {
        String input = "SEND#kurt#hej";
        String name = "kurt";
        String name2 = "lone";
        allMessage = new ArrayBlockingQueue<>(250);
        allMessage.add(input);
        PrintWriter pw = new PrintWriter(System.out, true);
        PrintWriter pw2 = new PrintWriter(System.out, true);
        BufferedReader br = new BufferedReader(new StringReader(input));
        allwriters = new HashMap<String,PrintWriter>();
        //ch = new ClientHandler(name, br, pw, allMessage, allwriters);
        //ch2 = new ClientHandler(name, br, pw, allMessage, allwriters);


    }
    @Test
    void testHandleMessage() throws InterruptedException{

    }
}
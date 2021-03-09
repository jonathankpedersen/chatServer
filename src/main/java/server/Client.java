package server;

public class Client {
    private String userName;
    private String ipAddress;
    private java.net.Socket socket = null;

    public Client(String userName, String ipAddress, java.net.Socket socket){
        this.userName = userName;
        this.ipAddress = ipAddress;
        this.socket = socket;
    }
    public java.net.Socket getSocket(){
        return this.socket;
    }

}

package tcpobject;
import application.*;
import java.net.ServerSocket;
import java.net.Socket;

import util.NetworkUtil;

public class Server {

    private ServerSocket serverSocket;

    Server() {
        try {
        	System.out.println("hi");
        	
            serverSocket = new ServerSocket(33533);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
                System.out.println("hi2");
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) {
        NetworkUtil nc = new NetworkUtil(clientSocket);
        new ReadThread(nc);
        new WriteThread(nc, "Server");
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}

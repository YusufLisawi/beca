package org.nttdata.sockets.chatroom;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

public class Server {
    private final Hashtable<Socket, DataOutputStream> clients = new Hashtable<>();
    private final ArrayList<String> messages = new ArrayList<>();

    public Server(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on " + serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort() + " ...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected from " + socket);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                clients.put(socket, dataOutputStream);
                new ServerThread(this, socket);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void broadCast(String msg) {
        try {
            synchronized (clients) {
                for (DataOutputStream dataOutputStream : clients.values()) {
                    messages.add(msg);
                    dataOutputStream.writeUTF(msg);
                    dataOutputStream.flush();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Hashtable<Socket, DataOutputStream> getClients() {
        return clients;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public static void main(String[] args) {
        Server server = new Server(9090);
    }
}

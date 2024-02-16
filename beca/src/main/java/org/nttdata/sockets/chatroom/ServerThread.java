package org.nttdata.sockets.chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Server server;
    private final Socket socket;
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;

    public ServerThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.dataOutputStream = server.getClients().get(socket);
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = dataInputStream.readUTF();
                server.broadCast(msg);
            } catch (EOFException e) {
                System.out.println("Client " + socket + " disconnected");
                server.getClients().remove(socket);
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

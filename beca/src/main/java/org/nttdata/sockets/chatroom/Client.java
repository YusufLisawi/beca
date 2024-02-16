package org.nttdata.sockets.chatroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame implements Runnable {

    private String name;
    private String host;
    private int port;
    private Socket socket;

    private JTextArea textArea;
    private JTextField textField;
    private DataOutputStream out;
    private DataInputStream in;


    public Client(String name, String host, int port) {
        super("Client: " + name);
        this.name = name;
        this.host = host;
        this.port = port;

        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = textField.getText();
                try {
                    out.writeUTF(name + " : " + msg);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                textField.setText("");
            }
        });
        add(textField, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        this.setVisible(true);
        this.connect();
    }

    public void connect() {
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            System.out.println("Connected to server");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = in.readUTF();
                textArea.append(msg + "\n");
                textField.setText("");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Client client2 = new Client("Youssef El Aissaoui", "10.131.118.36", 9090);
    }
}

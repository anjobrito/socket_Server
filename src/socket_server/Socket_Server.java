/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket_server;

/**
 *
 * @author server
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import socket_forms.frmServerSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Socket_Server implements Runnable {

    /**
     * @param args the command line arguments
     */
    private String host;
    private int port;
    private JFrame ifrmServerSocket;

    public Socket_Server() {
        this.host = "localhost";
        this.port = 11000;
    }

    public static void main(String[] args) {
        Socket_Server socket = new Socket_Server();
        socket.run();
    }

    @Override
    public void run() {        
            try {
                frmServerSocket ifrmServerSocket = new frmServerSocket();
                ifrmServerSocket.show();
                ServerSocket server = new ServerSocket(this.port);
                System.out.println("The Port " + this.port + " is open !");
                ifrmServerSocket.jLabel2.setText("connected");
                Socket client = server.accept();
                ifrmServerSocket.jTextArea1.setText(client.getInetAddress().getHostAddress());
                System.out.println("Client Conectado " + client.getInetAddress().getHostAddress());
                DataInputStream dataInPutStream = new DataInputStream(client.getInputStream());
                DataOutputStream dataOutPutStream = new DataOutputStream(client.getOutputStream());                 ;
                String mensagem = dataInPutStream.readUTF();
                dataOutPutStream.writeUTF(mensagem);                                                                
                server.close();
                client.close();
            } catch (IOException ex) {
                // System.exit(0);
                Logger.getLogger(Socket_Server.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}

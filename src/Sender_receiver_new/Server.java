package Sender_receiver_new;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ObjectInputStream in = null;
    static ObjectInputStream sender_in = null;
    static ObjectOutputStream receiver_out = null;
    static ObjectOutputStream sender_out = null;

    public static void main(String[] args) throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(6666);
        System.out.println("Waiting for connection");
        Socket socket = welcomeSocket.accept();
        System.out.println("Connection established");

        in = new ObjectInputStream(socket.getInputStream());

        try {
            String message = (String) in.readObject();
            if(message.equalsIgnoreCase("Sender connecting to the server")){

            }else if(message.equalsIgnoreCase("Receiver connecting to the server")){

            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}

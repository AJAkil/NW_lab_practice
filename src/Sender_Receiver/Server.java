package Sender_Receiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<SocketDetails>clientlist = new ArrayList<>();
    static int whoIsconnected=0;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket welcomeSocket = new ServerSocket(6666);
        SocketDetails socketinfo;

        while (true){
            System.out.println("Establishing connections");
            Socket socket = welcomeSocket.accept();
            System.out.println("Connection established");

            System.out.println("Adding to the client list");
            socketinfo = new SocketDetails(socket, new ObjectOutputStream(socket.getOutputStream()), new ObjectInputStream(socket.getInputStream()));
            String register = (String)socketinfo.getOis().readObject(); //blocking
            if(register.equalsIgnoreCase("Sender Connected")){
                socketinfo.setNameOfClient("Sender");
            }else if(register.equalsIgnoreCase("Receiver Connected")){
                socketinfo.setNameOfClient("Receiver");
            }
            clientlist.add(socketinfo);
            Thread serverthread = new ServerThread2(socketinfo);
            serverthread.start();

        }

    }


}

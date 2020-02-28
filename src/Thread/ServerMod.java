package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMod {
    static ArrayList<SocketStuffs> clientlists = new ArrayList<SocketStuffs>();

    public static void main(String[] args) throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(6666);
        SocketStuffs socketStuffs;

        while (true){
            System.out.println("Establishing connections");
            Socket socket = welcomeSocket.accept();
            System.out.println("Connection  established");

            //add to the list
            System.out.println("Adding to the client list");
            socketStuffs = new SocketStuffs(socket, new ObjectInputStream(socket.getInputStream()), new ObjectOutputStream(socket.getOutputStream()));
            clientlists.add(socketStuffs);

            System.out.println("Remote port: " + socket.getPort());
            System.out.println("Local port: " + socket.getLocalPort());

            //opening the threads
            System.out.println("Server Thread started for the client: "+socket.getPort());
            Thread serverSpawn = new ServerThread2(socket);
            serverSpawn.start();

        }


    }
}

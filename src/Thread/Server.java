package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(6666);

        while (true){
            System.out.println("Establishing connections");
            Socket socket = welcomeSocket.accept();
            System.out.println("Connection established");
            System.out.println("Remote port: " + socket.getPort());
            System.out.println("Local port: " + socket.getLocalPort());

            //opening the threads
            Thread worker = new Worker(socket);
            worker.start();


        }

    }
}

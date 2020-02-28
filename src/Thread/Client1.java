package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost",6666);
        System.out.println("Connection is established");
        System.out.println("Remote port: " + socket.getPort());
        System.out.println("Local port: " + socket.getLocalPort());

        Scanner scan = new Scanner(System.in);
        Thread listener = new ClientListener(socket);
        listener.start();
        System.out.println("Started listener thread");

        Thread writer = new ClientWriteThread(socket);
        writer.start();
        System.out.println("Started writer thread");


    }
}

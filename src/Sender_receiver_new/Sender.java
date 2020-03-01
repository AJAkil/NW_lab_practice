package Sender_receiver_new;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost",6666);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        out.writeObject("Sender connecting to the server");

        while (true){
            System.out.println("Waiting for confirmation: ");
            String message = (String)in.readObject();
            System.out.println(message);
            System.out.println("Confirmed from the server");
            System.out.println("writing to the receiver");
            out.writeObject(new Scanner(System.in).nextLine());
            System.out.println("message sent to the receiver");
        }

    }
}

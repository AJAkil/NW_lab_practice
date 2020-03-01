package Sender_Receiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",6666);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        out.writeObject("Sender Connected");
        try{
            String message =(String) in.readObject(); //blocking
            System.out.println(message);
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        }


        while (true){

            System.out.println("message sending: ");
            out.writeObject(new Scanner(System.in).nextLine());
            System.out.println("message sent!");

            try{
                System.out.println("waiting for response from server");
                String message =(String) in.readObject(); //blocking
                System.out.println(message);
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    }
}

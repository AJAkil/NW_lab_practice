package Sender_Receiver;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class  Receiver {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost",6666);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject("Receiver Connected");
        while (true){
            System.out.println("waiting for message from the sender");
            String message = (String)in.readObject();
            System.out.println("message received");
            System.out.println(message);
        }
    }

}

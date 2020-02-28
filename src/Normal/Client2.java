package Normal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;




public class Client2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost",6666);
        System.out.println("Connection established");
        System.out.println("Remote port: "+socket.getPort());
        System.out.println("Local port: "+socket.getLocalPort());
        System.out.println("Local address: "+socket.getLocalAddress());

        //--------connection established here-----------
        //------buffers and IO of connections-------
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        //reading from the server
        String message = (String) in.readObject();
        System.out.println("From the server: "+message);

        //sending the messages to the server
        //out.writeObject(new Scanner(System.in).nextLine());
        out.writeObject("Hello server from client 2");

        /*while (true){
            //reading from the server
            String message = (String) in.readObject();
            System.out.println("From the server: "+message);

            //sending the messages to the server
            out.writeObject(new Scanner(System.in).nextLine());

        }*/




    }

}

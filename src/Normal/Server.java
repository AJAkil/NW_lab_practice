package Normal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket welcomeSocket;

        {
            try {
                welcomeSocket = new ServerSocket(6666);

                while (true){
                    System.out.println("Waiting for connection");
                    Socket socket = welcomeSocket.accept(); //returns a socket
                    System.out.println("Connection established");
                    System.out.println("Remote port: "+socket.getPort());
                    System.out.println("Local port: "+socket.getLocalPort());
                    System.out.println("Local address: "+socket.getLocalAddress());

                    //------connection done here---------

                    //------buffers and IO of connections-------
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    //send message to the client
                    out.writeObject("okay client!");

                    //read message from the client
                    String message = (String)in.readObject();
                    System.out.println("From Client: "+message);

                    /*String message = "-12";
                    while (!message.equalsIgnoreCase("Exit")){

                        //send message to the client
                        out.writeObject("okay client!");

                        //read message from the client
                        message = (String)in.readObject();
                        System.out.println("From Client: "+message);
                    }*/


                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}

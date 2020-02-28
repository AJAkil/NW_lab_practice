package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientListener extends Thread {
    Socket socket;

    ClientListener(Socket socket){
        this.socket = socket;
    }

    //this thread will only be used for listening messages from others
    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true){
                String message = (String) in.readObject();
                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

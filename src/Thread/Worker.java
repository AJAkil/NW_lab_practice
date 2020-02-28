package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class Worker extends Thread {
    private Socket socket;

    Worker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        //The thread is always handling the buffers
        try {
            ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());

            //infinite loop so that a thread sends the date after a single second
            while (true){
                Thread.sleep(1000);
                Date date = new Date();
                out.writeObject(date.toString());
                try {
                    String message = (String) in.readObject();
                    System.out.println(message);
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

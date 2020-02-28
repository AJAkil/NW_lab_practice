package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriteThread extends Thread {
    Socket socket;

    ClientWriteThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            while (true){
                out.writeObject(new Scanner(System.in).nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

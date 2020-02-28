package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread2 extends Thread {
    Socket socket;

    ServerThread2(Socket socket) throws IOException {
        this.socket = socket;

    }

    @Override
    public void run() {

        try {
            ObjectInputStream in = null;
            for(SocketStuffs socketDetails : ServerMod.clientlists){
                if(socketDetails.getSocket().getPort() == socket.getPort()){
                    in = socketDetails.getOis();
                }
            }

            while(true) {
                //System.out.println("infinity started");
                String msg = (String) in.readObject();
                System.out.println(msg+" from client port: "+socket.getPort());

                //Broadcasting message to all others by matching the input ports
                for (SocketStuffs ss:
                     ServerMod.clientlists) {

                    if(ss.getSocket().getPort()!= socket.getPort()){
                        ss.getOos().writeObject(msg);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

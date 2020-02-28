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
                /*for (SocketStuffs ss:
                     ServerMod.clientlists) {

                    if(ss.getSocket().getPort()!= socket.getPort()){
                        ss.getOos().writeObject(msg);
                    }
                }*/

                //Sending message to a specific user
                String[]buffer = msg.split(",");
                int receiver = Integer.parseInt(buffer[1]);
                msg = buffer[0];
                int currentSocketIndex = -1;

                //finding the index of the current socket of the client for which the thread is running, then modifying the message
                for (int i = 0; i < ServerMod.clientlists.size(); i++) {
                    if(ServerMod.clientlists.get(i).getSocket().getPort() == socket.getPort()){
                        currentSocketIndex = i;
                    }
                }

                //this check is done so that I can not send texts to myself
                if(currentSocketIndex!=-1 && (currentSocketIndex+1)!=receiver){
                    msg = "Client" + Integer.toString(currentSocketIndex+1) + ": " + msg;
                    //selecting the specific user and sending the text
                    ServerMod.clientlists.get(receiver-1).getOos().writeObject(msg);
                }else{
                    //giving myself a warning
                    ServerMod.clientlists.get(currentSocketIndex).getOos().writeObject("You cannot send text to yourself");

                }




            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

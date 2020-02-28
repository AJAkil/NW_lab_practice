package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ServerThread extends Thread {

    Socket socket;
    static ArrayList<NetworkUtil> clientlist = new ArrayList<NetworkUtil>();
    NetworkUtil util;

    ServerThread(Socket socket) throws IOException {
        this.socket = socket;

        //add to the list
        System.out.println("Adding to the client list");
        util = new NetworkUtil(socket);
        clientlist.add(util);
    }

    @Override
    public void run() {

        try {
            ObjectInputStream in = null;
            for(NetworkUtil socketDetails : clientlist){
                if(socketDetails.getSocket().getPort() == socket.getPort()){
                    in = socketDetails.getOis();
                }
            }

            while(true) {
                //System.out.println("infinity started");
                String msg = (String) in.readObject();
                System.out.println(msg);
                //System.out.println("repeat");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
}

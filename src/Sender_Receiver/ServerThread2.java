package Sender_Receiver;

import Normal.Client;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerThread2 extends Thread {

    SocketDetails socketDetails;
    int receiverIndex = -1;
    ServerThread2(SocketDetails socketDetails){
        this.socketDetails = socketDetails;
    }

    @Override
    public void run() {
        ObjectInputStream in = null;

        if(this.socketDetails.getNameOfClient().equalsIgnoreCase("Sender")){
            try{
                this.socketDetails.getOos().writeObject("Sender connected successfully");
            }catch (IOException e){
                e.printStackTrace();
            }

        }else if(this.socketDetails.getNameOfClient().equalsIgnoreCase("receiver")){
            try{
                this.socketDetails.getOos().writeObject("Receiver connected successfully");
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        while (true){

            String msg= null;
            if(this.socketDetails.getNameOfClient().equalsIgnoreCase("sender")){
                try {
                    msg = (String)this.socketDetails.getOis().readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

           /* if(receiverIndex!=-1){
                try {
                    if(Server.clientlist.get(receiverIndex).getOis().read() == -1 ){
                        Server.clientlist.remove(receiverIndex);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/


            if(Server.clientlist.size() == 2){

                for (int i = 0; i < Server.clientlist.size() ; i++) {
                    if(Server.clientlist.get(i).getNameOfClient().equalsIgnoreCase("Receiver")){
                        receiverIndex = i;
                        break;
                    }
                }

                if(this.socketDetails.getNameOfClient().equalsIgnoreCase("Sender")){
                    try {
                        Server.clientlist.get(receiverIndex).getOos().writeObject(msg);
                        this.socketDetails.getOos().writeObject("OK");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }else if(Server.clientlist.size() == 1){
                try {
                    this.socketDetails.getOos().writeObject("FAILED");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //approach--------- using a simple flag ----------------

        /*while (true){
            boolean thereIsListener = false;


            for (SocketDetails socketDetails : Server.clientlist) {
                if(socketDetails.getNameOfClient().equalsIgnoreCase("receiver"))
                    thereIsListener = true;
            }

            if(thereIsListener){
                for (SocketDetails socketDetails : Server.clientlist) {
                    if(socketDetails.getNameOfClient().equalsIgnoreCase("Sender")){

                    }

                }
            }else{
                for (SocketDetails socketDetails : Server.clientlist) {
                    if(socketDetails.getNameOfClient().equalsIgnoreCase("Sender")){

                    }

                }
            }

        }*/




    }
}

package Sender_Receiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Serverthread extends Thread {
    Socket socket;
    static ObjectInputStream sender_in = null;
    static ObjectOutputStream receiver_out = null;
    static ObjectOutputStream sender_out = null;

    Serverthread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        boolean flag = false;
        for (SocketDetails sd : Server.clientlist
        ) {
            if (sd.getNameOfClient().equalsIgnoreCase("Sender")) {
                try {
                    sd.getOos().writeObject("Sender connection successful");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (sd.getNameOfClient().equalsIgnoreCase("Receiver")) {
                try {
                    sd.getOos().writeObject("Receiver connection successful");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /*while (true){
            if(Server.clientlist.size() == 2){
                //System.out.println("here");
                for (SocketDetails sd: Server.clientlist
                     ) {
                    if(sd.getNameOfClient().equalsIgnoreCase("Sender")){
                        if(sender_in!=null){
                            sender_out = sd.getOos();
                            sender_in = sd.getOis();
                        }


                    }else if(sd.getNameOfClient().equalsIgnoreCase("Receiver")){
                        if(receiver_out!=null)
                            receiver_out = sd.getOos();
                    }
                }
                //System.out.println("there");

                try {
                    if(sender_in!=null && sender_out!= null){
                        String message = (String)sender_in.readObject();
                        System.out.println("The message is: "+message);
                        receiver_out.writeObject(message);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }else if(Server.clientlist.size() == 1){
                //check to see if the connection is sender or receiver
                if(Server.clientlist.get(0).getNameOfClient().equalsIgnoreCase("Sender")){
                    sender_in = Server.clientlist.get(0).getOis();
                    sender_out = Server.clientlist.get(0).getOos();
                    try {
                        String m = (String)sender_in.readObject();  //BLOCKING
                        System.out.println("Jokhon 1 ta "+m);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        sender_out.writeObject("Failed: 0");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(Server.clientlist.get(0).getNameOfClient().equalsIgnoreCase("receiver")){
                    receiver_out = Server.clientlist.get(0).getOos();
                }
            }
        }*/

        for (SocketDetails sd : Server.clientlist
        ) {
            if (sd.getNameOfClient().equalsIgnoreCase("Sender")) {
                if (sender_out == null && sender_in == null) {
                    System.out.println("here for the sender thread");
                    sender_in = sd.getOis();
                    sender_out = sd.getOos();
                }

            } else if (sd.getNameOfClient().equalsIgnoreCase("receiver")) {
                if (receiver_out == null) {
                    System.out.println("here for the receiver thread");
                    receiver_out = sd.getOos();
                    flag = true;
                }

            }
        }

        /*while (true){

            if(sender_in!=null && receiver_out!=null){
                //System.out.println("case 1");
                try {
                    System.out.println("receiving message");
                    String message = (String) sender_in.readObject();
                    System.out.println("message received");
                    //receiver_out.writeObject(message);
                    sender_out.writeObject("Succesful: 1");

                    //System.out.println("confirmation message sent to the sender");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(sender_in!=null){
                //System.out.println("case 2");
                try {
                    sender_out.writeObject("Failed: 0");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/

        while (true){
            if(receiver_out==null && sender_in!=null && !flag){

                try {
                    //System.out.println("here");
                    sender_out.writeObject("Failed Status: 0");
                }catch (IOException e){
                    e.printStackTrace();
                }

            }else if(receiver_out!=null && sender_in!=null && flag){
                try{
                    String message = (String) sender_in.readObject();
                    System.out.println(message);
                    receiver_out.writeObject(message);
                    sender_out.writeObject("Successful Status: 1");
                }catch (IOException | ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
        }

    }



}


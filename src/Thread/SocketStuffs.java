package Thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketStuffs {
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public SocketStuffs(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }
}

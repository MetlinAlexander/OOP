package metlin;

import static metlin.Server.id;
import static metlin.Server.completedTasks;
import static metlin.Server.tasks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * class represents connector for server.
 */
public class Connector implements Runnable {

    private final int port;

    /**
     * constructor for connector.
     *
     * @param port server port
     */
    public Connector(int port) {
        this.port = port;
    }


    @Override
    public void run() {
        try {
            ServerSocket servSock = new ServerSocket(port);
            ArrayList<Thread> clients = new ArrayList<>();
            int connected = 0;
            servSock.setSoTimeout(1000);
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket clientSock = servSock.accept();
                    Thread clientThread = new Thread(new Handler(clientSock,
                            id,
                            completedTasks,
                            tasks));
                    clientThread.start();
                    clients.add(clientThread);
                    System.out.println("New client connected");
                    connected++;
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket accept timeout occurred");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            joinClients(clients, connected);
            servSock.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * wait all clinets to join.
     *
     * @param clients all threads with clients
     * @param connected num of clients connected
     */
    private void joinClients(ArrayList<Thread> clients, int connected) {
        for (int i = 0; i < connected; i++) {
            try {
                clients.get(i).join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

package metlin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * class repsresents handler for server.
 */
public class Handler implements Runnable {
    private final Socket clientSock;
    private final AtomicInteger id;
    private final AtomicInteger completedTasks;
    private final ArrayList<Integer> tasks;


    /**
     * constructor for cur handler.
     *
     * @param clientSock socket
     * @param id id og client
     * @param completedTasks task completed
     * @param tasks task list
     */
    public Handler(Socket clientSock,
                   AtomicInteger id,
                   AtomicInteger completedTasks,
                   ArrayList<Integer> tasks) {
        this.clientSock = clientSock;
        this.id = id;
        this.completedTasks = completedTasks;
        this.tasks = tasks;
    }

    @Override
    public void run() {
        int currentClient = id.get();
        try {
            Scanner in = new Scanner(clientSock.getInputStream());
            PrintWriter out = new PrintWriter(clientSock.getOutputStream());
            boolean working = true;
            out.println("Connection has been successfully established");
            out.flush();
            while (working) {
                try {
                    currentClient = id.incrementAndGet();
                    int task = tasks.get(currentClient);
                    out.println(task);
                    out.flush();
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    break;
                }
                if (in.hasNextInt() && in.nextInt() == 1) {
                    completedTasks.incrementAndGet();
                    working = false;
                }
            }
        } catch (IOException | IllegalBlockingModeException e) {
            this.id.set(currentClient);
        }
    }
}

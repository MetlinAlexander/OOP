package metlin;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * class represents server.
 */
public class Server {

    static ArrayList<Integer> tasks;
    static final AtomicInteger completedTasks = new AtomicInteger();
    static final AtomicInteger id = new AtomicInteger();
    static int port;

    /**
     * consturctor for server.
     *
     * @param port port to connections
     * @param tasks task list
     */
    public Server(int port, ArrayList<Integer> tasks) {
        this.tasks = tasks;
        this.completedTasks.set(0);
        this.id.set(0);
        this.port = port;
    }

    /**
     * method to start server.
     *
     * @return result
     * @throws InterruptedException exp
     */
    public boolean start() throws InterruptedException {
        Thread mainThread = new Thread(new Connector(this.port));
        mainThread.start();
        while (true) {
            if (tasks.size() <= id.get() || completedTasks.get() != 0) {
                break;
            }
        }
        mainThread.interrupt();
        mainThread.join();
        return completedTasks.get() > 0;
    }
}

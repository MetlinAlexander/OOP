package metlin;

/**
 * class for client thread.
 */

public class ClientThread implements Runnable {

    @Override
    public void run() {
        Client.solver(12345);
    }
}
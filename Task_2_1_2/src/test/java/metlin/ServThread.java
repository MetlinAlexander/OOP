package metlin;

import static metlin.PrimesTest.result;

import java.util.ArrayList;


/**
 * class to simulate server.
 */

public class ServThread implements Runnable {
    private final ArrayList<Integer> data;

    /**
     * constructs new ServerThread.
     *
     * @param data data to solve.
     */
    public ServThread(ArrayList<Integer> data) {
        this.data = data;
    }

    @Override
    public void run() {
        var server = new Server(8000, data);
        try {
            result = server.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
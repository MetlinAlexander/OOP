package utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * class where implemented synchronized queue.
 *
 * @param <T> type of element
 */
public class MySynhronizedQueue<T> {
    private Queue<T> queue = new ArrayDeque<T>();
    private int maxCapacity;

    /**
     * Constructor.
     *
     * @param maxCapacity the maximum number of elements in queue
     */
    public MySynhronizedQueue(final int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Add element to the queue.
     * Wait until queue is < maxCapacity
     *
     * @param elem element to add
     */
    public synchronized void add(final T elem) throws InterruptedException {
        while (queue.size() == maxCapacity) {
            wait();
        }
        queue.add(elem);
        notifyAll();
    }

    /**
     * Remove element from queue.
     * Wait until queue is not empty.
     *
     * @return removed element
     */
    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        var elem = queue.poll();
        notifyAll();
        return elem;
    }

    /**
     * method to check queue is Empty.
     *
     * @return true if empty, else false.
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * method to get len of queue.
     *
     * @return len of queue.
     */
    public int getMaxCapacity() {
        return queue.size();
    }

    /**
     * method to wait when queue is become empty.
     */
    public synchronized void waitEmpty() throws InterruptedException {
        while (!queue.isEmpty()) {
            wait();
        }
    }

    /**
     * Method to get more than one element from queue.
     *
     * @param amount - how manu elements to pop
     * @return list of elements
     */
    public synchronized ArrayList<T> getSome(int amount)
            throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        ArrayList<T> ans = new ArrayList<>();
        while (!queue.isEmpty() && amount > 0) {
            ans.add(queue.poll());
            amount -= 1;
        }
        notifyAll();
        return ans;
    }
}

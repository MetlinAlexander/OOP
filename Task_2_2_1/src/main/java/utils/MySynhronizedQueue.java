package utils;

import java.util.ArrayDeque;
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
     * @throws InterruptedException
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
     * @throws InterruptedException
     */
    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        var elem = queue.poll();
        notifyAll();
        return elem;
    }

    public synchronized boolean isEmpty(){
        return queue.isEmpty();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}

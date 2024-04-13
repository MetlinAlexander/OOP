package myPizzeria;

import utils.MySynhronizedQueue;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class for pizzeria.
 */
public class Pizzeria {
    private ArrayList<Baker> bakers;
    private ArrayList<Courier> couriers;

    public MySynhronizedQueue<Order> toBake;
    public MySynhronizedQueue<Order> toDeliver;
    public AtomicBoolean isOpen;
    public AtomicInteger delevered;
    public AtomicInteger cooked;
    private int workingTime;
    private AtomicInteger orderId;
    private ArrayList<Thread> bakerThread = new ArrayList<>();
    private ArrayList<Thread> courierThread = new ArrayList<>();
    public static final Logger logger = LogManager.getLogger();

    /**
     * constuctor for pizzeria.
     *
     * @param workingTime working day time
     * @param storageSize size of storage
     * @param bakers all bakers
     * @param couriers all couriers
     */
    public Pizzeria(final int workingTime,
                    final int storageSize,
                    ArrayList<Baker> bakers,
                    ArrayList<Courier> couriers) {
        this.workingTime = workingTime;
        this.bakers = bakers;
        this.couriers = couriers;
        this.toBake = new MySynhronizedQueue<>(bakers.size());
        this.toDeliver = new MySynhronizedQueue<>(storageSize);
        this.isOpen = new AtomicBoolean(false);
        this.delevered = new AtomicInteger(0);
        this.cooked = new AtomicInteger(0);
        this.orderId = new AtomicInteger(0);
    }

    /**
     * The working day begins with the opening of the pizzeria.
     * Then all the workers start working
     */
    void StartWorkingDay() {
        isOpen.set(true);
        for (Baker baker : bakers) {
            baker.setWorkPlace(this);
            baker.start();
        }
        for (Courier courier : couriers) {
            courier.setWorkPlace(this);
            courier.start();
        }
    }

    /**
     * First we close the pizzeria.
     * Then the bakers finish all the orders and stop working.
     * And then the couriers
     * take all the remaining orders and stop working.
     * @throws InterruptedException
     */
    void ClosePizzeria() throws InterruptedException {
        isOpen.set(false);

        toBake.waitEmpty();

        for (Baker baker : bakers) {
            if (baker.getState() == Thread.State.WAITING) {
                baker.interrupt();
            } else {
                baker.join();
            }
        }
        logger.info("Bakers finished cook everything!");
        toDeliver.waitEmpty();

        for (Courier courier : couriers) {
            if (courier.getState() == Thread.State.WAITING) {
                courier.interrupt();
            } else {
                courier.join();
            }
        }
        logger.info("Couriers finished delevired everything!");
    }

    /**
     * typical working day of pizzeria.
     */
    void PizzeriaDay() {
        try {
            StartWorkingDay();
            logger.info("Pizzeria start working");
            Thread.sleep(workingTime);
            logger.info(
                    "Pizzeria preparing to close and stop taking new orders");
            ClosePizzeria();
            logger.info("Pizzeria closed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method to collect new orders from clients.
     *
     * @param curOrder new order
     * @return true if pizzeria take order else false
     */
    public boolean getOrder(Order curOrder) {
        try {
            if (!isOpen.get()) {
                return false;
            }
            curOrder.setOrderId(orderId.getAndAdd(1));
            toBake.add(curOrder);
            logger.info("Order {} is added to queue", curOrder.getOrderId());
            return true;
        } catch (InterruptedException err) {
            throw new RuntimeException(err);
        }
    }
}

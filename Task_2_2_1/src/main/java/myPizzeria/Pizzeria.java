package myPizzeria;

import utils.MySynhronizedQueue;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Pizzeria {
    private ArrayList<Baker> bakers;
    private ArrayList<Courier> couriers;

    public MySynhronizedQueue<Order> toBake;
    public MySynhronizedQueue<Order> toDeliver;
    public AtomicBoolean isOpen;
    public AtomicInteger delevered;
    public AtomicInteger cooked;

    private int workingTime;
    private int id;

    public Pizzeria(int workingTime, int storageSize, ArrayList<Baker> bakers, ArrayList<Courier> couriers){
        this.workingTime = workingTime;
        this.bakers = bakers;
        this.couriers = couriers;
        this.toBake = new MySynhronizedQueue<>(storageSize);
        this.toDeliver = new MySynhronizedQueue<>(bakers.size());
        this.isOpen = new AtomicBoolean(false);
        this.delevered = new AtomicInteger(0);
        this.cooked = new AtomicInteger(0);
    }

    private void startWorkingDay() {
        isOpen.set(true);
        for (Baker baker : bakers) {
            baker.setWorkPlace(this);
//            baker.start();
        }
        for (Courier courier : couriers) {
            courier.setWorkPlace(this);
//            courier.start();
        }
    }

    public void workingDay() throws InterruptedException {
        startWorkingDay();

//        Thread.sleep(workingDayTime);
//        finishWorkingDay();

    }

    private void finishWorkingDay() {
        isOpen.set(false);

        while (this.cooked.get() != (this.id - 1)) {
        }
//        for (Baker baker : bakers) {
//            baker.interrupt();
//        }
        while (this.delevered.get() != (this.id - 1)) {
        }
//        for (Courier courier : couriers) {
//            courier.interrupt();
//        }
    }

    public boolean sendOrder(Order order) throws InterruptedException {
        if (isOpen.get()) {
            order.setOrderId(id);
            id++;
            toBake.add(order);
            return true;
        } else {
            return false;
        }
    }
}

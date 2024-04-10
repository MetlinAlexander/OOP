package myPizzeria;

import java.util.ArrayList;

public class Courier extends Thread{
    private String name;
    private int bagCap;

    private Pizzeria workPlace;

    public Courier(String name, int bagCap){
        this.name = name;
        this.bagCap = bagCap;
    }

    public void setWorkPlace(Pizzeria workPlace) {
        this.workPlace = workPlace;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            int totalDeliveryTime = 0;
            ArrayList<Order> pickedOrders = new ArrayList<>();
            try {
                int amountOfPickedPizzas = this.bagCap;
                int count = workPlace.toDeliver.getMaxCapacity();
                while (!workPlace.isOpen.get()) {
                    if (count < bagCap && (count != 0)) {
                        amountOfPickedPizzas = count;
                        break;
                    }
                    count = workPlace.toDeliver.getMaxCapacity();
                }

                for (int i = 0; i < amountOfPickedPizzas; i++) {
                    Order order = workPlace.toDeliver.get();
                    pickedOrders.add(order);
                    totalDeliveryTime += order.getDeleveryTime();
                }

                Thread.sleep(totalDeliveryTime);
                workPlace.delevered.getAndAdd(pickedOrders.size());
            } catch (InterruptedException e) {

            }
        }
    }
}

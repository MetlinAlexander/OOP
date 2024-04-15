package pizzeriapack;

import java.util.ArrayList;

import static pizzeriapack.Pizzeria.logger;

/**
 * class for couriers.
 */
public class Courier extends Thread {
    private String name;
    private int bagCap;

    private Pizzeria workPlace;

    /**
     * constuctor for courier.
     *
     * @param name name of courier
     * @param bagCap capacity of bag
     */
    public Courier(final String name, final int bagCap) {
        this.name = name;
        this.bagCap = bagCap;
    }

    /**
     * set place where courier work.
     *
     * @param workPlace where courier work
     */
    public void setWorkPlace(final Pizzeria workPlace) {
        this.workPlace = workPlace;
    }

    /**
     * run method for courier thread.
     */
    @Override
    public void run() {
        while (!Thread.interrupted()
                && (workPlace.isOpen.get() || !workPlace.toDeliver.isEmpty())) {
            try {
                ArrayList<Order> orders = workPlace.toDeliver
                        .getSome(this.bagCap);
                int totalTimeSleep = 0;
                StringBuffer totalBag = new StringBuffer();
                for (int i = 0; i < orders.size(); i++) {
                    totalTimeSleep += orders.get(i).getDeleveryTime();
                    totalBag.append("|");
                    totalBag.append(orders.get(i).getOrderId());
                }
                logger.info("Courier {} start deliver orders: {}",
                        this.name, totalBag);
                Thread.sleep(totalTimeSleep);
                logger.info("Courier {} delivered orders: {}",
                        this.name, totalBag);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

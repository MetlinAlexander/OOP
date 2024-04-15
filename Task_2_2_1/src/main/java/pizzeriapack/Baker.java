package pizzeriapack;

import static pizzeriapack.Pizzeria.logger;

/**
 * class for bakers.
 */
public class Baker extends Thread {
    private String name;
    private int bakingTime;
    private Pizzeria workPlace;

    /**
     * constructor for baker.
     *
     * @param name name of Baker
     * @param bakingTime how much this Baker cooks pizza
     */
    public Baker(final String name, final int bakingTime) {
        this.name = name;
        this.bakingTime = bakingTime;
    }

    /**
     * set where this baker work.
     *
     * @param pizzeria - work place
     */
    public void setWorkPlace(final Pizzeria pizzeria) {
        this.workPlace = pizzeria;
    }

    /**
     * run method for baker thread.
     */
    @Override
    public void run() {
        while (!Thread.interrupted()
                && (workPlace.isOpen.get() || !workPlace.toBake.isEmpty())) {
            try {
                Order curOrder = workPlace.toBake.get();
                logger.info("Baker {} has started to cook order {}",
                        this.name, curOrder.getOrderId());
                Thread.sleep(bakingTime);
                logger.info("Baker {} already cooked order {}",
                        this.name, curOrder.getOrderId());
                workPlace.toDeliver.add(curOrder);
                logger.info("Baker {} move order {} to storage",
                        this.name, curOrder.getOrderId());
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

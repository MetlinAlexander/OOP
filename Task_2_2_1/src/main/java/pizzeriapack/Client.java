package pizzeriapack;

import java.util.ArrayList;
import java.util.Random;

/**
 * class to imitate clients.
 */
public final class Client {

    private static final Random random = new Random();

    /**
     * hide constuctor for utility class.
     *
     */
    private Client() { }

    /**
     * client randomly choose orders from list of all possible orders.
     *
     * @param pizzeria pizzeria where client order something
     * @param orders all possible orders
     * @param maxWait interval between new order
     */
    public static void RequestRandomOrders(final Pizzeria pizzeria,
                                           final ArrayList<Order> orders,
                                           final int maxWait) {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(random.nextInt(maxWait) + 1);
                boolean ans = pizzeria
                        .getOrder(orders.get(random.nextInt(orders.size())));
                if (!ans) {
                    return;
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

package pizzeriapack;

/**
 * class for order object.
 */
public class Order {
    private String address;
    private int deleveryTime;

    private int orderId;

    /**
     * constructor for order object.
     *
     * @param address adress to deliver
     * @param deleveryTime time to get to this place
     */
    public Order(final String address, final int deleveryTime) {
        this.address = address;
        this.deleveryTime = deleveryTime;
    }

    /**
     * Set orderid to this order.
     *
     * @param orderId int unique order id
     */
    public void setOrderId(final int orderId) {
        this.orderId = orderId;
    }

    /**
     * get delivery time.
     *
     * @return int delivery time
     */
    public int getDeleveryTime() {
        return deleveryTime;
    }

    /**
     * get order id.
     *
     * @return int order id
     */
    public int getOrderId() {
        return orderId;
    }
}

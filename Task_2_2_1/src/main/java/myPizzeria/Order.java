package myPizzeria;

public class Order {
    private String address;
    private int deleveryTime;

    private int orderId;
    public Order(String address, int deleveryTime){
        this.address = address;
        this.deleveryTime = deleveryTime;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeleveryTime() {
        return deleveryTime;
    }
}

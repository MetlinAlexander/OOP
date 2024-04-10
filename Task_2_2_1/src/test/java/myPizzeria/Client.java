package myPizzeria;

import java.util.ArrayList;

public class Client extends Thread{

    public Client(Pizzeria pizzeria, ArrayList<Order> orders) throws InterruptedException {
        for(Order curOrder : orders){
            boolean result = pizzeria.sendOrder(curOrder);
            if (!result) {
                break;
            }
            Thread.sleep(15);
        }
    }
}

import myPizzeria.Order;
import myPizzeria.Pizzeria;
import utils.JsonReader;
import utils.MySynhronizedQueue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws InterruptedException, IOException, org.json.simple.parser.ParseException {
        System.out.println("Hello");
        MySynhronizedQueue<Integer> test = new MySynhronizedQueue<Integer>(12);
        test.add(124);
        System.out.println(test.get());
        ArrayList<Order> orders = JsonReader.ordersRead("src/main/resources/orders.json");
        Pizzeria mypizza = JsonReader.readPizzeria("src/main/resources/workers.json");
//        List<Order> orders = JsonReader.ordersRead("src/main/resources/orders.json");
    }
}

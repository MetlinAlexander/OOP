package pizzatest;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import pizzeriapack.Client;
import pizzeriapack.Order;
import pizzeriapack.Pizzeria;
import utils.JsonReader;

class PizzeriaTest {

    @Test
    void runTest() throws IOException, ParseException, InterruptedException {
        ArrayList<Order> orders = JsonReader.ordersRead("src/main/resources/orders.json");
        Pizzeria pizzeria = JsonReader.readPizzeria("src/main/resources/workers.json");
        Thread pizzeriaThread = new Thread(() -> {
            pizzeria.pizzeriaDay();
        });
        Thread clientThread = new Thread(() -> {
            Client.requestRandomOrders(pizzeria, orders, 50);
        });


        pizzeriaThread.start();
        clientThread.start();
        pizzeriaThread.join();
        clientThread.interrupt();
    }
}
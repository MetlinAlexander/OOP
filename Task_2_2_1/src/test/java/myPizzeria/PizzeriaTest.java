package myPizzeria;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import utils.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PizzeriaTest {

    @Test
    void runTest() throws IOException, ParseException, InterruptedException {
        ArrayList<Order> orders = JsonReader.ordersRead("src/main/resources/orders.json");
        Pizzeria pizzeria = JsonReader.readPizzeria("src/main/resources/workers.json");
        var pizzeriaThread = new Thread(() -> {
            try {
                pizzeria.workingDay();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var clientThread = new Thread(() -> {
            try {
                new Client(pizzeria, orders);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        pizzeriaThread.start();
        clientThread.start();
        clientThread.join();
        pizzeriaThread.join();
    }
}
package utils;

import myPizzeria.Baker;
import myPizzeria.Courier;
import myPizzeria.Order;
import myPizzeria.Pizzeria;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Utility class for reading pizzeria config json files.
 */
public final class JsonReader {

    /**
     * private constrictor for utility class.
     */
    private JsonReader() { }

    /**
     * Methot to read data from orders.json.
     *
     * @param path path to the file
     * @return ArrayList of order objects
     */
    public static ArrayList<Order> ordersRead(final String path) {
        ArrayList<Order> orders = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            // Парсинг JSON-файла и преобразование его в массив объектов
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            //итерация по массиву объектов
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                //чтение данных из JSON объекта
                String address = (String) jsonObject.get("address");
                long deliveryTime = (long) jsonObject.get("deleveryTime");
                orders.add(new Order(address, (int) deliveryTime));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return orders;
    }

    /**
     * Method to read data from workers.json.
     *
     * @param path path to the file
     * @return pizzeria object
     */
    public static Pizzeria readPizzeria(final String path) {
        ArrayList<Baker> bakers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        long storageSize = 0;
        long workingTime = 0;
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            //Парсинг JSON-файла и преобразование его в объект
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            //Чтение данных из JSON объекта
            storageSize = (long) jsonObject.get("storageSize");
            workingTime = (long) jsonObject.get("workingTime");

            //Чтение списка пекарей
            JSONArray bakersArray = (JSONArray) jsonObject.get("bakers");
            for (Object bakerObj : bakersArray) {
                JSONObject baker = (JSONObject) bakerObj;
                String name = (String) baker.get("name");
                long bakingTime = (long) baker.get("bakingTime");
                bakers.add(new Baker(name, (int) bakingTime));
            }

            //Чтение списка курьеров
            JSONArray couriersArray = (JSONArray) jsonObject.get("couries");
            for (Object courierObj : couriersArray) {
                JSONObject courier = (JSONObject) courierObj;
                String name = (String) courier.get("name");
                long bagCapacity = (long) courier.get("bagCap");
                couriers.add(new Courier(name, (int) bagCapacity));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new Pizzeria((int) workingTime,
                            (int) storageSize,
                            bakers,
                            couriers);
    }

}

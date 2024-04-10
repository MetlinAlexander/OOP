package utils;

import myPizzeria.Baker;
import myPizzeria.Courier;
import myPizzeria.Order;
import myPizzeria.Pizzeria;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class JsonReader {

    public static ArrayList<Order> ordersRead(String path) throws IOException, ParseException {
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
                long deliveryTime = (long) jsonObject.get("deleveryTime"); // Преобразование к числовому типу
                orders.add(new Order(address, (int) deliveryTime));
                //вывод данных
                System.out.println("Address: " + address + ", Delivery Time: " + deliveryTime);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static Pizzeria readPizzeria(String path){
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

            System.out.println("Storage Size: " + storageSize);
            System.out.println("Working Time: " + workingTime);

            //Чтение списка пекарей
            JSONArray bakersArray = (JSONArray) jsonObject.get("bakers");
            System.out.println("Bakers:");
            for (Object bakerObj : bakersArray) {
                JSONObject baker = (JSONObject) bakerObj;
                String name = (String) baker.get("name");
                long bakingTime = (long) baker.get("bakingTime");
                bakers.add(new Baker(name, (int) bakingTime));
                System.out.println("- Name: " + name + ", Baking Time: " + bakingTime);
            }

            //Чтение списка курьеров
            JSONArray couriersArray = (JSONArray) jsonObject.get("couries");
            System.out.println("Couriers:");
            for (Object courierObj : couriersArray) {
                JSONObject courier = (JSONObject) courierObj;
                String name = (String) courier.get("name");
                long bagCapacity = (long) courier.get("bagCap");
                couriers.add(new Courier(name, (int) bagCapacity));
                System.out.println("- Name: " + name + ", Bag Capacity: " + bagCapacity);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new Pizzeria((int) workingTime, (int) storageSize, bakers, couriers);
    }

}

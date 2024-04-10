package myPizzeria;

public class Baker extends Thread{
    private String name;
    private int bakingTime;
    private Pizzeria workPlace;

    public Baker(String name, int bakingTime){
        this.name = name;
        this.bakingTime = bakingTime;
    }

    public void setWorkPlace(Pizzeria pizzeria){
        this.workPlace = pizzeria;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Order order = workPlace.toBake.get();
                Thread.sleep(this.bakingTime);
                workPlace.toDeliver.add(order);
                workPlace.cooked.getAndIncrement();
            } catch (InterruptedException e) {

            }
        }
    }

}

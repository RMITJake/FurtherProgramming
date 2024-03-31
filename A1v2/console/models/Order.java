package console.models;

public class Order extends Event{
    private double discountAmount;
    private int priceperhour;

    public Order(String client, String title, String artist, String date, String time, String target, int duration, String type, String category){
        super(client, title, artist, date, time, target, duration, type, category);
    }

    public Order(String client, String title, String artist, String date, String time, String target, int duration, String type, String category, int priceperhour){
        super(client, title, artist, date, time, target, duration, type, category);
        this.priceperhour = priceperhour;
    }

    public int getPricePerHour(){
        return this.priceperhour;
    }

    public double getTotalPrice(){
        return this.priceperhour * this.getDuration();
    }


    public double calculateDiscount(double input){
        return input - discountAmount;
    }

    public double calculatePrice(int priceperhour, int hours){
        int totalPrice = priceperhour * hours;
        return totalPrice;
    }

    public void display(){}
}
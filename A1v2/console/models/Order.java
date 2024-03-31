package console.models;

public class Order{
    private double discountAmount;
    private int priceperhour;

    public Order(Event event, Venue venue){
        this.event = event;
        this.venue = venue;
    }

    // public int getPricePerHour(){
    //     return this.priceperhour;
    // }

    public double getTotalPrice(){
        return this.venue.priceperhour * this.event.getDuration();
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
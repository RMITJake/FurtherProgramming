package console.models;

public class Order{
    private double discountAmount;
    private int priceperhour;
    private Event event;
    private Venue venue;

    public Order(Event event, Venue venue){
        this.event = event;
        this.venue = venue;
    }

    public Event getEvent(){ return this.event; }
    public Venue getVenue(){ return this.venue; }

    // public int getPricePerHour(){
    //     return this.priceperhour;
    // }

    public double getTotalPrice(){
        return this.venue.getPricePerHour() * this.event.getDuration();
    }


    public double calculateDiscount(double input){
        return input - discountAmount;
    }

    public double calculatePrice(){
        int totalPrice = this.venue.getPricePerHour() * this.event.getDuration();
        return totalPrice;
    }

    public double getBrokerFee(){
        return calculatePrice() / 10;
    }

    public void display(){}
}
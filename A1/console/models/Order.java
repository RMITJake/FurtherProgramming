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

    public int getPricePerHour(){
        return this.priceperhour;
    }

    public double getTotalPrice(){
        return this.venue.getPricePerHour() * this.event.getDuration();
    }


    public double calculateDiscount(){
        // get 10% of total price
        discountAmount = getTotalPrice() / 10;
        // get 10% of discount (1% of total price)
        return discountAmount / 10;
    }

    public double calculatePrice(){
        double totalPrice = this.venue.getPricePerHour() * this.event.getDuration() - calculateDiscount();
        return totalPrice;
    }

    public double getBrokerFee(){
        return calculatePrice() / 10;
    }

    public void display(){}
}
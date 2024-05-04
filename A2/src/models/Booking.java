package src.models;
import java.util.List;
import java.time.LocalDateTime;
import src.handlers.DatabaseHandler;

public class Booking{
    private double discountAmount;
    private int priceperhour;
    private Event event;
    private Venue venue;
    private int compatibilityScore;

    public Booking(Event event, Venue venue){
        this.event = event;
        this.venue = venue;
        this.compatibilityScore = calculateCompatibilityScore();
        this.venue.setCompatibilityScore(this.compatibilityScore);
    }

    public Booking(Event event, Venue venue, int compatibilityScore){
        this.event = event;
        this.venue = venue;
        this.compatibilityScore = compatibilityScore;
    }

    public Event getEvent(){ return this.event; }
    public Venue getVenue(){ return this.venue; }

    public void setCompatibilityScore(int compatibilityScore){ this.compatibilityScore = compatibilityScore; }
    public int getCompatibilityScore(){ return this.compatibilityScore; }
    public int calculateCompatibilityScore(){
        int score = 0;
        if(matchAvailable()){ score++; }
        if(matchCapacity()){ score++; }
        if(matchType()){ score++; }
        if(matchCategory()){ score++; }
        return score;
    }

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

////////////////////
// MATCH CRITERIA //
////////////////////
    public boolean matchAvailable(){
        List<Event> eventList = DatabaseHandler.readVenueEvents(this.venue.getName());
        // booking list needs to be list of events from one venue
        LocalDateTime eventStart = this.event.getDateTime();
        LocalDateTime eventEnd = this.event.getEventFinish();

        boolean result = true;
        for(Event event : eventList){
            // start during finish during
            if(eventStart.compareTo(event.getDateTime()) >= 0 && eventEnd.compareTo(event.getEventFinish()) < 0){
                result = false;
            }
            // start before finish during
            else if(eventStart.compareTo(event.getDateTime()) < 0 && eventEnd.compareTo(event.getDateTime()) >= 0){
                result = false;
            }
            // start during finish after
            if(eventStart.compareTo(event.getDateTime()) >= 0 && eventEnd.compareTo(event.getDateTime()) > 0){
                result = false;
            }
        }

        return result;
    }

    public boolean matchCapacity(){ 
        // matches event target(capacity)
        if(this.venue.getCapacity() >= this.event.getTarget()){
            return true;
        }
        return false;
    }

    public boolean matchType(){ 
        String[] suitableFor = this.venue.getSuitableFor();
        for(String string : suitableFor){
            try{
                if(this.event.getType().toLowerCase().equals(string.toLowerCase())){
                    return true;
                }
            } catch(NullPointerException NPE){
                System.err.println("venue not found");
            }
        }
        return false;
    }

    public boolean matchCategory(){ 
        if(this.venue.getCategory().toLowerCase().contains(this.event.getCategory().toLowerCase())){
            return true;
        }
        return false;
    }
// END MATCH CRITERIA //
}
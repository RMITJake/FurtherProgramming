package src.models;

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
        if(matchCapacity(this.venue, this.event)){ score++; }
        if(matchCategory(this.venue, this.event)){ score++; }
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
    public boolean matchCapacity(Venue venue, Event event){ 
        // matches event target(capacity)
        if(venue.getCapacity() >= event.getTarget()){
            return true;
        }
        return false;
    }

    public boolean matchType(Venue venue, Event event){ 
        String[] suitableFor = venue.getSuitableFor();
        for(String string : suitableFor){
            try{
                if(event.getType().toLowerCase().equals(string.toLowerCase())){
                    return true;
                }
            } catch(NullPointerException NPE){
                System.err.println("venue not found");
            }
        }
        return false;
    }

    public boolean matchCategory(Venue venue, Event event){ 
        if(venue.getCategory().toLowerCase().contains(event.getCategory().toLowerCase())){
            return true;
        }
        return false;
    }
// END MATCH CRITERIA //
}
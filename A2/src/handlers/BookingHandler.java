package src.handlers;
import src.models.Venue;
import src.models.Event;
import src.models.Booking;
import java.util.List;

public class BookingHandler {

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

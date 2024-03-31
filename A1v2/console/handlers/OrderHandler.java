package console.handlers;

import java.util.HashMap;
import console.models.Venue;
import console.models.Event;
import console.models.Order;

public class OrderHandler {
    private RequestHandler request;
    private VenueHandler venue;

    public OrderHandler(){}
    public OrderHandler(RequestHandler request, VenueHandler venue){
        this.request = request;
        this.venue = venue;
    }

    HashMap<Venue, Integer> autoMatchEvent(HashMap<Integer, Venue> venueList, Event event){
        HashMap<Venue, Integer> matchList = new HashMap<Venue, Integer>();
        int matchScore;
        
        for(int venue : venueList.keySet()){
            matchScore = 0;
            // matchList = new HashMap<>();

            if(matchCapacity(venueList.get(venue), event)){ matchScore++; }
            if(matchType(venueList.get(venue), event)){ matchScore++; }
            if(matchCategory(venueList.get(venue), event)){ matchScore++; }

            matchList.put(venueList.get(venue), matchScore);

// START DEBUGGING
/////////////////////////////////////////////////////////////////////////////
            // for(Venue id : matchVenue.keySet()){
                // System.out.printf("id: %s, venue %s\n", id.getName(), matchVenue.get(id));
            // }
/////////////////////////////////////////////////////////////////////////////
// END DEBUGGING
        }
        return matchList;
    }


////////////////////
// MATCH CRITERIA //
////////////////////
    boolean matchDateTime(){ 
        // matches venue date
        return null;
    }

    boolean matchCapacity(Venue venue, Event event){ 
        // matches event target(capacity)
        if(venue.getCapacity() >= event.getTarget()){
            return true;
        }
        return false;
    }

    boolean matchDuration(){ 
        return null;
    }

    boolean matchType(Venue venue, Event event){ 
        String[] suitableFor = venue.getSuitableFor();
        for(String string : suitableFor){
            if(event.getType().toLowerCase().equals(string.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    boolean matchCategory(Venue venue, Event event){ 
        if(event.getCategory().toLowerCase().equals(venue.getCategory().toLowerCase())){
            return true;
        }
        return false;
    }
// END MATCH CRITERIA //
}

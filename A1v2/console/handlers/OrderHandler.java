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

    void autoMatchEvent(HashMap<Integer, Venue> venueList, Event event){
        HashMap<Event, HashMap<Venue, Integer>> matchList = new HashMap<>();
        HashMap<Venue, Integer> matchVenue = null;
        int matchScore;
        
        for(int venue : venueList.keySet()){
            matchScore = 0;
            matchVenue = new HashMap<>();
            // switch(true){
                // case matchDate():
                    // matchScore++;
                // case matchTime():
                // case (matchCapacity(venueList.get(venue), event)):
                    // matchScore++;
                // case matchDuration():
                    // matchScore++;
                // case matchType():
                    // matchScore++;
                // case matchCategory():
                    // matchScore++;
                // default:
            // }
            if(matchCapacity(venueList.get(venue), event)){ matchScore++; }
            if(matchType(venueList.get(venue), event)){ matchScore++; }
            matchVenue.put(venueList.get(venue), matchScore);

// START DEBUGGING
/////////////////////////////////////////////////////////////////////////////
            for(Venue id : matchVenue.keySet()){
                System.out.printf("id: %s, venue %s\n", id.getName(), matchVenue.get(id));
            }
/////////////////////////////////////////////////////////////////////////////
// END DEBUGGING
        }
        matchList.put(event, matchVenue);
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
            if(event.getType().equals(string)){
                return true;
            }
        }
        return false;
    }

    boolean matchCategory(){ 
        return null;
    }
// END MATCH CRITERIA //
}

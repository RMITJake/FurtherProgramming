package src.handlers;

import java.util.HashMap;
import src.models.Venue;
import src.models.Event;
import src.models.Booking;

public class OrderHandler {

    // Match an event with a venue from the venueList
    // The order list input allows checking for checking date/time matches
    HashMap<Venue, Integer> autoMatchEvent(HashMap<Integer, Venue> venueList, Event event, HashMap<Integer, Booking> orderList){
        HashMap<Venue, Integer> matchList = new HashMap<Venue, Integer>();
        int matchScore;
        
        for(int venue : venueList.keySet()){
            matchScore = 0;
            
            // Match criteria
            if(matchCapacity(venueList.get(venue), event)){ matchScore++; }
            if(matchType(venueList.get(venue), event)){ matchScore++; }
            if(matchCategory(venueList.get(venue), event)){ matchScore++; }
            matchList.put(venueList.get(venue), matchScore);
        }
        return matchList;
    }

    HashMap<Event, Venue> getBestMatch(HashMap<Event, HashMap<Venue, Integer>> matchedList){
        HashMap<Event, Venue> filteredEvents = new HashMap<>();
        Venue bestMatch;
        int matchValue;
        for(Event event : matchedList.keySet()){
            bestMatch = null;
            matchValue = 0;
            for(Venue venue : matchedList.get(event).keySet()){
                if(matchedList.get(event).get(venue) > matchValue){
                    bestMatch = venue;
                    matchValue = matchedList.get(event).get(venue);
                }
            }
            filteredEvents.put(event, bestMatch);
        }
        return filteredEvents;
    }

    Booking newOrder(Event event, Venue venue){
        return new Booking(event, venue);
    }

    HashMap<Integer, Booking> generateOrders(HashMap<Integer, Booking> orderList, HashMap<Event, Venue> matchedList){
        int id = 0;
        for(Event event : matchedList.keySet()){
            id++;
            orderList.put(id, new Booking(event, matchedList.get(event)));
        }
        return orderList;
    }

////////////////////
// MATCH CRITERIA //
////////////////////
    boolean matchDateTime(Venue venue, Event event, HashMap<Integer, Booking> orderList){ 
        for(int orderId : orderList.keySet()){
            if(!matchVenue(venue, orderList.get(orderId))){ return false; }

            if(matchDate(event, orderList.get(orderId))){ return false; }

            if(matchTime(event, orderList.get(orderId))){ return false; }
        }
        return true;
    }

    public boolean matchVenue(Venue venue, Booking order){
        if(venue.getName().equals(order.getVenue().getName())){
            return true;
        }
        return false;
    }

    public boolean matchDate(Event event, Booking order){
        if(event.getDate().equals(order.getEvent().getDate())){ return true; }
        return false;
    }

    public boolean matchTime(Event event, Booking order){
        String[] eventTimeSplit = event.getTime().split(":");
        int eventTimeInt = Integer.parseInt(eventTimeSplit[0]);
        String[] orderTimeSplit = order.getEvent().getTime().split(":");
        int orderTimeInt = Integer.parseInt(orderTimeSplit[0]);
        
        if(eventTimeInt == orderTimeInt){
            return true;
        }

        if(eventTimeInt + event.getDuration() < orderTimeInt){
            return false;
        }

        if(eventTimeInt <= orderTimeInt + order.getEvent().getDuration()){
            return true;
        }

        return false;
    }

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

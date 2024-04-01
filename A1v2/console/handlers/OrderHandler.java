package console.handlers;

import java.util.ArrayList;
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

    HashMap<Venue, Integer> autoMatchEvent(HashMap<Integer, Venue> venueList, Event event, HashMap<Integer, Order> orderList){
        HashMap<Venue, Integer> matchList = new HashMap<Venue, Integer>();
        int matchScore;
        
        for(int venue : venueList.keySet()){
            matchScore = 0;

            if(matchCapacity(venueList.get(venue), event)){ matchScore++; }
            if(matchType(venueList.get(venue), event)){ matchScore++; }
            if(matchCategory(venueList.get(venue), event)){ matchScore++; }
            if(matchDateTime(venueList.get(venue), event, orderList)){ matchScore++; } else {
                matchScore = 0;
                // System.out.println("DEBUG!! VENUE IS ALREADY BOOKED");
            }
            if(matchScore != 0) {
                matchList.put(venueList.get(venue), matchScore);
            }
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

    Order newOrder(Event event, Venue venue){
        return new Order(event, venue);
    }

    HashMap<Integer, Order> generateOrders(HashMap<Integer, Order> orderList, HashMap<Event, Venue> matchedList){
        // int id = orderList.size();
        int id = 0;
        for(Event event : matchedList.keySet()){
            id++;
            orderList.put(id, new Order(event, matchedList.get(event)));
        }
        System.out.println("DEBUG!! GENERATEORDERS orderList.size() " + orderList.size());
        return orderList;
    }

////////////////////
// MATCH CRITERIA //
////////////////////
    boolean matchDateTime(Venue venue, Event event, HashMap<Integer, Order> orderList){ 
        for(int orderId : orderList.keySet()){
            if(!matchVenue(venue, orderList.get(orderId))){ return false; }

            if(matchDate(event, orderList.get(orderId))){ return false; }

            if(matchTime(event, orderList.get(orderId))){ return false; }
        }
        return true;
    }

    boolean matchVenue(Venue venue, Order order){
        if(venue.getName().equals(order.getVenue().getName())){
            return true;
        }
        return false;
    }

    boolean matchDate(Event event, Order order){
        if(event.getDate().equals(order.getEvent().getDate())){ return true; }
        return false;
    }

    boolean matchTime(Event event, Order order){
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

    boolean matchCapacity(Venue venue, Event event){ 
        // matches event target(capacity)
        if(venue.getCapacity() >= event.getTarget()){
            return true;
        }
        return false;
    }

    boolean matchDuration(){ 
        return false;
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

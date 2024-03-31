package console.handlers;

import java.util.HashMap;
import console.models.Venue;
import console.models.Event;
import console.models.Order;

public class MatchHandler {
    private RequestHandler request;
    private VenueHandler venue;
    private HashMap<Integer, Venue> venueList;
    private HashMap<Integer, Event> requestList;
    private HashMap<Integer, Order> orderList;
    private HashMap<Event, Venue> totalMatch;
    private HashMap<Event, Venue> greatMatch;
    private HashMap<Event, Venue> goodMatch;
    private HashMap<Event, Venue> poorMatch;
    private HashMap<Event, Venue> noMatch;

    public MatchHandler(){}
    public MatchHandler(RequestHandler request, VenueHandler venue){
        this.request = request;
        this.venue = venue;
    }
}

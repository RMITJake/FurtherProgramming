package console.handlers;

import console.models.Venue;
import console.models.Event;

public class MatchHandler {
    private RequestHandler request;
    private VenueHandler venue;

    public MatchHandler(){}
    public MatchHandler(RequestHandler request, VenueHandler venue){
        this.request = request;
        this.venue = venue;
    }
    
}

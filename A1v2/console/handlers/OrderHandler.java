package console.handlers;

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
}

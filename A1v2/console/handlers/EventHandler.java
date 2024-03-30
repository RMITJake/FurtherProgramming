package console.handlers;

import java.util.List;
import java.util.ArrayList;
import console.models.Event;

class EventHandler{
    FileHandler file = new FileHandler();
    private String headers;
    private String COMMA_DELIMITER = ",";
    private String REQUESTS = "requests.csv";
    private String VENUES = "venues.csv";

    public EventHandler(){}

    List<Event> listRequests(){
        return null;
    }
}
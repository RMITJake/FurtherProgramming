package console.handlers;

import java.util.List;
import java.util.ArrayList;
import console.models.Event;

class RequestHandler{
    FileHandler file;
    private String headers;
    private String COMMA_DELIMITER = ",";
    private String REQUESTS = "requests.csv";
    private String VENUES = "venues.csv";

    public RequestHandler(FileHandler file){
        this.file = file;
    }
}
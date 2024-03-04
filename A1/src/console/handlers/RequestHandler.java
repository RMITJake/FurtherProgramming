package console.handlers;
import java.util.ArrayList;

import console.models.Event;

class RequestHandler {
    FileHandler file;

    public RequestHandler(FileHandler file){
        this.file = file;
    }

    public ArrayList<Event> listRequests(){
        ArrayList<Event> eventsList = new ArrayList<Event>();
        for (String line : this.file.readCSV("requests.csv")){
            eventsList.add(deserialiseRequest(line));
        }
        return eventsList;
    }

    public Event deserialiseRequest(String csv){
        String[] splitString = csv.split(",");
        Event event = new Event(splitString[0], splitString[1], splitString[2], splitString[3], splitString[4], splitString[5], splitString[6], splitString[7]);
        return event;
    }

    public void serialiseRequest(){}
    public void requestToString(){}
}

package console.handlers;
import console.models.Event;
import console.models.EventArray;

class RequestHandler {
    FileHandler file;
    String headers;

    public RequestHandler(FileHandler file){
        this.file = file;
    }

    public EventArray listRequests(){
        EventArray eventsList = new EventArray();
        headers = "";
        for (String line : this.file.readCSV("requests.csv")){
            if(headers == ""){
                headers = line;
            } else {
                eventsList.add(deserialiseRequest(line));
            }
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

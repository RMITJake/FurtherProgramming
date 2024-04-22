package src.handlers;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import src.models.Event;

public class RequestHandler extends FileHandler{
    private String REQUESTS = "requests.csv";
    
    public RequestHandler(){ this.REQUESTS = "files/requests.csv"; };
    public RequestHandler(String file){ this.REQUESTS = file; };
    
    // Use the FileHandler to read the requests.csv and deserialize to a Venue object
    public HashMap<Integer, Event> retrieveRequestsFromCSV(HashMap<Integer, Event> requestList){
        List<List<String>> records = new ArrayList<>();
        try{
            records = getLineFromCSV(REQUESTS);
        } catch(FileNotFoundException FNF) {
            System.err.println(REQUESTS + " was not found.");
        }

        records.remove(0); // remove headers
        int id = 0;

        for(List<String> record : records){
            id++;
            if(requestList.get(id) == null){
                requestList.put(id, new Event(
                    record.get(0),
                    record.get(1),
                    record.get(2),
                    record.get(3),
                    record.get(4),
                    Integer.parseInt(record.get(5)),
                    Integer.parseInt(record.get(6)),
                    record.get(7),
                    record.get(8)
                ));
            }
        }
        return requestList;
    }
}

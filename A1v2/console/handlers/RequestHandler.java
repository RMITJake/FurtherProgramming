package console.handlers;

import java.util.List;
import java.util.HashMap;
import console.models.Event;

public class RequestHandler {
    private FileHandler file;
    private String REQUESTS = "requests.csv";
    
    public RequestHandler(FileHandler file){
        this.file = file;
    }

    HashMap<Integer, Event> retrieveRequests(HashMap<Integer, Event> requestList){
        List<List<String>> records = file.getLineFromCSV(REQUESTS);

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

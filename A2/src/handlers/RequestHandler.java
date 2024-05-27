package src.handlers;
// library imports
import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
// Local imports
import src.models.Event;

public class RequestHandler extends FileHandler{
    private String REQUESTS = "requests.csv";
    
    public RequestHandler(){ this.REQUESTS = "files/requests.csv"; };
    public RequestHandler(String file){ this.REQUESTS = file; };
    
    // Use the FileHandler to read the requests.csv and deserialize to a Venue object
    public List<Event> retrieveRequestsFromCSV(List<Event> requestList){
        List<List<String>> records = new ArrayList<>();
        try{
            records = getLineFromCSV(REQUESTS);
        } catch(FileNotFoundException FNF) {
            System.err.println(REQUESTS + " was not found.");
        }

        records.remove(0); // remove headers

        for(List<String> record : records){
            requestList.add(new Event(
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
        return requestList;
    }

    public List<Event> retrieveRequestsFromCSV(){ return retrieveRequestsFromCSV(new ArrayList<Event>()); }

    public static List<Event> eventResultToList(ResultSet result){
        List<Event> eventList = new ArrayList<Event>();
        try{
            while(result.next()){
                Event event = new Event(
                result.getInt("id"),
                result.getString("client"),
                result.getString("title"),
                result.getString("artist"),
                result.getString("datetime"),
                result.getInt("target"),
                result.getInt("duration"),
                result.getString("type"),
                result.getString("category")
                );
                eventList.add(event);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return eventList;
    }
}

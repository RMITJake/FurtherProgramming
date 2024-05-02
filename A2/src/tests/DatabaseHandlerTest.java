package src.tests;
import src.handlers.DatabaseHandler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import src.models.Venue;
import java.util.List;
import java.util.ArrayList;

public class DatabaseHandlerTest {
    private List<String> clientList = new ArrayList<>();
    private List<Venue> venueList = new ArrayList<>();
///////////
// Setup //
///////////
    @Before()
    @Test()
    public void setup(){
        DatabaseHandler.initializeDb(DatabaseHandler.testdb);

        // Client list
        String client;
        client = "Jake";
        clientList.add(client);
        client = "Hollie";
        clientList.add(client);
        client = "Lemon";
        clientList.add(client);
        client = "Basil";
        clientList.add(client);

        // Venue list
        Venue venue;
        venue = new Venue("Testing Factory",100,"Testing; Positive Outcomes","Unit Test",3000);
        venueList.add(venue);
        venue = new Venue("Unit Testing Station",50,"Unit Testing;Testing","Unit Test",7000);
        venueList.add(venue);
        venue = new Venue("Asserting Test Outcome",90,"Unit Testing; Positive Outcomes","Outcome",400);
        venueList.add(venue);
    }
// END Setup

////////////////
// writeVenue //
////////////////
    @Test()
    public void writeClient(){
        DatabaseHandler.writeClient(clientList);
    }
// END writeVenue

////////////////
// writeVenue //
////////////////
    @Test()
    public void writeVenue(){
        try {
            Thread.sleep(100);
        } catch (Exception e){}
        DatabaseHandler.writeVenue(venueList);
    }
// END writeVenue
}
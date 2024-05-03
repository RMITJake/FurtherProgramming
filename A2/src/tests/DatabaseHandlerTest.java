package src.tests;
import src.handlers.DatabaseHandler;
import org.junit.Before;
import org.junit.Test;
import src.models.Venue;
import src.models.Event;
import java.util.List;
import java.util.ArrayList;

public class DatabaseHandlerTest {
    private List<Event> eventList = new ArrayList<>();
    private List<Venue> venueList = new ArrayList<>();
///////////
// Setup //
///////////
    @Before()
    public void modelSetup(){
         // Event list
        Event event;
        event = new Event("House of Tests","Request Handler Unit","Unit Test","21/04/2024","9:00am",100,2,"unit test","testing");
        eventList.add(event);
        event = new Event("Admincorp","Execution Prep","Execution","19/04/2024","7:30pm",3000,12,"unit test","testing");
        eventList.add(event);
        event = new Event("Company Two","Next Level Testing","Unit Test","19/04/2024","12:00pm",24,5,"L2","integration");
        eventList.add(event);
        event = new Event("House of Tests","Final Approvals","Automated","22/04/2024","8:00am",670,8,"approval","system");
        eventList.add(event);

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

//////////////////
// initialiseDb //
//////////////////
    @Test()
    public void initializeDb(){
        DatabaseHandler.initializeDb(DatabaseHandler.testdb);
        DatabaseHandler.writeEvent(eventList);
        DatabaseHandler.writeVenue(venueList);
    }
// END of initialiseDb

////////////////
// writeVenue //
////////////////
    // @Test()
    public void writeEvent(){
        // DatabaseHandler.writeEvent(eventList);
    }
// END writeVenue

////////////////
// writeVenue //
////////////////
    // @Test()
    public void writeVenue(){
        // DatabaseHandler.writeVenue(venueList);
    }
// END writeVenue
}
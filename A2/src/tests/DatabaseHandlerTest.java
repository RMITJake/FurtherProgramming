package src.tests;
import src.handlers.DatabaseHandler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import src.models.Venue;
import java.util.List;
import java.util.ArrayList;

public class DatabaseHandlerTest {
    private List<String> clientList = new ArrayList<String>();
///////////
// Setup //
///////////
    @Before()
    @Test()
    public void setUp(){
        DatabaseHandler.initializeDb(DatabaseHandler.testdb);

        String client;
        client = "Jake";
        clientList.add(client);
        client = "Hollie";
        clientList.add(client);
        client = "Lemon";
        clientList.add(client);
        client = "Basil";
        clientList.add(client);
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
}
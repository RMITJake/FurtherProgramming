package src.tests;
import src.handlers.DatabaseHandler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class DatabaseHandlerTest {
///////////
// Setup //
///////////
    @Before()
    @Test()
    public void setUp(){
        DatabaseHandler.initializeDb(DatabaseHandler.testdb);
    }
// END Setup
}
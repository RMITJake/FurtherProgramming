package src.tests;
import src.handlers.RequestHandler;
import src.models.Event;
import org.junit.*;

public class RequestHandlerTest {
    RequestHandler handler;
    String testFile = "files/tests/requestsTest.csv";
    // HashMap<Integer, Venue> testMap;

///////////
// Setup //
///////////
    @Before()
    public void setUp(){
        handler = new RequestHandler(testFile);
    }
// END Setup
    
}

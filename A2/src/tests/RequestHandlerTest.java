package src.tests;
import src.handlers.RequestHandler;
import src.models.Event;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {
    RequestHandler handler;
    String testFile = "files/tests/requestsTest.csv";
    HashMap<Integer, Event> testMap;

///////////
// Setup //
///////////
    @Before()
    public void setUp(){
        handler = new RequestHandler(testFile);

        testMap = new HashMap<>();
        Event event;
        event = new Event("House of Tests","Request Handler Unit","Unit Test","21/04/2024","9:00am",100,2,"unit test","testing");
        testMap.put(1, event);
        event = new Event("Admincorp","Execution Prep","Execution","19/04/2024","7:30pm",3000,12,"unit test","testing");
        testMap.put(2, event);
        event = new Event("Company Two","Next Level Testing","Unit Test","19/04/2024","12:00pm",24,5,"L2","integration");
        testMap.put(3, event);
        event = new Event("House of Tests","Final Approvals","Automated","22/04/2024","8:00am",670,8,"approval","system");
        testMap.put(4, event);
    }
// END Setup
    
////////////////////////
// getLineFromCSVTest //
////////////////////////
    @Test()
    public void getLineFromCSVTest() throws FileNotFoundException{
        List<List<String>> expected = new ArrayList<>();
        List<String> record = new ArrayList<String>();

        record = new ArrayList<String>(){{
            add("Client");
            add("Title");
            add("Artist");
            add("Date");
            add("Time");
            add("Target");
            add("Duration");
            add("Type");
            add("Category");
        }};
        expected.add(record);

        record = new ArrayList<String>(){{
            add("House of Tests");
            add("Request Handler Unit");
            add("Unit Test");
            add("21/04/2024");
            add("9:00am");
            add("100");
            add("2");
            add("unit test");
            add("testing");
        }};
        expected.add(record);

        record = new ArrayList<String>(){{
            add("Admincorp");
            add("Execution Prep");
            add("Execution");
            add("19/04/2024");
            add("7:30pm");
            add("3000");
            add("12");
            add("unit test");
            add("testing");
        }};
        expected.add(record);

        record = new ArrayList<String>(){{
            add("Company Two");
            add("Next Level Testing");
            add("Unit Test");
            add("19/04/2024");
            add("12:00pm");
            add("24");
            add("5");
            add("L2");
            add("integration");
        }};
        expected.add(record);

        record = new ArrayList<String>(){{
            add("House of Tests");
            add("Final Approvals");
            add("Automated");
            add("22/04/2024");
            add("8:00am");
            add("670");
            add("8");
            add("approval");
            add("system");
        }};
        expected.add(record);

        List<List<String>> fileContents = handler.getLineFromCSV(testFile);
        
        assertEquals(expected, fileContents);
    }
// END getLineFromCSVTest

/////////////////////////////////////////////
// getLineFromCSVFileNotFoundExceptionTest //
/////////////////////////////////////////////
    @Test(expected=FileNotFoundException.class)
    public void getLineFromCSVFileNotFoundExceptionTest() throws FileNotFoundException{
        handler.getLineFromCSV("files/404.csv");
    }
// END getLineFromCSVFileNotFoundExceptionTest

/////////////////////////////
// retrieveRequestsFromCSV //
/////////////////////////////
    @Test()
    public void retrieveRequestsFromCSV(){
        HashMap<Integer, Event> eventMap = handler.retrieveRequestsFromCSV(new HashMap<Integer, Event>());

        for(int i=1; i <= testMap.size(); i++){
            assertEquals(testMap.get(i).getClient(), eventMap.get(i).getClient());
        }
    }
// END retrieveRequests
}

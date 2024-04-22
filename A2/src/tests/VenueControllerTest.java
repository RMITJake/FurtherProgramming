package src.tests;
import src.controllers.VenueController;
import src.models.Venue;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class VenueControllerTest {
    VenueController controller;
    String testFile = "files/tests/venuesTest.csv";
    HashMap<Integer, Venue> testMap;

///////////
// Setup //
///////////
    @Before()
    public void setUp(){
        controller = new VenueController(testFile);

        testMap = new HashMap<>();
        Venue venue;
        venue = new Venue("Testing Factory",100,"Testing; Positive Outcomes","Unit Test",3000);
        testMap.put(1,venue);
        venue = new Venue("Unit Testing Station",50,"Unit Testing;Testing","Unit Test",7000);
        testMap.put(2,venue);
        venue = new Venue("Asserting Test Outcome",90,"Unit Testing; Positive Outcomes","Outcome",400);
        testMap.put(3,venue);
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
            add("Name");
            add("Capacity");
            add("Suitable for");
            add("Category");
            add("Price per hour");
        }};
        expected.add(record);

        record = new ArrayList<String>(){{
            add("Testing Factory");
            add("100");
            add("Testing; Positive Outcomes");
            add("Unit Test");
            add("3000");
        }};
        expected.add(record);

        record = new ArrayList<String>(){{
            add("Unit Testing Station");
            add("50");
            add("Unit Testing;Testing");
            add("Unit Test");
            add("7000");
        }};
        expected.add(record);

        record = new ArrayList<String>(){{
            add("Asserting Test Outcome");
            add("90");
            add("Unit Testing; Positive Outcomes");
            add("Outcome");
            add("400");
        }};
        expected.add(record);

        List<List<String>> fileContents = controller.getLineFromCSV(testFile);
        
        assertEquals(expected, fileContents);
    }
// END getLineFromCSVTest

/////////////////////////////////////////////
// getLineFromCSVFileNotFoundExceptionTest //
/////////////////////////////////////////////
    @Test(expected=FileNotFoundException.class)
    public void getLineFromCSVFileNotFoundExceptionTest() throws FileNotFoundException{
        controller.getLineFromCSV("files/404.csv");
    }
// END getLineFromCSVFileNotFoundExceptionTest

///////////////////////////
// retrieveVenuesFromCSV //
///////////////////////////
    @Test()
    public void retrieveVenuesFromCSV(){
        HashMap<Integer, Venue> csvMap = controller.retrieveVenuesFromCSV(new HashMap<Integer, Venue>());

        for(int i=1; i <= testMap.size(); i++){
            assertEquals(testMap.get(i).getName(), csvMap.get(i).getName());
        }
    }
// END retrieveVenuesFromCSV

///////////////////
// getCategories //
///////////////////
    @Test()
    public void getCategories(){
        HashMap<Integer, String> expectedCategories = new HashMap<>();
        expectedCategories.put(1, "Unit Test");
        expectedCategories.put(2, "Outcome");

        HashMap<Integer, String> csvCategories = controller.getCategories(testMap);
        for(int i=1; i <= testMap.size(); i++){
            assertEquals(expectedCategories.get(i), csvCategories.get(i));
        }
    }
// END getCategories

////////////////////////
// getVenueByCategory //
////////////////////////
    @Test()
    public void getVenueByCategoryTrue(){
        String categoryMatch = "Unit Test";
        HashMap<Integer, Venue> categoryMap = controller.getVenueByCategory(categoryMatch, testMap);
        for(int i=1; i <= categoryMap.size(); i++){
            assertEquals(categoryMatch, categoryMap.get(i).getCategory());
        }
    }

    @Test()
    public void getVenueByCategoryFalse(){
        String categoryMatch = "Unit Test";
        String categoryFalse = "Outcome";
        HashMap<Integer, Venue> categoryMap = controller.getVenueByCategory(categoryMatch, testMap);
        for(int i=1; i <= categoryMap.size(); i++){
            assertFalse(categoryFalse.equals(categoryMap.get(i).getCategory()));
        }
    }
// END getVenueByCategory

///////////////////////
// searchVenueByName //
///////////////////////
    @Test()
    public void searchVenueByNameTrue(){
        String searchMatch = "Testing";
        HashMap<Integer, Venue> searchMap = controller.getVenueByCategory(searchMatch, testMap);
        for(int i=1; i <= searchMap.size(); i++){
            assertTrue(searchMap.get(i).getCategory().toLowerCase().contains(searchMatch.toLowerCase()));
        }
    }

    @Test()
    public void searchVenueByNameFalse(){
        String searchMatch = "Testing";
        String searchFalse = "Assert";
        HashMap<Integer, Venue> searchMap = controller.getVenueByCategory(searchMatch, testMap);
        for(int i=1; i <= searchMap.size(); i++){
            assertFalse(searchMap.get(i).getCategory().toLowerCase().contains(searchFalse.toLowerCase()));
        }
    }
// END searchVenueByName

////////////////////
// getVenueByName //
////////////////////
    @Test()
    public void getVenueByNameTrue(){
        String searchMatch = "Unit Testing Station";
        Venue searchVenue = controller.getVenueByName(searchMatch, testMap);
        assertEquals(searchMatch.toLowerCase(), searchVenue.getName().toLowerCase());
    }
// END getVenueByName
}

package src.tests;
import src.controllers.VenueController;
import src.models.Venue;
import java.util.HashMap;
import org.junit.*;
import static org.junit.Assert.assertTrue;

public class VenueControllerTest {
    VenueController controller;

///////////
// Setup //
///////////
    @Before()
    public void setUp(){
        controller = new VenueController("files/tests/venuesTest.csv");
    }
// END Setup

///////////////////////////
// retrieveVenuesFromCSV //
///////////////////////////
    @Test()
    public void retrieveVenuesFromCSVTrue(){
    }
    // HashMap<Integer, Venue> retrieveVenuesFromCSV(HashMap<Integer, Venue> venueList){
    //     List<List<String>> records = new ArrayList<>();
    //     try{
    //         records = getLineFromCSV(VENUES);
    //     } catch(FileNotFoundException FNF) {
    //         System.err.println(VENUES + " was not found.");
    //     }

    //     records.remove(0); // remove headers
    //     int id = 0;

    //     for(List<String> record : records){
    //         id++;
    //         if(venueList.get(id) == null){
    //             venueList.put(id, new Venue(
    //                 record.get(0),
    //                 Integer.parseInt(record.get(1)),
    //                 record.get(2),
    //                 record.get(3),
    //                 Integer.parseInt(record.get(4)
    //             )));
    //         }
    //     }
    //     return venueList;
    // }
// END retrieveVenuesFromCSV

///////////////////
// getCategories //
///////////////////
    // HashMap<Integer, String> getCategories(HashMap<Integer, Venue> venueList){
    //     HashMap<Integer, String> categories = new HashMap<Integer, String>();
    //     int id = 0;
    //     for(int venue : venueList.keySet()){
    //         if(!categories.containsValue(venueList.get(venue).getCategory())){
    //             id++;
    //             categories.put(id, venueList.get(venue).getCategory());
    //         }
    //     }
    //     return categories;
    // }
// END getCategories

////////////////////////
// getVenueByCategory //
////////////////////////
    // HashMap<Integer, Venue> getVenueByCategory(String category, HashMap<Integer, Venue> venueList){
    //     HashMap<Integer, Venue> venueFiltered = new HashMap<Integer, Venue>();

    //     int id = 0;
    //     for(int venueId : venueList.keySet()){
    //         if(venueList.get(venueId).getCategory().contains(category)){
    //             id++;
    //             venueFiltered.put(id, venueList.get(venueId));
    //         }
    //     }
    //     return venueFiltered;
    // }
// END getVenueByCategory

///////////////////////
// searchVenueByName //
///////////////////////
    // HashMap<Integer, Venue> searchVenueByName(String searchName, HashMap<Integer, Venue> venueList){
    //     HashMap<Integer, Venue> searchVenues = new HashMap<Integer, Venue>();
    //     int newId = 0;
    //     for(int venueId : venueList.keySet()){
    //         if(venueList.get(venueId).getName().toLowerCase().contains(searchName.toLowerCase())){
    //             newId++;
    //             searchVenues.put(newId, venueList.get(venueId));
    //         }
    //     }
    //     return searchVenues;
    // }
// END searchVenueByName

////////////////////
// getVenueByName //
////////////////////
    // Venue getVenueByName(String searchName, HashMap<Integer, Venue> venueList){
    //     for(int venueId : venueList.keySet()){
    //         if(venueList.get(venueId).getName().equals(searchName)){
    //             return venueList.get(venueId);
    //         }
    //     }
    //     return null;
    // }
// END getVenueByName
}

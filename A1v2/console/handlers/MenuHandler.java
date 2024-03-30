package console.handlers;

import console.ui.ConsoleUI;
// import java.io.FileNotFoundException;
import java.util.HashMap;
import console.models.Venue;

public class MenuHandler {
    ConsoleUI ui = new ConsoleUI();
    InputHandler in = new InputHandler();
    FileHandler file = new FileHandler();
    // EventHandler event = new EventHandler(file);
    VenueHandler venue = new VenueHandler(file);

    int applicationLoop;
    
    public int menuLoop(){
        do{
            applicationLoop = 0;
            displayMenu();
            applicationLoop = in.mainMenuInput();
            if(applicationLoop == 1){
                listjobRequests();
            } else if (applicationLoop == 2){
                HashMap<Integer, Venue> venueByCategory = selectVenueByCategory(getVenueCategories());
                Venue selectedVenue = selectVenueById(venueByCategory);
                ui.printVenue(selectedVenue);
                hireLoop(selectedVenue);
            }
        } while (applicationLoop != 6);

        return applicationLoop;
    }

    void displayMenu(){
        ui.getMainMenu();
    }

    void listjobRequests(){
    //    ui.printEvents(event.listRequests());
    }

    // HashMap<Integer, String> browseVenue(){
    //     HashMap<Integer, String> categories = venue.getCategories();
    //     ui.getVenueCategories(venue.getCategories());
    //     return categories;
    // }

    HashMap<Integer, String> getVenueCategories(){
        HashMap<Integer, String> categories = venue.getCategories();
        ui.getVenueCategories(categories);
        applicationLoop = in.mainMenuInput();
        return categories;
    }

    HashMap<Integer, Venue> selectVenueByCategory(HashMap<Integer, String> categories){
        ui.selectVenue();
        HashMap<Integer, Venue> venues = venue.getVenueByCategory(categories.get(applicationLoop));
        ui.printVenues(venues);
        return venues;
    }

    Venue selectVenueById(HashMap<Integer, Venue> venues){
        ui.selectMessage();
        return venues.get(applicationLoop = in.mainMenuInput());
    }

    void hireLoop(Venue venue){
        ui.hirePrice(venue);
        ui.selectMessage();
        applicationLoop = in.mainMenuInput();

        // ui.hireDetails();
    }
}

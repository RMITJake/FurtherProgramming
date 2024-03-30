package console.handlers;

import console.ui.ConsoleUI;
// import java.io.FileNotFoundException;
// import java.util.HashMap;

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
                browseVenue();
                applicationLoop = in.mainMenuInput();
            }
        } while (applicationLoop != 6);

        return applicationLoop;
    }

    public void displayMenu(){
        ui.getMainMenu();
    }

    public void listjobRequests(){
    //    ui.printEvents(event.listRequests());
    }

    public void browseVenue(){
        ui.getVenueCategories(venue.getCategories());
        // ui.printVenues(venue.retrieveVenues());
    }
}

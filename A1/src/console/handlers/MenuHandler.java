package console.handlers;

import console.models.*;
import console.ui.ConsoleUI;

public class MenuHandler {
    ConsoleUI ui = new ConsoleUI();
    InputHandler in = new InputHandler();
    FileHandler file = new FileHandler();
    RequestHandler request = new RequestHandler(file);
    VenueHandler venue = new VenueHandler(file);
    Event[] eventList;
    Venue[] venueList;
    Order[] orderList;
    int applicationLoop;
    private int exitCode;

    public int menuLoop(){
        do{
            exitCode = 6;
            applicationLoop = 0;
            displayMenu();
            applicationLoop = in.mainMenuInput();
            if(applicationLoop == 1){
                listjobRequests();
            } else if (applicationLoop == 2){
                venueByCategoryLoop();
            }
        } while (applicationLoop != exitCode);

        return applicationLoop;
    }

    public int venueByCategoryLoop(){
        exitCode = 9;
        applicationLoop = 0;
        do{
            selectCategory();
            applicationLoop = in.mainMenuInput();
            VenueArray venueList = new VenueArray();
            try {
                venueList = venue.getVenueByCategory(applicationLoop);
                ui.print(venueList);
            } finally {
            }
        } while (applicationLoop != exitCode);

        return exitCode;
    }

    public void displayMenu(){
        ui.getMainMenu();
    }
    public void displaySubmenu(){}

    public void listjobRequests(){
       ui.print(request.listRequests());
    }

    public void browseVenue(){
        ui.print(venue.listVenues());
    }
    
    void selectCategory(){
        ui.print(venue.listCategories());
    }

    public void searchVenue(){}
    public void autoMatch(){}
    public void showOrderSummary(){}
    public void exit(){}
}
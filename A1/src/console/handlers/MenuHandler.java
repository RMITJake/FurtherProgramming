package console.handlers;

import console.models.Event;
import console.models.Venue;
import console.models.Order;
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

    public int menuLoop(){
        do{
            applicationLoop = 0;
            displayMenu();
            applicationLoop = in.mainMenuInput();
            if(applicationLoop == 1){
                listjobRequests();
            } else if (applicationLoop == 2){
                browseVenue();
            }
        } while (applicationLoop != 6);

        return applicationLoop;
    }

    public void displayMenu(){
        ui.getMainMenu();
    }
    public void displaySubmenu(){}

    public void listjobRequests(){
       ui.printEvents(request.listRequests());
    }

    public void browseVenue(){
        ui.printVenues(venue.listVenues());
    }
    public void searchVenue(){}
    public void autoMatch(){}
    public void showOrderSummary(){}
    public void exit(){}
}
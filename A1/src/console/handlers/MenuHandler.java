package console.handlers;

import console.models.Event;
import console.models.Venue;
import console.models.Order;
import console.ui.ConsoleUI;

public class MenuHandler {
    ConsoleUI ui = new ConsoleUI();
    InputHandler in = new InputHandler();
    RequestHandler request = new RequestHandler();
    Event[] eventList;
    Venue[] venueList;
    Order[] orderList;
    int applicationLoop;

    public int menuLoop(){
        applicationLoop = 0;
        do{
            displayMenu();
            applicationLoop = in.mainMenuInput();
            if(applicationLoop == 1){
                listjobRequests();
            }
        } while (applicationLoop != 6);

        return applicationLoop;
    }

    public void displayMenu(){
        ui.getMainMenu();
    }
    public void displaySubmenu(){}

    public void listjobRequests(){
       ui.print(request.listRequests());
    }

    public void browseVenue(){}
    public void searchVenue(){}
    public void autoMatch(){}
    public void showOrderSummary(){}
    public void exit(){}
}
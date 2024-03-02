package console.handlers;

import console.models.Event;
import console.models.Venue;
import console.models.Order;
import console.ui.MenuUI;

public class MenuHandler {
    MenuUI ui = new MenuUI();
    InputHandler in = new InputHandler();
    Event[] eventList;
    Venue[] venueList;
    Order[] orderList;
    int applicationLoop;

    public int menuLoop(){
        applicationLoop = 0;
        do{
            displayMenu();
            applicationLoop = in.mainMenuInput();
        } while (applicationLoop != 6);

        return applicationLoop;
    }

    public void displayMenu(){
        System.out.println(ui.getMainMenu());
    }
    public void displaySubmenu(){}
    public void listjobRequests(){}
    public void browseVenue(){}
    public void searchVenue(){}
    public void autoMatch(){}
    public void showOrderSummary(){}
    public void exit(){}
}
package console.handlers;

import console.models.Event;
import console.models.Venue;
import console.models.Order;
import console.ui.MenuUI;

public class MenuHandler {
    MenuUI ui = new MenuUI();
    Event[] eventList;
    Venue[] venueList;
    Order[] orderList;

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
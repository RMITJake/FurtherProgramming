package console.handlers;

import java.util.ArrayList;
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
            ui.printSelect("category");
            selectCategory();
            applicationLoop = in.mainMenuInput();
            ArrayList<String> venueList = new ArrayList<String>();
            try {
                venueList = venue.getVenueNameByCategory(applicationLoop);
            } finally {
                if(venueList.size() > 0){
                    venueSelectLoop(venueList);
                }
            }
        } while (applicationLoop != exitCode);
        
        return exitCode;
    }
    
    public int venueSelectLoop(ArrayList<String> venueList){
            exitCode = 9;
            do{
                venueSelect(venueList);
                applicationLoop = in.mainMenuInput();
                try {
                    Venue searchVenue = venue.getVenueByName(applicationLoop, venueList);
                    ui.print(searchVenue);
                } finally {
                }
            } while (applicationLoop != exitCode);
        return 0;
    }
    
    public int venueSelect(ArrayList<String> venueList){
            ui.printSelect("venue list");
            ui.print(venueList);
        return 0;
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
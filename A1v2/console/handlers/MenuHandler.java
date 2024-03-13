package console.handlers;

import console.ui.ConsoleUI;

public class MenuHandler {
    ConsoleUI ui = new ConsoleUI();
    InputHandler in = new InputHandler();

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

    public void listjobRequests(){
    //    ui.printEvents(request.listRequests());
    }

    public void browseVenue(){
        // ui.printVenues(venue.listVenues());
    }
}

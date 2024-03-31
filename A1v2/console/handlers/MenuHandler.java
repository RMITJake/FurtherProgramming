package console.handlers;

import console.ui.ConsoleUI;
// import java.io.FileNotFoundException;
import java.util.HashMap;
import console.models.Venue;

public class MenuHandler {
    ConsoleUI ui = new ConsoleUI();
    InputHandler in = new InputHandler();
    ValidationHandler validate = new ValidationHandler();
    FileHandler file = new FileHandler();
    // EventHandler event = new EventHandler(file);
    VenueHandler venue = new VenueHandler(file);

    int applicationLoop;
    String input;
    
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
            } else if (applicationLoop == 3){
                boolean searchNameValidated = false;
                do{
                    ui.searchVenueByName();
                    input = in.userInput();
                    if(!validate.validateSearchVenueByName(input)){
                        ui.validateSearchVenueByNameError();
                    } else {
                        searchNameValidated = true;
                        HashMap<Integer, Venue> searchResult = selectVenueByName(input);
                        if(searchResult != null){
                            Venue selectedVenue = selectVenueById(searchResult);
                            ui.printVenue(selectedVenue);
                        } else {
                            ui.venueNotFoundError();
                        }
                    }
                } while(!searchNameValidated);
            } else if (applicationLoop == 4){
                // Auto-match events with suitable venues
            } else if (applicationLoop == 5){
                // Show order summary
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

    HashMap<Integer, String> getVenueCategories(){
        HashMap<Integer, String> categories = venue.getCategories();
        ui.getVenueCategories(categories);
        applicationLoop = in.mainMenuInput();
        return categories;
    }

    HashMap<Integer, Venue> selectVenueByCategory(HashMap<Integer, String> categories){
        ui.selectVenue();
        HashMap<Integer, Venue> venues = venue.getVenueByCategory(categories.get(applicationLoop));
        ui.printVenueNames(venues);
        return venues;
    }

    HashMap<Integer, Venue> selectVenueByName(String searchName){
        ui.selectVenue();
        HashMap<Integer, Venue> venues = venue.searchVenueByName(searchName);
        ui.printVenueNames(venues);
        return venues;
    }

    Venue selectVenueById(HashMap<Integer, Venue> venues){
        ui.selectMessage();
        return venues.get(applicationLoop = in.mainMenuInput());
    }

    int hireLoop(Venue venue){
        ui.hirePrice(venue);
        ui.selectMessage();
        applicationLoop = in.mainMenuInput();

        // Guard clause
        if(applicationLoop == 2){
            return applicationLoop;
        }

        ui.hireDetails();
        boolean hoursValidated = false;
        do{
            ui.hireDetailsHours();
            input = in.userInput();
            if(!validate.validateHours(input)){
                ui.validateHoursError();
            } else {
                hoursValidated = true;
            }
        } while(!hoursValidated);

        boolean dateValidated = false;
        do{
            ui.hireDetailsDate();
            input = in.userInput();
            if(!validate.validateDate(input)){
                ui.validateDateError();
            } else {
                dateValidated = true;
            }
        } while(!dateValidated);

        boolean timeValidated = false;
        do{
            ui.hireDetailsTime();
            input = in.userInput();
            if(!validate.validateTime(input)){
                ui.validateTimeError();
            } else {
                timeValidated = true;
            }
        } while(!timeValidated);

        boolean eventNameValidated = false;
        do{
            ui.hireDetailsEventName();
            input = in.userInput();
            if(!validate.validateEventName(input)){
                ui.validateEventNameError();
            } else {
                eventNameValidated = true;
                input = input.replace(",", ";");
            }
        } while(!eventNameValidated);

        boolean artistNameValidated = false;
        do{
            ui.hireDetailsArtistName();
            input = in.userInput();
            if(!validate.validateArtistName(input)){
                ui.validateArtistNameError();
            } else {
                artistNameValidated = true;
                input = input.replace(",", ";");
            }
        } while(!artistNameValidated);

        boolean requesterNameValidated = false;
        do{
            ui.hireDetailsRequesterName();
            input = in.userInput();
            if(!validate.validateRequesterName(input)){
                ui.validateRequesterNameError();
            } else {
                requesterNameValidated = true;
                input = input.replace(",", ";");
            }
        } while(!requesterNameValidated);

        boolean confimValidated = false;
        do{
            ui.hireDetailsConfirm();
            input = in.userInput();
            if(!validate.validateConfirm(input)){
                ui.validateConfirmError();
            } else {
                confimValidated = true;
            }
        } while(!confimValidated);

        if(input.contains("y")){
            // save
            ui.orderConfirmed();
        } else {
            ui.orderCancelled();
        }

        return applicationLoop;
    }
}

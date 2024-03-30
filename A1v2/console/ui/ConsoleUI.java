package console.ui;

import java.util.ArrayList;
import java.util.HashMap;
import console.models.Event;
import console.models.Venue;

public class ConsoleUI {
    private String menuText;
    private String linebreak = "---------------\n";

    public void getBanner(){
        menuText = "welcome to Venue Matcher";
        print(menuText);
    }

    public void getMainMenu(){
        menuText = ""
        +linebreak
        +"> Select from main menu\n"
        +linebreak
        +"1) List current job requests\n"
        +"2) Browse venue by category\n"
        +"3) Search venue by name\n"
        +"4) Auto-match events with suitable venues\n"
        +"5) Show order summary\n"
        +"6) Exit\n"
        +"Please select: ";
        print(menuText);
    }

    public void print(String string){
        System.out.print(string);
    }

    public void printEvents(ArrayList<Event> eventsList){
        for (Event event : eventsList) {
            System.out.println(event.getClient());
        }
    }

    public void printVenue(Venue venue){
            String format = ""
            +">%s\n"
            +"Capacity: %s\n"
            +"Category: %s\n"
            +"Suitable for: %s\n"
            +linebreak;

            System.out.printf(format, venue.getName(), venue.getCapacity(), venue.getCategory(), venue.getSuitableFor().replace(";", ","));
    }

    public void printVenues(HashMap<Integer, Venue> venueList){
        for (int id : venueList.keySet()) {
            String format = "%s) %s\t%s\t%s\t%s\n";

            System.out.printf(format, id, venueList.get(id).getName(), venueList.get(id).getCapacity(), venueList.get(id).getSuitableFor(), venueList.get(id).getCategory());
        }
    }

    public void getVenueCategories(HashMap<Integer, String> categories){
        menuText = "";

        for(int id : categories.keySet()){
            menuText += id
            +") " + categories.get(id) + "\n";
        }

        menuText += "Please select: ";
        print(menuText);
    }

    public void selectVenue(){
        menuText = ""
        +linebreak
        +"Select venue from list\n"
        +linebreak;
        print(menuText);
    }

    public void selectMessage(){
        menuText = "Please select: ";
        print(menuText);
    }

    public void searchVenueByName(){
        menuText = "Please enter a venue name: ";
        print(menuText);
    }

//////////////////
// HIRE DETAILS //
//////////////////
    public void hirePrice(Venue venue){
        menuText = ""
        +"1) Hire for $" + venue.getPricePerHour() + "/hour\n"
        +"2) Back to venue list\n";
        print(menuText);
    }

    public void hireDetails(){
        menuText = "\nHiring Details\n";
        print(menuText);
    }

    public void hireDetailsHours(){
        menuText = "Please enter number of hours: ";
        print(menuText);
    }

    public void hireDetailsDate(){
        menuText = "Please enter date: ";
        print(menuText);
    }

    public void hireDetailsTime(){
        menuText = "Please enter time: ";
        print(menuText);
    }

    public void hireDetailsEventName(){
        menuText = "Event name: ";
        print(menuText);
    }

    public void hireDetailsArtistName(){
        menuText = "Artist name: ";
        print(menuText);
    }

    public void hireDetailsRequesterName(){
        menuText = "Requester name: ";
        print(menuText);
    }

    public void hireDetailsConfirm(){
        menuText = "Confirm order (y/n): ";
        print(menuText);
    }

    public void orderConfirmed(){
        menuText = "Order confirmed.\n";
        print(menuText);
    }

    public void orderCancelled(){
        menuText = "Order cancelled.\n";
        print(menuText);
    }

////////////////
//   ERRORS   //
////////////////
    public void validateHoursError(){
        menuText = "Venues can only be hired for a minimum of 1 and maximum of 24 hours.\n";
        print(menuText);
    }

    public void validateDateError(){
        menuText = "Date must a valid date after 01/01/2024 and before 01/01/2029.\n"
        +"Dates must be in the format DD/MM/YYYY, e.g. 20/12/2024.\n";
        print(menuText);
    }
    
    public void validateTimeError(){
        menuText = "Time must be between 12:00am and 12:00pm.\n"
        +"Time must be in the format HH:mmXM.\n";
        print(menuText);
    }
    
    public void validateEventNameError(){
        menuText = "Names cannot start or end with spaces.\n";
        print(menuText);
    }
    
    public void validateArtistNameError(){
        menuText = "Names cannot start or end with spaces.\n";
        print(menuText);
    }

    public void validateRequesterNameError(){
        menuText = "Names cannot start or end with spaces.\n";
        print(menuText);
    }

    public void validateConfirmError(){
        menuText = "Only Y and N are valid inputs.\n";
        print(menuText);
    }

    public void validateSearchVenueByNameError(){
        menuText = "Names cannot start or end with spaces.\n";
        print(menuText);
    }
    
    public void venueNotFoundError(){
        menuText = "Venue could not be found.\n";
        print(menuText);
    }
}

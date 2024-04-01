package console.ui;

import java.util.ArrayList;
import java.util.HashMap;
import console.models.Event;
import console.models.Order;
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

    // public void printEvents(ArrayList<Event> eventsList){
    //     for (Event event : eventsList) {
    //         System.out.println(event.getClient());
    //     }
    // }

///////////////////
// MENU OPTION 1 //
///////////////////
    public void printRequests(HashMap<Integer, Event> requestList){
        String format = "%-30s\t%-36s\t%-24s\t%-12s\t%-7s\t%-10s\t%-10s\t%-20s\t%s\n";
        System.out.printf(format,
        "Client",
        "Title",
        "Artist",
        "Date",
        "Time",
        "Target",
        "Duration",
        "Type",
        "Category"
        );

        for(int id : requestList.keySet()){
            System.out.printf(format, 
            requestList.get(id).getClient(),
            requestList.get(id).getTitle(),
            requestList.get(id).getArtist(),
            requestList.get(id).getDate(),
            requestList.get(id).getTime(),
            requestList.get(id).getTarget(),
            requestList.get(id).getDuration(),
            requestList.get(id).getType(),
            requestList.get(id).getCategory()
            );
        }
    }
// END MENU OPTION 1 //

    public void printVenue(Venue venue){
            String format = ""
            +">%s\n"
            +"Capacity: %s\n"
            +"Category: %s\n"
            +"Suitable for: %s\n"
            +linebreak;

            System.out.printf(format, venue.getName(), venue.getCapacity(), venue.getCategory(), venue.getSuitableForString());
    }

    public void printVenues(HashMap<Integer, Venue> venueList){
        for (int id : venueList.keySet()) {
            String format = "%s) %s\t%s\t%s\t%s\n";

            System.out.printf(format, id, venueList.get(id).getName(), venueList.get(id).getCapacity(), venueList.get(id).getSuitableFor(), venueList.get(id).getCategory());
        }
    }

    public void printVenueNames(HashMap<Integer, Venue> venueList){
        for (int id : venueList.keySet()) {
            String format = "%s) %s\n";

            System.out.printf(format, id, venueList.get(id).getName());
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
//    ORDERS    //
//////////////////
    public void printOrderSummary(HashMap<Integer, Order> orderList){
        String format;
        int cumulativeTotal = 0;
        menuText = ""
        +linebreak
        +"Order Summary\n"
        +linebreak
        +"\n";
        print(menuText);

        System.out.println("DEBUG!! SUMMARY orderList.size() " + orderList.size());
        for (int id : orderList.keySet()) {
            System.out.println("DEBUG!! at id " + id);
            //increment cumulative total
            cumulativeTotal += orderList.get(id).getBrokerFee();

            format = "Job#%s\n"
                           +"%-20s\t%s\n"
                           +"%-20s\t%s\n"
                           +"%-20s\t%s\n"
                           +"%-20s\t%s\n"
                           +"%-20s\t%s\n"
                           +"%-20s\t%s\n"
                           +"\n"
                           +"%s hours venue hire @ $%s\t$%s\n"
                           +"Brokering commission 10:\t$%s\n"
                           +"\n";

            System.out.printf(format,
            id,
            "Client:", orderList.get(id).getEvent().getClient(),
            "Venue:", orderList.get(id).getVenue().getName(),
            "Event name:", orderList.get(id).getEvent().getTitle(),
            "Artist:", orderList.get(id).getEvent().getArtist(),
            "Event date:", orderList.get(id).getEvent().getDate(),
            "Event time:", orderList.get(id).getEvent().getTime(),
            orderList.get(id).getEvent().getDuration(), orderList.get(id).getVenue().getPricePerHour(),
            orderList.get(id).calculatePrice(),
            orderList.get(id).getBrokerFee()
            );
        }

        format = ""
        +"Total earnings to date: $%s.\n"
        +"--------------------------------------\n";
        System.out.printf(format, cumulativeTotal);
    }
//////////////////
// BOOKING LOOP //
//////////////////
    public void bookingPrompt(){
        menuText = "do you want to make a booking (y/n)?";
        print(menuText);
    }
// END BOOKING LOOP //

//////////////////
// HIRE DETAILS //
//////////////////
    public void hireAuto(){
        menuText = "The best venue for this order will be automatically matched.";
        print(menuText);
    }

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

    public void hireDuration(){
        menuText = "Please enter number of hours: ";
        print(menuText);
    }

    public void hireDate(){
        menuText = "Please enter date: ";
        print(menuText);
    }

    public void hireTime(){
        menuText = "Please enter time: ";
        print(menuText);
    }

    public void hireEventName(){
        menuText = "Event name: ";
        print(menuText);
    }

    public void hireArtistName(){
        menuText = "Artist name: ";
        print(menuText);
    }

    public void hireRequesterName(){
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

package console.handlers;

import console.ui.ConsoleUI;
import java.util.HashMap;
import console.models.Venue;
import console.models.Event;
import console.models.Order;

public class MenuHandler {
    // Dependencies
    private ConsoleUI ui = new ConsoleUI();
    private InputHandler in = new InputHandler();
    private ValidationHandler validate = new ValidationHandler();
    private FileHandler file = new FileHandler();
    private VenueHandler venue = new VenueHandler(file);
    private RequestHandler request = new RequestHandler(file);
    private OrderHandler order = new OrderHandler(request, venue);
    private MatchHandler match = new MatchHandler(request, venue);

    // Lists to store records with ids
    private HashMap<Integer, Venue> venueList = new HashMap<>();
    private HashMap<Integer, Event> requestList = new HashMap<>();
    private HashMap<Integer, Order> orderList = new HashMap<>();
    private HashMap<Event, HashMap<Venue, Integer>> unmatchedList = new HashMap<>();
    private HashMap<Event, Venue> matchedList = new HashMap<>();

    int applicationLoop;
    String input;
    
    public int menuLoop(){
        do{
            applicationLoop = 0;
            displayMenu();
            applicationLoop = in.mainMenuInput();
            if(applicationLoop == 1){
                listCurrentRequests();
            } else if (applicationLoop == 2){
                browseVenueByCategory();
            } else if (applicationLoop == 3){
               searchVenueByName(); 
            } else if (applicationLoop == 4){
                // autoMatchEvents();
                bestMatch();
            } else if (applicationLoop == 5){
                showOrderSummary();
            }
        } while (applicationLoop != 6);

        return applicationLoop;
    }

    void displayMenu(){
        ui.getMainMenu();
    }

///////////////////
// MENU OPTION 1 //
///////////////////
    private void listCurrentRequests(){
        System.out.println("DEBUG!! Inside this.listCurrentRequests()");
        ui.printRequests(request.retrieveRequests(requestList));
        bookingLoop(null);
    }
// END MENU OPTION 1 //

///////////////////
// MENU OPTION 2 //
///////////////////
    private void browseVenueByCategory(){
        System.out.println("DEBUG!! Inside this.browseVenueByCategory()");

        // Make sure there are venues in the list
        venueListNullCheck();
        
        HashMap<Integer, Venue> venueByCategory = selectVenueByCategory(getVenueCategories());
        Venue selectedVenue = selectVenueById(venueByCategory);
        ui.printVenue(selectedVenue);
        bookingLoop(selectedVenue);
    }

// ID related methods
    Venue selectVenueById(HashMap<Integer, Venue> venues){
        ui.selectMessage();
        return venues.get(applicationLoop = in.mainMenuInput());
    }

// Category related methods
    HashMap<Integer, String> getVenueCategories(){
        HashMap<Integer, String> categories = venue.getCategories(venueList);
        ui.getVenueCategories(categories);
        applicationLoop = in.mainMenuInput();
        return categories;
    }

    HashMap<Integer, Venue> selectVenueByCategory(HashMap<Integer, String> categories){
        ui.selectVenue();
        HashMap<Integer, Venue> venues = venue.getVenueByCategory(categories.get(applicationLoop), venueList);
        ui.printVenueNames(venues);
        return venues;
    }    
// END MENU OPTION 2 //

///////////////////
// MENU OPTION 3 //
///////////////////
    private void searchVenueByName(){
        System.out.println("DEBUG!! Inside this.searchVenueByName()");

        boolean searchNameValidated = false;
        HashMap<Integer, Venue> searchResult = null;
        do{
            ui.searchVenueByName();
            input = in.userInput();

            if(!validate.validateSearchVenueByName(input)){
                ui.validateSearchVenueByNameError();
            } else {
                searchNameValidated = true;
                searchResult = selectVenueByName(input);
            }
            
            if(searchResult != null){
                Venue selectedVenue = selectVenueById(searchResult);
                // ui.printVenue(selectedVenue);
                // hireLoop(selectedVenue);
                bookingLoop(selectedVenue);
            } else {
                ui.venueNotFoundError();
            }
        } while(!searchNameValidated);
    }

    HashMap<Integer, Venue> selectVenueByName(String searchName){
        venueListNullCheck();
        HashMap<Integer, Venue> venueListFiltered = venue.searchVenueByName(searchName, venueList);
        if(venueListFiltered.size() > 0){
            ui.selectVenue();
            ui.printVenueNames(venueListFiltered);
            return venueListFiltered;
        }
        return null;
    }
// END MENU OPTION 3 //

///////////////////
// MENU OPTION 4 //
///////////////////
    private void autoMatchEvents(){
        venueListNullCheck();
        requestListNullCheck();

        System.out.println("DEBUG!! Inside this.autoMatchEvents()");
        HashMap<Venue,Integer> temp = new HashMap<>();
        for(int id : requestList.keySet()){
            temp = order.autoMatchEvent(venueList,requestList.get(id));
            unmatchedList.put(requestList.get(id), temp);
        }
        // for(Event list : unmatchedList.keySet()){
        //     for(Venue venue : unmatchedList.get(list).keySet()){
        //         System.out.printf("%s, %s, %s\n", list.getArtist(), venue.getName(), unmatchedList.get(list).get(venue));
        //     }
        // }
    }

    private void bestMatch(){
        autoMatchEvents();
        System.out.println("DEBUG!! Inside this.bestMatch()");
        matchedList = order.getBestMatch(unmatchedList);
    }

    private void generateOrders(){
        orderList = order.generateOrders(orderList, matchedList);
    }
// END MENU OPTION 4 //

///////////////////
// MENU OPTION 5 //
///////////////////
    private void showOrderSummary(){
        System.out.println("DEBUG!! Inside this.showOrderSummary()");

        generateOrders();
        ui.printOrderSummary(orderList);
    }
// END MENU OPTION 5 //

//////////////////
// BOOKING LOOP //
//////////////////
    private void bookingLoop(Venue selectedVenue){
        boolean confimValidated = false;
        do{
            ui.bookingPrompt();
            input = in.userInput();
            if(!validate.validateConfirm(input)){
                ui.validateConfirmError();
            } else {
                confimValidated = true;
            }
        } while(!confimValidated);

        if(input.contains("y")){
            // ui.orderConfirmed();
            // select venue loop
            hireLoop(selectedVenue);
        }
    }
// END BOOKING LOOP //

////////////////////////
// SELECT VENUE LOOP //
///////////////////////

// END SELECT VENUE LOOP //

//////////////////
//   HIRE LOOP  //
//////////////////
    int hireLoop(Venue venue){
        if(venue == null){
            ui.hireAuto();
        } else {
            ui.hirePrice(venue);
            ui.selectMessage();
            applicationLoop = in.mainMenuInput();
        }

        // Guard clause, if 2 exit this loop
        if(applicationLoop == 2){
            return applicationLoop;
        }

        ui.hireDetails();
        Event newEvent = new Event();
        boolean hoursValidated = false;
        do{
            ui.hireDuration();
            input = in.userInput();
            if(!validate.validateHours(input)){
                ui.validateHoursError();
            } else {
                hoursValidated = true;
                newEvent.setDuration(Integer.parseInt(input));
            }
        } while(!hoursValidated);

        boolean dateValidated = false;
        do{
            ui.hireDate();
            input = in.userInput();
            if(!validate.validateDate(input)){
                ui.validateDateError();
            } else {
                dateValidated = true;
                newEvent.setDate(input);
            }
        } while(!dateValidated);

        boolean timeValidated = false;
        do{
            ui.hireTime();
            input = in.userInput();
            if(!validate.validateTime(input)){
                ui.validateTimeError();
            } else {
                timeValidated = true;
                newEvent.setTime(input);
            }
        } while(!timeValidated);

        boolean eventNameValidated = false;
        do{
            ui.hireEventName();
            input = in.userInput();
            if(!validate.validateEventName(input)){
                ui.validateEventNameError();
            } else {
                eventNameValidated = true;
                input = input.replace(",", ";");
                newEvent.setTitle(input);
            }
        } while(!eventNameValidated);

        boolean artistNameValidated = false;
        do{
            ui.hireArtistName();
            input = in.userInput();
            if(!validate.validateArtistName(input)){
                ui.validateArtistNameError();
            } else {
                artistNameValidated = true;
                input = input.replace(",", ";");
                newEvent.setArtist(input);
            }
        } while(!artistNameValidated);

        boolean requesterNameValidated = false;
        do{
            ui.hireRequesterName();
            input = in.userInput();
            if(!validate.validateRequesterName(input)){
                ui.validateRequesterNameError();
            } else {
                requesterNameValidated = true;
                input = input.replace(",", ";");
                newEvent.setClient(input);
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

        // add to list
        if(venue == null){
            requestList.put(requestList.size(), newEvent);
        } else {
            orderList.put(orderList.size(), new Order(newEvent, venue));
        }

        return applicationLoop;
    }
// END HIRE LOOP //

///////////////////
//   UTILITIES   //
///////////////////
    private void venueListNullCheck(){
        if(venueList.size() == 0){
            venueList = venue.retrieveVenues(venueList);
        }
    }

    private void requestListNullCheck(){
        if(requestList.size() == 0){
            requestList = request.retrieveRequests(requestList);
        }
    }

    // private void (){
    // System.out.println("DEBUG!! Inside this.()");
    // }
// END UTILITIES     //
}

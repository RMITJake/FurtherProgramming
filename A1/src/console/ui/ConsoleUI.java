package console.ui;

import java.util.ArrayList;
import console.models.Event;
import console.models.Venue;

public class ConsoleUI {
    private String menuText;

    public void getBanner(){
        menuText = "welcome to Venue Matcher";
        print(menuText);
    }

    public void getMainMenu(){
        menuText = ""
        +"---------------\n"
        +"> Select from main menu\n"
        +"---------------\n"
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
        System.out.println(string);
    }

    public void printEvents(ArrayList<Event> eventsList){
        for (Event event : eventsList) {
            System.out.println(event.client);
        }
    }

    public void printVenues(ArrayList<Venue> venuesList){
        for (Venue venue : venuesList) {
            System.out.println(venue.name);
        }
    }
}

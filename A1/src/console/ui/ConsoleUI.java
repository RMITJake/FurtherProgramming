package console.ui;

import java.util.ArrayList;
import console.models.*;

public class ConsoleUI {
    private String menuText;
    private String selectPrompt = "Please select: ";

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
        +"6) Exit\n";
        print(menuText);
        print(selectPrompt);
    }

    public void printSelect(String selection){
        menuText = ""
        +"---------------\n"
        +"> Select from " + selection + "\n"
        +"---------------\n";
        print(menuText);
    }

    public void print(String string){
        System.out.print(string);
    }

    public void print(EventArray eventsList){
        for (Event event : eventsList) {
            System.out.println(event.client);
        }
    }

    public void print(VenueArray venuesList){
        for (int i=0; i < venuesList.size(); i++){
            System.out.print((i+1) + ") ");
            System.out.println(venuesList.get(i).toString());
        }
        print(selectPrompt);
    }

    public void print(ArrayList<String> stringList){
        for (int i=0; i < stringList.size(); i++){
            System.out.print((i+1) + ") ");
            System.out.println(stringList.get(i));
        }
        print(selectPrompt);
    }

    public void print(Venue venue){
        System.out.println(venue.toString());
    }
}

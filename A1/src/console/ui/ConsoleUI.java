package console.ui;

import java.util.ArrayList;
import console.models.*;

public class ConsoleUI {
    private String menuText;
    private String selectPrompt = "Please select: ";
    private String lineBreak = "---------------\n";

    public void getBanner(){
        menuText = "welcome to Venue Matcher";
        print(menuText);
    }

    public void getMainMenu(){
        menuText = ""
        +lineBreak
        +"> Select from main menu\n"
        +lineBreak
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
        +lineBreak
        +"> Select from " + selection + "\n"
        +lineBreak;
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

    public void hireMenu(Venue venue){
        menuText = ""
        +lineBreak
        +"1) Hire for $" + 550.00 + "\n"
        +"2) Back to venue list";
        System.out.println(menuText);
    }
}

package console.handlers;
import java.util.Scanner;;

class InputHandler {
    Scanner scn = new Scanner(System.in);
    ValidationHandler validator = new ValidationHandler();
    String userInput;
    String regex;

    public int mainMenuInput(){
        userInput = scn.nextLine();

        if(!validator.validateInt(userInput)){
            System.out.println("Please select a valid menu option.");
            return -1;
        }
        
        return Integer.parseInt(userInput);
    }

    public String hireDetailsHoursInput(){
        regex = "";
        userInput = scn.nextLine();
        return userInput;
    }

    public String hireDetailsDateInput(){
        regex = "";
        userInput = scn.nextLine();
        return userInput;
    }

    public String hireDetailsTimeInput(){
        regex = "";
        userInput = scn.nextLine();
        return userInput;
    }

    public String hireDetailsEventNameInput(){
        regex = "";
        userInput = scn.nextLine();
        return userInput;
    }

    public String hireDetailsArtistNameInput(){
        regex = "";
        userInput = scn.nextLine();
        return userInput;
    }

    public String hireDetailsRequesterNameInput(){
        regex = "";
        userInput = scn.nextLine();
        return userInput;
    }

    public String hireDetailsConfirmInput(){
        regex = "";
        userInput = scn.nextLine();
        return userInput;
    }
}

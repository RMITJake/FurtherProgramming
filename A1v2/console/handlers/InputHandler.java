package console.handlers;
import java.util.Scanner;;

public class InputHandler {
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

        int inputInt = 0;
        try{
            inputInt = Integer.parseInt(userInput);
        } catch (Exception NumberFormatException){
            System.err.println(userInput + " is not a valid number");
        }
        
        return inputInt;
    }

    public String userInput(){
        userInput = scn.nextLine();
        return userInput;
    }
}

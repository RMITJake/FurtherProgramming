package console.handlers;
import java.util.Scanner;;

class InputHandler {
    Scanner scn = new Scanner(System.in);
    ValidationHandler validator = new ValidationHandler();
    String userInput;
    
    public int mainMenuInput(){
        userInput = scn.nextLine();

        if(!validator.validateInt(userInput)){
            System.out.println("Please select a valid menu option.");
            return -1;
        }
        
        return Integer.parseInt(userInput);
    }
}

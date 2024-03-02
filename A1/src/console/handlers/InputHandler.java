package console.handlers;
import java.util.Scanner;;

class InputHandler {
    Scanner scn = new Scanner(System.in);
    ValidationHandler validator = new ValidationHandler();
    String userInput;
    
    public int mainMenuInput(){
        System.out.println("InputHandler.mainMenuInput()");
        userInput = scn.nextLine();

        if(!validator.validateInt(userInput)){
            System.out.println("validateInt failed.");
            return -1;
        }
        
        System.out.println("validateInt success.");
        return Integer.parseInt(userInput);
    }
}

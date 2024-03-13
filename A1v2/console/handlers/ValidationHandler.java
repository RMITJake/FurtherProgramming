package console.handlers;
import java.util.regex.*;

class ValidationHandler {
    String mainMenuRegex = "^[1-6]$";

    boolean regexMatch(String regex, String input){
        return Pattern.matches(regex, input);
    }

    boolean validateInt(String input){
        try {
            Integer.parseInt(input);
        } catch (Exception e){
            return false;
        }
        return regexMatch(mainMenuRegex, input);
    }    
}
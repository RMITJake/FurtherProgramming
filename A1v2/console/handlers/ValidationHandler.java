package console.handlers;
import java.util.regex.*;

class ValidationHandler {
    String mainMenuRegex = "^[1-6]$";
    String regex;

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

    boolean validateHours(String input){
        regex = "";
        return regexMatch(regex, input);
    }

    boolean validateDate(String input){
        regex = "";
        return regexMatch(regex, input);
    }

    boolean validateTime(String input){
        regex = "";
        return regexMatch(regex, input);
    }

    boolean validateEventName(String input){
        regex = "";
        return regexMatch(regex, input);
    }

    boolean validateArtistName(String input){
        regex = "";
        return regexMatch(regex, input);
    }

    boolean validateRequesterName(String input){
        regex = "";
        return regexMatch(regex, input);
    }

    boolean validateConfirm(String input){
        regex = "[Y|N|y|n]";
        return regexMatch(regex, input);
    }
}
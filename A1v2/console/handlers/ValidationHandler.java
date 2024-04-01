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
        regex = "(^[1-9]$|^1[0-9]$|^2[0-4]$)";
        return regexMatch(regex, input);
    }

    boolean validateDate(String input){
        regex = "^((0[1-9]|[12][0-9]|3[01])\\/(01|03|05|07|08|10|12)|(0[1-9]|[12][0-9]|30)\\/(01|04|06|09|11)|([01][1-9]|2[0-9])\\/(02))\\/(20)([2-9][4-9])$";
        return regexMatch(regex, input);
    }

    boolean validateTime(String input){
        regex = "^(0?[1-9]|1[0-2]):([0-6]\\d)(am|AM|pm|PM)$";
        return regexMatch(regex, input);
    }

    boolean validateEventName(String input){
        regex = "^\\w[\\w|\\s]{1,}\\w$";
        return regexMatch(regex, input);
    }

    boolean validateArtistName(String input){
        regex = "^\\w[\\w|\\s]{1,}\\w$";
        return regexMatch(regex, input);
    }

    boolean validateRequesterName(String input){
        regex = "^\\w[\\w|\\s]{1,}\\w$";
        return regexMatch(regex, input);
    }

    boolean validateConfirm(String input){
        regex = "[Y|N|y|n]";
        return regexMatch(regex, input);
    }

    boolean validateSearchVenueByName(String input){
        regex = "^\\w[\\w|\\s]{1,}\\w$";
        return regexMatch(regex, input);
    }
}
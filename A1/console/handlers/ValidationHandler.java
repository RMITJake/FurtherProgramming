package console.handlers;
import java.util.regex.*;

public class ValidationHandler {
    String mainMenuRegex = "^[1-6]$";
    String regex;

    public boolean regexMatch(String regex, String input){
        return Pattern.matches(regex, input);
    }

    public boolean validateInt(String input){
        try {
            Integer.parseInt(input);
        } catch (Exception e){
            return false;
        }
        return regexMatch(mainMenuRegex, input);
    }    

    public boolean validateHours(String input){
        regex = "(^[1-9]$|^1[0-9]$|^2[0-4]$)";
        return regexMatch(regex, input);
    }

    public boolean validateDate(String input){
        regex = "^(([1-9]|[12][0-9]|3[01])\\/(1|3|5|7|8|10|12)|([1-9]|[12][0-9]|30)\\/(1|4|6|9|11)|([1]?[1-9]|2[0-9])\\/(2))\\/(20)([2-9][0-9])$";
        return regexMatch(regex, input);
    }

    public boolean validateTime(String input){
        regex = "^(0?[1-9]|1[0-2]):([0-6]\\d)(am|AM|pm|PM)$";
        return regexMatch(regex, input);
    }

    boolean validateEventName(String input){
        regex = "^\\w[\\w|\\s]{1,}\\w$";
        return regexMatch(regex, input);
    }

    public boolean validateArtistName(String input){
        regex = "^\\w[\\w|\\s]{1,}\\w$";
        return regexMatch(regex, input);
    }

    boolean validateRequesterName(String input){
        regex = "^\\w[\\w|\\s]{1,}\\w$";
        return regexMatch(regex, input);
    }

    public boolean validateConfirm(String input){
        regex = "[Y|N|y|n]";
        return regexMatch(regex, input);
    }

    boolean validateSearchVenueByName(String input){
        regex = "^\\w[\\w|\\s]{1,}\\w$";
        return regexMatch(regex, input);
    }
}
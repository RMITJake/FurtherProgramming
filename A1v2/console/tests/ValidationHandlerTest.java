package console.tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import console.handlers.ValidationHandler;

public class ValidationHandlerTest {
    ValidationHandler validation;
    String regex;
    String string;

    @Before()
    public void setUp(){
        validation = new ValidationHandler();
    }

    @Test()
    public void regexMatchTrueTest(){
        regex = "^This is a regex string, it needs to be matched!$";
        string = "This is a regex string, it needs to be matched!";
        assertTrue(validation.regexMatch(regex, string));
    }

    @Test()
    public void regexMatchFalseTest(){
        regex = "^This is a regex string, it needs to be matched!$";
        string = "^No match will occur with this string...$";
        assertFalse(validation.regexMatch(regex, string));
    }

    @Test()
    public void validateIntTrueTest(){
        assertTrue(validation.validateInt("2"));
    }

    @Test()
    public void validateIntFalseTest(){
        assertFalse(validation.validateInt("This is not a number"));
    }

    @Test()
    public void validateHoursUpperBoundTrueTest(){
        string = "24";
        assertTrue(validation.validateHours(string));
    }

    @Test()
    public void validateHoursUpperBoundFalseTest(){
        string = "25";
        assertFalse(validation.validateHours(string));
    }

    @Test()
    public void validateHoursLowerBoundTrueTest(){
        string = "1";
        assertTrue(validation.validateHours(string));
    }

    @Test()
    public void validateHoursLowerBoundFalseTest(){
        string = "0";
        assertFalse(validation.validateHours(string));
    }

    @Test()
    public void validateMonthLongUpperBoundTrueTest(){
        string = "31/1/2024";
        assertTrue(validation.validateDate(string));
    }

    @Test()
    public void validateMonthLongUpperBoundFalseTest(){
        string = "32/1/2024";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthLongLowerBoundTrueTest(){
        string = "1/1/2024";
        assertTrue(validation.validateDate(string));
    }

    @Test()
    public void validateMonthLongLowerBoundFalseTest(){
        string = "0/1/2024";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthShortUpperBoundTrueTest(){
        string = "30/4/2024";
        assertTrue(validation.validateDate(string));
    }

    @Test()
    public void validateMonthShortUpperBoundFalseTest(){
        string = "32/4/2024";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthShortLowerBoundTrueTest(){
        string = "1/4/2024";
        assertTrue(validation.validateDate(string));
    }

    @Test()
    public void validateMonthShortLowerBoundFalseTest(){
        string = "0/4/2024";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthFebruaryUpperBoundTrueTest(){
        string = "28/2/2024";
        assertTrue(validation.validateDate(string));
    }

    @Test()
    public void validateMonthFebruaryUpperBoundFalseTest(){
        string = "30/2/2024";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthFebruaryLowerBoundTrueTest(){
        string = "1/4/2024";
        assertTrue(validation.validateDate(string));
    }

    @Test()
    public void validateMonthFebruaryLowerBoundFalseTest(){
        string = "0/4/2024";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthMonthUpperBoundFalseTest(){
        string = "1/13/2024";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthMonthLowerBoundFalseTest(){
        string = "1/0/2024";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthYearUpperBoundTrueTest(){
        string = "1/1/2099";
        assertTrue(validation.validateDate(string));
    }

    @Test()
    public void validateMonthYearUpperBoundFalseTest(){
        string = "1/1/3000";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateMonthYearLowerBoundTrueTest(){
        string = "1/1/2020";
        assertTrue(validation.validateDate(string));
    }

    @Test()
    public void validateMonthYearLowerBoundFalseTest(){
        string = "1/1/2019";
        assertFalse(validation.validateDate(string));
    }

    @Test()
    public void validateTimeTrueTest(){
        string = "12:00pm";
        assertTrue(validation.validateTime(string));
    }

    @Test()
    public void validateTimeFalseTest(){
        string = "13:00am";
        assertFalse(validation.validateTime(string));
    }

    @Test()
    public void validateTimeCodeFalseTest(){
        string = "12:00xm";
        assertFalse(validation.validateTime(string));
    }

    @Test()
    public void validateNameTrueTest(){
        string = "Colin McCray";
        assertTrue(validation.validateArtistName(string));
    }

    @Test()
    public void validateNameFalseTest(){
        string = " ";
        assertFalse(validation.validateArtistName(string));
    }

    @Test()
    public void validateConfirmYTrueTest(){
        string = "y";
        assertTrue(validation.validateConfirm(string));
    }

    @Test()
    public void validateConfirmNTrueTest(){
        string = "N";
        assertTrue(validation.validateConfirm(string));
    }

    @Test()
    public void validateConfirmFalseTest(){
        string = "x";
        assertFalse(validation.validateConfirm(string));
    }    

    // @Test()
    // public void TrueTest(){
    //     regex = ;
    //     string = ;
    //     assertTrue();
    // }

    // @Test()
    // public void FalseTest(){
    //     regex = ;
    //     string = ;
    //     assertFalse();
    // }
}

package console.tests;
import console.handlers.FileHandler;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.*;

import java.io.FileNotFoundException;
import java.util.List;

public class FileHandlerTest {
    private String testFile = "venues.csv";
    FileHandler file = new FileHandler();

    @Test(expected=FileNotFoundException.class)
    public void getLineFromCSVNullPointerExceptionTest() throws FileNotFoundException{
        file.getLineFromCSV("404.csv");
    }

    @Test()
    public void getLineFromCSVTest() throws FileNotFoundException{
        String input = "inputTest.csv";
        String expected = "Home,4000,relax; watch tv,Indoor,100";
        List<List<String>> fileContents = file.getLineFromCSV(input);
        String fileString = "";
        for(List<String> outerStr : fileContents){
            for(String innerStr : outerStr){
                fileString += innerStr + ",";
            }
        }
        fileString = fileString.substring(0, fileString.length()-1);

        assertEquals(expected, fileString);
    }
}

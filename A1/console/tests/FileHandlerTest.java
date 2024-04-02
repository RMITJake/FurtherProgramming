package console.tests;
import console.handlers.FileHandler;
import static org.junit.Assert.assertEquals;

import org.junit.*;

import java.io.FileNotFoundException;
import java.util.List;

public class FileHandlerTest {
    private String testFile;
    FileHandler file;

    @Before()
    public void setUp(){
        file = new FileHandler();
        testFile = "inputTest.csv";
    }

    @Test(expected=FileNotFoundException.class)
    public void getLineFromCSVNullPointerExceptionTest() throws FileNotFoundException{
        file.getLineFromCSV("404.csv");
    }

    @Test()
    public void getLineFromCSVTest() throws FileNotFoundException{
        String input = testFile;
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

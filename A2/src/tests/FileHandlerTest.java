package src.tests;
import src.handlers.FileHandler;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerTest extends FileHandler {
    private String testFile;

///////////
// Setup //
///////////
    @Before()
    public void setUp(){
        testFile = "files/tests/inputTest.csv";
    }
// END Setup

////////////////////////
// getLineFromCSVTest //
////////////////////////
    @Test()
    public void getLineFromCSVTest() throws FileNotFoundException{
        String input = testFile;
        List<List<String>> expected = new ArrayList<>();
        List<String> record = new ArrayList<String>();

        record = new ArrayList<String>(){{
            add("Name");
            add("Capacity");
            add("Suitable for");
            add("Category");
            add("Price per hour");
        }};
        expected.add(record);

        record = new ArrayList<String>(){{
            add("Home");
            add("4000");
            add("relax; watch tv");
            add("Indoor");
            add("100");
        }};
        expected.add(record);

        List<List<String>> fileContents = getLineFromCSV(input);
        
        assertEquals(expected, fileContents);
    }
// END getLineFromCSVTest

/////////////////////////////////////////////
// getLineFromCSVFileNotFoundExceptionTest //
/////////////////////////////////////////////
    @Test(expected=FileNotFoundException.class)
    public void getLineFromCSVFileNotFoundExceptionTest() throws FileNotFoundException{
        getLineFromCSV("files/404.csv");
    }
// END getLineFromCSVFileNotFoundExceptionTest
}

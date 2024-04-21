package src.tests;
import src.handlers.FileHandler;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.List;

public class FileHandlerTest extends FileHandler {
    private String testFile;

///////////
// Setup //
///////////
    @Before()
    public void setUp(){
        testFile = "files/inputTest.csv";
    }
// END Setup


////////////////////////
// getLineFromCSVTest //
////////////////////////
@Test()
public void getLineFromCSVTest() throws FileNotFoundException{
    String input = testFile;
    String expected = "Home,4000,relax; watch tv,Indoor,100";
    List<List<String>> fileContents = getLineFromCSV(input);
    String fileString = "";
    for(List<String> outerStr : fileContents){
        for(String innerStr : outerStr){
            fileString += innerStr + ",";
        }
    }
    fileString = fileString.substring(0, fileString.length()-1);
    
    assertEquals(expected, fileString);
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

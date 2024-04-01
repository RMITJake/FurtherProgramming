package console.tests;
import console.handlers.FileHandler;
import org.junit.*;
import static org.junit.Assert.*;

import java.beans.Transient;
import java.util.Scanner;

public class FileHandlerTest {
    FileHandler file;
    private String COMMA_DELIMITER = ",";
    private Stirng testFile = "venues.csv";

    @Before
    public void setUp(){
        FileHandler file = new FileHandler();
        System.out.println("IN FHTEST");
    }

    @Test (expected=FileNotFoundException)
    public void testFileInput(){
        file.getLineFromCSV("FileNotFound.csv");
    }
}

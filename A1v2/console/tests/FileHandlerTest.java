package console.tests;
import console.handlers.FileHandler;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;

import java.beans.Transient;
import java.util.Scanner;

public class FileHandlerTest {
    FileHandler file;
    private String COMMA_DELIMITER = ",";
    private String testFile = "venues.csv";

    @Before
    public void setUp(){
        FileHandler file = new FileHandler();
        System.out.println("IN FHTEST");
    }

    @Test (expected=NullPointerException.class)
    public void testFileInput(){
        file.getLineFromCSV("404.csv");
    }
}

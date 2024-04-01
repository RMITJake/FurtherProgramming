package console.handlers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private String COMMA_DELIMITER = ",";

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        } catch (NullPointerException NPE){
            System.err.println("file line is null");
        }
        return values;
    }
    
    public List<List<String>> getLineFromCSV(String fileName) throws FileNotFoundException{
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
            return records;
        } catch (NullPointerException NPE){
            System.err.println("file line is null");
        }
        return null;
    }
}
package fpoua.week4.assessment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
	

			private String COMMA_DELIMITER = ",";

			/*
			 * getRecordFromLine
			 * @param line
			 * @returns value which is a List of Strings
			 */
			private List<String> getRecordFromLine(String line) {
				List<String> values = new ArrayList<String>();
				try (Scanner rowScanner = new Scanner(line)) {
					rowScanner.useDelimiter(COMMA_DELIMITER);
					while (rowScanner.hasNext()) {
						values.add(rowScanner.next());
					
					}
				}
				return values;
			}
			
			/*
			 * getLineFromCSV
			 * @param filename
			 * @returns records (List of Lists of Strings)
			 */
			public List<List<String>> getLineFromCSV(String fileName) throws FileNotFoundException{
				
				List<List<String>> records = new ArrayList<>();
				try (Scanner scanner = new Scanner(new File(fileName))) {
					while (scanner.hasNextLine()) {
						records.add(getRecordFromLine(scanner.nextLine()));
					}
					
					return records;
				}

			}

	
}

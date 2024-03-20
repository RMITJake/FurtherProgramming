package fpoua.week4.assessment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainWeek4 {

	public MainWeek4() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * WEEK4 ASSESSMENT Objectives: 
		 * - Import data from CSV file named pandas.csv 
		 * - Populate a List of Pandas with new Panda objects according to the data read from CSV.
		 * - Every new Panda will be given a unique id which increments
		 *   the last record's id. 
		 * - Print pandas in elegant table-like format
		 * - Show the name of the 3rd Panda in pandas list
		 * - List All pandas that contain blue colour
		 * HINT: CSV reader class is given.
		 * HINT: Animal class should now have a new constructor that takes in ID amongst other fields.
		 * HINT: Panda is an Animal sub-class.
		 * 
		 */

		CSVReader csvReader = new CSVReader();
		List<List<String>> records = csvReader.getLineFromCSV("pandas.csv");

		System.out.println(records);

		List<Panda> pandas = new ArrayList<Panda>();
		int recordNo = 0; // Every Panda should be given and id, starting from 1.

		records.remove(0); // remove the first line which is the row for column headers.

		// Add pandas to Panda list according to textual records from CSV file.
		for (List<String> record : records) {
			// Each time a new Panda is added, it's got given recordNo that increments from
			// the previous one.
			// Assuming we got zero Panda to begin with, 
			// the first Panda should be given id number 1 and so on.
			// HINT: To trim whitespaces for the age, use the strip() method.
			// HINT: To convert String to integer use Integer.parseInt

			// ASSESSED WEEK4: create a temp Panda object whilst taking in recordNo, name,
			// colour, and age;
			// Write your code below
			Panda aPanda = //.......
					
			// ASSESSED WEEK4: add the above new Panda into 'pandas' List.
			// Write your code below
			//.......
		}

		// System.out.println(pandas);

		// Print all Pandas, elegantly
		System.out.printf("%-3s%-10s%-20s%s\n", "ID", "Name", "Colour", "Age");
		for (Panda panda : pandas) {
			System.out.printf("%-3d%-10s%-20s%d\n", panda.getID(), panda.getName(), panda.getColour(), panda.getAge());

		}
		
		//HINT: List just the name of the third Panda in pandas list
		System.out.println(pandas.get(2).getName());
		
		//ASSESSED in Week 4
		//List ALL pandas that has blue as one of their colours 
		//HINT: The field should contain the searched colour
		//Write your code below
		//......
		
		
		//Additional Exercise: NOT ASSESSED in Week 4
		//ADD a Generic List of Animals that allows for all kinds of animals to be added.
		//Refer to Week 3 solution for similar case
	}

}

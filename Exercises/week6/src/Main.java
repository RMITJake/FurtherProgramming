package src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		/*
		 * WEEK6 ASSESSMENT Objectives: 
		 * 
		 * TASK 1: Create a List of fishes and populate it
		 * with the following data: 
		 *  1, Nemo, orange, 20 
		 * 	2, Dorry, blue, 7
		 * 
		 * TASK 2: Using ObjectOutputStream, write pandas list and fishes list to a file
		 * called pandas_and_fishes.dat All objects involved in the serialization
		 * process need to be serializable
		 * 
		 * TASK 3: Using ObjectInputStream, read pandas list and fishes list from the
		 * file called pandas_and_fishes.data and load them into to a new list called
		 * animals
		 * 
		 * TASK 4: Using the given GenericAnimalList, load pandas list and fishes list
		 * into a generic animal list.
		 */

		CSVReader csvReader = new CSVReader();
		List<List<String>> records = csvReader.getLineFromCSV("pandas.csv");

		// ASSESSMENT WEEK 6 TASK 1: Declare a list of fishes.
		List<Fish> fishes = new ArrayList<Fish>();

		// ASSESSMENT WEEK 6 TASK 1: Populate the list with 2 fishes:
		// 1, Nemo, orange, 7
		// 2, Dorry, blue, 20
		fishes.add(new Fish(1, "Nemo", "orange", 7));
		fishes.add(new Fish(2, "Dorry", "blue", 20));

		List<Panda> pandas = new ArrayList<Panda>();
		int recordNo = 0; // Every Panda should be given and id, starting from 1.

		records.remove(0); // remove the first line which is the row for column headers.

		// Add pandas to Panda list according to textual records from CSV file.
		for (List<String> record : records) {
			Panda aPanda = new Panda(++recordNo, record.get(0), record.get(1), Integer.parseInt(record.get(2).strip()));

			// Add that new Panda into Panda List.
			pandas.add(aPanda);
		}

		// Print all Pandas, elegantly
		System.out.printf("%-3s%-10s%-20s%s\n", "ID", "Name", "Colour", "Age");
		for (Panda panda : pandas) {
			System.out.printf("%-3d%-10s%-20s%d\n", panda.getID(), panda.getName(), panda.getColour(), panda.getAge());
		}

		/**
		 * ASSESSMENT WEEK 6 TASK 2 Using ObjectOutputStream, write pandas list and
		 * fishes list to a file called pandas_and_fishes.dat refer to Week 6 lecture
		 * notes on how to serialize and writeObject
		 */
		for(Panda panda : pandas){
			System.out.println(panda.getName());
		}
		for(Fish fish : fishes){
			System.out.println(fish.getName());
		}
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pandas_and_fishes.dat", true));
		out.writeObject(pandas);
		out.writeObject(fishes);
		out.close();

		/**
		 * ASSESSMENT WEEK 6 TASK 3: Using ObjectInputStream, read pandas list and fishes list from the
		 * file called pandas_and_fishes.data and load them into to a new list called
		 * animals
		 */
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("pandas_and_fishes.dat"));
		
		List<Fish> fishList = new ArrayList<Fish>();
		fishList = (ArrayList<Fish>) in.readObject();

		List<Panda> pandaList = new ArrayList<Panda>();
		pandaList = (ArrayList<Panda>) in.readObject();
		in.close();

		System.out.println(fishList);
		System.out.println(pandaList);

		/**
		 * ASSESSMENT WEEK 6 TASK 4 Add both the fish list and panda list into a generic
		 * animal list using existing GenericAnimalList's populate method. Refer to
		 * MainWeek4.java solution on how to use it.
		 */
		GenericAnimalList animals = new GenericAnimalList();
		animals.populate(fishList);
		animals.populate(pandaList);
		System.out.println("Generic Animal List contains " + animals.getList());

	}

}

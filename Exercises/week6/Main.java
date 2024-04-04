import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.Serializable;
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
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pandas_and_fishes.dat"));
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

/*
 * ASSESSMENT WEEK 6 TASK 2
 * Panda should now implement Serializable
 */
class Panda extends Animal implements Serializable{
	public Panda() {
		super();
	}

	public Panda(String name, String colour, int age) {
		super(name, colour, age);
		// TODO Auto-generated constructor stub
	}

	public Panda(int id, String name, String colour, int age) {
		super(id, name, colour, age);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub

	}
}

/*
 * ASSESSMENT WEEK 6 TASK 2
 * Fish should now implement Serializable in addition to CanSwim
 */
class Fish extends Animal implements CanSwim, Serializable{
	public Fish() {
		super();
	}
	
	/**
	 * 3 args constructor
	 * @param name
	 * @param colour
	 * @param age
	 */
	Fish (String name, String colour, int age) {
		super(name, colour, age);
	}
	
	/*
	 * 4 args constructor
	 * @param id
	 * @param name
	 * @param colour
	 * @param age
	 */
	Fish (int id, String name, String colour, int age) {
		super(id, name, colour, age);
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("Just keep swimming");
	}

	@Override
	public void swim() {
		// TODO Auto-generated method stub
		System.out.println("swimming");
	}
}

/*
 * ASSESSMENT WEEK 6 TASK 2
 * Animal should now be serializable
 */
abstract class Animal implements Serializable{
	protected int id;
	protected String name;
	protected String colour;
	protected int age;

	/*
	 * Animal default constructor that has no parameter.
	 * Initialises id, name, colour, and age with default falues.
	 * write your code below
	 */
	public Animal() {
		this.id = 0;
		this.name = "new animal";
		colour = "";
		age = 0;
	}
	
	/*
	 * Animal secondary constructor that takes in name, colour and age
	 * @param name, colour, age
	 */
	public Animal(String name, String colour, int age) {
		this.name = name;
		this.colour = colour;
		this.age = age;
	}
	
	/*
	 * Animal tertiary constructor that takes in id, name, colour and age
	 * @param id, name, colour, age
	 * write your code bellow
	 */
	public Animal(int id, String name, String colour, int age) {
		this.id = id;
		this.name = name;
		this.colour = colour;
		this.age = age;
	}
	
	public abstract void makeSound();
	
	public void eat() {
		System.out.println(getName() + " is eating");
	}
	
	public void sleep() {
		System.out.println(getName() + " is sleeping");
	}
	
	public String getName() {
		return name;
	}
	
	public String getColour() {
		return colour;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Setter method for id
	 */ 
	public void setID(int id) {
		this.id = id;
	}
	/*
	 * Getter method for for id
	 */ 
	public int getID() {
		return id;
	}
	
	/*
	 * Quick and dirty string to check content of object.
	 */
	public String toString() {
		return id + " " + name + " " + colour + " " + age;
	}
}

interface CanSwim {
	public void swim();
}

class GenericAnimalList<T extends Animal>  {
    List<T> list = new ArrayList<T>();
    public GenericAnimalList(){}

    public void populate(List<T> aList){
        list.addAll(aList);
    }
    
    public List<T> getList() {
    	return list;
    }
}

class CSVReader {
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

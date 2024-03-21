import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Main {

	public Main() {
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
			Panda aPanda = new Panda(recordNo, "Perry", "Red", 2);//.......
					
			// ASSESSED WEEK4: add the above new Panda into 'pandas' List.
			// Write your code below
			//.......
            recordNo++;
            pandas.add(new Panda(recordNo, record.get(0), record.get(1), Integer.parseInt(record.get(2))));
		}

		System.out.println(pandas);

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
		for (Panda panda : pandas) {
            if(panda.getColour().contains("blue")){
			    System.out.printf("%-3d%-10s%-20s%d\n", panda.getID(), panda.getName(), panda.getColour(), panda.getAge());
            }
        }
		
		//Additional Exercise: NOT ASSESSED in Week 4
		//ADD a Generic List of Animals that allows for all kinds of animals to be added.
		//Refer to Week 3 solution for similar case
	}
}

class Panda extends Animal {
	public Panda(String name, String colour, int age) {
		super(name, colour, age);
	}

	public Panda(int id, String name, String colour, int age) {
		//ASSESSMENT WEEK 4:
		//Complete the initialisation of fields for this constructor
        super(id, name, colour, age);
	}

	@Override
	public void makeSound() {
		// Do nothing
	}
}

abstract class Animal {
	
	//WEEK 4 ASSESSED: id field as integer. Write your code below
	//.....
    protected int id;
	protected String name;
	protected String colour;
	protected int age;

	/*
	 * Animal default constructor that has no parameter.
	 * Initialises id, name, colour, and age with default values.
	 * At this stage:
	 *  - id should be 0
	 *  - Name should be "new animal"
	 *  - Colour should be a blank string
	 *  - Age should be 0
	 * write your code below
	 */
	public Animal() {
		//....
		//....
		colour = "";
		//....
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
	 * WEEK 4 ASSESSED
	 * Animal tertiary constructor that takes in id, name, colour and age
	 * @param id, name, colour, age
	 * write your code bellow
	 */
	//.....
    public Animal(int id, String name, String colour, int age){
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
	 * WEEK 4 ASSSESSED
	 * Setter method for id
	 * write your code below
	 */ 
	//....
    public void setID(int id) {
        this.id = id;
    }
	
	/*
	 * WEEK 4 ASSSESSED
	 * Getter method for for id
	 * write your code below
	 */ 
	//....
    public int getID() {
        return this.id;
    }

	/*
	 * Quick and dirty string to check content of object.
	 */
	public String toString() {
		return id + " " + name + " " + colour + " " + age;
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

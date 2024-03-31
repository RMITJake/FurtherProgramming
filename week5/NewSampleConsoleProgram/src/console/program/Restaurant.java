package console.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Restaurant class provides the functionality needed to manage orders and checkout.
 */
public class Restaurant {

	private String name;
	private ArrayList<Food> foodList;
    
    public Restaurant(String name) {
    	this.name = name;
    	this.foodList = new ArrayList<Food>();
    }
    
    public String getName() {
    	return this.name;
    }
    
    public ArrayList<Food> getFoodList() {
    	return this.foodList;
    }
    
    /**
     * The method to initialize the restaurant
     */
    public void initRestaurant(String path){
        Scanner sc;
        try {
            sc = new Scanner(new File(path));
            while (sc.hasNextLine()) {
                String[] record = sc.nextLine().split(",");
                foodList.add(new Food(record[0], Double.parseDouble(record[1].replace("$", "")))); 
            }
            sc.close();
        } catch (NullPointerException e) {
        	System.err.println("File is null.");
 		    System.exit(1);
        } catch (FileNotFoundException e) {
        	System.err.println("File not found.");
 		    System.exit(1);
        } 
    }
    
	/**
     * The method to operate the restaurant.
     */
    public void run() {
    	boolean exit = false;
    	char input;
    	do {
			printMenu(this.getName());
			
			try {
				input = readUserInput(System.in);
	    	} catch (IndexOutOfBoundsException e) {
	    		System.out.println("Please provide a valid input.");
	    		continue;
	    	}
			
			switch (input) {
				case 'a':
					this.order();
					break;
				case 'b':
					this.checkout();
					break;
				case 'c':
					exit = true;
					break;
				default:
					System.out.println("Please select a valid menu option.");
					break;
			}
		} while (!exit);
    }
    
	/**
     * The utility method to print menu options.
     */
	public static void printMenu(String name){
		String banner = new String(new char[50]).replace('\u0000', '=');
		System.out.println(banner + "\n" + "Welcome to " + name + "\n" + banner);
		System.out.printf("   %s%n", "a) Order");
		System.out.printf("   %s%n", "b) Checkout");
		System.out.printf("   %s%n", "c) Exit");
		System.out.print("Please select: ");
	}
	
	/**
     * The utility method to read user input.
     */
    public static char readUserInput(InputStream input) throws IndexOutOfBoundsException {
	    Scanner sc = new Scanner(input);
	    String stringInput = sc.nextLine();
	    return stringInput.charAt(0);
    }
    	
    /**
     * The method to place orders.
     */
    public void order() {
    	System.out.println("Order food.");
    }
    
    /**
     * The method to manage checkout.
     */
    public void checkout() {
    	System.out.println("Checkout.");
    }
}

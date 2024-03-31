package console.program;

/**
 * The Main class is the entry point of the sample console program.
 */
public class Main {
	
	public static void main(String args[]){
		Restaurant restaurant = new Restaurant("Fast Burger");
		restaurant.initRestaurant("Restaurant.txt");
		restaurant.run();
	}
}

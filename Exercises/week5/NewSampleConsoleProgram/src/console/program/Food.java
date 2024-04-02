package console.program;

/**
 * The Food class contains attributes of a food item.
 */
public class Food {
	String name;
	double price;
	
	public Food(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
}

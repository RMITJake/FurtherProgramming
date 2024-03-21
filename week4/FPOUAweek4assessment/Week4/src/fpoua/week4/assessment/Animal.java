package fpoua.week4.assessment;

public abstract class Animal {
	
	//WEEK 4 ASSESSED: id field as integer. Write your code below
	//.....
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
	
	/*
	 * WEEK 4 ASSSESSED
	 * Getter method for for id
	 * write your code below
	 */ 
	//....

	
	/*
	 * Quick and dirty string to check content of object.
	 */
	public String toString() {
		return id + " " + name + " " + colour + " " + age;
	}
}

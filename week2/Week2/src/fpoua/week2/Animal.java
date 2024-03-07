package fpoua.week2;

public abstract class Animal {
	protected String name;
	protected String colour;
	protected int age;

	public Animal(String name, String colour, int age) {
		this.name = name;
		this.colour = colour;
		this.age = age;
	}
	
	public abstract void makeSound();
	
	public String getName() {
		return name;
	}
	
	public String getColour() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
}

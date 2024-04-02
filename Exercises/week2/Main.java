/*
 * Jake Kent
 * s3905550
*/
public class Main {
	public static void main(String[] args) {
		Animal animal[] = new Animal[3]; 
		animal[0] = new Cat("Figaro", "black and white", 12);
		animal[1] = new Dog("Pluto", "yellow", 9);
		animal[2] = new Fish("Dory", "orange", 20);
		//TASK 1: please add a 20-year old, orange fish named Dory who will say "Just keep swimming!" when asked to  make sound.
		
		for(int i=0; i<animal.length; i++) {
			System.out.print(animal[i].getName() + " says ");
			animal[i].makeSound();
		}
		
		//TASK 2: The fish class needs to implement an interface called CanSwim
		//that gives it the ability to print "swimming" when its swim method is invoked.
		for(int i=0; i<animal.length; i++) {
			if(animal[i] instanceof Fish) {
				System.out.print(animal[i].getName() + " is ");
				((Fish) animal[i]).swim();
			}
		}
	}
}

abstract class Animal {
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

class Cat extends Animal{

	/**
	 * Constructor for Cat
	 */
	public Cat(String name, String colour, int age) {
		super(name, colour, age);
	}
	
	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("miaw");
	}
}

class Dog extends Animal{

	/**
	 * Constructor for Dog
	 */
	public Dog(String name, String colour, int age) {
		super(name, colour, age);
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("woof");
	}

}

class Fish extends Animal implements CanSwim{

	/**
	 * Constructor for Fish
	 */
	public Fish(String name, String colour, int age) {
		super(name, colour, age);
	}

	@Override
	public void makeSound() {
		System.out.println("Just keep swimming!");
	}

	public void swim(){
		System.out.println("swimming");
	}

}

interface CanSwim {
	public void swim();
}

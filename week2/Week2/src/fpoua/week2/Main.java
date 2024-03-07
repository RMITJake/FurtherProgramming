/**
 * 
 */
package fpoua.week2;

/**
 * @author dipto
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Animal animal[] = new Animal[3]; 
		animal[0] = new Cat("Figaro", "black and white", 12);
		animal[1] = new Dog("Pluto", "yellow", 9);
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
		
		
		//TASK 3: Not assessed, do it after you submit TASK 1 and 2.
		//Watch the live coding in the workshop on how to extend the program further.
		//Add eat and sleep methods in the Animal class 
		//so that all inheritance have the same ability to print status whilst eating or sleeping.

	}

}

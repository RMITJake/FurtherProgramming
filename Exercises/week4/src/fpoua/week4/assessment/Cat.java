/**
 * 
 */
package fpoua.week4.assessment;

/**
 * @author dipto
 *
 */
public class Cat extends Animal{

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

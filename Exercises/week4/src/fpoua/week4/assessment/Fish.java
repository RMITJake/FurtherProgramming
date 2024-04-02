/**
 * 
 */
package fpoua.week4.assessment;

/**
 * @author dipto
 *
 */
public class Fish extends Animal implements CanSwim{

	/**
	 * 
	 */
	Fish (String name, String colour, int age) {
		super(name, colour, age);
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

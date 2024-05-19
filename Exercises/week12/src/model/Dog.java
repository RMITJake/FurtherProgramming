package src.model;
import src.daos.DogDaoImpl;

public class Dog extends Animal{
	private DogDaoImpl dogDaoImpl;

	public Dog(){
		super();
		this.dogDaoImpl = new DogDaoImpl();
	}


	public Dog(String name, String colour, int age){
		super(name, colour, age);
	}

	public Dog(int id, String name, String colour, int age){
		super(id, name, colour, age);
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("woof");
	}

	public DogDaoImpl getDogDaoImpl(){
		return this.dogDaoImpl;
	}
}

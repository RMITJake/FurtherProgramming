package src.model;

import src.daos.AnimalDaoImpl;

public class Dog extends Animal{
	private AnimalDaoImpl animalDaoImpl;

	public Dog(){
		super();
		this.animalDaoImpl = new AnimalDaoImpl();
	}

	public Dog(String name, String colour, int age){
		super(name, colour, age);
		this.animalDaoImpl = new AnimalDaoImpl();
	}

	public Dog(int id, String name, String colour, int age){
		super(id, name, colour, age);
		this.animalDaoImpl = new AnimalDaoImpl();
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("woof");
	}

	public AnimalDaoImpl getAnimalDaoImpl(){
        return this.animalDaoImpl;
    }
}

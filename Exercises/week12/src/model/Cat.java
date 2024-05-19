package src.model;

import src.daos.AnimalDaoImpl;

public class Cat extends Animal{
	private AnimalDaoImpl animalDaoImpl;

	public Cat(){
		super();
		this.animalDaoImpl = new AnimalDaoImpl();
	}

	public Cat(String name, String colour, int age) {
		super(name, colour, age);
		this.animalDaoImpl = new AnimalDaoImpl();
	}
	
	public Cat(int id, String name, String colour, int age) {
		super(id, name, colour, age);
		this.animalDaoImpl = new AnimalDaoImpl();
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("miaw");
	}

	public AnimalDaoImpl getAnimalDaoImpl(){
        return this.animalDaoImpl;
    }
}

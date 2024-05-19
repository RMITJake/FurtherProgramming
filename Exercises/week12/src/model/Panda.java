package src.model;

import src.daos.AnimalDaoImpl;
import java.io.Serializable;

/*
 * ASSESSMENT WEEK 6 TASK 2
 * Panda should now implement Serializable
 */
public class Panda extends Animal implements Serializable{
	private AnimalDaoImpl animalDaoImpl;

	public Panda(){
		super();
		this.animalDaoImpl = new AnimalDaoImpl();
	}

	public Panda(String name, String colour, int age){
		super(name, colour, age);
		this.animalDaoImpl = new AnimalDaoImpl();
	}

	public Panda(int id, String name, String colour, int age){
		super(id, name, colour, age);
		this.animalDaoImpl = new AnimalDaoImpl();
	}

	@Override
	public void makeSound(){}

	public AnimalDaoImpl getAnimalDaoImpl(){
        return this.animalDaoImpl;
    }
}

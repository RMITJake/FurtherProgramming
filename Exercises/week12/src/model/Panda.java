package src.model;

import java.io.Serializable;

import src.daos.PandaDaoImpl;

/*
 * ASSESSMENT WEEK 6 TASK 2
 * Panda should now implement Serializable
 */
public class Panda extends Animal implements Serializable{
	private PandaDaoImpl pandaDaoImpl;

	public Panda(){
		super();
		pandaDaoImpl = new PandaDaoImpl();
	}

	public Panda(String name, String colour, int age){
		super(name, colour, age);
		pandaDaoImpl = new PandaDaoImpl();
	}

	public Panda(int id, String name, String colour, int age){
		super(id, name, colour, age);
		pandaDaoImpl = new PandaDaoImpl();
	}

	@Override
	public void makeSound(){}

	public PandaDaoImpl getPandaDaoImpl(){
		return this.pandaDaoImpl;
	}
}

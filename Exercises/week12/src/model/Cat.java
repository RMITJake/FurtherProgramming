package src.model;

import src.daos.CatDaoImpl;

public class Cat extends Animal{
	private CatDaoImpl catDaoImpl;

	public Cat(){
		super();
		this.catDaoImpl = new CatDaoImpl();
	}

	public Cat(String name, String colour, int age) {
		super(name, colour, age);
	}
	
	public Cat(int id, String name, String colour, int age) {
		super(id, name, colour, age);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("miaw");
	}

	public CatDaoImpl getCatDaoImpl(){
		return this.catDaoImpl;
	}
}

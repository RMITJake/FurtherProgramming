package console.models;

public class Venue{
    public String name;
    public int capacity;
    public String suitableFor;
    public String category;

    public Venue(String name, int capacity, String suitableFor, String category){
        this.name = name;
        this.capacity = capacity;
        this.suitableFor = suitableFor;
        this.category = category;
    }

    public String toString(){
        return "> " + name + "\nCapacity: " + capacity + "\nSuitable For: " + suitableFor + "\nCategory: " + category;
    }
}
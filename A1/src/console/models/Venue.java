package console.models;

public class Venue{
    String name;
    int capacity;
    String suitableFor;
    String category;

    public Venue(String name, int capacity, String suitableFor, String category){
        this.name = name;
        this.capacity = capacity;
        this.suitableFor = suitableFor;
        this.category = category;
    }

    public String toString(String input){
        return input + "";
    }
}
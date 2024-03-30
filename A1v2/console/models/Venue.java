package console.models;

public class Venue{
    private String name;
    private int capacity;
    private String suitableFor;
    private String category;
    private int priceperhour;

    public Venue(String name, int capacity, String suitableFor, String category, int priceperhour){
        this.name = name;
        this.capacity = capacity;
        this.suitableFor = suitableFor;
        this.category = category;
        this.priceperhour = priceperhour;
    }

    public String getName(){ return this.name; }
    public int getCapacity(){ return this.capacity; }
    public String getSuitableFor(){ return this.suitableFor; }
    public String getCategory(){ return this.category; }
    public int getPricePerHour(){ return this.priceperhour; }

    public String toString(String input){
        return input + "";
    }
}
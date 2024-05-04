package src.models;

public class Venue{
    private int id;
    private String name;
    private int capacity;
    private String[] suitableFor;
    private String category;
    private int priceperhour;
    private int compatibilityScore;

    public Venue(){}
    public Venue(String name, int capacity, String suitableFor, String category, int priceperhour){
        this.name = name.trim();
        this.capacity = capacity;
        this.suitableFor = suitableFor.trim().split(";");
        this.category = category.trim();
        this.priceperhour = priceperhour;
    }

    public Venue(int id, String name, int capacity, String category, int priceperhour){
        this.id = id;
        this.name = name.trim();
        this.capacity = capacity;
        this.category = category.trim();
        this.priceperhour = priceperhour;
    }

    public int getId(){ return this.id; }
    public String getName(){ return this.name; }
    public int getCapacity(){ return this.capacity; }
    public String[] getSuitableFor(){ return this.suitableFor; }
    public void setSuitableFor(String[] suitableFor){ this.suitableFor = suitableFor; }
    public String getCategory(){ return this.category; }
    public int getPricePerHour(){ return this.priceperhour; }
    public void setCompatibilityScore(int compatibilityScore){ this.compatibilityScore = compatibilityScore; }
    public int getCompatibilityScore(){ return this.compatibilityScore; }
    
    public String getSuitableForString(){
        String string = "";
        for(String str : getSuitableFor()){
            string += str + ",";
        }
        string = string.substring(0, string.length()-1);
        return string;
    }

    public String toString(String input){
        return input + "";
    }
}
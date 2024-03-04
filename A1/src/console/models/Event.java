package console.models;

public class Event{
    public String client;
    String title;
    public String artist;
    String date;
    String time;
    String target;
    public String type;
    public String category;

    public Event(String client, String title, String artist, String date, String time, String target, String type, String category){
        this.client = client;
        this.title = title;
        this.artist = artist;
        this.date = date;
        this.time = time;
        this.target = target;
        this.type = type;
        this.category = category;
    }

    public String toString(String input){
        String output = input + "";
        return output;
    }
}
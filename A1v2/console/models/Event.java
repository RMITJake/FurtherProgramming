package console.models;

public class Event{
    private String client;
    private String title;
    private String artist;
    private String date;
    private String time;
    private String target;
    private String duration;
    private String type;
    private String category;

    public Event(String client, String title, String artist, String date, String time, String target, String duration, String type, String category){
        this.client = client;
        this.title = title;
        this.artist = artist;
        this.date = date;
        this.time = time;
        this.target = target;
        this.duration = duration;
        this.type = type;
        this.category = category;
    }

    public String getClient(){ return this.client; }
    public String getTitle(){ return this.title; }
    public String getArtist(){ return this.artist; }
    public String getDate(){ return this.date; }
    public String getTime(){ return this.time; }
    public String getTarget(){ return this.target; }
    public String getDuration(){ return this.duration; }
    public String getType(){ return this.type; }
    public String getCategory(){ return this.category; }

    public String toString(String input){
        String output = input + "";
        return output;
    }
}
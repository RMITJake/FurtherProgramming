package console.models;

public class Event{
    private String client;
    private String title;
    private String artist;
    private String date;
    private String time;
    private int target;
    private int duration;
    private String type;
    private String category;

    public Event(String client, String title, String artist, String date, String time, int target, int duration, String type, String category){
        this.client = client.trim();
        this.title = title.trim();
        this.artist = artist.trim();
        this.date = date.trim();
        this.time = time.trim();
        this.target = target;
        this.duration = duration;
        this.type = type.trim();
        this.category = category.trim();
    }

    public String getClient(){ return this.client; }
    public String getTitle(){ return this.title; }
    public String getArtist(){ return this.artist; }
    public String getDate(){ return this.date; }
    public String getTime(){ return this.time; }
    public int getTarget(){ return this.target; }
    public int getDuration(){ return this.duration; }
    public String getType(){ return this.type; }
    public String getCategory(){ return this.category; }

    public String toString(String input){
        String output = input + "";
        return output;
    }
}
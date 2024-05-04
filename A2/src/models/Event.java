package src.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event{
    private int id;
    private String client;
    private String title;
    private String artist;
    private LocalDateTime dateTime;
    private int target;
    private int duration;
    private String type;
    private String category;

    public Event(){}
    public Event(String client, String title, String artist, String date, String time, int target, int duration, String type, String category){
        this.client = client.trim();
        this.title = title.trim();
        this.artist = artist.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mma");
        this.dateTime = LocalDateTime.parse(date.trim() + " " + time.trim(), formatter);
        this.target = target;
        this.duration = duration;
        this.type = type.trim();
        this.category = category.trim();
    }

    public Event(int id, String client, String title, String artist, String dateTime, int target, int duration, String type, String category){
        this.id = id;
        this.client = client.trim();
        this.title = title.trim();
        this.artist = artist.trim();
        this.dateTime = LocalDateTime.parse(dateTime);
        this.target = target;
        this.duration = duration;
        this.type = type.trim();
        this.category = category.trim();
    }

    public int getId(){ return this.id; }
    public void setId(int id){ this.id = id; }
    public String getClient(){ return this.client; }
    public void setClient(String client){ this.client = client; }
    public String getTitle(){ return this.title; }
    public void setTitle(String title){ this.title = title; }
    public String getArtist(){ return this.artist; }
    public void setArtist(String artist){ this.artist = artist; }
    public LocalDateTime getDateTime(){ return this.dateTime; }
    public void setDateTime(LocalDateTime dateTime){ this.dateTime = dateTime; }
    public int getTarget(){ return this.target; }
    public void setTarget(int target){ this.target = target; }
    public int getDuration(){ return this.duration; }
    public void setDuration(int duration){ this.duration = duration; }
    public String getType(){ return this.type; }
    public void setType(String type){ this.type = type; }
    public String getCategory(){ return this.category; }
    public void setCategory(String category){ this.category = category; }

    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return this.dateTime.format(formatter);
    }

    public String getTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
        return this.dateTime.format(formatter);
    }

    public LocalDateTime getEventFinish(){
        return this.getDateTime().plusHours(this.getDuration());
    }

    public String toString(String input){
        String output = input + "";
        return output;
    }
}
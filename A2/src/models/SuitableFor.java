package src.models;

import java.io.Serializable;

public class SuitableFor implements Serializable{
    private int id;
    private int venueId;
    private String genre;

    public SuitableFor(int id, int venueId, String genre){
        
    }

    public int getId(){ return this.id; }
    public void setId(int id){ this.id = id; }
    public int getVenueId(){ return this.venueId; }
    public void setVenueId(int id){ this.venueId = id; }
    public String getGenre(){ return this.genre; }
    public void setGenre(String genre){ this.genre = genre; }
}

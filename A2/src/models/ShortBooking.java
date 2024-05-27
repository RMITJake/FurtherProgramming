package src.models;

import java.io.Serializable;

public class ShortBooking implements Serializable{
    int id;
    int eventId;
    int venueId;

    public ShortBooking(int id, int eventId, int venueId){
        this.id = id;
        this.eventId = eventId;
        this.venueId = venueId;
    }

    public int getId(){ return this.id; }
    public int getEventId(){ return this.eventId; }
    public int getVenueId(){ return this.venueId; }
}

package src.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.models.Booking;
import src.models.Event;
import src.models.Venue;

public class BookingDaoImpl implements BookingDao{
    private final String TABLE_NAME = DatabaseHandler.bookingsTable;
    public final String SCHEMA = ""
    +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
    +"eventId, "
    +"venueId, "
    +"FOREIGN KEY(eventId) REFERENCES events(id), "
    +"FOREIGN KEY(venueId) REFERENCES venues(id)";
    
    @Override
    public List<Booking> readBookingsTable() throws SQLException{
        List<Booking> bookingList = new ArrayList<>();

		try (Connection connection = DatabaseHandler.getConnection(); 
            Statement query = connection.createStatement();
        ){ // inside the try block
            ResultSet result = query.executeQuery(""
            +"SELECT * FROM " + TABLE_NAME
            +"INNER JOIN events ON bookings.eventid = events.id"
            +"INNER JOIN venues ON bookings.venueid = venues.id");
            DebugHandler.print(result.getString("name"));

            while(result.next()){
                Venue venue = new Venue(
                result.getInt("venues.id"),
                result.getString("venues.name"),
                result.getInt("venues.capacity"),
                result.getString("venues.category"),
                result.getInt("venues.priceperhour")
                );
                Event event = new Event(
                result.getInt("events.id"),
                result.getString("events.client"),
                result.getString("events.title"),
                result.getString("events.artist"),
                result.getString("events.dateTime"),
                result.getInt("events.target"),
                result.getInt("events.duration"),
                result.getString("events.type"),
                result.getString("events.category")
                );
                Booking booking = new Booking(event, venue);
                bookingList.add(booking);
            }
            return bookingList;
        }
    }

    @Override
    public void createBooking(Event event, Venue venue){
        DebugHandler.print("Creating booking list to db");
        String insertStatement = ""
        +"INSERT OR REPLACE INTO " + TABLE_NAME
        +" VALUES ((SELECT id FROM bookings WHERE eventid = ?), ?, ?)";

		try (Connection connection = DatabaseHandler.getConnection(); 
            PreparedStatement preparedInsert = connection.prepareStatement(insertStatement);
        ){ // inside the try block
            preparedInsert.setInt(1, event.getId());
            preparedInsert.setInt(2, event.getId());
            preparedInsert.setInt(3, venue.getId());
            preparedInsert.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace(System.err);
        } // END of Try-Catch block
    }

    @Override
    public List<Event> getEventsByVenue(String venueName) throws SQLException{
        DebugHandler.print("inside readVenueEvents with: " + venueName);
        List<Event> eventList = new ArrayList<>();

        String query = "SELECT e.id, e.client, e.title, e.artist, e.dateTime, e.target, e.duration, e.type, e.category FROM " + TABLE_NAME
        +" INNER JOIN events as e ON bookings.eventid = e.id "
        +"INNER JOIN venues ON bookings.venueid = venues.id "
        +"WHERE venues.name = ?";

		try (Connection connection = DatabaseHandler.getConnection(); 
            PreparedStatement preparedQuery = connection.prepareStatement(query);
        ){ // inside the try block
            preparedQuery.setString(1, venueName);
            ResultSet result = preparedQuery.executeQuery();

            while(result.next()){
                Event event = new Event(
                result.getInt("id"),
                result.getString("client"),
                result.getString("title"),
                result.getString("artist"),
                result.getString("dateTime"),
                result.getInt("target"),
                result.getInt("duration"),
                result.getString("type"),
                result.getString("category")
                );
                eventList.add(event);
            }
            return eventList;
        }
    }
}

package src.daos;

import java.sql.SQLException;
import java.util.List;

import src.models.Booking;
import src.models.Event;
import src.models.Venue;

public interface BookingDao {
    List<Booking> readBookingsTable() throws SQLException;
    void createBooking(Event event, Venue venue);
    List<Event> getEventsByVenue(String venueName) throws SQLException;
}

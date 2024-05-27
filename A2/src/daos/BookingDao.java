package src.daos;

import java.sql.SQLException;
import java.util.List;

import src.models.Event;
import src.models.Venue;
import src.models.ShortBooking;

public interface BookingDao {
    List<ShortBooking> readBookingsTable() throws SQLException;
    void createBooking(Event event, Venue venue);
    void createBooking(List<ShortBooking> bookingList) throws SQLException;
    List<Event> getEventsByVenue(String venueName) throws SQLException;
}

package src.daos;

import java.sql.SQLException;
import java.util.List;

import src.models.Venue;

public interface VenueDao {
    List<Venue> readVenuesTable() throws SQLException;
    void createVenue(List<Venue> venueList) throws SQLException;
}

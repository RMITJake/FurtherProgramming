package src.daos;

import java.sql.Connection;
import java.sql.SQLException;

import src.models.Venue;

public interface SuitableForDao {
    String[] readSuitableforTable(Venue venue) throws SQLException;
    void createSuitableFor(Connection connection, Venue venue, int id) throws SQLException;
}

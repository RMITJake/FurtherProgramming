package src.daos;

import java.sql.SQLException;

import src.models.Venue;

public interface SuitableForDao {
    String[] readSuitableforTable(Venue venue) throws SQLException;
}

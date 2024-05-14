package src.daos;

import java.sql.SQLException;

import src.models.Venue;

public interface SuitableForDao {
    public String[] readSuitableforTable(Venue venue) throws SQLException;
}

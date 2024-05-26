package src.daos;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import src.models.Venue;
import src.models.SuitableFor;

public interface SuitableForDao {
    List<SuitableFor> readSuitableForTable() throws SQLException;
    String[] readSuitableForTable(Venue venue) throws SQLException;
    void createSuitableFor(Connection connection, Venue venue, int id) throws SQLException;
}

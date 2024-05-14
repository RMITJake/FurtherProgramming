package src.daos;
import java.sql.SQLException;
import java.util.List;
import src.models.Event;

public interface EventDao {
    List<Event> readEventsTable() throws SQLException;
}

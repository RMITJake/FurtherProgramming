package src.daos;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.models.Event;
import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;

public class EventDaoImpl implements EventDao {
	private final String TABLE_NAME = DatabaseHandler.eventsTable;

    @Override
    public List<Event> readEventsTable() throws SQLException{
        List<Event> eventList = new ArrayList<>();
		String query = "SELECT * FROM " + TABLE_NAME;

		try (Connection connection = DatabaseHandler.getConnection(); 
            Statement statement = connection.createStatement();
        ){ // inside the try block
            ResultSet result = statement.executeQuery(query);
            DebugHandler.print(result.getString("client"));

            while(result.next()){
                DebugHandler.print(result.getString("client"));
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
                DebugHandler.print(String.format("Added event: %s %s", event.getId(), event.getClient()));
            }
            return eventList;
        }
    }
}

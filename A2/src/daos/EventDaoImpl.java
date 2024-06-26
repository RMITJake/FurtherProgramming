package src.daos;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.models.Event;
import src.handlers.DatabaseHandler;

public class EventDaoImpl implements EventDao {
	private final String TABLE_NAME = DatabaseHandler.eventsTable;
    public final String SCHEMA = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"client INTEGER, "
        +"title STRING, "
        +"artist INTEGER, "
        +"datetime DATETIME, "
        +"target INTEGER, "
        +"duration INTEGER, "
        +"type STRING, "
        +"category STRING";

    @Override
    public List<Event> readEventsTable() throws SQLException{
        List<Event> eventList = new ArrayList<>();
		String query = "SELECT * FROM " + TABLE_NAME;

		try (Connection connection = DatabaseHandler.getConnection(); 
            Statement statement = connection.createStatement();
        ){ // inside the try block
            ResultSet result = statement.executeQuery(query);

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

    public void createEvent(List<Event> eventList) throws SQLException{
        String insertStatement = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseHandler.getConnection(); 
            PreparedStatement preparedInsert = connection.prepareStatement(insertStatement);
        ){ // inside the try block
            for(Event event : eventList){
                preparedInsert.setString(2, event.getClient());
                preparedInsert.setString(3, event.getTitle().trim());
                preparedInsert.setString(4, event.getArtist());
                preparedInsert.setString(5, event.getDateTime().toString());
                preparedInsert.setInt(6, event.getTarget());
                preparedInsert.setInt(7, event.getDuration());
                preparedInsert.setString(8, event.getType());
                preparedInsert.setString(9, event.getCategory());
                preparedInsert.executeUpdate();
            }
        } 
    } // END of createEvent
}

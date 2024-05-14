package src.daos;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.models.Venue;

public class SuitableForDaoImpl implements SuitableForDao{
    private final String TABLE_NAME = DatabaseHandler.suitableForTable;
    public final String SCHEMA = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"venueId INTEGER, "
        +"genre STRING, "
        +"FOREIGN KEY(venueId) REFERENCES venues(id)";
    
    @Override
    public String[] readSuitableforTable(Venue venue) throws SQLException{
        DebugHandler.print("venue id is " + venue.getId());
        List<String> stringList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE venueid = ?";

		try (Connection connection = DatabaseHandler.getConnection(); 
            PreparedStatement preparedQuery = connection.prepareStatement(query);
        ){ // inside the try block
            preparedQuery.setInt(1, venue.getId());
            ResultSet result = preparedQuery.executeQuery();
            DebugHandler.print(result.getString("genre"));

            while(result.next()){
                stringList.add(result.getString("genre"));
            }

            return stringList.toArray(new String[0]);
        }
    }
}

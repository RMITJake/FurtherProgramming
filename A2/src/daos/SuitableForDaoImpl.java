package src.daos;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.models.SuitableFor;
import src.models.Venue;

public class SuitableForDaoImpl implements SuitableForDao{
    private final String TABLE_NAME = DatabaseHandler.suitableForTable;
    public final String SCHEMA = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"venueId INTEGER, "
        +"genre STRING, "
        +"FOREIGN KEY(venueId) REFERENCES venues(id)";

    @Override
    public List<SuitableFor> readSuitableForTable() throws SQLException{
        List<SuitableFor> suitableForList = new ArrayList<SuitableFor>();
		String query = "SELECT * FROM " + TABLE_NAME;

		try (Connection connection = DatabaseHandler.getConnection(); 
            Statement statement = connection.createStatement();
        ){ // inside the try block
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                SuitableFor suitableFor = new SuitableFor(
                result.getInt("id"),
                result.getInt("venueId"),
                result.getString("genre")
                );
                suitableForList.add(suitableFor);
            }
            return suitableForList;
        }
    }
    
    @Override
    public String[] readSuitableForTable(Venue venue) throws SQLException{
        List<String> stringList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE venueid = ?";

		try (Connection connection = DatabaseHandler.getConnection(); 
            PreparedStatement preparedQuery = connection.prepareStatement(query);
        ){ // inside the try block
            preparedQuery.setInt(1, venue.getId());
            ResultSet result = preparedQuery.executeQuery();

            while(result.next()){
                stringList.add(result.getString("genre"));
            }

            return stringList.toArray(new String[0]);
        }
    }

    @Override
    public void createSuitableFor(Connection connection, Venue venue, int id) throws SQLException{
        String insertStatement = ""
        +"INSERT INTO " + TABLE_NAME
        +" VALUES (?, ?, ?)";
        try(
            PreparedStatement preparedInsert = connection.prepareStatement(insertStatement)
            ){ // inside the try block
                for(String genre : venue.getSuitableFor()){
                    preparedInsert.setInt(2, id);
                    preparedInsert.setString(3, genre.trim());
                    preparedInsert.executeUpdate();
                }
        }
    }
}

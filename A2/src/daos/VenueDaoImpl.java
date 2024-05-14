package src.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.models.Venue;

public class VenueDaoImpl implements VenueDao {
    private final String TABLE_NAME = DatabaseHandler.venuesTable;
    public final String SCHEMA = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"name STRING, "
        +"capacity INTEGER, "
        +"category STRING, "
        +"priceperhour DOUBLE";
    private SuitableForDao suitableForDao;

    public VenueDaoImpl(){
        this.suitableForDao = new SuitableForDaoImpl();
    }
    
    @Override
    public List<Venue> readVenuesTable() throws SQLException{
        List<Venue> venueList = new ArrayList<>();
		String query = "SELECT * FROM " + TABLE_NAME;

		try (Connection connection = DatabaseHandler.getConnection(); 
            Statement statement = connection.createStatement();
        ){ // inside the try block
            ResultSet result = statement.executeQuery(query);
            DebugHandler.print("read venues table " + result.getString("name"));

            while(result.next()){
                Venue venue = new Venue(
                result.getInt("id"),
                result.getString("name"),
                result.getInt("capacity"),
                result.getString("category"),
                result.getInt("priceperhour")
                );
                DebugHandler.print("before suitable");
                venue.setSuitableFor(this.suitableForDao.readSuitableforTable(venue));
                DebugHandler.print("after suitable");
                for(String s : venue.getSuitableFor()){ DebugHandler.print(s); }
                venueList.add(venue);
            }
            return venueList;
        }
    }
}

package src.daos;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.handlers.DatabaseHandler;
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

            while(result.next()){
                Venue venue = new Venue(
                result.getInt("id"),
                result.getString("name"),
                result.getInt("capacity"),
                result.getString("category"),
                result.getInt("priceperhour")
                );
                venue.setSuitableFor(this.suitableForDao.readSuitableForTable(venue));
                venueList.add(venue);
            }
            return venueList;
        }
    }

    @Override
    public void createVenue(List<Venue> venueList) throws SQLException{
        String insertStatement = ""
        +"INSERT INTO " + TABLE_NAME
        +" VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DatabaseHandler.getConnection(); 
            PreparedStatement preparedInsert = connection.prepareStatement(insertStatement);
        ){ // inside the try block
            int venueId = 0;
            for(Venue venue : venueList){
                venueId++;
                preparedInsert.setString(2, venue.getName());
                preparedInsert.setInt(3, venue.getCapacity());
                preparedInsert.setString(4, venue.getCategory());
                preparedInsert.setDouble(5, venue.getPricePerHour());
                preparedInsert.executeUpdate();
                this.suitableForDao.createSuitableFor(connection, venue, venueId);
            }
        } // END of Try-Catch block
    } // END of writeVenue
}

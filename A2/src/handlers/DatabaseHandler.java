package src.handlers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import src.models.*;
import src.daos.BookingDaoImpl;
import src.daos.EventDaoImpl;
import src.daos.SuitableForDaoImpl;
import src.daos.UserDaoImpl;
import src.daos.VenueDaoImpl;

public class DatabaseHandler {
    // Variable to allow different databases to be accessed
    private static String database;
    private static String connectionString;
    public static final String eventsTable = "events";
    public static final String usersTable = "users";
    public static final String venuesTable = "venues";
    public static final String suitableForTable = "suitablefor";
    public static final String bookingsTable = "bookings";
    private static EventDaoImpl eventDao = new EventDaoImpl();
    private static VenueDaoImpl venueDao = new VenueDaoImpl();
    private static SuitableForDaoImpl suitableForDao = new SuitableForDaoImpl();
    private static UserDaoImpl userDao = new UserDaoImpl();
    private static BookingDaoImpl bookingDao = new BookingDaoImpl();

    // Variable which allows for a test database to be setup
    public static final String testdb = "testdb";

    public static void initializeDb(String db){
        database = db;
        connectionString = String.format("jdbc:sqlite:%s.db", database);
        createTable(eventsTable, eventDao.SCHEMA);
        createTable(venuesTable, venueDao.SCHEMA);
        createTable(suitableForTable, suitableForDao.SCHEMA);
        createTable(usersTable, userDao.SCHEMA);
        createTable(bookingsTable, bookingDao.SCHEMA);
    }

///////////////////////////
// Seed Database Methods //
///////////////////////////
    public static void seedDb(List<Venue> venueList, List<Event> eventList){
        writeVenue(venueList);
        // writeEvent(eventList);
        try{
            eventDao.createEvent(eventList);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
// END Seed Database Methods //

//////////////////////////
// Create Table Methods //
//////////////////////////
    // Create Table
    public static void createTable(String table, String schema){
        DebugHandler.print("Creating Tables");
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
        ){ // inside the try block
            statement.setQueryTimeout(30);
            statement.executeUpdate(String.format("DROP TABLE IF EXISTS %s", table));
            statement.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s (%s)", table, schema));
        } catch(SQLException ex){
            ex.printStackTrace(System.err);
        } // END of Try-Catch block
    }
// END Create Table Methods

/////////////////////
// Generic Methods //
/////////////////////
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(connectionString);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return connection;
    }
// END  Generic Methods //
    
/////////////////////////
// Write to DB Methods //
/////////////////////////
    

    public static void writeVenue(List<Venue> venueList){
        DebugHandler.print("Creating venu list to db");
        String insertStatement = "INSERT INTO venues VALUES (?, ?, ?, ?, ?)";
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement preparedInsert = connection.prepareStatement(insertStatement);
        ){ // inside the try block
            int rowsAffected = 0;
            int venueId = 0;
            for(Venue venue : venueList){
                venueId++;
                preparedInsert.setString(2, venue.getName());
                preparedInsert.setInt(3, venue.getCapacity());
                preparedInsert.setString(4, venue.getCategory());
                preparedInsert.setDouble(5, venue.getPricePerHour());
                int row = preparedInsert.executeUpdate();
                rowsAffected += row;
                writeSuitableFor(connection, venue, venueId);
            }
            DebugHandler.print(String.format("%s rows affected", rowsAffected));
        } catch(SQLException ex){
            ex.printStackTrace(System.err);
        } // END of Try-Catch block
    } // END of writeVenue

    public static void writeSuitableFor(Connection connection, Venue venue, int id){
        String insertStatement = "INSERT INTO suitablefor VALUES (?, ?, ?)";
        try(
            PreparedStatement preparedInsert = connection.prepareStatement(insertStatement)
            ){ // inside the try block
                for(String genre : venue.getSuitableFor()){
                    preparedInsert.setInt(2, id);
                    preparedInsert.setString(3, genre.trim());
                    preparedInsert.executeUpdate();
                }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    } // END of writeSuitableFor
// END Write to DB Methods

//////////////////
// Read from DB //
//////////////////

    public static List<Event> readVenueEvents(String venueName){
        DebugHandler.print("inside readVenueEvents with: " + venueName);
        List<Event> eventList = new ArrayList<>();

        String query = "SELECT e.id, e.client, e.title, e.artist, e.dateTime, e.target, e.duration, e.type, e.category FROM bookings "
            +"INNER JOIN events as e ON bookings.eventid = e.id "
            +"INNER JOIN venues ON bookings.venueid = venues.id "
            +"WHERE venues.name = ?";
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement preparedQuery = connection.prepareStatement(query);
        ){ // inside the try block
            preparedQuery.setString(1, venueName);
            ResultSet result = preparedQuery.executeQuery();

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
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
// END Read from DB
}

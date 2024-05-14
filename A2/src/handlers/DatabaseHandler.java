package src.handlers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
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

    public static void seedDb(List<Venue> venueList, List<Event> eventList){
        try{
            eventDao.createEvent(eventList);
            venueDao.createVenue(venueList);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

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

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(connectionString);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return connection;
    }
}

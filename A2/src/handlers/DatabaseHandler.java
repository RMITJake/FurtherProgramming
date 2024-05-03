package src.handlers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import src.models.Venue;

public class DatabaseHandler {
    // Variable to allow different databases to be accessed
    private static String database;
    private static String connectionString;

    // Variable which allows for a test database to be setup
    public static final String testdb = "testdb";

    public static void initializeDb(String db){
        database = db;
        connectionString = String.format("jdbc:sqlite:%s.db", database);
        createClientsTable();
        createArtistsTable();
        createEventsTable();
        createVenuesTable();
        createSuitableForTable();
        createBookingsTable();
    }

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
            statement.executeUpdate(String.format("CREATE TABLE %s (%s)", table, schema));
        } catch(SQLException ex){
            ex.printStackTrace(System.err);
        } // END of Try-Catch block
    }

    // Clients
    public static void createClientsTable(){
        String schema = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"name STRING";
        createTable("clients", schema);
    }

    // Artists
    public static void createArtistsTable(){
        String schema = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"name STRING";
        createTable("artists", schema);
    }

    // Events
    public static void createEventsTable(){
        String schema = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"client INTEGER, "
        +"title STRING, "
        +"artist INTEGER, "
        +"date DATETIME, "
        +"target INTEGER, "
        +"duration INTEGER, "
        +"type STRING, "
        +"category STRING, "
        +"FOREIGN KEY(client) REFERENCES clients(id)"
        +"FOREIGN KEY(artist) REFERENCES artists(id)";
        createTable("events", schema);
    }

    // Venues
    public static void createVenuesTable(){
        String schema = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"name STRING, "
        +"capacity INTEGER, "
        +"category STRING, "
        +"priceperhour DOUBLE";
        createTable("venues", schema);
    }

    // SuitableFor
    public static void createSuitableForTable(){
        String schema = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"venueId INTEGER, "
        +"genre STRING, "
        +"FOREIGN KEY(venueId) REFERENCES venues(id)";
        createTable("suitablefor", schema);
    }

    // Bookings
    public static void createBookingsTable(){
        String schema = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"eventId, "
        +"venueId, "
        +"FOREIGN KEY(eventId) REFERENCES events(id), "
        +"FOREIGN KEY(venueId) REFERENCES venues(id)";
        createTable("bookings", schema);
    }

    // Users
    public static void createUsersTable(){
        String schema = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"username STRING, "
        +"password STRING, "
        +"accountType STRING, "
        +"accountEnabled BOOLEAN";
        createTable("users", schema);
    }
// END Create Table Methods
    
/////////////////////////
// Write to DB Methods //
/////////////////////////
    public static void writeClient(List<String> clientList){
        DebugHandler.print("Creating client list to db");
        String insertStatement = "INSERT INTO clients VALUES (?, ?)";
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement prepartedStatement = connection.prepareStatement(insertStatement);
        ){ // inside the try block
            int rowsAffected = 0;
            for(String client : clientList){
                prepartedStatement.setString(2, client);
                int row = prepartedStatement.executeUpdate();
                rowsAffected += row;
            }
            DebugHandler.print(String.format("%s rows affected", rowsAffected));
        } catch(SQLException ex){
            ex.printStackTrace(System.err);
        } // END of Try-Catch block
    } // END of writeClient

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
    }
// END Write to DB Methods
}

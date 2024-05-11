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

public class DatabaseHandler {
    // Variable to allow different databases to be accessed
    private static String database;
    private static String connectionString;

    // Variable which allows for a test database to be setup
    public static final String testdb = "testdb";

    public static void initializeDb(String db){
        database = db;
        connectionString = String.format("jdbc:sqlite:%s.db", database);
        createEventsTable();
        createVenuesTable();
        createSuitableForTable();
        createBookingsTable();
        createUsersTable();
    }

///////////////////////////
// Seed Database Methods //
///////////////////////////
    public static void seedDb(List<Venue> venueList, List<Event> eventList){
        writeVenue(venueList);
        writeEvent(eventList);
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

    // Events
    public static void createEventsTable(){
        String schema = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"client INTEGER, "
        +"title STRING, "
        +"artist INTEGER, "
        +"datetime DATETIME, "
        +"target INTEGER, "
        +"duration INTEGER, "
        +"type STRING, "
        +"category STRING";
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
    public static void writeEvent(List<Event> eventList){
        DebugHandler.print("Adding event list to db");
        String insertStatement = "INSERT INTO events VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement preparedInsert = connection.prepareStatement(insertStatement);
        ){ // inside the try block
            int rowsAffected = 0;
            for(Event event : eventList){
                preparedInsert.setString(2, event.getClient());
                preparedInsert.setString(3, event.getTitle().trim());
                preparedInsert.setString(4, event.getArtist());
                preparedInsert.setString(5, event.getDateTime().toString());
                preparedInsert.setInt(6, event.getTarget());
                preparedInsert.setInt(7, event.getDuration());
                preparedInsert.setString(8, event.getType());
                preparedInsert.setString(9, event.getCategory());
                int row = preparedInsert.executeUpdate();
                rowsAffected += row;
            }
            DebugHandler.print(String.format("%s rows affected", rowsAffected));
        } catch(SQLException ex){
            ex.printStackTrace(System.err);
        } // END of Try-Catch block
    } // END of writeEvent

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

    public static void writeBooking(Event event, Venue venue){
        DebugHandler.print("Creating booking list to db");
        String insertStatement = "INSERT OR REPLACE INTO bookings VALUES ((SELECT id FROM bookings WHERE eventid = ?), ?, ?)";
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement preparedInsert = connection.prepareStatement(insertStatement);
        ){ // inside the try block
            preparedInsert.setInt(1, event.getId());
            preparedInsert.setInt(2, event.getId());
            preparedInsert.setInt(3, venue.getId());
            preparedInsert.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace(System.err);
        } // END of Try-Catch block
    }

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
    public static List<Event> readEventsTable(){
        List<Event> eventList = new ArrayList<>();
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            Statement query = connection.createStatement();
        ){ // inside the try block
            ResultSet result = query.executeQuery("SELECT * FROM events");
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
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Venue> readVenuesTable(){
        List<Venue> venueList = new ArrayList<>();
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            Statement query = connection.createStatement();
        ){ // inside the try block
            ResultSet result = query.executeQuery("SELECT * FROM venues");
            DebugHandler.print(result.getString("name"));

            while(result.next()){
                Venue venue = new Venue(
                result.getInt("id"),
                result.getString("name"),
                result.getInt("capacity"),
                result.getString("category"),
                result.getInt("priceperhour")
                );
                venue.setSuitableFor(readSuitableforTable(venue));
                for(String s : venue.getSuitableFor()){ DebugHandler.print(s); }
                venueList.add(venue);
            }

            return venueList;
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static String[] readSuitableforTable(Venue venue){
        List<String> stringList = new ArrayList<>();
        String query = "SELECT * FROM suitablefor WHERE venueid = ?";
        try(
            Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement preparedQuery = connection.prepareStatement(query);
        ){ // inside the try block
            preparedQuery.setInt(1, venue.getId());
            ResultSet result = preparedQuery.executeQuery();
            DebugHandler.print(result.getString("genre"));

            while(result.next()){
                stringList.add(result.getString("genre"));
            }

            return stringList.toArray(new String[0]);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Booking> readBookingsTable(){
        List<Booking> bookingList = new ArrayList<>();

        try(
            Connection connection = DriverManager.getConnection(connectionString);
            Statement query = connection.createStatement();
        ){ // inside the try block
            ResultSet result = query.executeQuery(""+
            "SELECT * FROM bookings"
            +"INNER JOIN events ON bookings.eventid = events.id"
            +"INNER JOIN venues ON bookings.venueid = venues.id");
            DebugHandler.print(result.getString("name"));

            while(result.next()){
                Venue venue = new Venue(
                result.getInt("venues.id"),
                result.getString("venues.name"),
                result.getInt("venues.capacity"),
                result.getString("venues.category"),
                result.getInt("venues.priceperhour")
                );
                Event event = new Event(
                result.getInt("events.id"),
                result.getString("events.client"),
                result.getString("events.title"),
                result.getString("events.artist"),
                result.getString("events.dateTime"),
                result.getInt("events.target"),
                result.getInt("events.duration"),
                result.getString("events.type"),
                result.getString("events.category")
                );
                Booking booking = new Booking(event, venue);
                bookingList.add(booking);
            }

            return bookingList;
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

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

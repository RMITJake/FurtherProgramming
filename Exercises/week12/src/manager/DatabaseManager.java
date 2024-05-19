package src.manager;

// Import local libs
import src.Debugger;

// Import Java libs
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	private static String connectionString;
	private static String database;

	public static void initializeDb(String db){
        Debugger.PRINT("Initializing DB");
		database = db;
		connectionString = String.format("jdbc:sqlite:%s.db", database);
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(connectionString);
	}

	public static void createTable(String table, String schema){
        Debugger.PRINT("Creating Tables");
        try(
            Statement statement = getConnection().createStatement();
        ){ // inside the try block
            statement.setQueryTimeout(30);
            statement.executeUpdate(String.format("DROP TABLE IF EXISTS %s", table));
            statement.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s (%s)", table, schema));
        } catch(SQLException ex){
            ex.printStackTrace(System.err);
        } // END of Try-Catch block
	}
}
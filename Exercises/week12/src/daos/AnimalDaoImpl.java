package src.daos;

import src.manager.DatabaseManager;
import src.model.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;

public class AnimalDaoImpl<T extends Animal> implements AnimalDao {
    private String TABLE_NAME;
    public static final String SCHEMA = ""
    +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
    +"name STRING, "
    +"colour STRING, "
    +"age INTEGER";

    public void setTableName(String tableName){
        this.TABLE_NAME = tableName;
    }

    public void createTable() throws SQLException{
        try(Statement statement = DatabaseManager.getConnection().createStatement();
        ){
        // try(Statement statement = DatabaseManager.getConnection().createStatement()){
            statement.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            statement.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s (%s)", TABLE_NAME, SCHEMA));
        }
    }

    public void createAnimal(String name, String colour, int age) throws SQLException{
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)){
            statement.setString(2, name);
            statement.setString(3, colour);
            statement.setInt(4, age);
            statement.executeUpdate();
        }
    }

    public void createAnimal(int id, String name, String colour, int age) throws SQLException{
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)){
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, colour);
            statement.setInt(4, age);
            statement.executeUpdate();
        }
    }

    public void createAnimal(Animal animal) throws SQLException{
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)){
            statement.setInt(1, animal.getID());
            statement.setString(2, animal.getName());
            statement.setString(3, animal.getColour());
            statement.setInt(4, animal.getAge());
            statement.executeUpdate();
        }
    }

    public List<T> readAnimalTable() throws SQLException{
        return null;
    }

    public T getAnimal() throws SQLException{
        return null;
    }

    public void updateAnimal(Animal animal) throws SQLException{
        String query = "INSERT OR REPLACE INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)){
            statement.setInt(1, animal.getID());
            statement.setString(2, animal.getName());
            statement.setString(3, animal.getColour());
            statement.setInt(4, animal.getAge());
            statement.executeUpdate();
        }
    }

    public void deleteAnimal(Animal animal) throws SQLException{
        String query = "DELETE FROM " + TABLE_NAME +" WHERE id = (?)";
		try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
			statement.setInt(1, animal.getID());
			statement.executeUpdate();
		}
    }
}

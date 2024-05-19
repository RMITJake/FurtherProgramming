package src.daos;

import src.manager.DatabaseManager;
import src.model.Animal;
import src.model.GenericAnimalList;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;

public class AnimalDaoImpl implements AnimalDao {
    private String TABLE_NAME = "pets";
    public static final String SCHEMA = ""
    +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
    +"name STRING, "
    +"colour STRING, "
    +"age INTEGER";

    public void createTable() throws SQLException{
        try(Statement statement = DatabaseManager.getConnection().createStatement();
        ){
        // try(Statement statement = DatabaseManager.getConnection().createStatement()){
            statement.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            statement.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s (%s)", TABLE_NAME, SCHEMA));
        }
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

    public void writeAnimalList(GenericAnimalList<Animal> animalList) throws SQLException{
        String query = "insert into pets values (?, ?, ?, ?)";
		try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            for(Animal animal : animalList.getList()){
                statement.setString(2, animal.getName());
                statement.setString(3, animal.getColour());
                statement.setInt(4, animal.getAge());
                statement.executeUpdate();
            }
        }
    }

    public AnimalDaoImpl getAnimalDaoImpl(){
        return this;
    }
}

package src.daos;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.manager.DatabaseManager;
import src.model.Dog;

public class DogDaoImpl extends AnimalDaoImpl{
    private String TABLE_NAME = "dogs";

    public DogDaoImpl(){
        setTableName(TABLE_NAME);
    }

    @Override
    public List<Dog> readAnimalTable() throws SQLException{
        String query = "SELECT * FROM " + TABLE_NAME;
        List<Dog> dogList = new ArrayList<Dog>();
        try(Statement statement = DatabaseManager.getConnection().createStatement()){
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Dog dog = new Dog(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("colour"),
                    result.getInt("age")
                );
                dogList.add(dog);
            }
        }
        return dogList;
    }

    public Dog getAnimal() throws SQLException{
        return null;
    }
}

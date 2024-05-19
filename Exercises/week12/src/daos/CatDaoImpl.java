package src.daos;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.manager.DatabaseManager;
import src.model.Cat;

public class CatDaoImpl extends AnimalDaoImpl{
    private String TABLE_NAME = "cats";

    public CatDaoImpl(){
        setTableName(TABLE_NAME);
    }

    @Override
    public List<Cat> readAnimalTable() throws SQLException{
        String query = "SELECT * FROM " + TABLE_NAME;
        List<Cat> catList = new ArrayList<Cat>();
        try(Statement statement = DatabaseManager.getConnection().createStatement()){
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Cat cat = new Cat(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("colour"),
                    result.getInt("age")
                );
                catList.add(cat);
            }
        }
        return catList;
    }

    public Cat getAnimal() throws SQLException{
        return null;
    }
}

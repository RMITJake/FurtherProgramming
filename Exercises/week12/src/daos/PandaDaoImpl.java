package src.daos;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.manager.DatabaseManager;
import src.model.Panda;

public class PandaDaoImpl extends AnimalDaoImpl{
    private String TABLE_NAME = "pandas";

    public PandaDaoImpl(){
        setTableName(TABLE_NAME);
    }

    @Override
    public List<Panda> readAnimalTable() throws SQLException{
        String query = "SELECT * FROM " + TABLE_NAME;
        List<Panda> pandaList = new ArrayList<Panda>();
        try(Statement statement = DatabaseManager.getConnection().createStatement()){
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Panda panda = new Panda(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("colour"),
                    result.getInt("age")
                );
                pandaList.add(panda);
            }
        }
        return pandaList;
    }

    public Panda getAnimal() throws SQLException{
        return null;
    }
}

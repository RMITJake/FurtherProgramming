package src.daos;

import java.sql.SQLException;
import java.util.List;

import src.model.Animal;
import src.model.GenericAnimalList;

public interface AnimalDao {
    void createTable() throws SQLException;
    void updateAnimal(Animal animal) throws SQLException;
    void writeAnimalList(GenericAnimalList<Animal> animalList) throws SQLException;
}

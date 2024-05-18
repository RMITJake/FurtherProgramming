package src.daos;

import java.sql.SQLException;
import java.util.List;

import src.model.Animal;

public interface AnimalDao {
    void createAnimal(String name, String colour, int age) throws SQLException;
    void createAnimal(Animal animal) throws SQLException;
    // List<T> readAnimalTable() throws SQLException;
    void updateAnimal(Animal animal) throws SQLException;
    void deleteAnimal(Animal animal) throws SQLException;
}

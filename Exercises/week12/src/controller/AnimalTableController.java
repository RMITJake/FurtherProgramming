package src.controller;

// import local libs
import src.Debugger;
import src.model.Animal;
import src.model.Cat;
import src.model.Dog;
import src.model.Fish;
import src.model.Panda;
import src.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
// Import JavaFX libs
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AnimalTableController {
    private Stage stage;
    private DatabaseManager dbManager;

    // FXML variables
    @FXML private TableView<Animal> animalTable;
    @FXML private TableColumn<Animal, String> nameColumn;
    @FXML private TableColumn<Animal, String> colourColumn;
    @FXML private TableColumn<Animal, Integer> ageColumn;

    public AnimalTableController(Stage primaryStage){
        Debugger.PRINT("in AnimalTableController");
        this.stage = primaryStage;
        this.dbManager = new DatabaseManager();
    }

    public void showStage(Pane root){
        Scene scene = new Scene(root, 440, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("My Pets");
        stage.show();
    }

    @FXML
    private void initialize(){
        DatabaseManager.initializeDb("test");
        Cat cat = new Cat();
        Dog dog = new Dog();
        Panda panda = new Panda();
        try{
            panda.getPandaDaoImpl().createTable();
            panda.getPandaDaoImpl().createAnimal(1, "Puma", "black white blue", 7);
            panda.getPandaDaoImpl().createAnimal(2, "Kioma", "black grey orange", 5);
            panda.getPandaDaoImpl().createAnimal(3, "Simba", "white grey blue", 9);

            cat.getCatDaoImpl().createTable();
            cat.getCatDaoImpl().createAnimal(1, "Hello Kitty", "white", 15);
            cat.getCatDaoImpl().createAnimal(2, "Garfield", "orange", 20);
            cat.getCatDaoImpl().createAnimal(3, "Grumpy cat", "brown and white", 7);

            dog.getDogDaoImpl().createTable();
            dog.getDogDaoImpl().createAnimal("Goofy", "yellow", 99);

        } catch(SQLException ex){
            ex.printStackTrace();
        }
        loadAnimalTable();
    }

    private void loadAnimalTable(){
        // Generate list
        List<Panda> pandas = new ArrayList<Panda>();
		pandas.add(new Panda(1, "Puma", "black white blue", 7));
		pandas.add(new Panda(2, "Kioma", "black grey orange", 5));
		pandas.add(new Panda(3, "Simba", "white grey blue", 9));

        // Setup Table
        nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        colourColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("colour"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("age"));

        // Add list to table
        animalTable.setItems(FXCollections.observableArrayList(pandas));
    }
}

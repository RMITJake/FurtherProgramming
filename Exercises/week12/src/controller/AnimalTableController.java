package src.controller;

// import local libs
import src.Debugger;
import src.model.Animal;
import src.model.Panda;
import src.manager.DatabaseManager;

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

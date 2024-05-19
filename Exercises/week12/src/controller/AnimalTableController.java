package src.controller;

// import local libs
import src.daos.AnimalDaoImpl;
import src.model.Animal;
import src.model.Cat;
import src.model.Dog;
import src.model.GenericAnimalList;
import src.model.Panda;
import src.manager.DatabaseManager;

import java.sql.SQLException;

import javax.swing.Action;

import org.sqlite.core.DB;

// Import JavaFX libs
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AnimalTableController {
	private String ANIMAL_DETAILS = "/src/view/AnimalDetailsView.fxml";
    private Stage stage;
    private GenericAnimalList<Animal> animalList;
	private Animal selectedItem;
    private AnimalDaoImpl animalDaoImpl = new AnimalDaoImpl();

    // FXML variables
    @FXML private TableView<Animal> animalTable;
    @FXML private TableColumn<Animal, String> nameColumn;
    @FXML private TableColumn<Animal, String> colourColumn;
    @FXML private TableColumn<Animal, Integer> ageColumn;
    @FXML private TextArea nameInput;
    @FXML private TextArea colourInput;
    @FXML private TextArea ageInput;
    @FXML private Button addToList;
    @FXML private Button removeFromList;
    @FXML private Button saveToDb;

    public AnimalTableController(Stage primaryStage, GenericAnimalList<Animal> animalList){
        this.stage = primaryStage;
        this.animalList = animalList;
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
        DatabaseManager.initializeDb("mypets");
        Cat cat = new Cat();
        Dog dog = new Dog();
        Panda panda = new Panda();
        try{
            panda.getAnimalDaoImpl().createTable();
            cat.getAnimalDaoImpl().createTable();
            dog.getAnimalDaoImpl().createTable();

        } catch(SQLException ex){
            ex.printStackTrace();
        }
        
        loadAnimalTable(animalList);
    }

    private void loadAnimalTable(GenericAnimalList<Animal> animalList){
        // Setup Table
        nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        colourColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("colour"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("age"));

        // Add list to table
        animalTable.setItems(FXCollections.observableArrayList(animalList.getList()));
    }

    @FXML private void addToList(ActionEvent actionEvent){
        String name = nameInput.getText();
        String colour = colourInput.getText();
        int age = Integer.parseInt(ageInput.getText());
        Cat aCat = new Cat(name, colour, age);
        animalList.addAnAnimal(aCat);
        loadAnimalTable(animalList);
    }

    @FXML private void removeFromList(ActionEvent actionEvent){
		TableViewSelectionModel<Animal> selectionModel = animalTable.getSelectionModel();
		Animal selectedItem = selectionModel.getSelectedItem();
        animalList.getList().remove(selectedItem);
        animalTable.getItems().clear();
        loadAnimalTable(animalList);
    }

    @FXML private void saveToDb(ActionEvent actionEvent) throws SQLException{
        animalDaoImpl.writeAnimalList(animalList);
    }
}

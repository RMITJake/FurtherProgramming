package src.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.model.Animal;
import src.model.GenericAnimalList;

public class AnimalDetailsController {
	private String ANIMAL_DETAILS = "/src/view/AnimalDetailsView.fxml";
    private Stage stage;
    private GenericAnimalList<Animal> animalList;
	private Animal selectedAnimal;

    public AnimalDetailsController(Stage primaryStage, GenericAnimalList<Animal> animalList, Animal selectedAnimal){
        this.stage = primaryStage;
        this.animalList = animalList;
        this.selectedAnimal = selectedAnimal;
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
    }
}

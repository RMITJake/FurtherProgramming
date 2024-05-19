package src;

// Import local libs
import src.controller.*;
import src.model.Animal;
import src.model.Cat;
import src.model.Dog;
import src.model.GenericAnimalList;
import src.model.Panda;

// Import Java std libs
import java.io.IOException;

// Import JavaFX libs
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {
	private String ANIMAL_VIEW = "/src/view/AnimalTableView.fxml";

	public void start(Stage primaryStage){
		GenericAnimalList<Animal> animalList = new GenericAnimalList<Animal>();
		animalList.addAnAnimal(new Panda(1, "Puma", "black white blue", 7));
		animalList.addAnAnimal(new Panda(2, "Kioma", "black grey orange", 5));
		animalList.addAnAnimal(new Panda(3, "Simba", "white grey blue", 9));
        animalList.addAnAnimal(new Cat(1, "Hello Kitty", "white", 15));
        animalList.addAnAnimal(new Cat(2, "Garfield", "orange", 20));
        animalList.addAnAnimal(new Cat(3, "Grumpy cat", "brown and white", 7));
        animalList.addAnAnimal(new Dog("Goofy", "yellow", 99));
		
		try{ FXMLLoader loader = new FXMLLoader(getClass().getResource(ANIMAL_VIEW));
			Callback<Class<?>, Object> controllerFactory = param -> {
				return new AnimalTableController(primaryStage, animalList);
			};

			loader.setControllerFactory(controllerFactory);

			AnchorPane root = loader.load();

			AnimalTableController animalTableController = loader.getController();
			animalTableController.showStage(root);
		} catch(IOException | RuntimeException ex){
			ex.printStackTrace();
			Scene scene = new Scene(new Label(ex.getMessage()), 400, 400);
			primaryStage.setTitle("Error");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
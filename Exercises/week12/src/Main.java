package src;

// Import local libs
import src.controller.*;

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
		try{ FXMLLoader loader = new FXMLLoader(getClass().getResource(ANIMAL_VIEW));
			Callback<Class<?>, Object> controllerFactory = param -> {
				return new AnimalTableController(primaryStage);
			};

			loader.setControllerFactory(controllerFactory);

			AnchorPane root = loader.load();

			AnimalTableController animalTableController = loader.getController();
			animalTableController.showStage(root);
		} catch(IOException | RuntimeException ex){
			ex.printStackTrace();
			Scene scene = new Scene(new Label(ex.getMessage()), 200, 100);
			primaryStage.setTitle("Error");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
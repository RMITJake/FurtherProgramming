package src;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import src.handlers.DatabaseHandler;
import src.handlers.VenueHandler;
import src.models.Venue;
import src.models.Event;
import src.models.Login;
import src.handlers.RequestHandler;
import src.controllers.LoginController;

public class App extends Application {
	private Login login;

	private void initialize(){
		VenueHandler venueHandler = new VenueHandler();
		RequestHandler requestHandler = new RequestHandler();
		List<Venue> venues = venueHandler.retrieveVenuesFromCSV();
		List<Event> events = requestHandler.retrieveRequestsFromCSV();
		DatabaseHandler.seedDb(venues, events);
	}

	@Override
	public void init() {
		login = new Login();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/views/LoginView.fxml"));
			
			// Customize controller instance
			Callback<Class<?>, Object> controllerFactory = param -> {
				return new LoginController(primaryStage, login);
			};

			loader.setControllerFactory(controllerFactory);

			GridPane root = loader.load();

			LoginController loginController = loader.getController();
			loginController.showStage(root);
		} catch (IOException | RuntimeException e) {
			e.printStackTrace();
			Scene scene = new Scene(new Label(e.getMessage()), 200, 100);
			primaryStage.setTitle("Error");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	public static void main(String[] args) {
		DatabaseHandler.initializeDb("livemusicvenuematchmaker");
		launch(args);
	}
}

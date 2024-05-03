package src;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
// import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.util.List;
import src.handlers.DatabaseHandler;
import src.handlers.VenueHandler;
import src.models.Venue;
import src.models.Event;
import src.handlers.RequestHandler;

public class App extends Application {
	private void initialize(){
		VenueHandler venueHandler = new VenueHandler();
		RequestHandler requestHandler = new RequestHandler();
		List<Venue> venues = venueHandler.retrieveVenuesFromCSV();
		List<Event> events = requestHandler.retrieveRequestsFromCSV();
		DatabaseHandler.seedDb(venues, events);
	}

	@Override
	public void start(Stage primaryStage) {
		initialize();
		try {
			// BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("views/MainMenu.fxml"));
			// Throws nullpointerexception
			Scene scene = new Scene(root,700,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Live Music Venue Matchmaker");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DatabaseHandler.initializeDb("livemusicvenuematchmaker");
		launch(args);
	}
}

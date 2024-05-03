package src.controllers;
// Java library imports
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import java.util.List;
// Local imports
import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.models.Event;
import src.models.Venue;

public class MainMenuController {
    // FXML
    @FXML private TableView<Event> requestTable;
    @FXML private TableColumn requestNoColumn;
    @FXML private TableColumn eventColumn;
    @FXML private TableColumn artistColumn;
    @FXML private TableColumn clientColumn;
    @FXML private TableView<Venue> venueTable;
    @FXML private TableColumn venueNoColumn;
    @FXML private TableColumn venueNameColumn;
    @FXML private TableColumn venueCompatibilityColumn;

    @SuppressWarnings("unchecked")
    @FXML private void initialize(){
        DebugHandler.print("IN MAINMENU.initialize");
        requestNoColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("artist"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("client"));

        List<Event> eventList = DatabaseHandler.readEventsTable();
        requestTable.setItems(FXCollections.observableArrayList(eventList));

        venueNoColumn.setCellValueFactory(new PropertyValueFactory<Venue, Integer>("id"));
        venueNameColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("name"));
        venueCompatibilityColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("category"));
        List<Venue> venueList = DatabaseHandler.readVenuesTable();
        venueTable.setItems(FXCollections.observableArrayList(venueList));
    }
}

package src.controllers;
// Java library imports
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

import java.util.List;
import javafx.collections.ObservableList;
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

    @FXML private void initialize(){
        DebugHandler.print("IN MAINMENU.initialize");
        loadRequestTable();
        loadVenueTable();
    }

    private void loadRequestTable(){
        requestNoColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("artist"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("client"));

        List<Event> eventList = DatabaseHandler.readEventsTable();
        requestTable.setItems(FXCollections.observableArrayList(eventList));
    }

    private void loadVenueTable(){
        venueNoColumn.setCellValueFactory(new PropertyValueFactory<Venue, Integer>("id"));
        venueNameColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("name"));
        // Compatibility column intentionally left blank on first load

        List<Venue> venueList = DatabaseHandler.readVenuesTable();
        venueTable.setItems(FXCollections.observableArrayList(venueList));
    }

    @FXML private void selectEvent(MouseEvent mouseEvent){
        Event event = requestTable.getSelectionModel().getSelectedItem();
        if(event != null){
            DebugHandler.print("clicked " + event.getArtist());
        }
    }
}

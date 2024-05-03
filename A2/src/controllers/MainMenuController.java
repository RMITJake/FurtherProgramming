package src.controllers;
// Java library imports
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

import org.junit.runner.Request;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.handlers.RequestHandler;
import src.handlers.VenueHandler;
// Local imports
import src.models.Event;

public class MainMenuController {
    // FXML
    @FXML private TableView<Event> requestTable;
    @FXML private TableColumn requestNoColumn;
    @FXML private TableColumn eventColumn;
    @FXML private TableColumn artistColumn;
    @FXML private TableColumn clientColumn;

    @SuppressWarnings("unchecked")
    @FXML private void initialize(){
        DebugHandler.print("IN MAINMENU.initialize");
        requestNoColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("artist"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("client"));

        List<Event> eventList = DatabaseHandler.readEventsTable();
        // ObservableList<Event> observableEvents = FXCollections.observableArrayList(eventList.get(0));

        requestTable.setItems(FXCollections.observableArrayList(eventList));
    }
}

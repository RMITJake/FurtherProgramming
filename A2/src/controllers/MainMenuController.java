package src.controllers;
// Java library imports
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.List;
import src.handlers.BookingHandler;
import java.util.Collections;
// Local imports
import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.models.Event;
import src.models.Venue;
import src.models.Booking;

public class MainMenuController {
    BookingHandler bookingHandler = new BookingHandler();
    // FXML
    // Request Table
    @FXML private TableView<Event> requestTable;
    @FXML private TableColumn<Event, Integer> requestNoColumn;
    @FXML private TableColumn<Event, String> eventColumn;
    @FXML private TableColumn<Event, String> artistColumn;
    @FXML private TableColumn<Event, String> clientColumn;
    // Venue Table
    @FXML private TableView<Venue> venueTable;
    @FXML private TableColumn<Venue, Integer> venueNoColumn;
    @FXML private TableColumn<Venue, String> venueNameColumn;
    @FXML private TableColumn<Venue, String> venueCompatibilityColumn;
    // Booking Table
    @FXML private TableView<Event> bookingTable;
    @FXML private TableColumn<Event, String> dateColumn;
    @FXML private TableColumn<Event, String> timeColumn;
    @FXML private TableColumn<Event, Integer> bookingRequestNoColumn;

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

    private void updateVenueTable(List<Venue> venueList){
        venueNoColumn.setCellValueFactory(new PropertyValueFactory<Venue, Integer>("id"));
        venueNameColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("name"));
        venueCompatibilityColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("compatibilityScore"));

        // Collections.sort(venueList, (Venue v1, Venue v2) -> v1.getCompatibilityScore() - v2.getCompatibilityScore());
        Collections.sort(venueList, (Venue v1, Venue v2) -> Integer.compare(v2.getCompatibilityScore(), v1.getCompatibilityScore()));
        venueTable.setItems(FXCollections.observableArrayList(venueList));
    }

    private void updateBookingTable(List<Event> eventList){
        dateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("time"));
        bookingRequestNoColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));

        bookingTable.setItems(FXCollections.observableArrayList(eventList));
    }

    @FXML private void selectEvent(MouseEvent mouseEvent){
        bookingTable.getItems().clear();
        Event event = requestTable.getSelectionModel().getSelectedItem();
        List<Booking> bookingList = new ArrayList<>();
        if(event != null){
            DebugHandler.print("clicked " + event.getArtist());
            bookingList = getBookingCompatibility(event);
        }

        List<Venue> updatedVenueList = new ArrayList<>();
        for(Booking booking : bookingList){
            Venue venue = booking.getVenue();
            venue.setCompatibilityScore(booking.getCompatibilityScore());
            updatedVenueList.add(venue);
        }
        venueTable.getItems().clear();
        updateVenueTable(updatedVenueList);
    }

    @FXML private void selectVenue(MouseEvent mouseEvent){
        Venue venue = venueTable.getSelectionModel().getSelectedItem();
        List<Event> eventList = DatabaseHandler.readVenueEvents(venue.getName());
        updateBookingTable(eventList);
    }

    private List<Booking> getBookingCompatibility(Event event){
        List<Booking> bookingList = new ArrayList<>();
        List<Venue> venueList = DatabaseHandler.readVenuesTable();
        for(Venue venue : venueList){
            bookingList.add(new Booking(event, venue));
        }
        for(Booking b : bookingList){
            DebugHandler.print(String.format("%s %s %s", b.getEvent().getClient(), b.getVenue().getName(), b.getCompatibilityScore()));
        }
        return bookingList;
    }
}

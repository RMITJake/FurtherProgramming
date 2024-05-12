package src.controllers;
// Java library imports
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import src.handlers.BookingHandler;
import java.util.Collections;
// Local imports
import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.models.Event;
import src.models.User;
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
    // Filter checkboxes
    @FXML private Button requestDetailsButton;
    @FXML private CheckBox availableCheckbox;
    @FXML private CheckBox capacityCheckbox;
    @FXML private CheckBox typeCheckbox;
    @FXML private CheckBox categoryCheckbox;
    // Booking buttons
    @FXML private Button bookVenueButton;
    // Banner Menu Options
    @FXML private MenuItem editAccountDetails;
    @FXML private MenuItem userList;

    private Stage stage;
    private Stage parentStage;
    private User user;

    public MainMenuController(Stage parentStage, User user){
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.user = user;
        DebugHandler.print("user's name: " + user.getFirstname() + user.getLastname());
    }

    public void showStage(Pane root){
        Scene scene = new Scene(root, 1000, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Main Menu - Live Venue Music Matcher");
        stage.show();
    }

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

    @FXML private void applyFilters(MouseEvent mouseEvent){
        DebugHandler.print("applying filters");
        Event event = requestTable.getSelectionModel().getSelectedItem();
        if(event != null){
            List<Venue> venueList = DatabaseHandler.readVenuesTable();
            List<Venue> filteredList = new ArrayList<>();

            for(Venue venue : venueList){
                Booking booking = new Booking(event, venue);
                venue.setCompatibilityScore(booking.getCompatibilityScore());
                if(availableCheckbox.isSelected() && booking.matchAvailable()){
                    filteredList.add(venue);
                } else if(capacityCheckbox.isSelected() && booking.matchCapacity()){
                    filteredList.add(venue);
                } else if(typeCheckbox.isSelected() && booking.matchType()){
                    filteredList.add(venue);
                } else if(categoryCheckbox.isSelected() && booking.matchCategory()){
                    filteredList.add(venue);
                }
            }

            if(filteredList.size() > 0){
                updateVenueTable(filteredList);
            } else {
                updateVenueTable(venueList);
            }
        }
    }

    @FXML private void bookVenue(MouseEvent mouseEvent){
        Event event = requestTable.getSelectionModel().getSelectedItem();
        Venue venue = venueTable.getSelectionModel().getSelectedItem();
        if(event != null && venue != null){
            DatabaseHandler.writeBooking(event, venue);
            updateBookingTable(DatabaseHandler.readVenueEvents(venue.getName()));
        }
    }

    @FXML private void autoMatch(){
        DebugHandler.print("in automatch");
        List<Event> eventList = DatabaseHandler.readEventsTable();
        List<Venue> venueList = DatabaseHandler.readVenuesTable();
        List<Booking> bookingList = new ArrayList<>();

        for(Event event : eventList){
            Booking mostCompatible = new Booking();
            for(Venue venue : venueList){
                Booking currentBooking = new Booking(event, venue);
                if(currentBooking.getCompatibilityScore() > mostCompatible.getCompatibilityScore()){
                    mostCompatible = currentBooking;
                }
            }
            bookingList.add(mostCompatible);
            DatabaseHandler.writeBooking(event, mostCompatible.getVenue());
        }
    }

    @FXML private void editAccount(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/views/AccountUpdateView.fxml"));
            
            // Customize controller instance
            Callback<Class<?>, Object> controllerFactory = param -> {
                return new AccountController(stage, user, user);
            };

            loader.setControllerFactory(controllerFactory);
            VBox root = loader.load();
            
            AccountController accountController = loader.getController();
            accountController.showStage(root);
            
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    @FXML private void viewUserList(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/views/UserListView.fxml"));
            
            // Customize controller instance
            Callback<Class<?>, Object> controllerFactory = param -> {
                return new UserListController(stage, user);
            };

            loader.setControllerFactory(controllerFactory);
            AnchorPane root = loader.load();
            
            UserListController userListController = loader.getController();
            userListController.showStage(root);
            
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
}

package src.controllers;
// JavaFX imports
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
// Java library imports
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
// Local imports
import src.daos.BookingDao;
import src.daos.BookingDaoImpl;
import src.daos.EventDao;
import src.daos.EventDaoImpl;
import src.daos.VenueDao;
import src.daos.VenueDaoImpl;
import src.handlers.BackupHandler;
import src.handlers.BookingHandler;
import src.models.Event;
import src.models.User;
import src.models.Venue;
import src.models.Booking;

public class MainMenuController {
    BookingHandler bookingHandler = new BookingHandler();
    // FXML
	@FXML private MenuItem saveMenuItem;
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
    @FXML private MenuItem backupMenu;
    @FXML private MenuItem editAccountDetails;
    @FXML private MenuItem userList;

    private Stage stage;
    private User currentUser;
    private EventDao eventDao;
    private VenueDao venueDao;
    private BookingDao bookingDao;

    public MainMenuController(Stage parentStage, User user){
        this.stage = new Stage();
        this.currentUser = user;
        this.eventDao = new EventDaoImpl();
        this.venueDao = new VenueDaoImpl();
        this.bookingDao = new BookingDaoImpl();
    }

    public void showStage(Pane root){
        Scene scene = new Scene(root, 1000, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Main Menu - Live Venue Music Matcher");
        stage.show();
        if(this.currentUser.getAccountType().equals("staff")){
            backupMenu.setVisible(false);
            userList.setVisible(false);
        }
    }

    @FXML private void initialize(){
        loadRequestTable();
        loadVenueTable();
    }

    private void loadRequestTable(){
        requestNoColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("artist"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("client"));

        List<Event> eventList = new ArrayList<Event>(); 
        try{
            eventList = eventDao.readEventsTable();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        requestTable.setItems(FXCollections.observableArrayList(eventList));
    }

    private void loadVenueTable(){
        venueNoColumn.setCellValueFactory(new PropertyValueFactory<Venue, Integer>("id"));
        venueNameColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("name"));
        // Compatibility column intentionally left blank on first load

        List<Venue> venueList = new ArrayList<Venue>();
        try{
            venueList = this.venueDao.readVenuesTable();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
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

    @FXML private void selectVenue(MouseEvent mouseEvent) throws SQLException, NullPointerException{
        Venue venue = venueTable.getSelectionModel().getSelectedItem();
        if(venue != null){
            List<Event> eventList = new ArrayList<Event>(); 
            try{
                eventList = this.bookingDao.getEventsByVenue(venue.getName());
            } catch(SQLException ex){
                ex.printStackTrace();
            }
            updateBookingTable(eventList);
        }
    }

    private List<Booking> getBookingCompatibility(Event event){
        List<Booking> bookingList = new ArrayList<>();
        List<Venue> venueList = new ArrayList<Venue>();
        try{
            venueList = venueDao.readVenuesTable();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        for(Venue venue : venueList){
            bookingList.add(new Booking(event, venue));
        }
        return bookingList;
    }

    @FXML private void applyFilters(MouseEvent mouseEvent){
        Event event = requestTable.getSelectionModel().getSelectedItem();
        if(event != null){
            List<Venue> venueList = new ArrayList<Venue>();
            try{
                venueList = venueDao.readVenuesTable();
            } catch(SQLException ex){
                ex.printStackTrace();
            }
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
            bookingDao.createBooking(event, venue);
            try{
                updateBookingTable(this.bookingDao.getEventsByVenue(venue.getName()));
            } catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @FXML private void autoMatch() throws SQLException{

        List<Event> eventList = new ArrayList<Event>(); 
        eventList = eventDao.readEventsTable();

        List<Venue> venueList = new ArrayList<Venue>();
        venueList = venueDao.readVenuesTable();

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
            bookingDao.createBooking(event, mostCompatible.getVenue());
        }
    }

    @FXML private void editAccount(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/views/AccountView.fxml"));
            
            // Customize controller instance
            Callback<Class<?>, Object> controllerFactory = param -> {
                return new AccountController(stage, this.currentUser, this.currentUser);
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
                return new UserListController(stage, this.currentUser);
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

    @FXML private void exportBackup(ActionEvent actionEvent) throws FileNotFoundException, IOException, SQLException{
        BackupHandler.exportBackup();
    }

    @FXML private void importBackup() throws FileNotFoundException, IOException, ClassNotFoundException{
        BackupHandler.importBackup();
        initialize();
    }
}

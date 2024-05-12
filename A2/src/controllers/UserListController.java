package src.controllers;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
// Java System imports
import java.util.List;
import java.util.ArrayList;
// Local imports
import src.models.User;

public class UserListController {
    private Stage stage;
    private Stage parentStage;
    private User currentUser;

    // @FXML private TableView userListTable;
    // @FXML private TableColumn usernameColumn;
    // @FXML private TableColumn firstnameColumn;
    // @FXML private TableColumn lastnameColumn;
    // @FXML private TableColumn accountTypeColumn;
    

    public UserListController(Stage parentStage, User currentUser){
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.currentUser = currentUser;
    }

    public void showStage(Pane root){
        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("User List - Live Venue Music Matcher");
        stage.show();
    }

    public void loadUserListTable(){

    }
}

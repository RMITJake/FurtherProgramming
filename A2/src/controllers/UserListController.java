package src.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
// Java System imports
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
// Local imports
import src.models.User;
import src.handlers.DatabaseHandler;
import src.handlers.DebugHandler;
import src.daos.UserDao;

public class UserListController {
    private Stage stage;
    private Stage parentStage;
    private User currentUser;
    private User selectedUser;

    // UserList Table
    @FXML private TableView<User> userListTable;
    @FXML private TableColumn<User, String> usernameColumn;
    @FXML private TableColumn<User, String> firstnameColumn;
    @FXML private TableColumn<User, String> lastnameColumn;
    @FXML private TableColumn<User, String> accountTypeColumn;
    // User inputs
    @FXML private TextField usernameInput;
    @FXML private TextField firstnameInput;
    @FXML private TextField lastnameInput;
    @FXML private PasswordField passwordInput;
    @FXML private ComboBox<String> accountTypeDropdown;
    @FXML private Button addAccountButton;
    @FXML private Button updateAccountButton;
    @FXML private Button deleteAccountButton;
    

    public UserListController(Stage parentStage, User currentUser){
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.currentUser = currentUser;
        this.selectedUser = new User();
    }

    public void showStage(Pane root){
        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("User List - Live Venue Music Matcher");
        stage.show();
    }

    @FXML private void initialize(){
        loadUserListTable();
    }

    public void loadUserListTable(){
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String> ("username"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<User, String>("accountType"));

        List<User> userList = DatabaseHandler.readUserTable();
        userListTable.setItems(FXCollections.observableArrayList(userList));
    }

    @FXML private void selectUser(MouseEvent mouseEvent){
        User user = userListTable.getSelectionModel().getSelectedItem();
        updateUserInputs(user);
    }

    @FXML private void updateUserInputs(User user){
        if(user != null){
            usernameInput.setText(user.getUsername());
            firstnameInput.setText(user.getFirstname());
            lastnameInput.setText(user.getLastname());
            passwordInput.setText("");
            accountTypeDropdown.setValue(user.getAccountType());
            
            if(user.getUsername() == null){
                usernameInput.setDisable(false);
                firstnameInput.setDisable(false);
                lastnameInput.setDisable(false);
                accountTypeDropdown.setDisable(false);
                addAccountButton.setDisable(false);
                updateAccountButton.setDisable(true);
                deleteAccountButton.setDisable(true);
            } else if(user.getUsername().equals("Admin")){
                usernameInput.setEditable(false);
                usernameInput.setDisable(true);
                firstnameInput.setEditable(false);
                firstnameInput.setDisable(true);
                lastnameInput.setEditable(false);
                lastnameInput.setDisable(true);
                accountTypeDropdown.setEditable(false);
                accountTypeDropdown.setDisable(true);
                addAccountButton.setDisable(true);
                updateAccountButton.setDisable(false);
                deleteAccountButton.setDisable(true);
            } else {
                usernameInput.setEditable(true);
                usernameInput.setDisable(false);
                firstnameInput.setEditable(true);
                firstnameInput.setDisable(false);
                lastnameInput.setEditable(true);
                lastnameInput.setDisable(false);
                accountTypeDropdown.setEditable(true);
                accountTypeDropdown.setDisable(false);
                addAccountButton.setDisable(false);
                updateAccountButton.setDisable(false);
                deleteAccountButton.setDisable(false);
            }

            if(user.getAccountType() != null && user.getAccountType().equals("admin")){
                usernameInput.setEditable(false);
                usernameInput.setDisable(true);
            }
        }
    }

    @FXML private void updateUser(ActionEvent actionEvent){
        this.selectedUser.setUser(userListTable.getSelectionModel().getSelectedItem());
        this.selectedUser.setUsername(usernameInput.getText());
        this.selectedUser.setFirstname(firstnameInput.getText());
        this.selectedUser.setLastname(lastnameInput.getText());
        this.selectedUser.setPassword(passwordInput.getText());
        this.selectedUser.setAccountType(accountTypeDropdown.getValue());
        try{
            this.selectedUser.getUserDao().updateUser(this.selectedUser);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        loadUserListTable();
        updateUserInputs(this.selectedUser);
    }

    @FXML private void deleteUser(ActionEvent actionEvent){
        this.selectedUser.setUser(userListTable.getSelectionModel().getSelectedItem());
        try{
            this.selectedUser.getUserDao().deleteUser(this.selectedUser);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        this.selectedUser = new User();
        loadUserListTable();
        updateUserInputs(this.selectedUser);
    }
}

package src.controllers;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import src.handlers.DebugHandler;
import src.models.User;
import src.daos.UserDaoImpl;

public class AccountController {
    private Stage stage;
    private Stage parentStage;
    private User currentUser;
    private User user;

    //FXML fields
    @FXML private TextField usernameInput;
    @FXML private TextField firstnameInput;
    @FXML private TextField lastnameInput;
    @FXML private PasswordField passwordInput;
    @FXML private PasswordField newPasswordInput;
    @FXML private PasswordField confirmPasswordInput;
    @FXML private Button deleteAccountButton;
    @FXML private Button changeAccountTypeButton;
    @FXML private Label updateDetailsErrorText;
    @FXML private Label passwordChangeErrorText;

    public AccountController(Stage parentStage, User currentUser, User user){
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.currentUser = user;
        this.user = user;
        DebugHandler.print("user's name: " + user.getFirstname() + user.getLastname());
    }

    public void showStage(Pane root){
        Scene scene = new Scene(root, 400, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sign up");
		stage.show();
    }

    @FXML private void initialize(){
        DebugHandler.print("user's name: " + user.getFirstname() + user.getLastname());
        usernameInput.setText(user.getUsername());
        firstnameInput.setText(user.getFirstname());
        lastnameInput.setText(user.getLastname());
        updateDetailsErrorText.setText("");
        passwordChangeErrorText.setText("");

        // Disable functions for Manager
        if(currentUser.getAccountType().equals("admin")){
            usernameInput.setEditable(false);
            usernameInput.setDisable(true);
        }

        // Disable functions for Staff
        if(currentUser.getAccountType().equals("staff")){
            deleteAccountButton.setVisible(false);
            changeAccountTypeButton.setVisible(false);
        }
    }

    @FXML private void updateDetails(ActionEvent actionEvent){
        if(!currentUser.getPassword().equals(passwordInput.getText())){
            updateDetailsErrorText.setText("Password is incorrect.");
            updateDetailsErrorText.setTextFill(Color.RED);
        } else if(currentUser.getPassword().equals(passwordInput.getText())){
            if(newPasswordInput.getText().equals("")){
                updateUserWithoutPassword();
                passwordChangeErrorText.setText("");
            } else if(!newPasswordInput.getText().equals(confirmPasswordInput.getText())){
                passwordChangeErrorText.setText("New passwords don't match");
                passwordChangeErrorText.setTextFill(Color.RED);
            } else if(newPasswordInput.getText().equals(confirmPasswordInput.getText())){
                updateUserWithPassword();
                passwordChangeErrorText.setText("");
            }
        }
    }

    private void updateUserWithoutPassword(){
        UserDaoImpl userDao = new UserDaoImpl();
        user.setFirstname(firstnameInput.getText());
        user.setLastname(lastnameInput.getText());
        try{
            userDao.updateUser(user);
            updateDetailsErrorText.setText("Account details updated.");
            updateDetailsErrorText.setTextFill(Color.GREEN);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private void updateUserWithPassword(){
        UserDaoImpl userDao = new UserDaoImpl();
        user.setFirstname(firstnameInput.getText());
        user.setLastname(lastnameInput.getText());
        user.setPassword(newPasswordInput.getText());
        try{
            userDao.updateUser(user);
            updateDetailsErrorText.setText("Account details updated.");
            updateDetailsErrorText.setTextFill(Color.GREEN);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}

package src.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    @FXML private Label updateDetailsErrorText;
    @FXML private Label passwordChangeErrorText;
    @FXML private Button backButton;

    public AccountController(Stage parentStage, User currentUser, User user){
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.currentUser = currentUser;
        this.user = user;
        DebugHandler.print("currentuser's id: " + user.getId() + user.getLastname());
    }

    public void showStage(Pane root){
        Scene scene = new Scene(root, 400, 383);
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
    }

    @FXML private void updateDetails(ActionEvent actionEvent){
        if(passwordInput.getText() == null || passwordInput.getText().equals("")){
            updateDetailsErrorText.setText("Enter your password to change your details.");
            updateDetailsErrorText.setTextFill(Color.RED);
        } else if(!currentUser.getPassword().equals(passwordInput.getText())){
            updateDetailsErrorText.setText("Password is incorrect.");
            updateDetailsErrorText.setTextFill(Color.RED);
        } else if(currentUser.getPassword().equals(passwordInput.getText())){
            if(newPasswordInput.getText().equals("")){
                updateUserWithoutPassword();
                passwordChangeErrorText.setText("");
            } else if(!newPasswordInput.getText().equals(confirmPasswordInput.getText())){
                passwordChangeErrorText.setText("New passwords don't match");
                passwordChangeErrorText.setTextFill(Color.RED);
            updateDetailsErrorText.setText("Account not updated.");
            updateDetailsErrorText.setTextFill(Color.RED);
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
        this.currentUser.setFirstname(firstnameInput.getText());
        this.currentUser.setLastname(lastnameInput.getText());
        this.currentUser.setPassword(newPasswordInput.getText());
        try{
            this.currentUser.getUserDao().updateUser(user);
            updateDetailsErrorText.setText("Account details updated.");
            updateDetailsErrorText.setTextFill(Color.GREEN);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @FXML private void mainMenu(ActionEvent actionEvent){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/views/MainMenu.fxml"));
            
            Callback<Class<?>, Object> controllerFactory = param -> {
                return new MainMenuController(stage, this.currentUser);
            };
            
            loader.setControllerFactory(controllerFactory);
            VBox root = loader.load();

            MainMenuController mainMenuController = loader.getController();
            mainMenuController.showStage(root);

            stage.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}

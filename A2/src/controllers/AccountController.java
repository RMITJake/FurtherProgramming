package src.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.handlers.DebugHandler;
import src.models.User;

public class AccountController {
    private Stage stage;
    private Stage parentStage;
    private User currentUser;
    private User user;

    //FXML fields
    @FXML private TextField usernameInput;
    @FXML private TextField firstnameInput;
    @FXML private TextField lastnameInput;

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
    }
}

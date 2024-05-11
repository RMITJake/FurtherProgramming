package src.controllers;

import java.io.IOException;
import java.sql.SQLException;

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
import src.models.*;

public class LoginController {
	@FXML
	private TextField name;
	@FXML
	private PasswordField password;
	@FXML
	private Label message;
	@FXML
	private Button loginButton;
	@FXML
	private Button signup;

	private Login login;
	private Stage stage;
	
	public LoginController(Stage stage, Login login) {
		this.stage = stage;
		this.login = login;
	}
	
	@FXML
	public void initialize() {		
		loginButton.setOnAction(event -> {
			if (!name.getText().isEmpty() && !password.getText().isEmpty()) {
				User user;
				try {
					user = login.getUserDao().getUser(name.getText(), password.getText());
					if (user != null) {
						login.setCurrentUser(user);
						message.setText("Login success for " + user.getUsername());
						message.setTextFill(Color.GREEN);

						// Successful login, load main menu
						try{
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/views/MainMenu.fxml"));
							
							Callback<Class<?>, Object> controllerFactory = param -> {
								return new MainMenuController(stage, user);
							};
							
							loader.setControllerFactory(controllerFactory);
							VBox root = loader.load();

							MainMenuController mainMenuController = loader.getController();
							mainMenuController.showStage(root);

							stage.close();
						} catch(IOException ex){
							ex.printStackTrace();
						}

					} else {
						message.setText("Wrong username or password");
						message.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					message.setText(e.getMessage());
					message.setTextFill(Color.RED);
				}
				
			} else {
				message.setText("Empty username or password");
				message.setTextFill(Color.RED);
			}
			name.clear();
			password.clear();
		});
		
		signup.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/views/SignupView.fxml"));
				
				// Customize controller instance
				Callback<Class<?>, Object> controllerFactory = param -> {
					return new SignupController(stage, login);
				};

				loader.setControllerFactory(controllerFactory);
				VBox root = loader.load();
				
				SignupController signupController = loader.getController();
				signupController.showStage(root);
				
				message.setText("");
				name.clear();
				password.clear();
				
				stage.close();
			} catch (IOException e) {
				e.printStackTrace();
			}});
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Welcome");
		stage.show();
	}
}


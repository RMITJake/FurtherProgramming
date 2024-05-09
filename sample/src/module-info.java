module Week9FullStackExample {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	
	opens main.java.controller to javafx.graphics, javafx.fxml;
}
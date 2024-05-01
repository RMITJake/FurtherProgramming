package fpoua.week10.assessment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * In this assessment, you are required to extend the Animal program to:
 *  - include event handlers to select, add, delete, and save.
 *  - save table view content into a database.
 *  There are 6 lines to be replaced by Java keywords or statements.
 *  Please fill in the blanks.
 */

public class MainWeek10 extends Application {

	private Animal selectedItem; //to keep track of item selected

	@Override // Override the start in the Application
	public void start(Stage primaryStage) throws IOException {

		ArrayList<Panda> pandas = new ArrayList<>();
		pandas.add(new Panda(1, "Puma", "black white blue", 7));
		pandas.add(new Panda(2, "Kioma", "black grey orange", 5));
		pandas.add(new Panda(3, "Simba", "white grey blue", 9));

		ArrayList<Cat> cats = new ArrayList<>();
		cats.add(new Cat(1, "Hello Kitty", "white", 15));
		cats.add(new Cat(2, "Garfield", "orange", 20));
		cats.add(new Cat(3, "Grumpy cat", "brown and white", 7));

		GenericAnimalList animals = new GenericAnimalList();

		animals.populate(cats);
		animals.populate(pandas);
		animals.addAnAnimal(new Dog("Goofy", "yellow", 99));

		TableView tableView = new TableView();

		TableColumn<Animal, String> column1 = new TableColumn<>("Name");

		column1.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Animal, String> column2 = new TableColumn<>("Colour");

		column2.setCellValueFactory(new PropertyValueFactory<>("colour"));

		TableColumn<Animal, Integer> column3 = new TableColumn<>("Age");

		column3.setCellValueFactory(new PropertyValueFactory<>("age"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);

		tableView.getItems().addAll(animals.getList());

		TableViewSelectionModel<Animal> selectionModel = tableView.getSelectionModel();

		ObservableList<Animal> selectedItems = selectionModel.getSelectedItems();

		VBox vbox = new VBox(tableView);

		HBox fieldsHbox = new HBox();

		TextField nameTextField = new TextField();
		nameTextField.setPromptText("name");

		TextField coloursTextField = new TextField();
		coloursTextField.setPromptText("colours");

		TextField ageTextField = new TextField();
		ageTextField.setPromptText("age");

		fieldsHbox.getChildren().add(nameTextField);
		fieldsHbox.getChildren().add(coloursTextField);
		fieldsHbox.getChildren().add(ageTextField);

		selectedItems.addListener((ListChangeListener<? super Animal>) new ListChangeListener<Animal>() {
			@Override
			public void onChanged(Change<? extends Animal> change) {
				System.out.println("Selection changed: " + change.getList());

				selectedItem = (Animal) tableView.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					nameTextField.setText(selectedItem.getName());
					coloursTextField.setText(____________________); //TASK 1 set colours field accordingly
					ageTextField.setText(String.valueOf(__________________)); //TASK 2 set age field accordingly
				}
			}
		});

		Button addButton = new Button("Add to List");
		addButton.setOnAction(event -> {
			Cat aCat = new Cat(nameTextField.getText(), coloursTextField.getText(), Integer.parseInt(ageTextField.getText()));
			animals.addAnAnimal(aCat);
			System.out.println("New cat added");
			tableView.getItems().clear(); //clear the current content to refresh it.
			tableView.getItems().addAll(animals.getList()); //re-populate the User Interface TableView list
		});

		Button ________ = new Button("Remove from List"); //TASK 3: Declare the remove button
		______________.setOnAction(event -> { //TASK 4: Attach an event handler to the button

			if (tableView.getItems().size()>0) {
				animals.getList().remove(selectedItem);
				tableView.getSelectionModel().clearSelection();
				tableView.getItems().clear(); //refresh
				tableView.getItems().addAll(______________); //TASK 5: re-populate the User Interface TableView list after clearing it
				System.out.println(animals.getList());
			}
		});

		Button saveButton = new Button("Save to DB");
		saveButton.setOnAction(event -> {
			System.out.print("Saving");
			DBManager.write(____________); //TASK 6: pass on the generic animal list using DBManager write method
		});

		HBox buttonsHbox = new HBox();
		buttonsHbox.getChildren().add(addButton);
		buttonsHbox.getChildren().add(removeButton);
		buttonsHbox.getChildren().add(saveButton);

		vbox.getChildren().add(fieldsHbox);
		vbox.getChildren().add(buttonsHbox);
		
		Scene scene = new Scene(vbox);
		primaryStage.setTitle("My pets");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		DBManager.createPetsTable();
		Application.launch(args);
		DBManager.read();
	}
}
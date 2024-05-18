package fpoua.week10.solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBManager {

	public static void read() {
		try (
				// create a database connection
				Connection connection = DriverManager.getConnection("jdbc:sqlite:animals.db");
				Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery("select * from person");
			while (rs.next()) {
				// read the result set
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace(System.err);
		}
	}

	public static void createPetsTable() {
		try (
				// create a database connection
				Connection connection = DriverManager.getConnection("jdbc:sqlite:animals.db");
				Statement statement = connection.createStatement();
		) {
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists pets");
			statement.executeUpdate("create table pets (" + "id integer PRIMARY KEY AUTOINCREMENT, " + "name string, "
					+ "colours string, " + "age integer)");
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace(System.err);
		}
	}

	public static void write(List<Animal> petList) {
		// NOTE: Connection and Statement are AutoCloseable.
		// Don't forget to close them both in order to avoid leaks.
		String insertStatement = "insert into pets values (?, ?, ?, ?)";

		try (
				// create a database connection
				Connection connection = DriverManager.getConnection("jdbc:sqlite:animals.db");
				PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
		) {
			int rows =0;
			for (Animal pet : petList) {
				preparedStatement.setString(2, pet.getName());
				preparedStatement.setString(3, pet.getColour());
				preparedStatement.setInt(4, pet.getAge());

				int row = preparedStatement.executeUpdate(); // rows affected
				System.out.print(".");
				rows += row;
			}
			System.out.print(rows + " rows"); // 1
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace(System.err);
		}
	}

	public static void main(String[] args) {

	}
}
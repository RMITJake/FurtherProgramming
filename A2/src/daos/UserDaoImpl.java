package src.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.handlers.DatabaseHandler;
import src.models.User;

public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME = "users";

	public UserDaoImpl() {}

	@Override
	public User getUser(String username, String password) throws SQLException {
		String query = "SELECT * FROM ? WHERE username = ? AND password = ?";
		try (Connection connection = DatabaseHandler.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, TABLE_NAME);
			statement.setString(2, username);
			statement.setString(3, password);
			
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
                    user.setAccountType(rs.getString("accountType"));
                    user.setAccountEnabled(rs.getBoolean("accountEnabled"));
					return user;
				}
				return null;
			} 
		}
	}

	@Override
	public User createUser(String username, String password) throws SQLException {
        User newUser = new User(username, password);
		String query = "INSERT INTO ? VALUES (?, ?, ?, ?)";
		try (Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, TABLE_NAME);
			statement.setString(2, newUser.getUsername());
			statement.setString(3, newUser.getPassword());
            statement.setString(4, newUser.getAccountType());
            statement.setBoolean(5, newUser.getAccountEnabled());

			statement.executeUpdate();
			return new User(username, password);
		} 
	}
}

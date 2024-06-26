package src.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import src.handlers.DatabaseHandler;
import src.models.User;

public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME = DatabaseHandler.usersTable;
	public final String SCHEMA = ""
        +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
        +"username STRING, "
        +"password STRING, "
        +"firstname STRING, "
        +"lastname STRING, "
        +"accountType STRING, "
        +"accountEnabled BOOLEAN";

	public UserDaoImpl() {}

	@Override
	public List<User> readUserTable() throws SQLException{
        List<User> userList = new ArrayList<>();
		String query = "SELECT * FROM " + TABLE_NAME;

		try (Connection connection = DatabaseHandler.getConnection(); 
            Statement statement = connection.createStatement();
        ){ // inside the try block
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                User user = new User(
                result.getInt("id"),
                result.getString("username"),
                result.getString("password"),
                result.getString("firstname"),
                result.getString("lastname"),
                result.getString("accountType"),
                result.getBoolean("accountEnabled")
                );
                userList.add(user);
            }

            return userList;
        }
    }

	@Override
	public Boolean userExists(String username) throws SQLException {
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
		try (Connection connection = DatabaseHandler.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, username);
			
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					return true;
				}
				return false;
			} 
		}
	}

	@Override
	public Boolean usernameWithOtherIdExists(String username, int id) throws SQLException {
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND id != ?";
		try (Connection connection = DatabaseHandler.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, username);
			statement.setInt(2, id);
			
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					return true;
				}
				return false;
			} 
		}
	}

	@Override
	public User getUser(String username, String password) throws SQLException {
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = DatabaseHandler.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, username);
			statement.setString(2, password);
			
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
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
		if(userExists(username)){
			return null;
		}
        User newUser = new User(username, password);
		String query = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
            // statement.setString(1, TABLE_NAME);
			statement.setString(2, newUser.getUsername());
			statement.setString(3, newUser.getPassword());
			statement.setString(4, newUser.getFirstname());
			statement.setString(5, newUser.getLastname());
            statement.setString(6, newUser.getAccountType());
            statement.setBoolean(7, newUser.getAccountEnabled());

			statement.executeUpdate();
			return new User(username, password);
		} 
	}

	@Override
	public User createUser(User newUser) throws SQLException {
		if(userExists(newUser.getUsername())){
			return null;
		}
		String query = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
            // statement.setString(1, TABLE_NAME);
			statement.setString(2, newUser.getUsername());
			statement.setString(3, newUser.getPassword());
			statement.setString(4, newUser.getFirstname());
			statement.setString(5, newUser.getLastname());
            statement.setString(6, newUser.getAccountType());
            statement.setBoolean(7, newUser.getAccountEnabled());

			statement.executeUpdate();
			return newUser;
		} 
	}

	@Override
	public void createUser(List<User> userList) throws SQLException {
		String query = "INSERT INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
			for(User user : userList){
				statement.setInt(1, user.getId());
				statement.setString(2, user.getUsername());
				statement.setString(3, user.getPassword());
				statement.setString(4, user.getFirstname());
				statement.setString(5, user.getLastname());
				statement.setString(6, user.getAccountType());
				statement.setBoolean(7, user.getAccountEnabled());

				statement.executeUpdate();
			}
		} 
	}

	@Override
	public User updateUser(User user) throws SQLException {
		if(usernameWithOtherIdExists(user.getUsername(), user.getId())){
			return null;
		}

		String query = "INSERT OR REPLACE INTO " + TABLE_NAME +" VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
            // statement.setString(1, TABLE_NAME);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstname());
			statement.setString(5, user.getLastname());
            statement.setString(6, user.getAccountType());
            statement.setBoolean(7, user.getAccountEnabled());

			statement.executeUpdate();
			return user;
		} 
	}

	@Override
	public void deleteUser(User user) throws SQLException {
		String query = "DELETE FROM " + TABLE_NAME +" WHERE id = (?)";
		try (Connection connection = DatabaseHandler.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, user.getId());

			statement.executeUpdate();
		}
	}
}

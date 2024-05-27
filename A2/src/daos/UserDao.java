package src.daos;

import java.sql.SQLException;
import java.util.List;

import src.models.User;

public interface UserDao{
	List<User> readUserTable() throws SQLException;
	Boolean userExists(String username) throws SQLException;
	public Boolean usernameWithOtherIdExists(String username, int id) throws SQLException;
	User getUser(String username, String password) throws SQLException;
	User createUser(String username, String password) throws SQLException;
	User createUser(User user) throws SQLException;
	void createUser(List<User> userList) throws SQLException;
	User updateUser(User user) throws SQLException;
	void deleteUser(User user) throws SQLException;
}
package src.daos;

import java.sql.SQLException;
import src.models.User;

public interface UserDao{
	User getUser(String username, String password) throws SQLException;
	User createUser(String username, String password) throws SQLException;
	User createUser(User user) throws SQLException;
	User updateUser(User user) throws SQLException;
	void deleteUser(User user) throws SQLException;
}
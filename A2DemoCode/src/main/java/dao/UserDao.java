package main.java.dao;

import java.sql.SQLException;

import main.java.model.User;

public interface UserDao {
	void setup() throws SQLException;
	User getUser(String username, String password) throws SQLException;
	User createUser(String username, String password) throws SQLException;
}

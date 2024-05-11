package src.models;

import src.daos.UserDao;
import src.daos.UserDaoImpl;

public class Login {
    private UserDao loginDao;
    private User currentUser;

    public Login(){
        loginDao = new UserDaoImpl();
    }

    public UserDao getUserDao(){
        return loginDao;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public void setCurrentUser(User user){
        currentUser = user;
    }
}

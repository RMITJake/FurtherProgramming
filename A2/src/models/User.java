package src.models;

import java.io.Serializable;

import src.daos.UserDao;
import src.daos.UserDaoImpl;

public class User implements Serializable{
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String accountType;
    private boolean accountEnabled;

    private UserDao userDao;
    private User currentUser;

    public User(){
        userDao = new UserDaoImpl();
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.firstname = "";
        this.lastname = "";
        this.accountType = "staff";
        this.accountEnabled = true;
    }

    public User(String username, String password, String firstname, String lastname, String accountType, boolean accountEnabled){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountType = accountType;
        this.accountEnabled = accountEnabled;
    }

    public User(int id, String username, String password, String firstname, String lastname, String accountType, boolean accountEnabled){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountType = accountType;
        this.accountEnabled = accountEnabled;
    }

    public void setUser(User user){
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.accountType = user.accountType;
        this.accountEnabled = user.accountEnabled;
    }

    public UserDao getUserDao(){
        return userDao;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public void setCurrentUser(User user){
        currentUser = user;
    }

// Getters and Setters
    public int getId(){ return this.id; }
    public void setId(int id){ this.id = id; }
    public String getUsername(){ return this.username; }
    public void setUsername(String username){ this.username = username; }
    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }
    public String getFirstname(){ return this.firstname; }
    public void setFirstname(String firstname){ this.firstname = firstname; }
    public String getLastname(){ return this.lastname; }
    public void setLastname(String lastname){ this.lastname = lastname; }
    public String getAccountType(){ return this.accountType; }
    public void setAccountType(String accountType){ this.accountType = accountType; }
    public boolean getAccountEnabled(){ return this.accountEnabled; }
    public void setAccountEnabled(boolean accountEnabled){ this.accountEnabled = accountEnabled; }
}
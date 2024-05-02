package src.models;

public class User{
    private String username;
    private String password;
    private String accountType;
    private boolean accountEnabled;

    public User(String username, String password, String accountType, boolean accountEnabled){
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.accountEnabled = accountEnabled;
    }

    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }
    public void set(String accountType){ this.accountType = accountType; }
    public void set(boolean accountEnabled){ this.accountEnabled = accountEnabled; }
}
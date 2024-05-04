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

    public String getUsername(){ return this.username; }
    public void setUsername(String username){ this.username = username; }
    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }
    public String getAccountType(){ return this.accountType; }
    public void set(String accountType){ this.accountType = accountType; }
    public boolean getAccountEnabled(){ return this.accountEnabled; }
    public void setAccountEnabled(boolean accountEnabled){ this.accountEnabled = accountEnabled; }
}
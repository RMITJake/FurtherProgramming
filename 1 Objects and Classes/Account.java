// A class is a template or blueprint of an object.
// It defines what the object's data and methods will be.
class Account {
    
    String accId;
    String name;
    double amount;
    
    // Constructor
    public Account(String accountId, String accountName, double amount){
        this.accId = accountId;
        this.name = accountName;
        this.amount = amount;
    }
    
    public void PrintData(){
        System.out.print(accId + ",");
        System.out.print(name + ",");
        System.out.print(amount + "\n");
    }
    
    public static void main(String[] args){
        Account obj = new Account("J1902", "Jake", 10.0);
        obj.PrintData();
        System.exit(0);
    }
}
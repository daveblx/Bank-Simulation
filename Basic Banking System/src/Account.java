public class Account {

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType;
    private String creationDate;
    private boolean isActive;

    public Account(String accountNumber, String accountHolderName, double initialBalance, String accountType, String creationDate) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.creationDate = creationDate;
        this.isActive = true;
    }

    //Getters and Setters

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //Account action methods

    public void createAccount(String accountNumber, String accountHolderName, double initialBalance, String accountType, String creationDate) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.creationDate = creationDate;
        this.isActive = true;
    }

    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount){
        if (amount > 0 && amount <= balance){
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds!");
        }
    }

    public double checkBalance(){
        return this.balance;
    }


}

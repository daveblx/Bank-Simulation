import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bank {

    private List<Account> accounts;
    private String bankName;
    private int checkDigit;
    private int bankCode;
    private long uniqueNumber;


    public Bank() {
        accounts = new ArrayList<>();
    }

    public void createCheckDigit(){
        Random random = new Random();
        checkDigit = random.nextInt(90);
    }

    public void createBankCode(){
        Random random = new Random();
        bankCode = random.nextInt(9000);
    }

    public void createUniqueNumber(){
        Random random = new Random();
        this.uniqueNumber = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
    }

    public String createAccountNumber(){
        createCheckDigit();
        createBankCode();
        createUniqueNumber();
        String accountNumber = "DE" + checkDigit + bankCode + "0000" + uniqueNumber;
        return accountNumber;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public Account findAccount(String accountNumber){
        for(Account account : accounts){
            if(account.getAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        System.out.println("Account not found!");
        return null;
    }

    public void performTransactions(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account number to perform a transaction: ");
        String accountNumber = scanner.nextLine();

        //Find the account
        Account account = findAccount(accountNumber);
        if(account == null){
            System.out.println("Account not found!");
            return;
        }

        System.out.println("Select an option: ");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch(option){
            case 1:
                System.out.println("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                System.out.println("Deposit successful. New balance: " + account.getBalance());
                break;
            case 2:
                System.out.println("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                if (account.getBalance() >= withdrawAmount){
                    account.withdraw(withdrawAmount);
                    System.out.println("Withdrawal successful. New balance: " + account.getBalance());
                } else {
                    System.out.println("Insufficient funds!");
                }
                break;
            case 3:
                System.out.println("Current balance: " + account.getBalance());
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }
}

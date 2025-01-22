import org.w3c.dom.ls.LSOutput;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.jar.JarOutputStream;

public class Main {

    public static void main(String[] args) {
        
        //Complete Bank System
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        // Infinite loop to continuously ask the user for input
        while (true) {
            // Display available options to the user
            System.out.println("\nSelect an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            // Read user's choice
            int option = -1;
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    option = Integer.parseInt(scanner.nextLine()); // Get the user input and parse it as an integer
                    if (option < 1 || option > 5) {
                        System.out.println("Invalid option. Please select a number between 1 and 5.");
                    } else {
                        break;  // Exit loop if the input is valid
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            // Execute the chosen action
            switch (option) {
                case 1: // Create Account
                    // Prompt user for account details
                    System.out.println("Enter account holder name:");
                    String accountHolderName = scanner.nextLine();

                    System.out.println("Enter account type (e.g., savings, checking):");
                    String accountType = scanner.nextLine();

                    System.out.println("Enter initial deposit amount:");
                    double initialDeposit = 0;
                    try {
                        initialDeposit = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount entered, defaulting to 0.");
                    }

                    // Generate the account number
                    String newAccountNumber = bank.createAccountNumber();
                    // Create a new account with the provided details
                    Account newAccount = new Account(newAccountNumber, accountHolderName, initialDeposit, accountType, "2025-01-22");
                    bank.addAccount(newAccount);
                    System.out.println("Account created! Account Number: " + newAccountNumber);
                    break;

                case 2: // Deposit
                    System.out.println("Enter account number to deposit into:");
                    String depositAccountNumber = scanner.nextLine();
                    Account depositAccount = bank.findAccount(depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.println("Enter amount to deposit:");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        depositAccount.deposit(depositAmount);
                        System.out.println("Deposit successful. New balance: " + depositAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3: // Withdraw
                    System.out.println("Enter account number to withdraw from:");
                    String withdrawAccountNumber = scanner.nextLine();
                    Account withdrawAccount = bank.findAccount(withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        System.out.println("Enter amount to withdraw:");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        if (withdrawAccount.getBalance() >= withdrawAmount) {
                            withdrawAccount.withdraw(withdrawAmount);
                            System.out.println("Withdrawal successful. New balance: " + withdrawAccount.getBalance());
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4: // Check Balance
                    System.out.println("Enter account number to check balance:");
                    String balanceAccountNumber = scanner.nextLine();
                    Account balanceAccount = bank.findAccount(balanceAccountNumber);
                    if (balanceAccount != null) {
                        System.out.println("Current balance: " + balanceAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5: // Exit
                    System.out.println("Exiting program...");
                    return;  // Exit the program

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

}

import java.util.Scanner;
import java.util.Random;

abstract class BankAccount {
    protected String CustName;
    protected String AccNo;
    protected double balance;

    
    Boolean generateAccNo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the account holder: ");
        this.CustName = sc.nextLine();
        Random r = new Random();
        this.AccNo = "";
        for (int i = 0; i < 11; i++) {
            int d = r.nextInt(10);
            this.AccNo += d;
        }
        System.out.println("The account number of the holder is: " + this.AccNo);
        return true;
    }

    // Method to get balance
    void getBalance() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the balance for "+this.CustName);
		this.balance = sc.nextDouble();
        System.out.println("The current balance for " + CustName + " (" + AccNo + "): " + this.balance);
    }

    abstract void displayBalance();
    abstract void withdraw();
    abstract void deposit();
}

class SavingsAccount extends BankAccount {

    @Override
    void displayBalance() {
        System.out.println("Savings Account Balance for " + CustName + " (" + AccNo + "): " + balance);
    }

    @Override
    void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to withdraw:");
        double amount = sc.nextDouble();
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    @Override
    void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to deposit:");
        double amount = sc.nextDouble();
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }
}

class CurrentAccount extends BankAccount {
    private double minBalance = 5000.0;
    private double serviceCharge = 100.0;

    @Override
    void displayBalance() {
        System.out.println("Current Account Balance for " + CustName + " (" + AccNo + "): " + balance);
    }

    @Override
    void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to withdraw:");
        double amount = sc.nextDouble();
        if (balance >= amount) {
            balance -= amount;
            if (balance < minBalance) {
                System.out.println("Balance below minimum. Applying service charge.");
                balance -= serviceCharge;
            }
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    @Override
    void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to deposit:");
        double amount = sc.nextDouble();
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    void chequeTransfer(BankAccount receiver, double amount) {
        if (this == receiver) {
            System.out.println("Cannot transfer cheque to the same account.");
        } else if (balance >= amount) {
            this.balance -= amount;
            receiver.balance += amount;
            System.out.println("Cheque transfer successful.");
            System.out.println("Donor("+this.CustName+")'s new balance: " + this.balance);
			System.out.println("Recipient("+receiver.CustName+")'s new balance: "+receiver.balance );
        } else {
            System.out.println("Insufficient balance for cheque transfer!");
        }
    }
}

public class BankDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of bank customers:");
        int n = sc.nextInt();
        sc.nextLine(); // Consume the newline
        BankAccount[] accounts = new BankAccount[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Choose account type for customer " + (i + 1) + ":");
            System.out.println("1. Savings Account\n2. Current Account");
            int accountType = sc.nextInt();
            sc.nextLine(); // Consume the newline

            if (accountType == 1) {
                accounts[i] = new SavingsAccount();
            } else if (accountType == 2) {
                accounts[i] = new CurrentAccount();
            } else {
                System.out.println("Invalid choice! Defaulting to Savings Account.");
                accounts[i] = new SavingsAccount();
            }

            // Generate account details and initial balance
            if (accounts[i].generateAccNo()) {
                System.out.println("Enter initial balance:");
                accounts[i].balance = sc.nextDouble();
                sc.nextLine(); // Consume the newline
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Display Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Cheque Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (BankAccount account : accounts) {
                        account.displayBalance();
                    }
                    break;

                case 2:
                    System.out.println("Enter customer index for withdrawal (1 to " + n + "):");
                    int withdrawIndex = sc.nextInt() - 1;
                    if (withdrawIndex >= 0 && withdrawIndex < n) {
                        accounts[withdrawIndex].withdraw();
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 3:
                    System.out.println("Enter customer index for deposit (1 to " + n + "):");
                    int depositIndex = sc.nextInt() - 1;
                    if (depositIndex >= 0 && depositIndex < n) {
                        accounts[depositIndex].deposit();
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 4:
                    System.out.println("Enter donor index (1 to " + n + "):");
                    int donorIndex = sc.nextInt() - 1;
                    System.out.println("Enter recipient index (1 to " + n + "):");
                    int recipientIndex = sc.nextInt() - 1;
                    if (donorIndex >= 0 && donorIndex < n && recipientIndex >= 0 && recipientIndex < n) {
                        System.out.println("Enter cheque amount:");
                        double chequeAmount = sc.nextDouble();
                    } else {
                        System.out.println("Invalid indices!");
                    }
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        System.out.println("Exiting program. Thank you!");
    }
}

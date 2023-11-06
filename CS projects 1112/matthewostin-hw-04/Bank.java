/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2023
author: Charles Peeke, Matthew Ostin

This file represetnsa a bank with the functions of manipulating accounts.
This is represeted with creating/closing account, depositing/withdrawing,
getting balance and processing transactions.
--------------------------------------------------------------------------*/

public class Bank {
    public String bankName; // Name of the bank
    private Account[] accounts; // Array to store accounts
    private Transaction[] transactions; // Array to store transactions
    private int count; // Number of accounts in the bank
    private int transactionCount; // Number of transactions in the bank

    public Bank(String name) {
        bankName = name;
        accounts = new Account[100]; // Assuming a maximum of 100 accounts
        transactions = new Transaction[1000]; // Assuming a maximum of 1000 transactions
        count = 0;
        transactionCount = 0;
    }

    // Method to create a new account and return the account number
    public int createAccount(String name) {
        Account account = new Account(name);
        accounts[count] = account;
        count++;
        return account.getAccountNumber();
    }

    // Method to close and remove an account and return the remaining balance
    public double closeAccount(int accountNumber) {
        Account accountToClose = null;
        int position = -1;
        for (int i = 0; i < accounts.length; i++) {
            Account account = accounts[i];
            if (account != null && account.getAccountNumber() == accountNumber) {
                accountToClose = account;
                position = i;
                break;
            }
        }

        if (accountToClose == null) {
            return -1;
        }

        double balance = accountToClose.getBalance();
        
        // remove the account from the accounts
        this.accounts[position] = null;
        // shift items one to the left to fill position
        while (position < accounts.length - 1) {
            accounts[position] = accounts[position + 1];
            position++;
        }
        accounts[position] = null;// remove the last account since it has been shifted by on to the left
        // a b c e f f_
        // a b c e f null
        // Perform any other necessary operations for closing the account
        return balance;
    }

    // Method to get the balance of an account
    public double getBalance(int accountNumber) {
        Account account = null;
        for (int i = 0; i < count; i++) {
            //skip an account if it is null since we are using arrays , there could be null spots
            if (accounts[i] == null)
                continue;

            if (accounts[i].getAccountNumber() == accountNumber) {
                account = accounts[i];
                break;
            }
        }

        if (account == null) {

            return -1;
        } else {
            // Perform any necessary operations for closing the account
            // For example, you can set a flag or mark the account as closed
            // without using setBalance or closed methods
            return account.getBalance();
        }
    }

    // Method to deposit an amount to an account
    public void deposit(int accountNumber, double amount) {
        for (int i = 0; i < count; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber() == accountNumber) {
                accounts[i].deposit(amount);
                break;
            }
        }
    }

    // Method to withdraw an amount from an account
    public void withdraw(int accountNumber, double amount) {
        for (int i = 0; i < count; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber() == accountNumber) {
                accounts[i].withdraw(amount);
                break;
            }
        }
    }
    // Transaction methods

    // Method to add a transaction to the transactions array
    public void addTransaction(Transaction t) {
        transactions[transactionCount++] = t; // Update transactionCount while adding transaction
    }

    // Method to process all transactions in the transactions array
    public void processTransaction() {
        for (int i = 0; i < transactionCount; i++) {
            Transaction transaction = transactions[i];
            int accountNumber = transaction.getAccountNumber();
            double amount = transaction.getAmount();
            for (int j = 0; j < count; j++) {
                if (accounts[j] != null && accounts[j].getAccountNumber() == accountNumber) {
                    accounts[j].deposit(amount);
                    break;
                }
            }
        }
        transactions = new Transaction[100];
        transactionCount = 0;
    }

    public String getBankName() {
        // Method to get the bank name
        return bankName;
    }

    public int getNumberOfAccounts() {
        // Method to get the total number of accounts
        int numAccounts = 0;
        // Iterate through the accounts array and count the non-null accounts
        for (int i = 0; i < count; i++) {
            if (accounts[i] != null) {
                numAccounts++;
            }
        }
        return numAccounts;
    }

    public int getNumberOfTransactions() {
        // Method to get the total number of transactions
        return transactionCount;
    }

    @Override
    public String toString() {
        String s = "Bank Name: " + bankName + " \n";
        s += "Number of Accounts: " + count + " | ";
        s += "Number of Pending Transactions: " + getNumberOfTransactions() + "\n";
        s += "Accounts: \n";
        for (int i = 0; i < count; i++) {
            if (accounts[i] == null) {
                continue;
            }

            s += accounts[i].toString() + " \n";
        }
        return s;
    }
}
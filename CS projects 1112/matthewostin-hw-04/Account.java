import java.text.DecimalFormat;

/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2023
author: Charles Peeke, Matthew Ostin
This class does an implementation of a bank account. IT has methods to 
deposit funds and withdraw funds. It also assigns a unique acc number
based on the ASCII values of the account name. Also provides a string
representation of the acc info.
--------------------------------------------------------------------------*/

public class Account {
    private String accountName; // name of acc 
    private int accountNumber; // acc num based on ASCII vals
    private double balance;
    
    
    public Account(String name) {
        this.accountName = name;
        this.balance = 0.0;
        this.assignAccountNumber(); // makes an acc num according to ASCII vals
    }
    
    // abc= A(a)A(b)A(c)
    private void assignAccountNumber() {
        int sum = 0; 
        for(int i=0; i<this.accountName.length(); i++){ 
            char ch = this.accountName.charAt(i);
            sum +=  ((int)ch); // calc the sum of ASCII vals
        }
        this.accountNumber = sum; 
    }
    
    // Getters and setters
    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }
    
    // Deposit and Withdraw Methods
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount; //adds the deposit amount to the current balance
        }
    }

    public void withdraw(double amount) {
            if (amount > 0) {
                double overdraft = this.balance - amount; 
                if (overdraft < -100.0) { //if overdraft no action is done
                } else {
                    this.balance -= amount; // takes the amount out of balance
                }
            }
        }
        

    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String s = "";
        s += "Account Name: " + this.accountName + " \t";
        s += "Account Number: " + this.accountNumber + " \t";
        s += "Balance: " + df.format(this.balance);
        return s;
    }
}



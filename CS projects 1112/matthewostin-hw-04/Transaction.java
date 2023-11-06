/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2023
author: Charles Peeke, Matthew Ostin

The transaction file represents transactions for depositing and withdrawing money from
a bank acc. Has four possible operation that can be done, opening/closing an acocunt
widrawaling or depositing to an account.
--------------------------------------------------------------------------*/
public class Transaction {
    public int accountNumber;
    private int operation;
    private double amount;

    public Transaction(int acctNumber, int operation, String content) {
        accountNumber = acctNumber;
        switch (operation) {
            case 0:
            case 1:
            case 2:
                this.operation = operation; 
                amount = Double.parseDouble(content);
                break;
            default:
                this.operation = translateOldOperation(content); 
                break;
        }
    }
    //getter method
    public int getAccountNumber() {
        return accountNumber;
    }

    //getter method
    public int getOperation() {
        return operation;
    }

    //getter method
    public double getAmount() {
        return amount;
    }
    // this is a helper method which is used to translate old operation codes
    //into the new ones
    private int translateOldOperation(String content) {
       
        double lOperand, rOperand;
        int operation = -1;
        String result;
        String[] opFields = content.split(" ");
    
        
        String[] calc = new String[opFields.length];
        int top = -1;
    
        for (int i = 0; i < opFields.length; i++) {
            if (opFields[i].equals("+")) {
                if (top < 1) {
                    // Handle error and throw exception
                } else {
                    rOperand = Double.parseDouble(calc[top--]);
                    lOperand = Double.parseDouble(calc[top--]);
                    lOperand += rOperand;
                    result = String.valueOf(lOperand);
                    calc[++top] = result;
                }
            } else if (opFields[i].equals("-")) {
                if (top < 1) {
                    // Handle error and throw exception
                } else {
                    rOperand = Double.parseDouble(calc[top--]);
                    lOperand = Double.parseDouble(calc[top--]);
                    lOperand -= rOperand;
                    result = String.valueOf(lOperand);
                    calc[++top] = result;
                }
            } else if (opFields[i].equals("*")) {
                if (top < 1) {
                    // Handle error and throw exception
                } else {
                    rOperand = Double.parseDouble(calc[top--]);
                    lOperand = Double.parseDouble(calc[top--]);
                    lOperand *= rOperand;
                    result = String.valueOf(lOperand);
                    calc[++top] = result;
                }
            } else if (opFields[i].equals("/")) {
                if (top < 1) {
                    // Handle error and throw exception
                } else {
                    rOperand = Double.parseDouble(calc[top--]);
                    lOperand = Double.parseDouble(calc[top--]);
    
                    if (Math.abs(rOperand) < 0.005) {
                        // Treat as attempted division by zero
                    } else {
                        lOperand /= rOperand;
                    }
    
                    result = String.valueOf(lOperand);
                    calc[++top] = result;
                }
            } else { // Anything else is a number?
                calc[++top] = opFields[i];
            }
        }
    
        lOperand = Double.parseDouble(calc[top--]);
    
        if (lOperand > 0) {
            // Set operation to deposit
            operation = 1;
        } else if (lOperand < 0) {
            // Set operation to withdrawal
            lOperand = -lOperand;
            operation = 2;
        } else {
            // Set operation to create?
            operation = 0;
        }
    
        amount = lOperand;
        return operation;
    }

    
    // translates into normal language
    @Override
    public String toString() {
        String operationString;
        if (this.operation == 0) {
            operationString = "Create";
        } else if (this.operation == 1) {
            operationString = "Deposit";
        } else if (this.operation == 2) {
            operationString = "Withdraw";
        } else {
            operationString = "Error";
        }
        return "Transaction [accountNumber=" + accountNumber + ", operation=" + operationString + ", amount=" + amount + "]";
    }
}


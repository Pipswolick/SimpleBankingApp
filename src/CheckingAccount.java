package src;

import src.Account;

import java.time.LocalDate;
import java.time.LocalTime;

public class CheckingAccount extends Account {

    private Bank bank;
    public CheckingAccount(Bank bank, String accountNumber, String customerName, String customerId, float balance) {
        super(accountNumber, customerName, customerId, balance);
        this.bank = bank;
        //is it ok to use super here?yes as long as init bank separately
    }

    @Override
    public void deposit(float amount){
        if(amount > 0) {
            this.balance += amount;
            System.out.println("Deposit of $" + amount + "successful. New balance: $" + this.balance);
            //make transaction object here and add it to bank transaction list
            Transaction transaction = new Transaction(
                    amount,
                    "Deposit",
                    LocalDate.now(),
                    LocalTime.now(),
                    getAccountNumber(), // fromAccount
                    null,              // toAccount (null for deposits)
                    getCustomerId()
                    /// I PAUSED RIGHT HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    //ADD IT TO LIST!!!!!!!!!!!
            );
            bank.addTransaction(transaction);
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    @Override
    public void withdraw(float amount){
        if(amount > 0) {
            if (this.balance >= amount) {
                this.balance -= amount;
                System.out.println("Withdrawal of $" + amount + "successful. New balance: $" + this.balance);
                //make transaction object here and add it to bank transaction list
                Transaction transaction = new Transaction(
                        amount,
                        "Withdrawal",
                        LocalDate.now(),
                        LocalTime.now(),
                        getAccountNumber(), // fromAccount
                        null,              // toAccount
                        getCustomerId()
                        //ADD IT TO LIST!!!!!!!!!!!
                );
                bank.addTransaction(transaction);
            } else {
                System.out.println("Insufficient funds. Balance: $" + this.balance);
            }
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }

    }

}
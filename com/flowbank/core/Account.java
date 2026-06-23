package com.flowbank.core;

import com.flowbank.model.Customer;
import com.flowbank.exception.InsufficientFundsException;

public abstract class Account implements Cloneable {
    private double balance;
    private String accountNumber;
    private Customer owner;
    private static int totalAccountsCreated;

    static { System.out.println("[FlowBank Core Loaded]"); }
    { System.out.println("Account created at: " + System.currentTimeMillis()); }

    public Account(String accountNumber, Customer owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        totalAccountsCreated++;
    }

    public abstract String getAccountType();
    public abstract double calculateInterest();

    public void deposit(double amount) { balance += amount; }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(accountNumber, amount, balance);
        }
        balance -= amount;
    }

    public double getBalance() { return balance; }
    public static int getTotalAccountsCreated() { return totalAccountsCreated; }

    @Override public String toString() { return "Account: " + accountNumber; }
    @Override public int hashCode() { return accountNumber.hashCode(); }
    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;
        Account other = (Account) obj;
        return accountNumber.equals(other.accountNumber);
    }

    @Override public Account clone() throws CloneNotSupportedException {
        Account cloned = (Account) super.clone();
        cloned.owner = owner.clone(); // deep clone
        return cloned;
    }

    // Non-static inner class
    public class BalanceSnapshot {
        public double snapshot() { return balance; }
    }
}

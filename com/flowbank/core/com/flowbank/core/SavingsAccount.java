package com.flowbank.core;

import com.flowbank.model.Customer;

public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, Customer owner, double balance) {
        super(accountNumber, owner, balance);
    }

    @Override public String getAccountType() { return "Savings"; }
    @Override public double calculateInterest() { return getBalance() * 0.04; }
}

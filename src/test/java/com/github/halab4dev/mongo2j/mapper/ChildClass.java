package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonProperty;

/*
 *
 * @author halab
 */
public class ChildClass extends ParentClass {

    @BsonProperty("account_id")
    private String accountId;

    private double balance;

    public ChildClass() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

package model;

import util.FileUtil;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private double balance;

    private ArrayList<String> transactions;
    private int loginAttempts;
    private boolean locked;

    public User(String username, String password) {

        this.username = username;
        this.password = password;
        this.balance = 0.0;

        this.transactions = new ArrayList<>();

        this.loginAttempts = 0;
        this.locked = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {

        balance += amount;

        String message = "Deposited: ₹" + amount;

        transactions.add(message);

        FileUtil.saveTransaction(
                username,
                message);
    }

    public boolean withdraw(double amount) {

        if (amount > balance) {

            return false;
        }

        balance -= amount;

        String message = "Withdrawn: ₹" + amount;

        transactions.add(message);

        FileUtil.saveTransaction(
                username,
                message);

        return true;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void incrementLoginAttempts() {
        loginAttempts++;
    }

    public void resetLoginAttempts() {
        loginAttempts = 0;
    }

    public boolean isLocked() {
        return locked;
    }

    public void lockAccount() {
        locked = true;
    }
}
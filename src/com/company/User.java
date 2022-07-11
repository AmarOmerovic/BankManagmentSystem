package com.company;

import java.util.ArrayList;

public class User {
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private double userBalance;
    private ArrayList<Double> userTransactions;
    private int[] depOrWith;

    public User(String userFirstName, String userLastName) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = getUserPassword();
        this.userBalance = 0;
        this.userTransactions = new ArrayList<>();
        this.depOrWith = new int[1000];
    }

    public void setDepOrWith(int[] depOrWith) {
        this.depOrWith = depOrWith;
    }

    public int[] getDepOrWith() {
        return depOrWith;
    }

    public String getUserFirstName() {
        return userFirstName;
    }


    public String getUserLastName() {
        return userLastName;
    }


    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getUserPassword() {
        return userPassword;
    }


    public ArrayList<Double> getUserTransactions() {
        return userTransactions;
    }


    private boolean validAmount(double amount){
        return !(amount <= 0);
    }


    public boolean newTransaction(double amount){
        if (validAmount(amount)) {
            userTransactions.add(amount);
            return true;
        }else {
            return false;
        }
    }


    public boolean depositUserBalance(double amount) {
        if (amount > 0 && newTransaction(amount)){
            this.userBalance += amount;
            return true;
        }
        return false;
    }


    public boolean withdrawUserBalance(double amount) {
        if (this.userBalance >= amount && newTransaction(amount)){
            this.userBalance -= amount;
            return true;
        }
        return false;
    }


    public double getUserBalance() {
        return userBalance;
    }
}

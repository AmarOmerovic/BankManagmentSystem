package com.company;

import java.util.ArrayList;
import java.util.Locale;

public class Bank {
    private ArrayList<User> users;
    private int count;

    public Bank() {
        this.users = new ArrayList<>();
    }


    private User findUser(String userFirstName, String userLastName){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUserFirstName().equals(userFirstName.toUpperCase(Locale.ROOT)) && users.get(i).getUserLastName().equals(userLastName.toUpperCase(Locale.ROOT))){
                return users.get(i);
            }
        }
        return null;
    }


    private String findUserPassword(String userFirstName, String userLastName, String userPassword){
        User existingUser = findUser(userFirstName, userLastName);
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).equals(existingUser) && users.get(i).getUserPassword().equals(userPassword)){
                return users.get(i).getUserPassword();
            }
        }
        return null;
    }


    private boolean validUserPassword(String userPassword){
        if (userPassword.length() != 4){
            return false;
        }else {
            boolean allDigits = true;
            for (int i = 0; i < 4; i++){
                if (!Character.isDigit(userPassword.charAt(i))){
                    allDigits = false;
                    break;
                }
            }
            return allDigits;
        }
    }


    public boolean createNewUser(String userFirstName, String userLastName, String userPassword){
        User existingUser = findUser(userFirstName, userLastName);
        if (existingUser == null && validUserPassword(userPassword)){
            existingUser = new User(userFirstName.toUpperCase(Locale.ROOT), userLastName.toUpperCase(Locale.ROOT));
            existingUser.setUserPassword(userPassword);

            this.count = 0;

            users.add(existingUser);
            return true;
        }
        return false;
    }


    public boolean existingUser(String userFirstName, String userLastName, String userPassword){
        User existingUser = findUser(userFirstName, userLastName);
        String existingUserPassword = findUserPassword(userFirstName, userLastName, userPassword);
        return existingUser != null && existingUserPassword != null;
    }


    public void displayUser(String userFirstName, String userLastName, String userPassword){
        User existingUser = findUser(userFirstName, userLastName);
        String existingUserPassword = findUserPassword(userFirstName, userLastName, userPassword);
        if (existingUser != null && existingUserPassword != null){
            System.out.println("\tFirst name: " + existingUser.getUserFirstName());
            System.out.println("\tLast name: " + existingUser.getUserLastName());
            System.out.println("\tPassword: " + existingUser.getUserPassword());
        }
    }


    public boolean depositOrWithdraw(String userFirstName, String userLastName, String userPassword, double amount, int depOrWith){
        User existingUser = findUser(userFirstName, userLastName);
        String existingUserPassword = findUserPassword(userFirstName, userLastName, userPassword);
        if (existingUser != null && existingUserPassword != null){
            if ((depOrWith == 2 && existingUser.depositUserBalance(amount)) || (depOrWith == 3 && existingUser.withdrawUserBalance(amount))){
                int[] array = existingUser.getDepOrWith();
                array[count] = depOrWith;
                existingUser.setDepOrWith(array);
                count++;
                return true;
            }
        }
        return false;
    }


    public void accountBalance(String userFirstName, String userLastName, String userPassword){
        User existingUser = findUser(userFirstName, userLastName);
        String existingUserPassword = findUserPassword(userFirstName, userLastName, userPassword);
        if (existingUser != null && existingUserPassword != null){
            System.out.println("\tBalance: " + existingUser.getUserBalance());
        }
    }


    public void transactions(String userFirstName, String userLastName, String userPassword) {
        User existingUser = findUser(userFirstName, userLastName);
        String existingUserPassword = findUserPassword(userFirstName, userLastName, userPassword);
        if (existingUser != null && existingUserPassword != null) {
            if (existingUser.getUserTransactions().size() == 0){
                System.out.println("\t* No transactions were made! *");
            }else {
                int[] array = existingUser.getDepOrWith();
                for (int i = 0; i < existingUser.getUserTransactions().size(); i++) {
                    if (array[i] == 2) {
                        System.out.println("\t[" + (i + 1) + "]" + " Deposit amount: " + existingUser.getUserTransactions().get(i));
                    } else if (array[i] == 3) {
                        System.out.println("\t[" + (i + 1) + "]" + " Withdraw amount: " + existingUser.getUserTransactions().get(i));
                    }
                }
            }
        }
    }


    public boolean closeAccount(String userFirstName, String userLastName, String userPassword){
        User existingUser = findUser(userFirstName, userLastName);
        String existingUserPassword = findUserPassword(userFirstName, userLastName, userPassword);
        if (existingUser != null && existingUserPassword != null){
            users.remove(existingUser);
            existingUser.setUserPassword(null);
            return true;
        }
        return false;
    }
}

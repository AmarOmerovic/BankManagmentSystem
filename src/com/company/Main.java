package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {

        boolean quit = false;

        while (!quit){
            startUpMenu();
            System.out.print("\n\t- Enter your choice: ");
            int userInputOne = scanner.nextInt();
            scanner.nextLine();
            switch (userInputOne){
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    logIntoExistingAccount();
                    break;
                case 3:
                    System.out.println("\n\tQuitting...");
                    quit = true;
                    break;
                default:
                    System.out.println("\n\t* Wrong input! *");
            }
        }

    }


    public static void bmsSign(){
        System.out.println("\n\t\t*** BANK MANAGEMENT SYSTEM ***\n");
    }


    public static void startUpMenu(){
        bmsSign();
        System.out.println("\t1. CREATE NEW ACCOUNT");
        System.out.println("\t2. LOG INTO EXISTING ACCOUNT");
        System.out.println("\t3. QUIT");
    }


    public static void createNewAccount(){
        while (true) {
            bmsSign();
            System.out.println("\t* Create new account! *\n");
            System.out.println("\t* Fill the needed information's below!*");
            System.out.print("\tFirst name: ");
            String firstName = scanner.nextLine();
            System.out.print("\tLast name: ");
            String lastName = scanner.nextLine();
            System.out.println("\t* Password need to be exact four digits!*");
            System.out.print("\tPassword: ");
            String password = scanner.nextLine();
            System.out.println("\n\tCreating...");
            if (bank.createNewUser(firstName, lastName, password)) {
                System.out.println("\n\t* Account created successfully! *");
                break;
            } else {
                System.out.println("\n\t* Account was not created! *");
                System.out.println("\n\t* Please try again! *");

                System.out.println("\n\t* To go back press \"1\", to try again press any other number!*");
                System.out.print("\n\t- Enter your choice: ");
                int exit = scanner.nextInt();
                scanner.nextLine();
                if (exit == 1){
                    break;
                }
            }
        }
    }


    public static void logIntoExistingAccount(){
        boolean quit = false;

        while (!quit) {
            bmsSign();
            System.out.println("\t* Log into existing account! *\n");
            System.out.println("\t* Fill the needed information's below!*");
            System.out.print("\tFirst name: ");
            String firstName = scanner.nextLine();
            System.out.print("\tLast name: ");
            String lastName = scanner.nextLine();
            System.out.println("\t* Password need to be exact four digits!*");
            System.out.print("\tPassword: ");
            String password = scanner.nextLine();
            System.out.println("\n\tLogging in...");

            if (bank.existingUser(firstName, lastName, password)) {
                System.out.println("\n\t* Logged in successfully! *");
                boolean back = false;
                while (!back) {
                    mainMenu();
                    System.out.print("\n\t- Enter your choice: ");
                    int userInputTwo = scanner.nextInt();
                    scanner.nextLine();

                    switch (userInputTwo) {
                        case 1:
                            accountInfo(firstName, lastName, password);
                            break;
                        case 2: case 3:
                            depositOrWithdraw(firstName, lastName, password, userInputTwo);
                            break;
                        case 4:
                            transactions(firstName, lastName, password);
                            break;
                        case 5:
                            accountBalance(firstName, lastName, password);
                            break;
                        case 6:
                            if (closeAccount(firstName,lastName,password)){
                                System.out.println("\n\tClosing account....");
                                back = true;
                                quit = true;
                            }
                            break;
                        case 7:
                            if (logout()){
                                System.out.println("\n\tLogging out....");
                                back = true;
                                quit = true;
                            }
                            break;
                        default:
                            System.out.println("\n\t* Wrong input! *");
                            break;
                    }
                }
            } else {
                System.out.println("\n\t* Log in was unsuccessful! *");
                System.out.println("\t* Please try again! *");
                System.out.println("\n\t* To go back press \"1\", to try again press any other number! *");
                System.out.print("\n\t- Enter your choice: ");
                int exit = scanner.nextInt();
                scanner.nextLine();
                if (exit == 1){
                    quit = true;
                }
            }
        }
    }


    public static void mainMenu(){
        bmsSign();
        System.out.println("\t1. ACCOUNT INFO");
        System.out.println("\t2. DEPOSIT");
        System.out.println("\t3. WITHDRAW");
        System.out.println("\t4. TRANSACTIONS");
        System.out.println("\t5. ACCOUNT BALANCE");
        System.out.println("\t6. CLOSE ACCOUNT");
        System.out.println("\t7. LOG OUT");
    }


    public static void accountInfo(String firstName, String lasName, String password){
        while (true){
            bmsSign();
            System.out.println("\t* Account info! *\n");
            bank.displayUser(firstName, lasName, password);
            System.out.println("\n\t* To go back press \"1\"! *");
            System.out.print("\n\t- Enter your choice: ");
            int back = scanner.nextInt();
            scanner.nextLine();
            if (back == 1) {
                break;
            }
        }
    }


    public static boolean logout(){
        while (true){
            bmsSign();
            System.out.println("\t* Log out! *\n");
            System.out.println("\t* Are you sure you want to log out? *");
            System.out.println("\t* To log out press \"1\", to continue press \"0\"! *");
            System.out.print("\n\t- Enter your choice: ");
            int back = scanner.nextInt();
            scanner.nextLine();
            if (back == 1) {
                return true;
            }else if (back == 0){
                return false;
            }
        }
    }


    public static void depositOrWithdraw(String firstName, String lasName, String password, int userInput){
        while (true){
            bmsSign();
            if (userInput == 2) {
                System.out.println("\t* Deposit! *\n");

            }else if (userInput == 3) {
                System.out.println("\t* Withdraw! *\n");
            }
            System.out.println("\t* Fill the needed information's below!*");
            System.out.print("\t- Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (userInput == 2) {
                System.out.println("\n\t Depositing...");
            }else if (userInput == 3) {
                System.out.println("\n\t Withdrawing...");
            }

            if (bank.depositOrWithdraw(firstName,lasName,password,amount, userInput)){
                if (userInput == 2) {
                    System.out.println("\n\t* Deposit was successfully! *");
                }else if (userInput == 3) {
                    System.out.println("\n\t* Withdraw was successfully! *");
                }
                System.out.println("\n\t* To go back press \"1\", to make another any other number! *");
            }else {
                if (userInput == 2) {
                    System.out.println("\n\t* Deposit was unsuccessfully! *");
                }else if (userInput == 3) {
                    System.out.println("\n\t* Withdraw was unsuccessfully! *");
                }
                System.out.println("\n\t* To go back press \"1\", to try again press any other number! *");
            }

            System.out.print("\n\t- Enter your choice: ");
            int back = scanner.nextInt();
            scanner.nextLine();
            if (back == 1) {
                break;
            }

        }
    }


    public static void accountBalance(String firstName, String lasName, String password){
        while (true){
            bmsSign();
            System.out.println("\t* Account balance! *\n");
            bank.accountBalance(firstName, lasName, password);
            System.out.println("\n\t* To go back press \"1\"! *");
            System.out.print("\n\t- Enter your choice: ");
            int back = scanner.nextInt();
            scanner.nextLine();
            if (back == 1) {
                break;
            }
        }
    }


    public static void transactions(String firstName, String lasName, String password){
        while (true){
            bmsSign();
            System.out.println("\t* Transactions! *\n");
            System.out.println("\t* List of last 1000 transactions! *");
            bank.transactions(firstName, lasName, password);
            System.out.println("\n\t* To go back press \"1\"! *");
            System.out.print("\n\t- Enter your choice: ");
            int back = scanner.nextInt();
            scanner.nextLine();
            if (back == 1) {
                break;
            }
        }
    }


    public static boolean closeAccount(String firstName, String lasName, String password){
        while (true){
            bmsSign();
            System.out.println("\t* Close account! *\n");
            System.out.println("\t* Are you sure you want to close your account? *");
            System.out.println("\t* If yes than press \"1\", if no \"0\"! *");
            System.out.print("\n\t- Enter your choice: ");
            int back = scanner.nextInt();
            scanner.nextLine();

            if (back == 1 && bank.closeAccount(firstName, lasName, password)) {
                return true;
            }else if (back == 0){
                return false;
            }
        }
    }
}




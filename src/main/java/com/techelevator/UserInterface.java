package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.text.DecimalFormat;

public class UserInterface {
    private Inventory inventory;
    private UserInput user;
    private String mainMenuChoice;
    private String paymentMenuChoice;
    private String feedMoneyOutput; //For testing purposes
    private String productSelection;
    private String produceSelectionOutput; //For testing purposes
    private double totalBalance;
    private LocalDateTime currentDate = LocalDateTime.now();
    private DecimalFormat newFormat = new DecimalFormat("#.00");


    public UserInterface(Inventory inventory) {
        this.inventory = inventory; //Connects this class to inventory
        this.user = new UserInput(); //Allows class to have user input
    }

    public void getMainMenu() { //Displays main menu and takes user input
        String mainMenu = "\nPlease make your selection. (1, 2, or 3)\n" +
                "1. Display Vending Machine Items\n" +
                "2. Purchase\n" +
                "3. Exit\n";
        System.out.println(mainMenu);

        user.getUserInput();
        mainMenuChoice = user.getInput();
        mainMenuInteraction(mainMenuChoice);
    }

    public void mainMenuInteraction(String mainMenuChoice) { //Guides user to respective choice based on input
        boolean isValidInput = false;
        while (!isValidInput) {
            if (mainMenuChoice.equals("1")) {
                isValidInput = true;
                System.out.println(inventory.displayInventory());
                getMainMenu();
                //Prints out the inventory display and returns user to main menu
            } else if (mainMenuChoice.equals("2")) {
                isValidInput = true;
                getPaymentMenu();
                //Takes user to the payment menu
            } else if (mainMenuChoice.equals("3")) {
                isValidInput = true;
                System.out.println("Thank you for using the Vendo-Matic 800. Please vend with us again soon!");
                System.exit(0);
                //Thanks the user and exits the program
            } else {
                isValidInput = false;
                System.err.println("Input invalid. Please try again.");
                getMainMenu();
                user.getUserInput();
                this.mainMenuChoice = user.getInput();
                //If user inputs invalid character, let's user know and prompts for another input,
                //Loops until user input is valid
            }
        }
    }

    public void getPaymentMenu() { //Displays payment menu and takes user input
        String paymentMenu = "Please make your selection. (1, 2, or 3)\n" +
                "1. Feed Money\n" +
                "2. Select Product\n" +
                "3. Finish Transaction\n\n" +
                "Current balance is: " + newFormat.format(totalBalance);
        System.out.println(paymentMenu);

        user.getUserInput();
        paymentMenuChoice = user.getInput();
        paymentMenuInteraction(paymentMenuChoice);
    }

    public void paymentMenuInteraction(String paymentMenuChoice) { //Guides user to respective choice based on input
        boolean isValidInput = false;
        while (!isValidInput) {
            if (paymentMenuChoice.equals("1")) {
                isValidInput = true;
                System.out.println("How much money would you like to add? (Please use whole dollar amounts.) ");
                user.getUserInput();
                String insertMoney = user.getInput();
                feedMoney(insertMoney);
                //Prompts user to input a whole dollar amount to increase their balance
            } else if (paymentMenuChoice.equals("2")) {
                isValidInput = true;
                System.out.println(inventory.displayInventory());
                System.out.println("Please select a product based on its slot location: ");
                user.getUserInput();
                productSelection = user.getInput();
                selectProduct(productSelection);
                //Displays inventory, allows user to choose based on slot location, and receive a product
            } else if (paymentMenuChoice.equals("3")) {
                isValidInput = true;
                changeMaker(totalBalance);
                getMainMenu();
                //Gives user appropriate change and takes them back to the main menu
            } else {
                isValidInput = false;
                System.err.println("Input invalid. Please try again.");
                getPaymentMenu();
                //Let's user know their input was invalid and takes them back to the main menu
            }
        }
    }

    public void feedMoney(String insertMoney) {
        double newBalance = 0.0; //New balance used for log purposes
        try {
            if (Integer.parseInt(insertMoney) > 0) { //If input is a valid whole number, it is added to the balance
                newBalance = totalBalance + Integer.parseInt(insertMoney);
            } else { //If not valid, let's user know and takes them back to payment menu
                feedMoneyOutput = "Invalid payment. Please pay in a whole dollar amount.";
                System.err.println(feedMoneyOutput);
                getPaymentMenu();
            }
        } catch (NumberFormatException e) {
            feedMoneyOutput = "Invalid payment. Please pay in a whole dollar amount.";
            System.err.println(feedMoneyOutput);
            getPaymentMenu();
        }

        //Logs the feed money transaction into the audit log
        try (FileWriter forLog = new FileWriter("Log.txt", true);
             PrintWriter log = new PrintWriter(forLog)) {
            log.append(currentDate + " FEED MONEY " + " \\" + newFormat.format(totalBalance) + " \\" + newFormat.format(newBalance) + ".").println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        totalBalance = newBalance; //Total balance set to the new balance after it is logged
        System.out.println("Your total balance is now: " + newFormat.format(totalBalance));
        getPaymentMenu();
    }

    public void selectProduct(String productSelection) {
        double newBalance = 0; //New balance used for log purposes
        //The following loops through to see if user input equals any of the slot locations,
        // if it reaches last one and does not equal any up to the last one it outputs an invalid entry error
        for (int i = 0; i < inventory.getSlotLocation().length; i++) {
            if (productSelection.equals(inventory.getSlotLocation()[i])) {

                if (totalBalance < inventory.getPrice()[i]) { //Catches insufficient funds
                    this.produceSelectionOutput = "Insufficient funds. Please feed money and try again.";
                    System.err.println(produceSelectionOutput);
                    break;
                } else if (inventory.getStock()[i] == 0) { //Catches if someone tries to get something out of stock
                    this.produceSelectionOutput = "Insufficient stock. Please try another item or come back later.";
                    System.err.println(produceSelectionOutput);
                    break;
                }

                inventory.getStock()[i]--; //Decrements stock
                newBalance = totalBalance - inventory.getPrice()[i]; //Establishes new balance in machine

                this.produceSelectionOutput = inventory.getItemTypeSound(i);
                System.out.println(produceSelectionOutput); //Prints out the Crunch Crunch Yum nonsense

                try(FileWriter forLog = new FileWriter("Log.txt", true);
                    PrintWriter log = new PrintWriter(forLog)) {
                    //Logs a Get Product action in the Log.txt
                    log.append(currentDate + " " + inventory.getProductName()[i] + " " +
                            inventory.getSlotLocation()[i] +
                            " \\" + newFormat.format(totalBalance) + " \\" + newFormat.format(newBalance)).println();


                    totalBalance = newBalance; //Total balance set to the new balance after it is logged
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else if (!productSelection.equals(inventory.getSlotLocation()[i]) && (i == inventory.getSlotLocation().length - 1)) {
                this.produceSelectionOutput = "Invalid slot location. Please try again with a valid slot location.";
                System.err.println(produceSelectionOutput);
                break;
                //Catches if slot location is not valid and lets user know
            }
        }
        getPaymentMenu();
    }

    public String changeMaker(double remainingBalance) {
        String output = "";

        if (totalBalance == 0) { //Output if user does not have a balance at this point
            output = "You have a remaining balance of 0. No change to give.";
            System.out.println(output);
            return output;
        }

        double forInt = remainingBalance * 100; //Balance calculated to balance in pennies
        double forIntPrecision = Math.round(forInt); //Rounds as needed
        int forChange = ((int) forIntPrecision);
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;

        //Determines number of coins to be given & adds on to output
        output = "Your change is ";
        if ((forChange / 25) >= 1) {
            quarters = (forChange / 25);
            forChange = forChange - (quarters * 25);
            if (quarters > 0) {
                output += quarters + " quarter(s) ";
            }
        }
        if ((forChange / 10) >= 1) {
            dimes = (forChange / 10);
            forChange = forChange - (dimes * 10);
            if (dimes > 0) {
                output += dimes + " dime(s) ";
            }
        }
        if ((forChange / 5) >= 1) {
            nickels = forChange / 5;
            forChange = forChange - (nickels * 5);
            if (nickels > 0) {
                output += nickels + " nickel(s) ";
            }
        }
        if (forChange >= 0) {
            pennies = forChange;
            if (pennies > 0) {
                output += pennies + " pennie(s) ";
            }
        }

        output += "for $" + newFormat.format(remainingBalance) + ".";

        //Logs to file
        try (FileWriter forLog = new FileWriter("Log.txt", true);
             PrintWriter log = new PrintWriter(forLog)
        ) {
            Scanner openFile = new Scanner("Log.txt");
            log.append(currentDate + " GIVE CHANGE: " + newFormat.format(totalBalance) + " $0.00.\n\\`\\`\\`\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        resetBalance(); //Resets balance to 0 since user was given change back
        System.out.println(output);
        return output; //used for testing purposes
    }

    public void resetBalance(){
        this.totalBalance = 0;
    }

    public String getProductSelection() {
        return productSelection;
    }

    public String getFeedMoneyOutput() {
        return feedMoneyOutput;
    }

    public void setProductSelection(String productSelection) {
        this.productSelection = productSelection;
    }

    public String getProduceSelectionOutput() {
        return produceSelectionOutput;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double balance) {
        this.totalBalance = balance;
    }

    public UserInput getUser() {
        return user;
    }

    public void setUser(UserInput user) {
        this.user = user;
    }
}
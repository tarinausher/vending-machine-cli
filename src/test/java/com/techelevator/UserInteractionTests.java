package com.techelevator;

import org.junit.*;
import org.mockito.*;

public class UserInteractionTests {


    @Test
    public void change_Maker_Test_valid() {
        FileReader testFile = new FileReader("vendingmachine.csv");
        Inventory testInventory = new Inventory(testFile);
        UserInterface testInterface = new UserInterface(testInventory);

        testInterface.setTotalBalance(15.00);
        int quarters = (int)(testInterface.getTotalBalance() * 100)/25;

        Assert.assertEquals("Your change is " + quarters + " quarter(s) " + "for $15.00.", testInterface.changeMaker(15.00));
    }

//    @Test
//    public void feed_Money() {
//        FileReader testFile = new FileReader("vendingmachine.csv");
//        Inventory testInventory = new Inventory(testFile);
//        UserInterface testInterface = new UserInterface(testInventory);
//
//        testInterface.feedMoney("12");
//
//        Assert.assertEquals(12.00, testInterface.getTotalBalance(), 0);
//    }











}
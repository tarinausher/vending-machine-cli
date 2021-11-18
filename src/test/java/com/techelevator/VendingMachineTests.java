package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTests {

    @Test
    public void initializing_Vending_Machine_User_Interface_Access() {
        VendingMachine vendingMachineTest = new VendingMachine("vendingmachine.csv");

        vendingMachineTest.getUserInterface().setProductSelection("C4");
        String valueTest = vendingMachineTest.getUserInterface().getProductSelection();

        Assert.assertEquals("C4", valueTest);
    }

//    @Test
//    public void feedMoney_invalid() {
//        VendingMachine vendingMachineTest = new VendingMachine("vendingmachine.csv");
//
//        vendingMachineTest.getUserInterface().feedMoney("12");
//
//
//        Assert.assertEquals(12.00, vendingMachineTest.getUserInterface().getTotalBalance(), 0.0);
//    } //Attempted test, but never stops running due to feedMoney() calling getPaymentMenu() within method & prompting user input

//    @Test
//    public void selectProduct_InsufficientFunds() {
//
//        VendingMachine vendingMachineTest = new VendingMachine("vendingmachine.csv");
//
//
//        vendingMachineTest.getUserInterface().setTotalBalance(0);
//
//        vendingMachineTest.getUserInterface().setProductSelection("A1");
//        String testSelect = vendingMachineTest.getUserInterface().getProductSelection();
//        vendingMachineTest.getUserInterface().selectProduct(testSelect);
//
//        Assert.assertEquals("Insufficient funds. Please feed money and try again.", vendingMachineTest.getUserInterface().getProduceSelectionOutput());
//
//    } //Attempted test, but never stops running due to selectProduct() calling getPaymentMenu() within method & prompting user input

}

package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTests {
    @Test
    public void slot_Breakdown_Test() {
        FileReader testFile = new FileReader("vendingmachine.csv");
        Inventory testInventory = new Inventory(testFile);
        testInventory.inventorySorter();

        String[] testArray = new String[] {"A1", "A2", "A3", "A4",
                "B1", "B2", "B3", "B4",
                "C1", "C2", "C3", "C4",
                "D1", "D2", "D3", "D4"};

        Assert.assertArrayEquals(testArray, testInventory.getSlotLocation());
    }

    @Test
    public void price_Breakdown_Test() {
        FileReader testFile = new FileReader("vendingmachine.csv");
        Inventory testInventory = new Inventory(testFile);
        testInventory.inventorySorter();

        Double[] testPriceArray = new Double[] {3.05, 1.45, 2.75, 3.65,
                1.80, 1.50, 1.50, 1.75,
                1.25, 1.50, 1.50, 1.50,
                0.85, 0.95, 0.75, 0.75};

        Assert.assertArrayEquals(testPriceArray, testInventory.getPrice());
    }

    @Test
    public void product_Breakdown_Test() {
        FileReader testFile = new FileReader("vendingmachine.csv");
        Inventory testInventory = new Inventory(testFile);
        testInventory.inventorySorter();

        String[] testProductArray = new String[] {"Potato Crisps", "Stackers", "Grain Waves", "Cloud Popcorn",
                "Moonpie", "Cowtales", "Wonka Bar", "Crunchie",
                "Cola", "Dr. Salt", "Mountain Melter", "Heavy",
                "U-Chews", "Little League Chew", "Chiclets", "Triplemint"};

        Assert.assertArrayEquals(testProductArray, testInventory.getProductName());
    }

    @Test
    public void type_Breakdown_Test() {
        FileReader testFile = new FileReader("vendingmachine.csv");
        Inventory testInventory = new Inventory(testFile);
        testInventory.inventorySorter();

        String[] testTypeArray = new String[] {"Chip", "Chip", "Chip", "Chip",
                "Candy", "Candy", "Candy", "Candy",
                "Drink", "Drink", "Drink", "Drink",
                "Gum", "Gum", "Gum", "Gum"};

        Assert.assertArrayEquals(testTypeArray, testInventory.getType());
    }


    @Test
    public void display_Sold_Out() {
        FileReader testFile = new FileReader("vendingmachine.csv");
        Inventory testInventory = new Inventory(testFile);
        testInventory.inventorySorter();

        testInventory.getStock()[1] = 0;


        Assert.assertEquals("A2 Stackers is SOLD OUT.",
                testInventory.getSlotLocation()[1] + " " + testInventory.getProductName()[1] + " is SOLD OUT.");
    }

    @Test
    public void display_Product_Sound() {
        FileReader testFile = new FileReader("vendingmachine.csv");
        Inventory testInventory = new Inventory(testFile);
        testInventory.inventorySorter();

        String testSound = testInventory.getItemTypeSound(2);

        Assert.assertEquals("Crunch Crunch, Yum!", testSound);
    }
}



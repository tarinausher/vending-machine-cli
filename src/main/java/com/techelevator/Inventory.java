package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private FileReader fileReader;
    private String[] slotLocation;
    private String[] productName;
    private Double[] price;
    private String[] type;
    private int[] stock;
    DecimalFormat newFormat = new DecimalFormat("#.00");



    public Inventory(FileReader fileReader) {
        this.fileReader = fileReader;

    }

    public void inventorySorter() {
        int arraySize = fileReader.getInventoryList().size(); //Array size based on size of the number of lines in the file

        this.slotLocation = new String[arraySize];
        this.productName = new String[arraySize];
        this.price = new Double[arraySize];
        this.type = new String[arraySize];
        this.stock = new int[arraySize];


        for (int i = 0; i < arraySize; i++) {
            String[] pieces = fileReader.getInventoryList().get(i).split("\\|");
            slotLocation[i] = pieces[0];
            productName[i] = pieces[1];
            price[i] = Double.parseDouble(pieces[2]);
            type[i] = pieces[3];
            stock[i] = 5;
        }
    }

    public String displayInventory() {
        String productDisplay = "";
        for (int i = 0; i < fileReader.getInventoryList().size(); i++) {
            if (stock[i] == 0) {
                productDisplay += slotLocation[i] + " " + productName[i] + " is SOLD OUT.\n";

            } else {
                productDisplay += slotLocation[i] + " " + productName[i] + " for $" + newFormat.format(price[i]) + "\n";
            }
        }
        return productDisplay;
    }

    public String getItemTypeSound(int index) {
        String output = "";
        if (type[index].equals("Chip")) {
            output = "Crunch Crunch, Yum!";
        } else if (type[index].equals("Candy")) {
            output = "Munch Munch, Yum!";
        } else if (type[index].equals("Drink")) {
            output = "Glug Glug, Yum!";
        } else if (type[index].equals("Gum")) {
            output = "Chew Chew, Yum!";
        }
        return output;
    }


    public String[] getSlotLocation() {
        return slotLocation;
    }

    public String[] getProductName() {
        return productName;
    }

    public Double[] getPrice() {
        return price;
    }

    public String[] getType() {
        return type;
    }

    public int[] getStock() {
        return stock;
    }
}

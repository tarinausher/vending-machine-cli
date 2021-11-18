package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private File vendingMachineFile;
    private List<String> inventoryList;

    public FileReader(String fileName) {
        vendingMachineFile = new File(fileName);
        inventoryList = new ArrayList<>();

        try (Scanner dataInput = new Scanner(vendingMachineFile)) {

            while (dataInput.hasNextLine()) {
                inventoryList.add(dataInput.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found. Please try again.");
        }
    }

    public File getVendingMachineFile() {
        return vendingMachineFile;
    }

    public List<String> getInventoryList() {
        return inventoryList;
    }

}







package com.techelevator;

public class VendingMachine {

    FileReader fileReader;
    Inventory inventory;
    UserInterface userInterface;

    public VendingMachine(String fileName) {
        this.fileReader = new FileReader(fileName);
        this.inventory = new Inventory(fileReader);
        this.inventory.inventorySorter();
        this.userInterface = new UserInterface(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }
}

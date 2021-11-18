package com.techelevator;

// Vending Machine Command Line Interface application
public class VendingMachineCLI {

	public static void main(String[] args) {

		VendingMachine vendomatic800 = new VendingMachine("vendingmachine.csv"); //Creating new vending machine

		System.out.println("Welcome to the Vendo-Matic 800!");

		vendomatic800.getUserInterface().getMainMenu(); //Accesses main menu

		//Program ends when user



	}
}



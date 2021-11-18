package com.techelevator;

import java.util.Scanner;

public class UserInput {
    private String input;
    private Scanner keyboard;

    public UserInput() {
        input = "";
        keyboard = new Scanner(System.in);
    }

    public String getUserInput(){
        this.input = keyboard.nextLine();
        return input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}

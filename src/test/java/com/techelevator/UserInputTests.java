package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class UserInputTests {
    @Test
    public void userInput() {
        UserInput testUser = new UserInput();

//        testUser.getUserInput(); Can't test this or else the test runs forever due to anticipating user input
        testUser.setInput("test");

        Assert.assertEquals("test", testUser.getInput());
    } //This is testing setters and getters, but there's really nothing else to test in this class

}

package com.techelevator;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderTests {

    @Test
    public void fileReader_withFile() {
        FileReader testFile = new FileReader("vendingmachine.csv");

        try (Scanner fileInput = new Scanner(testFile.getVendingMachineFile())) {
            int lineNumber = 0;
            while (fileInput.hasNextLine()) {
                String lineOfInput = fileInput.nextLine();
                Assert.assertEquals(lineOfInput, testFile.getInventoryList().get(lineNumber));
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


    }

    @Test
    public void fileReader_withNoFile() {
        FileReader testFile = new FileReader("not a file");

        boolean isNotFile = true;
        if (testFile.getVendingMachineFile().isFile()) {
            isNotFile = false;
        }

        Assert.assertEquals(!testFile.getVendingMachineFile().isFile(), isNotFile);
    }
}




package com.company;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Read from console or file(c/f):");
        Scanner mainScanner = new Scanner(System.in);
        String text = new String();
        if(mainScanner.nextLine().equals("c")) {
            System.out.println("Input text:");
            text = mainScanner.nextLine();
        } else {
            try {
                File inputFile = new File("Input.txt");
                Scanner fileScanner = new Scanner(inputFile);
                while(fileScanner.hasNextLine()) {
                    text += fileScanner.nextLine();
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File is not found");
            }
        }
        text = TaskMaker.Do(text);
        System.out.println("Write to console or file(c/f):");
        if(mainScanner.nextLine().equals("c")) {
            System.out.println(text);
        } else {
            try {
                File outputFile = new File("Output.txt");
                FileWriter fileWriter = new FileWriter(outputFile);
                fileWriter.write(text);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error happened");
            }
        }
    }
}
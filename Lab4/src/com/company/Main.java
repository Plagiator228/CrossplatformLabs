package com.company;

import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.BreakIterator;
import java.util.Locale;

public class Main {

    public static void task(Scanner scanner) {
        List<String> words = new LinkedList<String>();
        String text;
        StringTokenizer tokenizer;
        while(scanner.hasNextLine()) {
            tokenizer = new StringTokenizer(scanner.nextLine());
            while (tokenizer.hasMoreTokens()) {
                words.add(tokenizer.nextToken());
            }
        }
        scanner.close();
        text = words.get(0);
        for(int i = 1; i < words.size(); i++)
            text += " " + words.get(i);
        List<String> sentences = new LinkedList<String>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(text);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            sentences.add(text.substring(start,end));
        }
        List<String> rightEmail = new LinkedList<String>();
        List<String> mayBeEmail = new LinkedList<String>();
        String punctuations = ".,:;!?";
        String word;
        for(String s : words) {
            if(s.contains("@")) {
                if(punctuations.contains(s.substring(s.length() - 1)))
                    word = s.substring(0, s.length()-1);
                else
                    word = s;
                if(EmailChecker.CheckEmail(word)) {
                    rightEmail.add(word);
                    System.out.print("Right email: " + rightEmail.get(rightEmail.size() - 1) + "\n");
                } else
                    mayBeEmail.add(word);
            }
        }
        for(String s : mayBeEmail) {
            System.out.print("May be email: " + s + "\n");
        }
        for(int i = 0; i < sentences.size() - 1; i++) {
            for(String email : rightEmail) {
                if(sentences.get(i).contains(email)) {
                    sentences.set(i, sentences.get(sentences.size() - 1));
                    break;
                }
            }
        }
        System.out.println("Result of changing: ");
        for (String s : sentences) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        File file1 = new File("en.txt");
        File file2 = new File("ua.txt");
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file1);
            Scanner scanner = new Scanner(inputStream);
            task(scanner);
            inputStream = new FileInputStream(file2);
            scanner = new Scanner(inputStream);
            task(scanner);
        } catch(FileNotFoundException e) {
            System.out.println("File is not found!");
        }
    }
}

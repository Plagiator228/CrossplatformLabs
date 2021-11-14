package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library a = new Library("input.json");
        int userChoice = menu();
        while(true) {
            switch(userChoice) {
                case 1:
                    a.sortByYear();
                    System.out.println(a.getAllAvailableBooks());
                    break;
                case 2:
                    System.out.println(a.get2BooksEmails()+"\n\n");
                    break;
                case 3:
                    Scanner input = new Scanner(System.in);
                    System.out.println("Enter author's name: ");
                    String auth = input.nextLine();
                    System.out.println("There are " + a.authorCount(auth) + " books written by " + auth + " borrowed from our library");
                    break;
                case 4:
                    System.out.println("Most books taken by a single person is " + a.largestBookCount() + "\n\n");
                    break;
                case 5:
                    a.newsLetters();
                    break;
                case 6:
                    a.debtors();
                    break;
                case 7:
                    a.printAllTickets();
                    break;
                case 8:
                    a.lendBook();
                    System.out.println("Book successfully lended!\n\n");
                    break;
                case 9:
                    a.returnBook();
                    System.out.println("Book successfully returned");
                    break;
                case 10:
                    a.save();
                    break;
            }
            userChoice = menu();
        }
    }

    public static int menu() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Input what you want to do");
        System.out.println("1 - Sort by year of publishing");
        System.out.println("2 - Get emails of users who have more than 2 books");
        System.out.println("3 - Check how many people borrowed books by selected author");
        System.out.println("4 - Most books taken by a person");
        System.out.println("5 - Newsletter");
        System.out.println("6 - Get all debtors");
        System.out.println("7 - Print all tickets");
        System.out.println("8 - Lend a book");
        System.out.println("9 - Return a book");
        System.out.println("10 - Save");
        selection = input.nextInt();
        return selection;
    }
}

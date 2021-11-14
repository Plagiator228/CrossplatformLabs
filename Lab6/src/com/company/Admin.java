package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    public int[] giveBook(ArrayList<SeasonTicket> tickets, ArrayList<Book> books) {
        Scanner input = new Scanner(System.in);
        System.out.println("Select a person to whom you'd want to lend a book(enter their number): ");
        for(int i=0; i<tickets.size(); i++) {
            System.out.println("#" + i +"   " + tickets.get(i).getInfo());
        }
        int ticketID  = input.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Select a book you'd like to lend(enter it's number): ");
        for(int i=0; i<books.size(); i++) {
            System.out.println("#" + i +"   " + books.get(i).getInfo());
        }
        int bookID = input.nextInt();
        System.out.println("Enter term(in days): ");
        int term = input.nextInt();
        return new int[] {ticketID, bookID, term};
    }

    public Book returnBook(ArrayList<SeasonTicket> tickets) {
        Scanner input = new Scanner(System.in);
        System.out.println("Select a person to who would like to return a book(enter their number): ");
        for(int i=0; i<tickets.size(); i++) {
            System.out.println("#" + i +"   " + tickets.get(i).getInfo());
        }
        int ticketID  = input.nextInt();
        ArrayList<Book> newArrayList = new ArrayList<>(tickets.get(ticketID).getBooks());
        System.out.println("Enter number of a book you'd like to return: ");
        for(int i=0; i<newArrayList.size(); i++) {
            System.out.println("#" + i +"   " + newArrayList.get(i).getInfo());
        }
        int bookID  = input.nextInt();
        var tempBook = newArrayList.get(bookID);
        tempBook.BeReturned();
        newArrayList.remove(bookID);
        tickets.get(ticketID).setBooks(newArrayList);
        return tempBook;
    }
}
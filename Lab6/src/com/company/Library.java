package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private ArrayList<Book> AllBooks;
    private ArrayList<SeasonTicket> AllTickets;
    public Admin admin;

    public Library() {
        AllBooks = new ArrayList<Book>();
        AllTickets = new ArrayList<SeasonTicket>();
        admin = new Admin();
    }

    public Library(String fileName) {
        this();
        Path filePath =  Path.of(fileName);
        JSONObject inputObject;
        try {
            String input =  Files.readString(filePath);
            inputObject = new JSONObject(input);
            JSONArray AvailableBooks = inputObject.getJSONArray("books");
            JSONArray JsonTickets = inputObject.getJSONArray("season tickets");
            for(int i = 0; i<JsonTickets.length(); i++) {
                JSONObject singleTicket = JsonTickets.getJSONObject(i);
                AllTickets.add(getSingleTicket(singleTicket));
            }
            for(int i = 0; i<AvailableBooks.length(); i++) {
                JSONObject singleBook = AvailableBooks.getJSONObject(i);
                AllBooks.add(getSingleBook(singleBook));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAllTickets() {
        for(var a: AllTickets) {
            System.out.println(a.toString());
        }
    }

    public ArrayList<String> get2BooksEmails() {
        return AllTickets.stream().filter((e)->e.getBooks().size()>=2).map(SeasonTicket::getEmail).collect(Collectors.toCollection(ArrayList::new));
    }

    public int authorCount(String author) {
        return (int) AllTickets.stream().filter(e->e.getBooks().stream().anyMatch(e2->e2.getAuthor().equals(author))).count();
    }

    public int largestBookCount() {
        return AllTickets.stream().mapToInt(e->e.getBooks().size()).max().orElseThrow(NoSuchElementException::new);
    }

    public void newsLetters() {
        List<SeasonTicket> less2 = AllTickets.stream().filter(e->e.getBooks().size()<2).collect(Collectors.toList());
        System.out.println("These users should check out our latest and greatest books!");
        for(SeasonTicket a : less2) {
            System.out.println(a.toString());
        }
        System.out.println("------------------------------------------\n");
        System.out.println("These users should not forget to return the books");
        List<SeasonTicket> others = AllTickets.stream().filter(e->e.getBooks().size()>=2).collect(Collectors.toList());
        for(SeasonTicket a : others) {
            System.out.println(a.toString());
        }
    }

    public void debtors() {
        System.out.println("Overdues: ");
        var a = AllTickets.stream().
                filter(e->e.getBooks().stream().anyMatch(e1->e1.getExpectedReturnDate()<System.currentTimeMillis())).
                map(SeasonTicket::getName).collect(Collectors.toList());
        var b = AllTickets.stream().
                filter(
                        e->
                                e.getBooks().stream().anyMatch(
                                        e1->
                                                e1.getExpectedReturnDate()<System.currentTimeMillis())).
                map(SeasonTicket::getDebtBooks).map(
                        e->
                                e.stream().map(
                                                e1->
                                                        e1.getInfo()+" Overdue: " + (int)Math.floor((System.currentTimeMillis()-e1.getExpectedReturnDate())/1000/60/60/24)).
                                        collect(Collectors.toCollection(ArrayList::new))).
                collect(Collectors.toCollection(ArrayList::new));
        for(int i=0; i<a.size(); i++) {
            System.out.println(a.get(i));
            for(int j=0; j<b.get(i).size(); j++) {
                System.out.println(b.get(i).get(j)+"\n");
            }
            System.out.println("\n\n");
        }
    }

    public String getAllAvailableBooks() {
        StringBuilder s = new StringBuilder();
        for(Book book : AllBooks) {
            s.append(book.getInfo()+"\n");
        }
        return s.toString();
    }

    public void sortByYear() {
        AllBooks = new ArrayList<Book>(AllBooks.stream().sorted((b1, b2) -> b1.getYear() - b2.getYear()).collect(Collectors.toList()));
    }

    private SeasonTicket getSingleTicket(JSONObject singleTicket) {
        ArrayList<Book> books = new ArrayList<Book>();
        JSONArray jsonbooks = singleTicket.getJSONArray("books");
        for(int i=0; i<jsonbooks.length(); i++) {
            JSONObject temp = jsonbooks.getJSONObject(i);
            books.add(getSingleBook(temp));
        }
        return new SeasonTicket(
                singleTicket.getString("first_name"),
                singleTicket.getString("last_name"),
                singleTicket.getString("middle_name"),
                singleTicket.getString("email"),
                books
        );
    }

    private Book getSingleBook(JSONObject singleBook) {
        ArrayList<Date> PickupDate = new ArrayList<Date>();
        ArrayList<Date> ExpectedReturnDate = new ArrayList<Date>();
        ArrayList<Date> ActualReturnDate = new ArrayList<Date>();
        JSONArray datesArray = singleBook.getJSONArray("dates");
        for(int i=0; i<datesArray.length(); i++) {
            JSONObject singleDate = datesArray.getJSONObject(i);
            PickupDate.add(new Date(singleDate.getLong("pickup_date")*1000));
            ExpectedReturnDate.add(new Date(singleDate.getLong("expected_return_date")*1000));
            if(singleDate.has("actual_return_date"))
                ActualReturnDate.add(new Date(singleDate.getLong("actual_return_date")*1000));
        }
        return new Book(
                singleBook.getString("title"),
                singleBook.getString("author"),
                singleBook.getInt("publish_year"),
                PickupDate,
                ExpectedReturnDate,
                ActualReturnDate
        );
    }

    public void lendBook() {
        int[] choices = admin.giveBook(AllTickets, AllBooks);
        AllBooks.get(choices[1]).BeGiven(choices[2]);
        var tempBook = AllBooks.get(choices[1]);
        AllTickets.get(choices[0]).borrowBook(tempBook);
        AllBooks.remove(choices[1]);
    }

    public void returnBook() {
        AllBooks.add(admin.returnBook(AllTickets));
    }

    public void save() {
        JSONArray booksArray = new JSONArray();
        JSONArray ticketsArray = new JSONArray();
        for(var book : AllBooks){
            booksArray.put(book.getJSONObject());
        }
        for(var ticket: AllTickets){
            ticketsArray.put(ticket.getJSONObject());
        }
        JSONObject obj = new JSONObject();
        obj.put("season tickets", ticketsArray);
        obj.put("books", booksArray);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("out.json"));
            writer.write( obj.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SeasonTicket {
    private String FirstName;
    private String LastName;
    private String MiddleName;
    private String Email;
    private ArrayList<Book> Books;

    public SeasonTicket(String FirstName, String LastName, String MiddleName, String Email, ArrayList<Book> Books) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.MiddleName = MiddleName;
        this.Email = Email;
        this.Books = new ArrayList<Book>(Books);
    }

    @Override
    public String toString() {
        return String.format("SEASON TICKET First name: %-12s Last name: %-12s Middle name: %-12s, email: %12s\n\n",
                FirstName, LastName, MiddleName, Email) + this.getListOfBooks();
    }

    public String getInfo() {
        return String.format("SEASON TICKET First name: %-12s Last name: %-12s Middle name: %-12s, email: %12s",
                FirstName, LastName, MiddleName, Email);
    }

    public void TakeBook(Book newBook) {
        Books.add(newBook);
    }

    public Book ReturnBook(int index) {
        Book tempBook = Books.get(index);
        Books.remove(index);
        return tempBook;
    }

    public ArrayList<Book> getBooks() {
        return Books;
    }

    public ArrayList<Book> getDebtBooks() {
        return Books.stream().filter(e->e.getExpectedReturnDate()<System.currentTimeMillis()).collect(Collectors.toCollection(ArrayList::new));
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return FirstName + " " + LastName + " " + MiddleName;
    }

    public void setBooks(ArrayList<Book> Books) {
        this.Books = new ArrayList<>(Books);
    }

    public String getListOfBooks() {
        StringBuilder res = new StringBuilder();
        for(Book book : Books){
            res.append(book.toString()+"\n");
        }
        return res.toString();
    }

    public void borrowBook(Book newBook) {
        Books.add(newBook);
    }

    public void returnBook(int id) {
        Books.remove(id);
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray jsonbooks = new JSONArray();
        for(var book : Books) {
            jsonbooks.put(book.getJSONObject());
        }
        try {
            obj.put("first_name", FirstName);
            obj.put("last_name", LastName);
            obj.put("middle_name", MiddleName);
            obj.put("email", Email);
            obj.put("books", jsonbooks);
        } catch (JSONException e) {}
        return obj;
    }
}

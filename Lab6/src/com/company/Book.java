package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;

public class Book {
    private String Title;
    private String Author;
    private int PublishYear;
    private ArrayList<Date> PickupDate;
    private ArrayList<Date> ExpectedReturnDate;
    private ArrayList<Date> ActualReturnDate;

    public Book(String Title, String Author, int PublishYear, ArrayList<Date> PickupDate,
                ArrayList<Date> ExpectedReturnDate, ArrayList<Date> ActualReturnDate) {
        this.Title = Title;
        this.Author = Author;
        this.PublishYear = PublishYear;
        this.PickupDate = new ArrayList<Date>(PickupDate);
        this.ExpectedReturnDate = new ArrayList<Date>(ExpectedReturnDate);
        this.ActualReturnDate = new ArrayList<Date>(ActualReturnDate);
    }

    @Override
    public String toString() {
        return "BOOK Author: " + Author + " Title: " + Title + ", published in " + PublishYear+"\n" +
                getDates();
    }

    public String getDates() {
        StringBuilder res = new StringBuilder();
        for(int i=0; i<this.PickupDate.size(); i++) {
            res.append(String.format("Date lended: %-15tF", PickupDate.get(i)));
            res.append(String.format("Expected return date: %-15tF", ExpectedReturnDate.get(i)));
            if(i<=ActualReturnDate.size()-1)
                res.append(String.format("Return date: %-15tF", ActualReturnDate.get(i)));
            res.append("\n");
        }
        return res.toString();
    }

    public void BeGiven(int Days) {
        PickupDate.add(new Date(System.currentTimeMillis()));
        ExpectedReturnDate.add(new Date(System.currentTimeMillis()+1000*60*60*24*Days));
    }

    public void BeReturned() {
        ActualReturnDate.add(new Date(System.currentTimeMillis()));
    }

    public String getAuthor() {
        return Author;
    }

    public String getInfo() {
        return "BOOK Author: " + Author + " Title: " + Title + ", published in " + PublishYear;
    }

    public long getExpectedReturnDate() {
        return ExpectedReturnDate.get(ExpectedReturnDate.size()-1).getTime();
    }

    public int getYear() {
        return this.PublishYear;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray dateArray = new JSONArray();
        for (int i=0; i < PickupDate.size(); i++) {
            JSONObject temp = new JSONObject();
            temp.put("pickup_date", PickupDate.get(i).getTime()/1000);
            temp.put("expected_return_date", ExpectedReturnDate.get(i).getTime()/1000);
            if(i<ActualReturnDate.size())
                temp.put("actual_return_date", ActualReturnDate.get(i).getTime()/1000);
            dateArray.put(temp);
        }
        try {
            obj.put("title", Title);
            obj.put("author", Author);
            obj.put("publish_year", PublishYear);
            obj.put("publish_year", PublishYear);
            obj.put("dates", dateArray);
        } catch (JSONException e) {}
        return obj;
    }
}

package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskMaker {
    static String regex = "\\b(?i)\\s*(a|an|the|or|are|on|in|out)\\b";

    public static String Do(String input) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if(input.length() == 0)
            input = "Text is empty";
        else {
            if(!m.find())
                System.out.println("Text not contains tokens");
            else {
                input = input.replaceAll("\\s{2}", " ");
                input = input.replaceAll(regex, "");
            }
        }
        return input;
    }
}

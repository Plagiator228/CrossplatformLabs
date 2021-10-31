package com.company;

import java.util.StringTokenizer;

public class EmailChecker {
    private static String[] badSymbols = new String[] {"!", "#", "$", "%", "^", "&", "*", "(", ")", "/", "~", "[", "]", "__", ".."};

    public static boolean CheckEmail(String s) {
        if(s.indexOf("@") != s.lastIndexOf("@") || s.indexOf("@") == 0 || s.indexOf("@") == (s.length()-1))
            return false;
        for (String symbols : badSymbols) {
            if(s.contains(symbols))
                return false;
        }
        for(int i = 0; i < s.length(); i++) {
            if(Character.UnicodeBlock.of(s.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return false;
            }
        }
        StringTokenizer tokenizer = new StringTokenizer(s, "@");
        String name = tokenizer.nextToken();
        String domain = tokenizer.nextToken();
        if(name.length() > 64 || domain.length() > 254 || !domain.contains("."))
            return false;
        return true;
    }
}

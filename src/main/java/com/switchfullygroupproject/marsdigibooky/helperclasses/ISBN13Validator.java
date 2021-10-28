package com.switchfullygroupproject.marsdigibooky.helperclasses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// CODEREVIEW I hope you found this regular expression on the internet :). It is common courtesy to mention where you fond it!
public class ISBN13Validator {
    private static final String regex =
            "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";

    public static boolean isValidISBN(String isbn) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(isbn);
        return matcher.matches();
    }
}

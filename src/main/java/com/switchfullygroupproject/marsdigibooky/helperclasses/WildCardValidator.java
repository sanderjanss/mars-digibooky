package com.switchfullygroupproject.marsdigibooky.helperclasses;

public class WildCardValidator {

    // CODEREVIEW I am assuming you found this code on the internet? If not, very creative use of recursion
    // if you did, be sure to mention where you found it
    // also, try searching for a library to include as a maven dependency instead of copying code from the internet
    public static boolean match(String first, String second) {

        if (first.length() == 0 && second.length() == 0)
            return true;


        if (first.length() > 1 && first.charAt(0) == '*' &&
                second.length() == 0)
            return false;

        if ((first.length() > 1 && first.charAt(0) == '?') ||
                (first.length() != 0 && second.length() != 0 &&
                        first.charAt(0) == second.charAt(0)))
            return match(first.substring(1),
                    second.substring(1));


        if (first.length() > 0 && first.charAt(0) == '*')
            return match(first.substring(1), second) ||
                    match(first, second.substring(1));
        return false;
    }


}

package org.example.library;

import java.util.Scanner;

public class Utils {
    public static char getAnswer(String menuChoices) {
        System.out.println("What do you want to do? ");
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            String userAns = scanner.nextLine();
            userAns = userAns.toUpperCase();
            if (userAns.length() == 1) {
                for (int i = 0; i < menuChoices.length(); i++) {
                    if (userAns.charAt(0) == menuChoices.charAt(i)) {
                        return userAns.charAt(0);
                    }
                }
            }
            System.out.println("that is not a valid input, try again");
        }
        throw new RuntimeException("something went wrong in getting the answer");
    }
    public enum PlayoffRound {
        QUARTERFINALS,
        SEMIFINALS,
        FINAL,
        FINISHED;
    }
}

package Helpers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;



public class Helper {
    public static String validateStringInput(Scanner scanner, String prompt, String errorMsg) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.next().trim();
            if (!input.isEmpty() && !input.matches(".*\\d+.*") && !input.isBlank()) {
                return input;
            } else {
                System.out.println(errorMsg);
            }
        }
    }

    public static int validateIntInput(Scanner scanner, String prompt, String errorMsg) {
        int number;
        do {
            System.out.println(prompt);
            String input = scanner.next().trim();

            try {
                number = Integer.parseInt(input);
                return number;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);

                number = Integer.MIN_VALUE;
            }
        } while (number == Integer.MIN_VALUE);

        return number;
    }


    // Method to validate a double input
    public static double validateDoubleInput(Scanner scanner, String prompt, String errorMsg) {
        while (true) {
            System.out.println(prompt);
            try {
                return Double.parseDouble(scanner.next().trim());
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }


    public static int validateIntOrKeepCurrent(Scanner scanner, String prompt, String errorMsg) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.next().trim();
            if (input.equals("-1")) {
                return -1;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double validateDoubleOrKeepCurrent(Scanner scanner, String prompt, String errorMsg) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.next().trim();
            if (input.equals("-1")) {
                return -1;
            }

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String validateStringOrKeepCurrent(Scanner scanner, String prompt, String errorMsg) {
        System.out.println(prompt);
        String input = scanner.next().trim();
        if (input.equals("-1")) {
            return "-1";
        }
        return input;
    }

    public static boolean isValidDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

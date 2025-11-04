package institute.management.system;

import java.util.regex.Pattern;

public class Validation {

    /**
     * Checks if a string contains only letters and spaces.
     * @param str The string to check.
     * @return true if the string contains only letters and spaces, false otherwise.
     */
    public static boolean isAlpha(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // Allows letters and spaces
        return str.matches("^[a-zA-Z ]+$");
    }

    /**
     * Checks if a string is numeric (allows decimals).
     * @param str The string to check.
     * @return true if the string is numeric, false otherwise.
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string contains ONLY digits (no decimals, no negatives).
     * @param str The string to check.
     * @return true if the string contains only digits, false otherwise.
     */
    public static boolean isDigitsOnly(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // Allows only numbers 0-9
        return str.matches("^[0-9]+$");
    }

    /**
     * Checks if a string is a valid percentage (a number between 0 and 100).
     * @param str The string to check.
     * @return true if the string is a valid percentage, false otherwise.
     */
    public static boolean isPercentage(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            double value = Double.parseDouble(str);
            return (value >= 0 && value <= 100);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string is a valid email address format.
     * @param str The string to check.
     * @return true if the email format is valid, false otherwise.
     */
    public static boolean isValidEmail(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // A standard regex for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(str).matches();
    }

    /**
     * Checks if a string is alphanumeric (can contain letters, numbers, spaces,
     * and common address characters like - , / #).
     * @param str The string to check.
     * @return true if the string is valid for an address, false otherwise.
     */
    public static boolean isValidAddress(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // Allows letters, numbers, spaces, and common address symbols
        return str.matches("^[a-zA-Z0-9 ,/-]+$");
    }
}
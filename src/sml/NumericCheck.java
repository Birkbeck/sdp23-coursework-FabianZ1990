package sml;


/**
 * A simple helper method to check whether a given string is a numeric value (int)
 * Used in the instruction factory to handle exceptions in case of a wrong entry (for example in the mov Instruction)*
 * Takes a String and returns a boolean
 *
 * @author Fabian Zischler
 */

public class NumericCheck {
    public static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals("")) {
            return false;
        }
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
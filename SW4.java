import javax.swing.JOptionPane;

public class SW4 {
    public static void main(String[] args) {
        // Flag to control whether the program should continue running
        boolean continueConversion = true;

        // Loop to allow repeated conversions until the user decides to exit
        while (continueConversion) {
            // Prompt the user to enter a number using a dialog box
            String input = JOptionPane.showInputDialog("Enter an Integer Number: ");

            // Validation: Check if the input is empty, null, or contains non-numeric characters
            if (input == null || input.trim().isEmpty() || !input.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Error: Please enter a valid number (0-99999).");
                continue; // Skip to the next iteration if input is invalid
            }

            // Parse the input string into an integer
            int num = Integer.parseInt(input);
            int originalNum = num; // Save the original number for Roman numeral conversion

            // Validate that the number is within the allowed range (0-99,999)
            if (num < 0 || num > 99999) {
                JOptionPane.showMessageDialog(null, "Error: Number must be between 0 and 99,999.");
                continue; // Skip to the next iteration if out of range
            }

            // Count the number of digits in the input number
            int digits = (num == 0) ? 1 : String.valueOf(num).length();

            // Arrays to store words for numbers
            String[] units = {"", "One", "Two", "Three", "Four", "Five",
                             "Six", "Seven", "Eight", "Nine"};
            String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                             "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
            String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
                            "Sixty", "Seventy", "Eighty", "Ninety"};

            // Variable to store the number in words
            String words = "";

            // Handle special case for zero
            if (num == 0) {
                words = "zero";
            } else {
                // Process the thousands part of the number
                if (num >= 1000) {
                    int thousandPart = num / 1000; // Extract the thousands part
                    if (thousandPart < 10) {
                        // Single-digit thousands (e.g., 1,000 -> "one thousand")
                        words += units[thousandPart] + " Thousand ";
                    } else if (thousandPart < 20) {
                        // Teen numbers in the thousands (e.g., 12,000 -> "twelve thousand")
                        words += teens[thousandPart - 10] + " Thousand ";
                    } else {
                        // Two-digit thousands (e.g., 23,000 -> "twenty-three thousand")
                        words += tens[thousandPart / 10] + "-" +
                                 units[thousandPart % 10] + " Thousand ";
                    }
                    num %= 1000; // Remove the thousands part from the number
                }

                // Process the hundreds part of the number
                if (num >= 100) {
                    int hundred = num / 100; // Extract the hundreds part
                    words += units[hundred] + " Hundred "; // Add the word for hundreds
                    num %= 100; // Remove the hundreds part from the number
                }

                // Process the remaining part of the number (less than 100)
                if (num > 0) {
                    if (num < 10) {
                        // Single-digit numbers (e.g., 1 -> "one")
                        words += units[num];
                    } else if (num < 20) {
                        // Teen numbers (e.g., 15 -> "fifteen")
                        words += teens[num - 10];
                    } else {
                        // Two-digit numbers (e.g., 45 -> "forty-five")
                        words += tens[num / 10] + "-" + units[num % 10];
                    }
                }
            }

            // Convert the number to Roman numerals
            String roman = "";
            int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL",
                               "X", "IX", "V", "IV", "I"};

            // Loop through the values and symbols arrays to build the Roman numeral
            for (int i = 0; i < values.length; i++) {
                while (originalNum >= values[i]) {
                    roman += symbols[i]; // Add the corresponding Roman symbol
                    originalNum -= values[i]; // Subtract the value from the number
                }
            }

            // Prepare the output message
            String message = "Digits: " + digits + "\n" +
                             "In words: " + words.trim() + "\n" +
                             "Roman numeral: " + roman;

            // Display the results in a dialog box
            JOptionPane.showMessageDialog(null, message);

            // Ask the user if they want to convert another number
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to convert another number?",
                                                      "Convert Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                continueConversion = false; // Exit the loop if the user chooses "No"
            }
        }
    }
}
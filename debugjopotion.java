import javax.swing.JOptionPane;

public class debugjopotion {
    public static void main(String[] args) {
        boolean continueConversion = true;

        while (continueConversion) {
            String input = JOptionPane.showInputDialog("Enter an Integer Number: ");

            if (input == null || input.trim().isEmpty() || !input.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "You entered an INVALID INPUT.");
                continue;
            }

            int num = Integer.parseInt(input);
            int originalNum = num;

            if (num < 0 || num > 99999) {
                JOptionPane.showMessageDialog(null, "Error: Number must be between 0 and 99,999.");
                continue;
            }

            int digits = (num == 0) ? 1 : String.valueOf(num).length();
            String words = numberToWord(num);

            String roman = convertToRoman(originalNum);

            String message = "Digits: " + digits + "\n" +
                             "In words: " + words.trim() + "\n" +
                             "Roman numeral: " + roman;

            JOptionPane.showMessageDialog(null, message);

            int choice = JOptionPane.showConfirmDialog(null, "Do you want to convert another number?",
                                                      "Convert Again?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                continueConversion = false;
            }
        }
    }

    public static String numberToWord(int num) {
        if (num == 0) return "zero";

        String words = "";

        if (num >= 1000) {
            int thousandPart = num / 1000;
            words += numberToWord(thousandPart) + " Thousand ";
            num %= 1000;
        }

        if (num >= 100) {
            int hundred = num / 100;
            words += numberToWord(hundred) + " Hundred ";
            num %= 100;
        }

        if (num > 0) {
            if (num < 10) {
                words += units(num);
            } else if (num < 20) {
                words += units(num);
            } else {
                words += tensToWord(num / 10);
                if (num % 10 != 0) {
                    words += "-" + units(num % 10);
                }
            }
        }

        return words.trim();
    }

    public static String units(int num) {
        switch (num) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
            default: return "";
        }
    }

    public static String tensToWord(int num) {
        switch (num) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
            default: return "";
        }
    }

    public static String convertToRoman(int num) {
        String roman = "";
        int value = num;

        while (value >= 1000) { roman += "M"; value -= 1000; }
        if (value >= 900) { roman += "CM"; value -= 900; }
        if (value >= 500) { roman += "D"; value -= 500; }
        if (value >= 400) { roman += "CD"; value -= 400; }
        while (value >= 100) { roman += "C"; value -= 100; }
        if (value >= 90) { roman += "XC"; value -= 90; }
        if (value >= 50) { roman += "L"; value -= 50; }
        if (value >= 40) { roman += "XL"; value -= 40; }
        while (value >= 10) { roman += "X"; value -= 10; }
        if (value >= 9) { roman += "IX"; value -= 9; }
        if (value >= 5) { roman += "V"; value -= 5; }
        if (value >= 4) { roman += "IV"; value -= 4; }
        if (value >= 1) { roman += "I"; value -= 1; }

        return roman;
    }
}
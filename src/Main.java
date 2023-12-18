import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static final int MAX_NUMBER_OF_UNLUCKY_NUMBER = 6;
    public static void main(String[] args) {
        System.out.println("Hello Mr. Keller, welcome to the Lottery App!");
        List<Integer> unluckyNumbers = readUnluckyNumbers();
        System.out.print("What do you want to play today? ");
        String gameChoice;
        Scanner gameScanner = new Scanner(System.in);

        do {
            gameChoice = gameScanner.nextLine().trim();

            if (gameChoice.isEmpty() || gameChoice.equalsIgnoreCase("Lotto")) {
                SuggestedNumbers lotto = new Lotto();
                System.out.println("You chose Lotto");
                List<Integer> result = lotto.generateNumbers(unluckyNumbers);
                System.out.println("Lotto Numbers: " + result);
                break;
            }
            else if (gameChoice.equalsIgnoreCase("Eurojackpot")) {
                SuggestedNumbers eurojackpot = new Eurojackpot();
                List<Integer> result = eurojackpot.generateNumbers(unluckyNumbers);
                System.out.println("Eurojackpot Numbers: " + result);
                System.out.println("You chose Eurojackpot");
                break;
            }
            else {
                System.out.print("Invalid choice! \nPlease enter \"Lotto\" or \"Eurojackpot\": ");
            }
        } while (true);
        // Close the scanner to avoid resource leak
        gameScanner.close();
    }

    private static List<Integer> readUnluckyNumbers() {
        System.out.print("Enter unlucky numbers separated by spaces (e.g., 7 23 30): ");
        List<Integer> unluckyNumbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try {
            while (scanner.hasNextInt()) {
                String unluckyNumber = scanner.nextLine().trim();

                if (unluckyNumber.isEmpty()) {
                    return unluckyNumbers;
                }

                String[] numberStrings = unluckyNumber.split("[,\\s]+");

                for (String numberString : numberStrings) {
                    int number = Integer.parseInt(numberString);
                    if (number >= 1 && number <=50) {
                        unluckyNumbers.add(number);
                    }
                    else {
                        throw new IllegalArgumentException("Input must be between 1 and 50");
                    }
                }

                if (unluckyNumbers.size() >= MAX_NUMBER_OF_UNLUCKY_NUMBER) {
                    System.out.println("Maximum 6 unlucky numbers allowed.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return unluckyNumbers;
    }

    public static List<Integer> getAllNumbers(int maxRange) {
        List<Integer> allNumbers = new ArrayList<>();
        for (int i = 0; i < maxRange; i++) {
            allNumbers.add(i);
        }
        return allNumbers;
    }

    public static List<Integer> excludeUnluckyNumbers(List<Integer> allNumbers, List<Integer> unluckyNumbers) {
        allNumbers.removeAll(unluckyNumbers);
        return allNumbers;
    }

    public static List<Integer> generateRandomNumbers(List<Integer> validNumbers, int count) {
        List<Integer> result = new ArrayList<>();
        Collections.shuffle(validNumbers);
        for (int i = 0; i < count; i++) {
            result.add(validNumbers.get(i));
        }

        Collections.sort(result);
        return result;
    }
}
import java.util.List;

public class Lotto implements SuggestedNumbers {
    @Override
    public List<Integer> generateNumbers(List<Integer> unluckyNumbers) {
        List<Integer> allNumbers = Main.getAllNumbers(49);
        List<Integer> validNumbers = Main.excludeUnluckyNumbers(allNumbers, unluckyNumbers);

        return Main.generateRandomNumbers(validNumbers, 6);
    }
}
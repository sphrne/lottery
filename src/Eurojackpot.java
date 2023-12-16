import java.util.Collections;
import java.util.List;

public class Eurojackpot implements SuggestedNumbers {

    @Override
    public List<Integer> generateNumbers(List<Integer> unluckyNumbers) {
        List<Integer> allNumbers = Main.getAllNumbers(50);
        List<Integer> validNumbers = Main.excludeUnluckyNumbers(allNumbers, unluckyNumbers);

        List<Integer> mainNumbers = Main.generateRandomNumbers(validNumbers, 5);
        List<Integer> luckyNumbers = Main.generateRandomNumbers(Main.getAllNumbers(10), 2);

        mainNumbers.addAll(luckyNumbers);
        Collections.sort(mainNumbers);

        return mainNumbers;
    }
}

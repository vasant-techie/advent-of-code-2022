package t22.day3.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day3");
    }

    private void process(String path) throws IOException {
        List<String> rucksacks = Files.lines(Paths.get(path)).collect(Collectors.toList());
        int totalSum = 0;

        for(String ruckSack: rucksacks) {

            int length = ruckSack.length()/2;
            String firstHalf = ruckSack.substring(0, length);
            String secondHalf = ruckSack.substring(length);
            Set<Integer> commons = findCommon(firstHalf, secondHalf);
            int result = calcSum(commons);
            //System.out.println("Temporary Result: " + result);

            totalSum += result;

        }
        System.out.println("### Total Sum: " + totalSum);

    }

    private int calcSum(Set<Integer> commons) {
        return commons.stream().reduce((x,y) -> x+y).get();
    }

    private Set<Integer> findCommon(String firstHalf, String secondHalf) {
        return firstHalf.chars().boxed().filter(c -> secondHalf.contains(Character.toString(c))).map(v -> (Character.isLowerCase(v))? v - 96: v - 38).collect(Collectors.toSet());
    }
}

package t22.day3.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
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
        int groupCount = 0;

        List<String> firstHalfGroup = new ArrayList<>();
        List<String> secondHalfGroup = new ArrayList<>();
        for (String ruckSack : rucksacks) {

            groupCount++;
            if (groupCount <= 3) {
                firstHalfGroup.add(ruckSack);
                continue;
            } else if (groupCount > 3 && groupCount < 6) {
                secondHalfGroup.add(ruckSack);
                continue;
            } else {
                secondHalfGroup.add(ruckSack);
                groupCount = 0;
            }

            Set<Integer> commons1 = findCommon(firstHalfGroup);
            Set<Integer> commons2 = findCommon(secondHalfGroup);


            int result = calcVal(commons1) + calcVal(commons2);
            //System.out.println("Temporary Result: " + result);

            totalSum += result;

            firstHalfGroup = new ArrayList<>();
            secondHalfGroup = new ArrayList<>();
        }
        System.out.println("### Total Sum: " + totalSum);

    }

    private int calcVal(Set<Integer> commons) {
        return commons.stream().map(v -> (Character.isLowerCase(v)) ? v - 96 : v - 38).reduce((x, y) -> x + y).get();
    }

    private Set<Integer> findCommon(List<String> group) {
        Set<Integer> commonChars = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : group) {
            stringBuilder.append(str);
        }

        String groupedStr = stringBuilder.toString();

        List<Integer> groupedChars = groupedStr.chars().boxed().collect(Collectors.toList());
        for (Integer ch : groupedChars) {
            boolean matching = true;
            for (int i = 0; i < group.size(); i++) {

                String subGroup = group.get(i);
                if (!subGroup.contains(Character.toString(ch))) {
                    matching = false;
                    continue;
                }

                if (matching && i == 2) {
                    commonChars.add(ch);
                }
            }
        }
        return commonChars;
    }

}

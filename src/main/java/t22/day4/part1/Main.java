package t22.day4.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day4");
    }

    private void process(String path) throws IOException {
        long total = 0l;
        List<String> lines = Files.lines(Paths.get(path)).collect(Collectors.toList());
        for(String line: lines) {

            String[] pairs = line.split(",");
            String[] firstPair = pairs[0].split("-");
            String[] secondPair = pairs[1].split("-");

            int firstPairStartNum = Integer.parseInt(firstPair[0].trim());
            int firstPairEndNum = Integer.parseInt(firstPair[1].trim());
            int secondPairStartNum = Integer.parseInt(secondPair[0].trim());
            int secondPairEndNum = Integer.parseInt(secondPair[1].trim());

            boolean res = withinRange(firstPairStartNum, firstPairEndNum, secondPairStartNum, secondPairEndNum);

            if(res)
                total += 1;
        }

        System.out.println("Total: " +  total);
    }

    private boolean withinRange(int firstPairStartNum, int firstPairEndNum, int secondPairStartNum, int secondPairEndNum) {
        if(firstPairStartNum <= secondPairStartNum && firstPairEndNum >= secondPairEndNum) {
            return true;
        } else if(secondPairStartNum <= firstPairStartNum && secondPairEndNum >= firstPairEndNum) {
            return true;
        }

        return false;
    }
}

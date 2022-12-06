package t22.day6.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        int index = main.process("src/main/resources/day6");
        System.out.println("Result: " + (index + 1));
    }

    private int process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        for(String sample: lines) {
            Set<Integer> coll = new HashSet<>();
            for(int c=0; c<sample.length(); c++) {
                for (int i = c; i < sample.length(); i++) {
                    int ch = sample.codePointAt(i);
                    boolean res = coll.add(ch);
                    if (coll.size() == 4 && res == true) {
                        return i;
                    } else if (coll.size() < 4 && !res) {
                        coll.clear();
                        break;
                    }
                }
            }
        }
        System.err.println("### ABNORMAL ###");
        return -1;
    }
}

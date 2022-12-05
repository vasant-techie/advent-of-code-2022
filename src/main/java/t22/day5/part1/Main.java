package t22.day5.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day5");
    }

    private void process(String path) throws IOException {
        Map<Integer, LinkedList<String>> stacksAndCrates = parseData(path);
        List<String> actions = parseActions(path);

        for(String action: actions) {
            int numToMove = Integer.parseInt(action.substring(5, action.indexOf(" from")));
            int fromStack = Integer.parseInt(action.substring(action.indexOf("from ") + 5, action.indexOf(" to")));
            int toStack = Integer.parseInt(action.substring(action.indexOf("to ") + 3));
            System.out.println("To Move: " + numToMove + " From: " + fromStack + " To: " + toStack);

            for(int i=0; i<numToMove; i++) {
                String crate = stacksAndCrates.get(fromStack - 1).pop();
                //System.out.println("Current Crate: " + crate);
                stacksAndCrates.get(toStack - 1).push(crate);
                //System.out.println("TEMP RESULT: " + stacksAndCrates);
            }

        }

        StringBuilder result = new StringBuilder();
        for(int s=0; s<stacksAndCrates.size(); s++) {
            String peek = stacksAndCrates.get(s).peek();
            result.append(peek);
        }
        System.out.println("### Final Result: " + result.toString());
    }

    private List<String> parseActions(String path) throws IOException {
        long count = Files.lines(Paths.get(path)).takeWhile(l -> !l.isEmpty()).count();
        List<String> actions = Files.lines(Paths.get(path)).skip(count+1).collect(Collectors.toList());
        System.out.println("Actions: " + actions);
        return actions;
    }

    private Map<Integer, LinkedList<String>> parseData(String path) throws IOException {

        List<String> list = Files.lines(Paths.get(path)).takeWhile(l -> !l.isEmpty()).collect(Collectors.toList());

        int totalStacks = list.get(list.size() - 1).trim().split("\\s+").length;
        System.out.println("Total Stacks: " + totalStacks);
        return parseStackData(list, totalStacks);
    }


    private Map<Integer, LinkedList<String>> parseStackData(List<String> list, int totalStacks) {
        Map<Integer, LinkedList<String>> stacksAndCrates = new HashMap<>();


        for (int i = list.size() - 2; i >= 0; i--) {
            String line = list.get(i);
            int space = 0;

            for (int s = 0; s < totalStacks; s++) {
                LinkedList<String> crates = stacksAndCrates.get(s);

                if (null == crates) {
                    crates = new LinkedList<>();
                    stacksAndCrates.put(s, crates);
                }

                if (space + 3 > line.length())
                    break;
                String cratesVal = line.substring(space, space + 3);
                if (!cratesVal.trim().isEmpty())
                    crates.push(cratesVal.substring(1, 2));
                space += 4;
            }
        }
        System.out.println("RESULT: " + stacksAndCrates);
        return stacksAndCrates;
    }
}

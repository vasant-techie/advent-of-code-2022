package t22.day2.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Paths.get("src/main/resources/day2")).collect(Collectors.toList());

        Long total = 0l;
        for(String line: lines) {
            String[] choices = line.split(" ");
            String choiceX = choices[0];
            String choiceY = choices[1];

            int res = calcResult(choiceY);
            choiceY = findChoice(res, typeOf(choiceX));

            int resultVal = (res == 1)? 6: (res == 0)? 3: 0;
            int curTotal = resultVal + valueOf(choiceY);
            total += curTotal;
        }

        System.out.println("Total: " + total);
    }

    private static int calcResult(String choiceY) {
        //X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
        switch(choiceY) {
            case "X":
                return -1;
            case "Y":
                return 0;
            case "Z":
                return 1;
        }
        System.err.println("@@@ ABNORMAL @@@");
        return -5;
    }

    private static String typeOf(String choice) {
        switch(choice) {
            case "X":
            case "A":
                return "ROCK";
            case "Y":
            case "B":
                return "PAPER";
            case "Z":
            case "C":
                return "SCISSORS";
        }
        System.err.println("!!! ABNORMAL !!!");
        return null;
    }
    private static int valueOf(String choice) {
        //1 for Rock, 2 for Paper, and 3 for Scissors
        switch(choice) {
            case "X":
            case "A":
            case "ROCK":
                return 1;
            case "Y":
            case "B":
            case "PAPER":
                return 2;
            case "Z":
            case "C":
            case "SCISSORS":
                return 3;
        }
        System.err.println("---ABNORMAL---");
        return -1;
    }

    private static String findChoice(int result, String choiceX) {
        Map<String, String> winRules = new HashMap<>();
        winRules.put("ROCK", "SCISSORS");
        winRules.put("SCISSORS", "PAPER");
        winRules.put("PAPER", "ROCK");

        Map<String, String> loseRules = new HashMap<>();
        loseRules.put("SCISSORS", "ROCK");
        loseRules.put("PAPER", "SCISSORS");
        loseRules.put("ROCK", "PAPER");

        if(result == 0) {
            return choiceX;
        } else if(result == -1) {
            return winRules.get(choiceX);
        } else if (result == 1) {
            return loseRules.get(choiceX);
        }

        System.err.println("%%% ABNORMAL %%%");
        return null;
    }

}

package t22.day2.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Paths.get("src/main/resources/day2")).collect(Collectors.toList());

        Long total = 0l;
        for(String line: lines) {
            String[] choices = line.split(" ");
            String choiceX = choices[0];
            String choiceY = choices[1];

            int res = findWinner(typeOf(choiceX), typeOf(choiceY));
            int resultVal = (res == 1)? 6: (res == 0)? 3: 0;
            int curTotal = resultVal + valueOf(choiceY);
            total += curTotal;
        }

        System.out.println("Total: " + total);
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
                return 1;
            case "Y":
            case "B":
                return 2;
            case "Z":
            case "C":
                return 3;
        }
        System.err.println("---ABNORMAL---");
        return -1;
    }

    private static int findWinner(String choiceX, String choiceY) {
        //a winner for that round is selected: Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock
        if(choiceX.equalsIgnoreCase(choiceY))
            return 0;
        else {
            if (choiceX.equalsIgnoreCase("ROCK") && choiceY.equalsIgnoreCase("SCISSORS"))
                return -1;
            else if (choiceX.equalsIgnoreCase("SCISSORS") && choiceY.equalsIgnoreCase("ROCK"))
                return 1;
            else if(choiceX.equalsIgnoreCase("SCISSORS") && choiceY.equalsIgnoreCase("PAPER"))
                return -1;
            else if(choiceX.equalsIgnoreCase("PAPER") && choiceY.equalsIgnoreCase("SCISSORS"))
                return 1;
            else if(choiceX.equalsIgnoreCase("PAPER") && choiceY.equalsIgnoreCase("ROCK"))
                return -1;
            else if(choiceX.equalsIgnoreCase("ROCK") && choiceY.equalsIgnoreCase("PAPER"))
                return 1;
        }
        System.err.println("###ABNORMAL###");
        return -5;
    }
}

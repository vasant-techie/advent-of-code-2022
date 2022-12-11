package t22.day9.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private long xCoord = 1l;
    private long yCoord = 1l;

    private long xStartPos = 1l;
    private long yStartPos = 1l;
    private long xEndPos = 1l;
    private long yEndPos = 1l;

    private List<Coord> tailCoords = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day9-sample");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        findGridCoordAndHeadEndPosition(lines);
        Collections.reverse(lines);
        findHeadStartPosition(lines);
    }

    private void findHeadStartPosition(List<String> lines) {
        long xPos = this.xEndPos;
        long yPos = this.yEndPos;

        for(String line: lines) {
//            System.out.println("Line: " + line);
            String[] actionAndCount = line.split(" ");
            String action = actionAndCount[0];
            int count = Integer.parseInt(actionAndCount[1]);
            switch(action) {
                case "R":
                    xPos = xPos - count;
                    break;
                case "L":
                    xPos = xPos + count;
                    break;
                case "D":
                    yPos = yPos + count;
                    break;
                case "U":
                    yPos = yPos - count;
                    break;
            }
        }
        System.out.printf("\nSTART POS: X: %d, Y: %d\n", xPos, yPos);
    }
    private void findGridCoordAndHeadEndPosition(List<String> lines) {
        long xPos = 1l;
        long yPos = 1l;
        for(String line: lines) {
            System.out.println("Line: " + line);
            String[] actionAndCount = line.split(" ");
            String action = actionAndCount[0];
            int count = Integer.parseInt(actionAndCount[1]);
            switch(action) {
                case "R":
                    xPos = xPos + count;
                    this.xCoord = (xPos > this.xCoord)? xPos: this.xCoord;
                    break;
                case "L":
                    long tempXPos = xPos - count;
                    if(tempXPos <= 0)
                        xPos = xPos + Math.abs(tempXPos) + 1;
                    else
                        xPos = tempXPos;
                    this.xCoord = (xPos > this.xCoord)? xPos: this.xCoord;
                    break;
                case "U":
                    yPos = yPos + count;
                    this.yCoord = (yPos > this.yCoord)? yPos: this.yCoord;
                    break;
                case "D":
                    long tempYPos = yPos - count;
                    if(tempYPos <= 0)
                        yPos = yPos + Math.abs(tempYPos) + 1;
                    else
                        yPos = tempYPos;
                    this.yCoord = (yPos > this.yCoord)? yPos: this.yCoord;
                    break;
            }
        }
        System.out.printf("\nX: %d, Y: %d\n", xPos, yPos);
        this.xEndPos = xPos;
        this.yEndPos = yPos;
        System.out.println("X Coord: " + this.xCoord);
        System.out.println("Y Coord: " + this.yCoord);
    }
}

package t22.day9.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private int xCoord = 1;
    private int yCoord = 1;

    private int xStartPos = 1;
    private int yStartPos = 1;
    private int xEndPos = 1;
    private int yEndPos = 1;

    private List<Coord> tailCoords = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day9-sample");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        find(lines);
        calcUniqueVisited(lines);
    }

    private void calcUniqueVisited(List<String> lines) {
        int[][] grid = new int[this.xCoord][this.yCoord];

    }

    private void find(List<String> lines) {

        for(String line: lines) {
            System.out.println("Line: " + line);
            String[] actionAndCount = line.split(" ");
            String action = actionAndCount[0];
            int count = Integer.parseInt(actionAndCount[1]);
            switch(action) {
                case "R":
                    this.xEndPos = this.xEndPos + count;
                    this.xCoord = (this.xEndPos > this.xCoord)? this.xEndPos: this.xCoord;
                    break;
                case "L":
                    int tempXPos = this.xEndPos - count;
                    if(tempXPos <= 0) {
                        this.xStartPos = this.xStartPos + Math.abs(tempXPos) + 1;
                        this.xCoord = this.xCoord + Math.abs(tempXPos) + 1;
                        this.xEndPos = 1;
                    }
                    else {
                        this.xEndPos = tempXPos;
                        this.xCoord = (this.xEndPos > this.xCoord) ? this.xEndPos : this.xCoord;
                    }
                    break;
                case "U":
                    this.yEndPos = this.yEndPos + count;
                    this.yCoord = (this.yEndPos > this.yCoord)? this.yEndPos: this.yCoord;
                    break;
                case "D":
                    int tempYPos = this.yEndPos - count;
                    if(tempYPos <= 0) {
                        this.yStartPos = this.yStartPos + Math.abs(tempYPos) + 1;
                        this.yCoord = this.yCoord + Math.abs(tempYPos) + 1;
                        this.yEndPos = 1;
                    }
                    else {
                        this.yEndPos = tempYPos;
                        this.yCoord = (this.yEndPos > this.yCoord) ? this.yEndPos : this.yCoord;
                    }
                    break;
            }
            System.out.printf("\nX Coord: %d, Y Coord: %d\n", this.xCoord, this.yCoord);
            System.out.printf("\nX Start Pos: %d, Y Start Pos: %d\n", this.xStartPos, this.yStartPos);
            System.out.printf("\nX End Pos: %d, Y End Pos: %d\n", this.xEndPos, this.yEndPos);

        }

    }
}

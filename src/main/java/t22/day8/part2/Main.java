package t22.day8.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day8");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        long[][] treeHeights = parseData(lines);
        long maxScenicScore = evaluateVisibleTrees(treeHeights);
        System.out.printf("\nMax Scenic Score: %d", maxScenicScore);
    }

    private long evaluateVisibleTrees(long[][] treeHeights) {

        List<Long> scenicScores = new ArrayList<>();
        for(int r=1; r<treeHeights.length-1; r++) {
            for(int c=1; c<treeHeights.length-1; c++) {
                long height = treeHeights[r][c];
                System.out.printf("\nHeight of [%d][%d]: %d", r, c, height);
                long leftCount = isVisibleTopLeft(height, treeHeights, r, c, true);
                long topCount = isVisibleTopLeft(height, treeHeights, r, c, false);
                long rightCount = isVisibleBottomRight(height, treeHeights, r, c, true);
                long bottomCount = isVisibleBottomRight(height, treeHeights, r, c, false);

                System.out.printf("\nUp: %d, Left: %d, Down: %d, Right: %d\n", topCount, leftCount, bottomCount, rightCount);
                long result = leftCount * topCount * rightCount * bottomCount;
                scenicScores.add(result);
            }
        }

        return Collections.max(scenicScores);
    }

    private long isVisibleTopLeft(long height, long[][] treeHeights, int r, int c, boolean isLeft) {
        long visibleCount = 0l;

        int position = (isLeft)? c: r;
        for(int i = position-1; i>=0; i--) {

            long curHeight = (isLeft)? treeHeights[r][i]: treeHeights[i][c];
            if(height > curHeight) {
                visibleCount++;
            } else {
                visibleCount++;
                return visibleCount;
            }
        }
        return visibleCount;
    }

    private long isVisibleBottomRight(long height, long[][] treeHeights, int r, int c, boolean isRight) {
        long visibleCount = 0l;

        int position = (isRight)? c: r;
        for(int i = position+1; i<treeHeights.length; i++) {
            long curHeight = (isRight)? treeHeights[r][i]: treeHeights[i][c];
            if(height > curHeight) {
                visibleCount++;
            } else {
                visibleCount++;
                return visibleCount;
            }
        }
        return visibleCount;
    }


    private long[][] parseData(List<String> lines) {
        int x = lines.get(0).length();
        int y = lines.size();
        long[][] treeHeights = new long[x][y];
        for(int r=0; r<lines.size(); r++) {
            String line = lines.get(r);
            for(int c=0; c<line.length(); c++) {
                treeHeights[r][c] = Integer.parseInt(String.valueOf(line.charAt(c)));
            }
        }
        return treeHeights;
    }

}

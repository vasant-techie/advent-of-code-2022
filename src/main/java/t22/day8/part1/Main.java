package t22.day8.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day8");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        long[][] treeHeights = parseData(lines);
        long innerVisibleCount = evaluateVisibleTrees(treeHeights);
        long gridCount = calcGridCount(treeHeights.length);
        long totalVisibleCount = gridCount + innerVisibleCount;
        System.out.printf("\nOuter (Grid) Visible Count: %d", gridCount);
        System.out.printf("\nInner Visible Count: %d", innerVisibleCount);
        System.out.printf("\nTotal Visible Result: %d", totalVisibleCount);
    }

    private long calcGridCount(int length) {
        long result = 0l;
        for(int i=0; i<4; i++) {
            result += (length - 1);
        }
        return result;
    }

    private long evaluateVisibleTrees(long[][] treeHeights) {
        long result = 0l;

        for(int r=1; r<treeHeights.length-1; r++) {
            for(int c=1; c<treeHeights.length-1; c++) {
                long height = treeHeights[r][c];
                System.out.printf("\nHeight of [%d][%d]: %d", r, c, height);
                if(isVisibleTopLeft(height, treeHeights, r, c, true)) {
                    System.out.print(" is Visible (Left)");
                    result += 1;
                } else if (isVisibleTopLeft(height, treeHeights, r, c, false)) {
                    System.out.print(" is Visible (Top)");
                    result += 1;
                } else if (isVisibleBottomRight(height, treeHeights, r, c, true)) {
                    System.out.print(" is Visible (Right)");
                    result += 1;
                }else if (isVisibleBottomRight(height, treeHeights, r, c, false)) {
                    System.out.print(" is Visible (Bottom)");
                    result += 1;
                }
            }
        }

        return result;
    }

    private boolean isVisibleTopLeft(long height, long[][] treeHeights, int r, int c, boolean isLeft) {
        boolean isVisible = true;

        int position = (isLeft)? c: r;
        for(int i = position-1; i>=0; i--) {

            long curHeight = (isLeft)? treeHeights[r][i]: treeHeights[i][c];
            if(height <= curHeight) {
                isVisible = false;
                break;
            }
        }
        return isVisible;
    }

    private boolean isVisibleBottomRight(long height, long[][] treeHeights, int r, int c, boolean isRight) {
        boolean isVisible = true;

        int position = (isRight)? c: r;
        for(int i = position+1; i<treeHeights.length; i++) {
            long curHeight = (isRight)? treeHeights[r][i]: treeHeights[i][c];
            if(height <= curHeight) {
                isVisible = false;
                break;
            }
        }
        return isVisible;
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

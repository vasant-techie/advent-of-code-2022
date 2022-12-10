package t22.day10.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day10");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        eval(lines);
    }

    private void eval(final List<String> lines) {
        int spriteStartPos = 0;
        int registerPos = 1;
        for (int i = 0, c = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if ("noop".equals(line)) {
                print(c, spriteStartPos);
                c++;
                if (c >= 40) {
                    System.out.println();
                    spriteStartPos = 0;
                    c = 0;
                }
            } else {
                print(c, spriteStartPos);
                c++;
                if (c >= 40) {
                    System.out.println();
                    spriteStartPos = 0;
                    c = 0;
                }
                print(c, spriteStartPos);
                c++;
                if (c >= 40) {
                    System.out.println();
                    spriteStartPos = 0;
                    c = 0;
                }
                registerPos += Integer.parseInt(line.split(" ")[1]);
                spriteStartPos = registerPos - 1;
            }
        }
    }

    private void print(int cycle, int spriteStartPos) {
        if (isWithInScope(cycle, spriteStartPos)) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
    }

    private boolean isWithInScope(int cycle, int spriteStartPos) {
        return (cycle >= spriteStartPos && cycle < (spriteStartPos + 3));
    }
}

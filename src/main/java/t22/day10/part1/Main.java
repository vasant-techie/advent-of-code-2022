package t22.day10.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day10");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));

        AtomicLong result = new AtomicLong(0l);
        //20th, 60th, 100th, 140th, 180th, and 220th cycles.
        List<Integer> thresholds = Arrays.asList(20, 60, 100, 140, 180, 220);
        thresholds.stream().forEach(threshold -> {
            long res = findXVal(threshold, lines);
            System.out.printf("\nThreshold: %d, Result: %d", threshold, res);
            result.addAndGet(threshold * res);
        });
        System.out.printf("\nResult: %d", result.get());
    }

    private long findXVal(final int threshold, final List<String> lines) {
        AtomicInteger cycle = new AtomicInteger();

        return lines.stream()
                .map(l -> {
                    if(l.equals("noop")) {
                        cycle.incrementAndGet();
                    } else {
                        cycle.incrementAndGet();
                        cycle.incrementAndGet();
                    };
                    return l;
                })
                .takeWhile(l -> (cycle.get() < threshold))
                .filter(l -> l.startsWith("addx "))
                .map(l -> Integer.parseInt(l.split(" ")[1]))
                .reduce(1, (x,y) -> x + y);
    }
}

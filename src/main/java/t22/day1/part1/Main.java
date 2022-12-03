package t22.day1.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(Paths.get("src/main/resources/day1-sample"));
        List<Long> result = new ArrayList<>();
        final AtomicLong sumVal = new AtomicLong(0);

        lines.forEach(v -> {
            if(v.isEmpty()) {
                result.add(sumVal.get());
                sumVal.set(0);
            } else {
                sumVal.set(sumVal.get() + Long.valueOf(v));
            }
        });
//        System.out.println("Count: " + result.size());
        Long maxVal = Collections.max(result);
        System.out.println("### Max Val: " + maxVal);

    }
}
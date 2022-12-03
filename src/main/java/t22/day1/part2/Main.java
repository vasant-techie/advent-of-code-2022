package t22.day1.part2;

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
        Collections.sort(result, Collections.reverseOrder());
//        System.out.println("Max 1st: " + result.get(0));
//        System.out.println("Max 2nd: " + result.get(1));
//        System.out.println("Max 3rd: " + result.get(2));

        long maxVal = result.get(0) + result.get(1) + result.get(2);
        System.out.println();
        System.out.println("### Sum of Top 3 Max Values: " + maxVal);

    }
}
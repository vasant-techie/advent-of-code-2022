package t22.day7.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.process("src/main/resources/day7");
    }

    private void process(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        Dir dir = parseData(lines);
        List<Long> sumList = new ArrayList<>();
        calc(dir, sumList);
        System.out.println("################### of DIRECTORIES: " + sumList.size());
        long totalSum = sumList.stream().reduce((x,y) -> x+y).get();
        totalSum += dir.getFiles().stream().map(f -> f.getSize()).reduce((x,y) -> x+y).get();
        System.out.println("total sumList: " + totalSum);
        sumList.add(totalSum);

        System.out.println("Sum List: " + sumList);
        long result = sumList.stream().filter(s -> s<=100000l).reduce((x,y) -> x+y).get();
        System.out.println("Result: " + result);
    }

    private void calc(Dir parentDir, List<Long> sizesSum) {

        List<Dir> dirs = parentDir.getDirs();

        for(Dir dir: dirs) {
            long fileSizesTotal = sum(dir, 0l);
            System.out.println(dir.getName() + ": " + fileSizesTotal);
            sizesSum.add(fileSizesTotal);
            calc(dir, sizesSum);
        }
    }

    private long sum(Dir dir, long fileSizeTotal) {
        fileSizeTotal += dir.getFiles().stream().map(f -> f.getSize()).reduce((x, y) -> x + y).orElse(0l);
        for(Dir subDir: dir.getDirs()) {
            return sum(subDir, fileSizeTotal);
        }
        return fileSizeTotal;
    }

    private Dir parseData(List<String> lines) {
        Dir parentDir = new Dir(null, "/");
        Dir curDir = null;

        for(int i=0; i<lines.size(); i++) {
            String line = lines.get(i);
            System.out.println("Current Line: " + line);
            if(line.equals("$ cd ..")) {
                curDir = curDir.getParent();
            } else if(line.equals("$ cd /")) {
                curDir = parentDir;
            } else if(line.startsWith("$ cd ")) {
                String dirName = line.substring("$ cd ".length());
                curDir = curDir.getDirs().stream().filter(d -> d.getName().equals(dirName)).collect(Collectors.toList()).get(0);
            } else if(line.equals("$ ls")) {
                continue;
            } else if(line.startsWith("dir ")) {
                String dirName = line.substring("dir ".length());
                Dir dir = new Dir(curDir, dirName);
                curDir.getDirs().add(dir);
            }else if(!line.startsWith("dir ")) {
                String[] fileData = line.split(" ");
                long size = Long.parseLong(fileData[0]);
                String fileName = fileData[1];
                File file = new File(fileName, size);
                curDir.getFiles().add(file);
            }
        }

        //parentDir.printStackTrace();
        return parentDir;
    }
}

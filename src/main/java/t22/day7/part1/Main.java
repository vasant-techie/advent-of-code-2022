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
        dir.printStackTrace();
        calc(dir);
        long result = printDirSizes(dir, 0l);

        System.out.println("Result: " + result);
    }

    private long printDirSizes(Dir dir, long result) {
        for(Dir curDir: dir.getDirs()) {
            System.out.println("Dir Name: " + curDir.getName() + ", Size: " + curDir.getTotalSize());
            if(curDir.getTotalSize() <= 100000) {
                result += curDir.getTotalSize();
            }
            result = printDirSizes(curDir, result);
        }
        return result;
    }

    private void calc(Dir dir) {
        long fileSizesTotal = sum(dir);
        dir.setTotalSize(fileSizesTotal);
        updateParentRecursively(dir, fileSizesTotal);

        for (Dir subDir : dir.getDirs()) {
            calc(subDir);
        }
    }

    private void updateParentRecursively(Dir dir, long fileSizesTotal) {
        Dir pDir = dir.getParent();
        while(pDir != null) {
            pDir.setTotalSize(pDir.getTotalSize() + fileSizesTotal);
            pDir = pDir.getParent();
        }
    }

    private long sum(Dir dir) {
        return dir.getFiles().stream().map(f -> f.getSize()).reduce((x, y) -> x + y).orElse(0l);
    }

    private Dir parseData(List<String> lines) {
        Dir parentDir = new Dir(null, "/");
        Dir curDir = null;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            //System.out.println("Current Line: " + line);
            if (line.equals("$ ls")) {
                continue;
            } else if (line.equals("$ cd ..")) {
                curDir = curDir.getParent();
            } else if (line.equals("$ cd /")) {
                curDir = parentDir;
            } else if (line.startsWith("$ cd ")) {
                String dirName = line.substring("$ cd ".length());
                curDir = curDir.getDirs().stream().filter(d -> d.getName().equals(dirName)).collect(Collectors.toList()).get(0);
            } else if (line.startsWith("dir ")) {
                String dirName = line.substring("dir ".length());
                Dir dir = new Dir(curDir, dirName);
                curDir.getDirs().add(dir);
            } else if (!line.startsWith("dir ")) {
                String[] fileData = line.split(" ");
                long size = Long.parseLong(fileData[0]);
                String fileName = fileData[1];
                File file = new File(fileName, size);
                curDir.getFiles().add(file);
            }
        }

        return parentDir;
    }
}

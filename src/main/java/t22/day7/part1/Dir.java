package t22.day7.part1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Dir {
    private String name;
    private List<File> files = new ArrayList<>();
    private List<Dir> dirs = new ArrayList<>();

    private Dir parent;

    private long totalSize;


    public Dir(Dir parent, String dirName) {
        this.parent = parent;
        this.name = dirName;
    }


    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Dir> getDirs() {
        return dirs;
    }

    public void setDirs(List<Dir> dirs) {
        this.dirs = dirs;
    }

    public Dir getParent() {
        return parent;
    }

    public void setParent(Dir parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printStackTrace() {
        String dirName = this.getName();
        System.out.println("- " + dirName + " (dir)");
        buildStackTrace(this, 2);
    }

    private void buildStackTrace(Dir dir, int spaceCount) {
        for(File file: dir.getFiles()) {
            System.out.println(getSpace(spaceCount) + "- " + file.getName() + " (file, size=" + file.getSize()+")");
        }
        for(Dir curDir: dir.getDirs()) {
            System.out.println(getSpace(spaceCount) + "- " + curDir.getName() + " (dir)");
            buildStackTrace(curDir, spaceCount+2);
        }
    }
    private String getSpace(int spaceCount) {
        StringBuilder spaceBuilder = new StringBuilder();
        for(int i=0; i<spaceCount; i++) {
            spaceBuilder.append(" ");
        }
        return spaceBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dir dir = (Dir) o;
        return Objects.equals(name, dir.name) && Objects.equals(files, dir.files) && Objects.equals(dirs, dir.dirs) && Objects.equals(parent, dir.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, files, dirs, parent);
    }
}
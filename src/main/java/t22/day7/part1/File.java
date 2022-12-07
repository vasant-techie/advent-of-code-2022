package t22.day7.part1;

import java.util.Objects;

public class File {
    private String name;
    private long size;


    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return size == file.size && Objects.equals(name, file.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}

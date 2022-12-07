package t22.day7.part1;

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
}

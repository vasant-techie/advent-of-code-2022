package t22.day9.part1;

import java.util.Objects;

public class Coord {

    private Integer x;
    private Integer y;

    public Coord(Integer x, Integer y) {
        System.out.printf("\nTail X: %d, Y: %d", x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return Objects.equals(x, coord.x) && Objects.equals(y, coord.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

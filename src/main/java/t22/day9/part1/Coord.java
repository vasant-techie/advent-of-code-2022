package t22.day9.part1;

import java.util.Objects;

public class Coord {

    private Long x;
    private Long y;

    public Coord(Long x, Long y) {
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

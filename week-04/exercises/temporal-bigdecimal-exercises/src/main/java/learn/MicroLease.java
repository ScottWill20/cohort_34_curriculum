package learn;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A lease that covers any time span from years to seconds.
 */
public class MicroLease implements Comparable {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public MicroLease(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MicroLease that = (MicroLease) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

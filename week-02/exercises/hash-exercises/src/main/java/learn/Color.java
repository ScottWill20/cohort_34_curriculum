package learn;

import java.util.Objects;

public class Color {

    private final String name;

    public Color(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return name.equals(color.name);
    }

    @Override
    public int hashCode() {
        return 1;
    }



    // 1. Override Color .equals and .hashCode to use the `name` field.
    // (Hint: IntelliJ can generate these methods for you.)
}

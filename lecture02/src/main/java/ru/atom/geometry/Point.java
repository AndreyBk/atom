package ru.atom.geometry;

/**
 * Template class for
 */
public class Point implements Collider/* super class and interfaces here if necessary */ {
    int x = 0;
    int y = 0;
    // fields
    // and methods


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @param o - other object to check equality with
     * @return true if two points are equal and not null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (o instanceof Point) return (this.x == ((Point) o).getX() & this.y == ((Point) o).getY());

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isColliding(Collider other) {
        if (this == other) return true;
        if (other == null) return false;

        if (other instanceof Point) return (this.x == ((Point) other).getX() & this.y == ((Point) other).getY());
        if (other instanceof Geometry) {
            return (this.x >= ((Geometry) other).getX1()
                & this.x <= ((Geometry) other).getX2())
                & (this.y >= ((Geometry) other).getY1()
                & this.y <= ((Geometry) other).getY2());
        }
        return false;
    }
}

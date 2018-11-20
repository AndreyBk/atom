package ru.atom.geometry;

/**
 * ^ Y
 * |
 * |
 * |
 * |          X
 * .---------->
 */

public final class Geometry implements Collider {
    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;

    public Geometry(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    /**
     * Bar is a rectangle, which borders are parallel to coordinate axis
     * Like selection bar in desktop, this bar is defined by two opposite corners
     * Bar is not oriented
     * (It is not relevant, which opposite corners you choose to define bar)
     *
     * @return new Bar
     */
    public static Collider createBar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY) {
        int xld;
        int xru;
        int yld;
        int yru;
        if (firstCornerX < secondCornerX) {
            xld = firstCornerX;
            xru = secondCornerX;
        } else {
            xld = secondCornerX;
            xru = firstCornerX;
        }
        if (firstCornerY < secondCornerY) {
            yld = firstCornerY;
            yru = secondCornerY;
        } else {
            yld = secondCornerY;
            yru = firstCornerY;
        }
        return new Geometry(xld, yld, xru, yru);
    }

    /**
     * 2D point
     *
     * @return new Point
     */
    public static Collider createPoint(int x, int y) {
        return new Point(x, y);
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        if (obj instanceof Geometry) return
            (this.x1 == ((Geometry) obj).getX1()
                & this.y1 == ((Geometry) obj).getY1()
                & this.x2 == ((Geometry) obj).getX2()
                & this.y2 == ((Geometry) obj).getY2());
        return false;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (this == other) return true;
        if (other == null) return false;
        if (other instanceof Point)
            return ((((Point) other).getX() >= this.getX1()
                & ((Point) other).getX() <= this.getX2())
                & (((Point) other).getY() >= this.getY1()
                & ((Point) other).getY() <= this.getY2()));
        if (other instanceof Geometry) {
            int xld;
            int xru;
            int yld;
            int yru;
            if (((Geometry) other).getX1() < ((Geometry) other).getX2()) {
                xld = ((Geometry) other).getX1();
                xru = ((Geometry) other).getX2();
            } else {
                xld = ((Geometry) other).getX2();
                xru = ((Geometry) other).getX1();
            }
            if (((Geometry) other).getY1() < ((Geometry) other).getY2()) {
                yld = ((Geometry) other).getY1();
                yru = ((Geometry) other).getY2();
            } else {
                yld = ((Geometry) other).getY2();
                yru = ((Geometry) other).getY1();
            }

            if (xld < this.x1 & xru < this.x1) return false;
            if (xld > this.x2 & xru > this.x2) return false;
            if (yld < this.y1 & yru < this.y1) return false;
            if (yld > this.y2 & yru > this.y2) return false;
            return true;
        }
        return false;
    }
}

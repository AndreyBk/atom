package ru.atom.geometry;

/**
 *  ^ Y
 *  |
 *  |
 *  |
 *  |          X
 *  .---------->
 */

public final class Geometry implements Collider{
//    private Point p1=null, p2=null;
    private int x1=0,y1=0,x2=0,y2=0;

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
     * @return new Bar
     */
    public static Collider createBar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY) {
        return new Geometry(firstCornerX,firstCornerY,secondCornerX,secondCornerY);
//        throw new UnsupportedOperationException();
    }

    /**
     * 2D point
     * @return new Point
     */
    public static Collider createPoint(int x, int y) {
        return  new Point(x, y);
//        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        if(obj instanceof Geometry) return
                (this.x1==((Geometry) obj).getX1() & this.y1==((Geometry) obj).getY1()&
                 this.x2==((Geometry) obj).getX2() & this.y2==((Geometry) obj).getY2());
        return false;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (this == other) return true;
        if (other == null ) return false;
        if(other instanceof Point)
            return ((((Point) other).getX() >= this.getX1() & ((Point) other).getX() <= this.getX2()) &
                    (((Point) other).getY() >= this.getY1() & ((Point) other).getY() <= this.getY2()));
        if(other instanceof Geometry)
            return ((((Geometry) other).getX1() >= this.getX1() & ((Geometry) other).getX1() <= this.getX2()) &
                    (((Geometry) other).getY1() >= this.getY1() & ((Geometry) other).getY1() <= this.getY2())|
                    (((Geometry) other).getX2() >= this.getX1() & ((Geometry) other).getX2() <= this.getX2()) &
                    (((Geometry) other).getY2() >= this.getY1() & ((Geometry) other).getY2() <= this.getY2())

//                    (((Geometry) other).getX1() <= this.getX1() & ((Geometry) other).getX1() >= this.getX2()) &
//                    (((Geometry) other).getY1() <= this.getY1() & ((Geometry) other).getY1() >= this.getY2())|
//                    (((Geometry) other).getX2() <= this.getX1() & ((Geometry) other).getX2() >= this.getX2()) &
//                    (((Geometry) other).getY2() <= this.getY1() & ((Geometry) other).getY2() >= this.getY2())
            );
        return false;
    }

}

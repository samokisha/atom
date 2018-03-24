package ru.atom.geometry;

import java.util.Objects;

public class Bar implements Collider {

    private final Point point1;
    private final Point point2;

    public Bar(final Point point1, final Point point2) {
        this.point1 = new Point(Integer.min(point1.getX(), point2.getX()), Integer.min(point1.getY(), point2.getY()));
        this.point2 = new Point(Integer.max(point1.getX(), point2.getX()), Integer.max(point1.getY(), point2.getY()));
    }

    @Override
    public boolean isColliding(Collider other) {
        if (this.equals(other)) return true;
        if (other instanceof Point) {
            Point point = ((Point) other);
            return point1.getX() <= point.getX() && point.getX() <= point2.getX()
                      && point1.getY() <= point.getY() && point.getY() <= point2.getY();
        } else {
            Bar bar = ((Bar) other);
            return (bar.point1.getX() <= this.point2.getX() && bar.point2.getX() >= this.point1.getX())
                      && (bar.point1.getY() <= this.point2.getY() && bar.point2.getY() >= this.point1.getY());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bar bar = (Bar) o;
        return (Objects.equals(point1, bar.point1)
                  && Objects.equals(point2, bar.point2));
    }
}

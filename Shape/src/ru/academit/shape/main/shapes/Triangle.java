package ru.academit.shape.main.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        return 0.5 * ((x1 - x3) * (y2 - y3) + (x2 - x3) * (y1 - y3));
    }

    private static double getSideOfTriangle(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    @Override
    public double getPerimeter() {
        return getSideOfTriangle(x1, y1, x2, y2) + getSideOfTriangle(x2, y2, x3, y3) + getSideOfTriangle(x1, y1, x3, y3);
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) object;
        return (x1 == triangle.x1) && (y1 == triangle.y1) && (x2 == triangle.x2) && (y2 == triangle.y2) && (x3 == triangle.x3) && (y3 == triangle.y3);
    }

    @Override
    public int hashCode() {
        return (int) (x1 * y1 * x2 * y2 * x3 * y3);
    }
}

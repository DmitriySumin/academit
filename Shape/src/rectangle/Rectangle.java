package rectangle;

import interfaceShape.interfaceShape;

public class Rectangle implements interfaceShape.Shape {
    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Ширина: " + width + "Высота: " + height;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) object;
        return (width == rectangle.width) && (height == rectangle.height);
    }

    @Override
    public int hashCode(){
        return (int) (width * height);
    }
}

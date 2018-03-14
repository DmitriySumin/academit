package square;

import interfaceShape.interfaceShape;

public class Square implements interfaceShape.Shape {
    double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "Ширина квадрата: " + side;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Square side1 = (Square) object;
        return (side == side1.side);
    }

    @Override
    public int hashCode() {
        return (int) side;
    }
}

package ru.academit.shape;

import comparator.CompareToArea;
import comparator.CompareToPerimeter;
import shapes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Circle circleSmall = new Circle(2);
        Circle circleBig = new Circle(8);
        Rectangle rectangle = new Rectangle(2, 3);
        Square squareSmall = new Square(1);
        Square squareBig = new Square(3);
        Triangle triangle = new Triangle(1, 2, 4, 1, 5, 8);
        ArrayList<InterfaceShape> notSortedShapes = new ArrayList<InterfaceShape>();
        notSortedShapes.add(circleSmall);
        notSortedShapes.add(circleBig);
        notSortedShapes.add(rectangle);
        notSortedShapes.add(squareSmall);
        notSortedShapes.add(squareBig);
        notSortedShapes.add(triangle);
        notSortedShapes.sort(new CompareToArea());
        notSortedShapes.sort(new CompareToPerimeter());

        System.out.println("Максимальная площадь: " + notSortedShapes.get(0).getArea());
        System.out.println("Второй по величине периметр: " + notSortedShapes.get(1).getPerimeter());
    }
}

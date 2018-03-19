package comparator;

import shapes.InterfaceShape;

import java.util.Comparator;


public class CompareToArea implements Comparator<InterfaceShape> {
    public int compare(InterfaceShape shapeX, InterfaceShape shapeY) {
        return Double.compare(shapeY.getArea(), shapeX.getArea());
    }
}

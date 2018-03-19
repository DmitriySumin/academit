package comparator;

import shapes.InterfaceShape;

import java.util.Comparator;

public class CompareToPerimeter implements Comparator<InterfaceShape> {
    public int compare(InterfaceShape shapeX, InterfaceShape shapeY) {
        return Double.compare(shapeY.getPerimeter(), shapeX.getPerimeter());
    }
}

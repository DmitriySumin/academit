package comparator;

import ru.academit.shape.main.shapes.Shape;

import java.util.Comparator;


public class CompareToArea implements Comparator<Shape> {
    public int compare(Shape shapeX, Shape shapeY) {
        return Double.compare(shapeY.getArea(), shapeX.getArea());
    }
}

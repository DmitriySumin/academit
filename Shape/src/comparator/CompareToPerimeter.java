package comparator;

import interfaceShape.interfaceShape;

import java.util.Comparator;

public class CompareToPerimeter implements Comparator<interfaceShape.Shape> {
    public int compare(interfaceShape.Shape shapeX, interfaceShape.Shape shapeY) {
        if (shapeX.getPerimeter() > shapeY.getPerimeter()){
            return -1;
        } else if(shapeX.getPerimeter() < shapeY.getPerimeter()){
            return 1;
        } else {
            return 0;
        }
    }
}

package comparator;
import java.util.Comparator;
import interfaceShape.interfaceShape;


public class CompareToArea implements Comparator<interfaceShape.Shape> {
    public int compare(interfaceShape.Shape shapeX, interfaceShape.Shape shapeY) {
        if (shapeX.getArea() > shapeY.getArea()){
            return -1;
        } else if(shapeX.getArea() < shapeY.getArea()){
            return 1;
        } else {
            return 0;
        }
    }
}

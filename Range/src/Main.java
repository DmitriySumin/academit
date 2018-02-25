import ru.academits.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(2, 10);
        Range range1 = new Range(1, 15);
        System.out.println("Число входит в заданный диапазон: " + range.isInside(1));
        System.out.println("длина диапазона: " + range.getLength());
        //System.out.println(range.getIntersection(range1));
        Object[] array = range.getUnionRange(range1);
        for (Object e : array) {
            System.out.println(e);
        }
    }
}

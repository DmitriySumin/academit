import ru.academits.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(5, 8);
        Range range1 = new Range(1, 10);
        System.out.println("Число входит в заданный диапазон: " + range.isInside(1));
        System.out.println("длина диапазона: " + range.getLength());
        //System.out.println(range.getIntersection(range1));
        Range[] array = range.getDifferenceRange(range1);
        for (Range e : array) {
            System.out.println(e);
        }
    }
}

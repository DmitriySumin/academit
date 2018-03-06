package newPackage;

import ru.academits.range.work.Range;

public class Main {
    public static void main(String[] args) {
        /*Range range = new Range(5, 9);
        Range range1 = new Range(5, 7);
        System.out.println("Число входит в заданный диапазон: " + range.isInside(1));
        System.out.println("длина диапазона: " + range.getLength());
        System.out.println(range.getIntersection(range1));
        Range[] array = range.getDifferenceRange(range1);*/

        Range range = new Range(5, 7);
        Range range1 = new Range(1, 6);
        Range[] array = range.getDifferenceRange(range1);
        for (Range e : array) {
            System.out.println(e);
        }
    }
}

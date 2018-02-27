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
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0, j = a.length - 1; i < (a.length) / 2; ++i, --j) {
            Range range = new Range(a[i], a[j]);
            Range range1 = new Range(2, 7);
            Range[] array = range.getDifferenceRange(range1);
            for (Range e : array) {
                System.out.println(e);
            }
            System.out.println();
        }
    }
}

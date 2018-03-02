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
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0, j = a.length - 1; i < (a.length) / 2; ++i, --j) {
            Range range = new Range(a[i], a[j]);
            Range range1 = new Range(3, 6);
            Range[] array = range.getDifferenceRange(range1);
            if (i == 0) {
                System.out.println("Из диапазона от 1 до 9, вычитаю диапазон от 3 до 6. Ожидаю получить два интервала. Первый от 1 до 2, втрой от 7 до 9");
            } else if (i == 1) {
                System.out.println("Из диапазона от 2 до 8, вычитаю диапазон от 3 до 6. Ожидаю получить два интервала. Первый от 2 до 2, втрой от 7 до 8");
            } else if (i == 2) {
                System.out.println("Из диапазона от 3 до 7, вычитаю диапазон от 3 до 6. Ожидаю получить итервал от 7 до 7");
            } else if (i == 3) {
                System.out.println("Из диапазона от 3 до 6, вычитаю диапазон от 4 до 6. Ожидаю получить интервал от 3 до 3");
            }
            for (Range e : array) {
                System.out.println(e);
            }
            System.out.println();
        }
    }
}

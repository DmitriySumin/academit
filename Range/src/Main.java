public class Main {
    private static double[] getIntersection(double[] array, double[] array1) {
        if ((array1[0] > array[0]) && (array1[1] < array[1])) {
            array[0] = array1[0];
            array[1] = array1[1];
            return array;
        } else if ((array[0] > array1[0]) && (array[1] < array1[1])) {
            return array;
        } else if ((array[0] < array1[0]) && (array1[0] < array[1])) {
            array[0] = array1[0];
            return array;
        } else if ((array1[0] < array[0]) && (array[0] < array1[1])) {
            array[1] = array1[1];
            return array;
        }
        return null;
    }

    public static void main(String[] args) {
        Range range = new Range(1, 9);
        System.out.println("Число входит в заданный диапазон: " + range.isInside(1));
        System.out.println("длина диапазона: " + range.getLength());
        Range range1 = new Range(3, 12);
        double[] array = getIntersection(range.getRange(), range1.getRange());
        for (double e : array) {
            System.out.println(e);
        }
    }
}

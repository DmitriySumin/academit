public class Main {
    public static void main(String[] args) {
        Range range = new Range(10, 15, 14, 20);
        System.out.println("Число входит в заданные диапазоны: " + range.isInside(17));
        double [] array = range.getLength();
        for (double e:array) {
            System.out.println(e + " - длина диапазона ");
        }
    }
}

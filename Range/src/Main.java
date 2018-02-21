public class Main {
    public static void main(String[] args) {
        Range range = new Range(5, 9);
        System.out.println("Число входит в заданный диапазон: " + range.isInside(1));
        System.out.println("длина диапазона: " + range.getLength());
        range.rangeIntersection(3, 6);
    }
}

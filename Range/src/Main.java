public class Main {
    public static void main(String[] args) {
        Range range = new Range(1, 3);
        Range range1 = new Range(6, 7);
        System.out.println("Число входит в заданный диапазон: " + range.isInside(1));
        System.out.println("длина диапазона: " + range.getLength());
        System.out.println(range.getIntersection(range1));
    }
}

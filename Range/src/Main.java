public class Main {
    public static void main(String[] args) {
        Range range = new Range(10, 15);
        System.out.println("Число входит в заданный диапазон: " + range.isInside(17));
        System.out.println("длина диапазона: " + range.getLength());
    }
}

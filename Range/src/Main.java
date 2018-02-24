public class Main {
    public static void main(String[] args) {
        Range range = new Range(1, 4);
        Range range1 = new Range(3, 20);
        System.out.println("Число входит в заданный диапазон: " + range.isInside(1));
        System.out.println("длина диапазона: " + range.getLength());
        Object[] array = range.getAssociation(range1);
        for (Object e : array) {
            System.out.println(e);
        }
    }
}

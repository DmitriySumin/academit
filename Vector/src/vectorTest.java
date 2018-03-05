public class vectorTest {
    public static void main(String[] args) {
        Vector vector = new Vector (5, new double[]{2, 4, 6 ,8 ,10});
        Vector vector1 = new Vector(5, new double[]{1, 2, 3, 4, 5});
        System.out.println(vector.getTurnVector());
    }
}

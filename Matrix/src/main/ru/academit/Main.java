package main.ru.academit;

import matrix.ru.academit.Matrix;
import ru.academit.vector.Vector;

public class Main {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(new double[][]{{1, -1}, {2, 0}, {3, 0}});
        Vector vector = new Vector(new double[]{1, 1});
        Vector vector1 = new Vector(new double[]{2, 0});
        Vector vector2 = new Vector(new double[]{7, 8, 9});
        Matrix matrix1 = new Matrix(new Vector[]{vector, vector1});
        System.out.println(matrix.getTranspose());
    }
}

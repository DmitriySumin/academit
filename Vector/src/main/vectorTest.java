package main;

import ru.academit.vector.Vector;

public class vectorTest {
    public static double[] getAddVectors(Vector vector, Vector vector1) {
        double[] array;
        if (vector.getSize() > vector1.getSize()) {
            array = new double[vector.getSize()];
            for (int i = 0; i < vector.getSize(); ++i) {
                array[i] = vector.getElement(i) + vector1.getElement(i);
            }
        } else if (vector.getSize() < vector1.getSize()) {
            array = new double[vector1.getSize()];
            for (int i = 0; i < vector1.getSize(); ++i) {
                array[i] = vector.getElement(i) + vector1.getElement(i);
            }
        } else {
            array = new double[vector.getSize()];
            for (int i = 0; i < vector.getSize(); ++i) {
                array[i] = vector.getElement(i) + vector1.getElement(i);
            }
        }
        return array;
    }

    public static double[] getDiffVectors(Vector vector, Vector vector1) {
        double[] array;
        if (vector.getSize() > vector1.getSize()) {
            array = new double[vector.getSize()];
            for (int i = 0; i < vector.getSize(); ++i) {
                array[i] = vector.getElement(i) - vector1.getElement(i);
            }
        } else if (vector.getSize() < vector1.getSize()) {
            array = new double[vector1.getSize()];
            for (int i = 0; i < vector1.getSize(); ++i) {
                array[i] = vector.getElement(i) - vector1.getElement(i);
            }
        } else {
            array = new double[vector.getSize()];
            for (int i = 0; i < vector.getSize(); ++i) {
                array[i] = vector.getElement(i) - vector1.getElement(i);
            }
        }
        return array;
    }

    public static double[] getMultByScalar(Vector vector, int n) {
        double[] array = new double[vector.getSize()];
        for (int i = 0; i < vector.getSize(); ++i) {
            array[i] = vector.getElement(i) * n;
        }
        return array;
    }

    public static void main(String[] args) {
        Vector vector = new Vector(new double[]{1, 2});
        Vector vector1 = new Vector(new double[]{1, 2, 3});
        // System.out.println(vector.getDifferencesVectors(vector1));
        System.out.println(vector1.getLength());
        System.out.println(vector.equals(vector1));
    }
}

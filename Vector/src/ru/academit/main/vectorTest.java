package ru.academit.main;

import java.util.Arrays;

public class vectorTest {
    public static double[] getAddVectors(Vector vector, Vector vector1) {
        Vector vector2 = new Vector(vector);
        Vector vector3 = new Vector(vector1);
        double[] array;
        if (vector2.getSize() > vector3.getSize()) {
            array = new double[vector2.getSize()];
            for (int i = 0; i < vector2.getSize(); ++i) {
                array[i] = vector2.getVectorIndex(i) + vector3.getVectorIndex(i);
            }
        } else if (vector2.getSize() < vector3.getSize()) {
            array = new double[vector3.getSize()];
            for (int i = 0; i < vector3.getSize(); ++i) {
                array[i] = vector2.getVectorIndex(i) + vector3.getVectorIndex(i);
            }
        } else {
            array = new double[vector2.getSize()];
            for (int i = 0; i < vector2.getSize(); ++i) {
                array[i] = vector2.getVectorIndex(i) + vector3.getVectorIndex(i);
            }
        }
        return array;
    }

    public static double[] getDiffVectors(Vector vector, Vector vector1) {
        Vector vector2 = new Vector(vector);
        Vector vector3 = new Vector(vector1);
        double[] array;
        if (vector2.getSize() > vector3.getSize()) {
            array = new double[vector2.getSize()];
            for (int i = 0; i < vector2.getSize(); ++i) {
                array[i] = vector2.getVectorIndex(i) - vector3.getVectorIndex(i);
            }
        } else if (vector2.getSize() < vector3.getSize()) {
            array = new double[vector3.getSize()];
            for (int i = 0; i < vector3.getSize(); ++i) {
                array[i] = vector2.getVectorIndex(i) - vector3.getVectorIndex(i);
            }
        } else {
            array = new double[vector2.getSize()];
            for (int i = 0; i < vector2.getSize(); ++i) {
                array[i] = vector2.getVectorIndex(i) - vector3.getVectorIndex(i);
            }
        }
        return array;
    }

    public static double[] getMultByScalar(Vector vector, int n) {
        double[] array = new double[vector.getSize()];
        Vector vector1 = new Vector(vector);
        for (int i = 0; i < vector1.getSize(); ++i) {
            array[i] = vector1.getVectorIndex(i) * n;
        }
        return array;
    }

    public static void main(String[] args) {
        Vector vector = new Vector(new double[]{2, 4, 6});
        Vector vector1 = new Vector(new double[]{1, 2, 3});
        //System.out.println(vector.getAdditionVectors(vector1));
        System.out.println(Arrays.toString(getDiffVectors(vector, vector1)));
        System.out.println(Arrays.toString(getMultByScalar(vector, 2)));
    }
}

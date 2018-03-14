package main;

import ru.academit.vector.Vector;

public class vectorTest {
    //статический метод сложения двух векторов
    public static Vector getAdditive(Vector vectorX, Vector vectorY) {
        if (vectorX.getSize() > vectorY.getSize()) {
            for (int i = 0; i < vectorY.getSize(); ++i) {
                vectorX.setElement(i, vectorX.getElement(i) + vectorY.getElement(i));
            }
            return vectorX;
        } else if (vectorX.getSize() < vectorY.getSize()) {
            for (int i = 0; i < vectorX.getSize(); ++i) {
                vectorY.setElement(i, vectorX.getElement(i) + vectorY.getElement(i));
            }
            return vectorY;
        } else {
            for (int i = 0; i < vectorX.getSize(); ++i) {
                vectorX.setElement(i, vectorX.getElement(i) + vectorY.getElement(i));
            }
            return vectorX;
        }
    }

    public static Vector getDifference(Vector vectorX, Vector vectorY) {
        //вычитание векторов
        if (vectorX.getSize() > vectorY.getSize()) {
            for (int i = 0; i < vectorY.getSize(); ++i) {
                vectorX.setElement(i, vectorX.getElement(i) - vectorY.getElement(i));
            }
            return vectorX;
        } else if (vectorX.getSize() < vectorY.getSize()) {
            for (int i = 0; i < vectorX.getSize(); ++i) {
                vectorY.setElement(i, vectorX.getElement(i) - vectorY.getElement(i));
            }
            for (int i = vectorX.getSize(); i < vectorY.getSize(); ++i) {
                vectorY.setElement(i, vectorY.getElement(i) * -1);
            }
            return vectorY;
        } else {
            for (int i = 0; i < vectorX.getSize(); ++i) {
                vectorX.setElement(i, vectorX.getElement(i) - vectorY.getElement(i));
            }
            return vectorX;
        }
    }

    public static int getScalarProductVectors(Vector vectorX, Vector vectorY) {
        //скалярное произведение векторов
        int result = 0;
        if (vectorX.getSize() > vectorY.getSize()) {
            for (int i = 0; i < vectorY.getSize(); ++i) {
                result += vectorX.getElement(i) * vectorY.getElement(i);
            }
        } else if (vectorX.getSize() < vectorY.getSize()) {
            for (int i = 0; i < vectorX.getSize(); ++i) {
                result += vectorX.getElement(i) * vectorY.getElement(i);
            }
        } else {
            for (int i = 0; i < vectorX.getSize(); ++i) {
                result += vectorX.getElement(i) * vectorY.getElement(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Vector vector = new Vector(new double[]{1, 2, 3});
        Vector vector1 = new Vector(new double[]{1, 2, 3});
        //System.out.println(vector.getDifferences(vector1));
        //System.out.println(vector.getMultiplicationScalar(2));
        //System.out.println(vector.equals(vector1));
        System.out.println(getScalarProductVectors(vector, vector1));
    }
}

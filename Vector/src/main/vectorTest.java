package main;

import ru.academit.vector.Vector;

public class vectorTest {
    public static Vector getAddVectors(Vector vectorX, Vector vectorY) {
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

    public static Vector getDiffVectors(Vector vectorX, Vector vectorY) {
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

    public static int getScalarProductOfVectors(Vector vectorX, Vector vectorY) {
        if (vectorX.getSize() > vectorY.getSize()) {
            int result = 0;
            for (int i = 0; i < vectorY.getSize(); ++i) {
                result += vectorX.getElement(i) * vectorY.getElement(i);
            }
            return result;
        } else if (vectorX.getSize() < vectorY.getSize()) {
            int result = 0;
            for (int i = 0; i < vectorX.getSize(); ++i) {
                result += vectorX.getElement(i) * vectorY.getElement(i);
            }
            return result;
        } else {
            int result = 0;
            for (int i = 0; i < vectorX.getSize(); ++i) {
                result += vectorX.getElement(i) * vectorY.getElement(i);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Vector vector = new Vector(new double[]{1, 2, 3});
        Vector vector1 = new Vector(new double[]{1, 2});
        System.out.println(vector.getDifferencesVectors(vector1));
        //System.out.println(vector.getMultiplicationByScalar(2));
        //System.out.println(vector.equals(vector1));
        //System.out.println(getDiffVectors(vector, vector1));
    }
}

import java.util.Arrays;

public class Vector {
    private int n;
    private double [] array;

    public Vector(int dimension) {
        this.n = dimension;
        this.array = new double[dimension];
        for (int i = 0; i < array.length; ++i) {
            this.array[i] = 0;
        }
    }
    public Vector (Vector vector){
        array = new double[vector.array.length];
        for(int i = 0; i < vector.array.length; ++i){
            array[i] = vector.array[i];
        }
    }

    public Vector(double[] elements) {
        this.array = elements;
    }

    public Vector(int dimension, double[] elements) {
        this.n = dimension;
        this.array = new double[dimension];
        for (int i = 0; i < elements.length; ++i){
            array[i] = elements[i];
        }
        if (elements.length < n) {
            for (int i = elements.length; i < n; ++i) {
                array[i] = 0;
            }
        }
    }
    public int getSize(){
        return array.length;
    }
    public Vector getAdditionVectors(Vector vector){
        for (int i = 0; i < array.length; ++i){
            vector.array[i] = vector.array[i] + array[i];
        }
        return vector;
    }

    public Vector getDifferenceectors(Vector vector){
        for (int i = 0; i < array.length; ++ i){
            vector.array[i] = array[i] - vector.array[i];
        }
        return vector;
    }

    public Vector getMultiplicationByScalar(int scalar){
        for (int i = 0; i < array.length; ++i){
            array[i] = array[i] * scalar;
        }
        return new Vector(array);
    }

    public Vector getTurnVector(){
        for (int i = 0; i < array.length; ++i){
            array[i] = array[i] * -1;
        }
        return new Vector(array);
    }

    public String toString() {
        return Arrays.toString(array);
    }
}

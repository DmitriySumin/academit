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

    public String toString() {
        return Arrays.toString(array);
    }
}

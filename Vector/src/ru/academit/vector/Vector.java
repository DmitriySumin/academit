package ru.academit.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    //размерность n все компоненты равны 0
    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Ошибка, размерность вектора задана не верно.");
        }
        this.vector = new double[dimension];
    }

    //конструктор копирования
    public Vector(Vector vector) {
        this.vector = Arrays.copyOf(vector.vector, vector.vector.length);
    }

    //заполнение вектора значениями из массива
    public Vector(double[] elements) {
        if (elements.length == 0) {
            throw new IllegalArgumentException("Ошибка, не заданы элементы вектора.");
        }
        this.vector = Arrays.copyOf(elements, elements.length);
    }

    //заполнение вектора значениями из массива с заданием размерности вектора
    public Vector(int dimension, double[] elements) {
        if (dimension == 0) {
            throw new IllegalArgumentException("Ошибкаб, размерность вектора не может быть равна нулю.");
        }
        this.vector = Arrays.copyOf(elements, dimension);
    }

    //получение размерности вектора
    public int getSize() {
        return vector.length;
    }

    //сложение векторов
    public Vector getAddition(Vector vector) {
        if (this.vector.length < vector.vector.length) {
            this.vector = Arrays.copyOf(this.vector, vector.vector.length);
        }
        for (int i = 0; i < vector.vector.length; ++i) {
            this.vector[i] += vector.vector[i];
        }
        return this;
    }

    //вычитание векторов
    public Vector getDifferences(Vector vector) {
        double[] array = Arrays.copyOf(vector.vector, vector.vector.length);
        if (this.vector.length < vector.vector.length) {
            this.vector = Arrays.copyOf(this.vector, vector.vector.length);
        } else {
            array = Arrays.copyOf(vector.vector, this.vector.length);
        }
        for (int i = 0; i < this.vector.length; ++i) {
            this.vector[i] -= array[i];
        }

        return this;
    }

    //умножение вектора на скаляр
    public Vector getMultiplicationScalar(int scalar) {
        for (int i = 0; i < vector.length; ++i) {
            vector[i] = vector[i] * scalar;
        }
        return this;
    }

    //разворот вектора
    public Vector getTurn() {
        return getMultiplicationScalar(-1);
    }

    //длина вектора
    public double getVectorLength() {
        double result = 0;
        for (double e : vector) {
            result += e * e;
        }
        return Math.sqrt(result);
    }

    //получение значения вектора по индексу
    public double getElement(int index) {
        if ((index >= vector.length) || (index < 0)) {
            throw new IndexOutOfBoundsException("Ошибка, задан несуществующий индекс.");
        } else {
            return vector[index];
        }
    }

    //установка значения по индексу
    public void setElement(int index, double value) {
        if ((index >= vector.length) || (index < 0)) {
            throw new IndexOutOfBoundsException("Ошибка, задан несуществующий индес.");
        } else {
            vector[index] = value;
        }
    }

    //статический метод сложения векторов
    public static Vector getAddition(Vector vectorX, Vector vectorY) {
        Vector addition = new Vector(vectorX);
        return addition.getAddition(vectorY);
    }

    //статический метод вычитания векторов
    public static Vector getDifference(Vector vectorX, Vector vectorY) {
        Vector difference = new Vector(vectorX);
        return difference.getDifferences(vectorY);
    }

    //скалярное произведение векторов
    public static double getScalarProductVectors(Vector vectorX, Vector vectorY) {
        int result = 0;
        int length = Math.min(vectorX.getSize(), vectorY.getSize());
        for (int i = 0; i < length; ++i) {
            result += vectorX.getElement(i) * vectorY.getElement(i);
        }
        return result;
    }

    //переопределение equals
    @Override
    public boolean equals(Object vector) {
        if (vector == this) {
            return true;
        }
        if (vector == null || vector.getClass() != this.getClass()) {
            return false;
        }
        Vector vector1 = (Vector) vector;
        if (vector1.vector.length != this.vector.length) {
            return false;
        }
        for (int i = 0; i < vector1.vector.length; ++i) {
            if (vector1.vector[i] != this.vector[i]) {
                return false;
            }
        }
        return true;
    }

    //переопределение hashCode
    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        for (double e : vector) {
            result += PRIME * result + e;
        }
        return result;
    }

    //переопределение toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{  ");
        for (double e : vector) {
            builder.append(e).append("  ");
        }
        builder.append("}");
        return builder.toString();
    }

}

package ru.academit.vector;

import java.util.Arrays;

public class Vector {
    private int n;
    private double[] vector;

    //размерность n все компоненты равны 0
    public Vector(int dimension) {
        this.n = dimension;
        this.vector = new double[dimension];
        for (int i = 0; i < this.vector.length; ++i) {
            this.vector[i] = 0;
        }
    }

    //конструктор копирования
    public Vector(Vector vector) {
        this.vector = new double[vector.vector.length];
        for (int i = 0; i < vector.vector.length; ++i) {
            this.vector[i] = vector.vector[i];
        }
    }

    //заполнение вектора значениями из массива
    public Vector(double[] elements) {
        this.vector = elements;
    }

    //заполнение вектора значениями из массива с заданием размерности вектора
    public Vector(int dimension, double[] elements) {
        if (dimension < elements.length) {
            throw new IllegalArgumentException("Ошибка, размерность вектора задана не верно.");
        }
        this.n = dimension;
        this.vector = new double[dimension];
        for (int i = 0; i < elements.length; ++i) {
            vector[i] = elements[i];
        }
        if (elements.length < n) {
            for (int i = elements.length; i < n; ++i) {
                vector[i] = 0;
            }
        }
    }

    //получение размерности вектора
    public int getSize() {
        return vector.length;
    }

    //сложение векторов
    public Vector getAdditionVectors(Vector vector) {
        if (this.vector.length > vector.vector.length) {
            double[] newVector = new double[this.vector.length];
            for (int i = 0; i < vector.vector.length; ++i) {
                newVector[i] = vector.vector[i];
            }
            for (int i = 0; i < this.vector.length; ++i) {
                this.vector[i] = this.vector[i] + newVector[i];
            }
        } else if (this.vector.length < vector.vector.length) {
            double[] newVector = this.vector;
            this.vector = new double[vector.vector.length];
            for (int i = 0; i < newVector.length; ++i) {
                this.vector[i] = newVector[i];
            }
            for (int i = 0; i < vector.vector.length; ++i) {
                this.vector[i] = this.vector[i] + vector.vector[i];
            }
        } else {
            for (int i = 0; i < vector.vector.length; ++i) {
                this.vector[i] = this.vector[i] + vector.vector[i];
            }
        }
        return new Vector(this.vector);
    }

    //вычитание векторов
    public Vector getDifferencesVectors(Vector vector) {
        if (this.vector.length > vector.vector.length) {
            double[] newVector = new double[this.vector.length];
            for (int i = 0; i < vector.vector.length; ++i) {
                newVector[i] = vector.vector[i];
            }
            for (int i = 0; i < this.vector.length; ++i) {
                this.vector[i] = this.vector[i] - newVector[i];
            }
        } else if (this.vector.length < vector.vector.length) {
            double[] newVector = this.vector;
            this.vector = new double[vector.vector.length];
            for (int i = 0; i < newVector.length; ++i) {
                this.vector[i] = newVector[i];
            }
            for (int i = 0; i < vector.vector.length; ++i) {
                this.vector[i] = this.vector[i] - vector.vector[i];
            }
        } else {
            for (int i = 0; i < vector.vector.length; ++i) {
                this.vector[i] = this.vector[i] - vector.vector[i];
            }
        }
        return new Vector(this.vector);
    }

    //умножение вектора на скаляр
    public Vector getMultiplicationByScalar(int scalar) {
        for (int i = 0; i < vector.length; ++i) {
            vector[i] = vector[i] * scalar;
        }
        return new Vector(vector);
    }

    //разворот вектора
    public Vector getTurnVector() {
        for (int i = 0; i < vector.length; ++i) {
            vector[i] = vector[i] * -1;
        }
        return new Vector(vector);
    }

    //длина вектора
    public double getLengthVector() {
        double length = 0;
        for (int i = 0; i < vector.length; ++i) {
            length += vector[i];
        }
        return length;
    }

    //получение значения вектора по индексу
    public double getVectorIndex(int e) {
        for (int i = 0; i < vector.length; ++i) {
            if (i == e) {
                return vector[i];
            }
        }
        return 0;
    }

    //установка значения по индексу
    public Vector getElementOperation(int n, double number) {
        for (int i = 0; i < vector.length; ++i) {
            if (i == n) {
                vector[i] = number;
            }
        }
        return new Vector(vector);
    }

    //переопределение equals
    public boolean equals(Vector vector) {
        if (vector == null) {
            return false;
        }
        if (vector == this) {
            return true;
        }
        if (getClass() != vector.getClass()) {
            return false;
        }
        Vector vector1 = (Vector) vector;
        for (int i = 0; i < vector.vector.length; ++i) {
            if (this.vector[i] != vector1.vector[i]) {
                return false;
            }
        }
        return true;
    }

    //переопределение hashCode
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        for (int i = 0; i < vector.length; ++i) {
            result += PRIME * result + vector[i];
        }
        return result;
    }

    public String toString() {
        return Arrays.toString(vector);
    }
}

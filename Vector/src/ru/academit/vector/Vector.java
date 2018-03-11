package ru.academit.vector;

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
        this.vector = new double[vector.vector.length];
        System.arraycopy(vector.vector, 0, this.vector, 0, vector.vector.length);
    }

    //заполнение вектора значениями из массива
    public Vector(double[] elements) {
        if (elements.length == 0) {
            throw new IllegalArgumentException("Ошибка, не заданы элементы вектора.");
        }
        this.vector = elements;
    }

    //заполнение вектора значениями из массива с заданием размерности вектора
    public Vector(int dimension, double[] elements) {
        if (dimension < elements.length) {
            throw new IllegalArgumentException("Ошибка, размерность вектора задана не верно.");
        }
        this.vector = new double[dimension];
        System.arraycopy(elements, 0, vector, 0, elements.length);
        if (elements.length < dimension) {
            for (int i = elements.length; i < dimension; ++i) {
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
            System.arraycopy(vector.vector, 0, newVector, 0, vector.vector.length);
            for (int i = 0; i < this.vector.length; ++i) {
                this.vector[i] = this.vector[i] + newVector[i];
            }
        } else if (this.vector.length < vector.vector.length) {
            double[] newVector = this.vector;
            this.vector = new double[vector.vector.length];
            System.arraycopy(newVector, 0, this.vector, 0, newVector.length);
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
            System.arraycopy(vector.vector, 0, newVector, 0, vector.vector.length);
            for (int i = 0; i < this.vector.length; ++i) {
                this.vector[i] = this.vector[i] - newVector[i];
            }
        } else if (this.vector.length < vector.vector.length) {
            double[] newVector = this.vector;
            this.vector = new double[vector.vector.length];
            System.arraycopy(newVector, 0, this.vector, 0, newVector.length);
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
    public double getLength() {
        double length = 0;
        for (double e : vector) {
            length += e;
        }
        return length;
    }

    //получение значения вектора по индексу
    public double getElement(int index) {
        if (index < vector.length) {
            return vector[index];
        } else {
            return 0;
        }
    }

    //установка значения по индексу
    public Vector getReplaceElement(int index, double value) {
        if (index < vector.length) {
            vector[index] = value;
        } else {
            throw new IllegalArgumentException("Ошибка, задан несуществующий индес.");
        }
        return new Vector(vector);
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
        for (double e : vector) {
            builder.append(e).append(" ");
        }
        return "{" + builder + "}";
    }

}

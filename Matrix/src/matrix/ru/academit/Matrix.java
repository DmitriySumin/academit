package matrix.ru.academit;


import java.util.Arrays;

public class Matrix extends Vector {
    private double[][] matrix;

    //размерность n на m, все компоненты равны 0
    public Matrix(int n, int m) {
        this.matrix = new double[n][m];
    }

    //конструктор копирования
    public Matrix(Matrix matrix) {
        this.matrix = Arrays.copyOf(matrix.matrix, matrix.matrix.length);
    }

    //из двумерного массива
    public Matrix(double[][] array) {
        this.matrix = new double[array.length][array[0].length];
        for (int i = 0; i < array.length; ++i) {
            this.matrix[i] = Arrays.copyOf(array[i], array[i].length);
        }
    }

    //из массива векторов-строк
    public Matrix(Vector[] vector) {
        this.matrix = new double[vector.length][vector[0].getSize()];
        for (int i = 0; i < vector.length; ++i) {
            for (int j = 0; j < vector[i].getSize(); ++j) {
                this.matrix[i][j] = vector[i].getElement(j);
            }
        }
    }

    //получение размеров матрицы
    public double[] getsize() {
        double[] array = new double[2];
        array[0] = this.matrix.length;
        array[1] = this.matrix[0].length;
        return array;
    }

    //получчение вектора строки по индексу
    public double[] getVectorString(int index) {
        if (index > this.matrix.length - 1) {
            throw new IllegalArgumentException("Ошибкаб, задан неверный индекс.");
        }
        return Arrays.copyOf(this.matrix[index], this.matrix[index].length);
    }

    //задание вектора строки по индексу
    public Matrix setVectorString(int index, double[] array) {
        if (array.length > this.matrix[index].length) {
            throw new IllegalArgumentException("Ошибкаб, колличество элементов вектоа не может быть больше количества элементов стргоки матрицы.");
        }
        this.matrix[index] = Arrays.copyOf(array, this.matrix[index].length);
        return this;
    }

    //получение вектора-столбца по индексу
    public double[] getVectorColumn(int index) {
        if (index > this.matrix.length || index < 0) {
            throw new IndexOutOfBoundsException("Ошибка, задан неверный индекс.");
        }
        double[] array = new double[this.matrix.length];
        for (int i = 0; i < this.matrix.length; ++i) {
            array[i] = this.matrix[i][index];
        }
        return array;
    }

    //транспонирование матрицы
    public Matrix getTranspose() {
        Matrix matrix = new Matrix(this.matrix[0].length, this.matrix.length);
        for (int i = 0; i < matrix.matrix.length; ++i) {
            for (int j = 0; j < matrix.matrix[i].length; ++j) {
                matrix.matrix[i][j] = this.matrix[j][i];
            }
        }
        return matrix;
    }

    //умножение матрицы на скаляр
    public Matrix getMultiplicationScalar(int scalar) {
        for (int i = 0; i < this.matrix.length; ++i) {
            for (int j = 0; j < this.matrix[i].length; ++j) {
                this.matrix[i][j] *= scalar;
            }
        }
        return this;
    }

    //изменение элемента матрицы
    private void setElement(int indexRow, int indexColumn, double value) {
        if ((indexRow > this.matrix.length - 1) || (indexColumn > this.matrix[0].length)) {
            throw new IllegalArgumentException("Ошибкаб, неверно задан индекс.");
        }
        matrix[indexRow][indexColumn] = value;
    }

    //получение элемента матрицы
    private double getElement(int indexRow, int indexColumn) {
        if ((indexRow > this.matrix.length - 1) || (indexColumn > this.matrix[0].length)) {
            throw new IllegalArgumentException("Ошибкаб, неверно задан индекс.");
        }
        return matrix[indexRow][indexColumn];
    }

    //нахождение определителя матрицы
    public double determinant() {
        Matrix temp;
        double result = 0;

        if (matrix.length == 2) {
            result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
            return result;
        }

        for (int i = 0; i < matrix[0].length; ++i) {
            temp = new Matrix(new double[matrix.length - 1][matrix[0].length - 1]);
            for (int j = 1; j < matrix.length; ++j) {
                for (int k = 0; k < matrix[0].length; ++k) {
                    if (k < i) {
                        temp.setElement(j - 1, k, matrix[j][k]);
                    } else if (k > i) {
                        temp.setElement(j - 1, k - 1, matrix[j][k]);
                    }
                }
            }

            result += matrix[0][i] * Math.pow(-1, (double) i) * temp.determinant();
        }
        return result;
    }

    //умножение матрицы на вектор
    public Vector getMultiplicationVector(Vector vector) {
        Vector result = new Vector(vector.getSize());
        double[] calculation = new double[vector.getSize()];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                matrix[i][j] = matrix[i][j] * vector.getElement(j);
                calculation[i] += matrix[i][j];
            }
            result.setElement(i, calculation[i]);
        }
        return result;
    }

    //сложение матриц
    public Matrix getAddition(Matrix matrix) {
        for (int i = 0; i < this.matrix.length; ++i) {
            for (int j = 0; j < this.matrix[0].length; ++j) {
                this.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j));
            }
        }
        return this;
    }

    //вычитание матриц
    public Matrix getDifference(Matrix matrix) {
        for (int i = 0; i < this.matrix.length; ++i) {
            for (int j = 0; j < this.matrix[0].length; ++j) {
                this.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j) * -1);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{{ ");
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                stringBuilder.append(matrix[i][j]).append(" ");
                if ((j == matrix[i].length - 1) && (i != matrix.length - 1)) {
                    stringBuilder.append("}, { ");
                }
            }
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }
}

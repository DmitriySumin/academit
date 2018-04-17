package matrix.ru.academit;

import ru.academit.vector.Vector;

public class Matrix {
    private Vector[] matrixData;

    //размерность column на row, все компоненты равны 0
    public Matrix(int column, int row) {
        if (column == 0 || row == 0) {
            throw new IllegalArgumentException();
        }
        matrixData = new Vector[column];
        for (int i = 0; i < matrixData.length; ++i) {
            matrixData[i] = new Vector(row);
        }
    }

    //конструктор копирования
    public Matrix(Matrix matrix) {
        this.matrixData = new Vector[matrix.matrixData.length];
        for (int i = 0; i < matrix.matrixData.length; ++i) {
            //noinspection unchecked
            this.matrixData[i] = new Vector(matrix.matrixData[i]);
        }
    }

    //из двумерного массива
    public Matrix(double[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException();
        }
        matrixData = new Vector[array.length];
        int count = 0;
        for (double[] anArray : array) {
            if (count < anArray.length) {
                count = anArray.length;
            }
        }
        for (int i = 0; i < array.length; ++i) {
            matrixData[i] = new Vector(count);
            for (int j = 0; j < array[i].length; ++j) {
                this.setElement(i, j, array[i][j]);
            }
        }
    }

    //из массива векторов-строк
    public Matrix(Vector[] vector) {
        matrixData = new Vector[vector.length];
        if (matrixData.length == 0) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for (Vector aVector : vector) {
            if (count < aVector.getSize()) {
                count = aVector.getSize();
            }
        }
        for (int i = 0; i < vector.length; ++i) {
            matrixData[i] = new Vector(count);
            for (int j = 0; j < vector[i].getSize(); ++j) {
                this.setElement(i, j, vector[i].getElement(j));
            }
        }
    }


    //получение размеров матрицы
    public int getCountRow() {
        return this.matrixData.length;
    }

    public int getCountColumn() {
        return matrixData[0].getSize();
    }

    //получчение вектора строки по индексу
    public Vector getMatrixRow(int index) {
        if (index >= this.getCountRow()) {
            throw new IndexOutOfBoundsException("Ошибка, задан неверный индекс.");
        }
        return new Vector(matrixData[index]);
    }

    //задание вектора строки по индексу
    public void setRowMatrix(int index, Vector vector) {
        if (vector.getSize() > matrixData[0].getSize()) {
            throw new IndexOutOfBoundsException("Ошибка, неверное колличество элементов вектора");
        }
        matrixData[index] = new Vector(vector);
    }

    //получение вектора-столбца по индексу
    public Vector getMatrixColumn(int index) {
        if (index > getCountColumn() || index < 0) {
            throw new IndexOutOfBoundsException("Ошибка, задан неверный индекс.");
        }
        Vector vector = new Vector(this.getCountRow());
        for (int i = 0; i < this.getCountRow(); ++i) {
            vector.setElement(i, this.getElement(i, index));
        }
        return vector;
    }

    //транспонирование матрицы
    public Matrix getTranspose() {
        Matrix matrix = new Matrix(this.matrixData);
        for (int i = 0; i < this.getCountColumn(); ++i) {
            for (int j = 0; j < this.getCountRow(); ++j) {
                this.setElement(i, j, matrix.getElement(j, i));
            }
        }
        return this;
    }

    //умножение матрицы на скаляр
    public Matrix getMultiplicationScalar(int scalar) {
        for (int i = 0; i < this.getCountRow(); ++i) {
            for (int j = 0; j < this.getCountColumn(); ++j) {
                this.setElement(i, j, this.getElement(i, j) * scalar);
            }
        }
        return this;
    }

    //изменение элемента матрицы
    public void setElement(int indexRow, int indexColumn, double value) {
        if ((indexRow > this.getCountRow()) || (indexColumn > this.getCountColumn())) {
            throw new IllegalArgumentException("Ошибкаб, неверно задан индекс.");
        }
        this.matrixData[indexRow].setElement(indexColumn, value);
    }

    //получение элемента матрицы
    public double getElement(int indexRow, int indexColumn) {
        if ((indexRow > this.getCountRow()) || (indexColumn > this.getCountColumn())) {
            throw new IllegalArgumentException("Ошибкаб, неверно задан индекс.");
        }
        return this.matrixData[indexRow].getElement(indexColumn);
    }

    //нахождение определителя матрицы
    public double determinant() {
        if (this.getCountRow() != this.getCountColumn()) {
            throw new IllegalArgumentException("Ошибкаб, матрица должна быть квадратной");
        }
        double result = 0;

        if (matrixData.length == 2) {
            result = ((this.getElement(0, 0) * this.getElement(1, 1)) - (this.getElement(0, 1) * this.getElement(1, 0)));
            return result;
        }
        Matrix temp;
        for (int i = 0; i < this.getCountColumn(); ++i) {
            temp = new Matrix(new double[this.getCountRow() - 1][this.getCountColumn() - 1]);
            for (int j = 1; j < matrixData.length; ++j) {
                for (int k = 0; k < this.getCountColumn(); ++k) {
                    if (k < i) {
                        temp.setElement(j - 1, k, this.getElement(j, k));
                    } else if (k > i) {
                        temp.setElement(j - 1, k - 1, this.getElement(j, k));
                    }
                }
            }
            result += this.getElement(0, i) * Math.pow(-1, (double) i) * temp.determinant();
        }
        return result;
    }

    //умножение матрицы на вектор
    public Vector getMultiplicationVector(Vector vector) {
        if (this.getCountRow() != vector.getSize()) {
            throw new IllegalArgumentException("Ошибкаб, размерность вектора задана неверно");
        }
        Vector result = new Vector(vector.getSize());
        double[] calculation = new double[vector.getSize()];
        for (int i = 0; i < this.getCountRow(); ++i) {
            for (int j = 0; j < this.getCountColumn(); ++j) {
                this.setElement(i, j, this.getElement(i, j) * vector.getElement(j));
                calculation[i] += this.getElement(i, j);
            }
            result.setElement(i, calculation[i]);
        }
        return result;
    }

    //сложение матриц
    public Matrix getAddition(Matrix matrix) {
        if (this.getCountRow() != this.getCountColumn()) {
            throw new IllegalArgumentException("Ошибкаб, матрица должна быть квадратной");
        }
        for (int i = 0; i < this.getCountRow(); ++i) {
            for (int j = 0; j < this.getCountColumn(); ++j) {
                this.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j));
            }
        }
        return this;
    }

    //вычитание матриц
    public Matrix getDifference(Matrix matrix) {
        if (this.getCountRow() != this.getCountColumn()) {
            throw new IllegalArgumentException("Ошибкаб, матрица должна быть квадратной");
        }
        for (int i = 0; i < this.getCountRow(); ++i) {
            for (int j = 0; j < this.getCountColumn(); ++j) {
                this.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j) * -1);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{{ ");
        for (int i = 0; i < this.getCountRow(); ++i) {
            for (int j = 0; j < this.getCountColumn(); ++j) {
                stringBuilder.append(this.getElement(i, j)).append("  ");
                if ((j == this.getCountColumn() - 1) && (i != this.getCountRow() - 1)) {
                    stringBuilder.append("}, { ");
                }
            }
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    //статическое сложение матриц
    public static Matrix getAdd(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getCountRow() == matrixB.getCountRow() && matrixA.getCountColumn() == matrixB.getCountColumn()) {
            Matrix result = new Matrix(matrixA.getCountRow(), matrixB.getCountColumn());
            for (int i = 0; i < matrixA.getCountRow(); ++i) {
                result.setRowMatrix(i, matrixA.getMatrixRow(i).getAddition(matrixB.getMatrixRow(i)));
            }
            return result;
        } else {
            throw new IllegalArgumentException("Размерность матриц задана неверно");
        }
    }

    //статическое вычитание матриц
    public static Matrix getDiff(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getCountRow() == matrixB.getCountRow() && matrixA.getCountColumn() == matrixB.getCountColumn()) {
            Matrix result = new Matrix(matrixA.getCountRow(), matrixB.getCountColumn());
            for (int i = 0; i < matrixA.getCountRow(); ++i) {
                result.setRowMatrix(i, matrixA.getMatrixRow(i).getDifferences(matrixB.getMatrixRow(i)));
            }
            return result;
        } else {
            throw new IllegalArgumentException("Размерность матриц задана неверно");
        }
    }

    //умножение матриц
    public static Matrix getMultiplication(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getCountColumn() == matrixB.getCountRow()) {
            Vector result = new Vector(matrixA.getCountRow());
            Matrix matrix = new Matrix(matrixB.getCountRow(), matrixA.getCountRow());

            for (int i = 0; i < matrixB.getCountRow(); ++i) {
                Vector resultMatrixB = matrixB.getMatrixColumn(i);
                for (int j = 0; j < matrixA.getCountRow(); ++j) {
                    int temp = 0;
                    Vector resultMatrixA = matrixA.getMatrixRow(j);
                    for (int m = 0; m < resultMatrixA.getSize(); ++m) {
                        temp += resultMatrixB.getElement(m) * resultMatrixA.getElement(m);
                    }
                    result.setElement(j, temp);
                }
                matrix.setRowMatrix(i, result);
            }
            return matrix;
        } else {
            throw new IllegalArgumentException("Неверная размерность матриц");
        }
    }

    public static class Main {
        public static void main(String[] args) {
            Matrix matrix = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
            Vector vector = new Vector(new double[]{1, 2, 3});
            Vector vector1 = new Vector(new double[]{4, 5, 6});
            Matrix matrix1 = new Matrix(new Vector[]{vector, vector1});
            System.out.println(getDiff(matrix, matrix1));
        }
    }
}

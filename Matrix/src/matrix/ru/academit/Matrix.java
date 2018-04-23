package matrix.ru.academit;

import ru.academit.vector.Vector;

public class Matrix {
    private Vector[] matrixData;

    //размерность column на row, все компоненты равны 0
    public Matrix(int column, int row) {
        if (column == 0 || row == 0 || column < 0 || row < 0) {
            throw new IllegalArgumentException("Параметры матрицы заданы неверно");
        }
        matrixData = new Vector[row];
        for (int i = 0; i < matrixData.length; ++i) {
            matrixData[i] = new Vector(column);
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
        if (array.length == 0) {
            throw new IllegalArgumentException("Параметры матрицы заданы неверно");
        }
        matrixData = new Vector[array.length];
        int count = 0;
        for (double[] e : array) {
            if (count < e.length) {
                count = e.length;
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
        if (vector.length == 0) {
            throw new IllegalArgumentException("Параметры матрицы заданы неверно");
        }
        matrixData = new Vector[vector.length];
        int count = 0;
        for (Vector e : vector) {
            if (count < e.getSize()) {
                count = e.getSize();
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
    public int getRowsCount() {
        return this.matrixData.length;
    }

    public int getColumnCount() {
        return matrixData[0].getSize();
    }

    //получчение вектора строки по индексу
    public Vector getMatrixRow(int index) {
        if (index >= this.getRowsCount() || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Ошибка, задан неверный индекс");
        }
        return new Vector(matrixData[index]);
    }

    //задание вектора строки по индексу
    public void setRowMatrix(int index, Vector vector) {
        if (vector.getSize() > this.getColumnCount() || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Ошибка, задан неверный индекс");
        }
        matrixData[index] = new Vector(vector);
    }

    //получение вектора-столбца по индексу
    public Vector getMatrixColumn(int index) {
        if (index >= getColumnCount() || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Ошибка, задан неверный индекс");
        }
        Vector vector = new Vector(this.getRowsCount());
        for (int i = 0; i < this.getRowsCount(); ++i) {
            vector.setElement(i, this.getElement(i, index));
        }
        return vector;
    }

    //транспонирование матрицы
    public Matrix getTranspose() {
        Matrix matrix = new Matrix(this.getRowsCount(), this.getColumnCount());
        for (int i = 0; i < this.getColumnCount(); ++i) {
            matrix.setRowMatrix(i, this.getMatrixColumn(i));
        }
        return matrix;
    }

    //умножение матрицы на скаляр
    public Matrix getMultiplicationScalar(int scalar) {
        for (int i = 0; i < this.getRowsCount(); ++i) {
            this.matrixData[i].getMultiplicationScalar(scalar);
        }
        return this;
    }

    //изменение элемента матрицы
    public void setElement(int indexRow, int indexColumn, double value) {
        if ((indexRow > this.getRowsCount()) || (indexColumn > this.getColumnCount())) {
            throw new ArrayIndexOutOfBoundsException("Ошибка, задан неверный индекс");
        }
        this.matrixData[indexRow].setElement(indexColumn, value);
    }

    //получение элемента матрицы
    public double getElement(int indexRow, int indexColumn) {
        if ((indexRow > this.getRowsCount()) || (indexColumn > this.getColumnCount())) {
            throw new ArrayIndexOutOfBoundsException("Ошибка, задан неверный индекс");
        }
        return this.matrixData[indexRow].getElement(indexColumn);
    }

    //нахождение определителя матрицы
    public double determinant() {
        if (this.getRowsCount() != this.getColumnCount()) {
            throw new IllegalArgumentException("Ошибкаб, матрица должна быть квадратной");
        }
        if (this.getColumnCount() == 1 && this.getRowsCount() == 1) {
            return 1;
        }
        double result = 0;
        if (matrixData.length == 2) {
            result = ((this.getElement(0, 0) * this.getElement(1, 1)) - (this.getElement(0, 1) * this.getElement(1, 0)));
            return result;
        }
        for (int i = 0; i < this.getColumnCount(); ++i) {
            Matrix temp = new Matrix(new double[this.getRowsCount() - 1][this.getColumnCount() - 1]);
            for (int j = 1; j < matrixData.length; ++j) {
                for (int k = 0; k < this.getColumnCount(); ++k) {
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
        if (this.getColumnCount() != vector.getSize()) {
            throw new IllegalArgumentException("Ошибка, размерность вектора задана неверно");
        }
        Vector result = new Vector(this.getRowsCount());
        double calculation = 0;
        for (int i = 0; i < this.getRowsCount(); ++i) {
            Vector temp = this.getMatrixRow(i);
            for (int j = 0; j < vector.getSize(); ++j) {
                calculation += temp.getElement(j) * vector.getElement(j);
            }
            result.setElement(i, calculation);
            calculation = 0;
        }
        return result;
    }

    //сложение матриц
    public Matrix getAddition(Matrix matrix) {
        if (this.getRowsCount() != matrix.getRowsCount() && this.getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Ошибка, матрицы должны быть одного размера");
        }
        for (int i = 0; i < this.getRowsCount(); ++i) {
            this.matrixData[i].getAddition(matrix.getMatrixRow(i));
        }
        return this;
    }

    //вычитание матриц
    public Matrix getDifference(Matrix matrix) {
        if (this.getRowsCount() != matrix.getRowsCount() && this.getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Ошибка, матрицы должны быть одного размера");
        }
        for (int i = 0; i < this.getRowsCount(); ++i) {
            this.matrixData[i].getDifferences(matrix.getMatrixRow(i));
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");
        for (int i = 0; i < this.getRowsCount(); ++i) {
            stringBuilder.append(this.matrixData[i].toString());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    //статическое сложение матриц
    public static Matrix getAdd(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getRowsCount() != matrixB.getRowsCount() || matrixA.getColumnCount() != matrixB.getColumnCount()) {
            throw new IllegalArgumentException("Размерность матриц задана неверно");
        }
        Matrix result = new Matrix(matrixA.getRowsCount(), matrixB.getColumnCount());
        for (int i = 0; i < matrixA.getRowsCount(); ++i) {
            result.setRowMatrix(i, matrixA.getMatrixRow(i).getAddition(matrixB.getMatrixRow(i)));
        }
        return result;
    }

    //статическое вычитание матриц
    public static Matrix getDiff(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getRowsCount() != matrixB.getRowsCount() || matrixA.getColumnCount() != matrixB.getColumnCount()) {
            throw new IllegalArgumentException("Размерность матриц задана неверно");
        }
        Matrix result = new Matrix(matrixA.getRowsCount(), matrixB.getColumnCount());
        for (int i = 0; i < matrixA.getRowsCount(); ++i) {
            result.setRowMatrix(i, matrixA.getMatrixRow(i).getDifferences(matrixB.getMatrixRow(i)));
        }
        return result;
    }

    //умножение матриц
    public static Matrix getMultiplication(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getColumnCount() != matrixB.getRowsCount()) {
            throw new IllegalArgumentException("Размерность матриц задана неверно");
        }
        Matrix matrix = new Matrix(matrixA.getRowsCount(), matrixB.getColumnCount());
        for (int i = 0; i < matrixB.getColumnCount(); ++i) {
            Vector result = new Vector(matrixA.getRowsCount());
            Vector columnB = matrixB.getMatrixColumn(i);
            for (int j = 0; j < matrixA.getRowsCount(); ++j) {
                Vector rowsA = matrixA.getMatrixRow(j);
                int temp = 0;
                for (int m = 0; m < rowsA.getSize(); ++m) {
                    temp += columnB.getElement(m) * rowsA.getElement(m);
                }
                result.setElement(j, temp);
            }
            matrix.setRowMatrix(i, result);
        }
        return matrix.getTranspose();
    }
}
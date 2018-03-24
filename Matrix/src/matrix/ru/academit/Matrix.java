package matrix.ru.academit;

public class Matrix {
    private Vector[] matrix;

    //размерность n на m, все компоненты равны 0
    public Matrix(int n, int m) {
        matrix = new Vector[n];
        for (int i = 0; i < matrix.length; ++i) {
            matrix[i] = new Vector(m);
        }
    }

    //конструктор копирования
    public Matrix(Matrix matrix) {
        this.matrix = new Vector[matrix.matrix.length];
        for (int i = 0; i < matrix.matrix.length; ++i) {
            this.matrix[i] = new Vector(matrix.matrix[i]);
        }
    }

    //из двумерного массива
    public Matrix(double[][] array) {
        matrix = new Vector[array.length];
        for (int i = 0; i < array.length; ++i) {
            matrix[i] = new Vector(array[i]);
        }
    }

    //из массива векторов-строк
    public Matrix(Vector[] vector) {
        matrix = new Vector[vector.length];
        for (int i = 0; i < vector.length; ++i) {
            matrix[i] = new Vector(vector[i]);
        }
    }


    //получение размеров матрицы
    public int getSizeRow() {
        return this.matrix.length;
    }

    public int getSizeColumn() {
        return matrix[0].getSize();
    }

    //получчение вектора строки по индексу
    public Vector getVector(int index) {
        if (index > this.getSizeRow()) {
            throw new IllegalArgumentException("Ошибкаб, задан неверный индекс.");
        }
        return new Vector(matrix[index]);
    }

    //задание вектора строки по индексу
    public void setVector(int index, Vector vector) {
        if (this.getSizeRow() < vector.getSize()) {
            throw new IllegalArgumentException("Ошибкаб, колличество элементов вектоа не может быть больше количества элементов стргоки матрицы.");
        }
        matrix[index] = new Vector(vector);
    }

    //получение вектора-столбца по индексу
    public Vector getVectorRow(int index) {
        if (index > this.matrix.length || index < 0) {
            throw new IndexOutOfBoundsException("Ошибка, задан неверный индекс.");
        }
        Vector vector = new Vector(this.getSizeRow());
        for (int i = 0; i < this.getSizeRow(); ++i) {
            vector.setElement(i, this.getElement(i, 0));
        }
        return vector;
    }

    //транспонирование матрицы
    public Matrix getTranspose() {
        Matrix matrix = new Matrix(this.getSizeColumn(), this.getSizeRow());
        for (int i = 0; i < this.getSizeColumn(); ++i) {
            for (int j = 0; j < this.getSizeRow(); ++j) {
                matrix.setElement(i, j, this.getElement(j, i));
            }
        }
        return matrix;
    }

    //умножение матрицы на скаляр
    public Matrix getMultiplicationScalar(int scalar) {
        for (int i = 0; i < this.getSizeRow(); ++i) {
            for (int j = 0; j < this.getSizeColumn(); ++j) {
                this.setElement(i, j, this.getElement(i, j) * scalar);
            }
        }
        return this;
    }

    //изменение элемента матрицы
    public void setElement(int indexRow, int indexColumn, double value) {
        if ((indexRow > this.getSizeRow()) || (indexColumn > this.getSizeColumn())) {
            throw new IllegalArgumentException("Ошибкаб, неверно задан индекс.");
        }
        this.matrix[indexRow].setElement(indexColumn, value);
    }

    //получение элемента матрицы
    public double getElement(int indexRow, int indexColumn) {
        if ((indexRow > this.getSizeRow()) || (indexColumn > this.getSizeColumn())) {
            throw new IllegalArgumentException("Ошибкаб, неверно задан индекс.");
        }
        return this.matrix[indexRow].getElement(indexColumn);
    }

    //нахождение определителя матрицы
    public double determinant() {
        if (this.getSizeRow() != this.getSizeColumn()) {
            throw new IllegalArgumentException("Ошибкаб, матрица должна быть квадратной");
        }
        Matrix temp;
        double result = 0;

        if (matrix.length == 2) {
            result = ((this.getElement(0, 0) * this.getElement(1, 1)) - (this.getElement(0, 1) * this.getElement(1, 0)));
            return result;
        }

        for (int i = 0; i < this.getSizeColumn(); ++i) {
            temp = new Matrix(new double[this.getSizeRow()][this.getSizeColumn()]);
            for (int j = 1; j < matrix.length; ++j) {
                for (int k = 0; k < this.getSizeColumn(); ++k) {
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
        if (this.getSizeRow() != vector.getSize()) {
            throw new IllegalArgumentException("Ошибкаб, размерность вектора задана неверно");
        }
        Vector result = new Vector(vector.getSize());
        double[] calculation = new double[vector.getSize()];
        for (int i = 0; i < this.getSizeRow(); ++i) {
            for (int j = 0; j < this.getSizeColumn(); ++j) {
                this.setElement(i, j, this.getElement(i, j) * vector.getElement(j));
                calculation[i] += this.getElement(i, j);
            }
            result.setElement(i, calculation[i]);
        }
        return result;
    }

    //сложение матриц
    public Matrix getAddition(Matrix matrix) {
        if (this.getSizeRow() != this.getSizeColumn()) {
            throw new IllegalArgumentException("Ошибкаб, матрица должна быть квадратной");
        }
        for (int i = 0; i < this.getSizeRow(); ++i) {
            for (int j = 0; j < this.getSizeColumn(); ++j) {
                this.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j));
            }
        }
        return this;
    }

    //вычитание матриц
    public Matrix getDifference(Matrix matrix) {
        if (this.getSizeRow() != this.getSizeColumn()) {
            throw new IllegalArgumentException("Ошибкаб, матрица должна быть квадратной");
        }
        for (int i = 0; i < this.getSizeRow(); ++i) {
            for (int j = 0; j < this.getSizeColumn(); ++j) {
                this.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j) * -1);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{{ ");
        for (int i = 0; i < this.getSizeRow(); ++i) {
            for (int j = 0; j < this.getSizeColumn(); ++j) {
                stringBuilder.append(this.getElement(i, j)).append(" ");
                if ((j == this.getSizeColumn() - 1) && (i != this.getSizeRow() - 1)) {
                    stringBuilder.append("}, { ");
                }
            }
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    //статическое сложение матриц
    public static Matrix getAdd(Matrix matrixA, Matrix matrixB) {
        Matrix result = new Matrix(matrixA.getSizeRow(), matrixA.getSizeColumn());
        for (int i = 0; i < matrixA.getSizeRow(); ++i) {
            for (int j = 0; j < matrixA.getSizeColumn(); ++j) {
                result.setElement(i, j, matrixA.getElement(i, j) + matrixB.getElement(i, j));
            }
        }
        return result;
    }

    //статическое вычитание матриц
    public static Matrix getDiff(Matrix matrixA, Matrix matrixB) {
        Matrix result = new Matrix(matrixA.getSizeRow(), matrixA.getSizeColumn());
        for (int i = 0; i < matrixA.getSizeRow(); ++i) {
            for (int j = 0; j < matrixA.getSizeColumn(); ++j) {
                result.setElement(i, j, matrixA.getElement(i, j) + (matrixB.getElement(i, j) * -1));
            }
        }
        return result;
    }
}

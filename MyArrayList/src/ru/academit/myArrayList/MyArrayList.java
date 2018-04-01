package ru.academit.myArrayList;

import java.util.Arrays;

public class MyArrayList<T> implements InterfaceMyArrayList<T> {
    private T[] elements;
    private int index;
    private final int CAPACITY = 16;

    //конструктор, все элементы равны нулю
    public MyArrayList() {
        elements = (T[]) new Object[CAPACITY];
    }

    public MyArrayList(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    //добавление новго элемента в конец списка
    @Override
    public void addElement(Object value) {
        if (index == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[index] = (T) value;
        ++index;
    }

    //добавление нового элемента по индексу
    @Override
    public void addElement(int indexElement, Object value) {
        if (indexElement == elements.length - 1) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        } else {
            elements = Arrays.copyOf(elements, elements.length + 1);
        }
        System.arraycopy(elements, 0, elements, 0, indexElement - 1);
        System.arraycopy(elements, indexElement + 1, elements, indexElement + 1, elements.length - indexElement - 1);
        elements[indexElement] = (T) value;
    }

    //получение элемента
    @Override
    public T[] getElement(int indexElement) {
        return (T[]) elements[indexElement];
    }

    //изменение элемента
    @Override
    public void setElement(int indexElement, Object value) {
        elements[indexElement] = (T) value;
    }

    //удаление элемента
    @Override
    public T[] getRemoveElement(int indexElement) {
        if (indexElement > elements.length) {
            throw new IndexOutOfBoundsException("Ошибка, задан неверный индекс.");
        }
        System.arraycopy(elements, 0, elements, 0, indexElement - 1);
        System.arraycopy(elements, indexElement + 1, elements, indexElement, elements.length - indexElement - 1);
        elements = Arrays.copyOf(elements, elements.length - 1);
        return elements;
    }

    public T[] trimToSize() {
        for (int i = 0; i < elements.length; ++i) {
            if (elements[i] == null) {
                this.getRemoveElement(i);
            }
        }
        return elements;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

}

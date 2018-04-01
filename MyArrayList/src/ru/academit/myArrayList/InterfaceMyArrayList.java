package ru.academit.myArrayList;

public interface InterfaceMyArrayList<T> {

    //добавление новго элемента в конец списка
    void addElement(Object value);

    //добавление нового элемента по индексу
    void addElement(int indexElement, Object value);

    //получение элемента
    T[] getElement(int indexElement);

    //изменение элемента
    void setElement(int indexElement, Object value);

    //удаление элемента
    T[] getRemoveElement(int indexElement);
}

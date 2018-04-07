package ru.academit.myArrayList;

import java.util.*;

public class MyArrayList<T> implements List {
    private Object[] elements;
    private int index;
    private int next = -1;
    private int previous = elements.length - 1;
    private static final int CAPACITY = 5;

    public MyArrayList() {
        elements = (T[]) new Object[CAPACITY];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        if (elements.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < elements.length; ++i) {
            if (elements[i] == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                if (index >= elements.length) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Object next() {
                int i = next;
                if (i >= elements.length) {
                    throw new IndexOutOfBoundsException();
                }
                return elements[++next];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, elements.length);
    }

    private void ensureCapacity(int capacity) {
        if (elements.length == capacity) {
            T[] newArray = (T[]) new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArray, 0, elements.length - 1);
            elements = newArray;
        }
    }

    @Override
    public boolean add(Object o) {
        ensureCapacity(index + 1);
        elements[index] = (T) o;
        ++index;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < elements.length; ++i) {
            if (elements[i].equals(o)) {
                System.arraycopy(elements, i + 1, elements, i, elements.length - i - 1);
                elements = Arrays.copyOf(elements, elements.length - 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        Object[] array = collection.toArray();
        if (array.length != 0) {
            ensureCapacity(elements.length + array.length);
            System.arraycopy(array, 0, elements, elements.length, array.length - 1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        Object[] array = collection.toArray();
        if (array.length != 0) {
            ensureCapacity(elements.length + array.length - 1);
            System.arraycopy(elements, index, elements, index + array.length - 1, elements.length - index - 1);
            System.arraycopy(array, 0, elements, index, array.length - 1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; ++i) {
            elements[i] = null;
        }
    }

    @Override
    public Object get(int i) {
        if (i >= elements.length) {
            throw new IndexOutOfBoundsException("Ошибка, не верный индекс.");
        }
        return elements[i];
    }

    @Override
    public Object set(int i, Object o) {
        elements[i] = (T) o;
        return elements[i];
    }

    @Override
    public void add(int i, Object o) {
        ensureCapacity(index + 1);
        System.arraycopy(elements, i, elements, i + 1, elements.length - i - 1);
        elements[index] = (T) o;
        ++index;
    }

    @Override
    public Object remove(int i) {
        T oldValue = (T) elements[i];
        System.arraycopy(elements, i + 1, elements, i, elements.length - i - 1);
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < elements.length; ++i) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = elements.length; i > 0; --i) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return new ListIterator() {
            @Override
            public boolean hasNext() {
                if (index >= elements.length) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Object next() {
                if (next >= elements.length) {
                    throw new IndexOutOfBoundsException();
                }
                return elements[++next];
            }

            @Override
            public boolean hasPrevious() {
                --previous;
                if (previous < 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Object previous() {
                if (previous < 0) {
                    throw new NoSuchElementException();
                }
                return elements[--previous];
            }

            @Override
            public int nextIndex() {
                if (next >= elements.length) {
                    return elements.length;
                }
                return ++next;
            }

            @Override
            public int previousIndex() {
                if (previous < 0) {
                    return -1;
                }
                return --previous;
            }

            @Override
            public void remove() {
                System.arraycopy(elements, nextIndex() + 1, elements, nextIndex(), elements.length - nextIndex() - 1);
            }

            @Override
            public void set(Object o) {
                elements[nextIndex()] = o;
            }

            @Override
            public void add(Object o) {
                ensureCapacity(nextIndex() + 1);
                elements[nextIndex()] = (T) o;
                ++index;
            }
        };
    }

    @Override
    public ListIterator listIterator(int i) {
        return null;
    }

    @Override
    public List subList(int i, int i1) {
        return null;
    }

    @Override
    public boolean retainAll(Collection collection) {
        int count = 0;
        Object[] array = new Object[elements.length];
        for (int i = 0; i < elements.length; ++i) {
            if (collection.contains(elements[i])) {
                array[i] = elements[i];
                ++count;
            }
        }
        if (count != 0) {
            elements = Arrays.copyOf(array, array.length);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection collection) {
        int count = 0;
        for (int i = 0; i < elements.length; ++i) {
            if (collection.contains(elements[i])) {
                this.remove(i);
                ++count;
            }
        }
        if (count != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection collection) {
        while (collection.iterator().hasNext()) {
            if (!this.contains(collection.iterator().next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return Arrays.copyOf(elements, elements.length);
    }

    public String toString() {
        return Arrays.toString(elements);
    }
}

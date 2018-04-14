package ru.academit.myArrayList;


import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] elementData;
    private int size;
    private static final int CAPACITY = 5;
    private int modCount;

    public MyArrayList() {
        elementData = (T[]) new Object[CAPACITY];
    }

    public MyArrayList(int capacity) {
        elementData = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (elementData.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        if (this.indexOf(o) != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private int currentIndex = -1;

    public class MyIterator implements Iterator<T> {
        int temp = modCount;

        @Override
        public boolean hasNext() {
            return ++currentIndex < size;
        }

        @Override
        public T next() {
            ++currentIndex;
            if (currentIndex > size()) {
                throw new NoSuchElementException();
            }
            if (temp != modCount) {
                throw new ConcurrentModificationException();
            }
            return elementData[currentIndex];
        }

        @Override
        public void remove() {
            elementData = Arrays.copyOf(elementData, size - 1);
            --size;
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, elementData.length);
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return Arrays.copyOf(t1s, t1s.length);
    }

    public void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public void trimToSize() {
        modCount++;
        int oldCapacity = elementData.length;
        if (size < oldCapacity) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    @Override
    public boolean add(T t) {
        ensureCapacity(size + 1);
        elementData[size] = t;
        ++size;
        ++modCount;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            this.trimToSize();
        }
        for (int i = 0; i < size(); ++i) {
            if (elementData[i].equals(o)) {
                if (i == size - 1) {
                    elementData = Arrays.copyOf(elementData, size - 1);
                    --size;
                    ++modCount;
                    return true;
                } else {
                    System.arraycopy(elementData, i + 1, elementData, i, size - i);
                    elementData = Arrays.copyOf(elementData, size);
                    --size;
                    ++modCount;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() != 0) {
            for (int i = 0; i < collection.size(); ++i) {
                if (this.indexOf(collection.iterator().next()) == -1) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.size() != 0) {
            for (int i = 0; i < collection.size(); ++i) {
                this.add(collection.iterator().next());
            }
            ++modCount;
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        if (i > size()) {
            throw new IllegalArgumentException();
        }
        if (collection.size() != 0) {
            for (int j = 0, k = i; j < collection.size(); ++j, ++k) {
                this.add(k, collection.iterator().next());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (int i = 0; i < collection.size(); ++i) {
            this.remove(collection.iterator().next());
        }
        ++modCount;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        this.clear();
        for (int i = 0; i < collection.size(); ++i) {
            this.set(i, (T) collection.iterator().next());
        }
        ++modCount;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            elementData[i] = null;
        }
    }

    @Override
    public T get(int i) {
        if (i > size()) {
            throw new NoSuchElementException();
        }
        return elementData[i];
    }

    @Override
    public T set(int i, T t) {
        if (i > size()) {
            throw new NoSuchElementException();
        }
        return elementData[i] = t;
    }

    @Override
    public void add(int i, T t) {
        ensureCapacity(size() + 1);
        System.arraycopy(elementData, i, elementData, i + 1, size - i);
        elementData[i] = (T) t;
        ++modCount;
        ++size;
    }

    @Override
    public T remove(int i) {
        if (i > size()) {
            throw new NoSuchElementException();
        }
        T oldValue = elementData[i];
        System.arraycopy(elementData, i + 1, elementData, i, size - i);
        --size;
        ++modCount;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); ++i) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size(); i > 0; --i) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements ListIterator<T> {
        private int lastIndex = size;
        private int temp = modCount;

        @Override
        public boolean hasNext() {
            ++currentIndex;
            if (currentIndex == size) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public T next() {
            ++currentIndex;
            if (currentIndex == size) {
                throw new NoSuchElementException();
            }
            if (temp != modCount) {
                throw new ConcurrentModificationException();
            }
            return elementData[currentIndex];
        }

        @Override
        public boolean hasPrevious() {
            --lastIndex;
            if (lastIndex != 0) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T previous() {
            int oldIndex = currentIndex;
            --currentIndex;
            return elementData[oldIndex];
        }

        @Override
        public int nextIndex() {
            ++currentIndex;
            if (currentIndex + 1 > elementData.length) {
                return elementData.length;
            }
            return currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            --currentIndex;
            if (currentIndex < 0) {
                return -1;
            }
            return currentIndex;
        }

        @Override
        public void remove() {
            if (currentIndex == -1) {
                throw new IllegalStateException();
            }
            if (currentIndex == size - 1) {
                elementData = Arrays.copyOf(elementData, size - 1);
            } else {
                System.arraycopy(elementData, currentIndex + 1, elementData, currentIndex, size - currentIndex);
            }
            --size;
        }

        @Override
        public void set(T t) {
            if (currentIndex == -1) {
                throw new IllegalStateException();
            }
            elementData[currentIndex] = t;
        }

        @Override
        public void add(T t) {
            if (currentIndex == -1) {
                throw new IllegalStateException();
            }
            ensureCapacity(size + 1);
            System.arraycopy(elementData, currentIndex, elementData, currentIndex + 1, size - currentIndex);
            elementData[currentIndex] = t;
            ++size;
        }
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return new MyListIteratorI(i);
    }

    private class MyListIteratorI implements ListIterator<T> {
        int cursor;
        int temp = modCount;

        MyListIteratorI(int i) {
            cursor = i;
        }

        @Override
        public boolean hasNext() {
            if (cursor != size) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            ++cursor;
            if (cursor == size) {
                throw new NoSuchElementException();
            }
            if (temp != modCount) {
                throw new ConcurrentModificationException();
            }
            return elementData[cursor];
        }

        @Override
        public boolean hasPrevious() {
            --cursor;
            if (cursor != 0) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T previous() {
            int oldIndex = cursor;
            --cursor;
            return elementData[oldIndex];
        }

        @Override
        public int nextIndex() {
            ++cursor;
            if (cursor + 1 > elementData.length) {
                return elementData.length;
            }
            return cursor + 1;
        }

        @Override
        public int previousIndex() {
            --cursor;
            if (cursor < 0) {
                return -1;
            }
            return cursor;
        }

        @Override
        public void remove() {
            if (cursor < 0) {
                throw new IllegalStateException();
            }
            if (cursor == size - 1) {
                elementData = Arrays.copyOf(elementData, size - 1);
            } else {
                System.arraycopy(elementData, cursor + 1, elementData, cursor, size - cursor);
            }
            --size;
        }

        @Override
        public void set(T t) {
            elementData[cursor] = t;
        }

        @Override
        public void add(T t) {
            if (cursor < 0) {
                throw new IllegalStateException();
            }
            ensureCapacity(size + 1);
            System.arraycopy(elementData, cursor, elementData, cursor + 1, size - cursor);
            elementData[cursor] = t;
            ++size;
        }
    }

    @Override
    public List<T> subList(int i, int i1) {
        MyArrayList<T> array = new MyArrayList<>();
        if (i >= 0 && i1 <= size - 1) {
            for (int j = i; j <= i1; ++j) {
                array.add(elementData[j]);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}

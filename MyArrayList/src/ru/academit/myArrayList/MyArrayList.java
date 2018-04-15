package ru.academit.myArrayList;


import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] elementData;
    private int size;
    private static final int CAPACITY = 5;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        elementData = (T[]) new Object[CAPACITY];
    }

    public MyArrayList(int capacity) {
        //noinspection unchecked
        elementData = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }


    public class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int temp = modCount;

        @Override
        public boolean hasNext() {
            ++currentIndex;
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (currentIndex <= size) {
                ++currentIndex;
            } else {
                throw new NoSuchElementException("Колличество элементов списка: " + size());
            }
            if (temp != modCount) {
                throw new ConcurrentModificationException("Во время прохождения списка, список был подвержен изменению.");
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
        return Arrays.copyOf(elementData, size());
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        if (t1s.length < size()) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(elementData, size);
        } else {
            t1s[size] = null;
            return t1s;
        }
    }

    private void ensureCapacity(int minCapacity) {
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

    private void fastRemove(int i) {
        ++modCount;
        System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
        --size;
        elementData[size] = null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size(); ++i) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size(); ++i) {
                if (o.equals(elementData[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() != 0) {
            Iterator e = collection.iterator();
            for (int i = 0; i < collection.size(); ++i) {
                if (!this.contains(e.next())) {
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
            Iterator e = collection.iterator();
            for (int i = 0; i < collection.size(); ++i) {
                //noinspection unchecked
                this.add((T) e.next());
            }
        }
        return false;
    }


    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        if (i >= size()) {
            throw new IllegalArgumentException("Неверный индекс");
        }
        if (collection.size() != 0) {
            Iterator e = collection.iterator();
            for (int j = 0, k = i; j < collection.size(); ++j, ++k) {
                //noinspection unchecked
                this.add(k, (T) e.next());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Iterator e = collection.iterator();
        for (int i = 0; i < collection.size(); ++i) {
            if (this.contains(e.next())) {
                --size;
            }
            this.remove(e.next());
        }
        ++modCount;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        this.clear();
        Iterator e = collection.iterator();
        for (int i = 0; i < collection.size(); ++i) {
            //noinspection unchecked
            this.set(i, (T) e.next());
        }
        size = collection.size();
        ++modCount;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int i) {
        if (i >= size()) {
            throw new IndexOutOfBoundsException("Неверный индекс");
        }
        return elementData[i];
    }

    @Override
    public T set(int i, T t) {
        if (i >= size()) {
            throw new IndexOutOfBoundsException("Неверный индекс");
        }
        T oldValue = elementData[i];
        elementData[i] = t;
        return oldValue;
    }

    @Override
    public void add(int i, T t) {
        ensureCapacity(size() + 1);
        System.arraycopy(elementData, i, elementData, i + 1, size - i);
        elementData[i] = t;
        ++modCount;
        ++size;
    }

    @Override
    public T remove(int i) {
        if (i >= size()) {
            throw new NoSuchElementException("Неверный индекс");
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
        private int currentIndex = -1;
        private int lastIndex = size;
        private int temp = modCount;

        @Override
        public boolean hasNext() {
            ++currentIndex;
            return currentIndex != size;
        }

        @Override
        public T next() {
            ++currentIndex;
            if (currentIndex == size) {
                throw new NoSuchElementException("Колличество элементов списка: " + size);
            }
            if (temp != modCount) {
                throw new ConcurrentModificationException("Во время прохождения списка, список был подвержен изменению.");
            }
            return elementData[currentIndex];
        }

        @Override
        public boolean hasPrevious() {
            --lastIndex;
            return lastIndex != 0;
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
                throw new IllegalStateException("Неверный индекс");
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
                throw new IllegalStateException("Неверный индекс");
            }
            elementData[currentIndex] = t;
        }

        @Override
        public void add(T t) {
            if (currentIndex == -1) {
                throw new IllegalStateException("Неверный индекс");
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
            return cursor != size;
        }

        @Override
        public T next() {
            ++cursor;
            if (cursor == size) {
                throw new NoSuchElementException("Неверный индекс");
            }
            if (temp != modCount) {
                throw new ConcurrentModificationException("Во время прохождения списка, список был подвержен изменению.");
            }
            return elementData[cursor];
        }

        @Override
        public boolean hasPrevious() {
            --cursor;
            return cursor != 0;
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
                throw new IllegalStateException("Неверный индекс");
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
                throw new IllegalStateException("Неверный индекс");
            }
            ensureCapacity(size + 1);
            System.arraycopy(elementData, cursor, elementData, cursor + 1, size - cursor);
            elementData[cursor] = t;
            ++size;
        }

    }

    @Override
    public List<T> subList(int i, int i1) {
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for (int i = 0; i < size(); ++i) {
            if (i == size - 1) {
                builder.append(this.get(i));
            } else {
                builder.append(this.get(i)).append(", ");
            }
        }
        builder.append(" }");
        return builder.toString();
    }
}

package heap;

import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity;
    private int n;
    private Object[] a;

    public Heap(int initialCapacity) {
        capacity = initialCapacity;
        n = 0;
        a = new Object[initialCapacity + 1];
    }

    public Heap() {
        new Heap<>(DEFAULT_CAPACITY);
    }

    public void insert(T element) {
        resizeIfNeeded();
        a[++n] = element;
        swim(n);
    }

    public T extract() {
        if (size() <= 0) {
            throw new NoSuchElementException();
        }
        Object max = a[1];
        swap(1, n--);
        sink(1);
        return (T) max;
    }

    public int size() {
        return n;
    }

    public void clear() {
        n = 0;
    }

    T get(int i) {
        return (T) a[i];
    }

    private void swim(int k) {
        while (k > 1 && ((T) a[k]).compareTo((T) a[k / 2]) > 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int child = 2 * k;
            if (child + 1 <= n && ((T) a[child + 1]).compareTo((T) a[child]) > 0) {
                child = child + 1;
            }
            if (((T) a[k]).compareTo((T) a[child]) >= 0) {
                break;
            }
            swap(k, child);
            k = child;
        }
    }

    private void swap(int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void resizeIfNeeded() {
        if (n != capacity) {
            return;
        }
        this.capacity = capacity * 2;
        Object[] arr = new Object[capacity];
        System.arraycopy(a, 0, arr, 0, a.length);
        this.a = arr;
    }

}
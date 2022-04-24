package heap;

import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 8;
    private int capacity;
    private int n;
    private Object[] a;

    public Heap(int initialCapacity) {
        capacity = initialCapacity; //Текущее максимальное количество элементов
        n = 0; //Текущее количество элементов
        a = new Object[initialCapacity + 1]; //Размер + 1, чтобы было удобнее вычислять индексы
        //left child = current * 2, right child = current * 2 + 1, parent = current / 2
    }

    public Heap() {
        new Heap<>(DEFAULT_CAPACITY);
    }

    public void insert(T element) {
        resizeIfNeeded(); //Увеличиваем размер массива, если элемент не влезает
        a[++n] = element; //Добавляем элемент в конец, увеличиваем n
        swim(n); //Вызываем swim() на последнем элементе, чтобы он "всплыл"
    }

    public T extract() {
        if (size() <= 0) {
            throw new NoSuchElementException();
        }
        Object max = a[1]; //Запомнили ссылку на максимальный элемент, чтобы его вернуть
        swap(1, n--); //Меняем местами первый и последний элемент, уменьшаем n на 1
        sink(1);  // "Топим" первый элемент, вызываем на нём sink()
        return (T) max;
    }

    public int size() {
        return n;
    }

    public void clear() {
        n = 0;
    }

    //Метод для отрисовки кучи, возвращает элемент на позиции i
    T get(int i) {
        return (T) a[i];
    }

    private void swim(int k) {
        //Пока элемент не родитель и больше родителя
        while (k > 1 && ((T) a[k]).compareTo((T) a[k / 2]) > 0) {
            swap(k, k / 2); //Меняем с родителем
            k /= 2; //Меняем индекс k на родительский
        }
    }

    private void sink(int k) {
        //Пока у элемента есть левый ребёнок
        while (2 * k <= n) {
            int child = 2 * k;
            //Если есть правый ребёнок и он больше
            if (child + 1 <= n && ((T) a[child + 1]).compareTo((T) a[child]) > 0) {
                child = child + 1; //Теперь child -  правый ребёнок
            }
            //Если родитель >= ребёнка, то инвариант кучи сохраняется - выходим
            if (((T) a[k]).compareTo((T) a[child]) >= 0) {
                break;
            }
            swap(k, child); //Меняем родителя с большим ребёнком
            k = child; //Меняем текущий индекс k на индекс ребёнка
        }
    }

    private void swap(int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void resizeIfNeeded() {
        if (n != capacity) {
            return; //Нет необходимости увеличивать размер
        }
        this.capacity = capacity * 2; //Увеличиваем размер вдвое, аллоцируем новый массив и копируем в него старый
        Object[] arr = new Object[capacity];
        System.arraycopy(a, 0, arr, 0, a.length);
        this.a = arr;
    }

}
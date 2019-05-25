import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayImplements<E extends Object & Comparable<? super E>> implements ArrayCustom<E> {

    private static final int INITIAL_CAPACITY = 32;

    protected E[] data;
    protected int currentSize;


    //Конструктор

    public ArrayImplements() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayImplements(int initialCapacity) {
        this.data = (E[]) new Object[initialCapacity];
    }


    //Метод генерирующий случайный массив типа Integer
    @Override
    public void generateRandomIntegerArray(int size, int min, int max) {
        for (int i = 0; i < size; i++) {
            add((E) randInt(min, max)); //приведение типов
        }
    }

    //Метод генерирующий случайное целое число в пределах от min до max
    private Integer randInt(int min, int max) {
        Random rand = new Random();
        Integer randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


    //Метод добавления элемента в массив
    @Override
    public void add(E value) {
        if (currentSize == data.length) {
            data = grow();
        }
        data[currentSize++] = value;
    }


    protected E[] grow() {
        return Arrays.copyOf(data, data.length * 2);
    }


    @Override
    public E get(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IllegalArgumentException("Invalid index value");
        }
        return data[index];
    }


    //Метод удаления элемента из массива
    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < currentSize - 1; i++) {
            data[i] = data[i + 1];
        }
        data[currentSize - 1] = null;
        currentSize--;
        return true;
    }


    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < currentSize; i++) {
            if (value.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSize() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }


    @Override //O(n^2 сравнений и n^2 перестановок)
    public void sortBubble() {
        for (int i = 0; i < currentSize - 1; i++) {
            for (int j = 0; j < currentSize - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }


    @Override
    public void sortSelect() {
        for (int i = 0; i < currentSize - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < currentSize; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(minIndex, i);
            }

        }
    }

    @Override
    public void sortInsert() {
        for (int i = 1; i < currentSize; i++) {
            E temp = data[i];
            int in = i;

            while (in > 0 && data[in - 1].compareTo(temp) >= 0) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
    }

    private void swap(int index1, int index2) {
        E temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }


    @Override
    public String toString() {
        return Stream.of(data)
                .limit(currentSize)
                .map(String::valueOf) //свели все элементы к типу стринг
                .collect(Collectors.joining(", ", "[", "]"));

    }
}
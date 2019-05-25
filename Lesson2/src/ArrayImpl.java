import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayImpl<E extends Object & Comparable<? super E>> implements Array<E> {

    private static final int INITIAL_CAPACITY = 16;


    protected E[] data;
    protected int currentSize;


    //Конструктор

    public ArrayImpl() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayImpl(int initialCapacity) {
        this.data = (E[]) new Object[initialCapacity];
    }

    @Override
    public void add(E value) {
        if (currentSize==data.length){
            data=grow();
        }
        data[currentSize++]=value;
    }

    protected E[] grow() {
        return Arrays.copyOf(data, data.length * 2);
    }


    @Override //O(1)
    public E get(int index) {

        if (index < 0 || index >= currentSize) {
            throw new IllegalArgumentException("Invalid index value");
        }
        return data[index];
    }

    @Override //O(n)
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

    @Override//O(n)
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
        for (int i = 0; i < currentSize-1; i++) {
            for (int j = 0; j < currentSize-1-i; j++) {
                if (data[j].compareTo(data[j+1])>0) {
                    swap(j, j+1);
                }
            }
        }
    }



    @Override
    public void sortSelect() {
        for (int i = 0; i < currentSize-1; i++) {
            int minIndex=i;
            for (int j = i+1; j <currentSize ; j++) {
                if (data[j].compareTo(data[minIndex])<0){
                    minIndex=j;
                }
            }
            if (minIndex !=i){
                swap(minIndex,i);
            }
            
        }
    }

    @Override
    public void sortInsert() {
        for (int i = 1; i < currentSize; i++) {
            E temp=data[i];
            int in=i;

            while (in > 0 && data [in-1].compareTo(temp)>=0){
                data [in]=data[in-1];
                in--;
            }

            data[in]=temp;


        }
    }

    private void swap(int index1, int index2) {
        E temp=data[index1];
        data[index1]=data[index2];
        data[index2]=temp;
    }

    @Override
    public String toString() {

        return Stream.of(data)
                .limit(currentSize)
                //      .map(element -> String.valueOf(element))
                .map(String::valueOf) //свели все элементы к типу стринг
                .collect(Collectors.joining(", ", "[", "]"));


//        StringBuilder sb = new StringBuilder("[");
//        for (int i = 0; i < currentSize; i++) {
//            sb.append(data[i]);
//            if (i<currentSize-1){
//                sb.append(", ");
//            }
//        }
//        sb.append("]");
//        return sb.toString();
    }
}

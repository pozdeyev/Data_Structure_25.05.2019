package ru.geekbrains.datastructure.stack;

public class StackImpl<E> implements Stack<E> {

    private E[] data;
    private int maxSize;
    private int size;


    public StackImpl(int maxSize) {
        this.maxSize = maxSize;
        this.data = createArray(maxSize);

    }

    @SuppressWarnings("unchecked")
    private E[] createArray(int maxSize) {
        return (E[]) new Object[maxSize];
    }


    @Override//O(1)
    public void push(E value) {

         //data[size]=value;
        // size++;
        if (isFull()) {
            throw new StackOverloadException();
        }
        data[size++] = value;

    }

    @Override //извлекаем и удаляем последний//O(1)
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");

        }
        //  E value = data[size - 1];
        //  size--;
        E value = data[--size];
        data[size] = null;
        return value;
    }

    @Override//O(1)
    public E peek() {
        return data[size - 1];
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override//O(1)
    public void clear() {
        this.data = createArray(maxSize);
        this.size = 0;
        // while (!isEmpty()){
        //     pop();
        //  }
    }
}

package deque;
import queue.QueueImpl;

//Расширяет Queue
public class DequeImplements<E> extends QueueImpl<E> implements DequeCustom <E>{

    public DequeImplements(int maxSize) {
        super(maxSize);
    }

    //аналогично обычной очереди queue
    @Override
    public E removeLeft() {
        return super.remove();
    }

    @Override
    public void insertRight(E value) {
        super.insert(value);
    }

    @Override
    public void insertLeft(E value) {
        if (isFull()) {
            throw new RuntimeException("Dequeue is full!");
        }
        //замыкаем круг
        if (front - 1 < 0)
            front = data.length;

        //сдвигаем метку front влево и записываем значение
        data[--front] = value;
        size++;
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            throw new RuntimeException("Dequeue is empty!");
        }
        if (rear < 0)
            rear = data.length - 1;

        size--; //уменьшаем размер очереди
        return data[rear--]; //смещаем конечную метко влево, возвращаем по метке данные
    }

}
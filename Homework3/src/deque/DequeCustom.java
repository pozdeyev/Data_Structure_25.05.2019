package deque;
import queue.*;


public interface DequeCustom<E> extends Queue<E> {

    //Вставка с начало
    void insertLeft(E value);

    //Вставка в конец
    void insertRight(E value);

    //Удалить первый по порядку добавления
    E removeLeft();

    //Удалить последний
    E removeRight();
}

package linkedlist;

//Расширяем Iterable
public interface LinkedList<E> extends Iterable<E>{

    void insertFirst(E value);

    E removeFirst();

    boolean remove(E value);

    void display();

    boolean isEmpty();

    int getSize();

    boolean contains(E value);

    E getFirstElement();


    Entry<E> getFirst();
}

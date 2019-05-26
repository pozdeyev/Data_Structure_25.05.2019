package linkedlist;
import java.util.Objects;

public class Entry<E> {
    public final E value;
    public Entry<E> next;

    //конструктор
    public Entry(E value) {
        this.value = value;
    }
    public Entry<E> getNext() {
        return next;
    }
    public E getValue() {
        return value;
    }
    public void setNext(Entry<E> next) {
        this.next = next; }

    //переопределяем метод сравнения
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?> entry = (Entry<?>) o;
        return Objects.equals(value, entry.value);
    }

}


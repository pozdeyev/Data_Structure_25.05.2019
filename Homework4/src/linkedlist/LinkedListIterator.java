package linkedlist;
import iterator.ListIterator;

public class LinkedListIterator<E> implements ListIterator<E> {

    private SimpleLinkedListImpl list;

    private Entry<E> current;
    private Entry<E> previous;

    public LinkedListIterator(SimpleLinkedListImpl list) {
        this.list = list;
        reset();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E nextValue = current.getValue();
        previous = current;
        current = current.getNext();
        return nextValue;
    }

    @Override
    public void remove() {
        if (previous == null) {
            list.firstElement = current.getNext();
            reset();
        } else {
            previous.setNext(current.getNext());
            if (!hasNext()) {
                reset();
            } else {
                current = current.getNext();
            }
        }
    }

    @Override
    public void reset() {
        current = list.firstElement;
        previous = null;
    }

    @Override
    public void insertBefore(E value) {
        Entry newItem = new Entry(value);
        if (previous == null) {
            newItem.setNext(list.firstElement);
            list.firstElement = newItem;
            reset();
        } else {
            newItem.setNext(previous.getNext());
            previous.setNext(newItem);
            current = newItem;
        }

    }

    @Override
    public void insertAfter(E value) {
        Entry newItem = new Entry(value);
        if (list.isEmpty()) {
            list.firstElement = newItem;
            current = newItem;
        } else {
            newItem.setNext(current.getNext());
            current.setNext(newItem);
            next();
        }
    }
}
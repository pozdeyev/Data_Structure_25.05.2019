package linkedlist;

public class TwoSideLinkedListImpl<E> extends SimpleLinkedListImpl<E> implements TwoSideLinkedList<E> {

    private Entry<E> lastElement;


    @Override
    public void insertLast(E value) {
        Entry<E> entry = new Entry<>(value);
        if (isEmpty()) {
            firstElement = entry;
        } else {
            lastElement.next = entry;
        }

        lastElement = entry;
        size++;
    }

    @Override
    public void insertFirst(E value) {
        super.insertFirst(value);
        if (getSize() == 1) {
            lastElement = firstElement;
        }
    }

    @Override
    public E removeFirst() {
        E result = super.removeFirst();
        if (isEmpty()) {
            lastElement = null;
        }

        return result;
    }

    @Override
    public boolean remove(E value) {
        Entry<E> previousElement = null;
        Entry<E> currentElement = firstElement;
        while (currentElement != null) {
            if ( currentElement.value.equals(value) ) {
                break;
            }
            previousElement = currentElement;
            currentElement = currentElement.next;
        }

        if (currentElement == null) {
            return false;
        }

        if (currentElement == firstElement) {
            firstElement = firstElement.next;
        }
        else if (currentElement == lastElement) {
            lastElement = previousElement;
            previousElement.next = null;
        }
        else {
            previousElement.next = currentElement.next;
        }

        size--;
        return true;
    }
}

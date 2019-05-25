public interface ArrayCustom<E> {

    void add(E value);


    E get(int index);

    boolean remove(E value);

    boolean contains(E value);
    int indexOf(E value);

    int getSize();
    boolean isEmpty();

    void sortBubble();
    void sortSelect();
    void sortInsert();

    void generateRandomIntegerArray(int size, int min, int max);


}
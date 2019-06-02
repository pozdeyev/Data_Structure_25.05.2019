public interface Tree<E> {

    enum TraverseMode {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER,
    }

    //Добавил метод логического типа (сбалансировано ли дерево или нет)
    boolean isBalanced();

    boolean add(E value);
    boolean remove(E value);
    boolean find(E value);
    boolean isEmpty();
    void display();
    void traverse(TraverseMode traverseMode);


}
import java.util.Objects;

public class Node <T extends Comparable<? super T>> {

    private final T value;
    private int level;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T value) {
        this.value = value;
    }


    //возвращаем глубину узла
    public int getLevel() {
        return level;
    }
    // задаем глубину узла
    public void setLevel(int level) {
        this.level = level;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean shouldBeLeft(T value) {
        return value.compareTo(this.value) < 0;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Узел{" +
                "значение =" + value +
                '}';
    }

    public boolean isLeaf() {
        return getLeftChild() == null && getRightChild() == null;
    }
}
import java.util.Stack;

public class TreeImpl<E extends Comparable<? super E>> implements Tree<E> {

    private Node<E> root;
    private int maxLevel;


    //Конструктор (на вход получаем глубину дерева)
    public TreeImpl(int maxLevel) {
        this.maxLevel = maxLevel;
    }


    @Override
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //Метод для определения сбалансированности дерева
    private boolean isBalanced(Node node) {
        return (node == null) ||
                isBalanced(node.getLeftChild()) &&
                        isBalanced(node.getRightChild()) &&
                        Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1; //глубина дерева <=1
    }

    private int height(Node node) {
        //если узел пустой, то возвращаем 0, если нет, то прибавляем к 1 максимальную глубину справа или слева
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }


    //Внутренний класс для хранения текущего и предыдущего элемента
    private class NodeAndPrevious {
        Node<E> current;
        Node<E> previous;

        public NodeAndPrevious(Node<E> current, Node<E> previous) {
            this.current = current;
            this.previous = previous;
        }
    }

    //Добавляем элемент в дерево
    @Override
    public boolean add(E value) {
        if (isEmpty()) {
            root = new Node<>(value);
            return true;
        }
        NodeAndPrevious nodeAndPrevious = doFind(value);
        Node<E> previous = nodeAndPrevious.previous;

        if (nodeAndPrevious.current != null) {
            return false;//Проверка на дублирование
        }

        int level = previous.getLevel() + 1; //получаем текущую глубину
        if (level > maxLevel) {
            return false; //если больше - выходим со значением false
        }

        Node<E> newValue = new Node<>(value);
        Node<E> parent = nodeAndPrevious.previous;

        if (parent.shouldBeLeft(value)) {
            parent.setLeftChild(newValue);
        } else {
            parent.setRightChild(newValue);
        }
        return true;
    }

    //Удаление элемента
    @Override
    public boolean remove(E value) {
        NodeAndPrevious nodeAndPrevious = doFind(value);
        Node<E> removedNode = nodeAndPrevious.current;
        Node<E> parent = nodeAndPrevious.previous;
        if (removedNode == null) {
            return false;
        }
        if (removedNode.isLeaf()) {
            removeLeaf(removedNode, parent); //удаляем лист
        } else if (hasOnlySingleChildNode(removedNode)) {
            removeNodeWithSingleChild(removedNode, parent);//удаляем с одним потомком
        } else {
            removeCommonNode(removedNode, parent); //удаляем с двумя потомками
        }
        return true;
    }

    //Метод удаление элемента без потомков (листья)
    private void removeLeaf(Node<E> removedNode, Node<E> parent) {
        if (removedNode == root) {
            root = null;
        } else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(null);
        } else {
            parent.setRightChild(null);
        }
    }

    //Метод удаление элемента с одним потомком
    private void removeNodeWithSingleChild(Node<E> removedNode, Node<E> parent) {
        Node<E> childNode = removedNode.getLeftChild() != null //если не пустой левый
                ? removedNode.getLeftChild() //то удаляем левый
                : removedNode.getRightChild(); //иначе удаляем правый

        if (removedNode == root) {
            root = childNode;
        } else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(childNode);
        } else {
            parent.setRightChild(childNode);
        }
    }

    //Метод определения элемента с одним потомком
    private boolean hasOnlySingleChildNode(Node<E> node) {
        return node.getLeftChild() != null ^ node.getRightChild() != null; //XOR
    }

    //Метод удаление элемента с двумя потомками
    private void removeCommonNode(Node<E> removedNode, Node<E> parent) {
        Node<E> successor = getSuccessor(removedNode);
        if (removedNode == root) {
            root = successor;
        } else if (parent.getLeftChild() == removedNode) {
            parent.setLeftChild(successor);
        } else {
            parent.setRightChild(successor);
        }
        successor.setLeftChild(removedNode.getLeftChild());
    }

    //Метод определения наследника
    private Node<E> getSuccessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }
        if (successor != removedNode.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedNode.getRightChild());
        }
        return successor;
    }

    //Поиск элемента
    @Override
    public boolean find(E value) {
        NodeAndPrevious nodeAndPrevious = doFind(value);
        return nodeAndPrevious.current != null;
    }

    private NodeAndPrevious doFind(E value) {
        Node<E> previous = null;
        Node<E> current = root;

        current.setLevel(1); //устанавливаем глубину 1

        while (current != null) {
            //если предыдущий не пустой, добавляем уровень на 1
            if (previous != null) {
                current.setLevel(previous.getLevel() + 1);
            }
            if (current.getValue().equals(value)) {
                return new NodeAndPrevious(current, previous);
            }

            previous = current;
            if (current.shouldBeLeft(value)) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        return new NodeAndPrevious(null, previous);
    }

    //Утилитный метод определения наличия элемента
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    //Метод прохода по дереву
    @Override
    public void traverse(TraverseMode traverseMode) {
        switch (traverseMode) {
            case IN_ORDER:
                inOrder(root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            default:
                throw new IllegalArgumentException("Unknown traverse mode " + traverseMode);
        }
    }

    //проход по порядку
    private void inOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeftChild());
        System.out.println(node);
        inOrder(node.getRightChild());
    }

    //обратный проход
    private void postOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeftChild());
        inOrder(node.getRightChild());
        System.out.println(node);
    }

    //проход с корня
    private void preOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        inOrder(node.getLeftChild());
        inOrder(node.getRightChild());
    }


    //Метод вывода дерева на экран
    @Override
    public void display() {
        Stack<Node> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;
        boolean isRowEmpty = false;
        System.out.println("----------------------------------------------------------------------------------------" +
                "------------------------------------");
        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>();
            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }
            while (!globalStack.isEmpty()) {
                Node tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
            nBlanks /= 2;
        }
        System.out.println("----------------------------------------------------------------------------------------" +
                "------------------------------------");
    }

}

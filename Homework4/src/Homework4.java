import iterator.ListIterator;
import linkedlist.*;
import queue.*;
import stack.*;

/**
 * Algorithms and data structures in Java. Basic course. Lesson 4.
 *
 * @author Dmitry Pozdeyev
 * @version 26.05.2019
 */

/*
 Урок 4. Связанные списки
1. Реализовать все классы, рассмотренные в данном уроке.
2. В методе main LinkIteratorApp проверить все методы итератора.
3*. реализовать forEach для связанного списка
*/

public class Homework4 {

    public static void main(String[] args) {

        System.out.println("ЗАДАНИЕ №1");
        testLinkedList();
        testTwoSideLinkedList();
        testStack();
        testQueue();

        System.out.println("ЗАДАНИЕ №2 И №3");
        testIterator();

        //     java.util.Deque<Integer> queue = new java.util.LinkedList<>();
    }


    private static void testLinkedList() {

        System.out.println("ТЕСТ СВЯЗАННОГО СПИСКА (SimpleLinkedList)");
        LinkedList<Integer> linkedList = new SimpleLinkedListImpl<>();
        linkedList.insertFirst(1);
        linkedList.insertFirst(2);
        linkedList.insertFirst(3);
        linkedList.insertFirst(4);
        linkedList.insertFirst(5);
        linkedList.insertFirst(6);

        linkedList.display();

        System.out.println("Size = " + linkedList.getSize());
        System.out.println("\nУдаляем первый номер и цифру 2:");
        linkedList.removeFirst();
        linkedList.remove(2);

        linkedList.display();

        System.out.println("\nУдаляем все с конца в начало и выводим в консоль:");
        while (!linkedList.isEmpty()) {
            System.out.println(linkedList.removeFirst());
        }
    }

    private static void testTwoSideLinkedList() {
        System.out.println("\nТЕСТ ДВУСТОРОННЕГО СВЯЗАННОГО СПИСКА (TwoSideLinkedList):\n" +
                " c 1 по 4 ставим в начало (insertFirst), 5 и 6 в конец (insertLast)");
        TwoSideLinkedList<Integer> linkedList = new TwoSideLinkedListImpl<>();
        linkedList.insertFirst(1);
        linkedList.insertFirst(2);
        linkedList.insertFirst(3);
        linkedList.insertFirst(4);
        linkedList.insertLast(5);
        linkedList.insertLast(6);

        linkedList.display();
        System.out.println("Size = " + linkedList.getSize());

        System.out.println("\nУдаляем первый номер и цифру 3:");
        linkedList.removeFirst();
        linkedList.remove(3);

        linkedList.display();

        System.out.println("\nУдаляем все с конца в начало и выводим в консоль:");
        while (!linkedList.isEmpty()) {
            System.out.println(linkedList.removeFirst());
        }
    }

    private static void testStack() {

        System.out.println("\nТЕСТ CТЕКА НА ОСНОВЕ СВЯЗАННОГО СПИСКА (ПОСЛЕДНИЙ ВЫХОДИТ ПЕРВЫМ) (LinkedStack)");
        Stack<Integer> stack = new LinkedStackImpl<>();
        addToStack(stack, 1);
        addToStack(stack, 2);
        addToStack(stack, 3);
        addToStack(stack, 4);
        addToStack(stack, 5);
        addToStack(stack, 6);

        System.out.println("Top of stack is " + stack.peek());

//        stack.clear();

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private static void testQueue() {
        System.out.println("\nТЕСТ ОЧЕРЕДИ НА ОСНОВЕ СВЯЗАННОГО СПИСКА (ПЕРВЫЙ ДОБАВЛЕННЫЙ ВЫХОДИТ ПЕРВЫМ) (LinkedQueue)");
        Queue<Integer> queue = new LinkedQueueImpl<>();
        addToQueue(queue, 1);
        addToQueue(queue, 2);
        addToQueue(queue, 3);
        addToQueue(queue, 4);
        addToQueue(queue, 5);
//            queue.insert(6);

        System.out.println("Top of queue is " + queue.peek());

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
//        queue.remove();
    }

    private static void addToQueue(Queue<Integer> queue, int value) {
        if (!queue.isFull()) {
            queue.insert(value);
        }
    }

    private static void addToStack(Stack<Integer> stack, int value) {
        if (!stack.isFull()) {
            stack.push(value);
        }
    }

    private static void testIterator() {

        LinkedList<Integer> linkedList = new SimpleLinkedListImpl<>();
        linkedList.insertFirst(1);
        linkedList.insertFirst(2);
        linkedList.insertFirst(3);
        linkedList.insertFirst(4);
        linkedList.insertFirst(5);
        linkedList.insertFirst(6);

        linkedList.display();

        ListIterator<Integer> iterator = (ListIterator) linkedList.iterator();
        iterator.reset();
        iterator.insertAfter(555);
        iterator.next();
        iterator.remove();
        iterator.insertBefore(0);

        while (iterator.hasNext()) {
            Integer next = iterator.next();
        }

        displayAll(linkedList);
    }

    private static void displayAll(LinkedList<Integer> list) {

        //for each
        for (Integer value : list) {
            System.out.println(value);
        }
    }
}
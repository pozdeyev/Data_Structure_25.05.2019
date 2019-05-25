import deque.*;
import queue.*;
import stack.*;

/**
 * Algorithms and data structures in Java. Basic course. Lesson 3.
 *
 * @author Dmitry Pozdeyev
 * @version 25.05.2019
 */

 /*
 Урок 3. Массивы и сортировка
1. Реализовать рассмотренные структуры данных в консольных программах.
2. Создать программу, которая переворачивает вводимые строки (читает справа налево).
3. Создать класс для реализации дека.
 */


public class Homework3 {

    public static void main(String[] args) {

        //Задание 1. Реализовать рассмотренные структуры данных в консольных программах.
        System.out.println("ЗАДАНИЕ №1");
        //Тест стека (Stack) (последний добавленый, первый на выход)
        testStack();
        //Тест очереди (Queue) (первый добавленный, первый на выход)
        testQueue();
        //Тест очереди с приоритетом (PriorityQueue) (на выход в соответствии с приоритетом)
        testPriorityQueue();

        //Задание 2.  Создать программу, которая переворачивает вводимые строки (читает справа налево).
        System.out.println("ЗАДАНИЕ №2\n");
        String text = new String("QWERTY");
        rotateString(text);

        //Задание №3. Создать класс для реализации дека (одновременно стек и очередь)
        System.out.println("\nЗАДАНИЕ №3\nЗаполняем дек: \n");
        testDeque();
    }

    private static void rotateString(String text) {

        //Выводим исходную строку
        System.out.println("Исходная строка: \n" + text + "\n");

        //Инициализируем стек длиной в строку
        Stack<Character> stack_text = new StackImpl<>(text.length());

        for (int i = 0; i < text.length(); i++) {
            stack_text.push(text.charAt(i)); //добавляем по символу у стек
        }
        System.out.println("Строка в обратном порядке:");
        while (!stack_text.isEmpty()) {
            System.out.print(stack_text.pop()); //забираем из стека
        }
        System.out.println();

    }


    private static void testStack() {

        System.out.println("\nТест стека (последний добавленный, первый на выход)\n");
        Stack<Integer> stack = new StackImpl<>(5);
        addToStack(stack, 1);
        addToStack(stack, 2);
        addToStack(stack, 3);
        addToStack(stack, 4);
        addToStack(stack, 5);
        //  addToStack(stack, 6);
        System.out.println("Top of stack is " + stack.peek());
        //    stack.clear();
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        //stack.pop();
        System.out.println("-----------------------------");
    }

    private static void testQueue() {
        System.out.println("\nТест очереди (первый добавленный, первый на выход)\n");
        Queue<Integer> queue = new QueueImpl<>(5);
        addToQueue(queue, 1);
        addToQueue(queue, 2);
        addToQueue(queue, 3);
        addToQueue(queue, 4);
        addToQueue(queue, 5);
        // addToQueue(queue, 6);
        // queue.insert(6);

        System.out.println("Top of queue is " + queue.peek());
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
        //    queue.remove();
        System.out.println("-----------------------------");
    }


    private static void testPriorityQueue() {

        System.out.println("\nТест очереди с приоритетом (на выход в соответствии с приоритетом)." +
                " 1-самый высокий приоритет, 5-самый низкий. \n");
        Queue<Integer> queue = new PriorityQueue<>(5);
        addToQueue(queue, 2);
        addToQueue(queue, 4);
        addToQueue(queue, 1);
        addToQueue(queue, 5);
        addToQueue(queue, 3);
        // addToQueue(queue, 6);
        // queue.insert(6);

        System.out.println("Top of priority queue is " + queue.peek());
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
        //    queue.remove();
        System.out.println("-----------------------------");
    }

    private static void testDeque() {

        //Создаем Дек
        DequeCustom<Integer> deque = new DequeImplements<>(7);

        fillDeque(deque);

        System.out.println(" \nСлева направо (как очередь Queue):");
        LeftToRight(deque);
        System.out.println("\nЗаполняем дек снова:");
        fillDeque(deque);

        System.out.println(" \nСправо налево (как стек Stack): \n");
        RightToLeft(deque);

    }

    private static void fillDeque(DequeCustom<Integer> deque) {
        insertRight(deque, 1);
        System.out.println("insertRight(1)");
        insertRight(deque, 2);
        System.out.println("insertRight(2)");
        insertRight(deque, 3);
        System.out.println("insertRight(3)");
        insertRight(deque, 4);// 1 2 3 4
        System.out.println("insertRight(4)");
        insertLeft(deque, 5); // 5 1 2 3 4
        System.out.println("insertLeft(5)");
        insertLeft(deque, 6); // 6 5 1 2 3 4
        System.out.println("insertLeft(6)");
        insertRight(deque, 7); //6 5 1 2 3 4 7
        System.out.println("insertRight(7)");
    }


    //Вставка справа
    private static <E> void insertRight(DequeCustom<E> deque, E value) {
        if (!deque.isFull()) {
            deque.insertRight(value);
        }
    }

    //Вставка слева
    private static <E> void insertLeft(DequeCustom<E> deque, E value) {
        if (!deque.isFull()) {
            deque.insertLeft(value);
        }
    }

    //Очищение слева направо (начало в конец)
    private static <E> void LeftToRight(DequeCustom<E> deque) {
        while (!deque.isEmpty()) {
            System.out.println(deque.removeLeft());
        }
    }

    //Очищение справа налево (конец в начало)
    private static <E> void RightToLeft(DequeCustom<E> deque) {
        while (!deque.isEmpty()) {
            System.out.println(deque.removeRight());
        }
    }


    private static void addToStack(Stack<Integer> stack, int value) {
        if (!stack.isFull()) {
            stack.push(value);
        }
    }

    private static void addToQueue(Queue<Integer> queue, int value) {
        if (!queue.isFull()) {
            queue.insert(value);
        }
    }


}

package ru.geekbrains.datastructure;

import ru.geekbrains.datastructure.queue.PriorityQueue;
import ru.geekbrains.datastructure.queue.Queue;
import ru.geekbrains.datastructure.queue.QueueImpl;
import ru.geekbrains.datastructure.stack.Stack;
import ru.geekbrains.datastructure.stack.StackImpl;

public class Main3 {

    public static void main(String[] args) {
        //       testStack();

             testQueue();

    }

    private static void testQueue() {
        //Queue<Integer> queue = new QueueImpl<>(5);
        Queue<Integer> queue = new PriorityQueue<>(5);
        addToQueue(queue, 2);
        addToQueue(queue, 4);
        addToQueue(queue, 1);
        addToQueue(queue, 5);
        addToQueue(queue, 3);
        // addToQueue(queue, 6);
        // queue.insert(6);

        System.out.println("Top of queue is " + queue.peek());
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
        //    queue.remove();
    }

    private static void addToQueue(Queue<Integer> queue, int value) {
        if (!queue.isFull()) {
            queue.insert(value);
        }
    }


    private static void testStack() {
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
    }


    private static void addToStack(Stack<Integer> stack, int value) {
        if (!stack.isFull()) {
            stack.push(value);
        }
    }


}



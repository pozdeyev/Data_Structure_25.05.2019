import java.util.Stack;

/**
 * Algorithms and data structures in Java. Basic course. Lesson 7
 * @author Dmitry Pozdeyev
 * @version 08.06.2019
 */

/*
 Урок 7. Графы
1. Реализовать программу, в которой задается граф из 10 вершин. Задать ребра и найти
кратчайший путь с помощью поиска в ширину

Для реализации воспользовался классами написаными на уроке.
*/


public class Homework7 {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Тамбов");
        graph.addVertex("Липецк");
        graph.addVertex("Орел");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");
        graph.addVertex("Москва");



        graph.addEdge("Москва","Тула");
        graph.addEdge("Москва","Калуга");
        graph.addEdge("Москва","Рязань");
        graph.addEdge("Тула","Липецк");
        graph.addEdge("Рязань","Тамбов");
        graph.addEdge("Калуга","Орел");
        graph.addEdge("Липецк","Воронеж");
        graph.addEdge("Тамбов","Саратов");
        graph.addEdge("Орел","Курск");
        graph.addEdge("Саратов","Воронеж");
        graph.addEdge("Курск","Воронеж");


        Stack<String> path = graph.findShortPathViaBfs("Рязань", "Курск");
        System.out.println("\n Кратчайший путь:");
        showShortPath(path);
    }

    private static void showShortPath(Stack<String> path) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        while ( !path.isEmpty() ) {
            if (!isFirst) {
                sb.append(" -> ");
            }
            isFirst = false;
            sb.append(path.pop());
        }

        System.out.println(sb);
    }


}


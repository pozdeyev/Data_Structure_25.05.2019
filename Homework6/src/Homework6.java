import java.util.Random;

/**
 * Algorithms and data structures in Java. Basic course. Lesson 6.
 *
 * @author Dmitry Pozdeyev
 * @version 02.06.2019
 */

/*
 Урок 6. Деревья
1. Создать и запустить программу для построения двоичного дерева.
В цикле построить двадцать деревьев с глубиной в 4 уровня.
Данные, которыми необходимо заполнить узлы деревьев, представляются в виде чисел типа int.
Число, которое попадает в узел, должно генерироваться случайным образом в диапазоне от -20 до 20.
2. Проанализировать, какой процент созданных деревьев являются несбалансированными.

Для реализации воспользовался классами написаными на уроке.
*/


public class Homework6 {


    public static void main(String[] args) {
        Random rand = new Random(); //Инициализируем генератор случайных чисел
        int maxLevel = 4; //Максимальная глубина
        int treeCount = 20; //Количество деревьев
        int nodeCount = (int) (Math.pow(2, maxLevel) - 1); //Количество узлов (2^maxlevel-1)
        int maxValue = 20; //Максимальное значение
        int balancedCount = 0; //Счетчик сбалансированных деревьев

        System.out.println("\nЗАДАНИЕ №1 и 2. Генерируем "+treeCount +" деревьев с глубиной " + maxLevel + " уровня." +
                "\nНа экран выводим только сбалансированые деревья (длина пути от корня до любого листового элемента" +
                " одинакова или отличается не более, чем на единицу)\n" );


        for (int i = 0; i < treeCount; i++) {
            Tree<Integer> testTree = new TreeImpl<>(maxLevel);
            initRandomTree(rand, nodeCount, maxValue, testTree);
            if (testTree.isBalanced()) {
                balancedCount++;
                System.out.println ("Дерево №"+i + " сбалансировано");
                    testTree.display();
                }
            }


System.out.println("Всего сбалансированных деревьев: " + balancedCount + " из " + treeCount + " сгенерированных.\n"+
        "Процент сбалансированных деревьев: " + ((balancedCount / (treeCount * 1.0)) * 100) + "%");

    }
        //Метод заполняющий дерево числами
        private static void initRandomTree (Random rand,int nodeCount, int maxValue, Tree testTree){
            for (int i = 0; i < nodeCount; i++) {
                testTree.add(rand.nextInt(maxValue * 2 + 1) - maxValue);
            }
        }


}
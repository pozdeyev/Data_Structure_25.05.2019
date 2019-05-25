import java.util.Scanner;

/**
 * Algorithms and data structures in Java. Basic course. Lesson 2.
 *
 * @author Dmitry Pozdeyev
 * @version 19.05.2019
 */

 /*
 Урок 2. Массивы и сортировка
 1. Создать массив большого размера (10 000 элементов).
2. Написать методы удаления, добавления, поиска элемента массива.
3. Заполнить массив случайными числами.
4. Написать методы, реализующие рассмотренные виды сортировок, и проверить скорость выполнения каждой.
 */


public class Homework2 {

    private static final int BIG_SIZE_TEST = 50_000;//число элементов в массиве
    private static final int SMALL_SIZE_TEST = 25;//число элементов в массиве
    private static final int MIN = 0; //минимальное число в массиве
    private static final int MAX = 9; //максимальное число в массиве


    public static void main(String[] args) {

        System.out.println("Homework 2");


        //Задание 1
        //Создаем исходный массив  от MIN до MAX размером BIGSIZETEST

        System.out.println("\nУпражнение 1. Создать массив большого размера (" + BIG_SIZE_TEST + " элементов)");
        ArrayCustom<Integer> arrayBig = new ArrayImplements<>(BIG_SIZE_TEST); //Инициируем массив типа Integer
        stop();


        System.out.println("Массив из " + BIG_SIZE_TEST + " элементов создан ");

        System.out.println("--------------------------------------------------------------------------------");
        //Задание 2
        //Создаем исходный массив случайных чисел от MIN до MAX размером SMALLSIZETEST
        ArrayCustom<Integer> arraySmall = new ArrayImplements<>(SMALL_SIZE_TEST); //Инициируем массив типа Integer
        arraySmall.generateRandomIntegerArray(SMALL_SIZE_TEST, MIN, MAX); //Создаем массив случайных чисел


        System.out.println("\nУпражнение 2. Методы удаления, добавления, поиска элемента массива. На примере массива из " +
                SMALL_SIZE_TEST + " элементов:");

        System.out.println(arraySmall); //Выводим в консоль маленький массив

        System.out.println("\nУдаление элемента массива\n");
        int e = readInteger("Какое число удалить в массиве от 0 до " + MAX + "? ", MAX, MIN);

        arraySmall.remove(e);
        System.out.println("Обновленый массив (удаляем первое совпадение):");
        System.out.println(arraySmall);


        System.out.println("\nДобавление элемента в массив\n");
        e = readInteger("Какое число добавить в массив от 0 до " + MAX + "? ", MAX, MIN);

        arraySmall.add(e);
        System.out.println("Обновленый массив (добавляем число в конец массива):");
        System.out.println(arraySmall);


        System.out.println("\nПоиск числа в массиве\n");
        e = readInteger("Какое число найти в массиве от 0 до " + MAX + "? ", MAX, MIN);

        int index = arraySmall.indexOf(e);
        System.out.println("Индекс первого совпадения: " + index);

        stop();

        //Задание 3
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nУпражнение 3. Заполнить массив из " + BIG_SIZE_TEST + " элементов случайными числами");
        stop();
        System.out.println("Массив из " + BIG_SIZE_TEST + " элементов заполнен случайными числами от " + MIN + " до " +
                MAX + " создан");
        arrayBig.generateRandomIntegerArray(BIG_SIZE_TEST, MIN, MAX); //Создаем массив случайных чисел

        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\nУпражнение 4. Написать методы, реализующие рассмотренные виды сортировок, " +
                "и проверить скорость выполнения каждой на массиве из упражнения 3");

        //Создаем 3 идентичные копии массива
        ArrayCustom<Integer> arrayBubble = new ArrayImplements<>(BIG_SIZE_TEST);
        ArrayCustom<Integer> arraySelect = new ArrayImplements<>(BIG_SIZE_TEST);
        ArrayCustom<Integer> arrayInsert = new ArrayImplements<>(BIG_SIZE_TEST);

        for (int i = 0; i < BIG_SIZE_TEST-1; i++) {
            arrayBubble.add(arrayBig.get(i));
            arraySelect.add(arrayBig.get(i));
            arrayInsert.add(arrayBig.get(i));
        }



        System.out.println("\nПузырьковая сортировка (sort bubble)");
        stop();

        //Измерение времени выполнения метода сортировки: SortBubble
        long startTime = System.nanoTime();
        arrayBubble.sortBubble();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1_000_000;
        System.out.println("Время пузырьковой сортировки на массиве из " + BIG_SIZE_TEST + " элементов "
                + "составляет: " + duration + " миллисекунд");



        System.out.println("\nCортировка методом выбора (sort select)");
        stop();
        //Измерение метода сортировки: SortBubble
        startTime = System.nanoTime();
        arraySelect.sortSelect();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.println("Время сортировки методом выбора на массиве из " + BIG_SIZE_TEST + " элементов "
                + "составляет: " + duration + " миллисекунд");



        System.out.println("\nCортировка методом вставки (sort insert)");
        stop();
        //Измерение метода сортировки: SortBubble
        startTime = System.nanoTime();
        arrayInsert.sortInsert();
        endTime = System.nanoTime();
        duration = (endTime - startTime)/1000000;

        System.out.println("Время сортировки методом вставки на массиве из " + BIG_SIZE_TEST + " элементов "
                + "составляет: " + duration + " миллисекунд");



    }

    //Вспомогательный метод ввода целого числа

    static int readInteger(String text, int max, int min) {

        Scanner userIn = new Scanner(System.in);
        System.out.print(text);
        int arg = 0; //Инициируем переменную

        if (userIn.hasNextInt()) { //Проверка корректности ввода
            arg = userIn.nextInt();

            if ((arg < min) || (arg > max)) { //Проверка выхода из диапазона
                System.err.println("Число вне диапазона от " + min + " до " + max + " ");
                arg = readInteger(text, max, min); //Рекурсивный запуск метода пока не введут корректное число
            }

        } else {
            System.err.println("Вы не ввели число типа integer. Введите число снова.\n");
            arg = readInteger(text, max, min); //Рекурсивный запуск метода пока не введут корректное число
        }
        return arg;
    }

    static void stop() {
        Scanner userIn = new Scanner(System.in);
        System.out.print("\nНажмите ENTER");
        userIn.nextLine();
    }

}
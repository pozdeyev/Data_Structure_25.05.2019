/**
 * Algorithms and data structures in Java. Basic course. Lesson 8.
 *
 * @author Dmitry Pozdeyev
 * @version 10.06.2019
 */

/*
 Урок 8. Хеш-таблицы
1. Создать программу, реализующую метод цепочек.
*/



public class Homework8 {

    public static void main(String[] args) {

        HashMapLink hashMapLink=new HashMapLink();

        hashMapLink.put("Володимир",2);
        hashMapLink.put("Дмитрий",3);
        hashMapLink.put("Николай",6);
        hashMapLink.put("Дмитрий",4);


        System.out.println(hashMapLink.get("Дмитрий"));
        hashMapLink.delete("Дмитрий");

        System.out.println(hashMapLink.get("Дмитрий"));
    }


}

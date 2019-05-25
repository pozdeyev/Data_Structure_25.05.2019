import java.util.ArrayList;
import java.util.List;

public class Main1 {

private static int b;
    public static void main (String[]apps){
        int a=5; //в методе нужно инициализировать значения
        System.out.println(a);

        List<String> list =new ArrayList<>();

        Person p1=new Person("Ivan", 25);
        Person p2=new Person("Ivan", 25);

        System.out.println(p1==p2); //сравнивает значение ячейки ассоцируемой с переменной (сравниваем адреса)
        System.out.println("p1 equals p2: "+p1.equals(p2));

        String str1="str";
        String str2=new String("str");

        System.out.println("str1 == str2: "+ (str1==str2));
        System.out.println("str1 equals str2: "+ str1.equals(str2));

        int c=5;
        int d=5;

        System.out.println(c==d);

        System.out.println("-----------------------------");
        System.out.println(c);
        testA(c);
        System.out.println(c);

        System.out.println("-----------------------------");
        Person p3=new Person("Ivan", 25);
        System.out.println(p3);
        testB(p3);
        System.out.println(p3);
    }

    private static void testB(Person p3) {
        System.out.println(p3);
        p3.setName("Pavel"); //через сеттер можно передать
        p3=new Person ("Petr",33); //разные переменные
        System.out.println(p3);
    }

    private static void testA(int c){
        System.out.println(c);
        c=7; //действует только внутри метода --> изменяем локальную переменую
        System.out.println(c);
    }


}

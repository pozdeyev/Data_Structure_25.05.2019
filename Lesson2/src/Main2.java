public class Main2 {

    public static void main (String[]args){
  //      testJdkArray();
  //      testArray();

        Array<Integer> array = new ArrayImpl<>();
        array.add(1);
        array.add(5);
        array.add(3);
        array.add(4);
        array.add(2);
        System.out.println(array);
       // array.sortBubble();
      //  array.sortSelect();
        array.sortInsert();
        System.out.println(array);
    }

    private static void testArray() {
       // Array<Integer> array = new ArrayImpl<>();
        Array<Integer> array = new SortedArrayImpl<>();

        System.out.println(array);

        array.add(1);
        array.add(5);
        array.add(3);
        array.add(4);
        array.add(2);

        System.out.println(array);

        System.out.println("Find 5 = " + array.contains(5));
        System.out.println("Index of 3 = " + array.indexOf(3));
        System.out.println("Index of 33 = " + array.indexOf(33));

        System.out.println("Remove 3: "+array.remove(3));
        System.out.println("Remove 33: "+array.remove(33));
        System.out.println("Index of 3 = " + array.indexOf(3));

        System.out.println(array);
        System.out.println("Size: " + array.getSize());

        System.out.println("-----------------");
        for (int i = 0; i < array.getSize(); i++) {
            System.out.println(array.get(i));
        }
    }

    private static void testJdkArray() {
        int[]array;
        array=new int[5];
        //две записи одного и того же

        //Integer[]arr=new Integer[]{1,2,3,4,5};
        //arr[0]=5;
        //arr[2]=48;

        Integer[]arr={1,2,3,4,5};

        for (Integer value : arr) {
            System.out.println(value);
        }
    }


}

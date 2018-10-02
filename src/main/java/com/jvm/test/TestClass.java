package com.jvm.test;

public class TestClass implements FieldImpleme {

//    int i = 0;

    public static void main(String... args) {

        int i = -1;
        TestClass testClass = new TestClass();
        System.out.println(testClass.i);
        i = testClass.i;
        i = TestClass.i;
        System.out.println(TestClass.i);
        i = FieldImpleme.i;

        Parent parent = new Children();
        System.out.print(parent.x + ",");
        parent.print();

        int var1 = 1;
        int avr2 = 2;
        System.out.println(--var1 + avr2++ + "" + var1 + avr2);
       /* Integer intObj = Integer.valueOf(args[args.length - 1]);
        int i = intObj.intValue();
        if (args.length > 1) {
            System.out.println(i);
        }

        if (args.length > 0) {
            System.out.println(i - 1);
        }else{
            System.out.println(i - 2);
        }*/
    }


}

class  Parent{

    int x = 5;
    public  void print() {
        System.out.println("Parent");

    }

}

class  Children extends  Parent{
    int x = 2;
    public  void print(){
        System.out.println("Children");

    }
}


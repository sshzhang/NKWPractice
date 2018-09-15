package com.CollectionCodeAnalysisSeven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class ArrayListClassTest extends  AbstractArrayListClassTest implements  ArrayListInterface {
    @Override
    public void getSize() {
    }

    @Override
    public int getAllSize() {
        return 0;
    }

    @Override
    public int ModifySign() {
        return 0;
    }

    public static void main(String... args) throws IOException {

        List<String>  arrs = new ArrayList<>();
        arrs.add("a");
        arrs.add("b");
        arrs.add("c");
        arrs.add("d");
        arrs.add("e");
        arrs.add("f");
        arrs.add("h");
        arrs.add("i");
        arrs.add("j");
        Spliterator<String> a =  arrs.spliterator();
        System.out.println(a);
        //此时结果：a:0-9（index-fence）
        Spliterator<String> b = a.trySplit();
        System.out.println(b);

        Spliterator<String> c = b.trySplit();
        System.out.println(c);

    }

    @Override
    public void text1(int size) {
        System.out.println("ArrayListClassTest.BinaryTreeNoReculsive()");
    }

    public void text1() {
        super.text1();
        System.out.println("ArrayListClassTest");
    }


}

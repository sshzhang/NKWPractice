package com.CollectionCodeAnalysisSeven;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

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
        int[] copy = Arrays.copyOf(new int[]{1, 2, 3}, 10);
        System.out.println(copy+" "+copy.length);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("h");
        strings.add("e");
        System.out.println(strings);
        strings.removeIf(new ArrayListPredictFilter<>());
        System.out.println(strings);
       /* ListIterator<String> stringListIterator =
                strings.listIterator();
        while (stringListIterator.hasNext()) {
            //stringListIterator.set(stringListIterator.next() + "12");
            stringListIterator.next();
            stringListIterator.remove();
        }
        System.out.println(strings);*/


    }

    @Override
    public void text1(int size) {
        System.out.println("ArrayListClassTest.text1()");
    }

    public void text1() {
        super.text1();
        System.out.println("ArrayListClassTest");
    }


}

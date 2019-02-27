package com.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

public class ThisEscape {

    private int count;

    private String value;

    private Set<innnerClass> innnerClassSet = new HashSet<>();

    public ThisEscape() {
        count = 12;
        innnerClass sm = new innnerClass() {//内部类包含一个外部类的引用
            @Override
            public void read() {
                System.out.println(ThisEscape.this.count);
                System.out.println(ThisEscape.this.value);
            }
        };
        innnerClassSet.add(sm);
        count = 10;

    }

    class innnerClass {
        public  void read() {
        }
}

    public static void main(String... args) {

        new ThisEscape().innnerClassSet.iterator().next().read();

    }


}

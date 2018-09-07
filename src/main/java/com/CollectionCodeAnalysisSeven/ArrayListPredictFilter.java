package com.CollectionCodeAnalysisSeven;

import java.util.function.Predicate;

public class ArrayListPredictFilter<T> implements Predicate<T> {
    @Override
    public boolean test(T string) {
        if (string instanceof String) {
            String str = (String) string;
            return str.indexOf("a") != -1 ? true : false;
        }
        return false;
    }

    @Override
    public Predicate<T> and(Predicate<? super T> other) {
        /*return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return false && other.test(t);
            }
        };*/
        // 下面和上面等价
        return (t) -> test(t) && other.test(t);
    }
}

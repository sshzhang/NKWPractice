package com.CollectionCodeAnalysisSeven;

public interface ArrayListInterface  extends  ArrayListBaseInterface{

    void getSize();

    default  int getAllSize() {
        return 12;
    }

    static int getStaticConstanLength() {
        return 13;
    }

     int ModifySign();

    void text1();

    void text2();
}

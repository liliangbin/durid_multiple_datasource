package com.example.oracle.rdbs;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;

public class Lamda {

    public static void main(String[] args) {
        Function func = (x) -> x;
        Function func2 = String::valueOf;
        System.out.println(new Timestamp(System.currentTimeMillis()));
        System.out.println("二年一班".compareTo("二年二班"));

        String[] run = {"hello ","info","tst","info"};
        String[] dn = {"hello ","info","tst","info"};
        List<String> info =new ArrayList<String>(Arrays.asList(run));
        List<String> miaomiao= Arrays.asList(dn);

        info.sort( new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Collections.sort(miaomiao, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println();

        System.out.println("rush ");
    }
}

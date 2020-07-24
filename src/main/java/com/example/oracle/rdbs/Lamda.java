package com.example.oracle.rdbs;

import java.util.function.Function;

public class Lamda {

    public static void main(String[] args) {
        Function func = (x) -> x;
        Function func2 = String::valueOf;
    }
}

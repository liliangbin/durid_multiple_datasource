package com.example.oracle.rdbs.binarysearch;

import io.swagger.models.auth.In;

public class DividedTwoInterger {


    public static void main(String[] args) {


//         int 的最小值 math.abs 后为负数，不变
//        int a = getDivided(-2147483648, 1);

        int b = getDivided(10, 3);
//        System.out.println(a);
        System.out.println(b);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }

    public static int getDivided(int dividend, int divisor) {
        boolean a = false;
        boolean b = false;
        if (dividend < 0) {
            a = true;
        }
        if (divisor < 0) {
            b = true;
        }
        dividend = a ? -dividend : dividend;
        divisor = b ? -divisor:dividend;
        int temp = 0;
        while (dividend >= divisor) {
            dividend = dividend - divisor;
            temp++;
        }
        if (a != b) {
            return -temp;
        }
        return temp;
    }
}

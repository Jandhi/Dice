package com.company;

public class Functions {
    public static int sum(int[] results) {
        int amount = 0;
        for(int i : results) {
            amount += i;
        }
        return amount;
    }
}

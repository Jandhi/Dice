package com.company;

import java.util.Arrays;

public class ResultPool implements ResultSource, IntSource {
    private int results[];

    public ResultPool(int[] results) {
        this.results = results;
        Arrays.sort(results);
    }

    @Override
    public String toString() {
        String s = "[";
        for(int i = 0; i < results.length; i++) {
            s += results[i];
            if(i < results.length - 1) s += " ";
        }
        s += "]";
        return s;
    }

    public int sum() {
        return Functions.sum(results);
    }

    public int get(int index) {
        return results[index];
    }

    public void set(int index, int value) {
        results[index] = value;
        Arrays.sort(results);
    }

    public int lowest() {
        return results[0];
    }

    public ResultPool lowest(int amount) {
        int[] results = new int[amount];
        for(int i = 0; i < amount; i++) {
            results[i] = this.results[i];
        }
        return new ResultPool(results);
    }

    public int highest() {
        return results[results.length];
    }

    public ResultPool highest(int amount) {
        int[] results = new int[amount];
        for(int i = 0; i < amount; i++) {
            results[amount - 1 - i] = this.results[this.results.length - 1 - i];
        }
        return new ResultPool(results);
    }

    @Override
    public ResultPool roll() {
        return this;
    }

    @Override
    public int getInt() {
        return sum();
    }
}

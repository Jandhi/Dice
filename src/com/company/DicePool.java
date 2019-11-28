package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DicePool implements ResultSource, IntSource {
    private Map<Integer, Integer> sidesAmountMap;

    public DicePool() {
        sidesAmountMap = new HashMap<>();
    }

    public DicePool(int amount, int sides) {
        this();
        sidesAmountMap.put(sides, amount);
    }

    public DicePool(Map<Integer, Integer> sidesAmountMap) {
        this.sidesAmountMap = sidesAmountMap;
    }

    private int getAmount() {
        int total = 0;
        for(Integer amount : sidesAmountMap.values()) {
            total += amount;
        }
        return total;
    }

    @Override
    public ResultPool roll() {
        int[] results = new int[getAmount()];
        int index = 0;
        for(Map.Entry<Integer, Integer> sidesAmountPair : sidesAmountMap.entrySet()) {
            for(int i = 0; i < sidesAmountPair.getValue(); i++) {
                results[index] = new Random().nextInt(sidesAmountPair.getKey()) + 1;
                index++;
            }
        }
        return new ResultPool(results);
    }

    @Override
    public int getInt() {
        return roll().sum();
    }
}

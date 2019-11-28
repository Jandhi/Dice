package com.company;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class PercentTable {
    private String name;
    private int size;
    private String [] category;
    private double [] value;

    public PercentTable(String name, int size, String[] category, double[] value) {
        this.name = name;
        this.size = size;
        this.category = category;
        this.value = value;
    }

    public PercentTable(String name, Map<Integer, Double> table) {
        this.name = name;
        int index = 0;
        size = table.size();
        category = new String[size];
        value = new double[size];
        for(Map.Entry<Integer, Double> entry : table.entrySet()) {
            category[index] = "" + entry.getKey();
            value[index] = entry.getValue();
            index++;
        }
    }

    @Override
    public String toString() {
        String s = name;
        for(int i = 0; i < size; i++) {
            s += "\n" + category[i] + ": " + String.format("%.2f", value[i]);
        }
        return s;
    }

    public PercentTable mergeCategories(Pair<Integer, Integer> ... pairs) {
        for(Pair<Integer, Integer> pair : pairs) {
            size -= (pair.getValue() - pair.getKey());
        }
        size++;
        String[] newCategories = new String[size];
        double[] newValues = new double[size];

        int index = 0;
        for(int i = 0; i < pairs.length; i++) {
            //BOTTOM LEFTOVER
            while(index < pairs[i].getKey()) {
                newCategories[i] = category[index];
                newValues[i] = value[index];
                index++;
            }

            newCategories[i] = pairs[i].getKey() + "-" + pairs[i].getValue();

            while(index <= pairs[i].getValue() && index < value.length) {
                newValues[i] += value[index];
                index++;
            }
        }

        //TOP LEFTOVER
        while(index < value.length) {
            newCategories[index + size - value.length] = category[index];
            newValues[index + size - value.length] = value[index];
            index++;
        }

        return new PercentTable(this.name, size, newCategories, newValues);
    }

    public PercentTable mergeNamedCategories(Pair<Pair<Integer, Integer>, String> ... pairs) {
        for(Pair<Pair<Integer, Integer>, String> pair : pairs) {
            size -= (pair.getKey().getValue() - pair.getKey().getKey());
        }
        size++;
        String[] newCategories = new String[size];
        double[] newValues = new double[size];

        int index = 0;
        for(int i = 0; i < pairs.length; i++) {
            //BOTTOM LEFTOVER
            while(index < pairs[i].getKey().getKey()) {
                newCategories[i] = category[index];
                newValues[i] = value[index];
                index++;
            }

            newCategories[i] = pairs[i].getValue();

            while(index <= pairs[i].getKey().getValue() && index < value.length) {
                newValues[i] += value[index];
                index++;
            }
        }

        //TOP LEFTOVER
        while(index < value.length) {
            newCategories[index + size - value.length] = category[index];
            newValues[index + size - value.length] = value[index];
            index++;
        }

        return new PercentTable(this.name, size, newCategories, newValues);
    }
}

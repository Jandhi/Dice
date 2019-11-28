package diceroller;

import javafx.util.Pair;

public class Main {

    public static void main(String[] args) {
        output(new StatMachine(new DicePool(3, 6), "3d6").getTable().mergeNamedCategories(new Pair<>(new Pair<>(0, 7), "failure"), new Pair<>(new Pair<>(8, 11), "partial"), new Pair<>(new Pair<>(12, 16), "success")));
        output(new StatMachine(new Reroller(new DicePool(3, 6), 1, 4, new DicePool(1, 6)), "3d6 1 reroll").getTable().mergeNamedCategories(new Pair<>(new Pair<>(0, 7), "failure"), new Pair<>(new Pair<>(8, 11), "partial"), new Pair<>(new Pair<>(12, 16), "success")));
        output(new StatMachine(new Reroller(new DicePool(3, 6), 2, 4, new DicePool(1, 6)), "3d6 2 reroll").getTable().mergeNamedCategories(new Pair<>(new Pair<>(0, 7), "failure"), new Pair<>(new Pair<>(8, 11), "partial"), new Pair<>(new Pair<>(12, 16), "success")));
    }

    private static void output(ResultPool pool, String name) {
        System.out.println(name + ": " + pool);
    }

    private static void output(ResultPool pool) {
        System.out.println(pool);
    }

    private static void output(PercentTable table) {
        System.out.println(table);
    }
}

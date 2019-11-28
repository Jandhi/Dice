package diceroller;

import java.util.HashMap;
import java.util.Map;

public class StatMachine {
    public enum Speed {
        LIGHTNING(10000L),
        QUICK(100000L),
        MID(1000000L),
        CAREFUL(10000000L),
        SLOW(100000000L);

        private long value;

        Speed(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }
    }

    private HashMap<Integer, Long> resultAmountMap;
    private ResultSource source;
    private String name;
    private Speed speed = Speed.CAREFUL;
    private final long limit;
    private final int decimalPlaces = 2;

    public StatMachine(ResultSource source, String name) {
        this.source = source;
        this.name = name;
        this.limit = speed.getValue();
        resultAmountMap = new HashMap<>();
    }

    public StatMachine(ResultSource source, Speed speed, String name) {
        this.source = source;
        this.speed = speed;
        this.limit = speed.getValue();
        this.name = name;
        resultAmountMap = new HashMap<>();
    }

    private void roll() {
        for(long i = 0; i < limit; i++) {
            addTo(source.roll().sum());
        }
    }

    private void addTo(int result) {
        if(resultAmountMap.containsKey(result)) {
            resultAmountMap.put(result, resultAmountMap.get(result) + 1);
        } else {
            resultAmountMap.put(result, 1L);
        }
    }

    public void change(String name, ResultSource source) {
        this.name = name;
        this.source = source;
    }

    public String getPercent(Long amount) {
        int multiplier = (int) Math.pow(10, decimalPlaces);
        int pc = (int) ((amount * 100 * multiplier) / limit);
        int num = pc / multiplier;
        int dec = pc % multiplier;
        return num + "." + dec;
    }

    public PercentTable getTable() {
        roll();
        Map<Integer, Double> table = new HashMap<>();
        for(Map.Entry<Integer, Long> resultAmountPair : resultAmountMap.entrySet()) {
            table.put(resultAmountPair.getKey(), Double.valueOf(getPercent(resultAmountPair.getValue())));
        }
        return new PercentTable(name, table);
    }
}

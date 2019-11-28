package diceroller;

public class Reroller implements ResultSource {
    private ResultSource src;
    private int amount;
    private int target;
    private IntSource reroll;

    public Reroller(ResultSource src, int amount, int target, IntSource reroll) {
        this.src = src;
        this.amount = amount;
        this.target = target;
        this.reroll = reroll;
    }

    @Override
    public ResultPool roll() {
        ResultPool result = src.roll();
        for(int i = 0; i < amount; i++) {
            if(result.lowest() < target) result.set(0, reroll.getInt());
        }
        return result;
    }
}

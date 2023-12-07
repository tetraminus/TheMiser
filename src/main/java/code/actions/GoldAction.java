package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class GoldAction extends AbstractGameAction {
    private boolean gain;


    public GoldAction(int amount, boolean gain) {
        this.amount = amount;
        this.gain = gain;
    }

    public GoldAction(int amount) {
        this(amount, true);
    }

    public void update() {
        if (gain) {
            this.gainGold();
        } else {
            this.loseGold();
        }
        this.isDone = true;
    }

    private void gainGold() {
        if (this.amount > 0) {
            player.gainGold(amount);
        }
    }

    private void loseGold() {
        if (this.amount > 0) {
            player.loseGold(amount);
        }
    }

}

package code.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;

import static code.TheMiserMod.makeID;


public class HoldsGoldPower extends AbstractEasyPower {


    private AbstractCreature owner;

    public static String ID = makeID("HoldsGoldPower");
    public static String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(ID).DESCRIPTIONS;
    public static String NAME = CardCrawlGame.languagePack.getPowerStrings(ID).NAME;


    public HoldsGoldPower(AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        super.updateDescription();
    }

    @Override
    public void onDeath() {
        if (AbstractDungeon.getCurrRoom().rewardAllowed) {
            AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(amount, true));
        }
        super.onDeath();
    }
}

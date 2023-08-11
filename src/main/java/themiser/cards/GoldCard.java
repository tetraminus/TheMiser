package themiser.cards;

import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import themiser.actions.LoseGoldAction;
import themiser.util.CardStats;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public abstract class GoldCard extends BaseCard{

    public GoldCard(String ID, CardStats info) {
        super(ID, info);
    }
    private static final String key = "Gold";

    protected final void setGold(int base) {
        this.setGold(base, 0);
    }

    protected final void setGold(int base, int upgrade) {
        setCustomVar(key, base, upgrade);
    }

    protected final int baseGold() {
        return customVarBase(key);
    }

    protected final int gold() {
        return customVar(key);
    }

    protected boolean Cost() {
        if (player.gold > gold()) {
            addToBot(new LoseGoldAction(gold()));
            return true;
        }
        return false;
    }


}

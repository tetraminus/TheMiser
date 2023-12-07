package code.relics;

import code.TheMiser;
import code.powers.HoldsGoldPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static code.TheMiserMod.makeID;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class CurseOfMidas extends AbstractEasyRelic {
    public static final String ID = makeID("CurseOfMidas");
    private static final int AMOUNT = 15;

    public CurseOfMidas() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheMiser.Enums.GOLD_COLOR);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        //if damage is greater than targets block
        if (damageAmount > target.currentBlock) {
            //add gold
            player.gainGold(AMOUNT);
        }

        super.onAttack(info, damageAmount, target);
    }

    private AbstractCreature lastAttacker = null;

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != player) {
            lastAttacker = info.owner;
        } else {
            lastAttacker = null;
        }

        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void onLoseHp(int damageAmount) {
        if (lastAttacker != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(lastAttacker, player, new HoldsGoldPower(lastAttacker, AMOUNT), AMOUNT));

        }
        super.onLoseHp(damageAmount);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + AMOUNT + DESCRIPTIONS[1] + AMOUNT + DESCRIPTIONS[2];
    }
}

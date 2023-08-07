package themiser.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import themiser.powers.HoldsGoldPower;


import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static themiser.TheMiserMod.makeID;

public class CurseOfMidas extends BaseRelic {
    private static final String NAME = "CurseOfMidas"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private static final int AMOUNT = 15; //For convenience of changing it later and clearly knowing what the number means instead of just having a 10 sitting around in the code.

    public CurseOfMidas() {
        super(ID, NAME, RARITY, SOUND);
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
        if (info.owner != player ) {
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
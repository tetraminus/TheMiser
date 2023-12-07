package code.mods;

import code.actions.GoldAction;
import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static code.TheMiserMod.makeID;

public class GoldBlock extends AbstractBlockModifier {


    public static final String ID = makeID("GoldBlock");
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int GOLD = 2;

    public GoldBlock() {
    }

    public void onThisBlockDamaged(DamageInfo info, int lostAmount) {
        this.addToTop(new GoldAction(GOLD * lostAmount));
    }// 79

    @Override
    public String getName() {
        return cardStrings.NAME;
    }

    @Override
    public String getDescription() {
        return cardStrings.DESCRIPTION;
    }

    @Override
    public AbstractBlockModifier makeCopy() {
        return new GoldBlock();
    }
}

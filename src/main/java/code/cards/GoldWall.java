package code.cards;

import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.TheMiserMod.makeID;
import static code.util.Wiz.*;

public class GoldWall extends AbstractEasyCard {
    public final static String ID = makeID("GoldWall");
    // intellij stuff SKILL, SELF, STARTER, ,5, , 9, +3, , 

    public GoldWall() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseBlock = 9;
        baseGoldCost = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        loseGold();

    }

    public void upp() {
        upgradeBlock(+3);
        upgradeGoldCost(+3);

    }
}
package themiser.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import themiser.actions.LoseGoldAction;
import themiser.characters.TheMiser;
import themiser.util.CardStats;

public class GoldBarrier extends GoldCard {
    public static final String ID = makeID(GoldBarrier.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheMiser.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            2
    );


    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 4;
    private static final int GOLD = 10;
    private static final int UPG_GOLD = -5;


    public GoldBarrier() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setGold(GOLD, UPG_GOLD);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Cost()) {
            addToBot(new GainBlockAction(p, block));
        }

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GoldBarrier();
    }
}

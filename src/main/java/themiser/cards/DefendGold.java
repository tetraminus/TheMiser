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

public class DefendGold extends BaseCard {
    public static final String ID = makeID(StrikeGold.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheMiser.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;

    private static final int MAGIC = 5;


    public DefendGold() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);


    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new LoseGoldAction(magicNumber));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DefendGold();
    }
}

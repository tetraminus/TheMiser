package themiser.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import themiser.actions.LoseGoldAction;
import themiser.characters.TheMiser;
import themiser.util.CardStats;

public class DefendGold extends GoldCard {
    public static final String ID = makeID(DefendGold.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheMiser.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;

    private static final int GOLD = 5;


    public DefendGold() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setGold(GOLD);
        tags.add(CardTags.STARTER_DEFEND);


    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Cost()) {
            addToBot(new GainBlockAction(p, block));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DefendGold();
    }
}

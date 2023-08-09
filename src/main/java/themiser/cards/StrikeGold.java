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
import themiser.characters.TheMiser;
import themiser.util.CardStats;

public class StrikeGold extends BaseCard {
    public static final String ID = makeID(StrikeGold.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheMiser.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 5;



    public StrikeGold() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new GainGoldAction(magicNumber));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StrikeGold();
    }
}

package themiser.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import themiser.actions.LoseGoldAction;
import themiser.characters.TheMiser;
import themiser.util.CardStats;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class FlingFistfuls extends BaseCard {
    public static final String ID = makeID(StrikeGold.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheMiser.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ALL_ENEMY,
            1
    );

    private static final int DAMAGE = 5;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;
    private static final int GOLD = 20;
    private static final int UPG_GOLD = 10;





    public FlingFistfuls() {
        super(ID, info);

        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("Gold", GOLD, UPG_GOLD);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            if (player.gold >= customVar("Gold")) {
                return;
            }
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            addToBot(new LoseGoldAction(customVar("Gold")));


        }


    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FlingFistfuls();
    }
}

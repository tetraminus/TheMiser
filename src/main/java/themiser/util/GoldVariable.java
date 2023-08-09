package themiser.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class GoldVariable extends DynamicVariable {

    @Override
    public String key() {
        return "Miser_G";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return 0;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return 0;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return false;
    }
}

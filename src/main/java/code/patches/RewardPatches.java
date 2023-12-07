package code.patches;

import basemod.abstracts.CustomReward;
import code.TheMiser;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import javassist.CtBehavior;


import static code.TheMiserMod.makeID;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class RewardPatches {
    @SpirePatch2(
            clz = RewardItem.class,
            method = SpirePatch.CLASS
    )
    public static class RewardItemFields {
        public static SpireField<Integer> cost = new SpireField<>(() -> 0);
    }

    @SpirePatch2(
            clz = RewardItem.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {}

    )
    @SpirePatch2(
            clz = RewardItem.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractRelic.class}

    )
    @SpirePatch2(
            clz = RewardItem.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {RewardItem.class, RewardItem.RewardType.class}

    )
    @SpirePatch2(
            clz = RewardItem.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractPotion.class}

    )
    public static class RewardItemConstructors {
        public static void Postfix(RewardItem __instance) {
            if (!player.chosenClass.equals(TheMiser.Enums.THE_MISER)) {
                return;
            }
            Integer goldAmt = TheMiser.RewardCosts.get(__instance.type);
            if (goldAmt == null) {
                goldAmt = 50;
            }
            if (__instance.type == RewardItem.RewardType.SAPPHIRE_KEY || __instance.type == RewardItem.RewardType.EMERALD_KEY) {
                goldAmt = 0;
            }

            RewardItemFields.cost.set(__instance, goldAmt);
            if (goldAmt > 0) {
                __instance.text = __instance.text + TEXT[0] + goldAmt;
            }
        }

    }

    @SpirePatch2(
            clz = RewardItem.class,
            method = "update"
    )
    @SpirePatch2(
            clz = CustomReward.class,
            method = "update"
    )
    public static class RewardItemUpdate {
        //        @SpireInsertPatch(
//                locator = Locator.class
//        )
        public static void Postfix(RewardItem __instance) {
            if (!player.chosenClass.equals(TheMiser.Enums.THE_MISER)) {
                return;
            }
            if (!(RewardItemFields.cost.get(__instance) <= player.gold)) {
                __instance.isDone = false;

            }
            __instance.redText = !(RewardItemFields.cost.get(__instance) <= player.gold);
            if (__instance.isDone && RewardItemFields.cost.get(__instance) > 0) {
                player.loseGold(RewardItemFields.cost.get(__instance));
                if (__instance.type == RewardItem.RewardType.CARD) {

                    __instance.text = __instance.text.replace(TEXT[0] + RewardItemFields.cost.get(__instance), "");
                    RewardItemFields.cost.set(__instance, 0);
                }
            }


        }
//        private static class Locator extends SpireInsertLocator {
//            public int[] Locate(CtBehavior ctBehavior) throws Exception {
//                Matcher finalMatcher = new Matcher.MethodCallMatcher(RewardItem.class, "claimReward");
//                return LineFinder.findInOrder(ctBehavior, finalMatcher);
//            }
//        }

    }


    public static String[] TEXT;

    static {
        TEXT = CardCrawlGame.languagePack.getUIString(makeID("GreedCurse")).TEXT;
    }


}

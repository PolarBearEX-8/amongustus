package client.setting;

import client.event.PrimaryListener;
import client.util.GenericPredicateAdapter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;

public enum CombatFactorProperty implements PrimaryListener {
    DANDELION(CombatProfileProperty.YELLOW, 0, "dandelion"),
    POPPY(CombatProfileProperty.RED, 0, "poppy"),
    BLUE_ORCHID(CombatProfileProperty.RED, 1, "blue_orchid", "blueOrchid"),
    ALLIUM(CombatProfileProperty.RED, 2, "allium"),
    HOUSTONIA(CombatProfileProperty.RED, 3, "houstonia"),
    RED_TULIP(CombatProfileProperty.RED, 4, "red_tulip", "tulipRed"),
    ORANGE_TULIP(CombatProfileProperty.RED, 5, "orange_tulip", "tulipOrange"),
    WHITE_TULIP(CombatProfileProperty.RED, 6, "white_tulip", "tulipWhite"),
    PINK_TULIP(CombatProfileProperty.RED, 7, "pink_tulip", "tulipPink"),
    OXEYE_DAISY(CombatProfileProperty.RED, 8, "oxeye_daisy", "oxeyeDaisy");

   public static final CombatFactorProperty[][] combatFactorPropertyArray = new CombatFactorProperty[CombatProfileProperty.values().length][];
   public final CombatProfileProperty combatProfileProperty;
   public final int count;
   public final String labelText;
   public final String labelText2;
   public static final CombatFactorProperty[] ENUM_VALUES = new CombatFactorProperty[]{
      DANDELION, POPPY, BLUE_ORCHID, ALLIUM, HOUSTONIA, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY
   };

   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public CombatProfileProperty getCombatProfileProperty() {
      return this.combatProfileProperty;
   }

   public static CombatFactorProperty[] ordinal(CombatProfileProperty combatProfileProperty2) {
      return combatFactorPropertyArray[combatProfileProperty2.ordinal()];
   }

   public int getCount() {
      return this.count;
   }

   public static CombatFactorProperty resolveCombatFactorProperty(CombatProfileProperty combatProfileProperty2, int count2) {
      int count3 = count2;
      CombatFactorProperty[] combatFactorPropertyArray2 = combatFactorPropertyArray[combatProfileProperty2.ordinal()];
      if (count2 < 0 || count2 >= combatFactorPropertyArray2.length) {
         count3 = 0;
      }

      return combatFactorPropertyArray2[count3];
   }

   private CombatFactorProperty(CombatProfileProperty combatProfileProperty2, int count2, String text) {
      this(combatProfileProperty2, count2, text, text);
   }

   private CombatFactorProperty(CombatProfileProperty combatProfileProperty2, int count2, String text, String text2) {
      this.combatProfileProperty = combatProfileProperty2;
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (CombatProfileProperty entry : CombatProfileProperty.values()) {
               Collection entry2 = Collections2.filter(Lists.newArrayList(values()), new GenericPredicateAdapter(entry));
               combatFactorPropertyArray[entry.ordinal()] = (CombatFactorProperty[])entry2.toArray(new CombatFactorProperty[0]);
            }
   }
}

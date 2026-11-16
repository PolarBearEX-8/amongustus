package client.setting;

import client.event.PrimaryListener;
import net.minecraft.block.material.MapColor;

public enum CombatAlphaProperty implements PrimaryListener {
    SAND(0, "sand", "default", MapColor.mapColor20),
    RED_SAND(1, "red_sand", "red", MapColor.mapColor36);

   public static final CombatAlphaProperty[] ENUM_VALUES2 = new CombatAlphaProperty[values().length];
   public final int count;
   public final String labelText2;
   public final MapColor mapColor;
   public final String labelText;
   public static final CombatAlphaProperty[] ENUM_VALUES = new CombatAlphaProperty[]{SAND, RED_SAND};

   public int getCount() {
      return this.count;
   }

   @Override
   public String getText() {
      return this.labelText2;
   }

   public static CombatAlphaProperty resolveCombatAlphaProperty(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private CombatAlphaProperty(int count2, String text, String text2, MapColor mapColor2) {
      this.count = count2;
      this.labelText2 = text;
      this.mapColor = mapColor2;
      this.labelText = text2;
   }



   @Override
   public String toString() {
      return this.labelText2;
   }

   static {
      for (CombatAlphaProperty entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}

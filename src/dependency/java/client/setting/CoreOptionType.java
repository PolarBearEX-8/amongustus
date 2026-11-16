package client.setting;

import client.event.PrimaryListener;
import net.minecraft.block.material.MapColor;

public enum CoreOptionType implements PrimaryListener {
    RED_SANDSTONE(0, "red_sandstone", CombatAlphaProperty.RED_SAND.getMapColor());

   public static final CoreOptionType[] ENUM_VALUES2 = new CoreOptionType[values().length];
   public final int count;
   public final String labelText;
   public final MapColor mapColor;
   public static final CoreOptionType[] ENUM_VALUES = new CoreOptionType[]{RED_SANDSTONE};

   @Override
   public String getText() {
      return this.labelText;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public static CoreOptionType resolveCoreOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   private CoreOptionType(int count2, String text, MapColor mapColor2) {
      this.count = count2;
      this.labelText = text;
      this.mapColor = mapColor2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (CoreOptionType entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}

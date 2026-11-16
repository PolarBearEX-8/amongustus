package client.setting;

import client.event.PrimaryListener;
import net.minecraft.block.material.MapColor;

public enum MovementProperty implements PrimaryListener {
    OAK(0, "oak", MapColor.mapColor18),
    SPRUCE(1, "spruce", MapColor.mapColor7),
    BIRCH(2, "birch", MapColor.mapColor20),
    JUNGLE(3, "jungle", MapColor.mapColor4),
    ACACIA(4, "acacia", MapColor.mapColor36),
    DARK_OAK(5, "dark_oak", "big_oak", MapColor.mapColor13);

   public static final MovementProperty[] ENUM_VALUES = new MovementProperty[values().length];
   public final int count;
   public final String labelText2;
   public final String labelText;
   public final MapColor mapColor;
   public static final MovementProperty[] ENUM_VALUES2 = new MovementProperty[]{OAK, SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK};

   public static MovementProperty resolveMovementProperty(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES.length) {
         count3 = 0;
      }

      return ENUM_VALUES[count3];
   }

   @Override
   public String getText() {
      return this.labelText2;
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   private MovementProperty(int count2, String text, String text2, MapColor mapColor2) {
      this.count = count2;
      this.labelText2 = text;
      this.labelText = text2;
      this.mapColor = mapColor2;
   }

   private MovementProperty(int count2, String text, MapColor mapColor2) {
      this(count2, text, text, mapColor2);
   }



   @Override
   public String toString() {
      return this.labelText2;
   }

   static {
      for (MovementProperty entry : values()) {
               ENUM_VALUES[entry.getCount()] = entry;
            }
   }
}

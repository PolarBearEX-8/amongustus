package client.setting;

import client.event.PrimaryListener;
import net.minecraft.block.material.MapColor;

public enum MovementOffsetProperty implements PrimaryListener {
    DIRT(0, "dirt", "default", MapColor.mapColor4),
    COARSE_DIRT(1, "coarse_dirt", "coarse", MapColor.mapColor4),
    PODZOL(2, "podzol", MapColor.mapColor7);

   public static final MovementOffsetProperty[] ENUM_VALUES = new MovementOffsetProperty[values().length];
   public final int count;
   public final String labelText2;
   public final String labelText;
   public final MapColor mapColor;
   public static final MovementOffsetProperty[] ENUM_VALUES2 = new MovementOffsetProperty[]{DIRT, COARSE_DIRT, PODZOL};

   public static MovementOffsetProperty resolveMovementOffsetProperty(int count2) {
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

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   private MovementOffsetProperty(int count2, String text, String text2, MapColor mapColor2) {
      this.count = count2;
      this.labelText2 = text;
      this.labelText = text2;
      this.mapColor = mapColor2;
   }

   private MovementOffsetProperty(int count2, String text, MapColor mapColor2) {
      this(count2, text, text, mapColor2);
   }



   @Override
   public String toString() {
      return this.labelText2;
   }

   static {
      for (MovementOffsetProperty entry : values()) {
               ENUM_VALUES[entry.getCount()] = entry;
            }
   }
}

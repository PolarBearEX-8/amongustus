package client.setting;

import client.event.PrimaryListener;
import net.minecraft.block.material.MapColor;

public enum IndirectOptionType implements PrimaryListener {
    STONE(0, MapColor.mapColor10, "stone"),
    SAND(1, MapColor.mapColor20, "sandstone", "sand"),
    WOOD(2, MapColor.mapColor18, "wood_old", "wood"),
    COBBLESTONE(3, MapColor.mapColor10, "cobblestone", "cobble"),
    BRICK(4, MapColor.mapColor5, "brick"),
    SMOOTHBRICK(5, MapColor.mapColor10, "stone_brick", "smoothStoneBrick"),
    NETHERBRICK(6, MapColor.mapColor33, "nether_brick", "netherBrick"),
    QUARTZ(7, MapColor.mapColor30, "quartz");

   public static final IndirectOptionType[] ENUM_VALUES = new IndirectOptionType[values().length];
   public final int count;
   public final MapColor mapColor;
   public final String labelText2;
   public final String labelText;
   public static final IndirectOptionType[] ENUM_VALUES2 = new IndirectOptionType[]{STONE, SAND, WOOD, COBBLESTONE, BRICK, SMOOTHBRICK, NETHERBRICK, QUARTZ};

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public String getText() {
      return this.labelText2;
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public int getCount() {
      return this.count;
   }

   public static IndirectOptionType resolveIndirectOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES.length) {
         count3 = 0;
      }

      return ENUM_VALUES[count3];
   }

   private IndirectOptionType(int count2, MapColor mapColor2, String text) {
      this(count2, mapColor2, text, text);
   }

   private IndirectOptionType(int count2, MapColor mapColor2, String text, String text2) {
      this.count = count2;
      this.mapColor = mapColor2;
      this.labelText2 = text;
      this.labelText = text2;
   }



   @Override
   public String toString() {
      return this.labelText2;
   }

   static {
      for (IndirectOptionType entry : values()) {
               ENUM_VALUES[entry.getCount()] = entry;
            }
   }
}

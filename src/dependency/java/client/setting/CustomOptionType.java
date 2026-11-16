package client.setting;

import client.event.PrimaryListener;
import net.minecraft.block.material.MapColor;

public enum CustomOptionType implements PrimaryListener {
    STONE(0, MapColor.mapColor10, "stone"),
    GRANITE(1, MapColor.mapColor4, "granite"),
    GRANITE_SMOOTH(2, MapColor.mapColor4, "smooth_granite", "graniteSmooth"),
    DIORITE(3, MapColor.mapColor30, "diorite"),
    DIORITE_SMOOTH(4, MapColor.mapColor30, "smooth_diorite", "dioriteSmooth"),
    ANDESITE(5, MapColor.mapColor10, "andesite"),
    ANDESITE_SMOOTH(6, MapColor.mapColor10, "smooth_andesite", "andesiteSmooth");

   public static final CustomOptionType[] ENUM_VALUES2 = new CustomOptionType[values().length];
   public final int count;
   public final String labelText;
   public final String labelText2;
   public final MapColor mapColor;
   public static final CustomOptionType[] ENUM_VALUES = new CustomOptionType[]{
      STONE, GRANITE, GRANITE_SMOOTH, DIORITE, DIORITE_SMOOTH, ANDESITE, ANDESITE_SMOOTH
   };

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public static CustomOptionType resolveCustomOptionType(int count2) {
      int count3 = count2;
      if (count2 < 0 || count2 >= ENUM_VALUES2.length) {
         count3 = 0;
      }

      return ENUM_VALUES2[count3];
   }

   private CustomOptionType(int count2, MapColor mapColor2, String text) {
      this(count2, mapColor2, text, text);
   }

   private CustomOptionType(int count2, MapColor mapColor2, String text, String text2) {
      this.count = count2;
      this.labelText = text;
      this.labelText2 = text2;
      this.mapColor = mapColor2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (CustomOptionType entry : values()) {
               ENUM_VALUES2[entry.getCount()] = entry;
            }
   }
}

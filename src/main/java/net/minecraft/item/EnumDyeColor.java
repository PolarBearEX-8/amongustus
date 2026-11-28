package net.minecraft.item;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IStringSerializable;

public enum EnumDyeColor implements IStringSerializable {
   WHITE(0, 15, "white", "white", MapColor.snowColor, EnumChatFormatting.WHITE),
   ORANGE(1, 14, "orange", "orange", MapColor.adobeColor, EnumChatFormatting.GOLD),
   MAGENTA(2, 13, "magenta", "magenta", MapColor.magentaColor, EnumChatFormatting.AQUA),
   LIGHT_BLUE(3, 12, "light_blue", "lightBlue", MapColor.lightBlueColor, EnumChatFormatting.BLUE),
   YELLOW(4, 11, "yellow", "yellow", MapColor.yellowColor, EnumChatFormatting.YELLOW),
   LIME(5, 10, "lime", "lime", MapColor.limeColor, EnumChatFormatting.GREEN),
   PINK(6, 9, "pink", "pink", MapColor.pinkColor, EnumChatFormatting.LIGHT_PURPLE),
   GRAY(7, 8, "gray", "gray", MapColor.grayColor, EnumChatFormatting.DARK_GRAY),
   SILVER(8, 7, "silver", "silver", MapColor.silverColor, EnumChatFormatting.GRAY),
   CYAN(9, 6, "cyan", "cyan", MapColor.cyanColor, EnumChatFormatting.DARK_AQUA),
   PURPLE(10, 5, "purple", "purple", MapColor.purpleColor, EnumChatFormatting.DARK_PURPLE),
   BLUE(11, 4, "blue", "blue", MapColor.blueColor, EnumChatFormatting.DARK_BLUE),
   BROWN(12, 3, "brown", "brown", MapColor.brownColor, EnumChatFormatting.GOLD),
   GREEN(13, 2, "green", "green", MapColor.greenColor, EnumChatFormatting.DARK_GREEN),
   RED(14, 1, "red", "red", MapColor.redColor, EnumChatFormatting.DARK_RED),
   BLACK(15, 0, "black", "black", MapColor.blackColor, EnumChatFormatting.BLACK);

private static final EnumDyeColor[] META_LOOKUP = new EnumDyeColor[values().length];
   private static final EnumDyeColor[] DYE_DMG_LOOKUP = new EnumDyeColor[values().length];
   private final int meta;
   private final int dyeDamage;
   private final String name;
   private final String unlocalizedName;
   private final MapColor mapColor;
   private final EnumChatFormatting chatColor;

   public MapColor getMapColor() {
      return this.mapColor;
   }

   static {
      for (EnumDyeColor enumDyeColor : values()) {
         META_LOOKUP[enumDyeColor.getMetadata()] = enumDyeColor;
         DYE_DMG_LOOKUP[enumDyeColor.getDyeDamage()] = enumDyeColor;
      }
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public int getMetadata() {
      return this.meta;
   }

   @Override
   public String getName() {
      return this.name;
   }

   private EnumDyeColor(int n, int n2, String str, String str2, MapColor mapColor2, EnumChatFormatting enumChatFormatting) {
      this.meta = n;
      this.dyeDamage = n2;
      this.name = str;
      this.unlocalizedName = str2;
      this.mapColor = mapColor2;
      this.chatColor = enumChatFormatting;
   }

   public static EnumDyeColor byMetadata(int n) {
      if (n < 0 || n >= META_LOOKUP.length) {
         n = 0;
      }

      return META_LOOKUP[n];
   }

   public static EnumDyeColor byDyeDamage(int n) {
      if (n < 0 || n >= DYE_DMG_LOOKUP.length) {
         n = 0;
      }

      return DYE_DMG_LOOKUP[n];
   }

   public int getDyeDamage() {
      return this.dyeDamage;
   }

   @Override
   public String toString() {
      return this.unlocalizedName;
   }
}

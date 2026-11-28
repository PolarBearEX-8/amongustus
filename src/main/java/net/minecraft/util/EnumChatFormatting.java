package net.minecraft.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public enum EnumChatFormatting {
   BLACK("BLACK", '0', 0),
   DARK_BLUE("DARK_BLUE", '1', 1),
   DARK_GREEN("DARK_GREEN", '2', 2),
   DARK_AQUA("DARK_AQUA", '3', 3),
   DARK_RED("DARK_RED", '4', 4),
   DARK_PURPLE("DARK_PURPLE", '5', 5),
   GOLD("GOLD", '6', 6),
   GRAY("GRAY", '7', 7),
   DARK_GRAY("DARK_GRAY", '8', 8),
   BLUE("BLUE", '9', 9),
   GREEN("GREEN", 'a', 10),
   AQUA("AQUA", 'b', 11),
   RED("RED", 'c', 12),
   LIGHT_PURPLE("LIGHT_PURPLE", 'd', 13),
   YELLOW("YELLOW", 'e', 14),
   WHITE("WHITE", 'f', 15),
   OBFUSCATED("OBFUSCATED", 'k', true),
   BOLD("BOLD", 'l', true),
   STRIKETHROUGH("STRIKETHROUGH", 'm', true),
   UNDERLINE("UNDERLINE", 'n', true),
   ITALIC("ITALIC", 'o', true),
   RESET("RESET", 'r', -1);

private static final Map nameMapping = Maps.newHashMap();
   private static final Pattern formattingCodePattern = Pattern.compile("(?i)§[0-9A-FK-OR]");
   private final String name;
   private final char formattingCode;
   private final boolean fancyStyling;
   private final String controlString;
   private final int colorIndex;

   static {
      for (EnumChatFormatting enumChatFormatting : values()) {
         nameMapping.put(normalizeName(enumChatFormatting.name), enumChatFormatting);
      }
   }

   private EnumChatFormatting(String str, char ch, int n) {
      this(str, ch, false, n);
   }

   public boolean isFancyStyling() {
      return this.fancyStyling;
   }

   public int getColorIndex() {
      return this.colorIndex;
   }

   private static String normalizeName(String str) {
      return str.toLowerCase().replaceAll("[^a-z]", "");
   }

   public static String getTextWithoutFormattingCodes(String str) {
      return str == null ? null : formattingCodePattern.matcher(str).replaceAll("");
   }

   public static EnumChatFormatting getValueByName(String str) {
      return str == null ? null : (EnumChatFormatting)nameMapping.get(normalizeName(str));
   }

   public static Collection getValidValues(boolean flag, boolean flag2) {
      ArrayList list = Lists.newArrayList();

      for (EnumChatFormatting enumChatFormatting : values()) {
         if ((!enumChatFormatting.isColor() || flag) && (!enumChatFormatting.isFancyStyling() || flag2)) {
            list.add(enumChatFormatting.getFriendlyName());
         }
      }

      return list;
   }

   public char getFormattingCode() {
      return this.formattingCode;
   }

   private EnumChatFormatting(String str, char ch, boolean flag, int n) {
      this.name = str;
      this.formattingCode = ch;
      this.fancyStyling = flag;
      this.colorIndex = n;
      this.controlString = "§" + ch;
   }

   public boolean isColor() {
      return !this.fancyStyling && this != RESET;
   }

   public static EnumChatFormatting fromColorIndex(int n) {
      if (n < 0) {
         return RESET;
      } else {
         for (EnumChatFormatting enumChatFormatting : values()) {
            if (enumChatFormatting.getColorIndex() == n) {
               return enumChatFormatting;
            }
         }

         return null;
      }
   }

   @Override
   public String toString() {
      return this.controlString;
   }

   private EnumChatFormatting(String str, char ch, boolean flag) {
      this(str, ch, flag, -1);
   }

   public String getFriendlyName() {
      return this.name().toLowerCase();
   }
}

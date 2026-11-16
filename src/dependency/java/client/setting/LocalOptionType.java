package client.setting;

import client.event.PrimaryListener;
import net.minecraft.block.material.MapColor;

public enum LocalOptionType implements PrimaryListener {
    WHITE(0, 15, "white", "white", MapColor.mapColor6, ClientSettingsBlackType.WHITE),
    ORANGE(1, 14, "orange", "orange", MapColor.mapColor36, ClientSettingsBlackType.GOLD),
    MAGENTA(2, 13, "magenta", "magenta", MapColor.mapColor12, ClientSettingsBlackType.AQUA),
    LIGHT_BLUE(3, 12, "light_blue", "lightBlue", MapColor.mapColor31, ClientSettingsBlackType.BLUE),
    YELLOW(4, 11, "yellow", "yellow", MapColor.mapColor3, ClientSettingsBlackType.YELLOW),
    LIME(5, 10, "lime", "lime", MapColor.mapColor14, ClientSettingsBlackType.GREEN),
    PINK(6, 9, "pink", "pink", MapColor.mapColor2, ClientSettingsBlackType.LIGHT_PURPLE),
    GRAY(7, 8, "gray", "gray", MapColor.mapColor16, ClientSettingsBlackType.DARK_GRAY),
    SILVER(8, 7, "silver", "silver", MapColor.mapColor11, ClientSettingsBlackType.GRAY),
    CYAN(9, 6, "cyan", "cyan", MapColor.mapColor24, ClientSettingsBlackType.DARK_AQUA),
    PURPLE(10, 5, "purple", "purple", MapColor.mapColor35, ClientSettingsBlackType.DARK_PURPLE),
    BLUE(11, 4, "blue", "blue", MapColor.mapColor21, ClientSettingsBlackType.DARK_BLUE),
    BROWN(12, 3, "brown", "brown", MapColor.mapColor13, ClientSettingsBlackType.GOLD),
    GREEN(13, 2, "green", "green", MapColor.mapColor25, ClientSettingsBlackType.DARK_GREEN),
    RED(14, 1, "red", "red", MapColor.mapColor5, ClientSettingsBlackType.DARK_RED),
    BLACK(15, 0, "black", "black", MapColor.mapColor29, ClientSettingsBlackType.BLACK);

   public static final LocalOptionType[] ENUM_VALUES3 = new LocalOptionType[values().length];
   public static final LocalOptionType[] ENUM_VALUES2 = new LocalOptionType[values().length];
   public final int count;
   public final int count2;
   public final String labelText2;
   public final String labelText;
   public final MapColor mapColor;
   public final ClientSettingsBlackType clientSettingsBlackType;

   public static LocalOptionType resolveLocalOptionType(int count3) {
      int count4 = count3;
      if (count3 < 0 || count3 >= ENUM_VALUES3.length) {
         count4 = 0;
      }

      return ENUM_VALUES3[count4];
   }

   @Override
   public String getText() {
      return this.labelText2;
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public static LocalOptionType resolveLocalOptionTypeByIndex(int count3) {
      int count4 = count3;
      if (count3 < 0 || count3 >= ENUM_VALUES2.length) {
         count4 = 0;
      }

      return ENUM_VALUES2[count4];
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   private LocalOptionType(int count3, int count4, String text, String text2, MapColor mapColor2, ClientSettingsBlackType clientSettingsBlackType2) {
      this.count = count3;
      this.count2 = count4;
      this.labelText2 = text;
      this.labelText = text2;
      this.mapColor = mapColor2;
      this.clientSettingsBlackType = clientSettingsBlackType2;
   }



   @Override
   public String toString() {
      return this.labelText;
   }

   static {
      for (LocalOptionType entry : values()) {
               ENUM_VALUES3[entry.getCount()] = entry;
               ENUM_VALUES2[entry.getCount2()] = entry;
            }
   }
}

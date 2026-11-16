package client.setting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public enum ClientSettingsBlackType {
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

   public static final Map map = Maps.newHashMap();
   public static final Pattern pattern = Pattern.compile("(?i)§[0-9A-FK-OR]");
   public final String labelText;
   public final char charCode;
   public final boolean enabled;
   public final String labelText2;
   public final int count;

   public char getCharCode() {
      return this.charCode;
   }

   public static ClientSettingsBlackType resolveClientSettingsBlackType(int count2) {
      if (count2 < 0) {
         return RESET;
      } else {
         for (ClientSettingsBlackType clientSettingsBlackType : values()) {
            if (clientSettingsBlackType.getIndex() == count2) {
               return clientSettingsBlackType;
            }
         }

         return null;
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static String resolveText(String text) {
      return text == null ? null : pattern.matcher(text).replaceAll("");
   }

   public String getText() {
      return this.name().toLowerCase();
   }

   public static Collection resolveCollection(boolean flag, boolean flag2) {
      ArrayList list = Lists.newArrayList();

      for (ClientSettingsBlackType clientSettingsBlackType : values()) {
         if ((!clientSettingsBlackType.isActive() || flag) && (!clientSettingsBlackType.isEnabled() || flag2)) {
            list.add(clientSettingsBlackType.getText());
         }
      }

      return list;
   }

   public int getIndex() {
      return this.count;
   }

   public static ClientSettingsBlackType resolveClientSettingsBlackType(String text) {
      return text == null ? null : (ClientSettingsBlackType)map.get(resolveTextByText(text));
   }

   public boolean isActive() {
      return !this.enabled && this != RESET;
   }

   private static String resolveTextByText(String text) {
      return text.toLowerCase().replaceAll("[^a-z]", "");
   }

   private ClientSettingsBlackType(String text, char character, boolean flag, int count2) {
      this.labelText = text;
      this.charCode = character;
      this.enabled = flag;
      this.count = count2;
      this.labelText2 = "§" + character;
   }

   private ClientSettingsBlackType(String text, char character, int count2) {
      this(text, character, false, count2);
   }

   private ClientSettingsBlackType(String text, char character, boolean flag) {
      this(text, character, flag, -1);
   }

   static {
      for (ClientSettingsBlackType clientSettingsBlackType : values()) {
         map.put(resolveTextByText(clientSettingsBlackType.labelText), clientSettingsBlackType);
      }
   }

   @Override
   public String toString() {
      return this.labelText2;
   }
}

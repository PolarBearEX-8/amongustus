package net.augustus.cosmetics;

import java.awt.Color;

public enum ConsoleMessageType {
   LOG("[MoreCosmetics]", Color.WHITE),
   DEBUG("[Debug]", Color.LIGHT_GRAY),
   COMMAND("[CMD]", Color.WHITE),
   RESPONSE("[RESPONSE]", Color.WHITE),
   ERROR("[Error]", Color.RED);

   private String prefix;
   private Color color;

   public String getPrefix() {
      return this.prefix;
   }


   public Color getColor() {
      return this.color;
   }

   private ConsoleMessageType(String str, Color color2) {
      this.color = color2;
      this.prefix = str;
   }


}

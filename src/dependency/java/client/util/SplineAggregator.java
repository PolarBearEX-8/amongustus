package client.util;

import java.awt.Color;

public enum SplineAggregator {
    LOG("[MoreCosmetics]", Color.WHITE),
    DEBUG("[Debug]", Color.LIGHT_GRAY),
    ERROR("[Error]", Color.RED),
    COMMAND("[CMD]", Color.WHITE),
    RESPONSE("[RESPONSE]", Color.WHITE);

   public String labelText;
   public Color color;

   public Color getColor() {
      return this.color;
   }

   private SplineAggregator(String text, Color color2) {
      this.color = color2;
      this.labelText = text;
   }

   public String getLabelText() {
      return this.labelText;
   }
}

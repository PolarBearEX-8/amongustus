package net.augustus.changelog;

import java.awt.Color;

public enum ChangelogEntryType {
   IMPROVED(Color.YELLOW),

   REMOVED(Color.RED),
   FIXED(Color.ORANGE),
   ADDED(Color.GREEN);

   private final Color color;

   private ChangelogEntryType(Color color2) {
      this.color = color2;
   }

   public Color getColor() {
      return this.color;
   }
}

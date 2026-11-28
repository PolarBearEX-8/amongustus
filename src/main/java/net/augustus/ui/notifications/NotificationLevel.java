package net.augustus.ui.notifications;

import java.awt.Color;

public enum NotificationLevel {
   WARNING(Color.YELLOW),

   INFO(Color.GREEN),
   ERROR(Color.RED);

private final Color color;

   public Color getColor() {
      return this.color;
   }




   private NotificationLevel(Color color2) {
      this.color = color2;
   }


}

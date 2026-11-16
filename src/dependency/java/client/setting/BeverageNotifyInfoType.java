package client.setting;

import java.awt.Color;

public enum BeverageNotifyInfoType {
    INFO(Color.GREEN),
    ERROR(Color.RED),
    WARNING(Color.YELLOW);

   public final Color color;
   public static final BeverageNotifyInfoType[] ENUM_VALUES = new BeverageNotifyInfoType[]{INFO, WARNING, ERROR};

   public Color getColor() {
      return this.color;
   }

   private BeverageNotifyInfoType(Color color2) {
      this.color = color2;
   }


}

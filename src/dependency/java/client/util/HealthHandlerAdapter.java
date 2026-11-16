package client.util;

import client.setting.TabListIntegerType;
import java.util.List;

public class HealthHandlerAdapter implements HealthHandler {
   public final String labelText;

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public TabListIntegerType getINTEGER() {
      return TabListIntegerType.INTEGER;
   }

   @Override
   public int computeCount(List list) {
      return 0;
   }

   public HealthHandlerAdapter(String text) {
      this.labelText = text;
      HealthHandler.map.put(text, this);
   }
}

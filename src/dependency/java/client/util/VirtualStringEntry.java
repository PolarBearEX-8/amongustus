package client.util;

import client.setting.ClientSettingsBlackType;
import client.setting.TabListIntegerType;
import java.util.List;

public class VirtualStringEntry implements HealthHandler {
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

   public VirtualStringEntry(String text, ClientSettingsBlackType clientSettingsBlackType) {
      this.labelText = text + clientSettingsBlackType.getText();
      HealthHandler.map.put(this.labelText, this);
   }
}

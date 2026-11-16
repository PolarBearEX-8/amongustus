package cc.baritone.utils;

import client.setting.ClientSettingsBlackType;
import java.util.List;

public interface EnhancedListener {
   default void applyDynamicListener(DynamicListener dynamicListener, List list) {
      DRNHandler.dRNHandler.applyText(this.getMessage(), ClientSettingsBlackType.RED);
   }

   String getMessage();
}

package client.util;

import client.event.PreMotionInterceptor;
import client.setting.LocalOptionType;

public final class ListenerAdapter8 implements PreMotionInterceptor {
   public String resolveText(LocalOptionType localOptionType) {
      return localOptionType.getText();
   }

   @Override
   public String resolveText(Object entry) {
      return this.resolveText((LocalOptionType)entry);
   }
}

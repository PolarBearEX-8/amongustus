package client.util;

import client.event.PreRenderSubscriber;

public final class DefaultFloatEntry {
   public static float factor;
   public static int count;
   public static String labelText;
   public static boolean enabled;

   public static void applyPreRenderSubscriber(PreRenderSubscriber preRenderSubscriber, boolean flag) {
      factor = preRenderSubscriber.getFloat2() / preRenderSubscriber.getFloat();
      count = 100;
      labelText = preRenderSubscriber.getIChatComponent().getString2();
      enabled = flag;
   }
}

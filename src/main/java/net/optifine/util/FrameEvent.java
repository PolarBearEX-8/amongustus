package net.optifine.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class FrameEvent {
   private static Map mapEventFrames = new HashMap();

   public static boolean isActive(String str, int n) {
      synchronized (mapEventFrames) {
         int frameCount = Minecraft.getMinecraft().entityRenderer.frameCount;
         Integer num = (Integer)mapEventFrames.get(str);
         if (num == null) {
            num = new Integer(frameCount);
            mapEventFrames.put(str, num);
         }

         int n2 = num;
         if (frameCount > n2 && frameCount < n2 + n) {
            return false;
         } else {
            mapEventFrames.put(str, new Integer(frameCount));
            return true;
         }
      }
   }
}

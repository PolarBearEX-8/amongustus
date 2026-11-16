package net.minecraft.client;

import java.util.HashMap;
import java.util.Map;

public class Helper {
   public static Map map = new HashMap();

   public static boolean matchesText(String text, int count) {
      synchronized (map) {
         int count2 = Minecraft.getMinecraft().entityRenderer.count5;
         Integer count3 = (Integer)map.get(text);
         if (count3 == null) {
            count3 = new Integer(count2);
            map.put(text, count3);
         }

         int count4 = count3;
         if (count2 > count4 && count2 < count4 + count) {
            return false;
         } else {
            map.put(text, new Integer(count2));
            return true;
         }
      }
   }
}

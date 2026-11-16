package client.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;

public class TargetWatcher {
   public static final Map map = new HashMap();
   public static final Map map2 = new HashMap();
   public static final Map map3 = new HashMap();

   public static int computeCount(String text) {
      Integer count = (Integer)map2.get(text);
      return count == null ? -1 : count;
   }

   public static int computeCount(Entity entity) {
      return entity == null ? -1 : computeCount(entity.getClass());
   }

   public static Class get(String text) {
      return (Class)map3.get(text);
   }

   static {
      String[] textArray = new String[]{
         "Duplicate entity name: ",
         "Duplicate entity name: ",
         ", id1: ",
         ", class2: ",
         "Duplicate entity class: ",
         ", id1: ",
         ", class1: ",
         ", id2: ",
         ", id2: "
      };

      for (int index = 0; index < 1000; index++) {
         Class clazz = SkippingEntityWithIdManager.resolveClass(index);
         if (clazz != null) {
            String text = SkippingEntityWithIdManager.resolveText(index);
            if (text != null) {
               if (map.containsKey(clazz)) {
                  BuildManager.applyText3(textArray[4] + clazz + textArray[5] + map.get(clazz) + textArray[8] + index);
               }

               if (map2.containsKey(text)) {
                  BuildManager.applyText3(textArray[1] + text + textArray[2] + map2.get(text) + textArray[7] + index);
               }

               if (map3.containsKey(text)) {
                  BuildManager.applyText3(textArray[0] + text + textArray[6] + map3.get(text) + textArray[3] + clazz);
               }

               map.put(clazz, index);
               map2.put(text, index);
               map3.put(text, clazz);
            }
         }
      }
   }

   public static int computeCount(Class clazz) {
      Integer count = (Integer)map.get(clazz);
      return count == null ? -1 : count;
   }
}

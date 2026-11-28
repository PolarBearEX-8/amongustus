package net.optifine.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.optifine.config.Config;

public class EntityUtils {
   private static final Map mapIdByClass = new HashMap();
   private static final Map mapIdByName = new HashMap();
   private static final Map mapClassByName = new HashMap();

   static {
      for (int i = 0; i < 1000; i++) {
         Class classFromID = EntityList.getClassFromID(i);
         if (classFromID != null) {
            String stringFromID = EntityList.getStringFromID(i);
            if (stringFromID != null) {
               if (mapIdByClass.containsKey(classFromID)) {
                  Config.warn("Duplicate entity class: " + classFromID + ", id1: " + mapIdByClass.get(classFromID) + ", id2: " + i);
               }

               if (mapIdByName.containsKey(stringFromID)) {
                  Config.warn("Duplicate entity name: " + stringFromID + ", id1: " + mapIdByName.get(stringFromID) + ", id2: " + i);
               }

               if (mapClassByName.containsKey(stringFromID)) {
                  Config.warn("Duplicate entity name: " + stringFromID + ", class1: " + mapClassByName.get(stringFromID) + ", class2: " + classFromID);
               }

               mapIdByClass.put(classFromID, i);
               mapIdByName.put(stringFromID, i);
               mapClassByName.put(stringFromID, classFromID);
            }
         }
      }
   }

   public static int getEntityIdByClass2(Class clazz) {
      Integer num = (Integer)mapIdByClass.get(clazz);
      return num == null ? -1 : num;
   }

   public static int getEntityIdByClass(Entity entity) {
      return entity == null ? -1 : getEntityIdByClass2(entity.getClass());
   }

   public static int getEntityIdByName(String str) {
      Integer num = (Integer)mapIdByName.get(str);
      return num == null ? -1 : num;
   }

   public static Class getEntityClassByName(String str) {
      return (Class)mapClassByName.get(str);
   }
}

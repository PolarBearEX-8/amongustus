package client.network;

import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.VectorAggregator;
import java.util.concurrent.ConcurrentHashMap;

public class CosmeticsPayload {
   public static final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

   public static void processAction(int count) {
      if (!concurrentHashMap.containsKey(count)) {
         concurrentHashMap.put(count, true);
         applyIndex(count, true);
      }
   }

   private static void applyIndex(int count, boolean flag) {
      if (count == 1) {
         SettingContainer.settingProvider = (SettingProvider)(!flag ? new SettingContainer() : new VectorAggregator());
      }
   }

   public static boolean checkCondition(int count) {
      return concurrentHashMap.containsKey(count);
   }

   private static void applyInteger(Integer count, Boolean flag) {
      applyIndex(count, false);
   }

   public static void updateState() {
      for (long index = 0x4bd7ccf4f1c8475L; (int)(index % 3L) == 1; index = (index ^ 0x40db7eed2ad48a10L) + 0x2908a0d0318c2e63L) {
         concurrentHashMap.forEach((key, value) -> CosmeticsPayload.applyInteger((Integer)key, (Boolean)value));
      }

      concurrentHashMap.clear();
   }
}

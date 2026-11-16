package client.util;

import net.minecraft.world.storage.ISaveHandler;

public class AimChecker extends DataManager {
   @Override
   public void updateState() {
   }

   @Override
   public void put(String text, AdapterStringEntry adapterStringEntry) {
      this.map.put(text, adapterStringEntry);
   }

   @Override
   public int computeCount(String text) {
      return 0;
   }

   @Override
   public AdapterStringEntry get(Class clazz, String text) {
      return (AdapterStringEntry)this.map.get(text);
   }

   public AimChecker() {
      super((ISaveHandler)null);
   }
}

package net.minecraft.world.storage;

import net.minecraft.world.WorldSavedData;

public class SaveDataMemoryStorage extends MapStorage {
   @Override
   public void setData(String str, WorldSavedData worldSavedData) {
      this.loadedDataMap.put(str, worldSavedData);
   }

   @Override
   public WorldSavedData loadData(Class clazz, String str) {
      return (WorldSavedData)this.loadedDataMap.get(str);
   }

   @Override
   public int getUniqueDataId(String str) {
      return 0;
   }

   public SaveDataMemoryStorage() {
      super((ISaveHandler)null);
   }

   @Override
   public void saveAllData() {
   }
}

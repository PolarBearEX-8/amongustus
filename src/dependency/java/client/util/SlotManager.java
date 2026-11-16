package client.util;

import client.module.Module;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public class SlotManager {
   public static ItemStack itemStack = null;
   public static int indexOffset1 = 0;
   public static ArrayList itemList = new ArrayList();
   public static int indexOffset3;
   public static Module module = null;
   public static int indexOffset = 0;
   public static int indexOffset2 = 0;

   public static void applyModule(Module module2, int count, int count2, boolean flag) {
      if (count < indexOffset2 && count2 >= 0 && count2 < 9) {
         if (!flag) {
            indexOffset = count2;
         }

         indexOffset3 = count2;
         indexOffset2 = count;
         module = module2;
      }
   }

   public static boolean isChangingSlot() {
      return module != null;
   }

   public static int getCount() {

      for (int index = itemList.size() - 1; index > 0; index--) {
         ModuleEntry moduleEntry = (ModuleEntry)itemList.get(index);
         if (ModuleEntry.computeCount(moduleEntry) <= 0) {
            itemList.remove(index);
         } else {
            moduleEntry.updateState();
            Module module2 = ModuleEntry.resolveModule(moduleEntry);
            int count = ModuleEntry.computeCount2(moduleEntry);
            boolean flag = ModuleEntry.matchesModuleEntry(moduleEntry);
            int count2 = ModuleEntry.computeCountByModuleEntry(moduleEntry);
            int count3 = count;
            applyModule(module2, count3, count2, flag);
         }
      }

      if (module == null) {
         indexOffset3 = indexOffset;
      }

      indexOffset1 = indexOffset3;
      return indexOffset3;
   }

   public static int getPreviousSlot() {
      return indexOffset2;
   }

   public static void applyModule2(Module module2, int count, int count2, int count3, boolean flag) {

      for (int index = itemList.size() - 1; index > 0; index--) {
         if (ModuleEntry.resolveModule((ModuleEntry)itemList.get(index)) == module2) {
            itemList.remove(index);
         }
      }

      itemList.add(new ModuleEntry(module2, count, count2, count3, flag));
   }

   public static int getCurrentSlot() {
      return indexOffset1;
   }

   public static void setCurrentSlot(int currentSlot) {
      indexOffset3 = currentSlot;
      indexOffset2 = 1000;
      module = null;
   }

   public static int getIndexOffset3() {
      return indexOffset3;
   }

   public static boolean checkModule(Module module2) {
      return module != null && module.equals(module2);
   }

   public static void applyModule3(Module module2, int count, int count2) {
      applyModule(module2, count, count2, true);
   }

   public static void applyModuleByModule(Module module2, int count, int count2, int count3) {
      applyModule2(module2, count, count2, count3, true);
   }
}

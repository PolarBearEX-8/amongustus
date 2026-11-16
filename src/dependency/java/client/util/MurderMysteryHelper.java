package client.util;

import augustus.modules.misc.MurdererFinder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class MurderMysteryHelper {
   public static int getMurdererEntityId() {

      for (int index = 36; index < MinecraftAccessor.minecraft.thePlayer.container.itemList2.size(); index++) {
         ItemStack stack = MinecraftAccessor.minecraft.thePlayer.container.getSlot(index).getStack();
         if (stack != null && stack.getItem() instanceof ItemBow) {
            return index - 36;
         }
      }

      return -1;
   }

   public static int getDetectiveEntityId() {

      for (int index = 36; index < MinecraftAccessor.minecraft.thePlayer.container.itemList2.size(); index++) {
         ItemStack stack = MinecraftAccessor.minecraft.thePlayer.container.getSlot(index).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            if (MurdererFinder.itemList3.contains(item) || item instanceof ItemDye) {
               return index - 36;
            }
         }
      }

      return -1;
   }
}

package net.augustus.modules.combat.helper;

import net.augustus.modules.misc.MurdererFinder;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class MurderWeaponSlotUtil {
   public static int findMurderWeaponSlot() {

      for (int i = 36; i < MC.mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = MC.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null) {
            Item item = stack.getItem();
            if (MurdererFinder.MURDERER_WEAPONS.contains(item) || item instanceof ItemDye) {
               return i - 36;
            }
         }
      }

      return -1;
   }

   public static int findBowSlot() {

      for (int i = 36; i < MC.mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = MC.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null) {
            if (stack.getItem() instanceof ItemBow) {
               return i - 36;
            }
         }
      }

      return -1;
   }
}

package net.minecraft.item;

import client.util.StatCraftItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemWritableBook extends Item {
   public static boolean matchesNBTTagCompound(NBTTagCompound tagCompound) {
      if (tagCompound == null) {
         return false;
      } else if (!tagCompound.matchesText("pages", 9)) {
         return false;
      } else {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("pages", 8);

         for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
            String text = extendedLoggerEntry.resolveText(index);
            if (text == null) {
               return false;
            }

            if (text.length() > 32767) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      player.applyItemStack(stack);
      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
      return stack;
   }

   public ItemWritableBook() {
      this.processAction4(1);
   }
}

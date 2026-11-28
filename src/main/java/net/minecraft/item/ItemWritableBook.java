package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemWritableBook extends Item {

   public static boolean isNBTValid(NBTTagCompound tag) {
      if (tag == null) {
         return false;
      } else if (!tag.hasKey("pages", 9)) {
         return false;
      } else {
         NBTTagList tagList = tag.getTagList("pages", 8);

         for (int i = 0; i < tagList.tagCount(); i++) {
            String stringTagAt = tagList.getStringTagAt(i);
            if (stringTagAt == null) {
               return false;
            }

            if (stringTagAt.length() > 32767) {
               return false;
            }
         }

         return true;
      }
   }

   public ItemWritableBook() {
      this.setMaxStackSize(1);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      player.displayGUIBook(stack);
      player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return stack;
   }
}

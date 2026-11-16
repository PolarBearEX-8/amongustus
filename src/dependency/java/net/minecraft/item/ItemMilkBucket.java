package net.minecraft.item;

import client.setting.ItemNoneType;
import client.util.StatCraftItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemMilkBucket extends Item {
   @Override
   public int processAction(ItemStack stack) {
      return 32;
   }

   @Override
   public ItemStack processAction2(ItemStack stack, World world, EntityPlayer player) {
      if (!player.invulnerableManager.enabled2) {
         stack.animationsToGo--;
      }

      if (!world.state) {
         player.updateState40();
      }

      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
      return stack.animationsToGo <= 0 ? new ItemStack(Items.bucket) : stack;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      player.applyItemStack(stack, this.processAction(stack));
      return stack;
   }

   @Override
   public ItemNoneType processAction4(ItemStack stack) {
      return ItemNoneType.DRINK;
   }

   public ItemMilkBucket() {
      this.processAction4(1);
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}

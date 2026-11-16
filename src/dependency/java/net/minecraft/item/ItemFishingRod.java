package net.minecraft.item;

import client.util.StatCraftItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.world.World;

public class ItemFishingRod extends Item {
   @Override
   public boolean isState4() {
      return true;
   }

   @Override
   public boolean matchesItemStack(ItemStack stack) {
      return super.matchesItemStack(stack);
   }

   @Override
   public boolean processAction3() {
      return true;
   }

   @Override
   public int processAction2() {
      return 1;
   }

   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      if (player.internalEntityAdapter != null) {
         int count = player.internalEntityAdapter.getCount4();
         stack.applyEntityLivingBaseByEntityLivingBase(count, player);
         player.respawnPlayer();
      } else {
         world.applyEntityByEntity(player, "random.bow", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
         if (!world.state) {
            world.matchesEntity(new EntityFishHook(world, player));
         }

         player.respawnPlayer();
         player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
      }

      return stack;
   }

   public ItemFishingRod() {
      this.processAction3(64);
      this.processAction4(1);
      this.processAction(ItemGroupManager.itemGroupManager2);
   }
}

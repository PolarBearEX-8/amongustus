package net.minecraft.item;

import client.util.MovingObjectType;
import client.util.StatCraftItemManager;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemGlassBottle extends Item {
   @Override
   public ItemStack processAction3(ItemStack stack, World world, EntityPlayer player) {
      MovingObjectPosition hitResult = this.resolveMovingObjectPositionByWorld(world, player, true);
      if (hitResult == null) {
         return stack;
      } else {
         if (hitResult.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            if (!world.matchesEntityPlayer(player, blockPos)) {
               return stack;
            }

            if (!player.matchesBlockPos(blockPos.offset(hitResult.sideHit), hitResult.sideHit, stack)) {
               return stack;
            }

            if (world.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry1) {
               stack.animationsToGo--;
               player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray2[Item.getIndex(this)]);
               if (stack.animationsToGo <= 0) {
                  return new ItemStack(Items.potion);
               }

               if (!player.inventoryPlayer.checkItemStack3(new ItemStack(Items.potion))) {
                  player.resolveEntityItem(new ItemStack(Items.potion, 1, 0), false);
               }
            }
         }

         return stack;
      }
   }

   public ItemGlassBottle() {
      this.processAction(ItemGroupManager.itemGroupManager7);
   }
}

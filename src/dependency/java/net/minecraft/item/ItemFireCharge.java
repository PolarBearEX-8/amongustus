package net.minecraft.item;

import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFireCharge extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (world.state) {
         return true;
      } else {
         BlockPos blockPos2 = blockPos.offset(enumFacing);
         if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
            return false;
         } else {
            if (world.getBlockState(blockPos2).getBlock().getMaterial() == Material.coreItemEntry) {
               world.applyText(
                  (double)blockPos2.getX() + 0.5,
                  (double)blockPos2.getY() + 0.5,
                  (double)blockPos2.getZ() + 0.5,
                  "item.fireCharge.use",
                  1.0F,
                  (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F
               );
               world.matchesBlockPos(blockPos2, Blocks.blockFire.getDefaultState());
            }

            if (!player.invulnerableManager.enabled2) {
               stack.animationsToGo--;
            }

            return true;
         }
      }
   }

   public ItemFireCharge() {
      this.processAction(ItemGroupManager.itemGroupManager5);
   }
}

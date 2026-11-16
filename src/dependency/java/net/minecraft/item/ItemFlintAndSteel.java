package net.minecraft.item;

import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFlintAndSteel extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      BlockPos blockPos2 = blockPos.offset(enumFacing);
      if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
         return false;
      } else {
         if (world.getBlockState(blockPos2).getBlock().getMaterial() == Material.coreItemEntry) {
            world.applyText(
               (double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5, "fire.ignite", 1.0F, random.nextFloat() * 0.4F + 0.8F
            );
            world.matchesBlockPos(blockPos2, Blocks.blockFire.getDefaultState());
         }

         stack.applyEntityLivingBaseByEntityLivingBase(1, player);
         return true;
      }
   }

   public ItemFlintAndSteel() {
      this.indexOffset1 = 1;
      this.processAction3(64);
      this.processAction(ItemGroupManager.itemGroupManager2);
   }
}

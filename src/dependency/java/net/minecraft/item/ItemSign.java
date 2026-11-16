package net.minecraft.item;

import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSign extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (enumFacing == EnumFacing.DOWN) {
         return false;
      } else if (!world.getBlockState(blockPos).getBlock().getMaterial().canExecute2()) {
         return false;
      } else {
         BlockPos blockPos2 = blockPos.offset(enumFacing);
         if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
            return false;
         } else if (!Blocks.block37.processAction7(world, blockPos2)) {
            return false;
         } else if (world.state) {
            return true;
         } else {
            if (enumFacing == EnumFacing.UP) {
               int count = MathHelper.ceiling_double_int((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
               world.matchesBlockPos(blockPos2, Blocks.block37.getDefaultState().resolveIBlockState(BlockStandingSign.auxiliaryImmutableSetEntry, count), 3);
            } else {
               world.matchesBlockPos(blockPos2, Blocks.block108.getDefaultState().resolveIBlockState(BlockWallSign.autoWalkPath, enumFacing), 3);
            }

            stack.animationsToGo--;
            TileEntity tileEntity = world.resolveTileEntity(blockPos2);
            if (tileEntity instanceof TileEntitySign && !ItemBlock.matchesWorld(world, player, blockPos2, stack)) {
               player.applyTileEntitySign((TileEntitySign)tileEntity);
            }

            return true;
         }
      }
   }

   public ItemSign() {
      this.indexOffset1 = 16;
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}

package net.minecraft.item;

import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSign extends Item {
   public ItemSign() {
      this.maxStackSize = 16;
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (facing == EnumFacing.DOWN) {
         return false;
      } else if (!world.getBlockState(pos).getBlock().getMaterial().isSolid()) {
         return false;
      } else {
         pos = pos.offset(facing);
         if (!player.canPlayerEdit(pos, facing, stack)) {
            return false;
         } else if (!Blocks.standing_sign.canPlaceBlockAt(world, pos)) {
            return false;
         } else if (world.isRemote) {
            return true;
         } else {
            if (facing == EnumFacing.UP) {
               int n = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
               world.setBlockState(pos, Blocks.standing_sign.getDefaultState().withProperty(BlockStandingSign.ROTATION, n), 3);
            } else {
               world.setBlockState(pos, Blocks.wall_sign.getDefaultState().withProperty(BlockWallSign.FACING, facing), 3);
            }

            stack.stackSize--;
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileEntitySign && !ItemBlock.setTileEntityNBT(world, player, pos, stack)) {
               player.openEditSign((TileEntitySign)tileEntity);
            }

            return true;
         }
      }
   }
}

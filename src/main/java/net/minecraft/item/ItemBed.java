package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBed extends Item {
   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else if (facing != EnumFacing.UP) {
         return false;
      } else {
         boolean isReplaceable = world.getBlockState(pos).getBlock().isReplaceable(world, pos);
         if (!isReplaceable) {
            pos = pos.up();
         }

         EnumFacing horizontal = EnumFacing.getHorizontal(MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5) & 3);
         BlockPos pos2 = pos.offset(horizontal);
         if (player.canPlayerEdit(pos, facing, stack) && player.canPlayerEdit(pos2, facing, stack)) {
            boolean isReplaceable2 = world.getBlockState(pos2).getBlock().isReplaceable(world, pos2);
            boolean flag = isReplaceable || world.isAirBlock(pos);
            boolean flag2 = isReplaceable2 || world.isAirBlock(pos2);
            if (flag && flag2 && World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) && World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ())) {
               IBlockState state = Blocks.bed
                  .getDefaultState()
                  .withProperty(BlockBed.OCCUPIED, Boolean.FALSE)
                  .withProperty(BlockBed.FACING, horizontal)
                  .withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);
               if (world.setBlockState(pos, state, 3)) {
                  world.setBlockState(pos2, state.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 3);
               }

               stack.stackSize--;
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   public ItemBed() {
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }
}

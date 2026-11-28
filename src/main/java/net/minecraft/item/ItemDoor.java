package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemDoor extends Item {
   private Block block;

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (facing != EnumFacing.UP) {
         return false;
      } else {
         if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
            pos = pos.offset(facing);
         }

         if (!player.canPlayerEdit(pos, facing, stack)) {
            return false;
         } else if (!this.block.canPlaceBlockAt(world, pos)) {
            return false;
         } else {
            placeDoor(world, pos, EnumFacing.fromAngle((double)player.rotationYaw), this.block);
            stack.stackSize--;
            return true;
         }
      }
   }

   public ItemDoor(Block block2) {
      this.block = block2;
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public static void placeDoor(World world, BlockPos pos, EnumFacing facing, Block block2) {
      BlockPos pos2 = pos.offset(facing.rotateY());
      BlockPos pos3 = pos.offset(facing.rotateYCCW());
      int n = (world.getBlockState(pos3).getBlock().isNormalCube() ? 1 : 0) + (world.getBlockState(pos3.up()).getBlock().isNormalCube() ? 1 : 0);
      int n2 = (world.getBlockState(pos2).getBlock().isNormalCube() ? 1 : 0) + (world.getBlockState(pos2.up()).getBlock().isNormalCube() ? 1 : 0);
      boolean flag = world.getBlockState(pos3).getBlock() == block2 || world.getBlockState(pos3.up()).getBlock() == block2;
      boolean flag2 = world.getBlockState(pos2).getBlock() == block2 || world.getBlockState(pos2.up()).getBlock() == block2;
      boolean flag3 = false;
      if (flag && !flag2 || n2 > n) {
         flag3 = true;
      }

      BlockPos pos4 = pos.up();
      IBlockState state = block2.getDefaultState()
         .withProperty(BlockDoor.FACING, facing)
         .withProperty(BlockDoor.HINGE, flag3 ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT);
      world.setBlockState(pos, state.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 2);
      world.setBlockState(pos4, state.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2);
      world.notifyNeighborsOfStateChange(pos, block2);
      world.notifyNeighborsOfStateChange(pos4, block2);
   }
}

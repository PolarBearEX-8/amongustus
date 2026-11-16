package net.minecraft.item;

import client.setting.CombatAngleProperty;
import client.setting.ContextOptionType;
import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemDarkOakDoor extends Item {
   public Block block;

   public static void applyWorldByWorld(World world, BlockPos blockPos, EnumFacing enumFacing, Block block2) {
      BlockPos blockPos2 = blockPos.offset(enumFacing.getEnumFacingAsEnumFacing());
      BlockPos blockPos3 = blockPos.offset(enumFacing.getEnumFacing2());
      int count = (world.getBlockState(blockPos3).getBlock().isActive2() ? 1 : 0) + (world.getBlockState(blockPos3.up()).getBlock().isActive2() ? 1 : 0);
      int count2 = (world.getBlockState(blockPos2).getBlock().isActive2() ? 1 : 0) + (world.getBlockState(blockPos2.up()).getBlock().isActive2() ? 1 : 0);
      boolean flag = world.getBlockState(blockPos3).getBlock() == block2 || world.getBlockState(blockPos3.up()).getBlock() == block2;
      boolean flag2 = world.getBlockState(blockPos2).getBlock() == block2 || world.getBlockState(blockPos2.up()).getBlock() == block2;
      boolean flag3 = false;
      if (flag && !flag2 || count2 > count) {
         flag3 = true;
      }

      BlockPos blockPos4 = blockPos.up();
      IBlockState blockState = block2.getDefaultState()
         .resolveIBlockState(BlockDarkOakDoor.autoWalkPath, enumFacing)
         .resolveIBlockState(BlockDarkOakDoor.immutableSetEntry2, flag3 ? ContextOptionType.RIGHT : ContextOptionType.LEFT);
      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(BlockDarkOakDoor.immutableSetEntry, CombatAngleProperty.LOWER), 2);
      world.matchesBlockPos(blockPos4, blockState.resolveIBlockState(BlockDarkOakDoor.immutableSetEntry, CombatAngleProperty.UPPER), 2);
      world.applyBlockPosByBlockPos(blockPos, block2);
      world.applyBlockPosByBlockPos(blockPos4, block2);
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      BlockPos blockPos2 = blockPos;
      if (enumFacing != EnumFacing.UP) {
         return false;
      } else {
         if (!world.getBlockState(blockPos).getBlock().processAction6(world, blockPos)) {
            blockPos2 = blockPos.offset(enumFacing);
         }

         if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
            return false;
         } else if (!this.block.processAction7(world, blockPos2)) {
            return false;
         } else {
            applyWorldByWorld(world, blockPos2, EnumFacing.resolveEnumFacing((double)player.rotationYaw), this.block);
            stack.animationsToGo--;
            return true;
         }
      }
   }

   public ItemDarkOakDoor(Block block2) {
      this.block = block2;
      this.processAction(ItemGroupManager.itemGroupManager9);
   }
}

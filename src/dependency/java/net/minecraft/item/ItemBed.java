package net.minecraft.item;

import client.setting.CombatStateProperty;
import client.util.IBlockState;
import net.minecraft.block.BlockBed;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBed extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      BlockPos blockPos2 = blockPos;
      if (world.state) {
         return true;
      } else if (enumFacing != EnumFacing.UP) {
         return false;
      } else {
         boolean flag = world.getBlockState(blockPos).getBlock().processAction6(world, blockPos);
         if (!flag) {
            blockPos2 = blockPos.up();
         }

         EnumFacing enumFacing2 = EnumFacing.resolveEnumFacing(MathHelper.ceiling_double_int((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5) & 3);
         BlockPos blockPos3 = blockPos2.offset(enumFacing2);
         if (player.matchesBlockPos(blockPos2, enumFacing, stack) && player.matchesBlockPos(blockPos3, enumFacing, stack)) {
            boolean flag2 = world.getBlockState(blockPos3).getBlock().processAction6(world, blockPos3);
            boolean flag3 = flag || world.isAirBlock(blockPos2);
            boolean flag4 = flag2 || world.isAirBlock(blockPos3);
            if (flag3
               && flag4
               && World.matchesIBlockAccess(world, blockPos2.getX(), blockPos2.getY() - 1, blockPos2.getZ())
               && World.matchesIBlockAccess(world, blockPos2.getX(), blockPos2.getY() - 1, blockPos2.getZ())) {
               IBlockState blockState = Blocks.bed
                  .getDefaultState()
                  .resolveIBlockState(BlockBed.internalImmutableSetEntry, Boolean.FALSE)
                  .resolveIBlockState(BlockBed.autoWalkPath, enumFacing2)
                  .resolveIBlockState(BlockBed.immutableSetEntry, CombatStateProperty.FOOT);
               if (world.matchesBlockPos(blockPos2, blockState, 3)) {
                  IBlockState blockState2 = blockState.resolveIBlockState(BlockBed.immutableSetEntry, CombatStateProperty.HEAD);
                  world.matchesBlockPos(blockPos3, blockState2, 3);
               }

               stack.animationsToGo--;
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
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}

package net.minecraft.item;

import client.setting.MovementModeProperty;
import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DiamondAxe extends ItemBlock {
   public final ExtraBlockAdapter extraBlockAdapter;
   public final ExtraBlockAdapter extraBlockAdapter2;

   @Override
   public int processAction2(int count) {
      return count;
   }

   @Override
   public String getText(ItemStack stack) {
      return this.extraBlockAdapter.resolveText(stack.getIndexOffset());
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (stack.animationsToGo == 0) {
         return false;
      } else if (!player.matchesBlockPos(blockPos.offset(enumFacing), enumFacing, stack)) {
         return false;
      } else {
         Object entry = this.extraBlockAdapter.resolveTarget(stack);
         IBlockState blockState = world.getBlockState(blockPos);
         if (blockState.getBlock() == this.extraBlockAdapter) {
            IProperty iProperty = this.extraBlockAdapter.getIProperty();
            Comparable comparable = blockState.getComparable(iProperty);
            MovementModeProperty comparable2 = (MovementModeProperty)blockState.getComparable(ExtraBlockAdapter.immutableSetEntry);
            if ((enumFacing == EnumFacing.UP && comparable2 == MovementModeProperty.BOTTOM || enumFacing == EnumFacing.DOWN && comparable2 == MovementModeProperty.TOP)
               && comparable == entry) {
               IBlockState blockState2 = this.extraBlockAdapter2.getDefaultState().resolveIBlockState(iProperty, comparable);
               if (world.matchesAxisAlignedBB(this.extraBlockAdapter2.processAction(world, blockState2, blockPos.getX(), blockPos.getY(), blockPos.getZ()))
                  && world.matchesBlockPos(blockPos, blockState2, 3)) {
                  world.applyText(
                     (double)((float)blockPos.getX() + 0.5F),
                     (double)((float)blockPos.getY() + 0.5F),
                     (double)((float)blockPos.getZ() + 0.5F),
                     this.extraBlockAdapter2.stepManager20.getTextAsText(),
                     (this.extraBlockAdapter2.stepManager20.getFactor2() + 1.0F) / 2.0F,
                     this.extraBlockAdapter2.stepManager20.getFactor() * 0.8F
                  );
                  stack.animationsToGo--;
               }

               return true;
            }
         }

         return this.matchesItemStack(stack, world, blockPos.offset(enumFacing), entry) ? true : super.processAction(stack, player, world, blockPos, enumFacing, posX, posY, posZ);
      }
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer player, ItemStack stack) {
      IProperty iProperty = this.extraBlockAdapter.getIProperty();
      Object entry = this.extraBlockAdapter.resolveTarget(stack);
      IBlockState blockState = world.getBlockState(blockPos);
      if (blockState.getBlock() == this.extraBlockAdapter) {
         boolean flag = blockState.getComparable(ExtraBlockAdapter.immutableSetEntry) == MovementModeProperty.TOP;
         if ((enumFacing == EnumFacing.UP && !flag || enumFacing == EnumFacing.DOWN && flag) && entry == blockState.getComparable(iProperty)) {
            return true;
         }
      }

      BlockPos blockPos2 = blockPos.offset(enumFacing);
      IBlockState blockState2 = world.getBlockState(blockPos2);
      return blockState2.getBlock() == this.extraBlockAdapter && entry == blockState2.getComparable(iProperty) ? true : super.matchesWorld(world, blockPos, enumFacing, player, stack);
   }

   private boolean matchesItemStack(ItemStack stack, World world, BlockPos blockPos, Object entry) {
      IBlockState blockState = world.getBlockState(blockPos);
      if (blockState.getBlock() == this.extraBlockAdapter) {
         Comparable comparable = blockState.getComparable(this.extraBlockAdapter.getIProperty());
         if (comparable == entry) {
            IBlockState blockState2 = this.extraBlockAdapter2.getDefaultState().resolveIBlockState(this.extraBlockAdapter.getIProperty(), comparable);
            if (world.matchesAxisAlignedBB(this.extraBlockAdapter2.processAction(world, blockState2, blockPos.getX(), blockPos.getY(), blockPos.getZ()))
               && world.matchesBlockPos(blockPos, blockState2, 3)) {
               world.applyText(
                  (double)((float)blockPos.getX() + 0.5F),
                  (double)((float)blockPos.getY() + 0.5F),
                  (double)((float)blockPos.getZ() + 0.5F),
                  this.extraBlockAdapter2.stepManager20.getTextAsText(),
                  (this.extraBlockAdapter2.stepManager20.getFactor2() + 1.0F) / 2.0F,
                  this.extraBlockAdapter2.stepManager20.getFactor() * 0.8F
               );
               stack.animationsToGo--;
            }

            return true;
         }
      }

      return false;
   }

   public DiamondAxe(Block block, ExtraBlockAdapter extraBlockAdapter3, ExtraBlockAdapter extraBlockAdapter4) {
      super(block);
      this.extraBlockAdapter = extraBlockAdapter3;
      this.extraBlockAdapter2 = extraBlockAdapter4;
      this.processAction3(0);
      this.processAction(true);
   }
}

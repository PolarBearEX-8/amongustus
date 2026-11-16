package net.minecraft.item;

import client.event.PreRenderListener;
import client.setting.MovementProfileProperty;
import client.util.IBlockState;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.SpecialBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public final class IronStaff extends ItemCallbackAdapter {
   public final ItemCallbackAdapter itemCallbackAdapter = new ItemCallbackAdapter();

   @Override
   public void applyPreRenderListener(PreRenderListener preRenderListener) {
      preRenderListener.getWorld().applyBlockPosByBlockPos(1000, preRenderListener.getBlockPos(), 0);
   }

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      EnumFacing enumFacing = BlockDispenser.resolveEnumFacing(preRenderListener.getCount());
      World world = preRenderListener.getWorld();
      double offset = preRenderListener.getValue() + (double)enumFacing.getInt2() * 1.125;
      double offset2 = Math.floor(preRenderListener.getValue2()) + (double)enumFacing.getInt3();
      double offset3 = preRenderListener.getValue3() + (double)enumFacing.getInt() * 1.125;
      BlockPos blockPos = preRenderListener.getBlockPos().offset(enumFacing);
      IBlockState blockState = world.getBlockState(blockPos);
      MovementProfileProperty movementProfileProperty = blockState.getBlock() instanceof SpecialBlockAdapter
         ? (MovementProfileProperty)blockState.getComparable(((SpecialBlockAdapter)blockState.getBlock()).getIProperty())
         : MovementProfileProperty.NORTH_SOUTH;
      double offset4;
      if (SpecialBlockAdapter.matchesIBlockState(blockState)) {
         if (movementProfileProperty.isActive()) {
            offset4 = 0.6;
         } else {
            offset4 = 0.1;
         }
      } else {
         if (blockState.getBlock().getMaterial() != Material.coreItemEntry || !SpecialBlockAdapter.matchesIBlockState(world.getBlockState(blockPos.down()))) {
            return this.itemCallbackAdapter.resolveItemStack(preRenderListener, stack);
         }

         IBlockState blockState2 = world.getBlockState(blockPos.down());
         MovementProfileProperty movementProfileProperty2 = blockState2.getBlock() instanceof SpecialBlockAdapter
            ? (MovementProfileProperty)blockState2.getComparable(((SpecialBlockAdapter)blockState2.getBlock()).getIProperty())
            : MovementProfileProperty.NORTH_SOUTH;
         if (enumFacing != EnumFacing.DOWN && movementProfileProperty2.isActive()) {
            offset4 = -0.4;
         } else {
            offset4 = -0.9;
         }
      }

      EntityMinecart entityMinecart = EntityMinecart.resolveEntityMinecart(
         world, offset, offset2 + offset4, offset3, ItemCommandBlockMinecart.resolveMovementNumberProperty((ItemCommandBlockMinecart)stack.getItem())
      );
      if (stack.isActive()) {
         entityMinecart.setLabelText(stack.getString());
      }

      world.matchesEntity(entityMinecart);
      stack.resolveItemStack(1);
      return stack;
   }
}

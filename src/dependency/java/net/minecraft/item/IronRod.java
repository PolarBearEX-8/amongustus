package net.minecraft.item;

import client.event.PreRenderListener;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityBoat;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public final class IronRod extends ItemCallbackAdapter {
   public final ItemCallbackAdapter itemCallbackAdapter = new ItemCallbackAdapter();

   @Override
   public void applyPreRenderListener(PreRenderListener preRenderListener) {
      preRenderListener.getWorld().applyBlockPosByBlockPos(1000, preRenderListener.getBlockPos(), 0);
   }

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      EnumFacing enumFacing = BlockDispenser.resolveEnumFacing(preRenderListener.getCount());
      World world = preRenderListener.getWorld();
      double offset = preRenderListener.getValue() + (double)((float)enumFacing.getInt2() * 1.125F);
      double offset2 = preRenderListener.getValue2() + (double)((float)enumFacing.getInt3() * 1.125F);
      double offset3 = preRenderListener.getValue3() + (double)((float)enumFacing.getInt() * 1.125F);
      BlockPos blockPos = preRenderListener.getBlockPos().offset(enumFacing);
      Material material = world.getBlockState(blockPos).getBlock().getMaterial();
      double offset4;
      if (Material.coreItemEntry1.equals(material)) {
         offset4 = 1.0;
      } else {
         if (!Material.coreItemEntry.equals(material) || !Material.coreItemEntry1.equals(world.getBlockState(blockPos.down()).getBlock().getMaterial())) {
            return this.itemCallbackAdapter.resolveItemStack(preRenderListener, stack);
         }

         offset4 = 0.0;
      }

      EntityBoat entityBoat = new EntityBoat(world, offset, offset2 + offset4, offset3);
      world.matchesEntity(entityBoat);
      stack.resolveItemStack(1);
      return stack;
   }
}

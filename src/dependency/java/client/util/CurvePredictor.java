package client.util;

import client.event.PreRenderListener;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityFireworksrocketentity;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public final class CurvePredictor extends ItemCallbackAdapter {
   @Override
   public void applyPreRenderListener(PreRenderListener preRenderListener) {
      preRenderListener.getWorld().applyBlockPosByBlockPos(1002, preRenderListener.getBlockPos(), 0);
   }

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      EnumFacing enumFacing = BlockDispenser.resolveEnumFacing(preRenderListener.getCount());
      double offset = preRenderListener.getValue() + (double)enumFacing.getInt2();
      double offset2 = (double)((float)preRenderListener.getBlockPos().getY() + 0.2F);
      double offset3 = preRenderListener.getValue3() + (double)enumFacing.getInt();
      EntityFireworksrocketentity entityFireworksrocketentity = new EntityFireworksrocketentity(preRenderListener.getWorld(), offset, offset2, offset3, stack);
      preRenderListener.getWorld().matchesEntity(entityFireworksrocketentity);
      stack.resolveItemStack(1);
      return stack;
   }
}

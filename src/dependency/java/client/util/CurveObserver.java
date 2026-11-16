package client.util;

import client.event.PreRenderListener;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public final class CurveObserver extends ItemCallbackAdapter {
   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      EnumFacing enumFacing = BlockDispenser.resolveEnumFacing(preRenderListener.getCount());
      double offset = preRenderListener.getValue() + (double)enumFacing.getInt2();
      double offset2 = (double)((float)preRenderListener.getBlockPos().getY() + 0.2F);
      double offset3 = preRenderListener.getValue3() + (double)enumFacing.getInt();
      Entity entity = ItemSpawnEgg.resolveEntityByWorld(preRenderListener.getWorld(), stack.getIndexOffset(), offset, offset2, offset3);
      if (entity instanceof EntityLivingBase && stack.isActive()) {
         ((EntityMob)entity).setLabelText(stack.getString());
      }

      stack.resolveItemStack(1);
      return stack;
   }
}

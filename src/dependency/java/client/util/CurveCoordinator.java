package client.util;

import client.event.HandlerListener;
import client.event.PreMotionListener;
import client.event.PreRenderListener;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class CurveCoordinator extends ItemCallbackAdapter {
   public abstract PreMotionListener resolvePreMotionListener(World world, HandlerListener handlerListener);

   public float getFactor() {
      return 6.0F;
   }

   @Override
   public void applyPreRenderListener(PreRenderListener preRenderListener) {
      preRenderListener.getWorld().applyBlockPosByBlockPos(1002, preRenderListener.getBlockPos(), 0);
   }

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      World world = preRenderListener.getWorld();
      HandlerListener handlerListener = BlockDispenser.resolveHandlerListener(preRenderListener);
      EnumFacing enumFacing = BlockDispenser.resolveEnumFacing(preRenderListener.getCount());
      PreMotionListener preMotionListener = this.resolvePreMotionListener(world, handlerListener);
      preMotionListener.applyDouble((double)enumFacing.getInt2(), (double)((float)enumFacing.getInt3() + 0.1F), (double)enumFacing.getInt(), this.getFactorAsFloat(), this.getFactor());
      world.matchesEntity((Entity)preMotionListener);
      stack.resolveItemStack(1);
      return stack;
   }

   public float getFactorAsFloat() {
      return 1.1F;
   }
}

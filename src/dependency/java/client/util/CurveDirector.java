package client.util;

import client.event.HandlerListener;
import client.event.PreMotionListener;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.VirtualItemEntry;
import net.minecraft.world.World;

public class CurveDirector extends CurveCoordinator {
   public final ItemStack itemStack;
   public final VirtualItemEntry virtualItemEntry;

   @Override
   public float getFactor() {
      return super.getFactor() * 0.5F;
   }

   public CurveDirector(VirtualItemEntry virtualItemEntry2, ItemStack stack) {
      this.virtualItemEntry = virtualItemEntry2;
      this.itemStack = stack;
   }

   @Override
   public PreMotionListener resolvePreMotionListener(World world, HandlerListener handlerListener) {
      return new EntityThrownpotion(world, handlerListener.getValue(), handlerListener.getValue2(), handlerListener.getValue3(), this.itemStack.getItemStack());
   }

   @Override
   public float getFactorAsFloat() {
      return super.getFactorAsFloat() * 1.25F;
   }
}

package client.util;

import client.event.HandlerListener;
import client.event.PreRenderListener;
import java.util.Random;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntitySmallfireball;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public final class CurveScheduler extends ItemCallbackAdapter {
   @Override
   public void applyPreRenderListener(PreRenderListener preRenderListener) {
      preRenderListener.getWorld().applyBlockPosByBlockPos(1009, preRenderListener.getBlockPos(), 0);
   }

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      EnumFacing enumFacing = BlockDispenser.resolveEnumFacing(preRenderListener.getCount());
      HandlerListener handlerListener = BlockDispenser.resolveHandlerListener(preRenderListener);
      double offset = handlerListener.getValue() + (double)((float)enumFacing.getInt2() * 0.3F);
      double offset2 = handlerListener.getValue2() + (double)((float)enumFacing.getInt3() * 0.3F);
      double offset3 = handlerListener.getValue3() + (double)((float)enumFacing.getInt() * 0.3F);
      World world = preRenderListener.getWorld();
      Random random = world.random;
      double offset4 = random.nextGaussian() * 0.05 + (double)enumFacing.getInt2();
      double offset5 = random.nextGaussian() * 0.05 + (double)enumFacing.getInt3();
      double offset6 = random.nextGaussian() * 0.05 + (double)enumFacing.getInt();
      world.matchesEntity(new EntitySmallfireball(world, offset, offset2, offset3, offset4, offset5, offset6));
      stack.resolveItemStack(1);
      return stack;
   }
}

package net.minecraft.item;

import client.event.HandlerListener;
import client.event.PreRenderListener;
import client.setting.EnumFacingXType;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityItem;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemCallbackAdapter implements CustomItemCallback {
   @Override
   public final ItemStack resolveItemStack(PreRenderListener preRenderListener, ItemStack stack) {
      ItemStack stack2 = this.resolveItemStackByPreRenderListene(preRenderListener, stack);
      this.applyPreRenderListener(preRenderListener);
      this.applyPreRenderListener(preRenderListener, BlockDispenser.resolveEnumFacing(preRenderListener.getCount()));
      return stack2;
   }

   private int computeCount(EnumFacing enumFacing) {
      return enumFacing.getInt2() + 1 + (enumFacing.getInt() + 1) * 3;
   }

   public void applyPreRenderListener(PreRenderListener preRenderListener) {
      preRenderListener.getWorld().applyBlockPosByBlockPos(1000, preRenderListener.getBlockPos(), 0);
   }

   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      EnumFacing enumFacing = BlockDispenser.resolveEnumFacing(preRenderListener.getCount());
      HandlerListener handlerListener = BlockDispenser.resolveHandlerListener(preRenderListener);
      ItemStack stack2 = stack.resolveItemStack(1);
      applyWorldByWorld(preRenderListener.getWorld(), stack2, 6, enumFacing, handlerListener);
      return stack;
   }

   public static void applyWorldByWorld(World world, ItemStack stack, int count, EnumFacing enumFacing, HandlerListener handlerListener) {
      double value = handlerListener.getValue();
      double value2 = handlerListener.getValue2();
      double value3 = handlerListener.getValue3();
      double offset;
      if (enumFacing.getEnumFacingXType() == EnumFacingXType.Y) {
         offset = value2 - 0.125;
      } else {
         offset = value2 - 0.15625;
      }

      EntityItem entityItem = new EntityItem(world, value, offset, value3, stack);
      double offset2 = world.random.nextDouble() * 0.1 + 0.2;
      entityItem.motionX = (double)enumFacing.getInt2() * offset2;
      entityItem.motionY = 0.2F;
      entityItem.motionZ = (double)enumFacing.getInt() * offset2;
      entityItem.motionX = entityItem.motionX + world.random.nextGaussian() * 0.0075F * (double)count;
      entityItem.motionY = entityItem.motionY + world.random.nextGaussian() * 0.0075F * (double)count;
      entityItem.motionZ = entityItem.motionZ + world.random.nextGaussian() * 0.0075F * (double)count;
      world.matchesEntity(entityItem);
   }

   public void applyPreRenderListener(PreRenderListener preRenderListener, EnumFacing enumFacing) {
      preRenderListener.getWorld().applyBlockPosByBlockPos(2000, preRenderListener.getBlockPos(), this.computeCount(enumFacing));
   }
}

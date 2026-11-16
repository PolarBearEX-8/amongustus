package client.util;

import client.event.PreRenderListener;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public final class GameTntPrimedManager extends ItemCallbackAdapter {
   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      World world = preRenderListener.getWorld();
      BlockPos blockPos = preRenderListener.getBlockPos().offset(BlockDispenser.resolveEnumFacing(preRenderListener.getCount()));
      EntityPrimedtnt entityPrimedtnt = new EntityPrimedtnt(world, (double)blockPos.getX() + 0.5, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5, (EntityLivingBase)null);
      world.matchesEntity(entityPrimedtnt);
      world.applyEntityByEntity(entityPrimedtnt, "game.tnt.primed", 1.0F, 1.0F);
      stack.animationsToGo--;
      return stack;
   }
}

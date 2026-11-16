package client.util;

import client.event.PreRenderListener;
import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public final class CurveFactory extends ItemCallbackAdapter {
   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      BlockPos blockPos = preRenderListener.getBlockPos().offset(BlockDispenser.resolveEnumFacing(preRenderListener.getCount()));
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      AxisAlignedBB boundingBox = new AxisAlignedBB((double)coordX, (double)coordY, (double)coordZ, (double)(coordX + 1), (double)(coordY + 1), (double)(coordZ + 1));
      List list = preRenderListener.getWorld().resolveList(EntityLivingBase.class, boundingBox, Predicates.and(LivingEntityFilter.predicate4, new CoreCustom(stack)));
      if (list.size() > 0) {
         EntityLivingBase livingEntity = (EntityLivingBase)list.get(0);
         byte byteValue = ((byte)((livingEntity instanceof EntityPlayer) ? 1 : 0));
         int count = EntityMob.computeCount(stack);
         ItemStack stack2 = stack.getItemStack();
         stack2.animationsToGo = 1;
         livingEntity.applyItemStack(count - byteValue, stack2);
         if (livingEntity instanceof EntityMob) {
            ((EntityMob)livingEntity).applyIndex(count, 2.0F);
         }

         stack.animationsToGo--;
         return stack;
      } else {
         return super.resolveItemStackByPreRenderListene(preRenderListener, stack);
      }
   }
}

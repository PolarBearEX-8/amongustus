package net.minecraft.inventory;

import client.event.LocalListener;
import client.util.DistanceBuffer;
import client.util.SplineFormatter;
import client.util.StatCraftItemManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SplineFormatterSlot extends Slot {
   public final SplineFormatter splineFormatter;
   public EntityPlayer entityPlayer;
   public int count;
   public final LocalListener localListener;

   @Override
   public void processAction(EntityPlayer player, ItemStack stack) {
      this.processAction4(stack);
      DistanceBuffer distance = this.splineFormatter.getDistanceBuffer();
      if (distance != null) {
         ItemStack stack2 = this.splineFormatter.processAction3(0);
         ItemStack stack3 = this.splineFormatter.processAction3(1);
         if (this.matchesDistanceBuffer(distance, stack2, stack3) || this.matchesDistanceBuffer(distance, stack3, stack2)) {
            this.localListener.applyDistanceBuffer(distance);
            player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager17);
            if (stack2 != null && stack2.animationsToGo <= 0) {
               stack2 = null;
            }

            if (stack3 != null && stack3.animationsToGo <= 0) {
               stack3 = null;
            }

            this.splineFormatter.processAction(0, stack2);
            this.splineFormatter.processAction(1, stack3);
         }
      }
   }

   private boolean matchesDistanceBuffer(DistanceBuffer distanceBuffer, ItemStack stack, ItemStack stack2) {
      ItemStack stack3 = distanceBuffer.getItemStack();
      ItemStack stack4 = distanceBuffer.getItemStack2();
      if (stack != null && stack.getItem() == stack3.getItem()) {
         if (stack4 != null && stack2 != null && stack4.getItem() == stack2.getItem()) {
            stack.animationsToGo = stack.animationsToGo - stack3.animationsToGo;
            stack2.animationsToGo = stack2.animationsToGo - stack4.animationsToGo;
            return true;
         }

         if (stack4 == null && stack2 == null) {
            stack.animationsToGo = stack.animationsToGo - stack3.animationsToGo;
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return false;
   }

   @Override
   public ItemStack decrStackSize(int count2) {
      if (this.isActive()) {
         this.count = this.count + Math.min(count2, this.getStack().animationsToGo);
      }

      return super.decrStackSize(count2);
   }

   @Override
   public void processAction4(ItemStack stack) {
      stack.processAction2(this.entityPlayer.worldObj, this.entityPlayer, this.count);
      this.count = 0;
   }

   public SplineFormatterSlot(EntityPlayer player, LocalListener localListener2, SplineFormatter splineFormatter2, int count2, int count3, int count4) {
      super(splineFormatter2, count2, count3, count4);
      this.entityPlayer = player;
      this.localListener = localListener2;
      this.splineFormatter = splineFormatter2;
   }

   @Override
   public void processAction(ItemStack stack, int count2) {
      this.count += count2;
      this.processAction4(stack);
   }
}

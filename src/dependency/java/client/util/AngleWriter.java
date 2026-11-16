package client.util;

import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.IronRing;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.MathHelper;

public class AngleWriter extends Slot {
   public EntityPlayer entityPlayer;
   public int count;

   @Override
   public ItemStack decrStackSize(int count2) {
      if (this.isActive()) {
         this.count = this.count + Math.min(count2, this.getStack().animationsToGo);
      }

      return super.decrStackSize(count2);
   }

   @Override
   public void processAction(EntityPlayer player, ItemStack stack) {
      this.processAction4(stack);
      super.processAction(player, stack);
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return false;
   }

   @Override
   public void processAction(ItemStack stack, int count2) {
      this.count += count2;
      this.processAction4(stack);
   }

   @Override
   public void processAction4(ItemStack stack) {
      stack.processAction2(this.entityPlayer.worldObj, this.entityPlayer, this.count);
      if (!this.entityPlayer.worldObj.state) {
         int count2 = this.count;
         float factor = IronRing.getIronRing().computeFactor(stack);
         if (factor == 0.0F) {
            count2 = 0;
         } else if (factor < 1.0F) {
            int count3 = MathHelper.floor_float((float)count2 * factor);
            if (count3 < MathHelper.computeCount((float)count2 * factor) && Math.random() < (double)((float)count2 * factor - (float)count3)) {
               count3++;
            }

            count2 = count3;
         }

         while (count2 > 0) {
            int count4 = EntityXporb.computeCount(count2);
            count2 -= count4;
            this.entityPlayer
               .worldObj
               .matchesEntity(
                  new EntityXporb(this.entityPlayer.worldObj, this.entityPlayer.posX, this.entityPlayer.posY + 0.5, this.entityPlayer.posZ + 0.5, count4)
               );
         }
      }

      this.count = 0;
      if (stack.getItem() == Items.ironIngot) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter12);
      }

      if (stack.getItem() == Items.cookedFish) {
         this.entityPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter28);
      }
   }

   public AngleWriter(EntityPlayer player, IInventory iInventory, int count2, int count3, int count4) {
      super(iInventory, count2, count3, count4);
      this.entityPlayer = player;
   }
}

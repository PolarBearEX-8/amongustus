package net.minecraft.item;

import client.util.HitBoxPredictor;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityVillager;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.util.MathHelper;

public class IronHelmet extends HitBoxPredictor {
   public int count3;
   public EntityVillager entityVillager;

   @Override
   public void updateState3() {
      super.updateState3();
      if (this.entityVillager.isActive35() && this.entity instanceof EntityVillager && ((EntityVillager)this.entity).isActive34()) {
         this.count3 = 10;
      } else {
         this.count3 = 0;
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      if (this.count3 > 0) {
         this.count3--;
         if (this.count3 == 0) {
            InventoryLargeChest inventoryLargeChest = this.entityVillager.getInventoryLargeChest();

            for (int index = 0; index < inventoryLargeChest.processAction4(); index++) {
               ItemStack stack = inventoryLargeChest.processAction3(index);
               ItemStack stack2 = null;
               if (stack != null) {
                  Item item = stack.getItem();
                  if ((item == Items.bread || item == Items.potato || item == Items.carrot) && stack.animationsToGo > 3) {
                     int count = stack.animationsToGo / 2;
                     stack.animationsToGo -= count;
                     stack2 = new ItemStack(item, count, stack.getIndexOffset());
                  } else if (item == Items.wheat && stack.animationsToGo > 5) {
                     int count2 = stack.animationsToGo / 2 / 3 * 3;
                     int count4 = count2 / 3;
                     stack.animationsToGo -= count2;
                     stack2 = new ItemStack(Items.bread, count4, 0);
                  }

                  if (stack.animationsToGo <= 0) {
                     inventoryLargeChest.processAction(index, (ItemStack)null);
                  }
               }

               if (stack2 != null) {
                  double offset = this.entityVillager.posY - 0.3F + (double)this.entityVillager.getHeight2();
                  EntityItem entityItem = new EntityItem(this.entityVillager.worldObj, this.entityVillager.posX, offset, this.entityVillager.posZ, stack2);
                  float factor = 0.3F;
                  float factor2 = this.entityVillager.scaleFactor12;
                  float factor3 = this.entityVillager.rotationPitch;
                  entityItem.motionX = (double)(-MathHelper.abs(factor2 / 180.0F * (float) Math.PI) * MathHelper.cos(factor3 / 180.0F * (float) Math.PI) * factor);
                  entityItem.motionZ = (double)(MathHelper.cos(factor2 / 180.0F * (float) Math.PI) * MathHelper.cos(factor3 / 180.0F * (float) Math.PI) * factor);
                  entityItem.motionY = (double)(-MathHelper.abs(factor3 / 180.0F * (float) Math.PI) * factor + 0.1F);
                  entityItem.updateState6();
                  this.entityVillager.worldObj.matchesEntity(entityItem);
                  break;
               }
            }
         }
      }
   }

   public IronHelmet(EntityVillager entityVillager2) {
      super(entityVillager2, EntityVillager.class, 3.0F, 0.02F);
      this.entityVillager = entityVillager2;
   }
}

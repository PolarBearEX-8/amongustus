package client.util;

import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityVillagergolem;

public class TertiaryCustom extends RotationPool {
   public EntityVillagergolem entityVillagergolem;
   public EntityVillager entityVillager;
   public int count2;

   @Override
   public boolean isActiveAsFlag() {
      return this.count2 > 0;
   }

   @Override
   public void updateState2() {
      this.entityVillagergolem.getEntityRavagerAdapter().applyEntityByEntity(this.entityVillager, 30.0F, 30.0F);
      this.count2--;
   }

   @Override
   public boolean isActive() {
      if (!this.entityVillagergolem.worldObj.isActive2()) {
         return false;
      } else if (this.entityVillagergolem.getRandom().nextInt(8000) != 0) {
         return false;
      } else {
         this.entityVillager = (EntityVillager)this.entityVillagergolem
            .worldObj
            .resolveEntity(EntityVillager.class, this.entityVillagergolem.getBoundingBox2().offset(6.0, 2.0, 6.0), this.entityVillagergolem);
         return this.entityVillager != null;
      }
   }

   @Override
   public void updateState3() {
      this.count2 = 400;
      this.entityVillagergolem.setEnabled7(true);
   }

   @Override
   public void updateState() {
      this.entityVillagergolem.setEnabled7(false);
      this.entityVillager = null;
   }

   public TertiaryCustom(EntityVillagergolem entityVillagergolem2) {
      this.entityVillagergolem = entityVillagergolem2;
      this.setCount(3);
   }
}

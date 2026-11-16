package net.minecraft.item;

import client.util.RotationPool;
import java.util.List;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityVillagergolem;

public class LocalItemEntry extends RotationPool {
   public EntityVillager entityVillager;
   public EntityVillagergolem entityVillagergolem;
   public int count2;
   public boolean enabled;

   @Override
   public void updateState() {
      this.entityVillagergolem = null;
      this.entityVillager.getPathfindManager().updateState4();
   }

   @Override
   public void updateState3() {
      this.count2 = this.entityVillager.getRandom().nextInt(320);
      this.enabled = false;
      this.entityVillagergolem.getPathfindManager().updateState4();
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.entityVillagergolem.getCount28() > 0;
   }

   @Override
   public void updateState2() {
      this.entityVillager.getEntityRavagerAdapter().applyEntityByEntity(this.entityVillagergolem, 30.0F, 30.0F);
      if (this.entityVillagergolem.getCount28() == this.count2) {
         this.entityVillager.getPathfindManager().matchesEntityByEntity(this.entityVillagergolem, 0.5);
         this.enabled = true;
      }

      if (this.enabled && this.entityVillager.getCoordinate(this.entityVillagergolem) < 4.0) {
         this.entityVillagergolem.setEnabled7(false);
         this.entityVillager.getPathfindManager().updateState4();
      }
   }

   @Override
   public boolean isActive() {
      if (this.entityVillager.getCount9() >= 0) {
         return false;
      } else if (!this.entityVillager.worldObj.isActive2()) {
         return false;
      } else {
         List list = this.entityVillager.worldObj.resolveList(EntityVillagergolem.class, this.entityVillager.getBoundingBox2().offset(6.0, 2.0, 6.0));
         if (list.isEmpty()) {
            return false;
         } else {
            for (EntityVillagergolem entityVillagergolem2 : (Iterable<EntityVillagergolem>)(Iterable<?>)list) {
               if (entityVillagergolem2.getCount28() > 0) {
                  this.entityVillagergolem = entityVillagergolem2;
                  break;
               }
            }

            return this.entityVillagergolem != null;
         }
      }
   }

   public LocalItemEntry(EntityVillager entityVillager2) {
      this.entityVillager = entityVillager2;
      this.setCount(3);
   }
}

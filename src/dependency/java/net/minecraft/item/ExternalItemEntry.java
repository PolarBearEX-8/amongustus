package net.minecraft.item;

import client.util.RotationPool;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityLivingBase;

public class ExternalItemEntry extends RotationPool {
   public EntityCreeper entityCreeper;
   public EntityLivingBase entityLivingBase;

   @Override
   public void updateState2() {
      if (this.entityLivingBase == null) {
         this.entityCreeper.applyIndex8(-1);
      } else if (this.entityCreeper.getCoordinate(this.entityLivingBase) > 49.0) {
         this.entityCreeper.applyIndex8(-1);
      } else if (!this.entityCreeper.getCanSeeManager().matchesEntityByEntity(this.entityLivingBase)) {
         this.entityCreeper.applyIndex8(-1);
      } else {
         this.entityCreeper.applyIndex8(1);
      }
   }

   @Override
   public void updateState3() {
      this.entityCreeper.getPathfindManager().updateState4();
      this.entityLivingBase = this.entityCreeper.getEntityLivingBase4();
   }

   @Override
   public boolean isActive() {
      EntityLivingBase entityLivingBase2 = this.entityCreeper.getEntityLivingBase4();
      return this.entityCreeper.getCount9() > 0 || entityLivingBase2 != null && this.entityCreeper.getCoordinate(entityLivingBase2) < 9.0;
   }

   @Override
   public void updateState() {
      this.entityLivingBase = null;
   }

   public ExternalItemEntry(EntityCreeper entityCreeper2) {
      this.entityCreeper = entityCreeper2;
      this.setCount(1);
   }
}

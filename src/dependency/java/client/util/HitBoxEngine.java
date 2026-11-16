package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.world.World;

public class HitBoxEngine extends RotationPool {
   public World world;
   public EntityMob entityMob;
   public EntityLivingBase entityLivingBase;
   public int count2;

   @Override
   public void updateState2() {
      this.entityMob.getEntityRavagerAdapter().applyEntityByEntity(this.entityLivingBase, 30.0F, 30.0F);
      double offset = (double)(this.entityMob.width * 2.0F * this.entityMob.width * 2.0F);
      double doubleItem = this.entityMob.getDouble3(this.entityLivingBase.posX, this.entityLivingBase.getBoundingBox2().minY, this.entityLivingBase.posZ);
      double offset2 = 0.8;
      if (doubleItem > offset && doubleItem < 16.0) {
         offset2 = 1.33;
      } else if (doubleItem < 225.0) {
         offset2 = 0.6;
      }

      this.entityMob.getPathfindManager().matchesEntityByEntity(this.entityLivingBase, offset2);
      this.count2 = Math.max(this.count2 - 1, 0);
      if (doubleItem <= offset && this.count2 <= 0) {
         this.count2 = 20;
         this.entityMob.matchesEntity4(this.entityLivingBase);
      }
   }

   @Override
   public void updateState() {
      this.entityLivingBase = null;
      this.entityMob.getPathfindManager().updateState4();
   }

   @Override
   public boolean isActive() {
      EntityLivingBase entityLivingBase2 = this.entityMob.getEntityLivingBase4();
      if (entityLivingBase2 == null) {
         return false;
      } else {
         this.entityLivingBase = entityLivingBase2;
         return true;
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.entityLivingBase.canExecute()
         ? false
         : (this.entityMob.getCoordinate(this.entityLivingBase) > 225.0 ? false : !this.entityMob.getPathfindManager().isActive2() || this.isActive());
   }

   public HitBoxEngine(EntityMob entityMob2) {
      this.entityMob = entityMob2;
      this.world = entityMob2.worldObj;
      this.setCount(3);
   }
}

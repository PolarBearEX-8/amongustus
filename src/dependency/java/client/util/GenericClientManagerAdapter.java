package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityVillager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GenericClientManagerAdapter extends RotationPool {
   public EntityVillager entityVillager;
   public EntityVillager entityVillager2;
   public World world;
   public int count2;
   public SplineChecker splineChecker;

   @Override
   public void updateState() {
      this.splineChecker = null;
      this.entityVillager2 = null;
      this.entityVillager.setEnabled31(false);
   }

   private void updateState4() {
      EntityVillager entityVillager3 = this.entityVillager.resolveEntityVillager(this.entityVillager2);
      this.entityVillager2.applyIndex8(6000);
      this.entityVillager.applyIndex8(6000);
      this.entityVillager2.onEnable(false);
      this.entityVillager.onEnable(false);
      entityVillager3.applyIndex8(-24000);
      entityVillager3.applyDoubleByDouble(this.entityVillager.posX, this.entityVillager.posY, this.entityVillager.posZ, 0.0F, 0.0F);
      this.world.matchesEntity(entityVillager3);
      this.world.applyEntity(entityVillager3, (byte)12);
   }

   private boolean isActive3() {
      if (!this.splineChecker.isActive()) {
         return false;
      } else {
         int count = (int)((double)((float)this.splineChecker.size()) * 0.35);
         return this.splineChecker.getCount4() < count;
      }
   }

   @Override
   public void updateState3() {
      this.count2 = 300;
      this.entityVillager.setEnabled31(true);
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.count2 >= 0 && this.isActive3() && this.entityVillager.getCount9() == 0 && this.entityVillager.checkFlag(false);
   }

   @Override
   public void updateState2() {
      this.count2--;
      this.entityVillager.getEntityRavagerAdapter().applyEntityByEntity(this.entityVillager2, 10.0F, 30.0F);
      if (this.entityVillager.getCoordinate(this.entityVillager2) > 2.25) {
         this.entityVillager.getPathfindManager().matchesEntityByEntity(this.entityVillager2, 0.25);
      } else if (this.count2 == 0 && this.entityVillager2.isEnabled31()) {
         this.updateState4();
      }

      if (this.entityVillager.getRandom().nextInt(35) == 0) {
         this.world.applyEntity(this.entityVillager, (byte)12);
      }
   }

   @Override
   public boolean isActive() {
      if (this.entityVillager.getCount9() != 0) {
         return false;
      } else if (this.entityVillager.getRandom().nextInt(500) != 0) {
         return false;
      } else {
         this.splineChecker = this.world.getWorldCustomProperty().resolveSplineChecker(new BlockPos(this.entityVillager), 0);
         if (this.splineChecker == null) {
            return false;
         } else if (this.isActive3() && this.entityVillager.checkFlag(true)) {
            Entity entity = this.world.resolveEntity(EntityVillager.class, this.entityVillager.getBoundingBox2().offset(8.0, 3.0, 8.0), this.entityVillager);
            if (entity == null) {
               return false;
            } else {
               this.entityVillager2 = (EntityVillager)entity;
               return this.entityVillager2.getCount9() == 0 && this.entityVillager2.checkFlag(true);
            }
         } else {
            return false;
         }
      }
   }

   public GenericClientManagerAdapter(EntityVillager entityVillager3) {
      this.entityVillager = entityVillager3;
      this.world = entityVillager3.worldObj;
      this.setCount(3);
   }
}

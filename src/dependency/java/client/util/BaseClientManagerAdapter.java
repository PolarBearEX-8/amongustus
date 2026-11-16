package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class BaseClientManagerAdapter extends RotationPool {
   public EntityMob entityMob;
   public Entity entity;
   public float factor;
   public int count2;
   public float factor2;
   public Class memberRef;

   @Override
   public void updateState() {
      this.entity = null;
   }

   @Override
   public void updateState3() {
      this.count2 = 40 + this.entityMob.getRandom().nextInt(40);
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.entity.canExecute() ? false : (this.entityMob.getCoordinate(this.entity) > (double)(this.factor * this.factor) ? false : this.count2 > 0);
   }

   @Override
   public void updateState2() {
      this.entityMob
         .getEntityRavagerAdapter()
         .applyDouble(this.entity.posX, this.entity.posY + (double)this.entity.getHeight2(), this.entity.posZ, 10.0F, (float)this.entityMob.getCount8());
      this.count2--;
   }

   @Override
   public boolean isActive() {
      if (this.entityMob.getRandom().nextFloat() >= this.factor2) {
         return false;
      } else {
         if (this.entityMob.getEntityLivingBase4() != null) {
            this.entity = this.entityMob.getEntityLivingBase4();
         }

         if (this.memberRef == EntityPlayer.class) {
            this.entity = this.entityMob.worldObj.resolveEntityPlayerByEntity(this.entityMob, (double)this.factor);
         } else {
            this.entity = this.entityMob
               .worldObj
               .resolveEntity(this.memberRef, this.entityMob.getBoundingBox2().offset((double)this.factor, 3.0, (double)this.factor), this.entityMob);
         }

         return this.entity != null;
      }
   }

   public BaseClientManagerAdapter(EntityMob entityMob2, Class clazz, float factor3) {
      this.entityMob = entityMob2;
      this.memberRef = clazz;
      this.factor = factor3;
      this.factor2 = 0.02F;
      this.setCount(2);
   }

   public BaseClientManagerAdapter(EntityMob entityMob2, Class clazz, float factor3, float factor4) {
      this.entityMob = entityMob2;
      this.memberRef = clazz;
      this.factor = factor3;
      this.factor2 = factor4;
      this.setCount(2);
   }
}

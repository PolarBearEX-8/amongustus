package client.util;

import client.setting.GameSettingsPeacefulType;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;

public class HitBoxTracker extends RotationPool {
   public EntityGuardian entityGuardian;
   public int count2;

   @Override
   public void updateState3() {
      this.count2 = -10;
      this.entityGuardian.getPathfindManager().updateState4();
      this.entityGuardian.getEntityRavagerAdapter().applyEntityByEntity(this.entityGuardian.getEntityLivingBase4(), 90.0F, 90.0F);
      this.entityGuardian.enabled4 = true;
   }

   @Override
   public boolean isActive() {
      EntityLivingBase entityLivingBase = this.entityGuardian.getEntityLivingBase4();
      return entityLivingBase != null && entityLivingBase.canExecute();
   }

   @Override
   public void updateState2() {
      EntityLivingBase entityLivingBase = this.entityGuardian.getEntityLivingBase4();
      this.entityGuardian.getPathfindManager().updateState4();
      this.entityGuardian.getEntityRavagerAdapter().applyEntityByEntity(entityLivingBase, 90.0F, 90.0F);
      if (!this.entityGuardian.checkEntity4(entityLivingBase)) {
         this.entityGuardian.setEntityLivingBase((EntityLivingBase)null);
      } else {
         this.count2++;
         if (this.count2 == 0) {
            EntityGuardian.applyEntityGuardian(this.entityGuardian, this.entityGuardian.getEntityLivingBase4().getEntityId());
            this.entityGuardian.worldObj.applyEntity(this.entityGuardian, (byte)21);
         } else if (this.count2 >= this.entityGuardian.getCount9()) {
            float factor = 1.0F;
            if (this.entityGuardian.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD) {
               factor += 2.0F;
            }

            if (this.entityGuardian.isActive32()) {
               factor += 2.0F;
            }

            entityLivingBase.matchesDamageSource(DamageSource.resolveDamageSource(this.entityGuardian, this.entityGuardian), factor);
            entityLivingBase.matchesDamageSource(
               DamageSource.resolveDamageSourceByEntityLivingBase(this.entityGuardian),
               (float)this.entityGuardian.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).getDouble()
            );
            this.entityGuardian.setEntityLivingBase((EntityLivingBase)null);
         } else if (this.count2 >= 60) {
            int count = this.count2 % 20;
         }

         super.updateState2();
      }
   }

   @Override
   public void updateState() {
      EntityGuardian.applyEntityGuardian(this.entityGuardian, 0);
      this.entityGuardian.setEntityLivingBase((EntityLivingBase)null);
      EntityGuardian.resolveAdvancedClientManagerAdapter(this.entityGuardian).updateState4();
   }

   @Override
   public boolean isActiveAsFlag() {
      return super.isActiveAsFlag()
         && (this.entityGuardian.isActive32() || this.entityGuardian.getCoordinate(this.entityGuardian.getEntityLivingBase4()) > 9.0);
   }

   public HitBoxTracker(EntityGuardian entityGuardian2) {
      this.entityGuardian = entityGuardian2;
      this.setCount(3);
   }
}

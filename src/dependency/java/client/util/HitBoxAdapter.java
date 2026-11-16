package client.util;

import client.event.PostMotionListener;
import client.setting.AttackEffectsExplosionNormalType;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class HitBoxAdapter extends HitBoxBuffer implements PostMotionListener {
   public HitBoxCoordinator hitBoxCoordinator = new HitBoxCoordinator(this);

   public void applyText(String text) {
      this.dataWatcher.applyTargetByTarget(17, text);
   }

   @Override
   public SecondaryClientManager getSecondaryClientManager() {
      if (this.isActive32()) {
         EntityLivingBase entityLivingBase = this.getEntityLivingBase7();
         if (entityLivingBase != null) {
            return entityLivingBase.getSecondaryClientManager();
         }
      }

      return super.getSecondaryClientManager();
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      String text;
      if (tagCompound.matchesText("OwnerUUID", 8)) {
         text = tagCompound.resolveText("OwnerUUID");
      } else {
         text = AdvancedCustom.resolveText(tagCompound.resolveText("Owner"));
      }

      if (text.length() > 0) {
         this.applyText(text);
         this.setEnabled9(true);
      }

      this.hitBoxCoordinator.setEnabled(tagCompound.matchesText("Sitting"));
      this.setEnabled8(tagCompound.matchesText("Sitting"));
   }

   public boolean matchesEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, EntityLivingBase livingEntity2) {
      return true;
   }

   @Override
   public String getTextAsText() {
      return this.dataWatcher.resolveText(17);
   }

   public boolean matchesEntityLivingBase3(EntityLivingBase livingEntity) {
      return livingEntity == this.getEntityLivingBase7();
   }

   public EntityLivingBase getEntityLivingBase7() {
      try {
         UUID uuid = UUID.fromString(this.getTextAsText());
         return uuid == null ? null : this.worldObj.getPlayerEntityByUUID(uuid);
      } catch (IllegalArgumentException illegalArgumentException) {
         return null;
      }
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 7) {
         this.setEnabled7(true);
      } else if (byteValue == 6) {
         this.setEnabled7(false);
      } else {
         super.processByte(byteValue);
      }
   }

   public HitBoxCoordinator getHitBoxCoordinator() {
      return this.hitBoxCoordinator;
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      if (!this.worldObj.state
         && this.worldObj.getDoFireTickManager().matchesText("showDeathMessages")
         && this.processAction()
         && this.getEntityLivingBase7() instanceof EntityPlayerAdapter) {
         ((EntityPlayerAdapter)this.getEntityLivingBase7()).applyIChatComponent(this.getGenericManager().getIChatComponent());
      }

      super.applyDamageSource(damageSource);
   }

   @Override
   public Entity getEntityAsEntity() {
      return this.getEntityLivingBase7();
   }

   @Override
   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      if (this.isActive32()) {
         EntityLivingBase entityLivingBase = this.getEntityLivingBase7();
         if (livingEntity == entityLivingBase) {
            return true;
         }

         if (entityLivingBase != null) {
            return entityLivingBase.matchesEntityLivingBase(livingEntity);
         }
      }

      return super.matchesEntityLivingBase(livingEntity);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      if (this.getTextAsText() == null) {
         tagCompound.put("OwnerUUID", "");
      } else {
         tagCompound.put("OwnerUUID", this.getTextAsText());
      }

      tagCompound.applyText("Sitting", this.isActive33());
   }

   public void updateState50() {
   }

   public void setEnabled8(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue | 1));
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue & -2));
      }
   }

   public boolean isActive32() {
      return (this.dataWatcher.computeByteCode(16) & 4) != 0;
   }

   public boolean isActive33() {
      return (this.dataWatcher.computeByteCode(16) & 1) != 0;
   }

   public void setEnabled9(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue | 4));
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue & -5));
      }

      this.updateState50();
   }

   public void setEnabled7(boolean enabled) {
      AttackEffectsExplosionNormalType attackEffectsExplosionNormalType = AttackEffectsExplosionNormalType.HEART;
      if (!enabled) {
         attackEffectsExplosionNormalType = AttackEffectsExplosionNormalType.SMOKE_NORMAL;
      }

      for (int index = 0; index < 7; index++) {
         double offset = this.random.nextGaussian() * 0.02;
         double offset2 = this.random.nextGaussian() * 0.02;
         double offset3 = this.random.nextGaussian() * 0.02;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               attackEffectsExplosionNormalType,
               this.posX + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
               this.posY + 0.5 + (double)(this.random.nextFloat() * this.height),
               this.posZ + (double)(this.random.nextFloat() * this.width * 2.0F) - (double)this.width,
               offset,
               offset2,
               offset3,
               new int[0]
            );
      }
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
      this.dataWatcher.applyTarget(17, "");
   }

   public HitBoxAdapter(World world) {
      super(world);
      this.updateState50();
   }
}

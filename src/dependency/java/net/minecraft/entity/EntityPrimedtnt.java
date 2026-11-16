package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPrimedtnt extends Entity {
   public int indexOffset4;
   public EntityLivingBase entityLivingBase;

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public EntityLivingBase getEntityLivingBase() {
      return this.entityLivingBase;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.indexOffset4 = tagCompound.computeByteCode("Fuse");
   }

   @Override
   public float getHeight2() {
      return 0.0F;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Fuse", (byte)this.indexOffset4);
   }

   private void updateState2() {
      float factor = 4.0F;
      this.worldObj.resolveBlockBreakStateByEntity(this, this.posX, this.posY + (double)(this.height / 16.0F), this.posZ, factor, true);
   }

   @Override
   public boolean isActive5() {
      return !this.isDead;
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY -= 0.04F;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.98F;
      this.motionY *= 0.98F;
      this.motionZ *= 0.98F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
         this.motionY *= -0.5;
      }

      if (this.indexOffset4-- <= 0) {
         this.updateState9();
         if (!this.worldObj.state) {
            this.updateState2();
         }
      } else {
         this.isActive2();
         this.worldObj
            .applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0, new int[0]);
      }
   }

   @Override
   public void updateState7() {
   }

   public EntityPrimedtnt(World world, double posX, double posY, double posZ, EntityLivingBase livingEntity) {
      this(world);
      this.processDouble2(posX, posY, posZ);
      float factor = (float)(Math.random() * Math.PI * 2.0);
      this.motionX = (double)(-((float)Math.sin((double)factor)) * 0.02F);
      this.motionY = 0.2F;
      this.motionZ = (double)(-((float)Math.cos((double)factor)) * 0.02F);
      this.indexOffset4 = 80;
      this.prevPosX = posX;
      this.prevPosY = posY;
      this.prevPosZ = posZ;
      this.entityLivingBase = livingEntity;
   }

   public EntityPrimedtnt(World world) {
      super(world);
      this.enabled = true;
      this.applyFloat(0.98F, 0.98F);
   }
}

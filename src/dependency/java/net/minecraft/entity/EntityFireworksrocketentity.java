package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFireworksrocketentity extends Entity {
   public int count11;
   public int count12;

   @Override
   public boolean checkDouble(double offset) {
      return offset < 4096.0;
   }

   @Override
   public int computeCount(float factor) {
      return super.computeCount(factor);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count11 = tagCompound.computeCount("Life");
      this.count12 = tagCompound.computeCount("LifeTime");
      NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("FireworksItem");
      if (nBTTagCompound != null) {
         ItemStack stack = ItemStack.resolveItemStack(nBTTagCompound);
         if (stack != null) {
            this.dataWatcher.applyTargetByTarget(8, stack);
         }
      }
   }

   @Override
   public void applyDouble2(double posX, double posY, double posZ) {
      this.motionX = posX;
      this.motionY = posY;
      this.motionZ = posZ;
      if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
         float factor = MathHelper.sqrt_double(posX * posX + posZ * posZ);
         this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.getDouble6(posX, posZ) * 180.0 / Math.PI);
         this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.getDouble6(posY, (double)factor) * 180.0 / Math.PI);
      }
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 17 && this.worldObj.state) {
         ItemStack stack = this.dataWatcher.getItemStack(8);
         NBTTagCompound tagCompound = null;
         if (stack != null && stack.canExecute()) {
            tagCompound = stack.getStackTagCompound().getNBTTagCompound("Fireworks");
         }

         this.worldObj.applyNBTTagCompound(this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, tagCompound);
      }

      super.processByte(byteValue);
   }

   @Override
   public float computeFactor(float factor) {
      return super.computeFactor(factor);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("Life", this.count11);
      tagCompound.put("LifeTime", this.count12);
      ItemStack stack = this.dataWatcher.getItemStack(8);
      if (stack != null) {
         NBTTagCompound tagCompound2 = new NBTTagCompound();
         stack.resolveNBTTagCompound(tagCompound2);
         tagCompound.applyText("FireworksItem", tagCompound2);
      }
   }

   @Override
   public boolean isActive3() {
      return false;
   }

   @Override
   public void updateState() {
      this.lastTickPosX = this.posX;
      this.lastTickPosY = this.posY;
      this.lastTickPosZ = this.posZ;
      super.updateState();
      this.motionX *= 1.15;
      this.motionZ *= 1.15;
      this.motionY += 0.04;
      this.applyDouble(this.motionX, this.motionY, this.motionZ);
      float factor = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = (float)(MathHelper.getDouble6(this.motionX, this.motionZ) * 180.0 / Math.PI);
      this.rotationPitch = (float)(MathHelper.getDouble6(this.motionY, (double)factor) * 180.0 / Math.PI);

      while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
         this.prevRotationPitch -= 360.0F;
      }

      while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
         this.prevRotationPitch += 360.0F;
      }

      while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
         this.prevRotationYaw -= 360.0F;
      }

      while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
         this.prevRotationYaw += 360.0F;
      }

      this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
      this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
      if (this.count11 == 0 && !this.isActive7()) {
         this.worldObj.applyEntityByEntity(this, "fireworks.launch", 3.0F, 1.0F);
      }

      this.count11++;
      if (this.worldObj.state && this.count11 % 2 < 2) {
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.FIREWORKS_SPARK,
               this.posX,
               this.posY - 0.3,
               this.posZ,
               this.random.nextGaussian() * 0.05,
               -this.motionY * 0.5,
               this.random.nextGaussian() * 0.05,
               new int[0]
            );
      }

      if (!this.worldObj.state && this.count11 > this.count12) {
         this.worldObj.applyEntity(this, (byte)17);
         this.updateState9();
      }
   }

   @Override
   public void updateState7() {
      this.dataWatcher.applyIndex(8, 5);
   }

   public EntityFireworksrocketentity(World world) {
      super(world);
      this.applyFloat(0.25F, 0.25F);
   }

   public EntityFireworksrocketentity(World world, double posX, double posY, double posZ, ItemStack stack) {
      super(world);
      this.count11 = 0;
      this.applyFloat(0.25F, 0.25F);
      this.processDouble2(posX, posY, posZ);
      int byteValue = 1;
      if (stack != null && stack.canExecute()) {
         this.dataWatcher.applyTargetByTarget(8, stack);
         NBTTagCompound nBTTagCompound = stack.getStackTagCompound().getNBTTagCompound("Fireworks");
         if (nBTTagCompound != null) {
            byteValue += nBTTagCompound.computeByteCode("Flight");
         }
      }

      this.motionX = this.random.nextGaussian() * 0.001;
      this.motionZ = this.random.nextGaussian() * 0.001;
      this.motionY = 0.05;
      this.count12 = 10 * byteValue + this.random.nextInt(6) + this.random.nextInt(7);
   }
}

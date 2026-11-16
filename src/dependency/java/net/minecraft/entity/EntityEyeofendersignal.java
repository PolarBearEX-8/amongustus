package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityEyeofendersignal extends Entity {
   public double value6;
   public double value10;
   public double value13;
   public int count11;
   public boolean enabled11;

   @Override
   public boolean checkDouble(double offset) {
      double offset2 = this.getBoundingBox2().getValue() * 4.0;
      if (Double.isNaN(offset2)) {
         offset2 = 4.0;
      }

      double offset3 = offset2 * 64.0;
      return offset < offset3 * offset3;
   }

   @Override
   public int computeCount(float factor) {
      return 15728880;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
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
   public float computeFactor(float factor) {
      return 1.0F;
   }

   public void applyBlockPosByBlockPos(BlockPos blockPos) {
      double coordX = (double)blockPos.getX();
      int coordY = blockPos.getY();
      double coordZ = (double)blockPos.getZ();
      double offset = coordX - this.posX;
      double offset2 = coordZ - this.posZ;
      float factor = MathHelper.sqrt_double(offset * offset + offset2 * offset2);
      if (factor > 12.0F) {
         this.value6 = this.posX + offset / (double)factor * 12.0;
         this.value13 = this.posZ + offset2 / (double)factor * 12.0;
         this.value10 = this.posY + 8.0;
      } else {
         this.value6 = coordX;
         this.value10 = (double)coordY;
         this.value13 = coordZ;
      }

      this.count11 = 0;
      this.enabled11 = this.random.nextInt(5) > 0;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
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
      this.posX = this.posX + this.motionX;
      this.posY = this.posY + this.motionY;
      this.posZ = this.posZ + this.motionZ;
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
      if (!this.worldObj.state) {
         double offset = this.value6 - this.posX;
         double offset2 = this.value13 - this.posZ;
         float factor2 = (float)Math.sqrt(offset * offset + offset2 * offset2);
         float doubleItem = (float)MathHelper.getDouble6(offset2, offset);
         double offset3 = (double)factor + (double)(factor2 - factor) * 0.0025;
         if (factor2 < 1.0F) {
            offset3 *= 0.8;
            this.motionY *= 0.8;
         }

         this.motionX = Math.cos((double)doubleItem) * offset3;
         this.motionZ = Math.sin((double)doubleItem) * offset3;
         if (this.posY < this.value10) {
            this.motionY = this.motionY + (1.0 - this.motionY) * 0.015F;
         } else {
            this.motionY = this.motionY + (-1.0 - this.motionY) * 0.015F;
         }
      }

      float factor3 = 0.25F;
      if (this.isInWater()) {
         for (int index = 0; index < 4; index++) {
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.WATER_BUBBLE,
                  this.posX - this.motionX * (double)factor3,
                  this.posY - this.motionY * (double)factor3,
                  this.posZ - this.motionZ * (double)factor3,
                  this.motionX,
                  this.motionY,
                  this.motionZ,
                  new int[0]
               );
         }
      } else {
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.PORTAL,
               this.posX - this.motionX * (double)factor3 + this.random.nextDouble() * 0.6 - 0.3,
               this.posY - this.motionY * (double)factor3 - 0.5,
               this.posZ - this.motionZ * (double)factor3 + this.random.nextDouble() * 0.6 - 0.3,
               this.motionX,
               this.motionY,
               this.motionZ,
               new int[0]
            );
      }

      if (!this.worldObj.state) {
         this.processDouble2(this.posX, this.posY, this.posZ);
         this.count11++;
         if (this.count11 > 80 && !this.worldObj.state) {
            this.updateState9();
            if (this.enabled11) {
               this.worldObj.matchesEntity(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.enderEye)));
            } else {
               this.worldObj.applyBlockPosByBlockPos(2003, new BlockPos(this), 0);
            }
         }
      }
   }

   @Override
   public void updateState7() {
   }

   public EntityEyeofendersignal(World world) {
      super(world);
      this.applyFloat(0.25F, 0.25F);
   }

   public EntityEyeofendersignal(World world, double posX, double posY, double posZ) {
      super(world);
      this.count11 = 0;
      this.applyFloat(0.25F, 0.25F);
      this.processDouble2(posX, posY, posZ);
   }
}

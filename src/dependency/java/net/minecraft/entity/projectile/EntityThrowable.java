package net.minecraft.entity.projectile;

import client.event.PreMotionListener;
import client.setting.AttackEffectsExplosionNormalType;
import client.util.MovingObjectType;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityThrowable extends Entity implements PreMotionListener {
   public int count16 = -1;
   public int count14 = -1;
   public int count11 = -1;
   public Block block;
   public boolean enabled11;
   public int count13;
   public EntityLivingBase entityLivingBase;
   public String labelText;
   public int count15;
   public int count12;

   @Override
   public boolean checkDouble(double offset) {
      double offset2 = this.getBoundingBox2().getValue() * 4.0;
      if (Double.isNaN(offset2)) {
         offset2 = 4.0;
      }

      double offset3 = offset2 * 64.0;
      return offset < offset3 * offset3;
   }

   public int getCount16() {
      return this.count16;
   }

   public abstract void applyMovingObjectPosition(MovingObjectPosition hitResult);

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count16 = tagCompound.processValue("xTile");
      this.count14 = tagCompound.processValue("yTile");
      this.count11 = tagCompound.processValue("zTile");
      if (tagCompound.matchesText("inTile", 8)) {
         this.block = Block.getBlockFromName(tagCompound.resolveText("inTile"));
      } else {
         this.block = Block.processAction3(tagCompound.computeByteCode("inTile") & 255);
      }

      this.count13 = tagCompound.computeByteCode("shake") & 255;
      this.enabled11 = tagCompound.computeByteCode("inGround") == 1;
      this.entityLivingBase = null;
      this.labelText = tagCompound.resolveText("ownerName");
      if (this.labelText != null && this.labelText.length() == 0) {
         this.labelText = null;
      }

      this.entityLivingBase = this.getEntityLivingBase();
   }

   public float getFactor() {
      return 0.0F;
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
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2) {
      float factor3 = MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
      double offset = posX / (double)factor3;
      double offset2 = posY / (double)factor3;
      double offset3 = posZ / (double)factor3;
      offset += this.random.nextGaussian() * 0.0075F * (double)factor2;
      offset2 += this.random.nextGaussian() * 0.0075F * (double)factor2;
      offset3 += this.random.nextGaussian() * 0.0075F * (double)factor2;
      offset *= (double)factor;
      offset2 *= (double)factor;
      offset3 *= (double)factor;
      this.motionX = offset;
      this.motionY = offset2;
      this.motionZ = offset3;
      float factor4 = MathHelper.sqrt_double(offset * offset + offset3 * offset3);
      this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.getDouble6(offset, offset3) * 180.0 / Math.PI);
      this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.getDouble6(offset2, (double)factor4) * 180.0 / Math.PI);
      this.count15 = 0;
   }

   public int getCount13() {
      return this.count13;
   }

   public int getCount12() {
      return this.count12;
   }

   public boolean isEnabled11() {
      return this.enabled11;
   }

   public float getFactorAsFloat() {
      return 0.03F;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public Block getBlock() {
      return this.block;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("xTile", (short)this.count16);
      tagCompound.put("yTile", (short)this.count14);
      tagCompound.put("zTile", (short)this.count11);
      ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(this.block);
      tagCompound.put("inTile", object == null ? "" : object.toString());
      tagCompound.put("shake", (byte)this.count13);
      tagCompound.put("inGround", (byte)(this.enabled11 ? 1 : 0));
      if ((this.labelText == null || this.labelText.length() == 0) && this.entityLivingBase instanceof EntityPlayer) {
         this.labelText = this.entityLivingBase.getText();
      }

      tagCompound.put("ownerName", this.labelText == null ? "" : this.labelText);
   }

   public int getCount14() {
      return this.count14;
   }

   public float getFactor2() {
      return 1.5F;
   }

   public EntityLivingBase getEntityLivingBase() {
      if (this.entityLivingBase == null && this.labelText != null && this.labelText.length() > 0) {
         this.entityLivingBase = this.worldObj.resolveEntityPlayerByText(this.labelText);
         if (this.entityLivingBase == null && this.worldObj instanceof WorldServer) {
            try {
               Entity entity = ((WorldServer)this.worldObj).get(UUID.fromString(this.labelText));
               if (entity instanceof EntityLivingBase) {
                  this.entityLivingBase = (EntityLivingBase)entity;
               }
            } catch (Throwable throwable) {
               this.entityLivingBase = null;
            }
         }
      }

      return this.entityLivingBase;
   }

   public int getCount15() {
      return this.count15;
   }

   public int getCount11() {
      return this.count11;
   }

   @Override
   public void updateState() {
      this.lastTickPosX = this.posX;
      this.lastTickPosY = this.posY;
      this.lastTickPosZ = this.posZ;
      super.updateState();
      if (this.count13 > 0) {
         this.count13--;
      }

      if (this.enabled11) {
         if (this.worldObj.getBlockState(new BlockPos(this.count16, this.count14, this.count11)).getBlock() == this.block) {
            this.count15++;
            if (this.count15 == 1200) {
               this.updateState9();
            }

            return;
         }

         this.enabled11 = false;
         this.motionX = this.motionX * (double)(this.random.nextFloat() * 0.2F);
         this.motionY = this.motionY * (double)(this.random.nextFloat() * 0.2F);
         this.motionZ = this.motionZ * (double)(this.random.nextFloat() * 0.2F);
         this.count15 = 0;
         this.count12 = 0;
      } else {
         this.count12++;
      }

      MovingObjectPosition movingObjectPosition = this.worldObj
         .getMovingObjectPosition2(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
      Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
      Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
      if (movingObjectPosition != null) {
         vec2 = new Vec3(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
      }

      if (!this.worldObj.state) {
         Entity entity = null;
         List list = this.worldObj.resolveListByEntity(this, this.getBoundingBox2().contract(this.motionX, this.motionY, this.motionZ).offset(1.0, 1.0, 1.0));
         double offset = 0.0;
         EntityLivingBase entityLivingBase2 = this.getEntityLivingBase();

         for (int index = 0; index < list.size(); index++) {
            Entity entity2 = (Entity)list.get(index);
            if (entity2.isActive5() && (entity2 != entityLivingBase2 || this.count12 >= 5)) {
               float factor = 0.3F;
               MovingObjectPosition hitResult = entity2.getBoundingBox2().offset((double)factor, (double)factor, (double)factor).calculateIntercept(vec, vec2);
               if (hitResult != null) {
                  double offset2 = vec.dotProduct(hitResult.hitVec);
                  if (offset2 < offset || offset == 0.0) {
                     entity = entity2;
                     offset = offset2;
                  }
               }
            }
         }

         if (entity != null) {
            movingObjectPosition = new MovingObjectPosition(entity);
         }
      }

      if (movingObjectPosition != null) {
         if (movingObjectPosition.typeOfHit == MovingObjectType.BLOCK && this.worldObj.getBlockState(movingObjectPosition.getBlockPos()).getBlock() == Blocks.blockPortal) {
            this.applyBlockPos(movingObjectPosition.getBlockPos());
         } else {
            this.applyMovingObjectPosition(movingObjectPosition);
         }
      }

      this.posX = this.posX + this.motionX;
      this.posY = this.posY + this.motionY;
      this.posZ = this.posZ + this.motionZ;
      float factor2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = (float)(MathHelper.getDouble6(this.motionX, this.motionZ) * 180.0 / Math.PI);
      this.rotationPitch = (float)(MathHelper.getDouble6(this.motionY, (double)factor2) * 180.0 / Math.PI);

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
      float factor3 = 0.99F;
      float factorAsFloat = this.getFactorAsFloat();
      if (this.isInWater()) {
         for (int index2 = 0; index2 < 4; index2++) {
            float factor4 = 0.25F;
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.WATER_BUBBLE,
                  this.posX - this.motionX * (double)factor4,
                  this.posY - this.motionY * (double)factor4,
                  this.posZ - this.motionZ * (double)factor4,
                  this.motionX,
                  this.motionY,
                  this.motionZ,
                  new int[0]
               );
         }

         factor3 = 0.8F;
      }

      this.motionX *= (double)factor3;
      this.motionY *= (double)factor3;
      this.motionZ *= (double)factor3;
      this.motionY -= (double)factorAsFloat;
      this.processDouble2(this.posX, this.posY, this.posZ);
   }

   @Override
   public void updateState7() {
   }

   public EntityThrowable(World world) {
      super(world);
      this.applyFloat(0.25F, 0.25F);
   }

   public EntityThrowable(World world, double posX, double posY, double posZ) {
      super(world);
      this.count15 = 0;
      this.applyFloat(0.25F, 0.25F);
      this.processDouble2(posX, posY, posZ);
   }

   public EntityThrowable(World world, EntityLivingBase livingEntity) {
      super(world);
      this.entityLivingBase = livingEntity;
      this.applyFloat(0.25F, 0.25F);
      this.applyDoubleByDouble(livingEntity.posX, livingEntity.posY + (double)livingEntity.getHeight2(), livingEntity.posZ, livingEntity.rotationYaw, livingEntity.rotationPitch);
      this.posX = this.posX - (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.posY -= 0.1F;
      this.posZ = this.posZ - (double)(MathHelper.abs(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.processDouble2(this.posX, this.posY, this.posZ);
      float factor = 0.4F;
      this.motionX = (double)(-MathHelper.abs(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * factor);
      this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * factor);
      this.motionY = (double)(-MathHelper.abs((this.rotationPitch + this.getFactor()) / 180.0F * (float) Math.PI) * factor);
      this.applyDouble(this.motionX, this.motionY, this.motionZ, this.getFactor2(), 1.0F);
   }
}

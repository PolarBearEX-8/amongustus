package net.minecraft.entity.projectile;

import client.setting.AttackEffectsExplosionNormalType;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityFireball extends Entity {
   public int count11 = -1;
   public int count15 = -1;
   public int count12 = -1;
   public Block block;
   public boolean enabled11;
   public EntityLivingBase entityLivingBase;
   public int count13;
   public int count14;
   public double lastTickPosZ2;
   public double coordOffset;
   public double prevPosZ2;

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
   public float getHeight() {
      return 1.0F;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count11 = tagCompound.processValue("xTile");
      this.count15 = tagCompound.processValue("yTile");
      this.count12 = tagCompound.processValue("zTile");
      if (tagCompound.matchesText("inTile", 8)) {
         this.block = Block.getBlockFromName(tagCompound.resolveText("inTile"));
      } else {
         this.block = Block.processAction3(tagCompound.computeByteCode("inTile") & 255);
      }

      this.enabled11 = tagCompound.computeByteCode("inGround") == 1;
      if (tagCompound.matchesText("direction", 9)) {
         NBTTagList extendedLoggerEntry = tagCompound.getExtendedLoggerEntry("direction", 6);
         this.motionX = extendedLoggerEntry.computeValue(0);
         this.motionY = extendedLoggerEntry.computeValue(1);
         this.motionZ = extendedLoggerEntry.computeValue(2);
      } else {
         this.updateState9();
      }
   }

   public float getFactor() {
      return 0.95F;
   }

   public abstract void applyMovingObjectPosition(MovingObjectPosition hitResult);

   @Override
   public float computeFactor(float factor) {
      return 1.0F;
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else {
         this.updateState8();
         if (damageSource.getEntity() != null) {
            Vec3 vecAsVec = damageSource.getEntity().getVecAsVec3();
            if (vecAsVec != null) {
               this.motionX = vecAsVec.xCoord;
               this.motionY = vecAsVec.yCoord;
               this.motionZ = vecAsVec.zCoord;
               this.lastTickPosZ2 = this.motionX * 0.1;
               this.coordOffset = this.motionY * 0.1;
               this.prevPosZ2 = this.motionZ * 0.1;
            }

            if (damageSource.getEntity() instanceof EntityLivingBase) {
               this.entityLivingBase = (EntityLivingBase)damageSource.getEntity();
            }

            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("xTile", (short)this.count11);
      tagCompound.put("yTile", (short)this.count15);
      tagCompound.put("zTile", (short)this.count12);
      ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(this.block);
      tagCompound.put("inTile", object == null ? "" : object.toString());
      tagCompound.put("inGround", (byte)(this.enabled11 ? 1 : 0));
      tagCompound.applyText("direction", this.resolveNBTTagList(new double[]{this.motionX, this.motionY, this.motionZ}));
   }

   @Override
   public boolean isActive5() {
      return true;
   }

   @Override
   public void updateState() {
      if (this.worldObj.state || (this.entityLivingBase == null || !this.entityLivingBase.isDead) && this.worldObj.checkBlockPos12(new BlockPos(this))) {
         super.updateState();
         this.applyIndexByIndex(1);
         if (this.enabled11) {
            if (this.worldObj.getBlockState(new BlockPos(this.count11, this.count15, this.count12)).getBlock() == this.block) {
               this.count13++;
               if (this.count13 == 600) {
                  this.updateState9();
               }

               return;
            }

            this.enabled11 = false;
            this.motionX = this.motionX * (double)(this.random.nextFloat() * 0.2F);
            this.motionY = this.motionY * (double)(this.random.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (double)(this.random.nextFloat() * 0.2F);
            this.count13 = 0;
            this.count14 = 0;
         } else {
            this.count14++;
         }

         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         MovingObjectPosition movingObjectPosition = this.worldObj.getMovingObjectPosition2(vec2.xCoord, vec2.yCoord, vec2.zCoord, vec.xCoord, vec.yCoord, vec.zCoord);
         Vec3 vec3 = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec4 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (movingObjectPosition != null) {
            vec4 = new Vec3(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
         }

         Entity entity = null;
         List list = this.worldObj.resolveListByEntity(this, this.getBoundingBox2().contract(this.motionX, this.motionY, this.motionZ).offset(1.0, 1.0, 1.0));
         double offset = 0.0;

         for (int index = 0; index < list.size(); index++) {
            Entity entity2 = (Entity)list.get(index);
            if (entity2.isActive5() && (!entity2.matchesEntity(this.entityLivingBase) || this.count14 >= 25)) {
               float factor = 0.3F;
               MovingObjectPosition hitResult = entity2.getBoundingBox2().offset((double)factor, (double)factor, (double)factor).calculateIntercept(vec3, vec4);
               if (hitResult != null) {
                  double offset2 = vec3.dotProduct(hitResult.hitVec);
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

         if (movingObjectPosition != null) {
            this.applyMovingObjectPosition(movingObjectPosition);
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float factor2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.rotationYaw = (float)(MathHelper.getDouble6(this.motionZ, this.motionX) * 180.0 / Math.PI) + 90.0F;
         this.rotationPitch = (float)(MathHelper.getDouble6((double)factor2, this.motionY) * 180.0 / Math.PI) - 90.0F;

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
         float factor3 = this.getFactor();
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

         this.motionX = this.motionX + this.lastTickPosZ2;
         this.motionY = this.motionY + this.coordOffset;
         this.motionZ = this.motionZ + this.prevPosZ2;
         this.motionX *= (double)factor3;
         this.motionY *= (double)factor3;
         this.motionZ *= (double)factor3;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0, new int[0]
            );
         this.processDouble2(this.posX, this.posY, this.posZ);
      } else {
         this.updateState9();
      }
   }

   @Override
   public void updateState7() {
   }

   public EntityFireball(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world);
      this.applyFloat(1.0F, 1.0F);
      this.applyDoubleByDouble(posX, posX2, posX3, this.rotationYaw, this.rotationPitch);
      this.processDouble2(posX, posX2, posX3);
      double offset = (double)MathHelper.sqrt_double(posX4 * posX4 + posY * posY + posZ * posZ);
      this.lastTickPosZ2 = posX4 / offset * 0.1;
      this.coordOffset = posY / offset * 0.1;
      this.prevPosZ2 = posZ / offset * 0.1;
   }

   public EntityFireball(World world) {
      super(world);
      this.applyFloat(1.0F, 1.0F);
   }

   public EntityFireball(World world, EntityLivingBase livingEntity, double posX, double posY, double posZ) {
      super(world);
      this.entityLivingBase = livingEntity;
      this.applyFloat(1.0F, 1.0F);
      this.applyDoubleByDouble(livingEntity.posX, livingEntity.posY, livingEntity.posZ, livingEntity.rotationYaw, livingEntity.rotationPitch);
      this.processDouble2(this.posX, this.posY, this.posZ);
      this.motionX = this.motionY = this.motionZ = 0.0;
      posX += this.random.nextGaussian() * 0.4;
      posY += this.random.nextGaussian() * 0.4;
      posZ += this.random.nextGaussian() * 0.4;
      double offset = (double)MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
      this.lastTickPosZ2 = posX / offset * 0.1;
      this.coordOffset = posY / offset * 0.1;
      this.prevPosZ2 = posZ / offset * 0.1;
   }
}

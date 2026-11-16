package net.minecraft.entity;

import client.event.PreMotionListener;
import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import client.util.LvlManager;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArrow extends Entity implements PreMotionListener {
   public int count16 = -1;
   public int count11 = -1;
   public int count17 = -1;
   public Block block;
   public int count18;
   public boolean enabled11;
   public int count12;
   public int count15;
   public Entity entity2;
   public int count14;
   public int count19;
   public double value6 = 2.0;
   public int count13;

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public void setCount13(int count) {
      this.count13 = count;
   }

   public void setEnabled5(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue | 1));
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue & -2));
      }
   }

   public int getCount17() {
      return this.count17;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count16 = tagCompound.processValue("xTile");
      this.count11 = tagCompound.processValue("yTile");
      this.count17 = tagCompound.processValue("zTile");
      this.count14 = tagCompound.processValue("life");
      if (tagCompound.matchesText("inTile", 8)) {
         this.block = Block.getBlockFromName(tagCompound.resolveText("inTile"));
      } else {
         this.block = Block.processAction3(tagCompound.computeByteCode("inTile") & 255);
      }

      this.count18 = tagCompound.computeByteCode("inData") & 255;
      this.count15 = tagCompound.computeByteCode("shake") & 255;
      this.enabled11 = tagCompound.computeByteCode("inGround") == 1;
      if (tagCompound.matchesText("damage", 99)) {
         this.value6 = tagCompound.computeValue("damage");
      }

      if (tagCompound.matchesText("pickup", 99)) {
         this.count12 = tagCompound.computeByteCode("pickup");
      } else if (tagCompound.matchesText("player", 99)) {
         this.count12 = (tagCompound.matchesText("player") ? 1 : 0);
      }
   }

   @Override
   public float getHeight2() {
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
         this.prevRotationPitch = this.rotationPitch;
         this.prevRotationYaw = this.rotationYaw;
         this.applyDoubleByDouble(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         this.count14 = 0;
      }
   }

   public Block getBlock() {
      return this.block;
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2) {
      float factor3 = MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
      double offset = posX / (double)factor3;
      double offset2 = posY / (double)factor3;
      double offset3 = posZ / (double)factor3;
      offset += this.random.nextGaussian() * (double)(this.random.nextBoolean() ? -1 : 1) * 0.0075F * (double)factor2;
      offset2 += this.random.nextGaussian() * (double)(this.random.nextBoolean() ? -1 : 1) * 0.0075F * (double)factor2;
      offset3 += this.random.nextGaussian() * (double)(this.random.nextBoolean() ? -1 : 1) * 0.0075F * (double)factor2;
      offset *= (double)factor;
      offset2 *= (double)factor;
      offset3 *= (double)factor;
      this.motionX = offset;
      this.motionY = offset2;
      this.motionZ = offset3;
      float factor4 = MathHelper.sqrt_double(offset * offset + offset3 * offset3);
      this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.getDouble6(offset, offset3) * 180.0 / Math.PI);
      this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.getDouble6(offset2, (double)factor4) * 180.0 / Math.PI);
      this.count14 = 0;
   }

   @Override
   public void applyEntityPlayer2(EntityPlayer player) {
      if (!this.worldObj.state && this.enabled11 && this.count15 <= 0) {
         boolean flag = this.count12 == 1 || this.count12 == 2 && player.invulnerableManager.enabled2;
         if (this.count12 == 1 && !player.inventoryPlayer.checkItemStack3(new ItemStack(Items.arrow, 1))) {
            flag = false;
         }

         if (flag) {
            this.applyText("random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.applyEntity2(this, 1);
            this.updateState9();
         }
      }
   }

   public void setValue6(double value) {
      this.value6 = value;
   }

   public int getCount16() {
      return this.count16;
   }

   public int getCount11() {
      return this.count11;
   }

   public int getCount19() {
      return this.count19;
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2, int count, boolean flag) {
      this.processDouble2(posX, posY, posZ);
      this.processFloat4(factor, factor2);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("xTile", (short)this.count16);
      tagCompound.put("yTile", (short)this.count11);
      tagCompound.put("zTile", (short)this.count17);
      tagCompound.put("life", (short)this.count14);
      ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(this.block);
      tagCompound.put("inTile", object == null ? "" : object.toString());
      tagCompound.put("inData", (byte)this.count18);
      tagCompound.put("shake", (byte)this.count15);
      tagCompound.put("inGround", (byte)(this.enabled11 ? 1 : 0));
      tagCompound.put("pickup", (byte)this.count12);
      tagCompound.put("damage", this.value6);
   }

   public boolean isActive16() {
      return (this.dataWatcher.computeByteCode(16) & 1) != 0;
   }

   public boolean isEnabled11() {
      return this.enabled11;
   }

   public double getValue6() {
      return this.value6;
   }

   public int getCount14() {
      return this.count14;
   }

   @Override
   public boolean isActive3() {
      return false;
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
         float factor = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.getDouble6(this.motionX, this.motionZ) * 180.0 / Math.PI);
         this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.getDouble6(this.motionY, (double)factor) * 180.0 / Math.PI);
      }

      BlockPos blockPos = new BlockPos(this.count16, this.count11, this.count17);
      IBlockState blockState = this.worldObj.getBlockState(blockPos);
      Block block2 = blockState.getBlock();
      if (block2.getMaterial() != Material.coreItemEntry) {
         block2.processAction(this.worldObj, blockPos.getX(), blockPos.getY(), blockPos.getZ());
         AxisAlignedBB boundingBox = block2.processAction(this.worldObj, blockState, blockPos.getX(), blockPos.getY(), blockPos.getZ());
         if (boundingBox != null && boundingBox.isVecInside(new Vec3(this.posX, this.posY, this.posZ))) {
            this.enabled11 = true;
         }
      }

      if (this.count15 > 0) {
         this.count15--;
      }

      if (this.enabled11) {
         int index = block2.getIndex(blockState);
         if (block2 == this.block && index == this.count18) {
            this.count14++;
            if (this.count14 >= 1200) {
               this.updateState9();
            }
         } else {
            this.enabled11 = false;
            this.motionX = this.motionX * (double)(this.random.nextFloat() * 0.2F);
            this.motionY = this.motionY * (double)(this.random.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (double)(this.random.nextFloat() * 0.2F);
            this.count14 = 0;
            this.count19 = 0;
         }
      } else {
         this.count19++;
         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         MovingObjectPosition movingObjectPosition = this.worldObj.getMovingObjectPosition2(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord);
         Vec3 vec3 = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec4 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (movingObjectPosition != null) {
            vec4 = new Vec3(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
         }

         Entity entity = null;
         List list = this.worldObj.resolveListByEntity(this, this.getBoundingBox2().contract(this.motionX, this.motionY, this.motionZ).offset(1.0, 1.0, 1.0));
         double offset = 0.0;

         for (int index2 = 0; index2 < list.size(); index2++) {
            Entity entity3 = (Entity)list.get(index2);
            if (entity3.isActive5() && (entity3 != this.entity2 || this.count19 >= 5)) {
               float factor2 = 0.3F;
               MovingObjectPosition hitResult = entity3.getBoundingBox2().offset((double)factor2, (double)factor2, (double)factor2).calculateIntercept(vec3, vec4);
               if (hitResult != null) {
                  double offset2 = vec3.dotProduct(hitResult.hitVec);
                  if (offset2 < offset || offset == 0.0) {
                     entity = entity3;
                     offset = offset2;
                  }
               }
            }
         }

         if (entity != null) {
            movingObjectPosition = new MovingObjectPosition(entity);
         }

         if (movingObjectPosition != null && movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityPlayer) {
            EntityPlayer entityHit = (EntityPlayer)movingObjectPosition.entityHit;
            if (entityHit.invulnerableManager.enabled || this.entity2 instanceof EntityPlayer && !((EntityPlayer)this.entity2).matchesEntityPlayer6(entityHit)) {
               movingObjectPosition = null;
            }
         }

         if (movingObjectPosition != null) {
            if (movingObjectPosition.entityHit != null) {
               int count = MathHelper.truncateDoubleToInt(
                  (double)MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ) * this.value6
               );
               if (this.isActive16()) {
                  count += this.random.nextInt(count / 2 + 2);
               }

               DamageSource damageSource;
               if (this.entity2 == null) {
                  damageSource = DamageSource.resolveDamageSourceByEntityArrow(this, this);
               } else {
                  damageSource = DamageSource.resolveDamageSourceByEntityArrow(this, this.entity2);
               }

               if (this.canExecute2() && !(movingObjectPosition.entityHit instanceof EntityEnderman)) {
                  movingObjectPosition.entityHit.applyIndexByIndex(5);
               }

               if (movingObjectPosition.entityHit.matchesDamageSource(damageSource, (float)count)) {
                  if (movingObjectPosition.entityHit instanceof EntityLivingBase) {
                     EntityLivingBase entityHit2 = (EntityLivingBase)movingObjectPosition.entityHit;
                     if (!this.worldObj.state) {
                        entityHit2.applyIndex6(entityHit2.getCount5() + 1);
                     }

                     if (this.count13 > 0) {
                        float factor3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                        if (factor3 > 0.0F) {
                           movingObjectPosition.entityHit
                              .applyDouble3(
                                 this.motionX * (double)this.count13 * 0.6F / (double)factor3, 0.1, this.motionZ * (double)this.count13 * 0.6F / (double)factor3
                              );
                        }
                     }

                     if (this.entity2 instanceof EntityLivingBase) {
                        LvlManager.applyEntityLivingBase(entityHit2, this.entity2);
                        LvlManager.applyEntityLivingBase2((EntityLivingBase)this.entity2, entityHit2);
                     }

                     if (this.entity2 != null
                        && movingObjectPosition.entityHit != this.entity2
                        && movingObjectPosition.entityHit instanceof EntityPlayer
                        && this.entity2 instanceof EntityPlayerAdapter) {
                        ((EntityPlayerAdapter)this.entity2).auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(6, 0.0F));
                     }
                  }

                  this.applyText("random.bowhit", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                  if (!(movingObjectPosition.entityHit instanceof EntityEnderman)) {
                     this.updateState9();
                  }
               } else {
                  this.motionX *= -0.1F;
                  this.motionY *= -0.1F;
                  this.motionZ *= -0.1F;
                  this.rotationYaw += 180.0F;
                  this.prevRotationYaw += 180.0F;
                  this.count19 = 0;
               }
            } else {
               BlockPos blockPos2 = movingObjectPosition.getBlockPos();
               this.count16 = blockPos2.getX();
               this.count11 = blockPos2.getY();
               this.count17 = blockPos2.getZ();
               IBlockState blockState2 = this.worldObj.getBlockState(blockPos2);
               this.block = blockState2.getBlock();
               this.count18 = this.block.getIndex(blockState2);
               this.motionX = (double)((float)(movingObjectPosition.hitVec.xCoord - this.posX));
               this.motionY = (double)((float)(movingObjectPosition.hitVec.yCoord - this.posY));
               this.motionZ = (double)((float)(movingObjectPosition.hitVec.zCoord - this.posZ));
               float factor4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
               this.posX = this.posX - this.motionX / (double)factor4 * 0.05F;
               this.posY = this.posY - this.motionY / (double)factor4 * 0.05F;
               this.posZ = this.posZ - this.motionZ / (double)factor4 * 0.05F;
               this.applyText("random.bowhit", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
               this.enabled11 = true;
               this.count15 = 7;
               this.setEnabled5(false);
               if (this.block.getMaterial() != Material.coreItemEntry) {
                  this.block.onEntityCollidedWithBlock(this.worldObj, blockPos2, blockState2, this);
               }
            }
         }

         if (this.isActive16()) {
            for (int index3 = 0; index3 < 4; index3++) {
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.CRIT,
                     this.posX + this.motionX * (double)index3 / 4.0,
                     this.posY + this.motionY * (double)index3 / 4.0,
                     this.posZ + this.motionZ * (double)index3 / 4.0,
                     -this.motionX,
                     -this.motionY + 0.2,
                     -this.motionZ,
                     new int[0]
                  );
            }
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float factor5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.rotationYaw = (float)(MathHelper.getDouble6(this.motionX, this.motionZ) * 180.0 / Math.PI);
         this.rotationPitch = (float)(MathHelper.getDouble6(this.motionY, (double)factor5) * 180.0 / Math.PI);

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
         float factor6 = 0.99F;
         float factor7 = 0.05F;
         if (this.isInWater()) {
            for (int index4 = 0; index4 < 4; index4++) {
               float factor8 = 0.25F;
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.WATER_BUBBLE,
                     this.posX - this.motionX * (double)factor8,
                     this.posY - this.motionY * (double)factor8,
                     this.posZ - this.motionZ * (double)factor8,
                     this.motionX,
                     this.motionY,
                     this.motionZ,
                     new int[0]
                  );
            }

            factor6 = 0.6F;
         }

         if (this.isActive11()) {
            this.updateState12();
         }

         this.motionX *= (double)factor6;
         this.motionY *= (double)factor6;
         this.motionZ *= (double)factor6;
         this.motionY -= (double)factor7;
         this.processDouble2(this.posX, this.posY, this.posZ);
         this.updateState13();
      }
   }

   @Override
   public void updateState7() {
      this.dataWatcher.applyTarget(16, (byte)0);
   }

   public EntityArrow(World world, EntityLivingBase livingEntity, EntityLivingBase livingEntity2, float factor, float factor2) {
      super(world);
      this.value3 = 10.0;
      this.entity2 = livingEntity;
      if (livingEntity instanceof EntityPlayer) {
         this.count12 = 1;
      }

      this.posY = livingEntity.posY + (double)livingEntity.getHeight2() - 0.1F;
      double offset = livingEntity2.posX - livingEntity.posX;
      double offset2 = livingEntity2.getBoundingBox2().minY + (double)(livingEntity2.height / 3.0F) - this.posY;
      double offset3 = livingEntity2.posZ - livingEntity.posZ;
      double offset4 = (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3);
      if (offset4 >= 1.0E-7) {
         float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI) - 90.0F;
         float factor4 = (float)(-(MathHelper.getDouble6(offset2, offset4) * 180.0 / Math.PI));
         double offset5 = offset / offset4;
         double offset6 = offset3 / offset4;
         this.applyDoubleByDouble(livingEntity.posX + offset5, this.posY, livingEntity.posZ + offset6, factor3, factor4);
         float factor5 = (float)(offset4 * 0.2F);
         this.applyDouble(offset, offset2 + (double)factor5, offset3, factor, factor2);
      }
   }

   public EntityArrow(World world, EntityLivingBase livingEntity, float factor) {
      super(world);
      this.value3 = 10.0;
      this.entity2 = livingEntity;
      if (livingEntity instanceof EntityPlayer) {
         this.count12 = 1;
      }

      this.applyFloat(0.5F, 0.5F);
      this.applyDoubleByDouble(livingEntity.posX, livingEntity.posY + (double)livingEntity.getHeight2(), livingEntity.posZ, livingEntity.rotationYaw, livingEntity.rotationPitch);
      this.posX = this.posX - (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.posY -= 0.1F;
      this.posZ = this.posZ - (double)(MathHelper.abs(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.processDouble2(this.posX, this.posY, this.posZ);
      this.motionX = (double)(-MathHelper.abs(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
      this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
      this.motionY = (double)(-MathHelper.abs(this.rotationPitch / 180.0F * (float) Math.PI));
      this.applyDouble(this.motionX, this.motionY, this.motionZ, factor * 1.5F, 1.0F);
   }

   public EntityArrow(World world, double posX, double posY, double posZ) {
      super(world);
      this.value3 = 10.0;
      this.applyFloat(0.5F, 0.5F);
      this.processDouble2(posX, posY, posZ);
   }

   public EntityArrow(World world) {
      super(world);
      this.value3 = 10.0;
      this.applyFloat(0.5F, 0.5F);
   }
}

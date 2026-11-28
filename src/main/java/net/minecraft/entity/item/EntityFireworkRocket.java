package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFireworkRocket extends Entity {
   private int fireworkAge;
   private int lifetime;

   public EntityFireworkRocket(World world, double d, double d2, double d3, ItemStack stack) {
      super(world);
      this.fireworkAge = 0;
      this.setSize(0.25F, 0.25F);
      this.setPosition(d, d2, d3);
      int n = 1;
      if (stack != null && stack.hasTagCompound()) {
         this.dataWatcher.updateObject(8, stack);
         NBTTagCompound compoundTag = stack.getTagCompound().getCompoundTag("Fireworks");
         if (compoundTag != null) {
            n += compoundTag.getByte("Flight");
         }
      }

      this.motionX = this.rand.nextGaussian() * 0.001;
      this.motionZ = this.rand.nextGaussian() * 0.001;
      this.motionY = 0.05;
      this.lifetime = 10 * n + this.rand.nextInt(6) + this.rand.nextInt(7);
   }

   @Override
   public boolean canAttackWithItem() {
      return false;
   }

   @Override
   public void onUpdate() {
      this.lastTickPosX = this.posX;
      this.lastTickPosY = this.posY;
      this.lastTickPosZ = this.posZ;
      super.onUpdate();
      this.motionX *= 1.15;
      this.motionZ *= 1.15;
      this.motionY += 0.04;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
      this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * 180.0 / Math.PI);

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
      if (this.fireworkAge == 0 && !this.isSilent()) {
         this.entityWorld.playSoundAtEntity(this, "fireworks.launch", 3.0F, 1.0F);
      }

      this.fireworkAge++;
      if (this.entityWorld.isRemote && this.fireworkAge % 2 < 2) {
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.FIREWORKS_SPARK,
               this.posX,
               this.posY - 0.3,
               this.posZ,
               this.rand.nextGaussian() * 0.05,
               -this.motionY * 0.5,
               this.rand.nextGaussian() * 0.05,
               new int[0]
            );
      }

      if (!this.entityWorld.isRemote && this.fireworkAge > this.lifetime) {
         this.entityWorld.setEntityState(this, (byte)17);
         this.setDead();
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setInteger("Life", this.fireworkAge);
      tag.setInteger("LifeTime", this.lifetime);
      ItemStack stack = this.dataWatcher.getWatchableObjectItemStack(8);
      if (stack != null) {
         NBTTagCompound tag2 = new NBTTagCompound();
         stack.writeToNBT(tag2);
         tag.setTag("FireworksItem", tag2);
      }
   }

   @Override
   public void handleStatusUpdate(byte b) {
      if (b == 17 && this.entityWorld.isRemote) {
         ItemStack stack = this.dataWatcher.getWatchableObjectItemStack(8);
         NBTTagCompound tag = null;
         if (stack != null && stack.hasTagCompound()) {
            tag = stack.getTagCompound().getCompoundTag("Fireworks");
         }

         this.entityWorld.makeFireworks(this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, tag);
      }

      super.handleStatusUpdate(b);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.fireworkAge = tag.getInteger("Life");
      this.lifetime = tag.getInteger("LifeTime");
      NBTTagCompound compoundTag = tag.getCompoundTag("FireworksItem");
      if (compoundTag != null) {
         ItemStack stack = ItemStack.loadItemStackFromNBT(compoundTag);
         if (stack != null) {
            this.dataWatcher.updateObject(8, stack);
         }
      }
   }

   @Override
   public void entityInit() {
      this.dataWatcher.addObjectByDataType(8, 5);
   }

   @Override
   public int getBrightnessForRender(float f) {
      return super.getBrightnessForRender(f);
   }

   public EntityFireworkRocket(World world) {
      super(world);
      this.setSize(0.25F, 0.25F);
   }

   @Override
   public void setVelocity(double d, double d2, double d3) {
      this.motionX = d;
      this.motionY = d2;
      this.motionZ = d3;
      if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
         float f = MathHelper.sqrt_double(d * d + d3 * d3);
         this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(d, d3) * 180.0 / Math.PI);
         this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(d2, (double)f) * 180.0 / Math.PI);
      }
   }

   @Override
   public float getBrightness(float f) {
      return super.getBrightness(f);
   }

   @Override
   public boolean isInRangeToRenderDist(double d) {
      return d < 4096.0;
   }
}

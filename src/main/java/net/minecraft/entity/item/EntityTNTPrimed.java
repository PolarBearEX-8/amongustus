package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityTNTPrimed extends Entity {
   public int fuse;
   private EntityLivingBase tntPlacedBy;

   @Override
   public float getEyeHeight() {
      return 0.0F;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setByte("Fuse", (byte)this.fuse);
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   public EntityLivingBase getTntPlacedBy() {
      return this.tntPlacedBy;
   }

   private void explode() {
      float f = 4.0F;
      this.entityWorld.createExplosion(this, this.posX, this.posY + (double)(this.height / 16.0F), this.posZ, f, true);
   }

   @Override
   public void entityInit() {
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.fuse = tag.getByte("Fuse");
   }

   public EntityTNTPrimed(World world) {
      super(world);
      this.preventEntitySpawning = true;
      this.setSize(0.98F, 0.98F);
   }

   public EntityTNTPrimed(World world, double d, double d2, double d3, EntityLivingBase entity) {
      this(world);
      this.setPosition(d, d2, d3);
      float f = (float)(Math.random() * Math.PI * 2.0);
      this.motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
      this.motionY = 0.2F;
      this.motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
      this.fuse = 80;
      this.prevPosX = d;
      this.prevPosY = d2;
      this.prevPosZ = d3;
      this.tntPlacedBy = entity;
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.motionY -= 0.04F;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.98F;
      this.motionY *= 0.98F;
      this.motionZ *= 0.98F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
         this.motionY *= -0.5;
      }

      if (this.fuse-- <= 0) {
         this.setDead();
         if (!this.entityWorld.isRemote) {
            this.explode();
         }
      } else {
         this.handleWaterMovement();
         this.entityWorld.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0, new int[0]);
      }
   }
}

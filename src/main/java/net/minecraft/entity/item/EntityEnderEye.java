package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityEnderEye extends Entity {
   private double targetX;
   private double targetY;
   private double targetZ;
   private int despawnTimer;
   private boolean shatterOrDrop;

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

   public EntityEnderEye(World world) {
      super(world);
      this.setSize(0.25F, 0.25F);
   }

   @Override
   public void entityInit() {
   }

   @Override
   public boolean isInRangeToRenderDist(double d) {
      double d2 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0;
      if (Double.isNaN(d2)) {
         d2 = 4.0;
      }

      d2 *= 64.0;
      return d < d2 * d2;
   }

   public EntityEnderEye(World world, double d, double d2, double d3) {
      super(world);
      this.despawnTimer = 0;
      this.setSize(0.25F, 0.25F);
      this.setPosition(d, d2, d3);
   }

   @Override
   public int getBrightnessForRender(float f) {
      return 15728880;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
   }

   @Override
   public float getBrightness(float f) {
      return 1.0F;
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
      this.posX = this.posX + this.motionX;
      this.posY = this.posY + this.motionY;
      this.posZ = this.posZ + this.motionZ;
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
      if (!this.entityWorld.isRemote) {
         double d = this.targetX - this.posX;
         double d2 = this.targetZ - this.posZ;
         float f2 = (float)Math.sqrt(d * d + d2 * d2);
         float f3 = (float)MathHelper.atan2(d2, d);
         double d3 = (double)f + (double)(f2 - f) * 0.0025;
         if (f2 < 1.0F) {
            d3 *= 0.8;
            this.motionY *= 0.8;
         }

         this.motionX = Math.cos((double)f3) * d3;
         this.motionZ = Math.sin((double)f3) * d3;
         if (this.posY < this.targetY) {
            this.motionY = this.motionY + (1.0 - this.motionY) * 0.015F;
         } else {
            this.motionY = this.motionY + (-1.0 - this.motionY) * 0.015F;
         }
      }

      float f4 = 0.25F;
      if (this.isInWater()) {
         for (int i = 0; i < 4; i++) {
            this.entityWorld
               .spawnParticle(
                  EnumParticleTypes.WATER_BUBBLE,
                  this.posX - this.motionX * (double)f4,
                  this.posY - this.motionY * (double)f4,
                  this.posZ - this.motionZ * (double)f4,
                  this.motionX,
                  this.motionY,
                  this.motionZ,
                  new int[0]
               );
         }
      } else {
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.PORTAL,
               this.posX - this.motionX * (double)f4 + this.rand.nextDouble() * 0.6 - 0.3,
               this.posY - this.motionY * (double)f4 - 0.5,
               this.posZ - this.motionZ * (double)f4 + this.rand.nextDouble() * 0.6 - 0.3,
               this.motionX,
               this.motionY,
               this.motionZ,
               new int[0]
            );
      }

      if (!this.entityWorld.isRemote) {
         this.setPosition(this.posX, this.posY, this.posZ);
         this.despawnTimer++;
         if (this.despawnTimer > 80 && !this.entityWorld.isRemote) {
            this.setDead();
            if (this.shatterOrDrop) {
               this.entityWorld.spawnEntityInWorld(new EntityItem(this.entityWorld, this.posX, this.posY, this.posZ, new ItemStack(Items.ender_eye)));
            } else {
               this.entityWorld.playAuxSFX(2003, new BlockPos(this), 0);
            }
         }
      }
   }

   public void moveTowards(BlockPos pos) {
      double x = (double)pos.getX();
      int y = pos.getY();
      double z = (double)pos.getZ();
      double d = x - this.posX;
      double d2 = z - this.posZ;
      float f = MathHelper.sqrt_double(d * d + d2 * d2);
      if (f > 12.0F) {
         this.targetX = this.posX + d / (double)f * 12.0;
         this.targetZ = this.posZ + d2 / (double)f * 12.0;
         this.targetY = this.posY + 8.0;
      } else {
         this.targetX = x;
         this.targetY = (double)y;
         this.targetZ = z;
      }

      this.despawnTimer = 0;
      this.shatterOrDrop = this.rand.nextInt(5) > 0;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
   }
}

package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoat extends Entity {
   private boolean isBoatEmpty = true;
   private double speedMultiplier = 0.07;
   private int boatPosRotationIncrements;
   private double boatX;
   private double boatY;
   private double boatZ;
   private double boatYaw;
   private double boatPitch;
   private double velocityX;
   private double velocityY;
   private double velocityZ;

   @Override
   public double getMountedYOffset() {
      return -0.3;
   }

   @Override
   public boolean interactFirst(EntityPlayer player) {
      if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player) {
         return true;
      } else {
         if (!this.entityWorld.isRemote) {
            player.mountEntity(this);
         }

         return true;
      }
   }

   public int getForwardDirection() {
      return this.dataWatcher.getWatchableObjectInt(18);
   }

   @Override
   public void setPositionAndRotation2(double d, double d2, double d3, float f, float f2, int n, boolean flag) {
      if (flag && this.riddenByEntity != null) {
         this.prevPosX = this.posX = d;
         this.prevPosY = this.posY = d2;
         this.prevPosZ = this.posZ = d3;
         this.rotationYaw = f;
         this.rotationPitch = f2;
         this.boatPosRotationIncrements = 0;
         this.setPosition(d, d2, d3);
         this.motionX = this.velocityX = 0.0;
         this.motionY = this.velocityY = 0.0;
         this.motionZ = this.velocityZ = 0.0;
      } else {
         if (this.isBoatEmpty) {
            this.boatPosRotationIncrements = n + 5;
         } else {
            double d4 = d - this.posX;
            double d5 = d2 - this.posY;
            double d6 = d3 - this.posZ;
            if ((d4 * d4 + d5 * d5 + d6 * d6) <= 1.0) {
               return;
            }

            this.boatPosRotationIncrements = 3;
         }

         this.boatX = d;
         this.boatY = d2;
         this.boatZ = d3;
         this.boatYaw = (double)f;
         this.boatPitch = (double)f2;
         this.motionX = this.velocityX;
         this.motionY = this.velocityY;
         this.motionZ = this.velocityZ;
      }
   }

   @Override
   public AxisAlignedBB getCollisionBox(Entity entity) {
      return entity.getEntityBoundingBox();
   }

   @Override
   public void setVelocity(double d, double d2, double d3) {
      this.velocityX = this.motionX = d;
      this.velocityY = this.motionY = d2;
      this.velocityZ = this.motionZ = d3;
   }

   @Override
   public void entityInit() {
      this.dataWatcher.addObject(17, 0);
      this.dataWatcher.addObject(18, 1);
      this.dataWatcher.addObject(19, 0.0F);
   }

   @Override
   public void performHurtAnimation() {
      this.setForwardDirection(-this.getForwardDirection());
      this.setTimeSinceHit(10);
      this.setDamageTaken(this.getDamageTaken() * 11.0F);
   }

   public void setDamageTaken(float f) {
      this.dataWatcher.updateObject(19, f);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
   }

   @Override
   public void updateFallState(double d, boolean flag, Block block, BlockPos pos) {
      if (flag) {
         if (this.fallDistance > 3.0F) {
            this.fall(this.fallDistance, 1.0F);
            if (!this.entityWorld.isRemote && !this.isDead) {
               this.setDead();
               if (this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
                  for (int i = 0; i < 3; i++) {
                     this.dropItemWithOffset(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
                  }

                  for (int j = 0; j < 2; j++) {
                     this.dropItemWithOffset(Items.stick, 1, 0.0F);
                  }
               }
            }

            this.fallDistance = 0.0F;
         }
      } else if (this.entityWorld.getBlockState(new BlockPos(this).down()).getBlock().getMaterial() != Material.water && d < 0.0) {
         this.fallDistance = (float)((double)this.fallDistance - d);
      }
   }

   public EntityBoat(World world) {
      super(world);
      this.preventEntitySpawning = true;
      this.setSize(1.5F, 0.6F);
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   public EntityBoat(World world, double d, double d2, double d3) {
      this(world);
      this.setPosition(d, d2, d3);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = d;
      this.prevPosY = d2;
      this.prevPosZ = d3;
   }

   @Override
   public void updateRiderPosition() {
      if (this.riddenByEntity != null) {
         double d = Math.cos((double)this.rotationYaw * Math.PI / 180.0) * 0.4;
         double d2 = Math.sin((double)this.rotationYaw * Math.PI / 180.0) * 0.4;
         this.riddenByEntity.setPosition(this.posX + d, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d2);
      }
   }

   public void setTimeSinceHit(int n) {
      this.dataWatcher.updateObject(17, n);
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else if (!this.entityWorld.isRemote && !this.isDead) {
         if (this.riddenByEntity != null && this.riddenByEntity == damageSource.getEntity() && damageSource instanceof EntityDamageSourceIndirect) {
            return false;
         } else {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + f * 10.0F);
            this.setBeenAttacked();
            boolean flag = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode;
            if (flag || this.getDamageTaken() > 40.0F) {
               if (this.riddenByEntity != null) {
                  this.riddenByEntity.mountEntity(this);
               }

               if (!flag && this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
                  this.dropItemWithOffset(Items.boat, 1, 0.0F);
               }

               this.setDead();
            }

            return true;
         }
      } else {
         this.setForwardDirection(-this.getForwardDirection());
         this.setTimeSinceHit(10);
         this.setDamageTaken(this.getDamageTaken() + f * 10.0F);
         this.setBeenAttacked();
         return true;
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox() {
      return this.getEntityBoundingBox();
   }

   public float getDamageTaken() {
      return this.dataWatcher.getWatchableObjectFloat(19);
   }

   public void setForwardDirection(int n) {
      this.dataWatcher.updateObject(18, n);
   }

   public int getTimeSinceHit() {
      return this.dataWatcher.getWatchableObjectInt(17);
   }

   @Override
   public boolean canBePushed() {
      return true;
   }

   @Override
   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.getTimeSinceHit() > 0) {
         this.setTimeSinceHit(this.getTimeSinceHit() - 1);
      }

      if (this.getDamageTaken() > 0.0F) {
         this.setDamageTaken(this.getDamageTaken() - 1.0F);
      }

      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      byte b = 5;
      double d = 0.0;

      for (int i = 0; i < b; i++) {
         double d2 = this.getEntityBoundingBox().minY
            + (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * (double)i / (double)b
            - 0.125;
         double d3 = this.getEntityBoundingBox().minY
            + (this.getEntityBoundingBox().maxY - this.getEntityBoundingBox().minY) * (double)(i + 1) / (double)b
            - 0.125;
         AxisAlignedBB box = new AxisAlignedBB(
            this.getEntityBoundingBox().minX, d2, this.getEntityBoundingBox().minZ, this.getEntityBoundingBox().maxX, d3, this.getEntityBoundingBox().maxZ
         );
         if (this.entityWorld.isAABBInMaterial(box, Material.water)) {
            d += 1.0 / (double)b;
         }
      }

      double d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      if (d4 > 0.2975) {
         double d5 = Math.cos((double)this.rotationYaw * Math.PI / 180.0);
         double d6 = Math.sin((double)this.rotationYaw * Math.PI / 180.0);

         for (int j = 0; (double)j < 1.0 + d4 * 60.0; j++) {
            double d7 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
            double d8 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7;
            if (this.rand.nextBoolean()) {
               double d9 = this.posX - d5 * d7 * 0.8 + d6 * d8;
               double d10 = this.posZ - d6 * d7 * 0.8 - d5 * d8;
               this.entityWorld.spawnParticle(EnumParticleTypes.WATER_SPLASH, d9, this.posY - 0.125, d10, this.motionX, this.motionY, this.motionZ, new int[0]);
            } else {
               double d11 = this.posX + d5 + d6 * d7 * 0.7;
               double d12 = this.posZ + d6 - d5 * d7 * 0.7;
               this.entityWorld.spawnParticle(EnumParticleTypes.WATER_SPLASH, d11, this.posY - 0.125, d12, this.motionX, this.motionY, this.motionZ, new int[0]);
            }
         }
      }

      if (this.entityWorld.isRemote && this.isBoatEmpty) {
         if (this.boatPosRotationIncrements > 0) {
            double d13 = this.posX + (this.boatX - this.posX) / (double)this.boatPosRotationIncrements;
            double d14 = this.posY + (this.boatY - this.posY) / (double)this.boatPosRotationIncrements;
            double d15 = this.posZ + (this.boatZ - this.posZ) / (double)this.boatPosRotationIncrements;
            double d16 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d16 / (double)this.boatPosRotationIncrements);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
            this.boatPosRotationIncrements--;
            this.setPosition(d13, d14, d15);
            this.setRotation(this.rotationYaw, this.rotationPitch);
         } else {
            this.setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            if (this.onGround) {
               this.motionX *= 0.5;
               this.motionY *= 0.5;
               this.motionZ *= 0.5;
            }

            this.motionX *= 0.99F;
            this.motionY *= 0.95F;
            this.motionZ *= 0.99F;
         }
      } else {
         if (d < 1.0) {
            double d20 = d * 2.0 - 1.0;
            this.motionY += 0.04F * d20;
         } else {
            if (this.motionY < 0.0) {
               this.motionY /= 2.0;
            }

            this.motionY += 0.007F;
         }

         if (this.riddenByEntity instanceof EntityLivingBase) {
            EntityLivingBase riddenByEntity2 = (EntityLivingBase)this.riddenByEntity;
            float f = this.riddenByEntity.rotationYaw + -riddenByEntity2.moveStrafing * 90.0F;
            this.motionX = this.motionX + -Math.sin((double)(f * (float) Math.PI / 180.0F)) * this.speedMultiplier * (double)riddenByEntity2.moveForward * 0.05F;
            this.motionZ = this.motionZ + Math.cos((double)(f * (float) Math.PI / 180.0F)) * this.speedMultiplier * (double)riddenByEntity2.moveForward * 0.05F;
         }

         double d21 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (d21 > 0.35) {
            double d22 = 0.35 / d21;
            this.motionX *= d22;
            this.motionZ *= d22;
            d21 = 0.35;
         }

         if (d21 > d4 && this.speedMultiplier < 0.35) {
            this.speedMultiplier = this.speedMultiplier + (0.35 - this.speedMultiplier) / 35.0;
            if (this.speedMultiplier > 0.35) {
               this.speedMultiplier = 0.35;
            }
         } else {
            this.speedMultiplier = this.speedMultiplier - (this.speedMultiplier - 0.07) / 35.0;
            if (this.speedMultiplier < 0.07) {
               this.speedMultiplier = 0.07;
            }
         }

         for (int k = 0; k < 4; k++) {
            int n = MathHelper.floor_double(this.posX + ((double)(k % 2) - 0.5) * 0.8);
            int n2 = MathHelper.floor_double(this.posZ + ((double)(k / 2) - 0.5) * 0.8);

            for (int m = 0; m < 2; m++) {
               int n3 = MathHelper.floor_double(this.posY) + m;
               BlockPos pos = new BlockPos(n, n3, n2);
               Block block = this.entityWorld.getBlockState(pos).getBlock();
               if (block == Blocks.snow_layer) {
                  this.entityWorld.setBlockToAir(pos);
                  this.isCollidedHorizontally = false;
               } else if (block == Blocks.waterlily) {
                  this.entityWorld.destroyBlock(pos, true);
                  this.isCollidedHorizontally = false;
               }
            }
         }

         if (this.onGround) {
            this.motionX *= 0.5;
            this.motionY *= 0.5;
            this.motionZ *= 0.5;
         }

         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         if (this.isCollidedHorizontally && d4 > 0.2975) {
            if (!this.entityWorld.isRemote && !this.isDead) {
               this.setDead();
               if (this.entityWorld.getGameRules().getBoolean("doEntityDrops")) {
                  for (int idx = 0; idx < 3; idx++) {
                     this.dropItemWithOffset(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
                  }

                  for (int index = 0; index < 2; index++) {
                     this.dropItemWithOffset(Items.stick, 1, 0.0F);
                  }
               }
            }
         } else {
            this.motionX *= 0.99F;
            this.motionY *= 0.95F;
            this.motionZ *= 0.99F;
         }

         this.rotationPitch = 0.0F;
         double rotationYaw2 = (double)this.rotationYaw;
         double d23 = this.prevPosX - this.posX;
         double d24 = this.prevPosZ - this.posZ;
         if (d23 * d23 + d24 * d24 > 0.001) {
            rotationYaw2 = (double)((float)(MathHelper.atan2(d24, d23) * 180.0 / Math.PI));
         }

         double d25 = MathHelper.wrapAngleTo180_double(rotationYaw2 - (double)this.rotationYaw);
         if (d25 > 20.0) {
            d25 = 20.0;
         }

         if (d25 < -20.0) {
            d25 = -20.0;
         }

         this.rotationYaw = (float)((double)this.rotationYaw + d25);
         this.setRotation(this.rotationYaw, this.rotationPitch);
         if (!this.entityWorld.isRemote) {
            List list = this.entityWorld.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(0.2F, 0.0, 0.2F));
            if (list != null && !list.isEmpty()) {
               for (int i2 = 0; i2 < list.size(); i2++) {
                  Entity entity = (Entity)list.get(i2);
                  if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBoat) {
                     entity.applyEntityCollision(this);
                  }
               }
            }

            if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
               this.riddenByEntity = null;
            }
         }
      }
   }

   public void setIsBoatEmpty(boolean flag) {
      this.isBoatEmpty = flag;
   }
}

package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityFireball extends Entity {
   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   private Block inTile;
   private boolean inGround;
   public EntityLivingBase shootingEntity;
   private int ticksAlive;
   private int ticksInAir;
   public double accelerationX;
   public double accelerationY;
   public double accelerationZ;

   public float getMotionFactor() {
      return 0.95F;
   }

   public EntityFireball(World world) {
      super(world);
      this.setSize(1.0F, 1.0F);
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

   @Override
   public boolean canBeCollidedWith() {
      return true;
   }

   @Override
   public float getBrightness(float f) {
      return 1.0F;
   }

   @Override
   public void entityInit() {
   }

   public EntityFireball(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world);
      this.setSize(1.0F, 1.0F);
      this.setLocationAndAngles(d, d2, d3, this.rotationYaw, this.rotationPitch);
      this.setPosition(d, d2, d3);
      double d7 = (double)MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6);
      this.accelerationX = d4 / d7 * 0.1;
      this.accelerationY = d5 / d7 * 0.1;
      this.accelerationZ = d6 / d7 * 0.1;
   }

   public EntityFireball(World world, EntityLivingBase entity, double d, double d2, double d3) {
      super(world);
      this.shootingEntity = entity;
      this.setSize(1.0F, 1.0F);
      this.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.motionX = this.motionY = this.motionZ = 0.0;
      d += this.rand.nextGaussian() * 0.4;
      d2 += this.rand.nextGaussian() * 0.4;
      d3 += this.rand.nextGaussian() * 0.4;
      double d4 = (double)MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
      this.accelerationX = d / d4 * 0.1;
      this.accelerationY = d2 / d4 * 0.1;
      this.accelerationZ = d3 / d4 * 0.1;
   }

   @Override
   public float getCollisionBorderSize() {
      return 1.0F;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.xTile = tag.getShort("xTile");
      this.yTile = tag.getShort("yTile");
      this.zTile = tag.getShort("zTile");
      if (tag.hasKey("inTile", 8)) {
         this.inTile = Block.getBlockFromName(tag.getString("inTile"));
      } else {
         this.inTile = Block.getBlockById(tag.getByte("inTile") & 255);
      }

      this.inGround = tag.getByte("inGround") == 1;
      if (tag.hasKey("direction", 9)) {
         NBTTagList tagList = tag.getTagList("direction", 6);
         this.motionX = tagList.getDoubleAt(0);
         this.motionY = tagList.getDoubleAt(1);
         this.motionZ = tagList.getDoubleAt(2);
      } else {
         this.setDead();
      }
   }

   public abstract void onImpact(MovingObjectPosition hit);

   @Override
   public int getBrightnessForRender(float f) {
      return 15728880;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setShort("xTile", (short)this.xTile);
      tag.setShort("yTile", (short)this.yTile);
      tag.setShort("zTile", (short)this.zTile);
      ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(this.inTile);
      tag.setString("inTile", nameForObject == null ? "" : nameForObject.toString());
      tag.setByte("inGround", (byte)(this.inGround ? 1 : 0));
      tag.setTag("direction", this.newDoubleNBTList(new double[]{this.motionX, this.motionY, this.motionZ}));
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else {
         this.setBeenAttacked();
         if (damageSource.getEntity() != null) {
            Vec3 lookVec = damageSource.getEntity().getLookVec();
            if (lookVec != null) {
               this.motionX = lookVec.xCoord;
               this.motionY = lookVec.yCoord;
               this.motionZ = lookVec.zCoord;
               this.accelerationX = this.motionX * 0.1;
               this.accelerationY = this.motionY * 0.1;
               this.accelerationZ = this.motionZ * 0.1;
            }

            if (damageSource.getEntity() instanceof EntityLivingBase) {
               this.shootingEntity = (EntityLivingBase)damageSource.getEntity();
            }

            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public void onUpdate() {
      if (this.entityWorld.isRemote || (this.shootingEntity == null || !this.shootingEntity.isDead) && this.entityWorld.isBlockLoaded(new BlockPos(this))) {
         super.onUpdate();
         this.setFire(1);
         if (this.inGround) {
            if (this.entityWorld.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile) {
               this.ticksAlive++;
               if (this.ticksAlive == 600) {
                  this.setDead();
               }

               return;
            }

            this.inGround = false;
            this.motionX = this.motionX * (double)(this.rand.nextFloat() * 0.2F);
            this.motionY = this.motionY * (double)(this.rand.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (double)(this.rand.nextFloat() * 0.2F);
            this.ticksAlive = 0;
            this.ticksInAir = 0;
         } else {
            this.ticksInAir++;
         }

         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         MovingObjectPosition hit = this.entityWorld.rayTraceBlocks(vec2.xCoord, vec2.yCoord, vec2.zCoord, vec.xCoord, vec.yCoord, vec.zCoord);
         vec = new Vec3(this.posX, this.posY, this.posZ);
         vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (hit != null) {
            vec2 = new Vec3(hit.hitVec.xCoord, hit.hitVec.yCoord, hit.hitVec.zCoord);
         }

         Entity entity = null;
         List list = this.entityWorld
            .getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
         double d = 0.0;

         for (int i = 0; i < list.size(); i++) {
            Entity entity2 = (Entity)list.get(i);
            if (entity2.canBeCollidedWith() && (!entity2.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25)) {
               float f = 0.3F;
               MovingObjectPosition hit2 = entity2.getEntityBoundingBox().expand((double)f, (double)f, (double)f).calculateIntercept(vec, vec2);
               if (hit2 != null) {
                  double d2 = vec.squareDistanceTo(hit2.hitVec);
                  if (d2 < d || d == 0.0) {
                     entity = entity2;
                     d = d2;
                  }
               }
            }
         }

         if (entity != null) {
            hit = new MovingObjectPosition(entity);
         }

         if (hit != null) {
            this.onImpact(hit);
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.rotationYaw = (float)(MathHelper.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) + 90.0F;
         this.rotationPitch = (float)(MathHelper.atan2((double)f2, this.motionY) * 180.0 / Math.PI) - 90.0F;

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
         float motionFactor = this.getMotionFactor();
         if (this.isInWater()) {
            for (int j = 0; j < 4; j++) {
               float f3 = 0.25F;
               this.entityWorld
                  .spawnParticle(
                     EnumParticleTypes.WATER_BUBBLE,
                     this.posX - this.motionX * (double)f3,
                     this.posY - this.motionY * (double)f3,
                     this.posZ - this.motionZ * (double)f3,
                     this.motionX,
                     this.motionY,
                     this.motionZ,
                     new int[0]
                  );
            }

            motionFactor = 0.8F;
         }

         this.motionX = this.motionX + this.accelerationX;
         this.motionY = this.motionY + this.accelerationY;
         this.motionZ = this.motionZ + this.accelerationZ;
         this.motionX *= (double)motionFactor;
         this.motionY *= (double)motionFactor;
         this.motionZ *= (double)motionFactor;
         this.entityWorld.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0, new int[0]);
         this.setPosition(this.posX, this.posY, this.posZ);
      } else {
         this.setDead();
      }
   }
}

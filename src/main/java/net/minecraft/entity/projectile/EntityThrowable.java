package net.minecraft.entity.projectile;

import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityThrowable extends Entity implements IProjectile {
   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   private Block inTile;
   public boolean inGround;
   public int throwableShake;
   private EntityLivingBase thrower;
   private String throwerName;
   private int ticksInGround;
   private int ticksInAir;

   public int getZTile() {
      return this.zTile;
   }

   @Override
   public void setThrowableHeading(double d, double d2, double d3, float f, float f2) {
      float f3 = MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
      d /= (double)f3;
      d2 /= (double)f3;
      d3 /= (double)f3;
      d += this.rand.nextGaussian() * 0.0075F * (double)f2;
      d2 += this.rand.nextGaussian() * 0.0075F * (double)f2;
      d3 += this.rand.nextGaussian() * 0.0075F * (double)f2;
      d *= (double)f;
      d2 *= (double)f;
      d3 *= (double)f;
      this.motionX = d;
      this.motionY = d2;
      this.motionZ = d3;
      float f4 = MathHelper.sqrt_double(d * d + d3 * d3);
      this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(d, d3) * 180.0 / Math.PI);
      this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(d2, (double)f4) * 180.0 / Math.PI);
      this.ticksInGround = 0;
   }

   @Override
   public void onUpdate() {
      this.lastTickPosX = this.posX;
      this.lastTickPosY = this.posY;
      this.lastTickPosZ = this.posZ;
      super.onUpdate();
      if (this.throwableShake > 0) {
         this.throwableShake--;
      }

      if (this.inGround) {
         if (this.entityWorld.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile) {
            this.ticksInGround++;
            if (this.ticksInGround == 1200) {
               this.setDead();
            }

            return;
         }

         this.inGround = false;
         this.motionX = this.motionX * (double)(this.rand.nextFloat() * 0.2F);
         this.motionY = this.motionY * (double)(this.rand.nextFloat() * 0.2F);
         this.motionZ = this.motionZ * (double)(this.rand.nextFloat() * 0.2F);
         this.ticksInGround = 0;
         this.ticksInAir = 0;
      } else {
         this.ticksInAir++;
      }

      MovingObjectPosition hit = this.entityWorld
         .rayTraceBlocks(this.posX, this.posY, this.posZ, this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
      Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
      Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
      if (hit != null) {
         vec2 = new Vec3(hit.hitVec.xCoord, hit.hitVec.yCoord, hit.hitVec.zCoord);
      }

      if (!this.entityWorld.isRemote) {
         Entity entity = null;
         List list = this.entityWorld
            .getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
         double d = 0.0;
         EntityLivingBase thrower2 = this.getThrower();

         for (int i = 0; i < list.size(); i++) {
            Entity entity2 = (Entity)list.get(i);
            if (entity2.canBeCollidedWith() && (entity2 != thrower2 || this.ticksInAir >= 5)) {
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
      }

      if (hit != null) {
         if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.entityWorld.getBlockState(hit.getBlockPos()).getBlock() == Blocks.portal) {
            this.setPortal(hit.getBlockPos());
         } else {
            this.onImpact(hit);
         }
      }

      this.posX = this.posX + this.motionX;
      this.posY = this.posY + this.motionY;
      this.posZ = this.posZ + this.motionZ;
      float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
      this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f2) * 180.0 / Math.PI);

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
      float f3 = 0.99F;
      float gravityVelocity = this.getGravityVelocity();
      if (this.isInWater()) {
         for (int j = 0; j < 4; j++) {
            float f4 = 0.25F;
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

         f3 = 0.8F;
      }

      this.motionX *= (double)f3;
      this.motionY *= (double)f3;
      this.motionZ *= (double)f3;
      this.motionY -= (double)gravityVelocity;
      this.setPosition(this.posX, this.posY, this.posZ);
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

      this.throwableShake = tag.getByte("shake") & 255;
      this.inGround = tag.getByte("inGround") == 1;
      this.thrower = null;
      this.throwerName = tag.getString("ownerName");
      if (this.throwerName != null && this.throwerName.length() == 0) {
         this.throwerName = null;
      }

      this.thrower = this.getThrower();
   }

   public int getXTile() {
      return this.xTile;
   }

   public boolean isInGround() {
      return this.inGround;
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

   public int getYTile() {
      return this.yTile;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setShort("xTile", (short)this.xTile);
      tag.setShort("yTile", (short)this.yTile);
      tag.setShort("zTile", (short)this.zTile);
      ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(this.inTile);
      tag.setString("inTile", nameForObject == null ? "" : nameForObject.toString());
      tag.setByte("shake", (byte)this.throwableShake);
      tag.setByte("inGround", (byte)(this.inGround ? 1 : 0));
      if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower instanceof EntityPlayer) {
         this.throwerName = this.thrower.getName();
      }

      tag.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
   }

   public String getThrowerName() {
      return this.throwerName;
   }

   public float getInaccuracy() {
      return 0.0F;
   }

   public float getVelocity() {
      return 1.5F;
   }

   public abstract void onImpact(MovingObjectPosition hit);

   public int getThrowableShake() {
      return this.throwableShake;
   }

   public float getGravityVelocity() {
      return 0.03F;
   }

   public int getTicksInGround() {
      return this.ticksInGround;
   }

   public EntityThrowable(World world, double d, double d2, double d3) {
      super(world);
      this.ticksInGround = 0;
      this.setSize(0.25F, 0.25F);
      this.setPosition(d, d2, d3);
   }

   @Override
   public void entityInit() {
   }

   public EntityThrowable(World world) {
      super(world);
      this.setSize(0.25F, 0.25F);
   }

   public EntityLivingBase getThrower() {
      if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
         this.thrower = this.entityWorld.getPlayerEntityByName(this.throwerName);
         if (this.thrower == null && this.entityWorld instanceof WorldServer) {
            try {
               Entity entityFromUuid = ((WorldServer)this.entityWorld).getEntityFromUuid(UUID.fromString(this.throwerName));
               if (entityFromUuid instanceof EntityLivingBase) {
                  this.thrower = (EntityLivingBase)entityFromUuid;
               }
            } catch (Throwable e) {
               this.thrower = null;
            }
         }
      }

      return this.thrower;
   }

   public EntityThrowable(World world, EntityLivingBase entity) {
      super(world);
      this.thrower = entity;
      this.setSize(0.25F, 0.25F);
      this.setLocationAndAngles(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
      this.posX = this.posX - (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.posY -= 0.1F;
      this.posZ = this.posZ - (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.setPosition(this.posX, this.posY, this.posZ);
      float f = 0.4F;
      this.motionX = (double)(
         -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f
      );
      this.motionZ = (double)(
         MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f
      );
      this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.getInaccuracy()) / 180.0F * (float) Math.PI) * f);
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getVelocity(), 1.0F);
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

   public Block getInTile() {
      return this.inTile;
   }

   public int getTicksInAir() {
      return this.ticksInAir;
   }
}

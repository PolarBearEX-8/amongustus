package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArrow extends Entity implements IProjectile {
   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   private Block inTile;
   private int inData;
   private boolean inGround;
   public int canBePickedUp;
   public int arrowShake;
   public Entity shootingEntity;
   private int ticksInGround;
   private int ticksInAir;
   private double damage = 2.0;
   private int knockbackStrength;

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
         float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
         this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * 180.0 / Math.PI);
      }

      BlockPos pos = new BlockPos(this.xTile, this.yTile, this.zTile);
      IBlockState blockState = this.entityWorld.getBlockState(pos);
      Block block = blockState.getBlock();
      if (block.getMaterial() != Material.air) {
         block.setBlockBoundsBasedOnState(this.entityWorld, pos.getX(), pos.getY(), pos.getZ());
         AxisAlignedBB collisionBoundingBox = block.getCollisionBoundingBox(this.entityWorld, blockState, pos.getX(), pos.getY(), pos.getZ());
         if (collisionBoundingBox != null && collisionBoundingBox.isVecInside(new Vec3(this.posX, this.posY, this.posZ))) {
            this.inGround = true;
         }
      }

      if (this.arrowShake > 0) {
         this.arrowShake--;
      }

      if (this.inGround) {
         int metaFromState = block.getMetaFromState(blockState);
         if (block == this.inTile && metaFromState == this.inData) {
            this.ticksInGround++;
            if (this.ticksInGround >= 1200) {
               this.setDead();
            }
         } else {
            this.inGround = false;
            this.motionX = this.motionX * (double)(this.rand.nextFloat() * 0.2F);
            this.motionY = this.motionY * (double)(this.rand.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (double)(this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         }
      } else {
         this.ticksInAir++;
         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         MovingObjectPosition hit = this.entityWorld.rayTraceBlocks(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord);
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
            if (entity2.canBeCollidedWith() && (entity2 != this.shootingEntity || this.ticksInAir >= 5)) {
               float f2 = 0.3F;
               MovingObjectPosition hit2 = entity2.getEntityBoundingBox().expand((double)f2, (double)f2, (double)f2).calculateIntercept(vec, vec2);
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

         if (hit != null && hit.entityHit != null && hit.entityHit instanceof EntityPlayer) {
            EntityPlayer entityHit = (EntityPlayer)hit.entityHit;
            if (entityHit.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityHit)) {
               hit = null;
            }
         }

         if (hit != null) {
            if (hit.entityHit != null) {
               int n = MathHelper.ceiling_double_int((double)MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ) * this.damage);
               if (this.getIsCritical()) {
                  n += this.rand.nextInt(n / 2 + 2);
               }

               DamageSource damageSource;
               if (this.shootingEntity == null) {
                  damageSource = DamageSource.causeArrowDamage(this, this);
               } else {
                  damageSource = DamageSource.causeArrowDamage(this, this.shootingEntity);
               }

               if (this.isBurning() && !(hit.entityHit instanceof EntityEnderman)) {
                  hit.entityHit.setFire(5);
               }

               if (hit.entityHit.attackEntityFrom(damageSource, (float)n)) {
                  if (hit.entityHit instanceof EntityLivingBase) {
                     EntityLivingBase entityHit2 = (EntityLivingBase)hit.entityHit;
                     if (!this.entityWorld.isRemote) {
                        entityHit2.setArrowCountInEntity(entityHit2.getArrowCountInEntity() + 1);
                     }

                     if (this.knockbackStrength > 0) {
                        float f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                        if (f4 > 0.0F) {
                           hit.entityHit
                              .addVelocity(
                                 this.motionX * (double)this.knockbackStrength * 0.6F / (double)f4,
                                 0.1,
                                 this.motionZ * (double)this.knockbackStrength * 0.6F / (double)f4
                              );
                        }
                     }

                     if (this.shootingEntity instanceof EntityLivingBase) {
                        EnchantmentHelper.applyThornEnchantments(entityHit2, this.shootingEntity);
                        EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, entityHit2);
                     }

                     if (this.shootingEntity != null
                        && hit.entityHit != this.shootingEntity
                        && hit.entityHit instanceof EntityPlayer
                        && this.shootingEntity instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
                     }
                  }

                  this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                  if (!(hit.entityHit instanceof EntityEnderman)) {
                     this.setDead();
                  }
               } else {
                  this.motionX *= -0.1F;
                  this.motionY *= -0.1F;
                  this.motionZ *= -0.1F;
                  this.rotationYaw += 180.0F;
                  this.prevRotationYaw += 180.0F;
                  this.ticksInAir = 0;
               }
            } else {
               BlockPos blockPos = hit.getBlockPos();
               this.xTile = blockPos.getX();
               this.yTile = blockPos.getY();
               this.zTile = blockPos.getZ();
               IBlockState blockState2 = this.entityWorld.getBlockState(blockPos);
               this.inTile = blockState2.getBlock();
               this.inData = this.inTile.getMetaFromState(blockState2);
               this.motionX = (double)((float)(hit.hitVec.xCoord - this.posX));
               this.motionY = (double)((float)(hit.hitVec.yCoord - this.posY));
               this.motionZ = (double)((float)(hit.hitVec.zCoord - this.posZ));
               float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
               this.posX = this.posX - this.motionX / (double)f5 * 0.05F;
               this.posY = this.posY - this.motionY / (double)f5 * 0.05F;
               this.posZ = this.posZ - this.motionZ / (double)f5 * 0.05F;
               this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
               this.inGround = true;
               this.arrowShake = 7;
               this.setIsCritical(false);
               if (this.inTile.getMaterial() != Material.air) {
                  this.inTile.onEntityCollidedWithBlock(this.entityWorld, blockPos, blockState2, this);
               }
            }
         }

         if (this.getIsCritical()) {
            for (int j = 0; j < 4; j++) {
               this.entityWorld
                  .spawnParticle(
                     EnumParticleTypes.CRIT,
                     this.posX + this.motionX * (double)j / 4.0,
                     this.posY + this.motionY * (double)j / 4.0,
                     this.posZ + this.motionZ * (double)j / 4.0,
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
         float f6 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
         this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f6) * 180.0 / Math.PI);

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
         float f7 = 0.99F;
         float f8 = 0.05F;
         if (this.isInWater()) {
            for (int k = 0; k < 4; k++) {
               float f9 = 0.25F;
               this.entityWorld
                  .spawnParticle(
                     EnumParticleTypes.WATER_BUBBLE,
                     this.posX - this.motionX * (double)f9,
                     this.posY - this.motionY * (double)f9,
                     this.posZ - this.motionZ * (double)f9,
                     this.motionX,
                     this.motionY,
                     this.motionZ,
                     new int[0]
                  );
            }

            f7 = 0.6F;
         }

         if (this.isWet()) {
            this.extinguish();
         }

         this.motionX *= (double)f7;
         this.motionY *= (double)f7;
         this.motionZ *= (double)f7;
         this.motionY -= (double)f8;
         this.setPosition(this.posX, this.posY, this.posZ);
         this.doBlockCollisions();
      }
   }

   public int getXTile() {
      return this.xTile;
   }

   public boolean getIsCritical() {
      return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      tag.setShort("xTile", (short)this.xTile);
      tag.setShort("yTile", (short)this.yTile);
      tag.setShort("zTile", (short)this.zTile);
      tag.setShort("life", (short)this.ticksInGround);
      ResourceLocation nameForObject = (ResourceLocation)Block.blockRegistry.getNameForObject(this.inTile);
      tag.setString("inTile", nameForObject == null ? "" : nameForObject.toString());
      tag.setByte("inData", (byte)this.inData);
      tag.setByte("shake", (byte)this.arrowShake);
      tag.setByte("inGround", (byte)(this.inGround ? 1 : 0));
      tag.setByte("pickup", (byte)this.canBePickedUp);
      tag.setDouble("damage", this.damage);
   }

   public void setKnockbackStrength(int n) {
      this.knockbackStrength = n;
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   public EntityArrow(World world, EntityLivingBase entity, EntityLivingBase entity2, float f, float f2) {
      super(world);
      this.renderDistanceWeight = 10.0;
      this.shootingEntity = entity;
      if (entity instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      this.posY = entity.posY + (double)entity.getEyeHeight() - 0.1F;
      double d = entity2.posX - entity.posX;
      double d2 = entity2.getEntityBoundingBox().minY + (double)(entity2.height / 3.0F) - this.posY;
      double d3 = entity2.posZ - entity.posZ;
      double d4 = (double)MathHelper.sqrt_double(d * d + d3 * d3);
      if (d4 >= 1.0E-7) {
         float f3 = (float)(MathHelper.atan2(d3, d) * 180.0 / Math.PI) - 90.0F;
         float f4 = (float)(-(MathHelper.atan2(d2, d4) * 180.0 / Math.PI));
         double d5 = d / d4;
         double d6 = d3 / d4;
         this.setLocationAndAngles(entity.posX + d5, this.posY, entity.posZ + d6, f3, f4);
         this.setThrowableHeading(d, d2 + (double)((float)(d4 * 0.2F)), d3, f, f2);
      }
   }

   public double getDamage() {
      return this.damage;
   }

   @Override
   public float getEyeHeight() {
      return 0.0F;
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
         this.prevRotationPitch = this.rotationPitch;
         this.prevRotationYaw = this.rotationYaw;
         this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         this.ticksInGround = 0;
      }
   }

   public boolean isInGround() {
      return this.inGround;
   }

   public int getYTile() {
      return this.yTile;
   }

   @Override
   public boolean canAttackWithItem() {
      return false;
   }

   public Block getInTile() {
      return this.inTile;
   }

   @Override
   public void onCollideWithPlayer(EntityPlayer player) {
      if (!this.entityWorld.isRemote && this.inGround && this.arrowShake <= 0) {
         boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && player.capabilities.isCreativeMode;
         if (this.canBePickedUp == 1 && !player.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1))) {
            flag = false;
         }

         if (flag) {
            this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.onItemPickup(this, 1);
            this.setDead();
         }
      }
   }

   public int getZTile() {
      return this.zTile;
   }

   public int getTicksInGround() {
      return this.ticksInGround;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      this.xTile = tag.getShort("xTile");
      this.yTile = tag.getShort("yTile");
      this.zTile = tag.getShort("zTile");
      this.ticksInGround = tag.getShort("life");
      if (tag.hasKey("inTile", 8)) {
         this.inTile = Block.getBlockFromName(tag.getString("inTile"));
      } else {
         this.inTile = Block.getBlockById(tag.getByte("inTile") & 255);
      }

      this.inData = tag.getByte("inData") & 255;
      this.arrowShake = tag.getByte("shake") & 255;
      this.inGround = tag.getByte("inGround") == 1;
      if (tag.hasKey("damage", 99)) {
         this.damage = tag.getDouble("damage");
      }

      if (tag.hasKey("pickup", 99)) {
         this.canBePickedUp = tag.getByte("pickup");
      } else if (tag.hasKey("player", 99)) {
         this.canBePickedUp = tag.getBoolean("player") ? 1 : 0;
      }
   }

   public EntityArrow(World world) {
      super(world);
      this.renderDistanceWeight = 10.0;
      this.setSize(0.5F, 0.5F);
   }

   public void setDamage(double d) {
      this.damage = d;
   }

   @Override
   public void setPositionAndRotation2(double d, double d2, double d3, float f, float f2, int n, boolean flag) {
      this.setPosition(d, d2, d3);
      this.setRotation(f, f2);
   }

   public void setIsCritical(boolean flag) {
      byte watchableObjectByte = this.dataWatcher.getWatchableObjectByte(16);
      if (flag) {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte | 1));
      } else {
         this.dataWatcher.updateObject(16, (byte)(watchableObjectByte & -2));
      }
   }

   @Override
   public void entityInit() {
      this.dataWatcher.addObject(16, (byte)0);
   }

   @Override
   public void setThrowableHeading(double d, double d2, double d3, float f, float f2) {
      float f3 = MathHelper.sqrt_double(d * d + d2 * d2 + d3 * d3);
      d /= (double)f3;
      d2 /= (double)f3;
      d3 /= (double)f3;
      d += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.0075F * (double)f2;
      d2 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.0075F * (double)f2;
      d3 += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.0075F * (double)f2;
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

   public EntityArrow(World world, double d, double d2, double d3) {
      super(world);
      this.renderDistanceWeight = 10.0;
      this.setSize(0.5F, 0.5F);
      this.setPosition(d, d2, d3);
   }

   public EntityArrow(World world, EntityLivingBase entity, float f) {
      super(world);
      this.renderDistanceWeight = 10.0;
      this.shootingEntity = entity;
      if (entity instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      this.setSize(0.5F, 0.5F);
      this.setLocationAndAngles(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
      this.posX = this.posX - (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.posY -= 0.1F;
      this.posZ = this.posZ - (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
      this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
      this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, f * 1.5F, 1.0F);
   }

   public int getTicksInAir() {
      return this.ticksInAir;
   }
}

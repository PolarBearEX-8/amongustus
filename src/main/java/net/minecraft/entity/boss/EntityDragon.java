package net.minecraft.entity.boss;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityDragon extends EntityLiving implements IBossDisplayData, IEntityMultiPart, IMob {
   public double targetX;
   public double targetY;
   public double targetZ;
   public double[][] ringBuffer = new double[64][3];
   public int ringBufferIndex = -1;
   public EntityDragonPart[] dragonPartArray;
   public EntityDragonPart dragonPartHead;

   @Override
   public float getHealth() { return this.getCurrentHealth(); }

   @Override
   public float getBossMaxHealth() { return this.getMaxHealth(); }

   @Override
   public net.minecraft.util.IChatComponent getBossDisplayName() { return this.getDisplayName(); }
   public EntityDragonPart dragonPartBody;
   public EntityDragonPart dragonPartTail1;
   public EntityDragonPart dragonPartTail2;
   public EntityDragonPart dragonPartTail3;
   public EntityDragonPart dragonPartWing1;
   public EntityDragonPart dragonPartWing2;
   public float prevAnimTime;
   public float animTime;
   public boolean forceNewTarget;
   public boolean slowed;
   private Entity target;
   public int deathTicks;
   public EntityEnderCrystal healingEnderCrystal;

   @Override
   public boolean attackEntityFromPart(EntityDragonPart entityDragonPart, DamageSource damageSource, float f) {
      if (entityDragonPart != this.dragonPartHead) {
         f = f / 4.0F + 1.0F;
      }

      float f2 = this.rotationYaw * (float) Math.PI / 180.0F;
      float f3 = MathHelper.sin(f2);
      float f4 = MathHelper.cos(f2);
      this.targetX = this.posX + (double)(f3 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
      this.targetY = this.posY + (double)(this.rand.nextFloat() * 3.0F) + 1.0;
      this.targetZ = this.posZ - (double)(f4 * 5.0F) + (double)((this.rand.nextFloat() - 0.5F) * 2.0F);
      this.target = null;
      if (damageSource.getEntity() instanceof EntityPlayer || damageSource.isExplosion()) {
         this.attackDragonFrom(damageSource, f);
      }

      return true;
   }

   private void updateDragonEnderCrystal() {
      if (this.healingEnderCrystal != null) {
         if (this.healingEnderCrystal.isDead) {
            if (!this.entityWorld.isRemote) {
               this.attackEntityFromPart(this.dragonPartHead, DamageSource.setExplosionSource((Explosion)null), 10.0F);
            }

            this.healingEnderCrystal = null;
         } else if (this.ticksExisted % 10 == 0 && this.getHealth() < this.getBossMaxHealth()) {
            this.setHealth(this.getHealth() + 1.0F);
         }
      }

      if (this.rand.nextInt(10) == 0) {
         float f = 32.0F;
         List entitiesWithinAABB = this.entityWorld.getEntitiesWithinAABB(EntityEnderCrystal.class, this.getEntityBoundingBox().expand((double)f, (double)f, (double)f));
         EntityEnderCrystal entityEnderCrystal = null;
         double maxvalue = Double.MAX_VALUE;

         for (EntityEnderCrystal entityEnderCrystal2 : (Iterable<EntityEnderCrystal>)(entitiesWithinAABB)) {
            double distanceSqToEntity = entityEnderCrystal2.getDistanceSqToEntity(this);
            if (distanceSqToEntity < maxvalue) {
               maxvalue = distanceSqToEntity;
               entityEnderCrystal = entityEnderCrystal2;
            }
         }

         this.healingEnderCrystal = entityEnderCrystal;
      }
   }

   private void generatePortal(BlockPos pos) {
      double d = 12.25;
      double d2 = 6.25;

      for (int i = -1; i <= 32; i++) {
         for (int j = -4; j <= 4; j++) {
            for (int k = -4; k <= 4; k++) {
               double d3 = (double)(j * j + k * k);
               if (d3 <= 12.25) {
                  BlockPos pos2 = pos.add(j, i, k);
                  if (i < 0) {
                     if (d3 <= 6.25) {
                        this.entityWorld.setBlockState(pos2, Blocks.bedrock.getDefaultState());
                     }
                  } else if (i > 0) {
                     this.entityWorld.setBlockState(pos2, Blocks.air.getDefaultState());
                  } else if (d3 > 6.25) {
                     this.entityWorld.setBlockState(pos2, Blocks.bedrock.getDefaultState());
                  } else {
                     this.entityWorld.setBlockState(pos2, Blocks.end_portal.getDefaultState());
                  }
               }
            }
         }
      }

      this.entityWorld.setBlockState(pos, Blocks.bedrock.getDefaultState());
      this.entityWorld.setBlockState(pos.up(), Blocks.bedrock.getDefaultState());
      BlockPos pos3 = pos.up(2);
      this.entityWorld.setBlockState(pos3, Blocks.bedrock.getDefaultState());
      this.entityWorld.setBlockState(pos3.west(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST));
      this.entityWorld.setBlockState(pos3.east(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST));
      this.entityWorld.setBlockState(pos3.north(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH));
      this.entityWorld.setBlockState(pos3.south(), Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH));
      this.entityWorld.setBlockState(pos.up(3), Blocks.bedrock.getDefaultState());
      this.entityWorld.setBlockState(pos.up(4), Blocks.dragon_egg.getDefaultState());
   }

   private boolean destroyBlocksInAABB(AxisAlignedBB box) {
      int n = MathHelper.floor_double(box.minX);
      int n2 = MathHelper.floor_double(box.minY);
      int n3 = MathHelper.floor_double(box.minZ);
      int n4 = MathHelper.floor_double(box.maxX);
      int n5 = MathHelper.floor_double(box.maxY);
      int n6 = MathHelper.floor_double(box.maxZ);
      boolean flag = false;
      boolean flag2 = false;

      for (int i = n; i <= n4; i++) {
         for (int j = n2; j <= n5; j++) {
            for (int k = n3; k <= n6; k++) {
               BlockPos pos = new BlockPos(i, j, k);
               Block block = this.entityWorld.getBlockState(pos).getBlock();
               if (block.getMaterial() != Material.air) {
                  if (block != Blocks.barrier
                     && block != Blocks.obsidian
                     && block != Blocks.end_stone
                     && block != Blocks.bedrock
                     && block != Blocks.command_block
                     && this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
                     flag2 = this.entityWorld.setBlockToAir(pos) || flag2;
                  } else {
                     flag = true;
                  }
               }
            }
         }
      }

      if (flag2) {
         double d = box.minX + (box.maxX - box.minX) * (double)this.rand.nextFloat();
         double d2 = box.minY + (box.maxY - box.minY) * (double)this.rand.nextFloat();
         double d3 = box.minZ + (box.maxZ - box.minZ) * (double)this.rand.nextFloat();
         this.entityWorld.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
      }

      return flag;
   }

   private float simplifyAngle(double d) {
      return (float)MathHelper.wrapAngleTo180_double(d);
   }

   private void attackEntitiesInList(List list) {
      for (int i = 0; i < list.size(); i++) {
         Entity entity = (Entity)list.get(i);
         if (entity instanceof EntityLivingBase) {
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0F);
            this.applyEnchantments(this, entity);
         }
      }
   }

   @Override
   public Entity[] getParts() {
      return this.dragonPartArray;
   }

   @Override
   public String getHurtSound() {
      return "mob.enderdragon.hit";
   }

   public boolean attackDragonFrom(DamageSource damageSource, float f) {
      return super.attackEntityFrom(damageSource, f);
   }

   @Override
   public float getSoundVolume() {
      return 5.0F;
   }

   @Override
   public void onLivingUpdate() {
      if (this.entityWorld.isRemote) {
         float f = MathHelper.cos(this.animTime * (float) Math.PI * 2.0F);
         if (MathHelper.cos(this.prevAnimTime * (float) Math.PI * 2.0F) <= -0.3F && f >= -0.3F && !this.isSilent()) {
            this.entityWorld.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
         }
      }

      this.prevAnimTime = this.animTime;
      if (this.getHealth() <= 0.0F) {
         float f3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
         float f4 = (this.rand.nextFloat() - 0.5F) * 4.0F;
         float f5 = (this.rand.nextFloat() - 0.5F) * 8.0F;
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.EXPLOSION_LARGE,
               this.posX + (double)f3,
               this.posY + 2.0 + (double)f4,
               this.posZ + (double)f5,
               0.0,
               0.0,
               0.0,
               new int[0]
            );
      } else {
         this.updateDragonEnderCrystal();
         float f6 = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
         f6 *= (float)Math.pow(2.0, this.motionY);
         if (this.slowed) {
            this.animTime += f6 * 0.5F;
         } else {
            this.animTime += f6;
         }

         this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
         if (this.isAIDisabled()) {
            this.animTime = 0.5F;
         } else {
            if (this.ringBufferIndex < 0) {
               for (int i = 0; i < this.ringBuffer.length; i++) {
                  this.ringBuffer[i][0] = (double)this.rotationYaw;
                  this.ringBuffer[i][1] = this.posY;
               }
            }

            if (++this.ringBufferIndex == this.ringBuffer.length) {
               this.ringBufferIndex = 0;
            }

            this.ringBuffer[this.ringBufferIndex][0] = (double)this.rotationYaw;
            this.ringBuffer[this.ringBufferIndex][1] = this.posY;
            if (this.entityWorld.isRemote) {
               if (this.newPosRotationIncrements > 0) {
                  double d = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
                  double d2 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
                  double d3 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
                  double d4 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
                  this.rotationYaw = (float)((double)this.rotationYaw + d4 / (double)this.newPosRotationIncrements);
                  this.rotationPitch = (float)(
                     (double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements
                  );
                  this.newPosRotationIncrements--;
                  this.setPosition(d, d2, d3);
                  this.setRotation(this.rotationYaw, this.rotationPitch);
               }
            } else {
               double d5 = this.targetX - this.posX;
               double d6 = this.targetY - this.posY;
               double d7 = this.targetZ - this.posZ;
               double d8 = d5 * d5 + d6 * d6 + d7 * d7;
               if (this.target != null) {
                  this.targetX = this.target.posX;
                  this.targetZ = this.target.posZ;
                  double d9 = this.targetX - this.posX;
                  double d10 = this.targetZ - this.posZ;
                  double d11 = Math.sqrt(d9 * d9 + d10 * d10);
                  double d12 = 0.4F + d11 / 80.0 - 1.0;
                  if (d12 > 10.0) {
                     d12 = 10.0;
                  }

                  this.targetY = this.target.getEntityBoundingBox().minY + d12;
               } else {
                  this.targetX = this.targetX + this.rand.nextGaussian() * 2.0;
                  this.targetZ = this.targetZ + this.rand.nextGaussian() * 2.0;
               }

               if (this.forceNewTarget || d8 < 100.0 || d8 > 22500.0 || this.isCollidedHorizontally || this.isCollidedVertically) {
                  this.setNewTarget();
               }

               d6 /= (double)MathHelper.sqrt_double(d5 * d5 + d7 * d7);
               float f7 = 0.6F;
               d6 = MathHelper.clamp_double(d6, (double)(-f7), (double)f7);
               this.motionY += d6 * 0.1F;
               this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
               double d14 = MathHelper.wrapAngleTo180_double((180.0 - MathHelper.atan2(d5, d7) * 180.0 / Math.PI) - (double)this.rotationYaw);
               if (d14 > 50.0) {
                  d14 = 50.0;
               }

               if (d14 < -50.0) {
                  d14 = -50.0;
               }

               Vec3 vec = new Vec3(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ).normalize();
               double d15 = (double)(-MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F));
               Vec3 vec2 = new Vec3((double)MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F), this.motionY, d15).normalize();
               float f8 = ((float)vec2.dotProduct(vec) + 0.5F) / 1.5F;
               if (f8 < 0.0F) {
                  f8 = 0.0F;
               }

               this.randomYawVelocity *= 0.8F;
               float f9 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
               double d16 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0 + 1.0;
               if (d16 > 40.0) {
                  d16 = 40.0;
               }

               this.randomYawVelocity = (float)((double)this.randomYawVelocity + d14 * (0.7F / d16 / (double)f9));
               this.rotationYaw = this.rotationYaw + this.randomYawVelocity * 0.1F;
               float f10 = (float)(2.0 / (d16 + 1.0));
               float f11 = 0.06F;
               this.moveFlying(0.0F, -1.0F, f11 * (f8 * f10 + (1.0F - f10)));
               if (this.slowed) {
                  this.moveEntity(this.motionX * 0.8F, this.motionY * 0.8F, this.motionZ * 0.8F);
               } else {
                  this.moveEntity(this.motionX, this.motionY, this.motionZ);
               }

               float f12 = ((float)new Vec3(this.motionX, this.motionY, this.motionZ).normalize().dotProduct(vec2) + 1.0F) / 2.0F;
               f12 = 0.8F + 0.15F * f12;
               this.motionX *= (double)f12;
               this.motionZ *= (double)f12;
               this.motionY *= 0.91F;
            }

            this.renderYawOffset = this.rotationYaw;
            this.dragonPartHead.width = this.dragonPartHead.height = 3.0F;
            this.dragonPartTail1.width = this.dragonPartTail1.height = 2.0F;
            this.dragonPartTail2.width = this.dragonPartTail2.height = 2.0F;
            this.dragonPartTail3.width = this.dragonPartTail3.height = 2.0F;
            this.dragonPartBody.height = 3.0F;
            this.dragonPartBody.width = 5.0F;
            this.dragonPartWing1.height = 2.0F;
            this.dragonPartWing1.width = 4.0F;
            this.dragonPartWing2.height = 3.0F;
            this.dragonPartWing2.width = 4.0F;
            float f13 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * (float) Math.PI;
            float f14 = MathHelper.cos(f13);
            float f15 = -MathHelper.sin(f13);
            float f16 = this.rotationYaw * (float) Math.PI / 180.0F;
            float f17 = MathHelper.sin(f16);
            float f18 = MathHelper.cos(f16);
            this.dragonPartBody.onUpdate();
            this.dragonPartBody.setLocationAndAngles(this.posX + (double)(f17 * 0.5F), this.posY, this.posZ - (double)(f18 * 0.5F), 0.0F, 0.0F);
            this.dragonPartWing1.onUpdate();
            this.dragonPartWing1.setLocationAndAngles(this.posX + (double)(f18 * 4.5F), this.posY + 2.0, this.posZ + (double)(f17 * 4.5F), 0.0F, 0.0F);
            this.dragonPartWing2.onUpdate();
            this.dragonPartWing2.setLocationAndAngles(this.posX - (double)(f18 * 4.5F), this.posY + 2.0, this.posZ - (double)(f17 * 4.5F), 0.0F, 0.0F);
            if (!this.entityWorld.isRemote && this.hurtTime == 0) {
               this.collideWithEntities(
                  this.entityWorld
                     .getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing1.getEntityBoundingBox().expand(4.0, 2.0, 4.0).offset(0.0, -2.0, 0.0))
               );
               this.collideWithEntities(
                  this.entityWorld
                     .getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing2.getEntityBoundingBox().expand(4.0, 2.0, 4.0).offset(0.0, -2.0, 0.0))
               );
               this.attackEntitiesInList(this.entityWorld.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartHead.getEntityBoundingBox().expand(1.0, 1.0, 1.0)));
            }

            double[] movementOffsets = this.getMovementOffsets(5, 1.0F);
            double[] movementOffsets2 = this.getMovementOffsets(0, 1.0F);
            float f19 = MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F - this.randomYawVelocity * 0.01F);
            float f20 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F - this.randomYawVelocity * 0.01F);
            this.dragonPartHead.onUpdate();
            this.dragonPartHead
               .setLocationAndAngles(
                  this.posX + (double)(f19 * 5.5F * f14),
                  this.posY + (movementOffsets2[1] - movementOffsets[1]) * 1.0 + (double)(f15 * 5.5F),
                  this.posZ - (double)(f20 * 5.5F * f14),
                  0.0F,
                  0.0F
               );

            for (int j = 0; j < 3; j++) {
               EntityDragonPart entityDragonPart = null;
               if (j == 0) {
                  entityDragonPart = this.dragonPartTail1;
               }

               if (j == 1) {
                  entityDragonPart = this.dragonPartTail2;
               }

               if (j == 2) {
                  entityDragonPart = this.dragonPartTail3;
               }

               double[] movementOffsets3 = this.getMovementOffsets(12 + j * 2, 1.0F);
               float f21 = this.rotationYaw * (float) Math.PI / 180.0F + this.simplifyAngle(movementOffsets3[0] - movementOffsets[0]) * (float) Math.PI / 180.0F * 1.0F;
               float f22 = MathHelper.sin(f21);
               float f23 = MathHelper.cos(f21);
               float f24 = 1.5F;
               float f25 = (float)(j + 1) * 2.0F;
               entityDragonPart.onUpdate();
               entityDragonPart.setLocationAndAngles(
                  this.posX - (double)((f17 * f24 + f22 * f25) * f14),
                  this.posY + (movementOffsets3[1] - movementOffsets[1]) * 1.0 - (double)((f25 + f24) * f15) + 1.5,
                  this.posZ + (double)((f18 * f24 + f23 * f25) * f14),
                  0.0F,
                  0.0F
               );
            }

            if (!this.entityWorld.isRemote) {
               this.slowed = this.destroyBlocksInAABB(this.dragonPartHead.getEntityBoundingBox())
                  | this.destroyBlocksInAABB(this.dragonPartBody.getEntityBoundingBox());
            }
         }
      }
   }

   @Override
   public void onKillCommand() {
      this.setDead();
   }

   @Override
   public void onDeathUpdate() {
      this.deathTicks++;
      if (this.deathTicks >= 180 && this.deathTicks <= 200) {
         float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
         float f2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
         float f3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
         this.entityWorld
            .spawnParticle(
               EnumParticleTypes.EXPLOSION_HUGE, this.posX + (double)f, this.posY + 2.0 + (double)f2, this.posZ + (double)f3, 0.0, 0.0, 0.0, new int[0]
            );
      }

      boolean flag = this.entityWorld.getGameRules().getBoolean("doMobLoot");
      if (!this.entityWorld.isRemote) {
         if (this.deathTicks > 150 && this.deathTicks % 5 == 0 && flag) {
            int n = 1000;

            while (n > 0) {
               int xpSplit = EntityXPOrb.getXPSplit(n);
               n -= xpSplit;
               this.entityWorld.spawnEntityInWorld(new EntityXPOrb(this.entityWorld, this.posX, this.posY, this.posZ, xpSplit));
            }
         }

         if (this.deathTicks == 1) {
            this.entityWorld.playBroadcastSound(1018, new BlockPos(this), 0);
         }
      }

      this.moveEntity(0.0, 0.1F, 0.0);
      this.renderYawOffset = this.rotationYaw += 20.0F;
      if (this.deathTicks == 200 && !this.entityWorld.isRemote) {
         if (flag) {
            int n2 = 2000;

            while (n2 > 0) {
               int xpSplit2 = EntityXPOrb.getXPSplit(n2);
               n2 -= xpSplit2;
               this.entityWorld.spawnEntityInWorld(new EntityXPOrb(this.entityWorld, this.posX, this.posY, this.posZ, xpSplit2));
            }
         }

         this.generatePortal(new BlockPos(this.posX, 64.0, this.posZ));
         this.setDead();
      }
   }

   @Override
   public World getWorld() {
      return this.entityWorld;
   }

   public double[] getMovementOffsets(int n, float f) {
      if (this.getHealth() <= 0.0F) {
         f = 0.0F;
      }

      f = 1.0F - f;
      int n2 = this.ringBufferIndex - n * 1 & 63;
      int n3 = this.ringBufferIndex - n * 1 - 1 & 63;
      double[] doubles = new double[3];
      double d = this.ringBuffer[n2][0];
      double d2 = MathHelper.wrapAngleTo180_double(this.ringBuffer[n3][0] - d);
      doubles[0] = d + d2 * (double)f;
      d = this.ringBuffer[n2][1];
      d2 = this.ringBuffer[n3][1] - d;
      doubles[1] = d + d2 * (double)f;
      doubles[2] = this.ringBuffer[n2][2] + (this.ringBuffer[n3][2] - this.ringBuffer[n2][2]) * (double)f;
      return doubles;
   }

   @Override
   public boolean canBeCollidedWith() {
      return false;
   }

   private void collideWithEntities(List list) {
      double d = (this.dragonPartBody.getEntityBoundingBox().minX + this.dragonPartBody.getEntityBoundingBox().maxX) / 2.0;
      double d2 = (this.dragonPartBody.getEntityBoundingBox().minZ + this.dragonPartBody.getEntityBoundingBox().maxZ) / 2.0;

      for (Entity entity : (Iterable<Entity>)(list)) {
         if (entity instanceof EntityLivingBase) {
            double d3 = entity.posX - d;
            double d4 = entity.posZ - d2;
            double d5 = d3 * d3 + d4 * d4;
            entity.addVelocity(d3 / d5 * 4.0, 0.2F, d4 / d5 * 4.0);
         }
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
   }

   public EntityDragon(World world) {
      super(world);
      this.dragonPartArray = new EntityDragonPart[]{
         this.dragonPartHead = new EntityDragonPart(this, "head", 6.0F, 6.0F),
         this.dragonPartBody = new EntityDragonPart(this, "body", 8.0F, 8.0F),
         this.dragonPartTail1 = new EntityDragonPart(this, "tail", 4.0F, 4.0F),
         this.dragonPartTail2 = new EntityDragonPart(this, "tail", 4.0F, 4.0F),
         this.dragonPartTail3 = new EntityDragonPart(this, "tail", 4.0F, 4.0F),
         this.dragonPartWing1 = new EntityDragonPart(this, "wing", 4.0F, 4.0F),
         this.dragonPartWing2 = new EntityDragonPart(this, "wing", 4.0F, 4.0F)
      };
      this.setHealth(this.getBossMaxHealth());
      this.setSize(16.0F, 8.0F);
      this.noClip = true;
      this.isImmuneToFire = true;
      this.targetY = 100.0;
      this.ignoreFrustumCheck = true;
   }

   @Override
   public void entityInit() {
      super.entityInit();
   }

   private void setNewTarget() {
      this.forceNewTarget = false;
      ArrayList list = Lists.newArrayList(this.entityWorld.playerEntities);
      Iterator it = list.iterator();

      while (it.hasNext()) {
         if (((EntityPlayer)it.next()).isSpectator()) {
            it.remove();
         }
      }

      if (this.rand.nextInt(2) == 0 && !list.isEmpty()) {
         this.target = (Entity)list.get(this.rand.nextInt(list.size()));
      } else {
         boolean flag;
         do {
            this.targetX = 0.0;
            this.targetY = (double)(70.0F + this.rand.nextFloat() * 50.0F);
            this.targetZ = 0.0;
            this.targetX = this.targetX + (double)(this.rand.nextFloat() * 120.0F - 60.0F);
            this.targetZ = this.targetZ + (double)(this.rand.nextFloat() * 120.0F - 60.0F);
            double d = this.posX - this.targetX;
            double d2 = this.posY - this.targetY;
            double d3 = this.posZ - this.targetZ;
            flag = d * d + d2 * d2 + d3 * d3 > 100.0;
         } while (!flag);

         this.target = null;
      }
   }

   @Override
   public String getLivingSound() {
      return "mob.enderdragon.growl";
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (damageSource instanceof EntityDamageSource && ((EntityDamageSource)damageSource).getIsThornsDamage()) {
         this.attackDragonFrom(damageSource, f);
      }

      return false;
   }

   @Override
   public void despawnEntity() {
   }
}

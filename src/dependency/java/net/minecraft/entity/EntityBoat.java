package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.InterpolationSelector;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoat extends Entity {
   public boolean enabled13 = true;
   public double value18 = 0.07;
   public int count11;
   public double value16;
   public double value8;
   public double value21;
   public double value7;
   public double value9;
   public double value14;
   public double value12;
   public double value6;

   @Override
   public void updateState17() {
      if (this.entity != null) {
         double offset = Math.cos((double)this.rotationYaw * Math.PI / 180.0) * 0.4;
         double offset2 = Math.sin((double)this.rotationYaw * Math.PI / 180.0) * 0.4;
         this.entity.processDouble2(this.posX + offset, this.posY + this.getValueAsDouble() + this.entity.getValue(), this.posZ + offset2);
      }
   }

   public void applyIndex5(int count) {
      this.dataWatcher.applyTargetByTarget(17, count);
   }

   @Override
   public boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      if (this.entity != null && this.entity instanceof EntityPlayer && this.entity != player) {
         return true;
      } else {
         if (!this.worldObj.state) {
            player.setEntity8(this);
         }

         return true;
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public double getValueAsDouble() {
      return -0.3;
   }

   public void setEnabled13(boolean enabled) {
      this.enabled13 = enabled;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public int getCount4() {
      return this.dataWatcher.computeCount(17);
   }

   @Override
   public void applyDouble2(double posX, double posY, double posZ) {
      this.value14 = this.motionX = posX;
      this.value12 = this.motionY = posY;
      this.value6 = this.motionZ = posZ;
   }

   @Override
   public AxisAlignedBB resolveAxisAlignedBB(Entity entity) {
      return entity.getBoundingBox2();
   }

   public void applyFloat(float factor) {
      this.dataWatcher.applyTargetByTarget(19, factor);
   }

   public void applyIndex6(int count) {
      this.dataWatcher.applyTargetByTarget(18, count);
   }

   @Override
   public boolean isActive9() {
      return true;
   }

   public int getCount5() {
      return this.dataWatcher.computeCount(18);
   }

   @Override
   public void applyBlock(double offset, boolean flag, Block block, BlockPos blockPos) {
      if (flag) {
         if (this.fallDistance > 3.0F) {
            this.processFloat2(this.fallDistance, 1.0F);
            if (!this.worldObj.state && !this.isDead) {
               this.updateState9();
               if (this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
                  for (int index = 0; index < 3; index++) {
                     this.resolveEntityItem(Item.processAction2(Blocks.block142), 1, 0.0F);
                  }

                  for (int index2 = 0; index2 < 2; index2++) {
                     this.resolveEntityItem(Items.stick, 1, 0.0F);
                  }
               }
            }

            this.fallDistance = 0.0F;
         }
      } else if (this.worldObj.getBlockState(new BlockPos(this).down()).getBlock().getMaterial() != Material.coreItemEntry1 && offset < 0.0) {
         this.fallDistance = (float)((double)this.fallDistance - offset);
      }
   }

   @Override
   public AxisAlignedBB getAxisAlignedBB() {
      return this.getBoundingBox2();
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   public float getFloat() {
      return this.dataWatcher.computeFactor(19);
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if (!this.worldObj.state && !this.isDead) {
         if (this.entity != null && this.entity == damageSource.getEntity() && damageSource instanceof InterpolationSelector) {
            return false;
         } else {
            this.applyIndex6(-this.getCount5());
            this.applyIndex5(10);
            this.applyFloat(this.getFloat() + factor * 10.0F);
            this.updateState8();
            boolean flag = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).invulnerableManager.enabled2;
            if (flag || this.getFloat() > 40.0F) {
               if (this.entity != null) {
                  this.entity.setEntity8(this);
               }

               if (!flag && this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
                  this.resolveEntityItem(Items.boat, 1, 0.0F);
               }

               this.updateState9();
            }

            return true;
         }
      } else {
         this.applyIndex6(-this.getCount5());
         this.applyIndex5(10);
         this.applyFloat(this.getFloat() + factor * 10.0F);
         this.updateState8();
         return true;
      }
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2, int count, boolean flag) {
      if (flag && this.entity != null) {
         this.prevPosX = this.posX = posX;
         this.prevPosY = this.posY = posY;
         this.prevPosZ = this.posZ = posZ;
         this.rotationYaw = factor;
         this.rotationPitch = factor2;
         this.count11 = 0;
         this.processDouble2(posX, posY, posZ);
         this.motionX = this.value14 = 0.0;
         this.motionY = this.value12 = 0.0;
         this.motionZ = this.value6 = 0.0;
      } else {
         if (this.enabled13) {
            this.count11 = count + 5;
         } else {
            double offset = posX - this.posX;
            double offset2 = posY - this.posY;
            double offset3 = posZ - this.posZ;
            if (offset * offset + offset2 * offset2 + offset3 * offset3 <= 1.0) {
               return;
            }

            this.count11 = 3;
         }

         this.value16 = posX;
         this.value8 = posY;
         this.value21 = posZ;
         this.value7 = (double)factor;
         this.value9 = (double)factor2;
         this.motionX = this.value14;
         this.motionY = this.value12;
         this.motionZ = this.value6;
      }
   }

   @Override
   public boolean isActive5() {
      return !this.isDead;
   }

   @Override
   public void updateState16() {
      this.applyIndex6(-this.getCount5());
      this.applyIndex5(10);
      this.applyFloat(this.getFloat() * 11.0F);
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.getCount4() > 0) {
         this.applyIndex5(this.getCount4() - 1);
      }

      if (this.getFloat() > 0.0F) {
         this.applyFloat(this.getFloat() - 1.0F);
      }

      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      double offset = 0.0;

      for (int index = 0; index < 5; index++) {
         double offset2 = this.getBoundingBox2().minY + (this.getBoundingBox2().maxY - this.getBoundingBox2().minY) * (double)index / (double)5 - 0.125;
         double offset3 = this.getBoundingBox2().minY + (this.getBoundingBox2().maxY - this.getBoundingBox2().minY) * (double)(index + 1) / (double)5 - 0.125;
         AxisAlignedBB boundingBox = new AxisAlignedBB(
            this.getBoundingBox2().minX, offset2, this.getBoundingBox2().minZ, this.getBoundingBox2().maxX, offset3, this.getBoundingBox2().maxZ
         );
         if (this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(boundingBox, Material.coreItemEntry1)) {
            offset += 1.0 / (double)5;
         }
      }

      double offset4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
      if (offset4 > 0.2975) {
         double offset5 = Math.cos((double)this.rotationYaw * Math.PI / 180.0);
         double offset6 = Math.sin((double)this.rotationYaw * Math.PI / 180.0);

         for (int index2 = 0; (double)index2 < 1.0 + offset4 * 60.0; index2++) {
            double offset7 = (double)(this.random.nextFloat() * 2.0F - 1.0F);
            double offset8 = (double)(this.random.nextInt(2) * 2 - 1) * 0.7;
            if (this.random.nextBoolean()) {
               double offset9 = this.posX - offset5 * offset7 * 0.8 + offset6 * offset8;
               double offset10 = this.posZ - offset6 * offset7 * 0.8 - offset5 * offset8;
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.WATER_SPLASH, offset9, this.posY - 0.125, offset10, this.motionX, this.motionY, this.motionZ, new int[0]
                  );
            } else {
               double offset11 = this.posX + offset5 + offset6 * offset7 * 0.7;
               double offset12 = this.posZ + offset6 - offset5 * offset7 * 0.7;
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.WATER_SPLASH, offset11, this.posY - 0.125, offset12, this.motionX, this.motionY, this.motionZ, new int[0]
                  );
            }
         }
      }

      if (this.worldObj.state && this.enabled13) {
         if (this.count11 > 0) {
            double offset13 = this.posX + (this.value16 - this.posX) / (double)this.count11;
            double offset14 = this.posY + (this.value8 - this.posY) / (double)this.count11;
            double offset15 = this.posZ + (this.value21 - this.posZ) / (double)this.count11;
            double doubleItem = MathHelper.getDouble4(this.value7 - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + doubleItem / (double)this.count11);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.value9 - (double)this.rotationPitch) / (double)this.count11);
            this.count11--;
            this.processDouble2(offset13, offset14, offset15);
            this.processFloat4(this.rotationYaw, this.rotationPitch);
         } else {
            double offset16 = this.posX + this.motionX;
            double offset17 = this.posY + this.motionY;
            double offset18 = this.posZ + this.motionZ;
            this.processDouble2(offset16, offset17, offset18);
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
         if (offset < 1.0) {
            double offset19 = offset * 2.0 - 1.0;
            this.motionY += 0.04F * offset19;
         } else {
            if (this.motionY < 0.0) {
               this.motionY /= 2.0;
            }

            this.motionY += 0.007F;
         }

         if (this.entity instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)this.entity;
            float factor = this.entity.rotationYaw + -entity.scaleFactor24 * 90.0F;
            this.motionX = this.motionX + -Math.sin((double)(factor * (float) Math.PI / 180.0F)) * this.value18 * (double)entity.scaleFactor3 * 0.05F;
            this.motionZ = this.motionZ + Math.cos((double)(factor * (float) Math.PI / 180.0F)) * this.value18 * (double)entity.scaleFactor3 * 0.05F;
         }

         double offset20 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (offset20 > 0.35) {
            double offset21 = 0.35 / offset20;
            this.motionX *= offset21;
            this.motionZ *= offset21;
            offset20 = 0.35;
         }

         if (offset20 > offset4 && this.value18 < 0.35) {
            this.value18 = this.value18 + (0.35 - this.value18) / 35.0;
            if (this.value18 > 0.35) {
               this.value18 = 0.35;
            }
         } else {
            this.value18 = this.value18 - (this.value18 - 0.07) / 35.0;
            if (this.value18 < 0.07) {
               this.value18 = 0.07;
            }
         }

         for (int index3 = 0; index3 < 4; index3++) {
            int count = MathHelper.ceiling_double_int(this.posX + ((double)(index3 % 2) - 0.5) * 0.8);
            int count2 = MathHelper.ceiling_double_int(this.posZ + ((double)(index3 / 2) - 0.5) * 0.8);

            for (int index4 = 0; index4 < 2; index4++) {
               int count3 = MathHelper.ceiling_double_int(this.posY) + index4;
               BlockPos blockPos = new BlockPos(count, count3, count2);
               Block block = this.worldObj.getBlockState(blockPos).getBlock();
               if (block == Blocks.block74) {
                  this.worldObj.matchesBlockPos8(blockPos);
                  this.isCollidedHorizontally = false;
               } else if (block == Blocks.block33) {
                  this.worldObj.matchesBlockPos3(blockPos, true);
                  this.isCollidedHorizontally = false;
               }
            }
         }

         if (this.onGround) {
            this.motionX *= 0.5;
            this.motionY *= 0.5;
            this.motionZ *= 0.5;
         }

         this.applyDouble(this.motionX, this.motionY, this.motionZ);
         if (this.isCollidedHorizontally && offset4 > 0.2975) {
            if (!this.worldObj.state && !this.isDead) {
               this.updateState9();
               if (this.worldObj.getDoFireTickManager().matchesText("doEntityDrops")) {
                  for (int index5 = 0; index5 < 3; index5++) {
                     this.resolveEntityItem(Item.processAction2(Blocks.block142), 1, 0.0F);
                  }

                  for (int index6 = 0; index6 < 2; index6++) {
                     this.resolveEntityItem(Items.stick, 1, 0.0F);
                  }
               }
            }
         } else {
            this.motionX *= 0.99F;
            this.motionY *= 0.95F;
            this.motionZ *= 0.99F;
         }

         this.rotationPitch = 0.0F;
         double factor2 = (double)this.rotationYaw;
         double offset22 = this.prevPosX - this.posX;
         double offset23 = this.prevPosZ - this.posZ;
         if (offset22 * offset22 + offset23 * offset23 > 0.001) {
            factor2 = (double)((float)(MathHelper.getDouble6(offset23, offset22) * 180.0 / Math.PI));
         }

         double doubleItem2 = MathHelper.getDouble4(factor2 - (double)this.rotationYaw);
         if (doubleItem2 > 20.0) {
            doubleItem2 = 20.0;
         }

         if (doubleItem2 < -20.0) {
            doubleItem2 = -20.0;
         }

         this.rotationYaw = (float)((double)this.rotationYaw + doubleItem2);
         this.processFloat4(this.rotationYaw, this.rotationPitch);
         if (!this.worldObj.state) {
            List list = this.worldObj.resolveListByEntity(this, this.getBoundingBox2().offset(0.2F, 0.0, 0.2F));
            if (list != null && !list.isEmpty()) {
               for (int index7 = 0; index7 < list.size(); index7++) {
                  Entity entity2 = (Entity)list.get(index7);
                  if (entity2 != this.entity && entity2.isActive9() && entity2 instanceof EntityBoat) {
                     entity2.applyEntity(this);
                  }
               }
            }

            if (this.entity != null && this.entity.isDead) {
               this.entity = null;
            }
         }
      }
   }

   @Override
   public void updateState7() {
      this.dataWatcher.applyTarget(17, 0);
      this.dataWatcher.applyTarget(18, 1);
      this.dataWatcher.applyTarget(19, 0.0F);
   }

   public EntityBoat(World world, double posX, double posY, double posZ) {
      this(world);
      this.processDouble2(posX, posY, posZ);
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.prevPosX = posX;
      this.prevPosY = posY;
      this.prevPosZ = posZ;
   }

   public EntityBoat(World world) {
      super(world);
      this.enabled = true;
      this.applyFloat(1.5F, 0.6F);
   }
}

package net.minecraft.entity;

import client.event.PreRenderProcessor;
import client.event.PreRenderSubscriber;
import client.setting.AttackEffectsExplosionNormalType;
import client.util.DamageSourceAdapter;
import client.util.VectorFormatter;
import client.util.VectorPipeline;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakState;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderdragon extends EntityMob implements PreRenderSubscriber, PreRenderProcessor, VectorPipeline {
   public double value44;
   public double value42;
   public double value36;
   public double[][] valueArray = new double[64][3];
   public int count31 = -1;
   public VectorFormatter[] vectorFormatterArray;
   public VectorFormatter vectorFormatter5;
   public VectorFormatter vectorFormatter6;
   public VectorFormatter vectorFormatter4;
   public VectorFormatter vectorFormatter7;
   public VectorFormatter vectorFormatter3;
   public VectorFormatter vectorFormatter2;
   public VectorFormatter vectorFormatter;
   public float factor41;
   public float factor40;
   public boolean enabled30;
   public boolean enabled28;
   public Entity entity6;
   public int count28;
   public EntityEndercrystal entityEndercrystal;

   @Override
   public IChatComponent getIChatComponent() {
      return this.getChatComponent();
   }

   @Override
   public float getFactor() {
      return 5.0F;
   }

   @Override
   public Entity[] getEntityArray() {
      return this.vectorFormatterArray;
   }

   private void applyList(List list) {
      double offset = (this.vectorFormatter6.getBoundingBox2().minX + this.vectorFormatter6.getBoundingBox2().maxX) / 2.0;
      double offset2 = (this.vectorFormatter6.getBoundingBox2().minZ + this.vectorFormatter6.getBoundingBox2().maxZ) / 2.0;

      for (Entity entity : (Iterable<Entity>)(Iterable<?>)list) {
         if (entity instanceof EntityLivingBase) {
            double offset3 = entity.posX - offset;
            double offset4 = entity.posZ - offset2;
            double offset5 = offset3 * offset3 + offset4 * offset4;
            entity.applyDouble3(offset3 / offset5 * 4.0, 0.2F, offset4 / offset5 * 4.0);
         }
      }
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(200.0);
   }

   private boolean matchesAxisAlignedBBByAxisAlignedBB(AxisAlignedBB boundingBox) {
      int count = MathHelper.ceiling_double_int(boundingBox.minX);
      int count2 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count3 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count4 = MathHelper.ceiling_double_int(boundingBox.maxX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxY);
      int count6 = MathHelper.ceiling_double_int(boundingBox.maxZ);
      boolean flag = false;

      boolean flag2;
      for (flag2 = false; count <= count4; count++) {
         for (int index = count2; index <= count5; index++) {
            for (int index2 = count3; index2 <= count6; index2++) {
               BlockPos blockPos = new BlockPos(count, index, index2);
               Block block = this.worldObj.getBlockState(blockPos).getBlock();
               if (block.getMaterial() != Material.coreItemEntry) {
                  if (block != Blocks.block145
                     && block != Blocks.block113
                     && block != Blocks.block28
                     && block != Blocks.block95
                     && block != Blocks.block139
                     && this.worldObj.getDoFireTickManager().matchesText("mobGriefing")) {
                     flag2 = this.worldObj.matchesBlockPos8(blockPos) || flag2;
                  } else {
                     flag = true;
                  }
               }
            }
         }
      }

      if (flag2) {
         double offset = boundingBox.minX + (boundingBox.maxX - boundingBox.minX) * (double)this.random.nextFloat();
         double offset2 = boundingBox.minY + (boundingBox.maxY - boundingBox.minY) * (double)this.random.nextFloat();
         double offset3 = boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) * (double)this.random.nextFloat();
         this.worldObj.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.EXPLOSION_LARGE, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
      }

      return flag;
   }

   public double[] resolveDoubleArray(int count, float factor) {
      float factor2 = factor;
      if (this.getFloat2() <= 0.0F) {
         factor2 = 0.0F;
      }

      factor2 = 1.0F - factor2;
      int count2 = this.count31 - count * 1 & 63;
      int count3 = this.count31 - count * 1 - 1 & 63;
      double[] offsetArray = new double[3];
      double offset = this.valueArray[count2][0];
      double doubleItem = MathHelper.getDouble4(this.valueArray[count3][0] - offset);
      offsetArray[0] = offset + doubleItem * (double)factor2;
      double offset2 = this.valueArray[count2][1];
      double offset3 = this.valueArray[count3][1] - offset2;
      offsetArray[1] = offset2 + offset3 * (double)factor2;
      offsetArray[2] = this.valueArray[count2][2] + (this.valueArray[count3][2] - this.valueArray[count2][2]) * (double)factor2;
      return offsetArray;
   }

   private float computeFactor(double offset) {
      return (float)MathHelper.getDouble4(offset);
   }

   private void applyBlockPosByBlockPos(BlockPos blockPos) {
      for (int index = -1; index <= 32; index++) {
         for (int index2 = -4; index2 <= 4; index2++) {
            for (int index3 = -4; index3 <= 4; index3++) {
               double offset = (double)(index2 * index2 + index3 * index3);
               if (offset <= 12.25) {
                  BlockPos blockPos2 = blockPos.add(index2, index, index3);
                  if (index < 0) {
                     if (offset <= 6.25) {
                        this.worldObj.matchesBlockPos(blockPos2, Blocks.block95.getDefaultState());
                     }
                  } else if (index > 0) {
                     this.worldObj.matchesBlockPos(blockPos2, Blocks.air.getDefaultState());
                  } else if (offset > 6.25) {
                     this.worldObj.matchesBlockPos(blockPos2, Blocks.block95.getDefaultState());
                  } else {
                     this.worldObj.matchesBlockPos(blockPos2, Blocks.block2.getDefaultState());
                  }
               }
            }
         }
      }

      this.worldObj.matchesBlockPos(blockPos, Blocks.block95.getDefaultState());
      this.worldObj.matchesBlockPos(blockPos.up(), Blocks.block95.getDefaultState());
      BlockPos blockPos3 = blockPos.resolveBlockPos2(2);
      this.worldObj.matchesBlockPos(blockPos3, Blocks.block95.getDefaultState());
      this.worldObj.matchesBlockPos(blockPos3.getBlockPos(), Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, EnumFacing.EAST));
      this.worldObj.matchesBlockPos(blockPos3.getBlockPos3(), Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, EnumFacing.WEST));
      this.worldObj.matchesBlockPos(blockPos3.getBlockPosAsBlockPos(), Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, EnumFacing.SOUTH));
      this.worldObj.matchesBlockPos(blockPos3.getBlockPos2(), Blocks.block21.getDefaultState().resolveIBlockState(BlockTorch.autoWalkPath, EnumFacing.NORTH));
      this.worldObj.matchesBlockPos(blockPos.resolveBlockPos2(3), Blocks.block95.getDefaultState());
      this.worldObj.matchesBlockPos(blockPos.resolveBlockPos2(4), Blocks.block82.getDefaultState());
   }

   @Override
   public boolean matchesVectorFormatter(VectorFormatter vectorFormatter8, DamageSource damageSource, float factor) {
      float factor2 = factor;
      if (vectorFormatter8 != this.vectorFormatter5) {
         factor2 = factor / 4.0F + 1.0F;
      }

      float factor3 = this.rotationYaw * (float) Math.PI / 180.0F;
      float factor4 = MathHelper.abs(factor3);
      float factor5 = MathHelper.cos(factor3);
      this.value44 = this.posX + (double)(factor4 * 5.0F) + (double)((this.random.nextFloat() - 0.5F) * 2.0F);
      this.value42 = this.posY + (double)(this.random.nextFloat() * 3.0F) + 1.0;
      this.value36 = this.posZ - (double)(factor5 * 5.0F) + (double)((this.random.nextFloat() - 0.5F) * 2.0F);
      this.entity6 = null;
      if (damageSource.getEntity() instanceof EntityPlayer || damageSource.isEnabled5()) {
         this.matchesDamageSourceByDamageSource(damageSource, factor2);
      }

      return true;
   }

   @Override
   public World getWorld2() {
      return this.worldObj;
   }

   public boolean matchesDamageSourceByDamageSource(DamageSource damageSource, float factor) {
      return super.matchesDamageSource(damageSource, factor);
   }

   @Override
   public String getText3() {
      return "mob.enderdragon.growl";
   }

   @Override
   public String getText10() {
      return "mob.enderdragon.hit";
   }

   @Override
   public void updateState15() {
      this.updateState9();
   }

   private void applyListByList(List list) {
      for (int index = 0; index < list.size(); index++) {
         Entity entity = (Entity)list.get(index);
         if (entity instanceof EntityLivingBase) {
            entity.matchesDamageSource(DamageSource.resolveDamageSourceByEntityLivingBase(this), 10.0F);
            this.applyEntityLivingBase(this, entity);
         }
      }
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (damageSource instanceof DamageSourceAdapter && ((DamageSourceAdapter)damageSource).isEnabled9()) {
         this.matchesDamageSourceByDamageSource(damageSource, factor);
      }

      return false;
   }

   @Override
   public void updateState4() {
   }

   @Override
   public boolean isActive5() {
      return false;
   }

   private void updateState47() {
      this.enabled30 = false;
      ArrayList list = Lists.newArrayList(this.worldObj.itemList7);
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         if (((EntityPlayer)iterator.next()).isActive23()) {
            iterator.remove();
         }
      }

      if (this.random.nextInt(2) == 0 && !list.isEmpty()) {
         this.entity6 = (Entity)list.get(this.random.nextInt(list.size()));
      } else {
         double offset;
         double offset2;
         double offset3;
         do {
            this.value44 = 0.0;
            this.value42 = (double)(70.0F + this.random.nextFloat() * 50.0F);
            this.value36 = 0.0;
            this.value44 = this.value44 + (double)(this.random.nextFloat() * 120.0F - 60.0F);
            this.value36 = this.value36 + (double)(this.random.nextFloat() * 120.0F - 60.0F);
            offset = this.posX - this.value44;
            offset2 = this.posY - this.value42;
            offset3 = this.posZ - this.value36;
         } while (!(offset * offset + offset2 * offset2 + offset3 * offset3 > 100.0));

         this.entity6 = null;
      }
   }

   private void updateState48() {
      if (this.entityEndercrystal != null) {
         if (this.entityEndercrystal.isDead) {
            if (!this.worldObj.state) {
               this.matchesVectorFormatter(this.vectorFormatter5, DamageSource.resolveDamageSource((BlockBreakState)null), 10.0F);
            }

            this.entityEndercrystal = null;
         } else if (this.ticksExisted % 10 == 0 && this.getFloat2() < this.getFloat()) {
            this.applyFloat(this.getFloat2() + 1.0F);
         }
      }

      if (this.random.nextInt(10) == 0) {
         float factor = 32.0F;
         List list = this.worldObj.resolveList(EntityEndercrystal.class, this.getBoundingBox2().offset((double)factor, (double)factor, (double)factor));
         EntityEndercrystal entityEndercrystal2 = null;
         double offset = Double.MAX_VALUE;

         for (EntityEndercrystal entityEndercrystal3 : (Iterable<EntityEndercrystal>)(Iterable<?>)list) {
            double coordinate = entityEndercrystal3.getCoordinate(this);
            if (coordinate < offset) {
               offset = coordinate;
               entityEndercrystal2 = entityEndercrystal3;
            }
         }

         this.entityEndercrystal = entityEndercrystal2;
      }
   }

   @Override
   public void updateState7() {
      super.updateState7();
   }

   @Override
   public void updateState33() {
      if (this.worldObj.state) {
         float factor = MathHelper.cos(this.factor40 * (float) Math.PI * 2.0F);
         if (MathHelper.cos(this.factor41 * (float) Math.PI * 2.0F) <= -0.3F && factor >= -0.3F && !this.isActive7()) {
            this.worldObj.applyText(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.random.nextFloat() * 0.3F, false);
         }
      }

      this.factor41 = this.factor40;
      if (this.getFloat2() <= 0.0F) {
         float factor2 = (this.random.nextFloat() - 0.5F) * 8.0F;
         float factor3 = (this.random.nextFloat() - 0.5F) * 4.0F;
         float factor4 = (this.random.nextFloat() - 0.5F) * 8.0F;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.EXPLOSION_LARGE,
               this.posX + (double)factor2,
               this.posY + 2.0 + (double)factor3,
               this.posZ + (double)factor4,
               0.0,
               0.0,
               0.0,
               new int[0]
            );
      } else {
         this.updateState48();
         float factor5 = 0.2F
            / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F)
            * (float)Math.pow(2.0, this.motionY);
         if (this.enabled28) {
            this.factor40 += factor5 * 0.5F;
         } else {
            this.factor40 += factor5;
         }

         this.rotationYaw = MathHelper.sqrt_float(this.rotationYaw);
         if (this.isActive25()) {
            this.factor40 = 0.5F;
         } else {
            if (this.count31 < 0) {
               for (int index = 0; index < this.valueArray.length; index++) {
                  this.valueArray[index][0] = (double)this.rotationYaw;
                  this.valueArray[index][1] = this.posY;
               }
            }

            if (++this.count31 == this.valueArray.length) {
               this.count31 = 0;
            }

            this.valueArray[this.count31][0] = (double)this.rotationYaw;
            this.valueArray[this.count31][1] = this.posY;
            if (this.worldObj.state) {
               if (this.count22 > 0) {
                  double offset = this.posX + (this.value6 - this.posX) / (double)this.count22;
                  double offset2 = this.posY + (this.value8 - this.posY) / (double)this.count22;
                  double offset3 = this.posZ + (this.value15 - this.posZ) / (double)this.count22;
                  double doubleItem = MathHelper.getDouble4(this.value9 - (double)this.rotationYaw);
                  this.rotationYaw = (float)((double)this.rotationYaw + doubleItem / (double)this.count22);
                  this.rotationPitch = (float)((double)this.rotationPitch + (this.value11 - (double)this.rotationPitch) / (double)this.count22);
                  this.count22--;
                  this.processDouble2(offset, offset2, offset3);
                  this.processFloat4(this.rotationYaw, this.rotationPitch);
               }
            } else {
               double offset4 = this.value44 - this.posX;
               double offset5 = this.value42 - this.posY;
               double offset6 = this.value36 - this.posZ;
               double offset7 = offset4 * offset4 + offset5 * offset5 + offset6 * offset6;
               if (this.entity6 != null) {
                  this.value44 = this.entity6.posX;
                  this.value36 = this.entity6.posZ;
                  double offset8 = this.value44 - this.posX;
                  double offset9 = this.value36 - this.posZ;
                  double offset10 = Math.sqrt(offset8 * offset8 + offset9 * offset9);
                  double offset11 = 0.4F + offset10 / 80.0 - 1.0;
                  if (offset11 > 10.0) {
                     offset11 = 10.0;
                  }

                  this.value42 = this.entity6.getBoundingBox2().minY + offset11;
               } else {
                  this.value44 = this.value44 + this.random.nextGaussian() * 2.0;
                  this.value36 = this.value36 + this.random.nextGaussian() * 2.0;
               }

               if (this.enabled30 || offset7 < 100.0 || offset7 > 22500.0 || this.isCollidedHorizontally || this.isCollidedVertically) {
                  this.updateState47();
               }

               double offset12 = offset5 / (double)MathHelper.sqrt_double(offset4 * offset4 + offset6 * offset6);
               float factor6 = 0.6F;
               double doubleItem2 = MathHelper.getDouble7(offset12, (double)(-factor6), (double)factor6);
               this.motionY += doubleItem2 * 0.1F;
               this.rotationYaw = MathHelper.sqrt_float(this.rotationYaw);
               double doubleItem3 = MathHelper.getDouble4(180.0 - MathHelper.getDouble6(offset4, offset6) * 180.0 / Math.PI - (double)this.rotationYaw);
               if (doubleItem3 > 50.0) {
                  doubleItem3 = 50.0;
               }

               if (doubleItem3 < -50.0) {
                  doubleItem3 = -50.0;
               }

               Vec3 vec = new Vec3(this.value44 - this.posX, this.value42 - this.posY, this.value36 - this.posZ).normalize();
               double offset13 = (double)(-MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F));
               Vec3 vec2 = new Vec3((double)MathHelper.abs(this.rotationYaw * (float) Math.PI / 180.0F), this.motionY, offset13).normalize();
               float factor7 = ((float)vec2.distanceTo(vec) + 0.5F) / 1.5F;
               if (factor7 < 0.0F) {
                  factor7 = 0.0F;
               }

               this.factor16 *= 0.8F;
               float factor8 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
               double offset14 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0 + 1.0;
               if (offset14 > 40.0) {
                  offset14 = 40.0;
               }

               this.factor16 = (float)((double)this.factor16 + doubleItem3 * (0.7F / offset14 / (double)factor8));
               this.rotationYaw = this.rotationYaw + this.factor16 * 0.1F;
               float factor9 = (float)(2.0 / (offset14 + 1.0));
               float factor10 = 0.06F;
               this.applyFloat(0.0F, -1.0F, factor10 * (factor7 * factor9 + (1.0F - factor9)));
               if (this.enabled28) {
                  this.applyDouble(this.motionX * 0.8F, this.motionY * 0.8F, this.motionZ * 0.8F);
               } else {
                  this.applyDouble(this.motionX, this.motionY, this.motionZ);
               }

               float factor11 = ((float)new Vec3(this.motionX, this.motionY, this.motionZ).normalize().distanceTo(vec2) + 1.0F) / 2.0F;
               float factor12 = 0.8F + 0.15F * factor11;
               this.motionX *= (double)factor12;
               this.motionZ *= (double)factor12;
               this.motionY *= 0.91F;
            }

            this.scaleFactor8 = this.rotationYaw;
            this.vectorFormatter5.width = this.vectorFormatter5.height = 3.0F;
            this.vectorFormatter4.width = this.vectorFormatter4.height = 2.0F;
            this.vectorFormatter7.width = this.vectorFormatter7.height = 2.0F;
            this.vectorFormatter3.width = this.vectorFormatter3.height = 2.0F;
            this.vectorFormatter6.height = 3.0F;
            this.vectorFormatter6.width = 5.0F;
            this.vectorFormatter2.height = 2.0F;
            this.vectorFormatter2.width = 4.0F;
            this.vectorFormatter.height = 3.0F;
            this.vectorFormatter.width = 4.0F;
            float factor13 = (float)(this.resolveDoubleArray(5, 1.0F)[1] - this.resolveDoubleArray(10, 1.0F)[1]) * 10.0F / 180.0F * (float) Math.PI;
            float factor14 = MathHelper.cos(factor13);
            float factor15 = -MathHelper.abs(factor13);
            float factor16 = this.rotationYaw * (float) Math.PI / 180.0F;
            float factor17 = MathHelper.abs(factor16);
            float factor18 = MathHelper.cos(factor16);
            this.vectorFormatter6.updateState();
            this.vectorFormatter6.applyDoubleByDouble(this.posX + (double)(factor17 * 0.5F), this.posY, this.posZ - (double)(factor18 * 0.5F), 0.0F, 0.0F);
            this.vectorFormatter2.updateState();
            this.vectorFormatter2
               .applyDoubleByDouble(this.posX + (double)(factor18 * 4.5F), this.posY + 2.0, this.posZ + (double)(factor17 * 4.5F), 0.0F, 0.0F);
            this.vectorFormatter.updateState();
            this.vectorFormatter
               .applyDoubleByDouble(this.posX - (double)(factor18 * 4.5F), this.posY + 2.0, this.posZ - (double)(factor17 * 4.5F), 0.0F, 0.0F);
            if (!this.worldObj.state && this.hurtTime == 0) {
               this.applyList(this.worldObj.resolveListByEntity(this, this.vectorFormatter2.getBoundingBox2().offset(4.0, 2.0, 4.0).expand(0.0, -2.0, 0.0)));
               this.applyList(this.worldObj.resolveListByEntity(this, this.vectorFormatter.getBoundingBox2().offset(4.0, 2.0, 4.0).expand(0.0, -2.0, 0.0)));
               this.applyListByList(this.worldObj.resolveListByEntity(this, this.vectorFormatter5.getBoundingBox2().offset(1.0, 1.0, 1.0)));
            }

            double[] offsetArray = this.resolveDoubleArray(5, 1.0F);
            double[] offsetArray2 = this.resolveDoubleArray(0, 1.0F);
            float factor19 = MathHelper.abs(this.rotationYaw * (float) Math.PI / 180.0F - this.factor16 * 0.01F);
            float factor20 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F - this.factor16 * 0.01F);
            this.vectorFormatter5.updateState();
            this.vectorFormatter5
               .applyDoubleByDouble(
                  this.posX + (double)(factor19 * 5.5F * factor14),
                  this.posY + (offsetArray2[1] - offsetArray[1]) * 1.0 + (double)(factor15 * 5.5F),
                  this.posZ - (double)(factor20 * 5.5F * factor14),
                  0.0F,
                  0.0F
               );

            for (int index2 = 0; index2 < 3; index2++) {
               VectorFormatter vectorFormatter8 = null;
               if (index2 == 0) {
                  vectorFormatter8 = this.vectorFormatter4;
               }

               if (index2 == 1) {
                  vectorFormatter8 = this.vectorFormatter7;
               }

               if (index2 == 2) {
                  vectorFormatter8 = this.vectorFormatter3;
               }

               double[] offsetArray3 = this.resolveDoubleArray(12 + index2 * 2, 1.0F);
               float factor21 = this.rotationYaw * (float) Math.PI / 180.0F + this.computeFactor(offsetArray3[0] - offsetArray[0]) * (float) Math.PI / 180.0F * 1.0F;
               float factor22 = MathHelper.abs(factor21);
               float factor23 = MathHelper.cos(factor21);
               float factor24 = 1.5F;
               float factor25 = (float)(index2 + 1) * 2.0F;
               vectorFormatter8.updateState();
               vectorFormatter8.applyDoubleByDouble(
                  this.posX - (double)((factor17 * factor24 + factor22 * factor25) * factor14),
                  this.posY + (offsetArray3[1] - offsetArray[1]) * 1.0 - (double)((factor25 + factor24) * factor15) + 1.5,
                  this.posZ + (double)((factor18 * factor24 + factor23 * factor25) * factor14),
                  0.0F,
                  0.0F
               );
            }

            if (!this.worldObj.state) {
               this.enabled28 = this.matchesAxisAlignedBBByAxisAlignedBB(this.vectorFormatter5.getBoundingBox2())
                  | this.matchesAxisAlignedBBByAxisAlignedBB(this.vectorFormatter6.getBoundingBox2());
            }
         }
      }
   }

   @Override
   public void updateState36() {
      this.count28++;
      if (this.count28 >= 180 && this.count28 <= 200) {
         float factor = (this.random.nextFloat() - 0.5F) * 8.0F;
         float factor2 = (this.random.nextFloat() - 0.5F) * 4.0F;
         float factor3 = (this.random.nextFloat() - 0.5F) * 8.0F;
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.EXPLOSION_HUGE,
               this.posX + (double)factor,
               this.posY + 2.0 + (double)factor2,
               this.posZ + (double)factor3,
               0.0,
               0.0,
               0.0,
               new int[0]
            );
      }

      boolean flag = this.worldObj.getDoFireTickManager().matchesText("doMobLoot");
      if (!this.worldObj.state) {
         if (this.count28 > 150 && this.count28 % 5 == 0 && flag) {
            int count = 1000;

            while (count > 0) {
               int count2 = EntityXporb.computeCount(count);
               count -= count2;
               this.worldObj.matchesEntity(new EntityXporb(this.worldObj, this.posX, this.posY, this.posZ, count2));
            }
         }

         if (this.count28 == 1) {
            this.worldObj.applyBlockPos19(1018, new BlockPos(this), 0);
         }
      }

      this.applyDouble(0.0, 0.1F, 0.0);
      this.scaleFactor8 = this.rotationYaw += 20.0F;
      if (this.count28 == 200 && !this.worldObj.state) {
         if (flag) {
            int count3 = 2000;

            while (count3 > 0) {
               int count4 = EntityXporb.computeCount(count3);
               count3 -= count4;
               this.worldObj.matchesEntity(new EntityXporb(this.worldObj, this.posX, this.posY, this.posZ, count4));
            }
         }

         this.applyBlockPosByBlockPos(new BlockPos(this.posX, 64.0, this.posZ));
         this.updateState9();
      }
   }

   public EntityEnderdragon(World world) {
      super(world);
      this.vectorFormatterArray = new VectorFormatter[]{
         this.vectorFormatter5 = new VectorFormatter(this, "head", 6.0F, 6.0F),
         this.vectorFormatter6 = new VectorFormatter(this, "body", 8.0F, 8.0F),
         this.vectorFormatter4 = new VectorFormatter(this, "tail", 4.0F, 4.0F),
         this.vectorFormatter7 = new VectorFormatter(this, "tail", 4.0F, 4.0F),
         this.vectorFormatter3 = new VectorFormatter(this, "tail", 4.0F, 4.0F),
         this.vectorFormatter2 = new VectorFormatter(this, "wing", 4.0F, 4.0F),
         this.vectorFormatter = new VectorFormatter(this, "wing", 4.0F, 4.0F)
      };
      this.applyFloat(this.getFloat());
      this.applyFloat(16.0F, 8.0F);
      this.noClip = true;
      this.state4 = true;
      this.value42 = 100.0;
      this.enabled3 = true;
   }
}

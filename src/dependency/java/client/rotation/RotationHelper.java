package client.rotation;

import augustus.modules.combat.BowAimbot;
import augustus.modules.legit.AutoRod;
import client.ClientGlobals;
import client.module.Module;
import client.util.IBlockState;
import client.util.MinecraftAccessor;
import client.util.MovingObjectType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class RotationHelper implements MinecraftAccessor {
   public double value;
   public double value2;
   public boolean enabled;
   public boolean enabled2;
   public double value3;
   public double value4;
   public double value5;
   public MovingObjectPosition movingObjectPosition;
   public double value6;
   public EntityLivingBase entityLivingBase;
   public float factor;
   public Vec3 vec3;
   public ItemStack itemStack;
   public EntityArrow entityArrow;
   public double value7;
   public int count;
   public double value8;
   public Random random = new Random();
   public double value9;
   public double value10;
   public double value11;
   public int indexOffset;
   public float factor2;
   public int count2;
   public double value12;
   public EntityThrowable entityThrowable;
   public double value13;
   public double value14;
   public float factor3;
   public AxisAlignedBB axisAlignedBB;
   public double value15;
   public Block block;
   public boolean enabled3;
   public int count3;

   public RotationHelper() {
      this.factor = 0.25F;
      this.factor3 = 0.25F;
      this.movingObjectPosition = null;
   }

   private float computeFactor(ItemStack stack) {
      Item item = stack.getItem();
      if (item.equals(Items.experienceBottle)) {
         return 0.7F;
      } else if (item.equals(Items.potion) && ItemPotion.checkCondition(stack.getIndexOffset())) {
         return 0.5F;
      } else if (item instanceof ItemBow) {
         float factor4 = (float)(((ItemBow)item).processAction(stack) - minecraft.thePlayer.getEntityId2()) / 20.0F;
         float factor5 = (factor4 * factor4 + factor4 * 2.0F) / 3.0F;
         if ((double)factor5 < 0.1) {
            return 0.0F;
         } else {
            if (factor5 > 1.0F) {
               factor5 = 1.0F;
            }

            return factor5 * 2.0F * 1.5F;
         }
      } else {
         return 1.5F;
      }
   }

   private boolean isActiveAsFlag() {
      if (this.axisAlignedBB != null) {
         AxisAlignedBB boundingBox = this.axisAlignedBB.offset(0.0, -0.4F, 0.0).resolveAxisAlignedBB(0.001, 0.001, 0.001);
         Material coreItemEntry = Material.coreItemEntry1;
         AxisAlignedBB boundingBox2 = boundingBox;
         if (this.matchesAxisAlignedBB(boundingBox2, coreItemEntry)) {
            this.factor2 = 0.0F;
            this.enabled3 = true;
            return this.enabled3;
         }
      }

      this.enabled3 = false;
      return this.enabled3;
   }

   public int getIndex(int count4, EntityLivingBase livingEntity, AxisAlignedBB boundingBox) {
      for (int index = 0; index < count4; index++) {
         this.value9 = this.value14;
         this.value10 = this.value7;
         this.value5 = this.value11;
         this.isActiveAsFlag();
         if (this.count2 > 0) {
            this.count2--;
         }

         if (this.enabled2) {
            return Integer.MAX_VALUE;
         }

         this.count++;
         if (this.count3 > 2) {
            return Integer.MAX_VALUE;
         }

         MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
            .getMovingObjectPosition7(
               this.value14, this.value7, this.value11, this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3, false, true, false
            );
         Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
         Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
         EntityLivingBase livingEntity2 = null;
         if (this.entityLivingBase != null && livingEntity.isActive5() && livingEntity != this.entityLivingBase && boundingBox.calculateIntercept(vec2, vec) != null) {
            livingEntity2 = livingEntity;
         }

         if (livingEntity2 != null) {
            return index;
         }

         if (movingObjectPosition2 != null && movingObjectPosition2.entityHit == null) {
            return Integer.MAX_VALUE;
         }

         this.value14 = this.value14 + this.value13;
         this.value7 = this.value7 + this.value2;
         this.value11 = this.value11 + this.value3;
         float factor4 = 0.99F;
         float factor5;
         if (this.entityThrowable == null) {
            ItemStack itemStack2 = this.itemStack;
            factor5 = this.computeFactor2(itemStack2);
         } else {
            factor5 = this.entityThrowable.getFactorAsFloat();
         }

         float factor6 = factor5;
         if (this.enabled3) {
            factor4 = 0.8F;
         }

         this.value13 *= (double)factor4;
         this.value2 *= (double)factor4;
         this.value3 *= (double)factor4;
         this.value2 -= (double)factor6;
         double value16 = this.value11;
         double value17 = this.value7;
         double value18 = this.value14;
         this.applyDouble(value18, value17, value16);
         if (this.value7 < boundingBox.minY && this.value2 < 0.0) {
            index = count4;
         }
      }

      return Integer.MAX_VALUE;
   }

   public ArrayList getArrayList(int count4) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < count4; index++) {
         this.value9 = this.value14;
         this.value10 = this.value7;
         this.value5 = this.value11;
         this.isActiveAsFlag();
         if (this.count2 > 0) {
            this.count2--;
         }

         if (this.enabled2) {
            if (minecraft.theWorld.getIBlockState2(this.vec3.xCoord, this.vec3.yCoord, this.vec3.zCoord).getBlock() == this.block) {
               this.count3++;
               if (this.count3 > 2) {
                  index = count4;
               }
            }

            this.enabled2 = false;
            this.value13 = this.value13 * (double)(this.random.nextFloat() * 0.2F);
            this.value2 = this.value2 * (double)(this.random.nextFloat() * 0.2F);
            this.value3 = this.value3 * (double)(this.random.nextFloat() * 0.2F);
            this.count3 = 0;
            this.count = 0;
         } else {
            this.count++;
         }

         MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
            .getMovingObjectPosition(
               this.value14, this.value7, this.value11, this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3
            );
         Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
         Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
         if (movingObjectPosition2 != null
            && (
               movingObjectPosition2.getBlockPos() == null
                  || !(
                     minecraft.theWorld.getIBlockState(movingObjectPosition2.getBlockPos().getX(), movingObjectPosition2.getBlockPos().getY(), movingObjectPosition2.getBlockPos().getZ()).getBlock() instanceof BlockLiquid
                  )
            )) {
            vec2 = new Vec3(movingObjectPosition2.hitVec.xCoord, movingObjectPosition2.hitVec.yCoord, movingObjectPosition2.hitVec.zCoord);
            this.movingObjectPosition = movingObjectPosition2;
            index = count4;
         }

         Entity entity = null;
         List list2 = minecraft.theWorld
            .resolveListByEntity(this.entityThrowable, this.axisAlignedBB.contract(this.value13, this.value2, this.value3).offset(1.0, 1.0, 1.0));
         double offset = 0.0;
         Object entry = this.entityThrowable == null ? minecraft.thePlayer : this.entityThrowable.getEntityLivingBase();
         if (entry != null) {
            for (Entity entity2 : (Iterable<Entity>)(list2)) {
               if (entity2.isActive5() && (entity2 != entry || this.count >= 5)) {
                  float factor4 = 0.3F;
                  MovingObjectPosition hitResult = entity2.getBoundingBox2().offset((double)factor4, (double)factor4, (double)factor4).calculateIntercept(vec, vec2);
                  if (hitResult != null) {
                     double offset2 = vec.dotProduct(hitResult.hitVec);
                     if (offset2 < offset || offset == 0.0) {
                        entity = entity2;
                        offset = offset2;
                     }
                  }
               }
            }
         }

         if (entity != null) {
            MovingObjectPosition hitResult2 = new MovingObjectPosition(entity);
            this.movingObjectPosition = hitResult2;
            index = count4;
         }

         this.value14 = this.value14 + this.value13;
         this.value7 = this.value7 + this.value2;
         this.value11 = this.value11 + this.value3;
         float factor5 = 0.99F;
         float factor6;
         if (this.entityThrowable == null) {
            ItemStack itemStack2 = this.itemStack;
            factor6 = this.computeFactor2(itemStack2);
         } else {
            factor6 = this.entityThrowable.getFactorAsFloat();
         }

         float factor7 = factor6;
         if (this.enabled3) {
            factor5 = 0.8F;
         }

         this.value13 *= (double)factor5;
         this.value2 *= (double)factor5;
         this.value3 *= (double)factor5;
         this.value2 -= (double)factor7;
         double value16 = this.value11;
         double value17 = this.value7;
         double value18 = this.value14;
         this.applyDouble(value18, value17, value16);
         list.add(new Vec3(this.value14, this.value7, this.value11));
         if (this.value7 < 0.0) {
            index = count4;
         }
      }

      return list;
   }

   public boolean checkEntityLivingBase(int count4, EntityLivingBase livingEntity, AxisAlignedBB boundingBox) {
      for (int index = 0; index < count4; index++) {
         this.isActiveAsFlag();
         if (this.enabled2) {
            return false;
         }

         MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
            .getMovingObjectPosition7(
               this.value14, this.value7, this.value11, this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3, false, true, false
            );
         Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
         Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
         if (movingObjectPosition2 != null) {
            this.movingObjectPosition = movingObjectPosition2;
            return false;
         }

         EntityLivingBase livingEntity2 = null;
         double offset = 0.0;
         if (this.entityLivingBase != null && livingEntity.isActive5() && livingEntity != this.entityLivingBase) {
            MovingObjectPosition hitResult = boundingBox.calculateIntercept(vec2, vec);
            if (hitResult != null && (vec2.dotProduct(hitResult.hitVec) < offset || offset == 0.0)) {
               livingEntity2 = livingEntity;
            }
         }

         if (livingEntity2 != null) {
            return true;
         }

         this.value14 = this.value14 + this.value13;
         this.value7 = this.value7 + this.value2;
         this.value11 = this.value11 + this.value3;
         float factor4 = 0.92F;
         double offset2 = 0.0;

         for (int index2 = 0; index2 < 5; index2++) {
            AxisAlignedBB axisAlignedBB2 = this.axisAlignedBB;
            double offset3 = axisAlignedBB2.maxY - axisAlignedBB2.minY;
            double offset4 = axisAlignedBB2.minY + offset3 * (double)index2 / (double)5;
            double offset5 = axisAlignedBB2.minY + offset3 * (double)(index2 + 1) / (double)5;
            AxisAlignedBB boundingBox2 = new AxisAlignedBB(axisAlignedBB2.minX, offset4, axisAlignedBB2.minZ, axisAlignedBB2.maxX, offset5, axisAlignedBB2.maxZ);
            if (minecraft.theWorld.matchesAxisAlignedBBByAxisAlignedBB(boundingBox2, Material.coreItemEntry1)) {
               offset2 += 1.0 / (double)5;
            }
         }

         double offset6 = offset2 * 2.0 - 1.0;
         this.value2 += 0.04F * offset6;
         if (offset2 > 0.0) {
            factor4 = (float)((double)factor4 * 0.9);
            this.value2 = this.value2 + this.value2 * 0.8;
         }

         this.value13 *= (double)factor4;
         this.value2 *= (double)factor4;
         this.value3 *= (double)factor4;
         double value16 = this.value11;
         double value17 = this.value7;
         double value18 = this.value14;
         this.applyDouble(value18, value17, value16);
         if (this.value7 < boundingBox.minY && this.value2 < 0.0) {
            return false;
         }
      }

      return false;
   }

   public void applyEntityLivingBase(EntityLivingBase livingEntity, ItemStack stack, double posX, double posY, double posZ, float factor4, float factor5) {
      this.vec3 = new Vec3(-1.0, -1.0, -1.0);
      this.itemStack = stack;
      Item item = stack.getItem();
      if (item instanceof ItemBow) {
         this.factor = 0.5F;
         this.factor3 = 0.5F;
      }

      this.entityLivingBase = livingEntity;
      this.value14 = livingEntity.posX;
      this.value7 = livingEntity.posY + (double)livingEntity.getHeight2();
      this.value11 = livingEntity.posZ;
      float factor6 = this.factor / 2.0F;
      float factor7 = this.factor3;
      this.value9 = this.value14;
      this.value10 = this.value7;
      this.value5 = this.value11;
      this.value14 = this.value14 + (double)(MathHelper.cos(factor4 / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
      this.value7 -= 0.1F;
      this.value11 = this.value11 + (double)(MathHelper.abs(factor4 / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
      this.axisAlignedBB = new AxisAlignedBB(
         this.value14 - (double)factor6,
         this.value7,
         this.value11 - (double)factor6,
         this.value14 + (double)factor6,
         this.value7 + (double)factor7,
         this.value11 + (double)factor6
      );
      float factor8 = item instanceof ItemBow ? 1.0F : 0.4F;
      this.value13 = (double)(-MathHelper.abs(factor4 / 180.0F * (float) Math.PI) * MathHelper.cos(factor5 / 180.0F * (float) Math.PI) * factor8);
      this.value2 = (double)(-MathHelper.abs((factor5 + this.computeFactorByItemStack(stack)) / 180.0F * (float) Math.PI) * factor8);
      this.value3 = (double)(MathHelper.cos(factor4 / 180.0F * (float) Math.PI) * MathHelper.cos(factor5 / 180.0F * (float) Math.PI) * factor8);
      double value16 = this.value13;
      double value17 = this.value2;
      double value18 = this.value3;
      float factor9 = this.computeFactor(stack);
      float factor10 = 1.0F;
      float factor11 = factor9;
      double offset = value18;
      double offset2 = value17;
      double offset3 = value16;
      this.applyDouble(offset3, offset2, offset, factor11, factor10);
   }

   public ArrayList getArrayList2(int count4) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < count4; index++) {
         this.value9 = this.value14;
         this.value10 = this.value7;
         this.value5 = this.value11;
         this.isActiveAsFlag();
         if (this.enabled2) {
            if (this.count3 > 2) {
               index = count4;
            }
         } else {
            MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
               .getMovingObjectPosition7(
                  this.value14,
                  this.value7,
                  this.value11,
                  this.value14 + this.value13,
                  this.value7 + this.value2,
                  this.value11 + this.value3,
                  false,
                  true,
                  false
               );
            Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
            Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
            if (movingObjectPosition2 != null) {
               vec2 = new Vec3(movingObjectPosition2.hitVec.xCoord, movingObjectPosition2.hitVec.yCoord, movingObjectPosition2.hitVec.zCoord);
               this.movingObjectPosition = movingObjectPosition2;
               index = count4;
            }

            Entity entity = null;
            List list2 = minecraft.theWorld
               .resolveListByEntity(this.entityArrow, this.axisAlignedBB.contract(this.value13, this.value2, this.value3).offset(1.0, 1.0, 1.0));
            double offset = 0.0;

            for (int index2 = 0; index2 < list2.size(); index2++) {
               Entity entity2 = (Entity)list2.get(index2);
               if (this.entityLivingBase != null && entity2.isActive5() && entity2 != this.entityLivingBase) {
                  float factor4 = 0.3F;
                  MovingObjectPosition hitResult = entity2.getBoundingBox2().offset((double)factor4, (double)factor4, (double)factor4).calculateIntercept(vec2, vec);
                  if (hitResult != null) {
                     double offset2 = vec2.dotProduct(hitResult.hitVec);
                     if (offset2 < offset || offset == 0.0) {
                        entity = entity2;
                        offset = offset2;
                     }
                  }
               }
            }

            if (entity != null) {
               MovingObjectPosition hitResult2 = new MovingObjectPosition(entity);
               this.movingObjectPosition = hitResult2;
               index = count4;
            }

            this.value14 = this.value14 + this.value13;
            this.value7 = this.value7 + this.value2;
            this.value11 = this.value11 + this.value3;
            float factor5 = 0.92F;
            double offset3 = 0.0;

            for (int index3 = 0; index3 < 5; index3++) {
               AxisAlignedBB axisAlignedBB2 = this.axisAlignedBB;
               double offset4 = axisAlignedBB2.maxY - axisAlignedBB2.minY;
               double offset5 = axisAlignedBB2.minY + offset4 * (double)index3 / (double)5;
               double offset6 = axisAlignedBB2.minY + offset4 * (double)(index3 + 1) / (double)5;
               AxisAlignedBB boundingBox = new AxisAlignedBB(axisAlignedBB2.minX, offset5, axisAlignedBB2.minZ, axisAlignedBB2.maxX, offset6, axisAlignedBB2.maxZ);
               if (minecraft.theWorld.matchesAxisAlignedBBByAxisAlignedBB(boundingBox, Material.coreItemEntry1)) {
                  offset3 += 1.0 / (double)5;
               }
            }

            double offset7 = offset3 * 2.0 - 1.0;
            this.value2 += 0.04F * offset7;
            if (offset3 > 0.0) {
               factor5 = (float)((double)factor5 * 0.9);
               this.value2 = this.value2 + this.value2 * 0.8;
            }

            this.value13 *= (double)factor5;
            this.value2 *= (double)factor5;
            this.value3 *= (double)factor5;
            double value16 = this.value11;
            double value17 = this.value7;
            double value18 = this.value14;
            this.applyDouble(value18, value17, value16);
            list.add(new Vec3(this.value14, this.value7, this.value11));
            if (this.value7 < 0.0) {
               index = count4;
            }
         }
      }

      return list;
   }

   public ArrayList getArrayList3(int count4, float factor4) {
      if (this.entityArrow == null || !this.entityArrow.onGround && !this.entityArrow.isEnabled11()) {
         ArrayList list = new ArrayList();

         for (int index = 0; index < count4; index++) {
            this.value9 = this.value14;
            this.value10 = this.value7;
            this.value5 = this.value11;
            this.isActiveAsFlag();
            IBlockState iBlockState = minecraft.theWorld.getIBlockState2(this.vec3.xCoord, this.vec3.yCoord, this.vec3.zCoord);
            Block block2 = iBlockState.getBlock();
            if (block2.getMaterial() != Material.coreItemEntry) {
               int count5 = MathHelper.ceiling_double_int(this.vec3.xCoord);
               int count6 = MathHelper.ceiling_double_int(this.vec3.yCoord);
               int count7 = MathHelper.ceiling_double_int(this.vec3.zCoord);
               block2.processAction(minecraft.theWorld, count5, count6, count7);
               AxisAlignedBB boundingBox = block2.processAction(minecraft.theWorld, iBlockState, count5, count6, count7);
               if (boundingBox != null && boundingBox.checkDouble(this.value14, this.value7, this.value11)) {
                  this.enabled2 = true;
               }
            }

            if (this.count2 > 0) {
               this.count2--;
            }

            if (this.enabled2) {
               if (this.count3 > 2) {
                  break;
               }
            } else {
               MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
                  .getMovingObjectPosition7(
                     this.value14,
                     this.value7,
                     this.value11,
                     this.value14 + this.value13,
                     this.value7 + this.value2,
                     this.value11 + this.value3,
                     false,
                     true,
                     false
                  );
               Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
               Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
               if (this.movingObjectPosition == null && movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK) {
                  this.movingObjectPosition = movingObjectPosition2;
               }

               Entity entity = null;
               List list2 = minecraft.theWorld
                  .resolveListByEntity(this.entityArrow, this.axisAlignedBB.contract(this.value13, this.value2, this.value3).offset(1.0, 1.0, 1.0));
               double offset = 0.0;

               for (int index2 = 0; index2 < list2.size(); index2++) {
                  Entity entity2 = (Entity)list2.get(index2);
                  if (this.entityLivingBase != null && entity2.isActive5()) {
                     float factor5 = 0.3F + (entity2 == this.entityLivingBase ? factor4 : 0.1F);
                     MovingObjectPosition hitResult = entity2.getBoundingBox2().offset((double)factor5, (double)factor5, (double)factor5).calculateIntercept(vec2, vec);
                     if (hitResult != null) {
                        double offset2 = vec2.dotProduct(hitResult.hitVec);
                        if (offset2 < offset || offset == 0.0) {
                           entity = entity2;
                           offset = offset2;
                        }
                     }
                  }
               }

               if (entity != null) {
                  this.movingObjectPosition = new MovingObjectPosition(entity);
                  this.indexOffset = index;
               }
            }

            this.value14 = this.value14 + this.value13;
            this.value7 = this.value7 + this.value2;
            this.value11 = this.value11 + this.value3;
            float factor6 = 0.99F;
            float factor7 = 0.05F;
            if (this.enabled3) {
               factor6 = 0.6F;
            }

            this.value13 *= (double)factor6;
            this.value2 *= (double)factor6;
            this.value3 *= (double)factor6;
            this.value2 -= (double)factor7;
            double value16 = this.value11;
            double value17 = this.value7;
            double value18 = this.value14;
            this.applyDouble(value18, value17, value16);
            list.add(new Vec3(this.value14, this.value7, this.value11));
            if (this.value7 < 0.0) {
               index = count4;
            }
         }

         return list;
      } else {
         return null;
      }
   }

   public ArrayList getArrayList4(int count4) {
      if (this.entityArrow == null || !this.entityArrow.onGround && !this.entityArrow.isEnabled11()) {
         ArrayList list = new ArrayList();

         for (int index = 0; index < count4; index++) {
            this.value9 = this.value14;
            this.value10 = this.value7;
            this.value5 = this.value11;
            this.isActiveAsFlag();
            IBlockState iBlockState = minecraft.theWorld.getIBlockState2(this.vec3.xCoord, this.vec3.yCoord, this.vec3.zCoord);
            Block block2 = iBlockState.getBlock();
            if (block2.getMaterial() != Material.coreItemEntry) {
               int count5 = MathHelper.ceiling_double_int(this.vec3.xCoord);
               int count6 = MathHelper.ceiling_double_int(this.vec3.yCoord);
               int count7 = MathHelper.ceiling_double_int(this.vec3.zCoord);
               block2.processAction(minecraft.theWorld, count5, count6, count7);
               AxisAlignedBB boundingBox = block2.processAction(minecraft.theWorld, iBlockState, count5, count6, count7);
               if (boundingBox != null && boundingBox.checkDouble(this.value14, this.value7, this.value11)) {
                  this.enabled2 = true;
               }
            }

            if (this.count2 > 0) {
               this.count2--;
            }

            if (this.enabled2) {
               if (this.count3 > 2) {
                  break;
               }
            } else {
               MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
                  .getMovingObjectPosition7(
                     this.value14,
                     this.value7,
                     this.value11,
                     this.value14 + this.value13,
                     this.value7 + this.value2,
                     this.value11 + this.value3,
                     false,
                     true,
                     false
                  );
               Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
               Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
               if (movingObjectPosition2 != null) {
                  this.movingObjectPosition = movingObjectPosition2;
                  break;
               }

               Entity entity = null;
               List list2 = minecraft.theWorld
                  .resolveListByEntity(this.entityArrow, this.axisAlignedBB.contract(this.value13, this.value2, this.value3).offset(1.0, 1.0, 1.0));
               double offset = 0.0;

               for (int index2 = 0; index2 < list2.size(); index2++) {
                  Entity entity2 = (Entity)list2.get(index2);
                  if (this.entityLivingBase != null && entity2.isActive5() && entity2 != this.entityLivingBase) {
                     float factor4 = 0.3F;
                     MovingObjectPosition hitResult = entity2.getBoundingBox2().offset((double)factor4, (double)factor4, (double)factor4).calculateIntercept(vec2, vec);
                     if (hitResult != null) {
                        double offset2 = vec2.dotProduct(hitResult.hitVec);
                        if (offset2 < offset || offset == 0.0) {
                           entity = entity2;
                           offset = offset2;
                        }
                     }
                  }
               }

               if (entity != null) {
                  movingObjectPosition2 = new MovingObjectPosition(entity);
                  this.movingObjectPosition = movingObjectPosition2;
                  index = count4;
               }

               if (movingObjectPosition2 != null && movingObjectPosition2.entityHit == null) {
                  BlockPos blockPos = movingObjectPosition2.getBlockPos();
                  this.vec3 = new Vec3((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
                  this.block = minecraft.theWorld.getIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ()).getBlock();
                  this.value13 = (double)((float)(movingObjectPosition2.hitVec.xCoord - this.value14));
                  this.value2 = (double)((float)(movingObjectPosition2.hitVec.yCoord - this.value7));
                  this.value3 = (double)((float)(movingObjectPosition2.hitVec.zCoord - this.value11));
                  float factor5 = MathHelper.sqrt_double(this.value13 * this.value13 + this.value2 * this.value2 + this.value3 * this.value3);
                  this.value14 = this.value14 + this.value13 / (double)factor5 * 0.05F * -1.0;
                  this.value7 = this.value7 + this.value2 / (double)factor5 * 0.05F * -1.0;
                  this.value11 = this.value11 + this.value3 / (double)factor5 * 0.05F * -1.0;
                  this.enabled2 = true;
                  this.count2 = 7;
               }
            }

            this.value14 = this.value14 + this.value13;
            this.value7 = this.value7 + this.value2;
            this.value11 = this.value11 + this.value3;
            float factor6 = 0.99F;
            float factor7 = 0.05F;
            if (this.enabled3) {
               factor6 = 0.6F;
            }

            this.value13 *= (double)factor6;
            this.value2 *= (double)factor6;
            this.value3 *= (double)factor6;
            this.value2 -= (double)factor7;
            double value16 = this.value11;
            double value17 = this.value7;
            double value18 = this.value14;
            this.applyDouble(value18, value17, value16);
            list.add(new Vec3(this.value14, this.value7, this.value11));
            if (this.value7 < 0.0) {
               index = count4;
            }
         }

         return list;
      } else {
         return new ArrayList();
      }
   }

   private boolean isActive() {
      double offset = this.value12 + 0.001;
      double offset2 = this.value4 + 0.4F + 0.001;
      double offset3 = this.value15 + 0.001;
      double offset4 = this.value6 - 0.001;
      double offset5 = this.value8 - 0.4F - 0.001;
      double offset6 = this.value - 0.001;
      Material coreItemEntry = Material.coreItemEntry1;
      double offset7 = offset6;
      double offset8 = offset5;
      double offset9 = offset4;
      double offset10 = offset3;
      double offset11 = offset2;
      double offset12 = offset;
      if (this.matchesMaterial(offset12, offset11, offset10, offset9, offset8, offset7, coreItemEntry)) {
         this.factor2 = 0.0F;
         this.enabled3 = true;
      } else {
         this.enabled3 = false;
      }

      return this.enabled3;
   }

   public void updateAuxiliaryEntityAdapter(EntityThrowable entityThrowable2) {
      this.entityLivingBase = minecraft.thePlayer;
      this.value14 = entityThrowable2.posX;
      this.value7 = entityThrowable2.posY;
      this.value11 = entityThrowable2.posZ;
      this.value9 = entityThrowable2.lastTickPosX;
      this.value10 = entityThrowable2.lastTickPosY;
      this.value5 = entityThrowable2.lastTickPosZ;
      this.value13 = entityThrowable2.motionX;
      this.value2 = entityThrowable2.motionY;
      this.value3 = entityThrowable2.motionZ;
      this.vec3 = new Vec3((double)entityThrowable2.getCount16(), (double)entityThrowable2.getCount14(), (double)entityThrowable2.getCount11());
      this.axisAlignedBB = new AxisAlignedBB(
         entityThrowable2.getBoundingBox2().minX,
         entityThrowable2.getBoundingBox2().minY,
         entityThrowable2.getBoundingBox2().minZ,
         entityThrowable2.getBoundingBox2().maxX,
         entityThrowable2.getBoundingBox2().maxY,
         entityThrowable2.getBoundingBox2().maxZ
      );
      this.count3 = entityThrowable2.getCount15();
      this.enabled3 = entityThrowable2.isInWater();
      this.enabled = entityThrowable2.enabled2;
      this.factor2 = entityThrowable2.fallDistance;
      this.block = entityThrowable2.getBlock();
      this.count = entityThrowable2.getCount12();
      this.enabled2 = entityThrowable2.isEnabled11();
      this.count2 = entityThrowable2.count13;
      this.factor = entityThrowable2.width;
      this.factor3 = entityThrowable2.height;
      this.entityThrowable = entityThrowable2;
   }

   private void applyDouble(double posX, double posY, double posZ, float factor4, float factor5) {
      float factor6 = MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
      double offset = posX / (double)factor6;
      double offset2 = posY / (double)factor6;
      double offset3 = posZ / (double)factor6;
      offset *= (double)factor4;
      offset2 *= (double)factor4;
      offset3 *= (double)factor4;
      this.value13 = offset;
      this.value2 = offset2;
      this.value3 = offset3;
      this.count3 = 0;
   }

   private float computeFactor2(ItemStack stack) {
      Item item = stack.getItem();
      if (item.equals(Items.experienceBottle)) {
         return 0.07F;
      } else {
         return item.equals(Items.potion) && ItemPotion.checkCondition(stack.getIndexOffset()) ? 0.05F : 0.03F;
      }
   }

   private float computeFactorByItemStack(ItemStack stack) {
      Item item = stack.getItem();
      return !item.equals(Items.experienceBottle) && (!item.equals(Items.potion) || !ItemPotion.checkCondition(stack.getIndexOffset())) ? 0.0F : -20.0F;
   }

   private boolean matchesAxisAlignedBB(AxisAlignedBB boundingBox, Material material) {
      int count4 = MathHelper.ceiling_double_int(boundingBox.minX);
      int count5 = MathHelper.ceiling_double_int(boundingBox.maxX + 1.0);
      int count6 = MathHelper.ceiling_double_int(boundingBox.minY);
      int count7 = MathHelper.ceiling_double_int(boundingBox.maxY + 1.0);
      int count8 = MathHelper.ceiling_double_int(boundingBox.minZ);
      int count9 = MathHelper.ceiling_double_int(boundingBox.maxZ + 1.0);
      if (!minecraft.theWorld.checkIndex(count4, count6, count8, count5, count7, count9, true)) {
         return false;
      } else {
         boolean flag = false;
         Vec3 vec = new Vec3(0.0, 0.0, 0.0);
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

         outerLoop:
         for (int index = 8000; count4 < count5; count4++) {
            for (int index2 = count6; index2 < count7; index2++) {
               for (int index3 = count8; index3 < count9; index3++) {
                  IBlockState iBlockState = minecraft.theWorld.getIBlockState(count4, index2, index3);
                  Block block2 = iBlockState.getBlock();
                  if (block2.getMaterial() == material) {
                     double offset = (double)(
                        (float)(index2 + 1) - BlockLiquid.computeFactor((Integer)iBlockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry))
                     );
                     if ((double)count7 >= offset) {
                        auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index2, index3);
                        flag = true;
                        vec = this.resolveVec3(block2, vec, auxiliaryBlockPosAdapter);
                     }
                  }

                  if (--index == 0) {
                     break outerLoop;
                  }
               }
            }
         }

         if (vec.getValueAsDouble() > 0.0) {
            Vec3 vec2 = vec.normalize();
            double offset2 = 0.014;
            this.value13 = this.value13 + vec2.xCoord * offset2;
            this.value2 = this.value2 + vec2.yCoord * offset2;
            this.value3 = this.value3 + vec2.zCoord * offset2;
         }

         return flag;
      }
   }

   public MovingObjectPosition getMovingObjectPosition() {
      return this.movingObjectPosition;
   }

   public void applyEntityLivingBase2(EntityLivingBase livingEntity, int count4, ItemStack stack) {
      this.vec3 = new Vec3(-1.0, -1.0, -1.0);
      this.itemStack = stack;
      Item item = stack.getItem();
      if (item instanceof ItemBow) {
         this.factor = 0.5F;
         this.factor3 = 0.5F;
      }

      float factor4;
      float factor5;
      guardBlock: {
         searchBlock: {
            this.entityLivingBase = livingEntity;
            factor4 = livingEntity.prevRotationYaw + (livingEntity.rotationYaw - livingEntity.prevRotationYaw) * minecraft.getTimer().scaleFactor6;
            factor5 = livingEntity.prevRotationPitch + (livingEntity.rotationPitch - livingEntity.prevRotationPitch) * minecraft.getTimer().scaleFactor6;
            if (((Module)ClientGlobals.moduleManager.bowAimbot).isToggled() && ClientGlobals.moduleManager.bowAimbot.getTargetEntity() != null) {
               BowAimbot bowAimbot = ClientGlobals.moduleManager.bowAimbot;
               if (ClientGlobals.rotationManager.checkModule((Module)bowAimbot)) {
                  break searchBlock;
               }
            }

            if (!((Module)ClientGlobals.moduleManager.autoRod).isToggled() || ClientGlobals.moduleManager.autoRod.getTargetEntity() == null) {
               break guardBlock;
            }

            AutoRod autoRod = ClientGlobals.moduleManager.autoRod;
            if (!ClientGlobals.rotationManager.checkModule((Module)autoRod)) {
               break guardBlock;
            }
         }

         factor4 = ClientGlobals.rotationManager.getRotation3().yaw
            + (ClientGlobals.rotationManager.getRotation2().yaw - ClientGlobals.rotationManager.getRotation3().yaw) * minecraft.getTimer().scaleFactor6;
         factor5 = ClientGlobals.rotationManager.getRotation3().pitch
            + (ClientGlobals.rotationManager.getRotation2().pitch - ClientGlobals.rotationManager.getRotation3().pitch) * minecraft.getTimer().scaleFactor6;
      }

      this.value14 = livingEntity.lastTickPosX + (livingEntity.posX - livingEntity.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6;
      this.value7 = livingEntity.lastTickPosY + (double)livingEntity.getHeight2() + (livingEntity.posY - livingEntity.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6;
      this.value11 = livingEntity.lastTickPosZ + (livingEntity.posZ - livingEntity.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6;
      float factor6 = this.factor / 2.0F;
      float factor7 = this.factor3;
      this.value9 = this.value14;
      this.value10 = this.value7;
      this.value5 = this.value11;
      this.axisAlignedBB = new AxisAlignedBB(
         this.value14 - (double)factor6,
         this.value7,
         this.value11 - (double)factor6,
         this.value14 + (double)factor6,
         this.value7 + (double)factor7,
         this.value11 + (double)factor6
      );
      this.value14 = this.value14 + (double)(MathHelper.cos(factor4 / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
      this.value7 -= 0.1F;
      this.value11 = this.value11 + (double)(MathHelper.abs(factor4 / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
      this.axisAlignedBB = new AxisAlignedBB(
         this.value14 - (double)factor6,
         this.value7,
         this.value11 - (double)factor6,
         this.value14 + (double)factor6,
         this.value7 + (double)factor7,
         this.value11 + (double)factor6
      );
      float factor8 = item instanceof ItemBow ? 1.0F : 0.4F;
      this.value13 = (double)(-MathHelper.abs(factor4 / 180.0F * (float) Math.PI) * MathHelper.cos(factor5 / 180.0F * (float) Math.PI) * factor8);
      this.value2 = (double)(-MathHelper.abs((factor5 + this.computeFactorByItemStack(stack)) / 180.0F * (float) Math.PI) * factor8);
      this.value3 = (double)(MathHelper.cos(factor4 / 180.0F * (float) Math.PI) * MathHelper.cos(factor5 / 180.0F * (float) Math.PI) * factor8);
      double value16 = this.value13;
      double value17 = this.value2;
      double value18 = this.value3;
      float factor9 = this.computeFactor(stack);
      float factor10 = 1.0F;
      float factor11 = factor9;
      double offset = value18;
      double offset2 = value17;
      double offset3 = value16;
      this.applyDouble(offset3, offset2, offset, factor11, factor10);
   }

   private int computeCount(double posX, double posY, double posZ) {
      double offset = posX;
      double offset2 = posZ;

      for (int index = 0; index < 300; index++) {
         offset *= offset2;
         if (offset + 2.0 > posY && offset - 2.0 < posY) {
            return index;
         }

         offset2 = offset2 * 0.99F - 0.05F;
      }

      return -1;
   }

   public int predictInterceptTick(int count4, EntityLivingBase livingEntity, AxisAlignedBB boundingBox) {
      for (int index = 0; index < count4; index++) {
         this.isActiveAsFlag();
         if (this.enabled2) {
            return Integer.MAX_VALUE;
         }

         MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
            .getMovingObjectPosition7(
               this.value14, this.value7, this.value11, this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3, false, true, false
            );
         Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
         Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
         if (movingObjectPosition2 != null) {
            this.movingObjectPosition = movingObjectPosition2;
            return Integer.MAX_VALUE;
         }

         EntityLivingBase livingEntity2 = null;
         double offset = 0.0;
         if (this.entityLivingBase != null && livingEntity.isActive5() && livingEntity != this.entityLivingBase) {
            float factor4 = 0.3F;
            MovingObjectPosition hitResult = boundingBox.offset((double)factor4, (double)factor4, (double)factor4).calculateIntercept(vec2, vec);
            if (hitResult != null && (vec2.dotProduct(hitResult.hitVec) < offset || offset == 0.0)) {
               livingEntity2 = livingEntity;
            }
         }

         if (livingEntity2 != null) {
            return index;
         }

         this.value14 = this.value14 + this.value13;
         this.value7 = this.value7 + this.value2;
         this.value11 = this.value11 + this.value3;
         float factor5 = 0.92F;
         double offset2 = 0.0;

         for (int index2 = 0; index2 < 5; index2++) {
            AxisAlignedBB axisAlignedBB2 = this.axisAlignedBB;
            double offset3 = axisAlignedBB2.maxY - axisAlignedBB2.minY;
            double offset4 = axisAlignedBB2.minY + offset3 * (double)index2 / (double)5;
            double offset5 = axisAlignedBB2.minY + offset3 * (double)(index2 + 1) / (double)5;
            AxisAlignedBB boundingBox2 = new AxisAlignedBB(axisAlignedBB2.minX, offset4, axisAlignedBB2.minZ, axisAlignedBB2.maxX, offset5, axisAlignedBB2.maxZ);
            if (minecraft.theWorld.matchesAxisAlignedBBByAxisAlignedBB(boundingBox2, Material.coreItemEntry1)) {
               offset2 += 1.0 / (double)5;
            }
         }

         double offset6 = offset2 * 2.0 - 1.0;
         this.value2 += 0.04F * offset6;
         if (offset2 > 0.0) {
            factor5 = (float)((double)factor5 * 0.9);
            this.value2 = this.value2 + this.value2 * 0.8;
         }

         this.value13 *= (double)factor5;
         this.value2 *= (double)factor5;
         this.value3 *= (double)factor5;
         double value17 = this.value11;
         double value18 = this.value7;
         double value19 = this.value14;
         this.applyDouble(value19, value18, value17);
         if (this.value7 < boundingBox.minY && this.value2 < 0.0) {
            return Integer.MAX_VALUE;
         }
      }

      return Integer.MAX_VALUE;
   }

   private Vec3 resolveVec3(Block block2, Vec3 vec, BlockPos blockPos) {
      if (block2 instanceof BlockLiquid) {
         vec.getVec36(((BlockLiquid)block2).resolveVec3(minecraft.theWorld, blockPos));
      }

      return vec;
   }

   public void applyEntityLivingBase3(EntityLivingBase livingEntity, ItemStack stack, float factor4, float factor5, double posX, double posY, double posZ) {
      this.vec3 = new Vec3(-1.0, -1.0, -1.0);
      this.itemStack = stack;
      Item item = stack.getItem();
      if (item instanceof ItemBow) {
         this.factor = 0.5F;
         this.factor3 = 0.5F;
      }

      this.entityLivingBase = livingEntity;
      this.value14 = posX;
      this.value7 = posY + (double)livingEntity.getHeight2();
      this.value11 = posZ;
      float factor6 = this.factor / 2.0F;
      float factor7 = this.factor3;
      this.value9 = this.value14;
      this.value10 = this.value7;
      this.value5 = this.value11;
      this.axisAlignedBB = new AxisAlignedBB(
         this.value14 - (double)factor6,
         this.value7,
         this.value11 - (double)factor6,
         this.value11 + (double)factor6,
         this.value7 + (double)factor7,
         this.value11 + (double)factor6
      );
      this.value14 = this.value14 + (double)(MathHelper.cos(factor4 / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
      this.value7 -= 0.1F;
      this.value11 = this.value11 + (double)(MathHelper.abs(factor4 / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
      this.axisAlignedBB = new AxisAlignedBB(
         this.value14 - (double)factor6,
         this.value7,
         this.value11 - (double)factor6,
         this.value11 + (double)factor6,
         this.value7 + (double)factor7,
         this.value11 + (double)factor6
      );
      float factor8 = item instanceof ItemBow ? 1.0F : 0.4F;
      this.value13 = (double)(-MathHelper.abs(factor4 / 180.0F * (float) Math.PI) * MathHelper.cos(factor5 / 180.0F * (float) Math.PI) * factor8);
      this.value2 = (double)(-MathHelper.abs((factor5 + this.computeFactorByItemStack(stack)) / 180.0F * (float) Math.PI) * factor8);
      this.value3 = (double)(MathHelper.cos(factor4 / 180.0F * (float) Math.PI) * MathHelper.cos(factor5 / 180.0F * (float) Math.PI) * factor8);
      double value16 = this.value13;
      double value17 = this.value2;
      double value18 = this.value3;
      float factor9 = this.computeFactor(stack);
      float factor10 = 1.0F;
      float factor11 = factor9;
      double offset = value18;
      double offset2 = value17;
      double offset3 = value16;
      this.applyDouble(offset3, offset2, offset, factor11, factor10);
   }

   private boolean matchesMaterial(double posX, double posX2, double posX3, double posX4, double posY, double posZ, Material material) {
      int count4 = MathHelper.ceiling_double_int(posX);
      int count5 = MathHelper.ceiling_double_int(posX4 + 1.0);
      int count6 = MathHelper.ceiling_double_int(posX2);
      int count7 = MathHelper.ceiling_double_int(posY + 1.0);
      int count8 = MathHelper.ceiling_double_int(posX3);
      int count9 = MathHelper.ceiling_double_int(posZ + 1.0);
      if (!minecraft.theWorld.checkIndex(count4, count6, count8, count5, count7, count9, true)) {
         return false;
      } else {
         boolean flag = false;
         Vec3 vec = new Vec3(0.0, 0.0, 0.0);
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

         outerLoop:
         for (int index = 8000; count4 < count5; count4++) {
            for (int index2 = count6; index2 < count7; index2++) {
               for (int index3 = count8; index3 < count9; index3++) {
                  IBlockState iBlockState = minecraft.theWorld.getIBlockState(count4, index2, index3);
                  Block block2 = iBlockState.getBlock();
                  if (block2.getMaterial() == material) {
                     double offset = (double)(
                        (float)(index2 + 1) - BlockLiquid.computeFactor((Integer)iBlockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry))
                     );
                     if ((double)count7 >= offset) {
                        auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count4, index2, index3);
                        flag = true;
                        vec = this.resolveVec3(block2, vec, auxiliaryBlockPosAdapter);
                     }
                  }

                  if (--index == 0) {
                     break outerLoop;
                  }
               }
            }
         }

         if (vec.getValueAsDouble() > 0.0) {
            Vec3 vec2 = vec.normalize();
            double offset2 = 0.014;
            this.value13 = this.value13 + vec2.xCoord * offset2;
            this.value2 = this.value2 + vec2.yCoord * offset2;
            this.value3 = this.value3 + vec2.zCoord * offset2;
         }

         return flag;
      }
   }

   private void applyDoubleByDouble(double posX, double posY, double posZ) {
      float factor4 = this.factor / 2.0F;
      float factor5 = this.factor3;
      this.value12 = posX - (double)factor4;
      this.value4 = posY;
      this.value15 = posZ - (double)factor4;
      this.value6 = posX + (double)factor4;
      this.value8 = posY + (double)factor5;
      this.value = posZ + (double)factor4;
   }

   public void updateEntityArrow(EntityArrow entityArrow2) {
      this.entityLivingBase = minecraft.thePlayer;
      this.value14 = entityArrow2.posX;
      this.value7 = entityArrow2.posY;
      this.value11 = entityArrow2.posZ;
      this.value9 = entityArrow2.lastTickPosX;
      this.value10 = entityArrow2.lastTickPosY;
      this.value5 = entityArrow2.lastTickPosZ;
      this.value13 = entityArrow2.motionX;
      this.value2 = entityArrow2.motionY;
      this.value3 = entityArrow2.motionZ;
      this.vec3 = new Vec3((double)entityArrow2.getCount16(), (double)entityArrow2.getCount11(), (double)entityArrow2.getCount17());
      this.axisAlignedBB = new AxisAlignedBB(
         entityArrow2.getBoundingBox2().minX,
         entityArrow2.getBoundingBox2().minY,
         entityArrow2.getBoundingBox2().minZ,
         entityArrow2.getBoundingBox2().maxX,
         entityArrow2.getBoundingBox2().maxY,
         entityArrow2.getBoundingBox2().maxZ
      );
      this.count3 = entityArrow2.getCount14();
      this.enabled3 = entityArrow2.isInWater();
      this.enabled = entityArrow2.enabled2;
      this.factor2 = entityArrow2.fallDistance;
      this.block = entityArrow2.getBlock();
      this.count = entityArrow2.getCount19();
      this.enabled2 = entityArrow2.isEnabled11();
      this.count2 = entityArrow2.count15;
      this.factor = entityArrow2.width;
      this.factor3 = entityArrow2.height;
      this.entityArrow = entityArrow2;
   }

   private double computeValue(double offset, double offset2) {
      return 0.0;
   }

   public void applyEntityLivingBase4(EntityLivingBase livingEntity, ItemStack stack, float factor4, float factor5) {
      double posZ = livingEntity.posZ;
      double posY = livingEntity.posY;
      double posX = livingEntity.posX;
      this.applyEntityLivingBase(livingEntity, stack, posX, posY, posZ, factor4, factor5);
   }

   public boolean matchesEntityLivingBase(int count4, int count5, EntityLivingBase livingEntity, AxisAlignedBB boundingBox, byte byteValue, int count6, double offset, double offset2) {
      for (int index = 0; index < count4; index++) {
         if (this.value2 < 0.0 && this.value7 < boundingBox.minY) {
            return false;
         }

         this.isActiveAsFlag();
         IBlockState iBlockState = minecraft.theWorld.getIBlockState2(this.vec3.xCoord, this.vec3.yCoord, this.vec3.zCoord);
         Block block2 = iBlockState.getBlock();
         if (block2.getMaterial() != Material.coreItemEntry) {
            int count7 = MathHelper.ceiling_double_int(this.vec3.xCoord);
            int count8 = MathHelper.ceiling_double_int(this.vec3.yCoord);
            int count9 = MathHelper.ceiling_double_int(this.vec3.zCoord);
            block2.processAction(minecraft.theWorld, count7, count8, count9);
            AxisAlignedBB boundingBox2 = block2.processAction(minecraft.theWorld, iBlockState, count7, count8, count9);
            if (boundingBox2 != null && boundingBox2.checkDouble(this.value14, this.value7, this.value11)) {
               this.enabled2 = true;
            }
         }

         if (this.count2 > 0) {
            this.count2--;
         }

         if (this.enabled2) {
            if (this.count3 > 2) {
               return false;
            }
         } else {
            MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
               .getMovingObjectPosition7(
                  this.value14,
                  this.value7,
                  this.value11,
                  this.value14 + this.value13,
                  this.value7 + this.value2,
                  this.value11 + this.value3,
                  false,
                  true,
                  false
               );
            Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
            Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
            if (movingObjectPosition2 != null) {
               this.movingObjectPosition = movingObjectPosition2;
            }

            Entity entity = null;
            List list = minecraft.theWorld
               .resolveListByEntity(this.entityArrow, this.axisAlignedBB.contract(this.value13, this.value2, this.value3).offset(1.0, 1.0, 1.0));
            double offset3 = 0.0;

            for (int index2 = 0; index2 < list.size(); index2++) {
               Entity entity2 = (Entity)list.get(index2);
               if (this.entityLivingBase != null && entity2.isActive5() && entity2 != this.entityLivingBase) {
                  float factor4 = 0.3F;
                  MovingObjectPosition hitResult = entity2.getBoundingBox2().offset((double)factor4, (double)factor4, (double)factor4).calculateIntercept(vec2, vec);
                  if (hitResult != null) {
                     double offset4 = vec2.dotProduct(hitResult.hitVec);
                     if (offset4 < offset3 || offset3 == 0.0) {
                        entity = entity2;
                        offset3 = offset4;
                     }
                  }
               }
            }

            if (entity != null) {
               MovingObjectPosition hitResult2 = new MovingObjectPosition(entity);
               this.movingObjectPosition = hitResult2;
               return entity.getEntityId() == livingEntity.getEntityId();
            }

            if (movingObjectPosition2 != null && movingObjectPosition2.entityHit == null) {
               return false;
            }
         }

         this.value14 = this.value14 + this.value13;
         this.value7 = this.value7 + this.value2;
         this.value11 = this.value11 + this.value3;
         float factor5 = 0.99F;
         float factor6 = 0.05F;
         if (this.enabled3) {
            factor5 = 0.6F;
         }

         this.value13 *= (double)factor5;
         this.value2 *= (double)factor5;
         this.value3 *= (double)factor5;
         this.value2 -= (double)factor6;
         double value16 = this.value11;
         double value17 = this.value7;
         double value18 = this.value14;
         this.applyDouble(value18, value17, value16);
         if (this.value7 < 0.0) {
            index = count4;
         }
      }

      return false;
   }

   public void applyIndex(int count4) {
      for (int index = 0; index < count4; index++) {
         this.value9 = this.value14;
         this.value10 = this.value7;
         this.value5 = this.value11;
         this.isActive();
         if (this.enabled2) {
            if (minecraft.theWorld.getIBlockState2(this.vec3.xCoord, this.vec3.yCoord, this.vec3.zCoord).getBlock() == this.block) {
               this.count3++;
               if (this.count3 > 2) {
                  index = count4;
               }
            }

            this.enabled2 = false;
            this.value13 = this.value13 * (double)(this.random.nextFloat() * 0.2F);
            this.value2 = this.value2 * (double)(this.random.nextFloat() * 0.2F);
            this.value3 = this.value3 * (double)(this.random.nextFloat() * 0.2F);
            this.count3 = 0;
            this.count = 0;
         } else {
            this.count++;
         }

         MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
            .getMovingObjectPosition(
               this.value14, this.value7, this.value11, this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3
            );
         if (movingObjectPosition2 != null
            && (
               movingObjectPosition2.getBlockPos() == null
                  || !(
                     minecraft.theWorld.getIBlockState(movingObjectPosition2.getBlockPos().getX(), movingObjectPosition2.getBlockPos().getY(), movingObjectPosition2.getBlockPos().getZ()).getBlock() instanceof BlockLiquid
                  )
            )) {
            this.movingObjectPosition = movingObjectPosition2;
            index = count4;
         }

         this.value14 = this.value14 + this.value13;
         this.value7 = this.value7 + this.value2;
         this.value11 = this.value11 + this.value3;
         float factor4 = 0.99F;
         float factor5;
         if (this.entityThrowable == null) {
            ItemStack itemStack2 = this.itemStack;
            factor5 = this.computeFactor2(itemStack2);
         } else {
            factor5 = this.entityThrowable.getFactorAsFloat();
         }

         float factor6 = factor5;
         if (this.enabled3) {
            factor4 = 0.8F;
         }

         this.value13 *= (double)factor4;
         this.value2 *= (double)factor4;
         this.value3 *= (double)factor4;
         this.value2 -= (double)factor6;
         double value16 = this.value11;
         double value17 = this.value7;
         double value18 = this.value14;
         this.applyDoubleByDouble(value18, value17, value16);
         if (this.value7 < 0.0) {
            index = count4;
         }
      }
   }

   private void applyDouble(double posX, double posY, double posZ) {
      float factor4 = this.factor / 2.0F;
      float factor5 = this.factor3;
      this.axisAlignedBB = new AxisAlignedBB(posX - (double)factor4, posY, posZ - (double)factor4, posX + (double)factor4, posY + (double)factor5, posZ + (double)factor4);
   }

   public int getIndex(char character, int count4, long value16, EntityLivingBase livingEntity, AxisAlignedBB boundingBox) {
      for (int index = 0; index < count4; index++) {
         this.value9 = this.value14;
         this.value10 = this.value7;
         this.value5 = this.value11;
         this.isActiveAsFlag();
         IBlockState iBlockState = minecraft.theWorld.getIBlockState2(this.vec3.xCoord, this.vec3.yCoord, this.vec3.zCoord);
         Block block2 = iBlockState.getBlock();
         if (block2.getMaterial() != Material.coreItemEntry) {
            int count5 = MathHelper.ceiling_double_int(this.vec3.xCoord);
            int count6 = MathHelper.ceiling_double_int(this.vec3.yCoord);
            int count7 = MathHelper.ceiling_double_int(this.vec3.zCoord);
            block2.processAction(minecraft.theWorld, count5, count6, count7);
            AxisAlignedBB boundingBox2 = block2.processAction(minecraft.theWorld, iBlockState, count5, count6, count7);
            if (boundingBox2 != null && boundingBox2.checkDouble(this.value14, this.value7, this.value11)) {
               this.enabled2 = true;
            }
         }

         if (this.count2 > 0) {
            this.count2--;
         }

         if (this.enabled2) {
            return Integer.MAX_VALUE;
         }

         MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
            .getMovingObjectPosition7(
               this.value14, this.value7, this.value11, this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3, false, true, false
            );
         Vec3 vec = new Vec3(this.value14, this.value7, this.value11);
         Vec3 vec2 = new Vec3(this.value14 + this.value13, this.value7 + this.value2, this.value11 + this.value3);
         if (this.entityLivingBase != null && livingEntity.isActive5() && livingEntity != this.entityLivingBase) {
            MovingObjectPosition hitResult = boundingBox.calculateIntercept(vec2, vec);
            if (hitResult != null) {
               if (movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK && vec.getCoordinate4(hitResult.hitVec) > vec.getCoordinate4(movingObjectPosition2.hitVec)) {
                  return Integer.MAX_VALUE;
               }

               return index;
            }
         }

         if (movingObjectPosition2 != null && movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK && minecraft.theWorld.getBlockState(movingObjectPosition2.getBlockPos()).getBlock().processAction10()) {
            return Integer.MAX_VALUE;
         }

         this.value14 = this.value14 + this.value13;
         this.value7 = this.value7 + this.value2;
         this.value11 = this.value11 + this.value3;
         float factor4 = 0.99F;
         float factor5 = 0.05F;
         if (this.enabled3) {
            factor4 = 0.6F;
         }

         this.value13 *= (double)factor4;
         this.value2 *= (double)factor4;
         this.value3 *= (double)factor4;
         this.value2 -= (double)factor5;
         double value17 = this.value11;
         double value18 = this.value7;
         double value19 = this.value14;
         this.applyDouble(value19, value18, value17);
         if (this.value7 < boundingBox.minY && this.value2 < 0.0) {
            return Integer.MAX_VALUE;
         }
      }

      return Integer.MAX_VALUE;
   }
}

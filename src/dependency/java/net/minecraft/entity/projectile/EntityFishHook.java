package net.minecraft.entity.projectile;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.CombatWeightProperty;
import client.setting.LocalOptionType;
import client.util.DampenerRanker;
import client.util.LvlManager;
import client.util.SmoothSystem;
import client.util.StatCraftItemManager;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityFishHook extends Entity {
   public static final List list2 = Arrays.asList(
      new DampenerRanker(new ItemStack(Items.leatherBoots), 10).withFactor(0.9F),
      new DampenerRanker(new ItemStack(Items.leather), 10),
      new DampenerRanker(new ItemStack(Items.bone), 10),
      new DampenerRanker(new ItemStack(Items.potion), 10),
      new DampenerRanker(new ItemStack(Items.string), 5),
      new DampenerRanker(new ItemStack(Items.fishingRod), 2).withFactor(0.9F),
      new DampenerRanker(new ItemStack(Items.bowl), 10),
      new DampenerRanker(new ItemStack(Items.stick), 5),
      new DampenerRanker(new ItemStack(Items.dye, 10, LocalOptionType.BLACK.getCount2()), 1),
      new DampenerRanker(new ItemStack(Blocks.blockTripwireHook), 10),
      new DampenerRanker(new ItemStack(Items.rottenFlesh), 10)
   );
   public static final List list3 = Arrays.asList(
      new DampenerRanker(new ItemStack(Blocks.block33), 1),
      new DampenerRanker(new ItemStack(Items.nameTag), 1),
      new DampenerRanker(new ItemStack(Items.saddle), 1),
      new DampenerRanker(new ItemStack(Items.bow), 1).withFactor(0.25F).getDampenerRanker(),
      new DampenerRanker(new ItemStack(Items.fishingRod), 1).withFactor(0.25F).getDampenerRanker(),
      new DampenerRanker(new ItemStack(Items.book), 1).getDampenerRanker()
   );
   public static final List list = Arrays.asList(
      new DampenerRanker(new ItemStack(Items.fish, 1, CombatWeightProperty.COD.getCount2()), 60),
      new DampenerRanker(new ItemStack(Items.fish, 1, CombatWeightProperty.SALMON.getCount2()), 25),
      new DampenerRanker(new ItemStack(Items.fish, 1, CombatWeightProperty.CLOWNFISH.getCount2()), 2),
      new DampenerRanker(new ItemStack(Items.fish, 1, CombatWeightProperty.PUFFERFISH.getCount2()), 13)
   );
   public int count23 = -1;
   public int count21 = -1;
   public int count11 = -1;
   public Block block;
   public boolean enabled12;
   public int count12;
   public EntityPlayer targetPlayer;
   public int count14;
   public int count15;
   public int count20;
   public int count22;
   public int count16;
   public float factor6;
   public Entity entity2;
   public int count18;
   public double value7;
   public double value11;
   public double value15;
   public double value17;
   public double value20;
   public double value13;
   public double value8;
   public double value6;

   @Override
   public boolean checkDouble(double offset) {
      double offset2 = this.getBoundingBox2().getValue() * 4.0;
      if (Double.isNaN(offset2)) {
         offset2 = 4.0;
      }

      double offset3 = offset2 * 64.0;
      return offset < offset3 * offset3;
   }

   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2) {
      float factor3 = MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
      double offset = posX / (double)factor3;
      double offset2 = posY / (double)factor3;
      double offset3 = posZ / (double)factor3;
      offset += this.random.nextGaussian() * 0.0075F * (double)factor2;
      offset2 += this.random.nextGaussian() * 0.0075F * (double)factor2;
      offset3 += this.random.nextGaussian() * 0.0075F * (double)factor2;
      offset *= (double)factor;
      offset2 *= (double)factor;
      offset3 *= (double)factor;
      this.motionX = offset;
      this.motionY = offset2;
      this.motionZ = offset3;
      float factor4 = MathHelper.sqrt_double(offset * offset + offset3 * offset3);
      this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.getDouble6(offset, offset3) * 180.0 / Math.PI);
      this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.getDouble6(offset2, (double)factor4) * 180.0 / Math.PI);
      this.count14 = 0;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      this.count23 = tagCompound.processValue("xTile");
      this.count21 = tagCompound.processValue("yTile");
      this.count11 = tagCompound.processValue("zTile");
      if (tagCompound.matchesText("inTile", 8)) {
         this.block = Block.getBlockFromName(tagCompound.resolveText("inTile"));
      } else {
         this.block = Block.processAction3(tagCompound.computeByteCode("inTile") & 255);
      }

      this.count12 = tagCompound.computeByteCode("shake") & 255;
      this.enabled12 = tagCompound.computeByteCode("inGround") == 1;
   }

   public int getCount4() {
      if (this.worldObj.state) {
         return 0;
      } else {
         byte byteValue = 0;
         if (this.entity2 != null) {
            double offset = this.targetPlayer.posX - this.posX;
            double offset2 = this.targetPlayer.posY - this.posY;
            double offset3 = this.targetPlayer.posZ - this.posZ;
            double offset4 = (double)MathHelper.sqrt_double(offset * offset + offset2 * offset2 + offset3 * offset3);
            double offset5 = 0.1;
            this.entity2.motionX += offset * offset5;
            this.entity2.motionY = this.entity2.motionY + offset2 * offset5 + (double)MathHelper.sqrt_double(offset4) * 0.08;
            this.entity2.motionZ += offset3 * offset5;
            byteValue = 3;
         } else if (this.count20 > 0) {
            EntityItem entityItem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, this.getItemStack());
            double offset6 = this.targetPlayer.posX - this.posX;
            double offset7 = this.targetPlayer.posY - this.posY;
            double offset8 = this.targetPlayer.posZ - this.posZ;
            double offset9 = (double)MathHelper.sqrt_double(offset6 * offset6 + offset7 * offset7 + offset8 * offset8);
            double offset10 = 0.1;
            entityItem.motionX = offset6 * offset10;
            entityItem.motionY = offset7 * offset10 + (double)MathHelper.sqrt_double(offset9) * 0.08;
            entityItem.motionZ = offset8 * offset10;
            this.worldObj.matchesEntity(entityItem);
            this.targetPlayer
               .worldObj
               .matchesEntity(
                  new EntityXporb(
                     this.targetPlayer.worldObj,
                     this.targetPlayer.posX,
                     this.targetPlayer.posY + 0.5,
                     this.targetPlayer.posZ + 0.5,
                     this.random.nextInt(6) + 1
                  )
               );
            byteValue = 1;
         }

         if (this.enabled12) {
            byteValue = 2;
         }

         this.updateState9();
         this.targetPlayer.internalEntityAdapter = null;
         return byteValue;
      }
   }

   @Override
   public void applyDouble2(double posX, double posY, double posZ) {
      this.value13 = this.motionX = posX;
      this.value8 = this.motionY = posY;
      this.value6 = this.motionZ = posZ;
   }

   public static List getList() {
      return list;
   }

   @Override
   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2, int count, boolean flag) {
      this.value7 = posX;
      this.value11 = posY;
      this.value15 = posZ;
      this.value17 = (double)factor;
      this.value20 = (double)factor2;
      this.count18 = count;
      this.motionX = this.value13;
      this.motionY = this.value8;
      this.motionZ = this.value6;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      tagCompound.put("xTile", (short)this.count23);
      tagCompound.put("yTile", (short)this.count21);
      tagCompound.put("zTile", (short)this.count11);
      ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(this.block);
      tagCompound.put("inTile", object == null ? "" : object.toString());
      tagCompound.put("shake", (byte)this.count12);
      tagCompound.put("inGround", (byte)(this.enabled12 ? 1 : 0));
   }

   private ItemStack getItemStack() {
      float factor = this.worldObj.random.nextFloat();
      int count = LvlManager.computeCount5(this.targetPlayer);
      int count2 = LvlManager.computeCount9(this.targetPlayer);
      float factor2 = 0.1F - (float)count * 0.025F - (float)count2 * 0.01F;
      float factor3 = 0.05F + (float)count * 0.01F - (float)count2 * 0.01F;
      float factor4 = MathHelper.clamp_float(factor2, 0.0F, 1.0F);
      float factor5 = MathHelper.clamp_float(factor3, 0.0F, 1.0F);
      if (factor < factor4) {
         this.targetPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager45);
         return ((DampenerRanker)SmoothSystem.resolveDampenerSorter(this.random, list2)).resolveItemStack(this.random);
      } else if (factor - factor4 < factor5) {
         this.targetPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager29);
         return ((DampenerRanker)SmoothSystem.resolveDampenerSorter(this.random, list3)).resolveItemStack(this.random);
      } else {
         this.targetPlayer.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager34);
         return ((DampenerRanker)SmoothSystem.resolveDampenerSorter(this.random, list)).resolveItemStack(this.random);
      }
   }

   @Override
   public void updateState9() {
      super.updateState9();
      if (this.targetPlayer != null) {
         this.targetPlayer.internalEntityAdapter = null;
      }
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.count18 > 0) {
         double offset = this.posX + (this.value7 - this.posX) / (double)this.count18;
         double offset2 = this.posY + (this.value11 - this.posY) / (double)this.count18;
         double offset3 = this.posZ + (this.value15 - this.posZ) / (double)this.count18;
         double doubleItem = MathHelper.getDouble4(this.value17 - (double)this.rotationYaw);
         this.rotationYaw = (float)((double)this.rotationYaw + doubleItem / (double)this.count18);
         this.rotationPitch = (float)((double)this.rotationPitch + (this.value20 - (double)this.rotationPitch) / (double)this.count18);
         this.count18--;
         this.processDouble2(offset, offset2, offset3);
         this.processFloat4(this.rotationYaw, this.rotationPitch);
      } else {
         if (!this.worldObj.state) {
            ItemStack stack = this.targetPlayer.getHeldItem();
            if (this.targetPlayer.isDead
               || !this.targetPlayer.canExecute()
               || stack == null
               || stack.getItem() != Items.fishingRod
               || this.getCoordinate(this.targetPlayer) > 1024.0) {
               this.updateState9();
               this.targetPlayer.internalEntityAdapter = null;
               return;
            }

            if (this.entity2 != null) {
               if (!this.entity2.isDead) {
                  this.posX = this.entity2.posX;
                  double height = (double)this.entity2.height;
                  this.posY = this.entity2.getBoundingBox2().minY + height * 0.8;
                  this.posZ = this.entity2.posZ;
                  return;
               }

               this.entity2 = null;
            }
         }

         if (this.count12 > 0) {
            this.count12--;
         }

         if (this.enabled12) {
            if (this.worldObj.getBlockState(new BlockPos(this.count23, this.count21, this.count11)).getBlock() == this.block) {
               this.count14++;
               if (this.count14 == 1200) {
                  this.updateState9();
               }

               return;
            }

            this.enabled12 = false;
            this.motionX = this.motionX * (double)(this.random.nextFloat() * 0.2F);
            this.motionY = this.motionY * (double)(this.random.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (double)(this.random.nextFloat() * 0.2F);
            this.count14 = 0;
            this.count15 = 0;
         } else {
            this.count15++;
         }

         Vec3 vec = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         MovingObjectPosition movingObjectPosition = this.worldObj.getMovingObjectPosition2(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord);
         Vec3 vec3 = new Vec3(this.posX, this.posY, this.posZ);
         Vec3 vec4 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (movingObjectPosition != null) {
            vec4 = new Vec3(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
         }

         Entity entity = null;
         List list4 = this.worldObj.resolveListByEntity(this, this.getBoundingBox2().contract(this.motionX, this.motionY, this.motionZ).offset(1.0, 1.0, 1.0));
         double offset4 = 0.0;

         for (int index = 0; index < list4.size(); index++) {
            Entity entity3 = (Entity)list4.get(index);
            if (entity3.isActive5() && (entity3 != this.targetPlayer || this.count15 >= 5)) {
               float factor = 0.3F;
               MovingObjectPosition hitResult = entity3.getBoundingBox2().offset((double)factor, (double)factor, (double)factor).calculateIntercept(vec3, vec4);
               if (hitResult != null) {
                  double offset5 = vec3.dotProduct(hitResult.hitVec);
                  if (offset5 < offset4 || offset4 == 0.0) {
                     entity = entity3;
                     offset4 = offset5;
                  }
               }
            }
         }

         if (entity != null) {
            movingObjectPosition = new MovingObjectPosition(entity);
         }

         if (movingObjectPosition != null) {
            if (movingObjectPosition.entityHit != null) {
               if (movingObjectPosition.entityHit.matchesDamageSource(DamageSource.resolveDamageSource2(this, this.targetPlayer), 0.0F)) {
                  this.entity2 = movingObjectPosition.entityHit;
               }
            } else {
               this.enabled12 = true;
            }
         }

         if (!this.enabled12) {
            this.applyDouble(this.motionX, this.motionY, this.motionZ);
            float factor2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(MathHelper.getDouble6(this.motionX, this.motionZ) * 180.0 / Math.PI);
            this.rotationPitch = (float)(MathHelper.getDouble6(this.motionY, (double)factor2) * 180.0 / Math.PI);

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
            float factor3 = 0.92F;
            if (this.onGround || this.isCollidedHorizontally) {
               factor3 = 0.5F;
            }

            double offset6 = 0.0;

            for (int index2 = 0; index2 < 5; index2++) {
               AxisAlignedBB boundingBox = this.getBoundingBox2();
               double offset7 = boundingBox.maxY - boundingBox.minY;
               double offset8 = boundingBox.minY + offset7 * (double)index2 / (double)5;
               double offset9 = boundingBox.minY + offset7 * (double)(index2 + 1) / (double)5;
               AxisAlignedBB boundingBox2 = new AxisAlignedBB(boundingBox.minX, offset8, boundingBox.minZ, boundingBox.maxX, offset9, boundingBox.maxZ);
               if (this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(boundingBox2, Material.coreItemEntry1)) {
                  offset6 += 1.0 / (double)5;
               }
            }

            if (!this.worldObj.state && offset6 > 0.0) {
               WorldServer world = (WorldServer)this.worldObj;
               int count = 1;
               BlockPos blockPos = new BlockPos(this).up();
               if (this.random.nextFloat() < 0.25F && this.worldObj.matchesBlockPos(blockPos)) {
                  count = 2;
               }

               if (this.random.nextFloat() < 0.5F && !this.worldObj.checkIndex2(blockPos.getX(), blockPos.getY(), blockPos.getZ())) {
                  count--;
               }

               if (this.count20 > 0) {
                  this.count20--;
                  if (this.count20 <= 0) {
                     this.count22 = 0;
                     this.count16 = 0;
                  }
               } else if (this.count16 > 0) {
                  this.count16 -= count;
                  if (this.count16 <= 0) {
                     this.motionY -= 0.2F;
                     this.applyText("random.splash", 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                     float factor4 = (float)MathHelper.ceiling_double_int(this.getBoundingBox2().minY);
                     world.applyAttackEffectsExplosionNorm(
                        AttackEffectsExplosionNormalType.WATER_BUBBLE,
                        this.posX,
                        (double)(factor4 + 1.0F),
                        this.posZ,
                        (int)(1.0F + this.width * 20.0F),
                        (double)this.width,
                        0.0,
                        (double)this.width,
                        0.2F,
                        new int[0]
                     );
                     world.applyAttackEffectsExplosionNorm(
                        AttackEffectsExplosionNormalType.WATER_WAKE,
                        this.posX,
                        (double)(factor4 + 1.0F),
                        this.posZ,
                        (int)(1.0F + this.width * 20.0F),
                        (double)this.width,
                        0.0,
                        (double)this.width,
                        0.2F,
                        new int[0]
                     );
                     this.count20 = MathHelper.nextInt(this.random, 10, 30);
                  } else {
                     this.factor6 = (float)((double)this.factor6 + this.random.nextGaussian() * 4.0);
                     float factor5 = this.factor6 * (float) (Math.PI / 180.0);
                     float factor7 = MathHelper.abs(factor5);
                     float factor8 = MathHelper.cos(factor5);
                     double offset10 = this.posX + (double)(factor7 * (float)this.count16 * 0.1F);
                     double offset11 = (double)((float)MathHelper.ceiling_double_int(this.getBoundingBox2().minY) + 1.0F);
                     double offset12 = this.posZ + (double)(factor8 * (float)this.count16 * 0.1F);
                     Block block2 = world.getBlockState(new BlockPos((int)offset10, (int)offset11 - 1, (int)offset12)).getBlock();
                     if (block2 == Blocks.blockLava2 || block2 == Blocks.blockFlowingLava) {
                        if (this.random.nextFloat() < 0.15F) {
                           world.applyAttackEffectsExplosionNorm(
                              AttackEffectsExplosionNormalType.WATER_BUBBLE, offset10, offset11 - 0.1F, offset12, 1, (double)factor7, 0.1, (double)factor8, 0.0, new int[0]
                           );
                        }

                        float factor9 = factor7 * 0.04F;
                        float factor10 = factor8 * 0.04F;
                        world.applyAttackEffectsExplosionNorm(
                           AttackEffectsExplosionNormalType.WATER_WAKE, offset10, offset11, offset12, 0, (double)factor10, 0.01, (double)(-factor9), 1.0, new int[0]
                        );
                        world.applyAttackEffectsExplosionNorm(
                           AttackEffectsExplosionNormalType.WATER_WAKE, offset10, offset11, offset12, 0, (double)(-factor10), 0.01, (double)factor9, 1.0, new int[0]
                        );
                     }
                  }
               } else if (this.count22 > 0) {
                  this.count22 -= count;
                  float factor11 = 0.15F;
                  if (this.count22 < 20) {
                     factor11 = (float)((double)factor11 + (double)(20 - this.count22) * 0.05);
                  } else if (this.count22 < 40) {
                     factor11 = (float)((double)factor11 + (double)(40 - this.count22) * 0.02);
                  } else if (this.count22 < 60) {
                     factor11 = (float)((double)factor11 + (double)(60 - this.count22) * 0.01);
                  }

                  if (this.random.nextFloat() < factor11) {
                     float factor12 = MathHelper.nextFloat(this.random, 0.0F, 360.0F) * (float) (Math.PI / 180.0);
                     float factor13 = MathHelper.nextFloat(this.random, 25.0F, 60.0F);
                     double offset13 = this.posX + (double)(MathHelper.abs(factor12) * factor13 * 0.1F);
                     double offset14 = (double)((float)MathHelper.ceiling_double_int(this.getBoundingBox2().minY) + 1.0F);
                     double offset15 = this.posZ + (double)(MathHelper.cos(factor12) * factor13 * 0.1F);
                     Block block3 = world.getBlockState(new BlockPos((int)offset13, (int)offset14 - 1, (int)offset15)).getBlock();
                     if (block3 == Blocks.blockLava2 || block3 == Blocks.blockFlowingLava) {
                        world.applyAttackEffectsExplosionNorm(
                           AttackEffectsExplosionNormalType.WATER_SPLASH, offset13, offset14, offset15, 2 + this.random.nextInt(2), 0.1F, 0.0, 0.1F, 0.0, new int[0]
                        );
                     }
                  }

                  if (this.count22 <= 0) {
                     this.factor6 = MathHelper.nextFloat(this.random, 0.0F, 360.0F);
                     this.count16 = MathHelper.nextInt(this.random, 20, 80);
                  }
               } else {
                  this.count22 = MathHelper.nextInt(this.random, 100, 900);
                  this.count22 = this.count22 - LvlManager.computeCount9(this.targetPlayer) * 20 * 5;
               }

               if (this.count20 > 0) {
                  this.motionY = this.motionY - (double)(this.random.nextFloat() * this.random.nextFloat() * this.random.nextFloat()) * 0.2;
               }
            }

            double offset16 = offset6 * 2.0 - 1.0;
            this.motionY += 0.04F * offset16;
            if (offset6 > 0.0) {
               factor3 = (float)((double)factor3 * 0.9);
               this.motionY *= 0.8;
            }

            this.motionX *= (double)factor3;
            this.motionY *= (double)factor3;
            this.motionZ *= (double)factor3;
            this.processDouble2(this.posX, this.posY, this.posZ);
         }
      }
   }

   @Override
   public void updateState7() {
   }

   public EntityFishHook(World world, EntityPlayer player) {
      super(world);
      this.enabled3 = true;
      this.targetPlayer = player;
      this.targetPlayer.internalEntityAdapter = this;
      this.applyFloat(0.25F, 0.25F);
      this.applyDoubleByDouble(player.posX, player.posY + (double)player.getHeight2(), player.posZ, player.rotationYaw, player.rotationPitch);
      this.posX = this.posX - (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.posY -= 0.1F;
      this.posZ = this.posZ - (double)(MathHelper.abs(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
      this.processDouble2(this.posX, this.posY, this.posZ);
      float factor = 0.4F;
      this.motionX = (double)(-MathHelper.abs(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * factor);
      this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * factor);
      this.motionY = (double)(-MathHelper.abs(this.rotationPitch / 180.0F * (float) Math.PI) * factor);
      this.applyDouble(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
   }

   public EntityFishHook(World world) {
      super(world);
      this.applyFloat(0.25F, 0.25F);
      this.enabled3 = true;
   }

   public EntityFishHook(World world, double posX, double posY, double posZ, EntityPlayer player) {
      this(world);
      this.processDouble2(posX, posY, posZ);
      this.enabled3 = true;
      this.targetPlayer = player;
      player.internalEntityAdapter = this;
   }
}

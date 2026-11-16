package net.minecraft.entity;

import client.event.PreRenderSubscriber;
import client.event.SystemListener;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.DisablerUndefinedType;
import client.setting.GameSettingsPeacefulType;
import client.util.AchievementTheEnd2Manager;
import client.util.AdvancedClientManagerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.ExtendedClassEntry;
import client.util.LivingEntityFilter;
import client.util.PathfindManagerAdapter;
import client.util.RotationValidator;
import client.util.SupplementalClientManagerAdapter;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.Items;
import net.minecraft.item.PassiveItemEntry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWitherboss extends EntityMonster implements SystemListener, PreRenderSubscriber {
   @Override
   public IChatComponent getIChatComponent() {
      return this.getChatComponent();
   }
   public float[] factorArray2 = new float[2];
   public float[] factorArray5 = new float[2];
   public float[] factorArray4 = new float[2];
   public float[] factorArray3 = new float[2];
   public int[] countArray2 = new int[2];
   public int[] countArray = new int[2];
   public int count30;
   public static final Predicate predicate3 = new RotationValidator();

   public float computeFactorByIndex(int count) {
      return this.factorArray2[count];
   }

   private void applyIndex(int count, double posX, double posY, double posZ, boolean flag) {
      this.worldObj.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1014, new BlockPos(this), 0);
      double offset = this.computeValue(count);
      double offset2 = this.computeValueByIndex(count);
      double offset3 = this.computeValue2(count);
      double offset4 = posX - offset;
      double offset5 = posY - offset2;
      double offset6 = posZ - offset3;
      EntityWitherskull entityWitherskull = new EntityWitherskull(this.worldObj, this, offset4, offset5, offset6);
      if (flag) {
         entityWitherskull.setEnabled5(true);
      }

      entityWitherskull.posY = offset2;
      entityWitherskull.posX = offset;
      entityWitherskull.posZ = offset3;
      this.worldObj.matchesEntity(entityWitherskull);
   }

   public void applyIndex(int count, int count2) {
      this.dataWatcher.applyTargetByTarget(17 + count, count2);
   }

   @Override
   public void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityLivingBaseByEntityLivingBase(0, livingEntity);
   }

   @Override
   public String getText9() {
      return "mob.wither.death";
   }

   @Override
   public int computeCount(float factor) {
      return 15728880;
   }

   @Override
   public DisablerUndefinedType getUNDEAD() {
      return DisablerUndefinedType.UNDEAD;
   }

   public int computeCountByIndex(int count) {
      return this.dataWatcher.computeCount(17 + count);
   }

   @Override
   public int getCount4() {
      return 4;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.applyIndex8(tagCompound.computeCount("Invul"));
   }

   @Override
   public void setEntity8(Entity entity) {
      this.ridingEntity = null;
   }

   private double computeValue(int count) {
      if (count <= 0) {
         return this.posX;
      } else {
         float factor = MathHelper.cos((this.scaleFactor8 + (float)(180 * (count - 1))) / 180.0F * (float) Math.PI);
         return this.posX + (double)factor * 1.3;
      }
   }

   public float computeFactor(int count) {
      return this.factorArray5[count];
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(300.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.6F);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4).applyDouble(40.0);
   }

   public boolean isActive32() {
      return this.getFloat2() <= this.getFloat() / 2.0F;
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      EntityItem entityItem = this.resolveEntityItem(Items.netherStar, 1);
      if (entityItem != null) {
         entityItem.updateState5();
      }

      if (!this.worldObj.state) {
         Iterator iterator = this.worldObj.resolveList(EntityPlayer.class, this.getBoundingBox2().offset(50.0, 100.0, 50.0)).iterator();

         while (iterator.hasNext()) {
            ((EntityPlayer)iterator.next()).applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter25);
         }
      }
   }

   @Override
   public void processFloat2(float factor, float factor2) {
   }

   @Override
   public void updateState14() {
   }

   @Override
   public void applyPotionEffectByPotionEffect(PotionEffect potionEffect) {
   }

   private void applyEntityLivingBaseByEntityLivingBase(int count, EntityLivingBase livingEntity) {
      this.applyIndex(count, livingEntity.posX, livingEntity.posY + (double)livingEntity.getHeight2() * 0.5, livingEntity.posZ, count == 0 && this.random.nextFloat() < 0.001F);
   }

   private float computeFactorByFloat(float posX, float posY, float posZ) {
      float factor = MathHelper.sqrt_float(posY - posX);
      if (factor > posZ) {
         factor = posZ;
      }

      if (factor < -posZ) {
         factor = -posZ;
      }

      return posX + factor;
   }

   public static boolean matchesBlock(Block block) {
      return block != Blocks.block95 && block != Blocks.block2 && block != Blocks.block52 && block != Blocks.block139 && block != Blocks.block145;
   }

   private double computeValue2(int count) {
      if (count <= 0) {
         return this.posZ;
      } else {
         float factor = MathHelper.abs((this.scaleFactor8 + (float)(180 * (count - 1))) / 180.0F * (float) Math.PI);
         return this.posZ + (double)factor * 1.3;
      }
   }

   @Override
   public String getText3() {
      return "mob.wither.idle";
   }

   @Override
   public String getText10() {
      return "mob.wither.hurt";
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("Invul", this.getCount9());
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if (damageSource == DamageSource.damageSource || damageSource.getEntity() instanceof EntityWitherboss) {
         return false;
      } else if (this.getCount9() > 0 && damageSource != DamageSource.damageSource10) {
         return false;
      } else if (this.isActive32() && damageSource.getEntityAsEntity() instanceof EntityArrow) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         if (entity != null && !(entity instanceof EntityPlayer) && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getUNDEAD() == this.getUNDEAD()) {
            return false;
         } else {
            if (this.count30 <= 0) {
               this.count30 = 20;
            }

            for (int index = 0; index < this.countArray.length; index++) {
               this.countArray[index] = this.countArray[index] + 3;
            }

            return super.matchesDamageSource(damageSource, factor);
         }
      }
   }

   private double computeValueByIndex(int count) {
      return count <= 0 ? this.posY + 3.0 : this.posY + 2.2;
   }

   @Override
   public void updateState3() {
      if (this.getCount9() > 0) {
         int count = this.getCount9() - 1;
         if (count <= 0) {
            this.worldObj
               .resolveBlockBreakState(
                  this,
                  this.posX,
                  this.posY + (double)this.getHeight2(),
                  this.posZ,
                  7.0F,
                  false,
                  this.worldObj.getDoFireTickManager().matchesText("mobGriefing")
               );
            this.worldObj.applyBlockPos19(1013, new BlockPos(this), 0);
         }

         this.applyIndex8(count);
         if (this.ticksExisted % 10 == 0) {
            this.applyFloatByFloat(10.0F);
         }
      } else {
         super.updateState3();

         for (int index = 1; index < 3; index++) {
            if (this.ticksExisted >= this.countArray2[index - 1]) {
               this.countArray2[index - 1] = this.ticksExisted + 10 + this.random.nextInt(10);
               if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.NORMAL
                  || this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD) {
                  int count2 = index - 1;
                  int count3 = this.countArray[index - 1];
                  this.countArray[count2] = this.countArray[index - 1] + 1;
                  if (count3 > 15) {
                     float factor = 10.0F;
                     float factor2 = 5.0F;
                     double offset = MathHelper.nextDouble(this.random, this.posX - (double)factor, this.posX + (double)factor);
                     double offset2 = MathHelper.nextDouble(this.random, this.posY - (double)factor2, this.posY + (double)factor2);
                     double offset3 = MathHelper.nextDouble(this.random, this.posZ - (double)factor, this.posZ + (double)factor);
                     this.applyIndex(index + 1, offset, offset2, offset3, true);
                     this.countArray[index - 1] = 0;
                  }
               }

               int count4 = this.computeCountByIndex(index);
               if (count4 > 0) {
                  Entity entityByID = this.worldObj.getEntityByID(count4);
                  if (entityByID == null || !entityByID.canExecute() || !(this.getCoordinate(entityByID) <= 900.0) || !this.checkEntity4(entityByID)) {
                     this.applyIndex(index, 0);
                  } else if (entityByID instanceof EntityPlayer && ((EntityPlayer)entityByID).invulnerableManager.enabled) {
                     this.applyIndex(index, 0);
                  } else {
                     this.applyEntityLivingBaseByEntityLivingBase(index + 1, (EntityLivingBase)entityByID);
                     this.countArray2[index - 1] = this.ticksExisted + 40 + this.random.nextInt(20);
                     this.countArray[index - 1] = 0;
                  }
               } else {
                  List list = this.worldObj
                     .resolveList(
                        EntityLivingBase.class, this.getBoundingBox2().offset(20.0, 8.0, 20.0), Predicates.and(predicate3, LivingEntityFilter.predicate4)
                     );

                  for (int index2 = 0; index2 < 10 && !list.isEmpty(); index2++) {
                     EntityLivingBase livingEntity = (EntityLivingBase)list.get(this.random.nextInt(list.size()));
                     if (livingEntity != this && livingEntity.canExecute() && this.checkEntity4(livingEntity)) {
                        if (livingEntity instanceof EntityPlayer) {
                           if (!((EntityPlayer)livingEntity).invulnerableManager.enabled) {
                              this.applyIndex(index, livingEntity.getEntityId());
                           }
                        } else {
                           this.applyIndex(index, livingEntity.getEntityId());
                        }
                        break;
                     }

                     list.remove(livingEntity);
                  }
               }
            }
         }

         if (this.getEntityLivingBase4() != null) {
            this.applyIndex(0, this.getEntityLivingBase4().getEntityId());
         } else {
            this.applyIndex(0, 0);
         }

         if (this.count30 > 0) {
            this.count30--;
            if (this.count30 == 0 && this.worldObj.getDoFireTickManager().matchesText("mobGriefing")) {
               int count5 = MathHelper.ceiling_double_int(this.posY);
               int count6 = MathHelper.ceiling_double_int(this.posX);
               int count7 = MathHelper.ceiling_double_int(this.posZ);
               boolean flag = false;

               for (int index3 = -1; index3 <= 1; index3++) {
                  for (int index4 = -1; index4 <= 1; index4++) {
                     for (int index5 = 0; index5 <= 3; index5++) {
                        int count8 = count6 + index3;
                        int count9 = count5 + index5;
                        int count10 = count7 + index4;
                        BlockPos blockPos = new BlockPos(count8, count9, count10);
                        Block block = this.worldObj.getBlockState(blockPos).getBlock();
                        if (block.getMaterial() != Material.coreItemEntry && matchesBlock(block)) {
                           flag = this.worldObj.matchesBlockPos3(blockPos, true) || flag;
                        }
                     }
                  }
               }

               if (flag) {
                  this.worldObj.applyEntityPlayerByEntityPlayer((EntityPlayer)null, 1012, new BlockPos(this), 0);
               }
            }
         }

         if (this.ticksExisted % 20 == 0) {
            this.applyFloatByFloat(1.0F);
         }
      }
   }

   @Override
   public void updateState4() {
      this.indexOffset14 = 0;
   }

   public void applyIndex8(int count) {
      this.dataWatcher.applyTargetByTarget(20, count);
   }

   public void updateState48() {
      this.applyIndex8(220);
      this.applyFloat(this.getFloat() / 3.0F);
   }

   public int getCount9() {
      return this.dataWatcher.computeCount(20);
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(17, 0);
      this.dataWatcher.applyTarget(18, 0);
      this.dataWatcher.applyTarget(19, 0);
      this.dataWatcher.applyTarget(20, 0);
   }

   @Override
   public void updateState33() {
      this.motionY *= 0.6F;
      if (!this.worldObj.state && this.computeCountByIndex(0) > 0) {
         Entity entityByID = this.worldObj.getEntityByID(this.computeCountByIndex(0));
         if (entityByID != null) {
            if (this.posY < entityByID.posY || !this.isActive32() && this.posY < entityByID.posY + 5.0) {
               if (this.motionY < 0.0) {
                  this.motionY = 0.0;
               }

               this.motionY = this.motionY + (0.5 - this.motionY) * 0.6F;
            }

            double offset = entityByID.posX - this.posX;
            double offset2 = entityByID.posZ - this.posZ;
            double offset3 = offset * offset + offset2 * offset2;
            if (offset3 > 9.0) {
               double offset4 = (double)MathHelper.sqrt_double(offset3);
               this.motionX = this.motionX + (offset / offset4 * 0.5 - this.motionX) * 0.6F;
               this.motionZ = this.motionZ + (offset2 / offset4 * 0.5 - this.motionZ) * 0.6F;
            }
         }
      }

      if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05F) {
         this.rotationYaw = (float)MathHelper.getDouble6(this.motionZ, this.motionX) * (180.0F / (float)Math.PI) - 90.0F;
      }

      super.updateState33();

      for (int index = 0; index < 2; index++) {
         this.factorArray3[index] = this.factorArray5[index];
         this.factorArray4[index] = this.factorArray2[index];
      }

      for (int index2 = 0; index2 < 2; index2++) {
         int count = this.computeCountByIndex(index2 + 1);
         Entity entity = null;
         if (count > 0) {
            entity = this.worldObj.getEntityByID(count);
         }

         if (entity != null) {
            double offset5 = this.computeValue(index2 + 1);
            double offset6 = this.computeValueByIndex(index2 + 1);
            double offset7 = this.computeValue2(index2 + 1);
            double offset8 = entity.posX - offset5;
            double offset9 = entity.posY + (double)entity.getHeight2() - offset6;
            double offset10 = entity.posZ - offset7;
            double offset11 = (double)MathHelper.sqrt_double(offset8 * offset8 + offset10 * offset10);
            float factor = (float)(MathHelper.getDouble6(offset10, offset8) * 180.0 / Math.PI) - 90.0F;
            float factor2 = (float)(-(MathHelper.getDouble6(offset9, offset11) * 180.0 / Math.PI));
            this.factorArray2[index2] = this.computeFactorByFloat(this.factorArray2[index2], factor2, 40.0F);
            this.factorArray5[index2] = this.computeFactorByFloat(this.factorArray5[index2], factor, 10.0F);
         } else {
            this.factorArray5[index2] = this.computeFactorByFloat(this.factorArray5[index2], this.scaleFactor8, 10.0F);
         }
      }

      boolean active = this.isActive32();

      for (int index3 = 0; index3 < 3; index3++) {
         double offset12 = this.computeValue(index3);
         double offset13 = this.computeValueByIndex(index3);
         double offset14 = this.computeValue2(index3);
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.SMOKE_NORMAL,
               offset12 + this.random.nextGaussian() * 0.3F,
               offset13 + this.random.nextGaussian() * 0.3F,
               offset14 + this.random.nextGaussian() * 0.3F,
               0.0,
               0.0,
               0.0,
               new int[0]
            );
         if (active && this.worldObj.random.nextInt(4) == 0) {
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.SPELL_MOB,
                  offset12 + this.random.nextGaussian() * 0.3F,
                  offset13 + this.random.nextGaussian() * 0.3F,
                  offset14 + this.random.nextGaussian() * 0.3F,
                  0.7F,
                  0.7F,
                  0.5,
                  new int[0]
               );
         }
      }

      if (this.getCount9() > 0) {
         for (int index4 = 0; index4 < 3; index4++) {
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.SPELL_MOB,
                  this.posX + this.random.nextGaussian() * 1.0,
                  this.posY + (double)(this.random.nextFloat() * 3.3F),
                  this.posZ + this.random.nextGaussian() * 1.0,
                  0.7F,
                  0.7F,
                  0.9F,
                  new int[0]
               );
         }
      }
   }

   public EntityWitherboss(World world) {
      super(world);
      this.applyFloat(this.getFloat());
      this.applyFloat(0.9F, 3.5F);
      this.state4 = true;
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled2(true);
      this.goalSetupManager.add(0, new SupplementalClientManagerAdapter(this));
      this.goalSetupManager.add(2, new PassiveItemEntry(this, 1.0, 40, 20.0F));
      this.goalSetupManager.add(5, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(6, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(7, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, false, new Class[0]));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityMob.class, 0, false, false, predicate3));
      this.count26 = 50;
   }
}

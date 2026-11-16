package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.CombatWeightProperty;
import client.util.AdvancedClientManagerAdapter;
import client.util.AimRegistry;
import client.util.BaseClientManagerAdapter;
import client.util.BezierDirector;
import client.util.CoreClientManagerAdapter;
import client.util.DampenerRanker;
import client.util.ExtendedClassEntry;
import client.util.FovSerializer;
import client.util.HitBoxTracker;
import client.util.PathfindManager;
import client.util.RayTraceCalculator;
import client.util.SmoothSystem;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGuardian extends EntityMonster {
   public float factor44;
   public float factor49;
   public float factor45;
   public float factor46;
   public float factor42;
   public EntityLivingBase entityLivingBase2;
   public int count32;
   public boolean enabled28;
   public AdvancedClientManagerAdapter advancedClientManagerAdapter;

   public static AdvancedClientManagerAdapter resolveAdvancedClientManagerAdapter(EntityGuardian entityGuardian) {
      return entityGuardian.advancedClientManagerAdapter;
   }

   @Override
   public float computeFactor(BlockPos blockPos) {
      return this.worldObj.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry1
         ? 10.0F + this.worldObj.computeFactor(blockPos.getX(), blockPos.getY(), blockPos.getZ()) - 0.5F
         : super.computeFactor(blockPos);
   }

   public float computeFactor4(float factor) {
      return ((float)this.count32 + factor) / (float)this.getCount9();
   }

   @Override
   public String getText9() {
      return !this.isInWater() ? "mob.guardian.land.death" : (this.isActive32() ? "mob.guardian.elder.death" : "mob.guardian.death");
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.setEnabled6(tagCompound.matchesText("Elder"));
   }

   @Override
   public void applyIndex4(int count) {
      super.applyIndex4(count);
      if (count == 16) {
         if (this.isActive32() && this.width < 1.0F) {
            this.applyFloat(1.9975F, 1.9975F);
         }
      } else if (count == 17) {
         this.count32 = 0;
         this.entityLivingBase2 = null;
      }
   }

   private void applyIndexByIndex(int count, boolean flag) {
      int count2 = this.dataWatcher.computeCount(16);
      if (flag) {
         this.dataWatcher.applyTargetByTarget(16, count2 | count);
      } else {
         this.dataWatcher.applyTargetByTarget(16, count2 & ~count);
      }
   }

   public static void applyEntityGuardian(EntityGuardian entityGuardian, int count) {
      entityGuardian.applyIndex8(count);
   }

   @Override
   public float getHeight2() {
      return this.height * 0.5F;
   }

   public EntityLivingBase getEntityLivingBase7() {
      if (!this.isActive33()) {
         return null;
      } else if (this.worldObj.state) {
         if (this.entityLivingBase2 != null) {
            return this.entityLivingBase2;
         } else {
            Entity entityByID = this.worldObj.getEntityByID(this.dataWatcher.computeCount(17));
            if (entityByID instanceof EntityLivingBase) {
               this.entityLivingBase2 = (EntityLivingBase)entityByID;
               return this.entityLivingBase2;
            } else {
               return null;
            }
         }
      } else {
         return this.getEntityLivingBase4();
      }
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(6.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.5);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4).applyDouble(16.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(30.0);
   }

   public void updateState48() {
      this.setEnabled6(true);
      this.factor42 = this.factor46 = 1.0F;
   }

   @Override
   public void updateState2() {
      ItemStack stack = ((DampenerRanker)SmoothSystem.resolveDampenerSorter(this.random, EntityFishHook.getList())).resolveItemStack(this.random);
      this.resolveEntityItem(stack, 1.0F);
   }

   @Override
   public boolean isActive23() {
      return this.worldObj.matchesAxisAlignedBBByAxisAlignedBB(this.getBoundingBox2(), this) && this.worldObj.getList2(this, this.getBoundingBox2()).isEmpty();
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(3) + this.random.nextInt(count + 1);
      if (count2 > 0) {
         this.resolveEntityItem(new ItemStack(Items.prismarineShard, count2, 0), 1.0F);
      }

      if (this.random.nextInt(3 + count) > 1) {
         this.resolveEntityItem(new ItemStack(Items.fish, 1, CombatWeightProperty.COD.getCount2()), 1.0F);
      } else if (this.random.nextInt(3 + count) > 1) {
         this.resolveEntityItem(new ItemStack(Items.prismarineCrystals, 1, 0), 1.0F);
      }

      if (flag && this.isActive32()) {
         this.resolveEntityItem(new ItemStack(Blocks.block11, 1, 1), 1.0F);
      }
   }

   public float computeFactor3(float factor) {
      return this.factor42 + (this.factor46 - this.factor42) * factor;
   }

   private boolean checkIndex2(int count) {
      return (this.dataWatcher.computeCount(16) & count) != 0;
   }

   public float computeFactor5(float factor) {
      return this.factor49 + (this.factor44 - this.factor49) * factor;
   }

   @Override
   public PathfindManager resolvePathfindManager(World world) {
      return new RayTraceCalculator(this, world);
   }

   @Override
   public String getText3() {
      return !this.isInWater() ? "mob.guardian.land.idle" : (this.isActive32() ? "mob.guardian.elder.idle" : "mob.guardian.idle");
   }

   public static void applyEntityGuardian(EntityGuardian entityGuardian, boolean flag) {
      entityGuardian.setEnabled7(flag);
   }

   @Override
   public void applyFloat2(float factor, float factor2) {
      if (this.isActive18()) {
         if (this.isInWater()) {
            this.applyFloat(factor, factor2, 0.1F);
            this.applyDouble(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.9F;
            this.motionY *= 0.9F;
            this.motionZ *= 0.9F;
            if (!this.isActive34() && this.getEntityLivingBase4() == null) {
               this.motionY -= 0.005;
            }
         } else {
            super.applyFloat2(factor, factor2);
         }
      } else {
         super.applyFloat2(factor, factor2);
      }
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (!this.isActive34() && !damageSource.isEnabled3() && damageSource.getEntityAsEntity() instanceof EntityLivingBase) {
         EntityLivingBase entityAsEntity = (EntityLivingBase)damageSource.getEntityAsEntity();
         if (!damageSource.isEnabled5()) {
            entityAsEntity.matchesDamageSource(DamageSource.resolveDamageSourceByEntity(this), 2.0F);
            entityAsEntity.applyText("damage.thorns", 0.5F, 1.0F);
         }
      }

      this.advancedClientManagerAdapter.updateState4();
      return super.matchesDamageSource(damageSource, factor);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("Elder", this.isActive32());
   }

   @Override
   public String getText10() {
      return !this.isInWater() ? "mob.guardian.land.hit" : (this.isActive32() ? "mob.guardian.elder.hit" : "mob.guardian.hit");
   }

   @Override
   public int getCount7() {
      return 160;
   }

   private void setEnabled7(boolean enabled) {
      this.applyIndexByIndex(2, enabled);
   }

   @Override
   public boolean isActive31() {
      return true;
   }

   public boolean isActive33() {
      return this.dataWatcher.computeCount(17) != 0;
   }

   private void applyIndex8(int count) {
      this.dataWatcher.applyTargetByTarget(17, count);
   }

   public boolean isActive34() {
      return this.checkIndex2(2);
   }

   @Override
   public void updateState3() {
      super.updateState3();
      if (this.isActive32()) {
         if ((this.ticksExisted + this.getEntityId()) % 1200 == 0) {
            Potion potion = Potion.potion7;

            for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable<?>)this.worldObj.resolveListByClass(EntityPlayerAdapter.class, new AimRegistry(this))) {
               if (!entityPlayerAdapter.checkPotion(potion) || entityPlayerAdapter.getPotionEffect(potion).getIndexOffset2() < 2 || entityPlayerAdapter.getPotionEffect(potion).getIndexOffset() < 1200) {
                  entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(10, 0.0F));
                  entityPlayerAdapter.applyPotionEffectByPotionEffect(new PotionEffect(potion.indexOffset3, 6000, 2));
               }
            }
         }

         if (!this.isActive30()) {
            this.applyBlockPos(new BlockPos(this), 16);
         }
      }
   }

   public boolean isActive32() {
      return this.checkIndex2(4);
   }

   public int getCount9() {
      return this.isActive32() ? 60 : 80;
   }

   @Override
   public int getCount8() {
      return 180;
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, 0);
      this.dataWatcher.applyTarget(17, 0);
   }

   public void setEnabled6(boolean enabled) {
      this.applyIndexByIndex(4, enabled);
      if (enabled) {
         this.applyFloat(1.9975F, 1.9975F);
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.3F);
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(8.0);
         this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(80.0);
         this.updateState5();
         this.advancedClientManagerAdapter.setCount2(400);
      }
   }

   @Override
   public void updateState33() {
      if (this.worldObj.state) {
         this.factor49 = this.factor44;
         if (!this.isInWater()) {
            this.factor45 = 2.0F;
            if (this.motionY > 0.0 && this.enabled28 && !this.isActive7()) {
               this.worldObj.applyText(this.posX, this.posY, this.posZ, "mob.guardian.flop", 1.0F, 1.0F, false);
            }

            this.enabled28 = this.motionY < 0.0 && this.worldObj.matchesBlockPos4(new BlockPos(this).down(), false);
         } else if (this.isActive34()) {
            if (this.factor45 < 0.5F) {
               this.factor45 = 4.0F;
            } else {
               this.factor45 = this.factor45 + (0.5F - this.factor45) * 0.1F;
            }
         } else {
            this.factor45 = this.factor45 + (0.125F - this.factor45) * 0.2F;
         }

         this.factor44 = this.factor44 + this.factor45;
         this.factor42 = this.factor46;
         if (!this.isInWater()) {
            this.factor46 = this.random.nextFloat();
         } else if (this.isActive34()) {
            this.factor46 = this.factor46 + (0.0F - this.factor46) * 0.25F;
         } else {
            this.factor46 = this.factor46 + (1.0F - this.factor46) * 0.06F;
         }

         if (this.isActive34() && this.isInWater()) {
            Vec3 vec3 = this.getVec36(0.0F);

            for (int index = 0; index < 2; index++) {
               this.worldObj
                  .applyAttackEffectsExplosionNorm(
                     AttackEffectsExplosionNormalType.WATER_BUBBLE,
                     this.posX + (this.random.nextDouble() - 0.5) * (double)this.width - vec3.xCoord * 1.5,
                     this.posY + this.random.nextDouble() * (double)this.height - vec3.yCoord * 1.5,
                     this.posZ + (this.random.nextDouble() - 0.5) * (double)this.width - vec3.zCoord * 1.5,
                     0.0,
                     0.0,
                     0.0,
                     new int[0]
                  );
            }
         }

         if (this.isActive33()) {
            if (this.count32 < this.getCount9()) {
               this.count32++;
            }

            EntityLivingBase entityLivingBase = this.getEntityLivingBase7();
            if (entityLivingBase != null) {
               this.getEntityRavagerAdapter().applyEntityByEntity(entityLivingBase, 90.0F, 90.0F);
               this.getEntityRavagerAdapter().updateState();
               double offset = (double)this.computeFactor4(0.0F);
               double offset2 = entityLivingBase.posX - this.posX;
               double offset3 = entityLivingBase.posY + (double)(entityLivingBase.height * 0.5F) - (this.posY + (double)this.getHeight2());
               double offset4 = entityLivingBase.posZ - this.posZ;
               double offset5 = Math.sqrt(offset2 * offset2 + offset3 * offset3 + offset4 * offset4);
               double offset6 = offset2 / offset5;
               double offset7 = offset3 / offset5;
               double offset8 = offset4 / offset5;
               double offset9 = this.random.nextDouble();

               while (offset9 < offset5) {
                  offset9 += 1.8 - offset + this.random.nextDouble() * (1.7 - offset);
                  this.worldObj
                     .applyAttackEffectsExplosionNorm(
                        AttackEffectsExplosionNormalType.WATER_BUBBLE,
                        this.posX + offset6 * offset9,
                        this.posY + offset7 * offset9 + (double)this.getHeight2(),
                        this.posZ + offset8 * offset9,
                        0.0,
                        0.0,
                        0.0,
                        new int[0]
                     );
               }
            }
         }
      }

      if (this.inWater) {
         this.applyIndex3(300);
      } else if (this.onGround) {
         this.motionY += 0.5;
         this.motionX = this.motionX + (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F);
         this.motionZ = this.motionZ + (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F);
         this.rotationYaw = this.random.nextFloat() * 360.0F;
         this.onGround = false;
         this.enabled4 = true;
      }

      if (this.isActive33()) {
         this.rotationYaw = this.scaleFactor12;
      }

      super.updateState33();
   }

   @Override
   public boolean nextInt() {
      return (this.random.nextInt(20) == 0 || !this.worldObj.matchesBlockPos9(new BlockPos(this))) && super.nextInt();
   }

   public EntityGuardian(World world) {
      super(world);
      this.count26 = 10;
      this.applyFloat(0.85F, 0.85F);
      this.goalSetupManager.add(4, new HitBoxTracker(this));
      CoreClientManagerAdapter coreClientManagerAdapter;
      this.goalSetupManager.add(5, coreClientManagerAdapter = new CoreClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(7, this.advancedClientManagerAdapter = new AdvancedClientManagerAdapter(this, 1.0, 80));
      this.goalSetupManager.add(8, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(8, new BaseClientManagerAdapter(this, EntityGuardian.class, 12.0F, 0.01F));
      this.goalSetupManager.add(9, new ComplexItemEntry(this));
      this.advancedClientManagerAdapter.setCount(3);
      coreClientManagerAdapter.setCount(3);
      this.goalSetupManager2.add(1, new ExtendedClassEntry(this, EntityLivingBase.class, 10, true, false, new FovSerializer(this)));
      this.entityPillagerAdapter = new BezierDirector(this);
      this.factor49 = this.factor44 = this.random.nextFloat();
   }
}

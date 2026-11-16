package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.CombatFactorProperty;
import client.util.AdvancedClientManagerAdapter;
import client.util.AuxiliaryWorldEntry;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.CoreClientManagerAdapter;
import client.util.CustomClientManagerAdapter;
import client.util.HitBoxBuilder;
import client.util.HitBoxDirector;
import client.util.HitBoxObserver;
import client.util.HitBoxSelector;
import client.util.IBlockState;
import client.util.PathfindManagerAdapter;
import client.util.SplineChecker;
import client.util.TertiaryCustom;
import client.util.VectorPipeline;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityVillagergolem extends HitBoxBuilder {
   public SplineChecker splineChecker;
   public int count29;
   public int count30;
   public int count28;

   @Override
   public void applyEntity9(Entity entity) {
      if (entity instanceof VectorPipeline && !(entity instanceof EntityCreeper) && this.getRandom().nextInt(20) == 0) {
         this.setEntityLivingBase((EntityLivingBase)entity);
      }

      super.applyEntity9(entity);
   }

   @Override
   public String getText9() {
      return "mob.irongolem.death";
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, Block block) {
      this.applyText("mob.irongolem.walk", 1.0F, 1.0F);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.setEnabled6(tagCompound.matchesText("PlayerCreated"));
   }

   @Override
   public boolean matchesClass(Class clazz) {
      return (!this.isActive31() || !EntityPlayer.class.isAssignableFrom(clazz)) && clazz != EntityCreeper.class && super.matchesClass(clazz);
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      this.count30 = 10;
      this.worldObj.applyEntity(this, (byte)4);
      boolean flag = entity.matchesDamageSource(DamageSource.resolveDamageSourceByEntityLivingBase(this), (float)(7 + this.random.nextInt(15)));
      if (flag) {
         entity.motionY += 0.4F;
         this.applyEntityLivingBase(this, entity);
      }

      this.applyText("mob.irongolem.throw", 1.0F, 1.0F);
      return flag;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(100.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.25);
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(3);

      for (int index = 0; index < count2; index++) {
         this.resolveEntityItem(Item.processAction2(Blocks.immutableSetEntry2Entry2), 1, (float)CombatFactorProperty.POPPY.getCount());
      }

      int count3 = 3 + this.random.nextInt(3);

      for (int index2 = 0; index2 < count3; index2++) {
         this.resolveEntityItem(Items.ironIngot, 1);
      }
   }

   @Override
   public int computeCount(int count) {
      return count;
   }

   @Override
   public void processByte(byte byteValue) {
      if (byteValue == 4) {
         this.count30 = 10;
         this.applyText("mob.irongolem.throw", 1.0F, 1.0F);
      } else if (byteValue == 11) {
         this.count28 = 400;
      } else {
         super.processByte(byteValue);
      }
   }

   public SplineChecker getSplineChecker() {
      return this.splineChecker;
   }

   @Override
   public void applyDamageSource(DamageSource damageSource) {
      if (!this.isActive31() && this.entityPlayer != null && this.splineChecker != null) {
         this.splineChecker.computeCount(this.entityPlayer.getText(), -5);
      }

      super.applyDamageSource(damageSource);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.applyText("PlayerCreated", this.isActive31());
   }

   @Override
   public String getText10() {
      return "mob.irongolem.hit";
   }

   @Override
   public void updateState3() {
      if (--this.count29 <= 0) {
         this.count29 = 70 + this.random.nextInt(50);
         this.splineChecker = this.worldObj.getWorldCustomProperty().resolveSplineChecker(new BlockPos(this), 32);
         if (this.splineChecker == null) {
            this.updateState47();
         } else {
            BlockPos blockPos = this.splineChecker.getBlockPos2();
            this.applyBlockPos(blockPos, (int)((float)this.splineChecker.getCount() * 0.6F));
         }
      }

      super.updateState3();
   }

   public int getCount28() {
      return this.count28;
   }

   public void setEnabled6(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      if (enabled) {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue | 1));
      } else {
         this.dataWatcher.applyTargetByTarget(16, (byte)(byteValue & -2));
      }
   }

   public boolean isActive31() {
      return (this.dataWatcher.computeByteCode(16) & 1) != 0;
   }

   public int getCount30() {
      return this.count30;
   }

   public void setEnabled7(boolean enabled) {
      this.count28 = enabled ? 400 : 0;
      this.worldObj.applyEntity(this, (byte)11);
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
   }

   @Override
   public void updateState33() {
      super.updateState33();
      if (this.count30 > 0) {
         this.count30--;
      }

      if (this.count28 > 0) {
         this.count28--;
      }

      if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.5000003E-7F && this.random.nextInt(5) == 0) {
         int count = MathHelper.ceiling_double_int(this.posX);
         int count2 = MathHelper.ceiling_double_int(this.posY - 0.2F);
         int count3 = MathHelper.ceiling_double_int(this.posZ);
         IBlockState blockState = this.worldObj.getBlockState(new BlockPos(count, count2, count3));
         if (blockState.getBlock().getMaterial() != Material.coreItemEntry) {
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.BLOCK_CRACK,
                  this.posX + ((double)this.random.nextFloat() - 0.5) * (double)this.width,
                  this.getBoundingBox2().minY + 0.1,
                  this.posZ + ((double)this.random.nextFloat() - 0.5) * (double)this.width,
                  4.0 * ((double)this.random.nextFloat() - 0.5),
                  0.5,
                  ((double)this.random.nextFloat() - 0.5) * 4.0,
                  new int[]{Block.processAction5(blockState)}
               );
         }
      }
   }

   public EntityVillagergolem(World world) {
      super(world);
      this.applyFloat(1.4F, 2.9F);
      ((PathfindManagerAdapter)this.getPathfindManager()).setEnabled3(true);
      this.goalSetupManager.add(1, new AuxiliaryWorldEntry(this, 1.0, true));
      this.goalSetupManager.add(2, new HitBoxObserver(this, 0.9, 32.0F));
      this.goalSetupManager.add(3, new CustomClientManagerAdapter(this, 0.6, true));
      this.goalSetupManager.add(4, new CoreClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(5, new TertiaryCustom(this));
      this.goalSetupManager.add(6, new AdvancedClientManagerAdapter(this, 0.6));
      this.goalSetupManager.add(7, new BaseClientManagerAdapter(this, EntityPlayer.class, 6.0F));
      this.goalSetupManager.add(8, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new HitBoxSelector(this));
      this.goalSetupManager2.add(2, new ClassArrayEntry(this, false, new Class[0]));
      this.goalSetupManager2.add(3, new HitBoxDirector(this, EntityMob.class, 10, false, true, VectorPipeline.predicate));
   }
}

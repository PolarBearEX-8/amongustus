package net.minecraft.entity;

import client.setting.GameSettingsPeacefulType;
import client.util.AchievementTheEnd2Manager;
import client.util.BaseLoggerEntry;
import client.util.BezierSynchronizer;
import client.util.HitBoxAccumulator;
import client.util.HitBoxProfiler;
import client.util.HitBoxSorter;
import client.util.VectorPipeline;
import client.util.VectorSerializer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGhast extends VectorSerializer implements VectorPipeline {
   public int count29 = 1;

   @Override
   public float getFactor() {
      return 10.0F;
   }

   @Override
   public String getText9() {
      return "mob.ghast.death";
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      if (tagCompound.matchesText("ExplosionPower", 99)) {
         this.count29 = tagCompound.computeCount("ExplosionPower");
      }
   }

   @Override
   public float getHeight2() {
      return 2.6F;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener5).applyDouble(10.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4).applyDouble(100.0);
   }

   public int getCount29() {
      return this.count29;
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      int count2 = this.random.nextInt(2) + this.random.nextInt(1 + count);

      for (int index = 0; index < count2; index++) {
         this.resolveEntityItem(Items.ghastTear, 1);
      }

      int count3 = this.random.nextInt(3) + this.random.nextInt(1 + count);

      for (int index2 = 0; index2 < count3; index2++) {
         this.resolveEntityItem(Items.gunpowder, 1);
      }
   }

   @Override
   public Item getGunpowder() {
      return Items.gunpowder;
   }

   @Override
   public String getText3() {
      return "mob.ghast.moan";
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      tagCompound.put("ExplosionPower", this.count29);
   }

   @Override
   public String getText10() {
      return "mob.ghast.scream";
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if ("fireball".equals(damageSource.getLabelText()) && damageSource.getEntity() instanceof EntityPlayer) {
         super.matchesDamageSource(damageSource, 1000.0F);
         ((EntityPlayer)damageSource.getEntity()).applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter21);
         return true;
      } else {
         return super.matchesDamageSource(damageSource, factor);
      }
   }

   @Override
   public int getCount6() {
      return 1;
   }

   public boolean isActive29() {
      return this.dataWatcher.computeByteCode(16) != 0;
   }

   public void setEnabled6(boolean enabled) {
      this.dataWatcher.applyTargetByTarget(16, (byte)(enabled ? 1 : 0));
   }

   @Override
   public void updateState() {
      super.updateState();
      if (!this.worldObj.state && this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.PEACEFUL) {
         this.updateState9();
      }
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
   }

   @Override
   public boolean nextInt() {
      return this.random.nextInt(20) == 0 && super.nextInt() && this.worldObj.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL;
   }

   public EntityGhast(World world) {
      super(world);
      this.applyFloat(4.0F, 4.0F);
      this.state4 = true;
      this.count26 = 5;
      this.entityPillagerAdapter = new BezierSynchronizer(this);
      this.goalSetupManager.add(5, new HitBoxAccumulator(this));
      this.goalSetupManager.add(7, new HitBoxSorter(this));
      this.goalSetupManager.add(7, new HitBoxProfiler(this));
      this.goalSetupManager2.add(1, new BaseLoggerEntry(this));
   }
}

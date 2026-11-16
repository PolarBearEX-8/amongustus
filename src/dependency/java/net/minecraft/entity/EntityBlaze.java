package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.AdvancedClientManagerAdapter;
import client.util.BaseClientManagerAdapter;
import client.util.ClassArrayEntry;
import client.util.CoreClientManagerAdapter;
import client.util.ExtendedClassEntry;
import client.util.HitBoxCounter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ComplexItemEntry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBlaze extends EntityMonster {
   public float factor41 = 0.5F;
   public int count30;

   @Override
   public String getText9() {
      return "mob.blaze.death";
   }

   @Override
   public int computeCount(float factor) {
      return 15728880;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).applyDouble(6.0);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).applyDouble(0.23F);
      this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener4).applyDouble(48.0);
   }

   @Override
   public void processFloat2(float factor, float factor2) {
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (flag) {
         int count2 = this.random.nextInt(2 + count);

         for (int index = 0; index < count2; index++) {
            this.resolveEntityItem(Items.blazeRod, 1);
         }
      }
   }

   @Override
   public Item getGunpowder() {
      return Items.blazeRod;
   }

   @Override
   public float computeFactor(float factor) {
      return 1.0F;
   }

   @Override
   public String getText3() {
      return "mob.blaze.breathe";
   }

   @Override
   public boolean canExecute2() {
      return this.isActive32();
   }

   @Override
   public String getText10() {
      return "mob.blaze.hit";
   }

   public boolean isActive32() {
      return (this.dataWatcher.computeByteCode(16) & 1) != 0;
   }

   @Override
   public boolean isActive31() {
      return true;
   }

   @Override
   public void updateState3() {
      if (this.isActive11()) {
         this.matchesDamageSource(DamageSource.damageSource, 1.0F);
      }

      this.count30--;
      if (this.count30 <= 0) {
         this.count30 = 100;
         this.factor41 = 0.5F + (float)this.random.nextGaussian() * 3.0F;
      }

      EntityLivingBase entityLivingBase = this.getEntityLivingBase4();
      if (entityLivingBase != null && entityLivingBase.posY + (double)entityLivingBase.getHeight2() > this.posY + (double)this.getHeight2() + (double)this.factor41) {
         this.motionY = this.motionY + (0.3F - this.motionY) * 0.3F;
         this.enabled4 = true;
      }

      super.updateState3();
   }

   public void setEnabled6(boolean enabled) {
      byte byteValue = this.dataWatcher.computeByteCode(16);
      byte byteValue2;
      if (enabled) {
         byteValue2 = (byte)(byteValue | 1);
      } else {
         byteValue2 = (byte)(byteValue & -2);
      }

      this.dataWatcher.applyTargetByTarget(16, byteValue2);
   }

   @Override
   public void updateState7() {
      super.updateState7();
      this.dataWatcher.applyTarget(16, (byte)0);
   }

   @Override
   public void updateState33() {
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      if (this.worldObj.state) {
         if (this.random.nextInt(24) == 0 && !this.isActive7()) {
            this.worldObj
               .applyText(
                  this.posX + 0.5,
                  this.posY + 0.5,
                  this.posZ + 0.5,
                  "fire.fire",
                  1.0F + this.random.nextFloat(),
                  this.random.nextFloat() * 0.7F + 0.3F,
                  false
               );
         }

         for (int index = 0; index < 2; index++) {
            this.worldObj
               .applyAttackEffectsExplosionNorm(
                  AttackEffectsExplosionNormalType.SMOKE_LARGE,
                  this.posX + (this.random.nextDouble() - 0.5) * (double)this.width,
                  this.posY + this.random.nextDouble() * (double)this.height,
                  this.posZ + (this.random.nextDouble() - 0.5) * (double)this.width,
                  0.0,
                  0.0,
                  0.0,
                  new int[0]
               );
         }
      }

      super.updateState33();
   }

   public EntityBlaze(World world) {
      super(world);
      this.state4 = true;
      this.count26 = 10;
      this.goalSetupManager.add(4, new HitBoxCounter(this));
      this.goalSetupManager.add(5, new CoreClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(7, new AdvancedClientManagerAdapter(this, 1.0));
      this.goalSetupManager.add(8, new BaseClientManagerAdapter(this, EntityPlayer.class, 8.0F));
      this.goalSetupManager.add(8, new ComplexItemEntry(this));
      this.goalSetupManager2.add(1, new ClassArrayEntry(this, true, new Class[0]));
      this.goalSetupManager2.add(2, new ExtendedClassEntry(this, EntityPlayer.class, true));
   }
}

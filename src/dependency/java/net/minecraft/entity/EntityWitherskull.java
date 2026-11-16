package net.minecraft.entity;

import client.setting.GameSettingsPeacefulType;
import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWitherskull extends net.minecraft.entity.projectile.EntityFireball {
   public void setEnabled5(boolean enabled) {
      this.dataWatcher.applyTargetByTarget(10, (byte)(enabled ? 1 : 0));
   }

   @Override
   public float getFactor() {
      return this.isActive16() ? 0.73F : super.getFactor();
   }

   @Override
   public void applyMovingObjectPosition(MovingObjectPosition hitResult) {
      if (!this.worldObj.state) {
         if (hitResult.entityHit != null) {
            if (this.entityLivingBase != null) {
               if (hitResult.entityHit.matchesDamageSource(DamageSource.resolveDamageSourceByEntityLivingBase(this.entityLivingBase), 8.0F)) {
                  if (!hitResult.entityHit.canExecute()) {
                     this.entityLivingBase.applyFloatByFloat(5.0F);
                  } else {
                     this.applyEntityLivingBase(this.entityLivingBase, hitResult.entityHit);
                  }
               }
            } else {
               hitResult.entityHit.matchesDamageSource(DamageSource.damageSource5, 5.0F);
            }

            if (hitResult.entityHit instanceof EntityLivingBase) {
               byte byteValue = 0;
               if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.NORMAL) {
                  byteValue = 10;
               } else if (this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.HARD) {
                  byteValue = 40;
               }

               if (byteValue > 0) {
                  ((EntityLivingBase)hitResult.entityHit).applyPotionEffectByPotionEffect(new PotionEffect(Potion.potion32.indexOffset3, 20 * byteValue, 1));
               }
            }
         }

         this.worldObj
            .resolveBlockBreakState(this, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getDoFireTickManager().matchesText("mobGriefing"));
         this.updateState9();
      }
   }

   public boolean isActive16() {
      return this.dataWatcher.computeByteCode(10) == 1;
   }

   @Override
   public boolean canExecute2() {
      return false;
   }

   @Override
   public float computeFactor(BlockBreakState blockBreakState, World world, BlockPos blockPos, IBlockState blockState) {
      float factor = super.computeFactor(blockBreakState, world, blockPos, blockState);
      Block block = blockState.getBlock();
      if (this.isActive16() && EntityWitherboss.matchesBlock(block)) {
         factor = Math.min(0.8F, factor);
      }

      return factor;
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      return false;
   }

   @Override
   public boolean isActive5() {
      return false;
   }

   @Override
   public void updateState7() {
      this.dataWatcher.applyTarget(10, (byte)0);
   }

   public EntityWitherskull(World world) {
      super(world);
      this.applyFloat(0.3125F, 0.3125F);
   }

   public EntityWitherskull(World world, EntityLivingBase livingEntity, double posX, double posY, double posZ) {
      super(world, livingEntity, posX, posY, posZ);
      this.applyFloat(0.3125F, 0.3125F);
   }

   public EntityWitherskull(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.applyFloat(0.3125F, 0.3125F);
   }
}

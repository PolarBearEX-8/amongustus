package net.minecraft.entity;

import client.setting.CombatConfigProperty;
import client.setting.GameSettingsPeacefulType;
import client.util.AuxiliaryUUIDEntry;
import client.util.LvlManager;
import client.util.VectorPipeline;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityMonster extends AuxiliaryUUIDEntry implements VectorPipeline {
   @Override
   public String getText6() {
      return "game.hostile.swim";
   }

   @Override
   public float computeFactor(BlockPos blockPos) {
      return 0.5F - this.worldObj.computeFactor(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   @Override
   public String getText9() {
      return "game.hostile.die";
   }

   @Override
   public boolean matchesEntity4(Entity entity) {
      float doubleItem = (float)this.getAuxiliaryListener(SharedMonsterAttributes.extendedListener).getDouble();
      int count = 0;
      if (entity instanceof EntityLivingBase) {
         doubleItem += LvlManager.getScaleFactor(this.getItemStack(), ((EntityLivingBase)entity).getUNDEAD());
         count += LvlManager.getIndex(this);
      }

      boolean flag = entity.matchesDamageSource(DamageSource.resolveDamageSourceByEntityLivingBase(this), doubleItem);
      if (flag) {
         if (count > 0) {
            entity.applyDouble3(
               (double)(-MathHelper.abs(this.rotationYaw * (float) Math.PI / 180.0F) * (float)count * 0.5F),
               0.1,
               (double)(MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * (float)count * 0.5F)
            );
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
         }

         int count2 = LvlManager.computeCount3(this);
         if (count2 > 0) {
            entity.applyIndexByIndex(count2 * 4);
         }

         this.applyEntityLivingBase(this, entity);
      }

      return flag;
   }

   @Override
   public void updateState27() {
      super.updateState27();
      this.getAdvancedMapEntry().resolveAuxiliaryListenerByExtendedListener(SharedMonsterAttributes.extendedListener);
   }

   @Override
   public String getText7() {
      return "game.hostile.swim.splash";
   }

   @Override
   public String getText10() {
      return "game.hostile.hurt";
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      if (this.matchesDamageSource(damageSource)) {
         return false;
      } else if (!super.matchesDamageSource(damageSource, factor)) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         return this.entity != entity && this.ridingEntity != entity ? true : true;
      }
   }

   @Override
   public String resolveText(int count) {
      return count > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
   }

   public boolean isActive31() {
      int count = MathHelper.ceiling_double_int(this.posX);
      int count2 = MathHelper.ceiling_double_int(this.getBoundingBox2().minY);
      int count3 = MathHelper.ceiling_double_int(this.posZ);
      if (this.worldObj.computeCount(CombatConfigProperty.SKY, count, count2, count3) > this.random.nextInt(32)) {
         return false;
      } else {
         int count4 = this.worldObj.computeCountByIndex(count, count2, count3);
         if (this.worldObj.isActiveAsFlag()) {
            int indexOffset = this.worldObj.getIndexOffset();
            this.worldObj.setIndexOffset(10);
            count4 = this.worldObj.computeCountByIndex(count, count2, count3);
            this.worldObj.setIndexOffset(indexOffset);
         }

         return count4 <= this.random.nextInt(8);
      }
   }

   @Override
   public boolean isActive19() {
      return true;
   }

   @Override
   public void updateState() {
      super.updateState();
      if (!this.worldObj.state && this.worldObj.getGameSettingsPeacefulType() == GameSettingsPeacefulType.PEACEFUL) {
         this.updateState9();
      }
   }

   @Override
   public void updateState33() {
      this.updateState37();
      if (this.computeFactor(1.0F) > 0.5F) {
         this.indexOffset14 += 2;
      }

      super.updateState33();
   }

   @Override
   public boolean nextInt() {
      return this.worldObj.getGameSettingsPeacefulType() != GameSettingsPeacefulType.PEACEFUL && this.isActive31() && super.nextInt();
   }

   public EntityMonster(World world) {
      super(world);
      this.count26 = 5;
   }
}

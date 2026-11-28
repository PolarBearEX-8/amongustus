package net.minecraft.entity.monster;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class EntityMob extends EntityCreature implements IMob {

   public boolean isValidLightLevel() {
      int n = MathHelper.floor_double(this.posX);
      int n2 = MathHelper.floor_double(this.getEntityBoundingBox().minY);
      int n3 = MathHelper.floor_double(this.posZ);
      if (this.entityWorld.getLightFor(EnumSkyBlock.SKY, n, n2, n3) > this.rand.nextInt(32)) {
         return false;
      } else {
         int light = this.entityWorld.getLight(n, n2, n3);
         if (this.entityWorld.isThundering()) {
            int skylightSubtracted = this.entityWorld.getSkylightSubtracted();
            this.entityWorld.setSkylightSubtracted(10);
            light = this.entityWorld.getLight(n, n2, n3);
            this.entityWorld.setSkylightSubtracted(skylightSubtracted);
         }

         return light <= this.rand.nextInt(8);
      }
   }

   @Override
   public void onLivingUpdate() {
      this.updateArmSwingProgress();
      if (this.getBrightness(1.0F) > 0.5F) {
         this.age += 2;
      }

      super.onLivingUpdate();
   }

   @Override
   public String getSplashSound() {
      return "game.hostile.swim.splash";
   }

   public EntityMob(World world) {
      super(world);
      this.experienceValue = 5;
   }

   @Override
   public String getHurtSound() {
      return "game.hostile.hurt";
   }

   @Override
   public boolean attackEntityAsMob(Entity entity) {
      float attributeValue = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
      int n = 0;
      if (entity instanceof EntityLivingBase) {
         attributeValue += EnchantmentHelper.getModifierForCreature(this.getHeldItem(), ((EntityLivingBase)entity).getCreatureAttribute());
         n += EnchantmentHelper.getKnockbackModifier(this);
      }

      boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), attributeValue);
      if (flag) {
         if (n > 0) {
            entity.addVelocity(
               (double)(-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * (float)n * 0.5F),
               0.1,
               (double)(MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * (float)n * 0.5F)
            );
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
         }

         int fireAspectModifier = EnchantmentHelper.getFireAspectModifier(this);
         if (fireAspectModifier > 0) {
            entity.setFire(fireAspectModifier * 4);
         }

         this.applyEnchantments(this, entity);
      }

      return flag;
   }

   @Override
   public String getSwimSound() {
      return "game.hostile.swim";
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.entityWorld.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
   }

   @Override
   public float getBlockPathWeight(BlockPos pos) {
      return 0.5F - this.entityWorld.getLightBrightness(pos.getX(), pos.getY(), pos.getZ());
   }

   @Override
   public String getFallSoundString(int n) {
      return n > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
   }

   @Override
   public boolean canDropLoot() {
      return true;
   }

   @Override
   public String getDeathSound() {
      return "game.hostile.die";
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      if (this.isEntityInvulnerable(damageSource)) {
         return false;
      } else if (!super.attackEntityFrom(damageSource, f)) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         return this.riddenByEntity != entity && this.ridingEntity != entity ? true : true;
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (!this.entityWorld.isRemote && this.entityWorld.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }
   }
}

package net.minecraft.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityWitherSkull extends EntityFireball {
   public EntityWitherSkull(World world, EntityLivingBase entity, double d, double d2, double d3) {
      super(world, entity, d, d2, d3);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public float getExplosionResistance(Explosion explosion, World world, BlockPos pos, IBlockState state) {
      float explosionResistance = super.getExplosionResistance(explosion, world, pos, state);
      Block block = state.getBlock();
      if (this.isInvulnerable() && EntityWither.canDestroyBlock(block)) {
         explosionResistance = Math.min(0.8F, explosionResistance);
      }

      return explosionResistance;
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      return false;
   }

   public void setInvulnerable(boolean flag) {
      this.dataWatcher.updateObject(10, (byte)(flag ? 1 : 0));
   }

   @Override
   public boolean canBeCollidedWith() {
      return false;
   }

   @Override
   public float getMotionFactor() {
      return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
   }

   @Override
   public boolean isBurning() {
      return false;
   }

   public EntityWitherSkull(World world) {
      super(world);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public void onImpact(MovingObjectPosition hit) {
      if (!this.entityWorld.isRemote) {
         if (hit.entityHit != null) {
            if (this.shootingEntity != null) {
               if (hit.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F)) {
                  if (!hit.entityHit.isEntityAlive()) {
                     this.shootingEntity.heal(5.0F);
                  } else {
                     this.applyEnchantments(this.shootingEntity, hit.entityHit);
                  }
               }
            } else {
               hit.entityHit.attackEntityFrom(DamageSource.magic, 5.0F);
            }

            if (hit.entityHit instanceof EntityLivingBase) {
               byte b = 0;
               if (this.entityWorld.getDifficulty() == EnumDifficulty.NORMAL) {
                  b = 10;
               } else if (this.entityWorld.getDifficulty() == EnumDifficulty.HARD) {
                  b = 40;
               }

               if (b > 0) {
                  ((EntityLivingBase)hit.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * b, 1));
               }
            }
         }

         this.entityWorld.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, this.entityWorld.getGameRules().getBoolean("mobGriefing"));
         this.setDead();
      }
   }

   public EntityWitherSkull(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, d4, d5, d6);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public void entityInit() {
      this.dataWatcher.addObject(10, (byte)0);
   }

   public boolean isInvulnerable() {
      return this.dataWatcher.getWatchableObjectByte(10) == 1;
   }
}

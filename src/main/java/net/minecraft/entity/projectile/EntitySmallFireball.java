package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySmallFireball extends EntityFireball {
   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      return false;
   }

   @Override
   public void onImpact(MovingObjectPosition hit) {
      if (!this.entityWorld.isRemote) {
         if (hit.entityHit != null) {
            if (hit.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F)) {
               this.applyEnchantments(this.shootingEntity, hit.entityHit);
               if (!hit.entityHit.isImmuneToFire()) {
                  hit.entityHit.setFire(5);
               }
            }
         } else {
            boolean flag2 = true;
            if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving) {
               flag2 = this.entityWorld.getGameRules().getBoolean("mobGriefing");
            }

            if (flag2) {
               BlockPos pos = hit.getBlockPos().offset(hit.sideHit);
               if (this.entityWorld.isAirBlock(pos)) {
                  this.entityWorld.setBlockState(pos, Blocks.fire.getDefaultState());
               }
            }
         }

         this.setDead();
      }
   }

   public EntitySmallFireball(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, d4, d5, d6);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   public boolean canBeCollidedWith() {
      return false;
   }

   public EntitySmallFireball(World world, EntityLivingBase entity, double d, double d2, double d3) {
      super(world, entity, d, d2, d3);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntitySmallFireball(World world) {
      super(world);
      this.setSize(0.3125F, 0.3125F);
   }
}

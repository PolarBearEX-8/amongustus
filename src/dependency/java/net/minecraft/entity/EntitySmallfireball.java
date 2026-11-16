package net.minecraft.entity;

import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySmallfireball extends net.minecraft.entity.projectile.EntityFireball {
   @Override
   public void applyMovingObjectPosition(MovingObjectPosition hitResult) {
      if (!this.worldObj.state) {
         if (hitResult.entityHit != null) {
            if (hitResult.entityHit
               .matchesDamageSource(DamageSource.resolveDamageSource((net.minecraft.entity.projectile.EntityFireball)this, this.entityLivingBase), 5.0F)) {
               this.applyEntityLivingBase(this.entityLivingBase, hitResult.entityHit);
               if (!hitResult.entityHit.isState4()) {
                  hitResult.entityHit.applyIndexByIndex(5);
               }
            }
         } else {
            boolean flag = true;
            if (this.entityLivingBase != null && this.entityLivingBase instanceof EntityMob) {
               flag = this.worldObj.getDoFireTickManager().matchesText("mobGriefing");
            }

            if (flag) {
               BlockPos blockPos = hitResult.getBlockPos().offset(hitResult.sideHit);
               if (this.worldObj.isAirBlock(blockPos)) {
                  this.worldObj.matchesBlockPos(blockPos, Blocks.blockFire.getDefaultState());
               }
            }
         }

         this.updateState9();
      }
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      return false;
   }

   @Override
   public boolean isActive5() {
      return false;
   }

   public EntitySmallfireball(World world) {
      super(world);
      this.applyFloat(0.3125F, 0.3125F);
   }

   public EntitySmallfireball(World world, EntityLivingBase livingEntity, double posX, double posY, double posZ) {
      super(world, livingEntity, posX, posY, posZ);
      this.applyFloat(0.3125F, 0.3125F);
   }

   public EntitySmallfireball(World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, posX4, posY, posZ);
      this.applyFloat(0.3125F, 0.3125F);
   }
}

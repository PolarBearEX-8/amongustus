package net.minecraft.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PathNavigateClimber extends PathNavigateGround {
   private BlockPos targetPosition;

   @Override
   public PathEntity getPathToEntityLiving(Entity entity) {
      this.targetPosition = new BlockPos(entity);
      return super.getPathToEntityLiving(entity);
   }

   @Override
   public boolean tryMoveToEntityLiving(Entity entity, double d) {
      PathEntity pathToEntityLiving = this.getPathToEntityLiving(entity);
      if (pathToEntityLiving != null) {
         return this.setPath(pathToEntityLiving, d);
      } else {
         this.targetPosition = new BlockPos(entity);
         this.speed = d;
         return true;
      }
   }

   @Override
   public PathEntity getPathToPos(BlockPos pos) {
      this.targetPosition = pos;
      return super.getPathToPos(pos);
   }

   @Override
   public void onUpdateNavigation() {
      if (!this.noPath()) {
         super.onUpdateNavigation();
      } else if (this.targetPosition != null) {
         double d = (double)(this.theEntity.width * this.theEntity.width);
         if (!(this.theEntity.getDistanceSqToCenter(this.targetPosition) >= d)
            || !(this.theEntity.posY <= (double)this.targetPosition.getY())
               && !(
                  this.theEntity
                        .getDistanceSqToCenter(
                           new BlockPos(this.targetPosition.getX(), MathHelper.floor_double(this.theEntity.posY), this.targetPosition.getZ())
                        )
                     >= d
               )) {
            this.targetPosition = null;
         } else {
            this.theEntity
               .getMoveHelper()
               .setMoveTo((double)this.targetPosition.getX(), (double)this.targetPosition.getY(), (double)this.targetPosition.getZ(), this.speed);
         }
      }
   }

   public PathNavigateClimber(EntityLiving entityLiving, World world) {
      super(entityLiving, world);
   }
}

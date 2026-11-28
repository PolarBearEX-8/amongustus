package net.minecraft.pathfinding;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.pathfinder.SwimNodeProcessor;

public class PathNavigateSwimmer extends PathNavigate {
   @Override
   public void pathFollow() {
      Vec3 entityPosition = this.getEntityPosition();
      float f = this.theEntity.width * this.theEntity.width;
      if (entityPosition.squareDistanceTo(this.currentPath.getVectorFromIndex(this.theEntity, this.currentPath.getCurrentPathIndex())) < (double)f) {
         this.currentPath.incrementPathIndex();
      }

      for (int i = Math.min(this.currentPath.getCurrentPathIndex() + ((byte)6), this.currentPath.getCurrentPathLength() - 1);
         i > this.currentPath.getCurrentPathIndex();
         i--
      ) {
         Vec3 vectorFromIndex = this.currentPath.getVectorFromIndex(this.theEntity, i);
         if (vectorFromIndex.squareDistanceTo(entityPosition) <= 36.0 && this.isDirectPathBetweenPoints(entityPosition, vectorFromIndex, 0, 0, 0)) {
            this.currentPath.setCurrentPathIndex(i);
            break;
         }
      }

      this.checkForStuck(entityPosition);
   }

   @Override
   public void removeSunnyPath() {
      super.removeSunnyPath();
   }

   @Override
   public Vec3 getEntityPosition() {
      return new Vec3(this.theEntity.posX, this.theEntity.posY + (double)this.theEntity.height * 0.5, this.theEntity.posZ);
   }

   @Override
   public boolean isDirectPathBetweenPoints(Vec3 vec, Vec3 vec2, int n, int n2, int n3) {
      MovingObjectPosition hit = this.worldObj
         .rayTraceBlocks(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord + (double)this.theEntity.height * 0.5, vec2.zCoord, false, true, false);
      return hit == null || hit.typeOfHit == MovingObjectPosition.MovingObjectType.MISS;
   }

   @Override
   public PathFinder getPathFinder() {
      return new PathFinder(new SwimNodeProcessor());
   }

   @Override
   public boolean canNavigate() {
      return this.isInLiquid();
   }

   public PathNavigateSwimmer(EntityLiving entityLiving, World world) {
      super(entityLiving, world);
   }
}

package net.minecraft.pathfinding;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;

public abstract class PathNavigate {
   public EntityLiving theEntity;
   public World worldObj;
   public PathEntity currentPath;
   public double speed;
   private final IAttributeInstance pathSearchRange;
   private int totalTicks;
   private int ticksAtLastPos;
   private Vec3 lastPosCheck = new Vec3(0.0, 0.0, 0.0);
   private float heightRequirement = 1.0F;
   private final PathFinder pathFinder;

   public PathNavigate(EntityLiving entityLiving, World world) {
      this.theEntity = entityLiving;
      this.worldObj = world;
      this.pathSearchRange = entityLiving.getEntityAttribute(SharedMonsterAttributes.followRange);
      this.pathFinder = this.getPathFinder();
   }

   public PathEntity getPath() {
      return this.currentPath;
   }

   public boolean setPath(PathEntity pathEntity, double d) {
      if (pathEntity == null) {
         this.currentPath = null;
         return false;
      } else {
         if (!pathEntity.isSamePath(this.currentPath)) {
            this.currentPath = pathEntity;
         }

         this.removeSunnyPath();
         if (this.currentPath.getCurrentPathLength() == 0) {
            return false;
         } else {
            this.speed = d;
            Vec3 entityPosition = this.getEntityPosition();
            this.ticksAtLastPos = this.totalTicks;
            this.lastPosCheck = entityPosition;
            return true;
         }
      }
   }

   public void onUpdateNavigation() {
      this.totalTicks++;
      if (!this.noPath()) {
         if (this.canNavigate()) {
            this.pathFollow();
         } else if (this.currentPath != null && this.currentPath.getCurrentPathIndex() < this.currentPath.getCurrentPathLength()) {
            Vec3 entityPosition = this.getEntityPosition();
            Vec3 vectorFromIndex = this.currentPath.getVectorFromIndex(this.theEntity, this.currentPath.getCurrentPathIndex());
            if (entityPosition.yCoord > vectorFromIndex.yCoord
               && !this.theEntity.onGround
               && MathHelper.floor_double(entityPosition.xCoord) == MathHelper.floor_double(vectorFromIndex.xCoord)
               && MathHelper.floor_double(entityPosition.zCoord) == MathHelper.floor_double(vectorFromIndex.zCoord)) {
               this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
            }
         }

         if (!this.noPath()) {
            Vec3 position = this.currentPath.getPosition(this.theEntity);
            if (position != null) {
               AxisAlignedBB box = new AxisAlignedBB(position.xCoord, position.yCoord, position.zCoord, position.xCoord, position.yCoord, position.zCoord).expand(0.5, 0.5, 0.5);
               List collidingBoundingBoxes = this.worldObj.getCollidingBoundingBoxes(this.theEntity, box.addCoord(0.0, -1.0, 0.0));
               double d = -1.0;
               box = box.offset(0.0, 1.0, 0.0);

               for (AxisAlignedBB box2 : (Iterable<AxisAlignedBB>)(collidingBoundingBoxes)) {
                  d = box2.calculateYOffset(box, d);
               }

               this.theEntity.getMoveHelper().setMoveTo(position.xCoord, position.yCoord + d, position.zCoord, this.speed);
            }
         }
      }
   }

   public void checkForStuck(Vec3 vec) {
      if (this.totalTicks - this.ticksAtLastPos > 100) {
         if (vec.squareDistanceTo(this.lastPosCheck) < 2.25) {
            this.clearPathEntity();
         }

         this.ticksAtLastPos = this.totalTicks;
         this.lastPosCheck = vec;
      }
   }

   public PathEntity getPathToPos(BlockPos pos) {
      if (!this.canNavigate()) {
         return null;
      } else {
         float pathSearchRange2 = this.getPathSearchRange();
         this.worldObj.theProfiler.startSection("pathfind");
         int n = MathHelper.floor_double(this.theEntity.posX);
         int n2 = MathHelper.floor_double(this.theEntity.posY);
         int n3 = MathHelper.floor_double(this.theEntity.posZ);
         int n4 = (int)(pathSearchRange2 + 8.0F);
         ChunkCache chunkCache = new ChunkCache(this.worldObj, n - n4, n2 - n4, n3 - n4, n + n4, n2 + n4, n3 + n4, 0);
         PathEntity pathEntity = this.pathFinder.createEntityPathTo(chunkCache, this.theEntity, pos, pathSearchRange2);
         this.worldObj.theProfiler.endSection();
         return pathEntity;
      }
   }

   public abstract PathFinder getPathFinder();

   public abstract boolean canNavigate();

   public final PathEntity getPathToXYZ(double d, double d2, double d3) {
      return this.getPathToPos(new BlockPos(MathHelper.floor_double(d), (int)d2, MathHelper.floor_double(d3)));
   }

   public void clearPathEntity() {
      this.currentPath = null;
   }

   public abstract Vec3 getEntityPosition();

   public boolean noPath() {
      return this.currentPath == null || this.currentPath.isFinished();
   }

   public boolean tryMoveToEntityLiving(Entity entity, double d) {
      PathEntity pathToEntityLiving = this.getPathToEntityLiving(entity);
      return pathToEntityLiving != null ? this.setPath(pathToEntityLiving, d) : false;
   }

   public boolean isInLiquid() {
      return this.theEntity.isInWater() || this.theEntity.isInLava();
   }

   public PathEntity getPathToEntityLiving(Entity entity) {
      if (!this.canNavigate()) {
         return null;
      } else {
         float pathSearchRange2 = this.getPathSearchRange();
         this.worldObj.theProfiler.startSection("pathfind");
         int n = MathHelper.floor_double(this.theEntity.posX);
         int n2 = MathHelper.floor_double(this.theEntity.posY) + 1;
         int n3 = MathHelper.floor_double(this.theEntity.posZ);
         int n4 = (int)(pathSearchRange2 + 16.0F);
         ChunkCache chunkCache = new ChunkCache(this.worldObj, n - n4, n2 - n4, n3 - n4, n + n4, n2 + n4, n3 + n4, 0);
         PathEntity pathEntity = this.pathFinder.createEntityPathTo(chunkCache, this.theEntity, entity, pathSearchRange2);
         this.worldObj.theProfiler.endSection();
         return pathEntity;
      }
   }

   public void setHeightRequirement(float f) {
      this.heightRequirement = f;
   }

   public void pathFollow() {
      Vec3 entityPosition = this.getEntityPosition();
      int currentPathLength = this.currentPath.getCurrentPathLength();

      for (int i = this.currentPath.getCurrentPathIndex(); i < this.currentPath.getCurrentPathLength(); i++) {
         if (this.currentPath.getPathPointFromIndex(i).yCoord != (int)entityPosition.yCoord) {
            currentPathLength = i;
            break;
         }
      }

      float f = this.theEntity.width * this.theEntity.width * this.heightRequirement;

      for (int j = this.currentPath.getCurrentPathIndex(); j < currentPathLength; j++) {
         if (entityPosition.squareDistanceTo(this.currentPath.getVectorFromIndex(this.theEntity, j)) < (double)f) {
            this.currentPath.setCurrentPathIndex(j + 1);
         }
      }

      int n = MathHelper.ceiling_float_int(this.theEntity.width);
      int n2 = (int)this.theEntity.height + 1;

      for (int k = currentPathLength - 1; k >= this.currentPath.getCurrentPathIndex(); k--) {
         if (this.isDirectPathBetweenPoints(entityPosition, this.currentPath.getVectorFromIndex(this.theEntity, k), n, n2, n)) {
            this.currentPath.setCurrentPathIndex(k);
            break;
         }
      }

      this.checkForStuck(entityPosition);
   }

   public void setSpeed(double d) {
      this.speed = d;
   }

   public boolean tryMoveToXYZ(double d, double d2, double d3, double d4) {
      return this.setPath(this.getPathToXYZ((double)MathHelper.floor_double(d), (double)((int)d2), (double)MathHelper.floor_double(d3)), d4);
   }

   public abstract boolean isDirectPathBetweenPoints(Vec3 vec, Vec3 vec2, int n, int n2, int n3);

   public void removeSunnyPath() {
   }

   public float getPathSearchRange() {
      return (float)this.pathSearchRange.getAttributeValue();
   }
}

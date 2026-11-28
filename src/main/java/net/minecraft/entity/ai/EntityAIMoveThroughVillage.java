package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIMoveThroughVillage extends EntityAIBase {
   private EntityCreature theEntity;
   private double movementSpeed;
   private PathEntity entityPathNavigate;
   private VillageDoorInfo doorInfo;
   private boolean isNocturnal;
   private List doorList = Lists.newArrayList();

   public EntityAIMoveThroughVillage(EntityCreature entityCreature, double d, boolean flag) {
      this.theEntity = entityCreature;
      this.movementSpeed = d;
      this.isNocturnal = flag;
      this.setMutexBits(1);
      if (!(entityCreature.getNavigator() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
      }
   }

   @Override
   public void startExecuting() {
      this.theEntity.getNavigator().setPath(this.entityPathNavigate, this.movementSpeed);
   }

   private VillageDoorInfo findNearestDoor(Village village) {
      VillageDoorInfo villageDoorInfo = null;
      int maxvalue = Integer.MAX_VALUE;

      for (VillageDoorInfo villageDoorInfo2 : (Iterable<VillageDoorInfo>)(village.getVillageDoorInfoList())) {
         int distanceSquared = villageDoorInfo2.getDistanceSquared(
            MathHelper.floor_double(this.theEntity.posX), MathHelper.floor_double(this.theEntity.posY), MathHelper.floor_double(this.theEntity.posZ)
         );
         if (distanceSquared < maxvalue && !this.doesDoorListContain(villageDoorInfo2)) {
            villageDoorInfo = villageDoorInfo2;
            maxvalue = distanceSquared;
         }
      }

      return villageDoorInfo;
   }

   private boolean doesDoorListContain(VillageDoorInfo villageDoorInfo) {
      for (VillageDoorInfo villageDoorInfo2 : (Iterable<VillageDoorInfo>)(this.doorList)) {
         if (villageDoorInfo.getDoorBlockPos().equals(villageDoorInfo2.getDoorBlockPos())) {
            return true;
         }
      }

      return false;
   }

   private void resizeDoorList() {
      if (this.doorList.size() > 15) {
         this.doorList.remove(0);
      }
   }

   @Override
   public boolean continueExecuting() {
      if (this.theEntity.getNavigator().noPath()) {
         return false;
      } else {
         float f = this.theEntity.width + 4.0F;
         return this.theEntity.getDistanceSq(this.doorInfo.getDoorBlockPos()) > (double)(f * f);
      }
   }

   @Override
   public void resetTask() {
      if (this.theEntity.getNavigator().noPath() || this.theEntity.getDistanceSq(this.doorInfo.getDoorBlockPos()) < 16.0) {
         this.doorList.add(this.doorInfo);
      }
   }

   @Override
   public boolean shouldExecute() {
      this.resizeDoorList();
      if (this.isNocturnal && this.theEntity.entityWorld.isDaytime()) {
         return false;
      } else {
         Village nearestVillage = this.theEntity.entityWorld.getVillageCollection().getNearestVillage(new BlockPos(this.theEntity), 0);
         if (nearestVillage == null) {
            return false;
         } else {
            this.doorInfo = this.findNearestDoor(nearestVillage);
            if (this.doorInfo == null) {
               return false;
            } else {
               PathNavigateGround navigator = (PathNavigateGround)this.theEntity.getNavigator();
               boolean enterDoors = navigator.getEnterDoors();
               navigator.setBreakDoors(false);
               this.entityPathNavigate = navigator.getPathToPos(this.doorInfo.getDoorBlockPos());
               navigator.setBreakDoors(enterDoors);
               if (this.entityPathNavigate != null) {
                  return true;
               } else {
                  Vec3 vec = RandomPositionGenerator.findRandomTargetBlockTowards(
                     this.theEntity,
                     10,
                     7,
                     new Vec3(
                        (double)this.doorInfo.getDoorBlockPos().getX(),
                        (double)this.doorInfo.getDoorBlockPos().getY(),
                        (double)this.doorInfo.getDoorBlockPos().getZ()
                     )
                  );
                  if (vec == null) {
                     return false;
                  } else {
                     navigator.setBreakDoors(false);
                     this.entityPathNavigate = this.theEntity.getNavigator().getPathToXYZ(vec.xCoord, vec.yCoord, vec.zCoord);
                     navigator.setBreakDoors(enterDoors);
                     return this.entityPathNavigate != null;
                  }
               }
            }
         }
      }
   }
}

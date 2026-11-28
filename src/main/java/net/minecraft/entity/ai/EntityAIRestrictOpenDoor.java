package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIRestrictOpenDoor extends EntityAIBase {
   private EntityCreature entityObj;
   private VillageDoorInfo frontDoor;

   @Override
   public void resetTask() {
      ((PathNavigateGround)this.entityObj.getNavigator()).setBreakDoors(true);
      ((PathNavigateGround)this.entityObj.getNavigator()).setEnterDoors(true);
      this.frontDoor = null;
   }

   @Override
   public void startExecuting() {
      ((PathNavigateGround)this.entityObj.getNavigator()).setBreakDoors(false);
      ((PathNavigateGround)this.entityObj.getNavigator()).setEnterDoors(false);
   }

   public EntityAIRestrictOpenDoor(EntityCreature entityCreature) {
      this.entityObj = entityCreature;
      if (!(entityCreature.getNavigator() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for RestrictOpenDoorGoal");
      }
   }

   @Override
   public boolean continueExecuting() {
      return this.entityObj.entityWorld.isDaytime()
         ? false
         : !this.frontDoor.getIsDetachedFromVillageFlag() && this.frontDoor.isInsideSide(new BlockPos(this.entityObj));
   }

   @Override
   public boolean shouldExecute() {
      if (this.entityObj.entityWorld.isDaytime()) {
         return false;
      } else {
         BlockPos pos = new BlockPos(this.entityObj);
         Village nearestVillage = this.entityObj.entityWorld.getVillageCollection().getNearestVillage(pos, 16);
         if (nearestVillage == null) {
            return false;
         } else {
            this.frontDoor = nearestVillage.getNearestDoor(pos);
            return this.frontDoor == null ? false : (double)this.frontDoor.getDistanceToInsideBlockSq(pos) < 2.25;
         }
      }
   }

   @Override
   public void updateTask() {
      this.frontDoor.incrementDoorOpeningRestrictionCounter();
   }
}

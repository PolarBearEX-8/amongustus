package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIMoveIndoors extends EntityAIBase {
   private EntityCreature entityObj;
   private VillageDoorInfo doorInfo;
   private int insidePosX = -1;
   private int insidePosZ = -1;

   @Override
   public boolean shouldExecute() {
      BlockPos pos = new BlockPos(this.entityObj);
      if ((!this.entityObj.entityWorld.isDaytime() || this.entityObj.entityWorld.isRaining() && !this.entityObj.entityWorld.getBiomeGenForCoords(pos).canSpawnLightningBolt())
         && !this.entityObj.entityWorld.provider.getHasNoSky()) {
         if (this.entityObj.getRNG().nextInt(50) != 0) {
            return false;
         } else if (this.insidePosX != -1 && this.entityObj.getDistanceSq((double)this.insidePosX, this.entityObj.posY, (double)this.insidePosZ) < 4.0) {
            return false;
         } else {
            Village nearestVillage = this.entityObj.entityWorld.getVillageCollection().getNearestVillage(pos, 14);
            if (nearestVillage == null) {
               return false;
            } else {
               this.doorInfo = nearestVillage.getDoorInfo(pos);
               return this.doorInfo != null;
            }
         }
      } else {
         return false;
      }
   }

   @Override
   public void startExecuting() {
      this.insidePosX = -1;
      BlockPos insideBlockPos = this.doorInfo.getInsideBlockPos();
      int x = insideBlockPos.getX();
      int y = insideBlockPos.getY();
      int z = insideBlockPos.getZ();
      if (this.entityObj.getDistanceSq(insideBlockPos) > 256.0) {
         Vec3 vec = RandomPositionGenerator.findRandomTargetBlockTowards(this.entityObj, 14, 3, new Vec3((double)x + 0.5, (double)y, (double)z + 0.5));
         if (vec != null) {
            this.entityObj.getNavigator().tryMoveToXYZ(vec.xCoord, vec.yCoord, vec.zCoord, 1.0);
         }
      } else {
         this.entityObj.getNavigator().tryMoveToXYZ((double)x + 0.5, (double)y, (double)z + 0.5, 1.0);
      }
   }

   @Override
   public void resetTask() {
      this.insidePosX = this.doorInfo.getInsideBlockPos().getX();
      this.insidePosZ = this.doorInfo.getInsideBlockPos().getZ();
      this.doorInfo = null;
   }

   @Override
   public boolean continueExecuting() {
      return !this.entityObj.getNavigator().noPath();
   }

   public EntityAIMoveIndoors(EntityCreature entityCreature) {
      this.entityObj = entityCreature;
      this.setMutexBits(1);
   }
}

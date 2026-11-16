package client.util;

import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class AltClientManagerAdapter extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public SmoothTransformer smoothTransformer;
   public int count3 = -1;
   public int count2 = -1;

   @Override
   public void updateState() {
      this.count3 = this.smoothTransformer.getBlockPos2().getX();
      this.count2 = this.smoothTransformer.getBlockPos2().getZ();
      this.smoothTransformer = null;
   }

   @Override
   public void updateState3() {
      this.count3 = -1;
      BlockPos blockPos = this.smoothTransformer.getBlockPos2();
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      if (this.auxiliaryUUIDEntry.computeValue(blockPos) > 256.0) {
         Vec3 vec = Vec3Entry2.resolveVec2(this.auxiliaryUUIDEntry, 14, 3, new Vec3((double)coordX + 0.5, (double)coordY, (double)coordZ + 0.5));
         if (vec != null) {
            this.auxiliaryUUIDEntry.getPathfindManager().checkDouble(vec.xCoord, vec.yCoord, vec.zCoord, 1.0);
         }
      } else {
         this.auxiliaryUUIDEntry.getPathfindManager().checkDouble((double)coordX + 0.5, (double)coordY, (double)coordZ + 0.5, 1.0);
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.auxiliaryUUIDEntry.getPathfindManager().isActive2();
   }

   @Override
   public boolean isActive() {
      BlockPos blockPos = new BlockPos(this.auxiliaryUUIDEntry);
      if ((
            !this.auxiliaryUUIDEntry.worldObj.isActive2()
               || this.auxiliaryUUIDEntry.worldObj.isActive() && !this.auxiliaryUUIDEntry.worldObj.resolveBiomeIDIsOutOfBoundsManager(blockPos).isActive2()
         )
         && !this.auxiliaryUUIDEntry.worldObj.secondaryFloatArrayEntry.isEnabled()) {
         if (this.auxiliaryUUIDEntry.getRandom().nextInt(50) != 0) {
            return false;
         } else if (this.count3 != -1 && this.auxiliaryUUIDEntry.getDouble3((double)this.count3, this.auxiliaryUUIDEntry.posY, (double)this.count2) < 4.0) {
            return false;
         } else {
            SplineChecker splineChecker = this.auxiliaryUUIDEntry.worldObj.getWorldCustomProperty().resolveSplineChecker(blockPos, 14);
            if (splineChecker == null) {
               return false;
            } else {
               this.smoothTransformer = splineChecker.resolveSmoothTransformerByBlockPos(blockPos);
               return this.smoothTransformer != null;
            }
         }
      } else {
         return false;
      }
   }

   public AltClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.setCount(1);
   }
}

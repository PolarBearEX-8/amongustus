package client.util;

import net.minecraft.util.BlockPos;

public class TertiaryClientManagerAdapter extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public SmoothTransformer smoothTransformer;

   @Override
   public void updateState() {
      ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).setEnabled(true);
      ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).setEnabledByFlag(true);
      this.smoothTransformer = null;
   }

   @Override
   public void updateState3() {
      ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).setEnabled(false);
      ((PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager()).setEnabledByFlag(false);
   }

   @Override
   public void updateState2() {
      this.smoothTransformer.updateState();
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.auxiliaryUUIDEntry.worldObj.isActive2()
         ? false
         : !this.smoothTransformer.isEnabled() && this.smoothTransformer.matchesBlockPos(new BlockPos(this.auxiliaryUUIDEntry));
   }

   @Override
   public boolean isActive() {
      if (this.auxiliaryUUIDEntry.worldObj.isActive2()) {
         return false;
      } else {
         BlockPos blockPos = new BlockPos(this.auxiliaryUUIDEntry);
         SplineChecker splineChecker = this.auxiliaryUUIDEntry.worldObj.getWorldCustomProperty().resolveSplineChecker(blockPos, 16);
         if (splineChecker == null) {
            return false;
         } else {
            this.smoothTransformer = splineChecker.resolveSmoothTransformer(blockPos);
            return this.smoothTransformer == null ? false : (double)this.smoothTransformer.computeCountByBlockPos(blockPos) < 2.25;
         }
      }
   }

   public TertiaryClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      if (!(auxiliaryUUIDEntry2.getPathfindManager() instanceof PathfindManagerAdapter)) {
         throw new IllegalArgumentException("Unsupported mob type for RestrictOpenDoorGoal");
      }
   }
}

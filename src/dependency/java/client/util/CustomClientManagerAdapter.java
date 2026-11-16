package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class CustomClientManagerAdapter extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public double value;
   public InterpolationConverter interpolationConverter;
   public SmoothTransformer smoothTransformer;
   public boolean enabled;
   public List list = Lists.newArrayList();

   public CustomClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset, boolean flag) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.value = offset;
      this.enabled = flag;
      this.setCount(1);
      if (!(auxiliaryUUIDEntry2.getPathfindManager() instanceof PathfindManagerAdapter)) {
         throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
      }
   }

   private boolean matchesSmoothTransformer(SmoothTransformer smoothTransformer2) {
      for (SmoothTransformer smoothTransformer3 : (Iterable<SmoothTransformer>)(Iterable)(this.list)) {
         if (smoothTransformer2.getBlockPos().equals(smoothTransformer3.getBlockPos())) {
            return true;
         }
      }

      return false;
   }

   private void updateState4() {
      if (this.list.size() > 15) {
         this.list.remove(0);
      }
   }

   @Override
   public void updateState() {
      if (this.auxiliaryUUIDEntry.getPathfindManager().isActive2() || this.auxiliaryUUIDEntry.computeValue(this.smoothTransformer.getBlockPos()) < 16.0) {
         this.list.add(this.smoothTransformer);
      }
   }

   private SmoothTransformer resolveSmoothTransformer(SplineChecker splineChecker) {
      SmoothTransformer smoothTransformer2 = null;
      int count = Integer.MAX_VALUE;

      for (SmoothTransformer smoothTransformer3 : (Iterable<SmoothTransformer>)(Iterable)(splineChecker.getList())) {
         int count2 = smoothTransformer3.computeCount(
            MathHelper.ceiling_double_int(this.auxiliaryUUIDEntry.posX),
            MathHelper.ceiling_double_int(this.auxiliaryUUIDEntry.posY),
            MathHelper.ceiling_double_int(this.auxiliaryUUIDEntry.posZ)
         );
         if (count2 < count && !this.matchesSmoothTransformer(smoothTransformer3)) {
            smoothTransformer2 = smoothTransformer3;
            count = count2;
         }
      }

      return smoothTransformer2;
   }

   @Override
   public boolean isActive() {
      this.updateState4();
      if (this.enabled && this.auxiliaryUUIDEntry.worldObj.isActive2()) {
         return false;
      } else {
         SplineChecker splineChecker = this.auxiliaryUUIDEntry.worldObj.getWorldCustomProperty().resolveSplineChecker(new BlockPos(this.auxiliaryUUIDEntry), 0);
         if (splineChecker == null) {
            return false;
         } else {
            this.smoothTransformer = this.resolveSmoothTransformer(splineChecker);
            if (this.smoothTransformer == null) {
               return false;
            } else {
               PathfindManagerAdapter pathfindManager = (PathfindManagerAdapter)this.auxiliaryUUIDEntry.getPathfindManager();
               boolean active = pathfindManager.isActive3();
               pathfindManager.setEnabled(false);
               this.interpolationConverter = pathfindManager.resolveInterpolationConverter(this.smoothTransformer.getBlockPos());
               pathfindManager.setEnabled(active);
               if (this.interpolationConverter != null) {
                  return true;
               } else {
                  Vec3 vec = Vec3Entry2.resolveVec2(
                     this.auxiliaryUUIDEntry,
                     10,
                     7,
                     new Vec3(
                        (double)this.smoothTransformer.getBlockPos().getX(),
                        (double)this.smoothTransformer.getBlockPos().getY(),
                        (double)this.smoothTransformer.getBlockPos().getZ()
                     )
                  );
                  if (vec == null) {
                     return false;
                  } else {
                     pathfindManager.setEnabled(false);
                     this.interpolationConverter = this.auxiliaryUUIDEntry
                        .getPathfindManager()
                        .resolveInterpolationConverter(vec.xCoord, vec.yCoord, vec.zCoord);
                     pathfindManager.setEnabled(active);
                     return this.interpolationConverter != null;
                  }
               }
            }
         }
      }
   }

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.getPathfindManager().matchesInterpolationConverter(this.interpolationConverter, this.value);
   }

   @Override
   public boolean isActiveAsFlag() {
      if (this.auxiliaryUUIDEntry.getPathfindManager().isActive2()) {
         return false;
      } else {
         float factor = this.auxiliaryUUIDEntry.width + 4.0F;
         return this.auxiliaryUUIDEntry.computeValue(this.smoothTransformer.getBlockPos()) > (double)(factor * factor);
      }
   }
}

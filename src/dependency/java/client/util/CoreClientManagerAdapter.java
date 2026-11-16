package client.util;

import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class CoreClientManagerAdapter extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public double value;
   public double value4;
   public double value3;
   public double value2;

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.getPathfindManager().checkDouble(this.value, this.value4, this.value3, this.value2);
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.auxiliaryUUIDEntry.getPathfindManager().isActive2();
   }

   @Override
   public boolean isActive() {
      if (this.auxiliaryUUIDEntry.isActive29()) {
         return false;
      } else {
         BlockPos blockPos = this.auxiliaryUUIDEntry.getBlockPos2();
         Vec3 vec = Vec3Entry2.resolveVec2(this.auxiliaryUUIDEntry, 16, 7, new Vec3((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()));
         if (vec == null) {
            return false;
         } else {
            this.value = vec.xCoord;
            this.value4 = vec.yCoord;
            this.value3 = vec.zCoord;
            return true;
         }
      }
   }

   public CoreClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.value2 = offset;
      this.setCount(1);
   }
}

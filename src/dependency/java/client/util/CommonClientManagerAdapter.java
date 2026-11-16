package client.util;

import net.minecraft.util.Vec3;

public class CommonClientManagerAdapter extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public double value;
   public double value2;
   public double value4;
   public double value3;

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.getPathfindManager().checkDouble(this.value2, this.value4, this.value3, this.value);
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.auxiliaryUUIDEntry.getPathfindManager().isActive2();
   }

   @Override
   public boolean isActive() {
      if (this.auxiliaryUUIDEntry.getTargetEntity() == null && !this.auxiliaryUUIDEntry.canExecute2()) {
         return false;
      } else {
         Vec3 vec = Vec3Entry2.resolveVec3(this.auxiliaryUUIDEntry, 5, 4);
         if (vec == null) {
            return false;
         } else {
            this.value2 = vec.xCoord;
            this.value4 = vec.yCoord;
            this.value3 = vec.zCoord;
            return true;
         }
      }
   }

   public CommonClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.value = offset;
      this.setCount(1);
   }
}

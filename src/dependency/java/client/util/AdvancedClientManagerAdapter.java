package client.util;

import net.minecraft.util.Vec3;

public class AdvancedClientManagerAdapter extends RotationPool {
   public AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public double value2;
   public double value;
   public double value3;
   public double value4;
   public int count2;
   public boolean enabled;

   public void setCount2(int count) {
      this.count2 = count;
   }

   public void updateState4() {
      this.enabled = true;
   }

   @Override
   public void updateState3() {
      this.auxiliaryUUIDEntry.getPathfindManager().checkDouble(this.value2, this.value, this.value3, this.value4);
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.auxiliaryUUIDEntry.getPathfindManager().isActive2();
   }

   @Override
   public boolean isActive() {
      if (!this.enabled) {
         if (this.auxiliaryUUIDEntry.getIndexOffset14() >= 100) {
            return false;
         }

         if (this.auxiliaryUUIDEntry.getRandom().nextInt(this.count2) != 0) {
            return false;
         }
      }

      Vec3 vec = Vec3Entry2.resolveVec3(this.auxiliaryUUIDEntry, 10, 7);
      if (vec == null) {
         return false;
      } else {
         this.value2 = vec.xCoord;
         this.value = vec.yCoord;
         this.value3 = vec.zCoord;
         this.enabled = false;
         return true;
      }
   }

   public AdvancedClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset) {
      this(auxiliaryUUIDEntry2, offset, 120);
   }

   public AdvancedClientManagerAdapter(AuxiliaryUUIDEntry auxiliaryUUIDEntry2, double offset, int count) {
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
      this.value4 = offset;
      this.count2 = count;
      this.setCount(1);
   }
}

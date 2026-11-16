package client.util;

public class TargetAggregator {
   public RotationMonitor[] rotationMonitorArray = new RotationMonitor[0];

   @Override
   public String toString() {
      StringBuffer builder = new StringBuffer();
      builder.append("[");

      for (int index = 0; index < this.rotationMonitorArray.length; index++) {
         RotationMonitor rotationMonitor = this.rotationMonitorArray[index];
         if (index > 0) {
            builder.append(", ");
         }

         builder.append(rotationMonitor.toString());
      }

      builder.append("]");
      return builder.toString();
   }

   public TargetAggregator(RotationMonitor rotationMonitor) {
      this.applyRotationMonitor(rotationMonitor);
   }

   public TargetAggregator() {
   }

   public RotationMonitor resolveRotationMonitor(int count) {
      return this.rotationMonitorArray[count];
   }

   public void applyRotationMonitor(RotationMonitor rotationMonitor) {
      this.rotationMonitorArray = (RotationMonitor[])BuildManager.resolveTargetArrayByTargetArray(this.rotationMonitorArray, rotationMonitor);
   }

   public boolean checkIndex(int count) {
      for (int index = 0; index < this.rotationMonitorArray.length; index++) {
         if (this.rotationMonitorArray[index].checkIndex(count)) {
            return true;
         }
      }

      return false;
   }

   public int getCount() {
      return this.rotationMonitorArray.length;
   }
}

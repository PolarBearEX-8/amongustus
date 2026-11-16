package client.util;

public class NativeCustom implements Callback22, DistanceBridge {
   public DistanceBridge distanceBridge;
   public boolean enabled;
   public float[] factorArray;

   @Override
   public float[] getFloatArray() {
      if (!this.enabled) {
         this.factorArray = this.distanceBridge.getFloatArray();
         this.enabled = true;
      }

      return this.factorArray;
   }

   public NativeCustom(DistanceBridge distanceBridge2) {
      this.distanceBridge = distanceBridge2;
   }

   @Override
   public String toString() {
      return "cached(" + this.distanceBridge + ")";
   }

   @Override
   public AimSorter getBOOL() {
      return AimSorter.FLOAT;
   }

   @Override
   public void updateState() {
      this.enabled = false;
   }
}

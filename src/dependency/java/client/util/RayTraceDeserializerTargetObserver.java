package client.util;

public class RayTraceDeserializerTargetObserver implements TargetObserver {
   public RayTraceDeserializer rayTraceDeserializer;

   @Override
   public AimSorter getBOOL() {
      return AimSorter.BOOL;
   }

   @Override
   public boolean isActive() {
      return RayTraceDeserializer.parseBoolean(this.rayTraceDeserializer.getLabelText2());
   }

   @Override
   public String toString() {
      return "" + this.rayTraceDeserializer;
   }

   public RayTraceDeserializerTargetObserver(RayTraceDeserializer rayTraceDeserializer2) {
      this.rayTraceDeserializer = rayTraceDeserializer2;
   }
}

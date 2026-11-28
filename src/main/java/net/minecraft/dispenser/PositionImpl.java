package net.minecraft.dispenser;

public class PositionImpl implements IPosition {
   public final double x;
   public final double y;
   public final double z;

   @Override
   public double getX() {
      return this.x;
   }

   @Override
   public double getZ() {
      return this.z;
   }

   public PositionImpl(double d, double d2, double d3) {
      this.x = d;
      this.y = d2;
      this.z = d3;
   }

   @Override
   public double getY() {
      return this.y;
   }
}

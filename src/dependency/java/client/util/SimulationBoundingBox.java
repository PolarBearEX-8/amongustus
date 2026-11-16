package client.util;

import net.minecraft.util.MathHelper;

public class SimulationBoundingBox implements MinecraftAccessor {
   public double value2;
   public int count;
   public SimulationSnapshot simulationSnapshot;
   public double[] valueArray;
   public double value;

   public SimulationSnapshot getSimulationSnapshot() {
      return this.simulationSnapshot;
   }

   public void setSimulationSnapshot(SimulationSnapshot simulationSnapshot2) {
      this.simulationSnapshot = simulationSnapshot2;
   }

   public double[] getValueArray() {
      return this.valueArray;
   }

   public double getCoordinate() {
      return this.value;
   }

   public void applyDouble(double offset, double offset2) {
      this.value2 = MathHelper.getDouble4(offset);
      this.value = offset2;
   }

   public int getCount() {
      return this.count;
   }

   public void setValueArray(double[] valueArray2) {
      this.valueArray = valueArray2;
   }

   public double getCoordinate2() {
      return this.value2;
   }

   public SimulationBoundingBox(SimulationSnapshot simulationSnapshot2, double offset, double offset2) {
      this.value2 = MathHelper.getDouble4(offset);
      this.value = MathHelper.getDouble7(offset2, -90.0, 90.0);
      this.simulationSnapshot = simulationSnapshot2;
      if (MinecraftAccessor.minecraft.thePlayer != null) {
         this.count = MinecraftAccessor.minecraft.thePlayer.ticksExisted;
      }
   }
}

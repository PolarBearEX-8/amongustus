package net.minecraft.item;

import client.util.SimulationSnapshot;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class GoldenSword {
   public SimulationSnapshot simulationSnapshot;
   public Vec3 vec2;
   public double value;
   public double value2;
   public double value3;
   public boolean enabled2;
   public boolean enabled = false;
   public Vec3 vec3;
   public SimulationSnapshot simulationSnapshot2;

   public double getValue() {
      return this.value;
   }

   public void applyDouble(double offset, double offset2) {
      this.value2 = MathHelper.getDouble4(offset);
      this.value3 = offset2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public SimulationSnapshot getSimulationSnapshot2() {
      return this.simulationSnapshot2;
   }

   public Vec3 getVec2() {
      return this.vec2;
   }

   public void setEnabled2(boolean enabled3) {
      this.enabled2 = enabled3;
   }

   public double getValue3() {
      return this.value3;
   }

   public void setValue(double value4) {
      this.value = value4;
   }

   public double getValue2() {
      return this.value2;
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public Vec3 getVec3() {
      return this.vec3;
   }

   public SimulationSnapshot getSimulationSnapshot() {
      return this.simulationSnapshot;
   }

   public void setSimulationSnapshot(SimulationSnapshot simulationSnapshot3) {
      this.simulationSnapshot = simulationSnapshot3;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public GoldenSword(Vec3 vec, Vec3 vec4, SimulationSnapshot simulationSnapshot3, SimulationSnapshot simulationSnapshot4, double offset, double offset2) {
      this.vec3 = vec;
      this.vec2 = vec4;
      this.simulationSnapshot2 = simulationSnapshot3;
      this.simulationSnapshot = simulationSnapshot4;
      this.value2 = MathHelper.getDouble4(offset);
      this.value3 = offset2;
   }
}

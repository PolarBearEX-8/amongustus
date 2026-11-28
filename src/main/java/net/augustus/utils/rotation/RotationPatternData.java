package net.augustus.utils.rotation;

import net.augustus.utils.math.MathStatisticsUtil;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RotationPatternData {
   private ArrayList yawSamples;
   private final String patternName;
   private ArrayList pitchSamples;
   private double pitchIndex;
   private double yawIndex;

   public ArrayList getYawSamples() {
      return this.yawSamples;
   }

   public double samplePitch(double d) {
      int n = (int)Math.floor(d);
      int n2 = (int)Math.ceil(d);
      if (n >= this.pitchSamples.size()) {
         n = this.pitchSamples.size() - 1;
      }

      if (n2 >= this.pitchSamples.size()) {
         n2 = this.pitchSamples.size() - 1;
      }

      double d2 = d % 1.0;
      return MathStatisticsUtil.lerp((Double)this.pitchSamples.get(n), (Double)this.pitchSamples.get(n2), d2);
   }

   public String getPatternName() {
      return this.patternName;
   }

   public void setPitchIndex(double d) {
      if (d >= (double)this.pitchSamples.size()) {
         d = 0.0;
      }

      this.pitchIndex = d;
   }

   public void setYawIndex(double d) {
      if (d >= (double)this.yawSamples.size()) {
         d = 0.0;
      }

      this.yawIndex = d;
   }

   public double getYawIndex() {
      return this.yawIndex;
   }

   public void randomizeIndices() {
      this.yawIndex = ThreadLocalRandom.current().nextDouble(0.0, (double)(this.yawSamples.size() - 1));
      this.pitchIndex = ThreadLocalRandom.current().nextDouble(0.0, (double)(this.pitchSamples.size() - 1));
   }

   public double getPitchIndex() {
      return this.pitchIndex;
   }

   public double sampleYaw(double d) {
      int n = (int)Math.floor(d);
      int n2 = (int)Math.ceil(d);
      if (n >= this.yawSamples.size()) {
         n = this.yawSamples.size() - 1;
      }

      if (n2 >= this.yawSamples.size()) {
         n2 = this.yawSamples.size() - 1;
      }

      double d2 = d % 1.0;
      return MathStatisticsUtil.lerp((Double)this.yawSamples.get(n), (Double)this.yawSamples.get(n2), d2);
   }

   public RotationPatternData(ArrayList list, ArrayList list2, String str) {
      this.yawSamples = list;
      this.pitchSamples = list2;
      this.patternName = str;
   }

   public ArrayList getPitchSamples() {
      return this.pitchSamples;
   }



}

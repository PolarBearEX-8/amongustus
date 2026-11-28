package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.settings.VarianceNoiseSetting;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.settings.SettingGroup;
import net.minecraft.util.Vec3;

public class UNoise3PointAimMode extends UNoise3AimMode {
   private VarianceNoiseSetting x;
   private VarianceNoiseSetting z;

   public UNoise3PointAimMode(UNoise3SettingContext uNoise3SettingContext2, boolean flag) {
       super(uNoise3SettingContext2, "Point", UNoise3AimConstraintType.NONE, true, flag);
      uNoise3SettingContext2 = uNoise3SettingContext2.withParentGroup(this.getSettingsGroup());
      VarianceNoiseSetting varianceNoiseSetting = new VarianceNoiseSetting(uNoise3SettingContext2, "x", true);
      varianceNoiseSetting = varianceNoiseSetting.named("x");
      double d = 100.0;
      double d2 = 0.0;
      this.x = varianceNoiseSetting.withVariance(100.0, d2, d);
      varianceNoiseSetting = new VarianceNoiseSetting(uNoise3SettingContext2, "z", true);
      varianceNoiseSetting = varianceNoiseSetting.named("z");
      double d4 = 100.0;
      double d5 = 0.0;
      this.z = varianceNoiseSetting.withVariance(100.0, d5, d4);
      this.x.addDefaultLayer();
      this.z.addDefaultLayer();
   }

   @Override
   public double computeTargetAngle(UNoise3AimContext uNoise3AimContext) {
      double d = 0.5;
      double d2 = 0.5;
      double d3 = MathStatisticsUtil.lerp(uNoise3AimContext.targetBoundingBox.minX, uNoise3AimContext.targetBoundingBox.maxX, this.x.sampleWithVariance(d2, d));
      double d4 = 0.5;
      double d5 = 0.5;
      Vec3 vec2 = new Vec3(d3, 0.0, MathStatisticsUtil.lerp(uNoise3AimContext.targetBoundingBox.minZ, uNoise3AimContext.targetBoundingBox.maxZ, this.z.sampleWithVariance(d5, d4))).subtract(uNoise3AimContext.playerEyePos);
      return Math.atan2(vec2.zCoord, vec2.xCoord) / Math.PI * 180.0 - 90.0;
   }

   @Override
   public void stepNoise() {
      this.x.stepLayers();
      this.z.stepLayers();
   }
}

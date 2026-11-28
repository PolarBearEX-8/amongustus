package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.settings.IntervalNoiseSetting;
import net.augustus.settings.NoiseLayerSetting;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.utils.rotation.RotationUtil;
import java.util.ArrayList;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.interfaces.MC;
import net.minecraft.util.MathHelper;

public abstract class UNoise3AimMode implements MC {
   private BooleanValue restrictToHittableSurface;
   private DoubleValue minSurfaceSize;
   private BooleanValue limitSurfaceToBoundingBox;
   private DoubleValue minSurfaceSizeBlocksOnly;
   private SettingGroup settingsGroup;
   private DoubleValue restrictionStrength;
   private DoubleValue restrictionStrengthBlocksOnly;
   private UNoise3SettingContext uNoise3SettingContext;
   private boolean vertical = false;
   private boolean supportsSurfaceRestriction;
   private UNoise3AimConstraintType uNoise3AimConstraintType;
   public IntervalNoiseSetting strengthNoise;
   private String name;
   private DoubleValue unrestrictedRotationStrength;

   private static void notifyStrengthRangeChanged(UNoise3SettingContext uNoise3SettingContext2, boolean flag) {
      uNoise3SettingContext2.getUNoise3PointSelector().recalculateModeStrengthLabels(flag);
   }

   public boolean isSurfaceRestrictionActive() {
      return this.supportsSurfaceRestriction && this.restrictToHittableSurface.getValue();
   }

   private void initSurfaceRestrictionSettings(UNoise3SettingContext uNoise3SettingContext2, UNoise3AimConstraintType uNoise3AimConstraintType2) {
      this.restrictToHittableSurface = (BooleanValue)new BooleanValue(uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getParentGroup().getName() + "_" + this.name + "restrictsurface", false, uNoise3SettingContext2.getParentGroup()).withDisplayName("Restrict Boundingbox to hittable surface only");
      if (uNoise3AimConstraintType2 != UNoise3AimConstraintType.POINT) {
         SettingGroup settingGroup = new SettingGroup(
            uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getParentGroup().getName() + "_" + this.name + "hittable_surface_restriction", uNoise3SettingContext2.getParentGroup()
         );
         settingGroup = (SettingGroup)settingGroup.withDisplayName("Hittable surface restriction");
         BooleanValue booleanValue2 = this.restrictToHittableSurface;
         SettingGroup settingGroup2 = settingGroup.visibleWhen(() -> booleanValue2.getValue());
         this.unrestrictedRotationStrength = new DoubleValue(uNoise3SettingContext2.getModule(), settingGroup2.getName() + "_unrestrictedstrength", 0.0, 0.0, 100.0, 1, settingGroup2).named("Strength of the unrestricted rotation");
         this.restrictionStrength = new DoubleValue(uNoise3SettingContext2.getModule(), settingGroup2.getName() + "_strength", 100.0, 0.0, 100.0, 1, settingGroup2).named("Strength");
         this.minSurfaceSize = new DoubleValue(uNoise3SettingContext2.getModule(), settingGroup2.getName() + "_minsurface", 1.0, 0.0, 30.0, 1, settingGroup2).named("Minimal surface size");
         this.restrictionStrengthBlocksOnly = new DoubleValue(uNoise3SettingContext2.getModule(), settingGroup2.getName() + "_strengthblocks", 100.0, 0.0, 100.0, 1, settingGroup2).named("Strength (Blocks only)");
         this.minSurfaceSizeBlocksOnly = new DoubleValue(uNoise3SettingContext2.getModule(), settingGroup2.getName() + "_minsurfaceblocks", 1.0, 0.0, 30.0, 1, settingGroup2).named("Minimal surface size (Blocks only)");
         if (this.vertical) {
            this.limitSurfaceToBoundingBox = (BooleanValue)new BooleanValue(uNoise3SettingContext2.getModule(), settingGroup2.getName() + "_limitBBSize", false, settingGroup2).withDisplayName("Limit surface size to actual bounding box size");
         }
      }
   }

   public IntervalNoiseSetting getStrengthNoise() {
      return this.strengthNoise;
   }

   public UNoise3AimMode(UNoise3SettingContext uNoise3SettingContext2) {
      this.uNoise3SettingContext = uNoise3SettingContext2;
   }

   public void setVertical(boolean flag) {
      this.vertical = flag;
   }

   public double clampToUnoccludedBounds(UNoise3AimContext uNoise3AimContext, double d) {
      double[] unoccludedAngleBounds = uNoise3AimContext.unoccludedAngleBounds;
      if (uNoise3AimContext.vertical) {
         return MathHelper.clamp_double(d, unoccludedAngleBounds[0], unoccludedAngleBounds[1]);
      } else {
         double normalizedYawPosition = HitboxAngleUtil.getNormalizedYawPosition(uNoise3AimContext.expandedAngleBounds[0], uNoise3AimContext.expandedAngleBounds[1], d);
         normalizedYawPosition = MathHelper.clamp_double(normalizedYawPosition, HitboxAngleUtil.getNormalizedYawPosition(uNoise3AimContext.expandedAngleBounds[0], uNoise3AimContext.expandedAngleBounds[1], unoccludedAngleBounds[0]), HitboxAngleUtil.getNormalizedYawPosition(uNoise3AimContext.expandedAngleBounds[0], uNoise3AimContext.expandedAngleBounds[1], unoccludedAngleBounds[1]));
         return MathHelper.wrapAngleTo180_double(HitboxAngleUtil.lerpRange(uNoise3AimContext.expandedAngleBounds, normalizedYawPosition));
      }
   }

   public UNoise3AimMode(UNoise3SettingContext uNoise3SettingContext2, String str, boolean flag) {
       this(uNoise3SettingContext2, str, UNoise3AimConstraintType.NONE, false, flag);
   }

   private double[] blendRestrictedBounds(double[] doubles, double[] doubles2, double d, double d2, boolean flag) {
      if (flag) {
         double[] doubles3 = new double[]{RotationUtil.lerpPitch(doubles2[0], doubles[0], d2), RotationUtil.lerpPitch(doubles2[1], doubles[1], d2)};
         if (Math.abs(doubles3[0] - doubles3[1]) < d) {
            double d3 = (doubles3[0] + doubles3[1]) / 2.0;
            doubles3[0] = d3 - d;
            doubles3[1] = d3 + d;
            if (this.limitSurfaceToBoundingBox.getValue()) {
               doubles3[0] = Math.max(doubles3[0], doubles2[0]);
               doubles3[1] = Math.min(doubles3[1], doubles2[1]);
            }
         }

         return HitboxAngleUtil.findMinMaxWithIndices(doubles3);
      } else {
         double[] doubles4 = new double[]{RotationUtil.lerpYaw(doubles2[0], doubles[0], d2), RotationUtil.lerpYaw(doubles2[1], doubles[1], d2)};
         doubles4 = HitboxAngleUtil.normalizeYawInterval(doubles4[0], doubles4[1]);
         if (HitboxAngleUtil.getShortestAngularDistance(MathHelper.wrapAngleTo180_double(doubles4[0]), MathHelper.wrapAngleTo180_double(doubles4[1])) < d) {
            double d7 = HitboxAngleUtil.lerpRange(doubles4, 0.5);
            doubles4[0] = d7 - d;
            doubles4[1] = d7 + d;
         }

         return doubles4;
      }
   }

   public SettingGroup getSettingsGroup() {
      return this.settingsGroup;
   }

   public double[] getEffectiveAngleBounds(UNoise3AimContext uNoise3AimContext, boolean flag) {
      double[] doubles = flag ? uNoise3AimContext.expandedAngleBounds : uNoise3AimContext.exactAngleBounds;
      if (this.supportsSurfaceRestriction && this.restrictToHittableSurface.getValue()) {
         if (this.unrestrictedRotationStrength.getValue(true) == 0.0) {
            if (this.restrictionStrength.getValue(true) == 0.0) {
               if (this.restrictionStrengthBlocksOnly.getValue(true) == 0.0) {
                  return doubles;
               }
            }
         }

         double[] hittableAngleBounds = uNoise3AimContext.hittableAngleBounds;
         double value = this.minSurfaceSize.getValue();
         double d = this.restrictionStrength.getValue(true) / 100.0;
         double[] doubles3 = this.blendRestrictedBounds(hittableAngleBounds, doubles, value, d, uNoise3AimContext.vertical);
         hittableAngleBounds = uNoise3AimContext.unoccludedAngleBounds;
         value = this.minSurfaceSizeBlocksOnly.getValue();
         d = this.restrictionStrengthBlocksOnly.getValue(true) / 100.0;
         double[] doubles5 = this.blendRestrictedBounds(hittableAngleBounds, doubles, value, d, uNoise3AimContext.vertical);
         ArrayList list = new ArrayList();
         double d6 = doubles[0];
         list.add(new Vec2d(d6, this.unrestrictedRotationStrength.getValue(true) / 100.0));
         d6 = doubles3[0];
         list.add(new Vec2d(d6, this.restrictionStrength.getValue(true) / 100.0));
         d6 = doubles5[0];
         list.add(new Vec2d(d6, this.restrictionStrengthBlocksOnly.getValue(true) / 100.0));
         double d7 = UNoise3PointSelector.computeWeightedAngleAverage(list, !uNoise3AimContext.vertical);
         list = new ArrayList();
         d6 = doubles[1];
         list.add(new Vec2d(d6, this.unrestrictedRotationStrength.getValue(true) / 100.0));
         d6 = doubles3[1];
         list.add(new Vec2d(d6, this.restrictionStrength.getValue(true) / 100.0));
         d6 = doubles5[1];
         list.add(new Vec2d(d6, this.restrictionStrengthBlocksOnly.getValue(true) / 100.0));
         double d8 = UNoise3PointSelector.computeWeightedAngleAverage(list, !uNoise3AimContext.vertical);
         return uNoise3AimContext.vertical ? HitboxAngleUtil.findMinMaxWithIndices(new double[]{d7, d8}) : HitboxAngleUtil.normalizeYawInterval(d7, d8);
      } else {
         return doubles;
      }
   }

   public abstract double computeTargetAngle(UNoise3AimContext uNoise3AimContext);

   public double sampleWeight(UNoise3AimContext uNoise3AimContext) {
      this.strengthNoise.stepLayers();
      return this.strengthNoise.sample();
   }

   public void updateStrengthSummaryLabel(double d, double d2) {
      if (this.settingsGroup == null) {
         this.strengthNoise.noisegroup.updateDisplayName(this.name + " Strength (min=" + MathStatisticsUtil.roundToDecimalPlaces(d * 100.0, 2) + "%, max=" + MathStatisticsUtil.roundToDecimalPlaces(d2 * 100.0, 2) + "%)");
      } else {
         this.settingsGroup.updateDisplayName(this.name + " (min=" + MathStatisticsUtil.roundToDecimalPlaces(d * 100.0, 2) + "%, max=" + MathStatisticsUtil.roundToDecimalPlaces(d2 * 100.0, 2) + "%)");
      }
   }

   public String getName() {
      return this.name;
   }

   public boolean isVertical() {
      return this.vertical;
   }

   public UNoise3AimMode(UNoise3SettingContext uNoise3SettingContext2, String str, UNoise3AimConstraintType uNoise3AimConstraintType2, boolean flag, boolean flag2) {
       super();
      this.vertical = false;
      if (uNoise3AimConstraintType2 != UNoise3AimConstraintType.NONE) {
         flag = true;
      }

      this.name = str;
      this.uNoise3SettingContext = uNoise3SettingContext2;
      this.supportsSurfaceRestriction = uNoise3AimConstraintType2 != UNoise3AimConstraintType.NONE;
      this.uNoise3AimConstraintType = uNoise3AimConstraintType2;
      this.vertical = flag2;
      if (flag) {
         this.settingsGroup = (SettingGroup)(new SettingGroup(uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getParentGroup().getName() + "_" + str, uNoise3SettingContext2.getParentGroup())).withDisplayName(str);
         this.strengthNoise = new IntervalNoiseSetting(uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getParentGroup().getName() + "_" + str, this.settingsGroup, false);
         if (this.supportsSurfaceRestriction) {
            this.initSurfaceRestrictionSettings(uNoise3SettingContext2.withParentGroup(this.settingsGroup), uNoise3AimConstraintType2);
         }
      } else {
         this.strengthNoise = new IntervalNoiseSetting(uNoise3SettingContext2.getModule(), uNoise3SettingContext2.getParentGroup().getName() + "_" + str, uNoise3SettingContext2.getParentGroup(), false);
      }

      double d = 0.0;
      double d2 = 0.0;
      double d3 = 100.0;
      double d4 = 0.0;
      this.strengthNoise.withInterval(d4, d3, d2, d, (byte)0);
      String str2 = str + " Strength";
      this.strengthNoise.named(str2);
      this.strengthNoise.addLayer(this.strengthNoise.newLayerBuilder().build());
      this.strengthNoise.intervalRange.addPropertyChangeCallback(() -> notifyStrengthRangeChanged(uNoise3SettingContext2, flag2));
   }

   public void stepNoise() {
   }




}

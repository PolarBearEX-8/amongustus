package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.utils.random.GeneralizedNormalSampler;
import java.util.Random;
import net.augustus.modules.Module;
import net.minecraft.util.MathHelper;

public class RandomizedRangeSettings extends SettingComposite {
   private RangeSetting outlierIntervalRange;
   private DoubleValue betaSetting;
   private GeneralizedNormalSampler generalizedNormalSampler;
   private int lastIntValue;
   private final Random random;
   private DoubleValue centerSetting;
   private BooleanValue notInARow;
   private DoubleValue maxInARowSetting;
   private BooleanValue outliersEnabled;
   private final SettingGroup parentGroup;
   private int consecutiveSameCount;
   private RangeSetting outlierValueRange;
   private final String baseName;
   private BooleanValue normalDistributionEnabled;
   private int samplesSinceOutlier;
   private int decimalPlaces;
   private RangeSetting rangeSetting;
   private boolean notInARowDisabled;
   private int nextOutlierThreshold;
   private final Module parentModule;
   private double lastDoubleValue;
   private DoubleValue sigmaSetting;

   public double nextDouble(boolean flag) {
      RangeSetting rangeSetting2 = this.rangeSetting;
      double d = this.sampleDoubleAvoidingRepeat(this.lastDoubleValue, rangeSetting2, false, flag);
      if (this.shouldTriggerOutlier()) {
         double d2 = Math.max(0.0, this.rangeSetting.getMinValue() - this.outlierValueRange.getMinValue());
         double maxValue = this.outlierValueRange.getMaxValue();
         double d3 = Math.max(0.0, maxValue - this.rangeSetting.getMaxValue());
         double d4 = d2 + d3;
         if (d4 > 0.0) {
            double d5;
            if (d2 == 0.0) {
               double d6 = this.outlierValueRange.getMaxValue(flag) - Math.max(this.outlierValueRange.getMinValue(flag), this.rangeSetting.getMaxValue(flag));
               RandomNumberGenerator instance = DefaultRandomGenerator.instance;
               double d8 = Math.max(0.0, d6);
               d5 = instance.nextDouble(0.0, d8);
            } else if (d3 == 0.0) {
               double d10 = Math.min(this.outlierValueRange.getMaxValue(flag), this.rangeSetting.getMinValue(flag)) - this.outlierValueRange.getMinValue(flag);
               RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
               double d12 = Math.max(0.0, d10);
               d5 = instance2.nextDouble(0.0, d12);
            } else {
               double d14 = 0.0;
               d5 = DefaultRandomGenerator.instance.nextDouble(d14, d4);
            }

            if (d5 < d2) {
               d = this.outlierValueRange.getMinValue(flag) + d5;
            } else {
               d = this.outlierValueRange.getMaxValue(flag) - d5;
            }
         }
      }

      this.lastDoubleValue = d;
      return d;
   }

   public double nextSecureDouble() {
      RangeSetting rangeSetting2 = this.rangeSetting;
      double d = this.sampleDoubleAvoidingRepeat(this.lastDoubleValue, rangeSetting2, true, false);
      if (this.shouldTriggerOutlier()) {
         double d2 = Math.max(0.0, this.rangeSetting.getMinValue() - this.outlierValueRange.getMinValue());
         double maxValue = this.outlierValueRange.getMaxValue();
         double d3 = Math.max(0.0, maxValue - this.rangeSetting.getMaxValue());
         double d4 = d2 + d3;
         if (d4 > 0.0) {
            double d5;
            if (d2 == 0.0) {
               double maxValue2 = this.outlierValueRange.getMaxValue();
               maxValue = this.outlierValueRange.getMinValue();
               double d6 = maxValue2 - Math.max(maxValue, this.rangeSetting.getMaxValue());
               RandomNumberGenerator instance = DefaultRandomGenerator.instance;
               double d7 = Math.max(0.0, d6);
               d5 = instance.nextSecureDouble(0.0, d7);
            } else if (d3 == 0.0) {
               double d9 = Math.min(this.outlierValueRange.getMaxValue(), this.rangeSetting.getMinValue()) - this.outlierValueRange.getMinValue();
               RandomNumberGenerator instance2 = DefaultRandomGenerator.instance;
               double d10 = Math.max(0.0, d9);
               d5 = instance2.nextSecureDouble(0.0, d10);
            } else {
               double d12 = 0.0;
               d5 = DefaultRandomGenerator.instance.nextSecureDouble(d12, d4);
            }

            if (d5 < d2) {
               d = this.outlierValueRange.getMinValue() + d5;
            } else {
               d = this.outlierValueRange.getMaxValue() - d5;
            }
         }
      }

      this.lastDoubleValue = d;
      return d;
   }

   private boolean isBetaSettingVisible() {
      return this.normalDistributionEnabled.getValue();
   }

   public RandomizedRangeSettings withoutNotInARow() {
      if (this.parentGroup != null) {
         this.parentGroup.getChildren().remove(this.notInARow);
      }

      if (this.parentModule != null) {
         ClientManagers.settingManager.getSettings().remove(this.notInARow);
         this.parentModule.getSettings().remove(this.notInARow);
      }

      this.notInARow.withVisibility(() -> false);
      this.notInARowDisabled = true;
      return this;
   }

   public RandomizedRangeSettings(Module module, String str, double d, double d2, double d3, double d4, int n) {
       super();
      this.outliersEnabled = null;
      this.outlierValueRange = null;
      this.outlierIntervalRange = null;
      this.normalDistributionEnabled = null;
      this.sigmaSetting = null;
      this.lastIntValue = 0;
      this.lastDoubleValue = 0.0;
      this.samplesSinceOutlier = 0;
      this.notInARowDisabled = false;
      this.random = new Random();
      this.consecutiveSameCount = 0;
      RangeSetting rangeSetting3 = new RangeSetting(module, str, d, d2, d3, d4, n).onChange(() -> this.nextValue());
      this.rangeSetting = (RangeSetting)this.registerNamedSetting("", rangeSetting3);
      this.notInARow = (BooleanValue)this.registerSetting(new BooleanValue(module, str + "NotInARow", false).withDisplayName("NotInARow"));
      this.decimalPlaces = n;
      this.rangeSetting.withValueUpdatedCallback(() -> this.nextValue());
      this.parentGroup = null;
      this.parentModule = module;
      this.baseName = str;
      this.nextValue();
   }

   private int sampleIntAvoidingRepeat(int n, RangeSetting rangeSetting2, boolean flag, boolean flag2) {
      int n3 = this.sampleRawInt(rangeSetting2, flag, flag2);
      boolean flag3 = !this.notInARowDisabled && this.notInARow.getValue() && (int)rangeSetting2.getMinValue(flag2) != (int)rangeSetting2.getMaxValue(flag2) && n3 == n;
      if (flag3 && this.maxInARowSetting != null) {
         this.consecutiveSameCount++;
         flag3 = false;
         if ((double)this.consecutiveSameCount >= this.maxInARowSetting.getValue()) {
            flag3 = true;
            this.consecutiveSameCount = 0;
         }
      } else {
         this.consecutiveSameCount = 0;
      }

      for (int i = 0; i < 30 && flag3 && n3 == n; i++) {
         n3 = this.sampleRawInt(rangeSetting2, flag, flag2);
      }

      return n3;
   }

   public RandomizedRangeSettings(Module module, String str, double d, double d2, double d3, double d4, int n, SettingGroup doubleValue) {
       super();
      this.outliersEnabled = null;
      this.outlierValueRange = null;
      this.outlierIntervalRange = null;
      this.normalDistributionEnabled = null;
      this.sigmaSetting = null;
      this.lastIntValue = 0;
      this.lastDoubleValue = 0.0;
      this.samplesSinceOutlier = 0;
      this.notInARowDisabled = false;
      this.random = new Random();
      this.consecutiveSameCount = 0;
      RangeSetting rangeSetting3 = new RangeSetting(module, str, d, d2, d3, d4, n, doubleValue).onChange(() -> this.nextValue());
      this.rangeSetting = (RangeSetting)this.registerNamedSetting("", rangeSetting3);
      this.notInARow = (BooleanValue)this.registerSetting(new BooleanValue(module, str + "NotInARow", false, doubleValue).withDisplayName("NotInARow"));
      this.decimalPlaces = n;
      this.rangeSetting.withValueUpdatedCallback(() -> this.nextValue());
      this.parentGroup = doubleValue;
      this.parentModule = module;
      this.baseName = str;
      this.nextValue();
   }

   private boolean shouldTriggerOutlier() {
      boolean flag = this.outliersEnabled != null && this.outliersEnabled.getValue() && this.samplesSinceOutlier > this.nextOutlierThreshold;
      if (flag) {
         this.resetOutlierCountdown();
      }

      this.samplesSinceOutlier++;
      return flag;
   }

   public RandomizedRangeSettings withMaxInARow() {
      DoubleValue doubleSetting = new DoubleValue(this.parentModule, this.baseName + "MaxInARow", 1.0, 0.0, 10.0, 0, this.parentGroup);
      doubleSetting = doubleSetting.named("MaxInARow");
      this.maxInARowSetting = (DoubleValue)this.registerSetting(doubleSetting.visibleWhen(() -> this.isMaxInARowVisible()));
      return this;
   }

   public void randomize() {
      this.rangeSetting.randomize();
      if (this.sigmaSetting != null) {
         this.sigmaSetting.randomize();
         this.centerSetting.randomize();
         if (this.betaSetting != null) {
            this.betaSetting.randomize();
         }
      }
   }

   public RandomizedRangeSettings withOutliers(double d, double d2, double d3, double d4, double d5, double d6) {
      return this.withOutliers(d, d2, d3, d4, d5, d6, true);
   }

   private boolean isMaxInARowVisible() {
      return this.notInARow.getValue() && this.notInARow.isVisible();
   }

   private void resetOutlierCountdown() {
      this.samplesSinceOutlier = 0;
      this.nextOutlierThreshold = DefaultRandomGenerator.instance.nextInt(this.outlierIntervalRange.getMinValue(), this.outlierIntervalRange.getMaxValue());
   }

   public double getLastValue() {
      return this.decimalPlaces == 0 ? (double)this.lastIntValue : this.lastDoubleValue;
   }

   public double getMinValue() {
      return this.rangeSetting.getMinValue();
   }

   public double nextValue(boolean flag) {
      return this.decimalPlaces == 0 ? (double)this.nextInt(flag) : this.nextDouble(flag);
   }

   public double getMaxValue() {
      return this.rangeSetting.getMaxValue();
   }

   public RandomizedRangeSettings withRangeNames(String str, String str2) {
      this.rangeSetting = this.rangeSetting.withBoundNames(str, str2);
      return this;
   }

   public double nextSecureValue() {
      return this.decimalPlaces == 0 ? (double)this.nextSecureInt() : this.nextSecureDouble();
   }

   public RandomizedRangeSettings withOutliers(double d, double d2, double d3, double d4, double d5, double d6, boolean flag) {
      this.outliersEnabled = (BooleanValue)this.registerSetting(new BooleanValue(this.parentModule, this.baseName + "Outliers", flag, this.parentGroup).withDisplayName("Outliers"));
      RangeSetting rangeSetting3 = new RangeSetting(this.parentModule, this.baseName + "OutlierValue", d, d2, d3, d4, this.decimalPlaces, this.parentGroup).named("OutlierValue");
      SettingVisibilityCondition condition = () -> {
         return this.outliersEnabled.getValue();
      };
      this.outlierValueRange = (RangeSetting)this.registerSetting(rangeSetting3.visibleWhen(condition));
      RangeSetting rangeSetting5 = new RangeSetting(this.parentModule, this.baseName + "OutlierDelay", d5, d6, 0.0, 100.0, 0, this.parentGroup);
      String str3 = "MaxOutlierInterval";
      rangeSetting5 = rangeSetting5.withBoundNames("MinOutlierInterval", str3);
      SettingVisibilityCondition condition2 = () -> {
         return this.outliersEnabled.getValue();
      };
      this.outlierIntervalRange = (RangeSetting)this.registerSetting(rangeSetting5.visibleWhen(condition2));
      this.outlierValueRange.withValueUpdatedCallback(() -> this.resetOutlierCountdown());
      this.outlierIntervalRange.withValueUpdatedCallback(() -> this.resetOutlierCountdown());
      this.resetOutlierCountdown();
      return this;
   }

   public double getMidpoint() {
      return this.getMinValue() + (this.getMaxValue() - this.getMinValue()) / 2.0;
   }

   public RandomizedRangeSettings withNormalDistribution(double d, double d2, double d3, boolean flag) {
      return this.withNormalDistribution(d, d2, d3, 2.0, flag, false);
   }

   public int nextInt() {
      return this.nextInt(false);
   }

   public double nextValue() {
      double d;
      if (this.decimalPlaces == 0) {
         d = (double)this.nextInt();
      } else {
         d = this.nextDouble();
      }

      return d;
   }

   public RandomizedRangeSettings withNormalDistribution(double d, double d2, double d3, double d4, boolean flag, boolean flag2) {
      this.normalDistributionEnabled = (BooleanValue)this.registerSetting(new BooleanValue(this.parentModule, this.baseName + "NormalDistribution", flag, this.parentGroup).withDisplayName("Normal Distribution"));
      DoubleValue doubleSetting = new DoubleValue(this.parentModule, this.baseName + "Sigma", d, d2, d3, 2, this.parentGroup);
      doubleSetting = doubleSetting.named("Sigma");
      SettingVisibilityCondition condition = () -> {
         return this.normalDistributionEnabled.getValue();
      };
      this.sigmaSetting = (DoubleValue)this.registerSetting(doubleSetting.visibleWhen(condition));
      this.centerSetting = (DoubleValue)this.registerSetting(new DoubleValue(this.parentModule, this.baseName + "Center", 50.0, 0.0, 100.0, 0, this.parentGroup).named("Center").visibleWhen(() -> this.isCenterSettingVisible()));
      if (flag2) {
         this.betaSetting = (DoubleValue)this.registerSetting(new DoubleValue(this.parentModule, this.baseName + "Beta", d4, 0.1, 4.0, 2, this.parentGroup).named("Beta").visibleWhen(() -> this.isBetaSettingVisible()));
         this.generalizedNormalSampler = new GeneralizedNormalSampler();
      }

      return this;
   }

   private boolean isCenterSettingVisible() {
      return this.normalDistributionEnabled.getValue();
   }

   public double nextDouble() {
      return this.nextDouble(false);
   }

   public void setLastValue(double lastValue) {
      if (this.decimalPlaces == 0) {
         this.lastIntValue = (int)lastValue;
      } else {
         this.lastDoubleValue = lastValue;
      }
   }

   private double sampleDoubleAvoidingRepeat(double d, RangeSetting rangeSetting2, boolean flag, boolean flag2) {
      double d3 = this.sampleRawDouble(rangeSetting2, flag);
      boolean flag3 = !this.notInARowDisabled && this.notInARow.getValue() && rangeSetting2.getMinValue(flag2) != rangeSetting2.getMaxValue(flag2) && d3 == d;
      if (flag3 && this.maxInARowSetting != null) {
         this.consecutiveSameCount++;
         flag3 = false;
         if ((double)this.consecutiveSameCount >= this.maxInARowSetting.getValue()) {
            flag3 = true;
            this.consecutiveSameCount = 0;
         }
      } else {
         this.consecutiveSameCount = 0;
      }

      for (int i = 0; i < 30 && flag3 && d3 == d; i++) {
         d3 = this.sampleRawDouble(rangeSetting2, flag);
      }

      return d3;
   }

   private double sampleRawDouble(RangeSetting rangeSetting2, boolean flag) {
      if (this.normalDistributionEnabled != null && this.normalDistributionEnabled.getValue()) {
         return MathHelper.clamp_double(this.sampleFromDistribution(rangeSetting2), rangeSetting2.getMinValue(), rangeSetting2.getMaxValue());
      } else {
         double d2;
         if (flag) {
            d2 = DefaultRandomGenerator.instance.nextSecureDouble(rangeSetting2.getMinValue(), rangeSetting2.getMaxValue());
         } else {
            d2 = DefaultRandomGenerator.instance.nextDouble(rangeSetting2.getMinValue(), rangeSetting2.getMaxValue());
         }

         return d2;
      }
   }

   private int sampleRawInt(RangeSetting rangeSetting2, boolean flag, boolean flag2) {
      if (this.normalDistributionEnabled != null && this.normalDistributionEnabled.getValue()) {
         return MathHelper.clamp_int((int)Math.round(this.sampleFromDistribution(rangeSetting2)), (int)rangeSetting2.getMinValue(), (int)rangeSetting2.getMaxValue());
      } else {
         int n2;
         if (flag) {
            n2 = (int)DefaultRandomGenerator.instance.nextSecureIntAsDouble(rangeSetting2.getMinValue(flag2), rangeSetting2.getMaxValue(flag2));
         } else {
            n2 = DefaultRandomGenerator.instance.nextInt(rangeSetting2.getMinValue(flag2), rangeSetting2.getMaxValue(flag2));
         }

         return n2;
      }
   }

   public int nextInt(boolean flag) {
      RangeSetting rangeSetting2 = this.rangeSetting;
      int n = this.sampleIntAvoidingRepeat(this.lastIntValue, rangeSetting2, false, flag);
      if (this.shouldTriggerOutlier()) {
         int n2 = Math.max(0, (int)(this.rangeSetting.getMinValue(flag) - this.outlierValueRange.getMinValue(flag)));
         int n3 = Math.max(0, (int)(this.outlierValueRange.getMaxValue(flag) - this.rangeSetting.getMaxValue(flag)));
         int n4 = n2 + n3;
         if (n4 > 0) {
            int n5;
            if (n2 == 0) {
               int n6 = (int)((double)((int)this.outlierValueRange.getMaxValue(flag)) - Math.max(this.outlierValueRange.getMinValue(flag), this.rangeSetting.getMaxValue(flag)));
               n5 = DefaultRandomGenerator.instance.nextInt((byte)0, Math.max(n6, 0)) + 1;
            } else if (n3 == 0) {
               int n8 = (int)(Math.min(this.outlierValueRange.getMaxValue(flag), this.rangeSetting.getMinValue(flag)) - this.outlierValueRange.getMinValue(flag));
               n5 = DefaultRandomGenerator.instance.nextInt((byte)0, Math.max(n8, 0));
            } else {
               int n10 = n4 - 1;
               n5 = DefaultRandomGenerator.instance.nextInt((byte)0, n10);
            }

            if (n5 < n2) {
               n = (int)(this.outlierValueRange.getMinValue(flag) + (double)n5);
            } else {
               n = (int)(this.outlierValueRange.getMaxValue(flag) - (double)(n5 - 1));
            }
         }
      }

      this.lastIntValue = n;
      return n;
   }

   private int sampleIntAvoidingRepeat(int n, RangeSetting rangeSetting2, boolean flag) {
      return this.sampleIntAvoidingRepeat(n, rangeSetting2, flag, false);
   }

   public int getLastIntValue() {
      return this.lastIntValue;
   }

   public int nextSecureInt() {
      RangeSetting rangeSetting2 = this.rangeSetting;
      int n = this.sampleIntAvoidingRepeat(this.lastIntValue, rangeSetting2, true);
      if (this.shouldTriggerOutlier()) {
         int n2 = Math.max(0, (int)(this.rangeSetting.getMinValue() - this.outlierValueRange.getMinValue()));
         int n3 = Math.max(0, (int)(this.outlierValueRange.getMaxValue() - this.rangeSetting.getMaxValue()));
         int n4 = n2 + n3;
         if (n4 > 0) {
            int n5;
            if (n2 == 0) {
               int n6 = (int)(((double)((int)this.outlierValueRange.getMaxValue())) - Math.max(this.outlierValueRange.getMinValue(), this.rangeSetting.getMaxValue()));
               n5 = DefaultRandomGenerator.instance.nextSecureInt((byte)0, Math.max(n6, 0)) + 1;
            } else if (n3 == 0) {
               int n9 = (int)(Math.min(this.outlierValueRange.getMaxValue(), this.rangeSetting.getMinValue()) - this.outlierValueRange.getMinValue());
               n5 = DefaultRandomGenerator.instance.nextSecureInt((byte)0, Math.max(n9, 0));
            } else {
               int n13 = n4 - 1;
               n5 = DefaultRandomGenerator.instance.nextSecureInt((byte)0, n13);
            }

            if (n5 < n2) {
               n = (int)(this.outlierValueRange.getMinValue() + (double)n5);
            } else {
               n = (int)(this.outlierValueRange.getMaxValue() - (double)(n5 - 1));
            }
         }
      }

      this.lastIntValue = n;
      return n;
   }

   private double sampleFromDistribution(RangeSetting rangeSetting2) {
      if (this.betaSetting != null) {
         GeneralizedNormalSampler generalizedNormalSampler2 = this.generalizedNormalSampler;
         generalizedNormalSampler2.setStdDev(this.sigmaSetting.getValue());
         generalizedNormalSampler2 = this.generalizedNormalSampler;
         generalizedNormalSampler2.setBeta(this.betaSetting.getValue());

         for (int i = 0; i < 50; i++) {
            generalizedNormalSampler2 = this.generalizedNormalSampler;
            double d2 = generalizedNormalSampler2.sample(MathStatisticsUtil.lerp(rangeSetting2.getMinValue(), rangeSetting2.getMaxValue(), this.centerSetting.getValue() / 100.0));
            if (((double)((int)d2)) <= rangeSetting2.getMaxValue() && d2 >= rangeSetting2.getMinValue()) {
               return d2;
            }
         }

         return MathStatisticsUtil.lerp(rangeSetting2.getMinValue(), rangeSetting2.getMaxValue(), Math.random());
      } else {
         for (int j = 0; j < 50; j++) {
            double d4 = this.random.nextGaussian() * this.sigmaSetting.getValue();
            double d5 = d4 + MathStatisticsUtil.lerp(rangeSetting2.getMinValue(), rangeSetting2.getMaxValue(), this.centerSetting.getValue() / 100.0);
            d4 = (double)((int)d5);
            if (d4 <= rangeSetting2.getMaxValue() && d5 >= rangeSetting2.getMinValue()) {
               return d5;
            }
         }

         return MathStatisticsUtil.lerp(rangeSetting2.getMinValue(), rangeSetting2.getMaxValue(), Math.random());
      }
   }


}

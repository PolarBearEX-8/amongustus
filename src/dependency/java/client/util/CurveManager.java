package client.util;

import client.ClientGlobals;
import client.module.Module;
import client.network.CosmeticsPayload;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.SettingGroup;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import net.minecraft.util.MathHelper;

public class CurveManager {
   public final ModeSetting settingMode;
   public final NumberSetting settingFrequency;
   public final BooleanSetting settingRandomizeOnlyAtRotating;
   public final NumberSetting settingInterval;
   public double value2;
   public final RangeSetting settingPitchAcceleration;
   public final RangeSetting settingYawSpeed;
   public float factor;
   public int count;
   public final RangeSetting settingPitchSpeed;
   public double value4;
   public final Supplier supplier;
   public final BooleanSetting settingBetterAcceleration;
   public final RangeSetting settingYawAcceleration;
   public double value;
   public float factor2;
   public final NumberSetting settingAmplitude;
   public double value3;
   public final RangeSetting settingRandomStrength;

   private static Boolean getBoolean() {
      return false;
   }

   private static double computeValue(double posX, double posX2, double posY, double posZ) {
      return Math.sqrt(Math.pow(posY - posX, 2.0) + Math.pow(posZ - posX2, 2.0));
   }

   public Rotation getRotation(Rotation rotation, Rotation rotation2) {
      float pitch = rotation2.pitch;
      float yaw = rotation2.yaw;
      float pitch2 = rotation.pitch;
      float yaw2 = rotation.yaw;
      return this.resolveRotation(yaw2, pitch2, yaw, pitch);
   }

   private boolean isActive2() {
      return this.settingMode.getSelected().equals("Curve");
   }

   private static double[] resolveDoubleArray(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      double offset = posX3 - posX;
      double offset2 = posX4 - posX2;
      double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
      double offset4 = Math.min(posX5, offset3);
      double offset5 = offset4 / offset3;
      double offset6 = posX + offset5 * offset;
      double offset7 = posX2 + offset5 * offset2;
      double offset8 = -offset2 / offset3;
      double offset9 = offset / offset3;
      double offset10 = posY * Math.sin(posZ * offset5 * Math.PI);
      double offset11 = offset6 + offset10 * offset8;
      double offset12 = offset7 + offset10 * offset9;
      return new double[]{offset11, offset12};
   }

   private boolean isActiveAsFlag() {
      return !this.settingMode.getSelected().equals("Curve");
   }

   private Rotation resolveRotation(float posX, float posX2, float posY, float posZ, boolean flag) {
      Rotation rotation = ClientGlobals.rotationManager.getRotation();
      Rotation rotation2 = ClientGlobals.rotationManager.getRotation3().getRotation(rotation);
      RangeSetting settingYawAcceleration2 = this.settingYawAcceleration;
      float yaw = rotation2.yaw;
      float factor3 = this.factor;
      float factor4 = (float)this.settingYawSpeed.recalculate();
      boolean enabled = this.settingBetterAcceleration.isEnabled();
      float factor5 = factor4;
      float factor6 = factor3;
      float factor7 = yaw;
      RangeSetting setting = settingYawAcceleration2;
      float factor8 = this.computeFactorByRangeSetting(setting, posX, posY, factor7, factor6, factor5, enabled, flag);
      settingYawAcceleration2 = this.settingPitchAcceleration;
      yaw = rotation2.pitch;
      factor3 = this.factor2;
      factor4 = (float)this.settingPitchSpeed.recalculate();
      boolean enabled2 = this.settingBetterAcceleration.isEnabled();
      float factor9 = factor4;
      float factor10 = factor3;
      float factor11 = yaw;
      RangeSetting setting2 = settingYawAcceleration2;
      float factor12 = this.computeFactorByRangeSetting(setting2, posX2, posZ, factor11, factor10, factor9, enabled2, flag);
      return new Rotation(factor8, factor12);
   }

   private static double[] resolveDoubleArray(double posX, double posX2, double posX3, double posY, double posZ) {
      double offset = posX3 - posX;
      double offset2 = posY - posX2;
      double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
      double offset4 = Math.min(posZ, offset3);
      double offset5 = offset4 / offset3;
      double offset6 = posX + offset5 * offset;
      double offset7 = posX2 + offset5 * offset2;
      return new double[]{offset6, offset7};
   }

   private boolean isActive() {
      return !this.settingMode.getSelected().equals("Curve");
   }

   private static double computeValue(double posX, double posX2, double posX3, double posY, double posZ) {
      double offset = (posY - posX2) / (posX3 - posX);
      return (posZ - posX2) / offset + posX;
   }

   public Rotation resolveRotation(float posX, float posX2, float posY, float posZ) {
      float floatItem = SimulatedPlayer.getFloat9(posY, posX, 180.0F);
      float floatItem2 = SimulatedPlayer.getFloat9(posZ, posX2, 180.0F);
      this.applyFloat(floatItem, floatItem2, false);
      boolean flag = CosmeticsPayload.checkCondition(1);
      Rotation rotation = this.resolveRotation(floatItem, floatItem2, posY, posZ, flag);
      Rotation rotation2 = null;
      String selected = this.settingMode.getSelected();
      switch (selected) {
         case "Independent":
            float pitch = rotation.pitch;
            float yaw = rotation.yaw;
            Rotation rotation3 = this.resolveRotationByFloat(floatItem, floatItem2, posY, posZ, yaw, pitch);
            rotation2 = this.resolveRotation(rotation3, floatItem, floatItem2);
            break;
         case "Linear":
            float pitch2 = rotation.pitch;
            float yaw2 = rotation.yaw;
            Rotation rotation4 = this.resolveRotation(floatItem, floatItem2, posY, posZ, yaw2, pitch2);
            rotation2 = this.resolveRotation(rotation4, floatItem, floatItem2);
            break;
         case "Curve":
            Rotation rotation5;
            if (flag) {
               float pitch3 = rotation.pitch;
               float yaw3 = rotation.yaw;
               rotation5 = this.resolveRotation(floatItem, floatItem2, posY, posZ, yaw3, pitch3);
            } else {
               float yaw4 = rotation.yaw;
               float pitch4 = rotation.pitch;
               int doubleItem = (int)this.settingInterval.getDouble2(true);
               float doubleItem2 = (float)this.settingAmplitude.getDouble2(true);
               float doubleItem3 = (float)this.settingFrequency.getDouble2(true);
               float factor3 = doubleItem2;
               int count4 = doubleItem;
               float factor4 = pitch4;
               float factor5 = yaw4;
               rotation5 = this.resolveRotation(floatItem, floatItem2, posY, posZ, factor5, factor4, count4, factor3, doubleItem3);
            }

            rotation2 = rotation5;
      }

      this.applyFloat(floatItem, floatItem2, true);
      return rotation2 == null ? null : SimulatedPlayer.getRotation8(rotation2.yaw, rotation2.pitch, posY, posZ);
   }

   private boolean isActive3() {
      return this.settingMode.getSelected().equals("Curve");
   }

   private static double computeValueByDouble(double posX, double posX2, double posX3, double posY, double posZ) {
      return (posY - posX2) / (posX3 - posX) * (posZ - posX) + posX2;
   }

   private Rotation resolveRotation(Rotation rotation, float factor3, float factor4) {
      Rotation rotation2 = new Rotation(rotation);
      if ((double)Math.abs(rotation2.yaw - factor3) > 0.16 || (double)Math.abs(rotation2.pitch - factor4) > 0.16 || !this.settingRandomizeOnlyAtRotating.isEnabled()) {
         rotation2.yaw = rotation2.yaw + (float)this.settingRandomStrength.computeValue(true) * (float)(ThreadLocalRandom.current().nextBoolean() ? 1 : -1);
         rotation2.pitch = rotation2.pitch + (float)this.settingRandomStrength.computeValue(true) * (float)(ThreadLocalRandom.current().nextBoolean() ? 1 : -1);
      }

      return rotation2;
   }

   public Rotation resolveRotationByFloat(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float floatItem = SimulatedPlayer.getFloat9(posX3, posX, posY);
      float floatItem2 = SimulatedPlayer.getFloat9(posX4, posX2, posZ);
      return new Rotation(floatItem, floatItem2);
   }

   private static double computeValue(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor3 = posX - posX3;
      float factor4 = posX2 - posX4;
      float factor5 = Math.min(Math.abs(factor3), posY);
      float factor6 = Math.min(Math.abs(factor4), posZ);
      float factor7 = posX4 + factor6 * (float)(factor4 > 0.0F ? 1 : -1);
      float factor8 = posX3 + factor5 * (float)(factor3 > 0.0F ? 1 : -1);
      double offset = computeValue((double)posX3, (double)posX4, (double)posX, (double)posX2, (double)factor7);
      double offset2 = computeValueByDouble((double)posX3, (double)posX4, (double)posX, (double)posX2, (double)factor8);
      double offset3 = computeValue((double)posX3, (double)posX4, offset, (double)factor7);
      double offset4 = computeValue((double)posX3, (double)posX4, (double)factor8, offset2);
      return Math.min(offset3, offset4);
   }

   public Rotation getRotation6(float factor3, float factor4, Rotation rotation) {
      float pitch = rotation.pitch;
      float yaw = rotation.yaw;
      return this.resolveRotation(factor3, factor4, yaw, pitch);
   }

   public static boolean checkDouble(double offset, double offset2) {
      return offset > 0.0 && offset2 > 0.0 || offset < 0.0 && offset2 < 0.0;
   }

   public Rotation resolveRotation(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float floatItem = SimulatedPlayer.getFloat9(posX3, posX, 180.0F);
      float floatItem2 = SimulatedPlayer.getFloat9(posX4, posX2, 180.0F);
      double offset = computeValue(floatItem, floatItem2, posX3, posX4, posY, posZ);
      double[] offsetArray = resolveDoubleArray((double)posX3, (double)posX4, (double)floatItem, (double)floatItem2, offset);
      return new Rotation((float)offsetArray[0], (float)offsetArray[1]);
   }

   private boolean isActive4() {
      return this.settingMode.getSelected().equals("Curve");
   }

   private void applyFloat(float factor3, float factor4, boolean flag) {
      if (flag || (Boolean)this.supplier.get() || !Float.isFinite(this.factor)) {
         this.factor = factor3;
         this.factor2 = factor4;
      }
   }

   public Rotation resolveRotation(float posX, float posX2, float posX3, float posX4, float posY, float posZ, int count2, float factor3, float factor4) {
      float floatItem = SimulatedPlayer.getFloat9(posX3, posX, 180.0F);
      float floatItem2 = SimulatedPlayer.getFloat9(posX4, posX2, 180.0F);
      double offset = computeValue(floatItem, floatItem2, posX3, posX4, posY, posZ);
      double offset2 = (double)floatItem - this.value3;
      double offset3 = (double)floatItem2 - this.value;
      if (this.count <= 0 || !checkDouble(offset2, this.value2) || !checkDouble(offset3, this.value4)) {
         this.count = count2;
         this.value3 = (double)posX3;
         this.value = (double)posX4;
         this.value2 = (double)(floatItem - posX3);
         this.value4 = (double)(floatItem2 - posX4);
      }

      this.count--;
      double offset4 = computeValue(this.value3, this.value, (double)posX3, (double)posX4);
      double[] offsetArray = resolveDoubleArray(this.value3, this.value, (double)floatItem, (double)floatItem2, offset + offset4, (double)factor3, (double)factor4);
      return new Rotation((float)offsetArray[0], (float)offsetArray[1]);
   }

   public float computeFactorByRangeSetting(RangeSetting setting, float posX, float posX2, float posX3, float posY, float posZ, boolean flag, boolean flag2) {
      float factor3 = (float)setting.getValue2() + (float)(flag2 ? 15 : 0);
      double offset = setting.getDouble9() + (double)(flag2 ? 15 : 0) + (setting.getDouble2() - setting.getDouble9()) / 2.0;
      float factor4 = posX - posX2;
      double offset2 = flag ? (double)Math.abs(posY - posX) : 0.0;
      double offset3 = (double)Math.abs(factor4 / Math.max(posX3, 1.0F));
      double offset4 = Math.abs(((double)posX3 - offset2) / offset);
      return MathHelper.clamp_float(Math.abs(posX3) + (offset3 > offset4 ? factor3 : -factor3), 0.0F, posZ);
   }

   public CurveManager(Module module, SettingGroup settingGroup, Supplier supplier2) {
        super();
      
      this.factor = Float.NaN;
      this.factor2 = 0.0F;
      this.count = 0;
      ModeSetting setting = new ModeSetting(module, "RotationEngineMode", "Independent", new String[]{"Independent", "Linear", "Curve"}, settingGroup);
      String text = "Mode";
      this.settingMode = setting.getModeSetting(text);
      RangeSetting setting2 = new RangeSetting(module, "RotationEngineYawSpeed", 15.0, 20.0, 0.0, 180.0, 1, settingGroup);
      String text2 = "YawSpeed";
      this.settingYawSpeed = setting2.getRangeSetting(text2).getRangeSetting2();
      RangeSetting setting3 = new RangeSetting(module, "RotationEnginePitchSpeed", 15.0, 20.0, 0.0, 180.0, 1, settingGroup);
      String text3 = "PitchSpeed";
      this.settingPitchSpeed = setting3.getRangeSetting(text3).getRangeSetting2();
      RangeSetting setting4 = new RangeSetting(module, "RotationEngineYawAcceleration", 5.0, 10.0, 0.0, 180.0, 1, settingGroup);
      String text4 = "YawAcceleration";
      this.settingYawAcceleration = setting4.getRangeSetting(text4).getRangeSetting2();
      RangeSetting setting5 = new RangeSetting(module, "RotationEnginePitchAcceleration", 5.0, 10.0, 0.0, 180.0, 1, settingGroup);
      String text5 = "PitchAcceleration";
      this.settingPitchAcceleration = setting5.getRangeSetting(text5).getRangeSetting2();
      RangeSetting rangeSetting = (new RangeSetting(module, "RotationEngineRandomStrength", 0.0, 0.5, 0.0, 4.0, 2, settingGroup)).getRangeSetting2();
      String text6 = "RandomStrength";
      RangeSetting rangeSetting2 = rangeSetting.getRangeSetting(text6);
      BooleanSupplier booleanSupplier = this::isActiveAsFlag;
      this.settingRandomStrength = rangeSetting2.getRangeSetting(15343, booleanSupplier);
      BooleanSetting setting6 = new BooleanSetting(module, "RotationEngineRandomizeOnlyWhileRotating", true, settingGroup);
      String text7 = "RandomizeOnlyAtRotating";
      BooleanSetting setting7 = (BooleanSetting)setting6.getSetting(text7);
      BooleanSupplier booleanSupplier2 = this::isActive;
      this.settingRandomizeOnlyAtRotating = (BooleanSetting)setting7.getSetting(booleanSupplier2);
      NumberSetting setting8 = new NumberSetting(module, "RotationEngineInterval", 7.0, 1.0, 20.0, 0, settingGroup);
      String text8 = "Interval";
      NumberSetting numberSetting = setting8.getNumberSetting(text8);
      BooleanSupplier booleanSupplier3 = this::isActive3;
      this.settingInterval = numberSetting.getNumberSetting(booleanSupplier3);
      NumberSetting setting9 = new NumberSetting(module, "RotationEngineAmplitude", 1.0, 0.1, 5.0, 2, settingGroup);
      String text9 = "Amplitude";
      NumberSetting numberSetting2 = setting9.getNumberSetting(text9);
      BooleanSupplier booleanSupplier4 = this::isActive4;
      this.settingAmplitude = numberSetting2.getNumberSetting(booleanSupplier4);
      NumberSetting setting10 = new NumberSetting(module, "RotationEngineFrequency", 1.0, 1.0, 8.0, 0, settingGroup);
      String text10 = "Frequency";
      NumberSetting numberSetting3 = setting10.getNumberSetting(text10);
      BooleanSupplier booleanSupplier5 = this::isActive2;
      this.settingFrequency = numberSetting3.getNumberSetting(booleanSupplier5);
      BooleanSetting setting11 = new BooleanSetting(module, "RotationEngineBetterAcceleration", false, settingGroup);
      String text11 = "BetterAcceleration";
      this.settingBetterAcceleration = (BooleanSetting)setting11.getSetting(text11);
      this.supplier = supplier2;
   }

   public CurveManager(Module module, SettingGroup settingGroup) {
      this(module, settingGroup, CurveManager::getBoolean);
   }
}

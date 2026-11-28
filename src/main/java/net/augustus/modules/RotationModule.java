package net.augustus.modules;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.events.EventMove;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.settings.RangeSetting;
import net.augustus.utils.rotation.MoveFixMode;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.Augustus;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public abstract class RotationModule extends Module implements MoveListener, MoveInputListener {
   public final RangeSetting yawSpeedInRotationModule;
   public final RangeSetting pitchSpeedInRotationModule;
   public final StringValue randomTypeInRotationModule;
   public final StringValue randomizeInRotationModule;
   public final DoubleValue randomStrengthInRotationModule;
   public final BooleanValue advancedRotsInRotationModule;
   public final BooleanValue interpolationInRotationModule;
   public final BooleanValue moveFixInRotationModule;
   public final StringValue silentMoveFix;
   public final BooleanValue silentMoveFixIntaveInRotationModule;
   public boolean rotating;

   public RotationModule(String str, String str2, boolean flag, Color color2, Categorys categorys2) {
       super(str, str2, flag, color2, categorys2);
      RangeSetting rangeSetting = new RangeSetting(this, "YawSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String str3 = "MaxYawSpeed";
      this.yawSpeedInRotationModule = rangeSetting.withBoundNames("MinYawSpeed", str3);
      RangeSetting rangeSetting2 = new RangeSetting(this, "PitchSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String str5 = "MaxPitchSpeed";
      this.pitchSpeedInRotationModule = rangeSetting2.withBoundNames("MinPitchSpeed", str5);
      this.randomTypeInRotationModule = (StringValue)new StringValue(
         this, "RandomTypeInRotationModule", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"}
      ).withDisplayName("RandomType");
      this.randomizeInRotationModule = (StringValue)new StringValue(
         this, "RandomizeInRotationModule", "OnlyRotation", new String[]{"None", "Basic", "Doubled", "OnlyRotation"}
      ).withDisplayName("Randomize");
      this.randomStrengthInRotationModule = new DoubleValue(this, "RandomStrengthInRotationModule", 1.25, 0.01, 5.0, 2).named("RandomStrength");
      this.advancedRotsInRotationModule = (BooleanValue)new BooleanValue(this, "AdvancedRotsInRotationModule", true).withDisplayName("AdvancedRots");
      this.interpolationInRotationModule = (BooleanValue)new BooleanValue(this, "InterpolationInRotationModule", false).withDisplayName("Interpolation");
      this.moveFixInRotationModule = (BooleanValue)new BooleanValue(this, "MoveFixInRotationModule", true).withDisplayName("MoveFix");
      this.silentMoveFix = (StringValue)new StringValue(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"}).withDisplayName("SilentMoveFix");
      this.silentMoveFixIntaveInRotationModule = (BooleanValue)new BooleanValue(this, "SilentMoveFixIntaveInRotationModule", true).withDisplayName("SilentMoveFixIntave");
      this.rotating = false;
   }

   public RotationModule(String str, String str2, Color color2, Categorys categorys2) {
       super(str, str2, color2, categorys2);
      RangeSetting rangeSetting = new RangeSetting(this, "YawSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String str3 = "MaxYawSpeed";
      this.yawSpeedInRotationModule = rangeSetting.withBoundNames("MinYawSpeed", str3);
      RangeSetting rangeSetting2 = new RangeSetting(this, "PitchSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String str5 = "MaxPitchSpeed";
      this.pitchSpeedInRotationModule = rangeSetting2.withBoundNames("MinPitchSpeed", str5);
      this.randomTypeInRotationModule = (StringValue)new StringValue(
         this, "RandomTypeInRotationModule", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"}
      ).withDisplayName("RandomType");
      this.randomizeInRotationModule = (StringValue)new StringValue(
         this, "RandomizeInRotationModule", "OnlyRotation", new String[]{"None", "Basic", "Doubled", "OnlyRotation"}
      ).withDisplayName("Randomize");
      this.randomStrengthInRotationModule = new DoubleValue(this, "RandomStrengthInRotationModule", 1.25, 0.01, 5.0, 2).named("RandomStrength");
      this.advancedRotsInRotationModule = (BooleanValue)new BooleanValue(this, "AdvancedRotsInRotationModule", true).withDisplayName("AdvancedRots");
      this.interpolationInRotationModule = (BooleanValue)new BooleanValue(this, "InterpolationInRotationModule", false).withDisplayName("Interpolation");
      this.moveFixInRotationModule = (BooleanValue)new BooleanValue(this, "MoveFixInRotationModule", true).withDisplayName("MoveFix");
      this.silentMoveFix = (StringValue)new StringValue(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"}).withDisplayName("SilentMoveFix");
      this.silentMoveFixIntaveInRotationModule = (BooleanValue)new BooleanValue(this, "SilentMoveFixIntaveInRotationModule", true).withDisplayName("SilentMoveFixIntave");
      this.rotating = false;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(28);
      this.toggleEventSubscription(21);
   }

   public Rotation applyRotation(float[] floats) {
      return this.applyRotation(floats[0], floats[1]);
   }

   public Rotation applyRotation(Rotation rotation) {
      float pitch = rotation.pitch;
      return this.applyRotation(rotation.yaw, pitch);
   }

   public Rotation calculateRotationToVec(Vec3 vec) {
      double zCoord = vec.zCoord;
      double yCoord = vec.yCoord;
      return this.calculateRotationToPos(vec.xCoord, yCoord, zCoord);
   }

   public Rotation calculateRotationToPos(double d, double d2, double d3) {
      double d4 = d - mc.thePlayer.posX;
      double d5 = d2 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d6 = d3 - mc.thePlayer.posZ;
      return this.applyRotation((float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0), (float)(-(MathHelper.atan2(d5, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI)));
   }

   public Rotation calculateRotationToPosWithSpeed(double d, double d2, double d3, float f, float f2) {
      double d4 = d - mc.thePlayer.posX;
      double d5 = d2 - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
      double d6 = d3 - mc.thePlayer.posZ;
      return this.computeSmoothedRotation((float)(MathHelper.atan2(d6, d4) * 180.0 / Math.PI - 90.0), (float)(-(MathHelper.atan2(d5, (double)MathHelper.sqrt_double(d4 * d4 + d6 * d6)) * 180.0 / Math.PI)), ClientManagers.rotationController.getPreviousRotation(), f, f2);
   }

   public Rotation calculateInterpolatedRotationFromBase(double d, double d2, double d3, Rotation rotation, float f) {
      double d4 = mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * (double)f;
      double d5 = mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * (double)f;
      double d6 = mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * (double)f;
      double d7 = d - d4;
      double d8 = d2 - (d5 + (double)mc.thePlayer.getEyeHeight());
      double d9 = d3 - d6;
      return this.applyRotationWithRandomSpeed((float)(MathHelper.atan2(d9, d7) * 180.0 / Math.PI - 90.0), (float)(-(MathHelper.atan2(d8, (double)MathHelper.sqrt_double(d7 * d7 + d9 * d9)) * 180.0 / Math.PI)), rotation);
   }

   public Rotation calculateSmoothInterpolatedRotation(double d, double d2, double d3, Rotation rotation, float f, float f2, float f3) {
      double d4 = mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * (double)f3;
      double d5 = mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * (double)f3;
      double d6 = mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * (double)f3;
      double d7 = d - d4;
      double d8 = d2 - (d5 + (double)mc.thePlayer.getEyeHeight());
      double d9 = d3 - d6;
      return this.computeSmoothedRotation((float)(MathHelper.atan2(d9, d7) * 180.0 / Math.PI - 90.0), (float)(-(MathHelper.atan2(d8, (double)MathHelper.sqrt_double(d7 * d7 + d9 * d9)) * 180.0 / Math.PI)), rotation, f, f2);
   }

   public Rotation calculateInterpolatedRotationToPos(double d, double d2, double d3, float f) {
      double d4 = mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * (double)f;
      double d5 = mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * (double)f;
      double d6 = mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * (double)f;
      double d7 = d - d4;
      double d8 = d2 - (d5 + (double)mc.thePlayer.getEyeHeight());
      double d9 = d3 - d6;
      return this.applyRotation((float)(MathHelper.atan2(d9, d7) * 180.0 / Math.PI - 90.0), (float)(-(MathHelper.atan2(d8, (double)MathHelper.sqrt_double(d7 * d7 + d9 * d9)) * 180.0 / Math.PI)));
   }

   public Rotation applyRotation(float f, float f2) {
      return this.applyRotationWithRandomSpeed(f, f2, ClientManagers.rotationController.getPreviousRotation());
   }

   public Rotation applyRotationWithRandomSpeed(float f, float f2, Rotation rotation) {
      RandomNumberGenerator instance = DefaultRandomGenerator.instance;
      double minValue = this.yawSpeedInRotationModule.getMinValue();
      float f3 = instance.nextSecureFloat(minValue, this.yawSpeedInRotationModule.getMaxValue());
      instance = DefaultRandomGenerator.instance;
      minValue = this.pitchSpeedInRotationModule.getMinValue();
      return this.computeSmoothedRotation(f, f2, rotation, f3, instance.nextSecureFloat(minValue, this.pitchSpeedInRotationModule.getMaxValue()));
   }

   public Rotation computeSmoothedRotation(float f, float f2, Rotation rotation, float f3, float f4) {
      float f5;
      float f6;
      if (this.interpolationInRotationModule.getValue()) {
         f5 = RotationUtil.interpolateAngleIfLarge(rotation.yaw, f, f3 / 180.0F);
         f6 = RotationUtil.interpolateAngleIfLarge(rotation.pitch, f2, f4 / 180.0F);
      } else {
         f5 = RotationUtil.updateAngle(rotation.yaw, f, f3);
         f6 = RotationUtil.updateAngle(rotation.pitch, f2, f4);
      }

      switch (this.randomizeInRotationModule.getSelectedValue()) {
         case "Basic":
            f5 += this.computeRandomOffset(f6);
            f6 += this.computeRandomOffset(f5);
            break;
         case "OnlyRotation":
            double d = (double)MathHelper.wrapAngleTo180_float(f - rotation.yaw);
            double d2 = (double)MathHelper.wrapAngleTo180_float(f2 - rotation.pitch);
            if (!((double)(-f3) <= d) || !(d <= (double)f3) || !((double)(-f4) <= d2) || !(d2 <= (double)f4)) {
               f5 += this.computeRandomOffset(f6);
               f6 += this.computeRandomOffset(f5);
            }
            break;
         case "Doubled":
            float f7 = this.computeRandomOffset(f6);
            float f8 = this.computeRandomOffset(f6);
            float f9 = this.computeRandomOffset(f6);
            float f10 = this.computeRandomOffset(f6);
            float f11 = this.computeRandomOffset(f5);
            float f12 = this.computeRandomOffset(f5);
            float f13 = this.computeRandomOffset(f5);
            float f14 = this.computeRandomOffset(f5);
            RandomNumberGenerator instance = DefaultRandomGenerator.instance;
            double d3 = (double)Math.max(f7, f8);
            double d4 = (double)Math.min(f7, f8);
            f5 += instance.nextSecureFloat(d4, d3);
            instance = DefaultRandomGenerator.instance;
            double d5 = (double)Math.max(f9, f10);
            double d6 = (double)Math.min(f9, f10);
            f5 += instance.nextSecureFloat(d6, d5);
            instance = DefaultRandomGenerator.instance;
            double d7 = (double)Math.max(f11, f12);
            double d8 = (double)Math.min(f11, f12);
            f6 += instance.nextSecureFloat(d8, d7);
            instance = DefaultRandomGenerator.instance;
            double d9 = (double)Math.max(f13, f14);
            double d10 = (double)Math.min(f13, f14);
            f6 += instance.nextSecureFloat(d10, d9);
      }

      if (this.advancedRotsInRotationModule.getValue()) {
         f6 = (float)((double)f6 + Math.sin(0.06981317007977318 * (double)(RotationUtil.updateAngle(rotation.yaw, f, 180.0F) - f5)) * 8.0);
      }

      return RotationUtil.applyGcdFix(f5, f6, rotation.yaw, rotation.pitch);
   }

   private float computeRandomOffset(float f) {
      double value = this.randomStrengthInRotationModule.getValue();
      switch (this.randomTypeInRotationModule.getSelectedValue()) {
         case "Random":
            double d = -value;
            return DefaultRandomGenerator.instance.nextFloat(d, value);
         case "Gaussian":
            return (float)(ThreadLocalRandom.current().nextGaussian() * value);
         case "SecureRandom":
            double d2 = -value;
            return DefaultRandomGenerator.instance.nextSecureFloat(d2, value);
         case "Intave":
            double d3 = 2.0;
            double d4 = 1.0;
            return (float)((double)((float)((double)DefaultRandomGenerator.instance.nextSecureFloat(d4, d3) * value)) * Math.sin((double)f * Math.PI));
         default:
            return 1.0F;
      }
   }

   @Override
   public void onMoveFlying(EventMove event) {
      if (this.rotating && !this.moveFixInRotationModule.getValue()) {
         event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      if (this.rotating && !this.moveFixInRotationModule.getValue()) {
         event.setYaw(Augustus.getInstance().getYawPitchHelper().realYaw);
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.rotating && this.moveFixInRotationModule.getValue() && !this.silentMoveFix.getSelectedValue().equals("None") && ClientManagers.rotationController.isOwnedBy(this)) {
         if (this.silentMoveFix.getSelectedValue().equals("Advanced")) {
            event.setMoveFixMode(MoveFixMode.ADVANCED);
         } else {
            event.setMoveFixMode(MoveFixMode.DEFAULT);
         }

         if (this.silentMoveFixIntaveInRotationModule.getValue()) {
            event.setPreventInstantReverse(true);
         }
      }
   }

   public void initVisibilityConditions() {
      StringValue silentMoveFix2 = this.silentMoveFix;
      SettingVisibilityCondition condition = () -> {
         return this.moveFixInRotationModule.getValue() && this.moveFixInRotationModule.isVisible();
      };
      silentMoveFix2.withVisibility(condition);
      this.silentMoveFixIntaveInRotationModule.withVisibility(() -> this.isSilentMoveFixIntaveVisible());
   }

   private boolean isSilentMoveFixIntaveVisible() {
      return this.silentMoveFix.isVisible() && !this.silentMoveFix.getSelectedValue().equals("None");
   }

}

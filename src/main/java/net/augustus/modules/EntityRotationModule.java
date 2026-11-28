package net.augustus.modules;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.settings.RandomizedRangeSettings;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public abstract class EntityRotationModule extends Module implements MoveListener, MoveInputListener {
   public final RangeSetting yawSpeedInRotationModule;
   public final RangeSetting pitchSpeedInRotationModule;
   public final StringValue rotationModeInEntityRotationModule;
   public final DoubleValue hitBoxPercentageVerticalInEntityRotationModule;
   public final DoubleValue hitBoxPercentageHorizontalInEntityRotationModule;
   public final DoubleValue hitBoxPercentageVerticalInEntityRotationModule1;
   public final DoubleValue hitBoxPercentageHorizontalInEntityRotationModule1;
   public final BooleanValue advancedRotsInRotationModule;
   public final BooleanValue interpolationInRotationModule;
   public final StringValue randomTypeInRotationModule;
   public final StringValue randomizeInRotationModule;
   public final BooleanValue reducedRotateInRotationModule;
   public final DoubleValue reduceRotationPercentageInRotationModule;
   public final BooleanValue smartRotateInRotationModule;
   public final BooleanValue pitchOutliersRotationModule;
   public final RandomizedRangeSettings pitchOutliersAddRotationModule;
   public final RandomizedRangeSettings pitchOutliersDelayRotationModule;
   public final RangeSetting innerRadiusInRotationModule;
   public final DoubleValue bestHitXMinDistInRotationModule;
   public final DoubleValue bestHitYMInDistInRotationModule;
   public final DoubleValue minAbsDistInRotationModule;
   public final DoubleValue maxXAbsDistInRotationModule;
   public final DoubleValue maxYAbsDistInRotationModule;
   public final RangeSetting xRelDistInRotationModule;
   public final RangeSetting yRelDistInRotationModule;
   public final DoubleValue maxRotationAngleInRotationModule;
   public final DoubleValue randomStrengthInRotationModule;
   public final BooleanValue moveFixInRotationModule;
   public final StringValue silentMoveFix;
   public final BooleanValue silentMoveFixIntaveInRotationModule;
   public boolean rotating;
   public boolean firstRotationTick;
   private final RotationUtil rotationUtil;

   public EntityRotationModule(String str, String str2, Color color2, Categorys categorys2) {
       super(str, str2, color2, categorys2);
      RangeSetting rangeSetting = new RangeSetting(this, "YawSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String str3 = "MaxYawSpeed";
      this.yawSpeedInRotationModule = rangeSetting.withBoundNames("MinYawSpeed", str3);
      RangeSetting rangeSetting2 = new RangeSetting(this, "PitchSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String str5 = "MaxPitchSpeed";
      this.pitchSpeedInRotationModule = rangeSetting2.withBoundNames("MinPitchSpeed", str5);
      this.rotationModeInEntityRotationModule = (StringValue)new StringValue(
         this, "RotationModeInEntityRotationModule", "BestHitVec", new String[]{"Head", "BestHitVec", "NearestHitVec"}
      ).withDisplayName("RotationMode");
      this.hitBoxPercentageVerticalInEntityRotationModule = new DoubleValue(this, "HitBoxPercentageVerticalInEntityRotationModule", 100.0, 1.0, 100.0, 0).named("HitBoxPercentageVertical");
      this.hitBoxPercentageHorizontalInEntityRotationModule = new DoubleValue(this, "HitBoxPercentageHorizontalInEntityRotationModule", 100.0, 1.0, 100.0, 0).named("HitBoxPercentageHorizontal");
      this.hitBoxPercentageVerticalInEntityRotationModule1 = new DoubleValue(this, "HitBoxPercentageVerticalInEntityRotationModule1", 100.0, 1.0, 100.0, 0).named("HitBoxPercentageVertical");
      this.hitBoxPercentageHorizontalInEntityRotationModule1 = new DoubleValue(this, "HitBoxPercentageHorizontalInEntityRotationModule1", 100.0, 1.0, 100.0, 0).named("HitBoxPercentageHorizontal");
      this.advancedRotsInRotationModule = (BooleanValue)new BooleanValue(this, "AdvancedRotsInRotationModule", true).withDisplayName("AdvancedRots");
      this.interpolationInRotationModule = (BooleanValue)new BooleanValue(this, "InterpolationInRotationModule", false).withDisplayName("Interpolation");
      this.randomTypeInRotationModule = (StringValue)new StringValue(
         this, "RandomTypeInRotationModule", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"}
      ).withDisplayName("RandomType");
      this.randomizeInRotationModule = (StringValue)new StringValue(
         this, "RandomizeInRotationModule", "OnlyRotation", new String[]{"None", "Basic", "Doubled", "OnlyRotation", "Advanced"}
      ).withDisplayName("Randomize");
      this.reducedRotateInRotationModule = (BooleanValue)((BooleanValue)new BooleanValue(this, "ReducedRotateInRotationModule", false).withDisplayName("ReducedRotate")).withVisibility(() -> this.isReducedRotateVisible());
      this.reduceRotationPercentageInRotationModule = new DoubleValue(this, "ReduceRotationPercentageInRotationModule", 100.0, 0.0, 100.0, 0).named("ReduceRotationPercentage").visibleWhen(() -> this.isReduceRotationPercentageVisible());
      this.smartRotateInRotationModule = (BooleanValue)((BooleanValue)new BooleanValue(this, "SmartRotateInRotationModule", false).withDisplayName("SmartRotate")).withVisibility(() -> this.isSmartRotateVisible());
      this.pitchOutliersRotationModule = (BooleanValue)((BooleanValue)new BooleanValue(this, "PitchOutliersRotationModule", false).withDisplayName("PitchOutliers")).withVisibility(() -> this.isPitchOutliersVisible());
      this.pitchOutliersAddRotationModule = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "PitchOutliersAddRotationModule", 4.0, 10.0, 0.0, 20.0, 2).withDisplayNamePrefix("PitchOutliersAdd")).withVisibility(() -> this.isPitchOutliersAddVisible());
      this.pitchOutliersDelayRotationModule = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "PitchOutliersDelayRotationModule", 15.0, 20.0, 1.0, 40.0, 0)).withoutNotInARow().withDisplayNamePrefix("PitchOutliersDelay")).withVisibility(() -> this.isPitchOutliersDelayVisible());
      RangeSetting rangeSetting3 = new RangeSetting(this, "InnerRadiusInRotationModule", "RadiusInRotationModule", 4.0, 9.0, 0.0, 15.0, 2);
      String str22 = "Radius";
      RangeSetting rangeSetting4 = rangeSetting3.withBoundNames("InnerRadius", str22);
      SettingVisibilityCondition condition7 = () -> {
         return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
      };
      this.innerRadiusInRotationModule = rangeSetting4.visibleWhen(condition7);
      this.bestHitXMinDistInRotationModule = new DoubleValue(this, "BestHitXMinDistInRotationModule", 2.0, 0.0, 10.0, 2).named("BestHitXMinDist").visibleWhen(() -> this.isBestHitXMinDistVisible());
      this.bestHitYMInDistInRotationModule = new DoubleValue(this, "BestHitYMInDistInRotationModule", 2.0, 0.0, 10.0, 2).named("BestHitYMInDist").visibleWhen(() -> this.isBestHitYMinDistVisible());
      this.minAbsDistInRotationModule = new DoubleValue(this, "MinAbsDistInRotationModule", 0.5, 0.0, 10.0, 2).named("MinAbsDist").visibleWhen(() -> this.isMinAbsDistVisible());
      this.maxXAbsDistInRotationModule = new DoubleValue(this, "MaxXAbsDistInRotationModule", 5.0, 0.0, 20.0, 2).named("MaxXAbsDist").visibleWhen(() -> this.isMaxXAbsDistVisible());
      this.maxYAbsDistInRotationModule = new DoubleValue(this, "MaxYAbsDistInRotationModule", 20.0, 0.0, 20.0, 2).named("MaxYAbsDist").visibleWhen(() -> this.isMaxYAbsDistVisible());
      RangeSetting rangeSetting5 = new RangeSetting(this, "XRelDistInRotationModule", 0.5, 10.0, 0.0, 20.0, 2);
      String str29 = "MaxXRelDist";
      this.xRelDistInRotationModule = rangeSetting5.withBoundNames("MinXRelDist", str29).visibleWhen(() -> this.isXRelDistVisible());
      RangeSetting rangeSetting7 = new RangeSetting(this, "YRelDistInRotationModule", 0.5, 10.0, 0.0, 20.0, 2);
      String str31 = "MaxYRelDist";
      RangeSetting rangeSetting8 = rangeSetting7.withBoundNames("MinYRelDist", str31);
      SettingVisibilityCondition condition14 = () -> {
         return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
      };
      this.yRelDistInRotationModule = rangeSetting8.visibleWhen(condition14);
      this.maxRotationAngleInRotationModule = new DoubleValue(this, "MaxRotationAngleInRotationModule", 90.0, 0.0, 180.0, 0).named("MaxRotationAngle").visibleWhen(() -> this.isMaxRotationAngleVisible());
      this.randomStrengthInRotationModule = new DoubleValue(this, "RandomStrengthInRotationModule", 1.25, 0.01, 5.0, 2).named("RandomStrength").visibleWhen(() -> this.isRandomStrengthVisible());
      this.moveFixInRotationModule = (BooleanValue)new BooleanValue(this, "MoveFixInRotationModule", true).withDisplayName("MoveFix");
      this.silentMoveFix = (StringValue)new StringValue(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"}).withDisplayName("SilentMoveFix");
      this.silentMoveFixIntaveInRotationModule = (BooleanValue)new BooleanValue(this, "SilentMoveFixIntaveInRotationModule", true).withDisplayName("SilentMoveFixIntave");
      this.rotating = false;
      this.firstRotationTick = true;
      this.rotationUtil = new RotationUtil();
   }

   @Override
   public void onEnable() {
      this.rotationUtil.generateDeltaCandidates((float)this.innerRadiusInRotationModule.getMaxValue(), (short)1000);
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(28);
      this.toggleEventSubscription(21);
   }

   public EntityRotationModule(String str, String str2, boolean flag, Color color2, Categorys categorys2) {
       super(str, str2, flag, color2, categorys2);
      RangeSetting rangeSetting = new RangeSetting(this, "YawSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String str3 = "MaxYawSpeed";
      this.yawSpeedInRotationModule = rangeSetting.withBoundNames("MinYawSpeed", str3);
      RangeSetting rangeSetting2 = new RangeSetting(this, "PitchSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String str5 = "MaxPitchSpeed";
      this.pitchSpeedInRotationModule = rangeSetting2.withBoundNames("MinPitchSpeed", str5);
      this.rotationModeInEntityRotationModule = (StringValue)new StringValue(
         this, "RotationModeInEntityRotationModule", "BestHitVec", new String[]{"Head", "BestHitVec", "NearestHitVec"}
      ).withDisplayName("RotationMode");
      DoubleValue doubleSetting = new DoubleValue(this, "HitBoxPercentageVerticalInEntityRotationModule", 100.0, 1.0, 100.0, 0);
      this.hitBoxPercentageVerticalInEntityRotationModule = doubleSetting.named("HitBoxPercentageVertical");
      doubleSetting = new DoubleValue(this, "HitBoxPercentageHorizontalInEntityRotationModule", 100.0, 1.0, 100.0, 0);
      this.hitBoxPercentageHorizontalInEntityRotationModule = doubleSetting.named("HitBoxPercentageHorizontal");
      doubleSetting = new DoubleValue(this, "HitBoxPercentageVerticalInEntityRotationModule1", 100.0, 1.0, 100.0, 0);
      this.hitBoxPercentageVerticalInEntityRotationModule1 = doubleSetting.named("HitBoxPercentageVertical");
      doubleSetting = new DoubleValue(this, "HitBoxPercentageHorizontalInEntityRotationModule1", 100.0, 1.0, 100.0, 0);
      this.hitBoxPercentageHorizontalInEntityRotationModule1 = doubleSetting.named("HitBoxPercentageHorizontal");
      this.advancedRotsInRotationModule = (BooleanValue)new BooleanValue(this, "AdvancedRotsInRotationModule", true).withDisplayName("AdvancedRots");
      this.interpolationInRotationModule = (BooleanValue)new BooleanValue(this, "InterpolationInRotationModule", false).withDisplayName("Interpolation");
      this.randomTypeInRotationModule = (StringValue)new StringValue(
         this, "RandomTypeInRotationModule", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"}
      ).withDisplayName("RandomType");
      this.randomizeInRotationModule = (StringValue)new StringValue(
         this, "RandomizeInRotationModule", "OnlyRotation", new String[]{"None", "Basic", "Doubled", "OnlyRotation", "Advanced"}
      ).withDisplayName("Randomize");
      this.reducedRotateInRotationModule = (BooleanValue)((BooleanValue)new BooleanValue(this, "ReducedRotateInRotationModule", false).withDisplayName("ReducedRotate")).withVisibility(() -> this.isReducedRotateVisible());
      doubleSetting = new DoubleValue(this, "ReduceRotationPercentageInRotationModule", 100.0, 0.0, 100.0, 0);
      doubleSetting = doubleSetting.named("ReduceRotationPercentage");
      this.reduceRotationPercentageInRotationModule = doubleSetting.visibleWhen(() -> this.isReduceRotationPercentageVisible());
      this.smartRotateInRotationModule = (BooleanValue)((BooleanValue)new BooleanValue(this, "SmartRotateInRotationModule", false).withDisplayName("SmartRotate")).withVisibility(() -> this.isSmartRotateVisible());
      this.pitchOutliersRotationModule = (BooleanValue)((BooleanValue)new BooleanValue(this, "PitchOutliersRotationModule", false).withDisplayName("PitchOutliers")).withVisibility(() -> this.isPitchOutliersVisible());
      this.pitchOutliersAddRotationModule = (RandomizedRangeSettings)((RandomizedRangeSettings)new RandomizedRangeSettings(this, "PitchOutliersAddRotationModule", 4.0, 10.0, 0.0, 20.0, 2).withDisplayNamePrefix("PitchOutliersAdd")).withVisibility(() -> this.isPitchOutliersAddVisible());
      this.pitchOutliersDelayRotationModule = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "PitchOutliersDelayRotationModule", 15.0, 20.0, 1.0, 40.0, 0)).withoutNotInARow().withDisplayNamePrefix("PitchOutliersDelay")).withVisibility(() -> this.isPitchOutliersDelayVisible());
      RangeSetting rangeSetting3 = new RangeSetting(this, "InnerRadiusInRotationModule", "RadiusInRotationModule", 4.0, 9.0, 0.0, 15.0, 2);
      String str22 = "Radius";
      RangeSetting rangeSetting4 = rangeSetting3.withBoundNames("InnerRadius", str22);
      SettingVisibilityCondition condition7 = () -> {
         return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
      };
      this.innerRadiusInRotationModule = rangeSetting4.visibleWhen(condition7);
      this.bestHitXMinDistInRotationModule = new DoubleValue(this, "BestHitXMinDistInRotationModule", 2.0, 0.0, 10.0, 2).named("BestHitXMinDist").visibleWhen(() -> this.isBestHitXMinDistVisible());
      this.bestHitYMInDistInRotationModule = new DoubleValue(this, "BestHitYMInDistInRotationModule", 2.0, 0.0, 10.0, 2).named("BestHitYMInDist").visibleWhen(() -> this.isBestHitYMinDistVisible());
      this.minAbsDistInRotationModule = new DoubleValue(this, "MinAbsDistInRotationModule", 0.5, 0.0, 10.0, 2).named("MinAbsDist").visibleWhen(() -> this.isMinAbsDistVisible());
      this.maxXAbsDistInRotationModule = new DoubleValue(this, "MaxXAbsDistInRotationModule", 5.0, 0.0, 20.0, 2).named("MaxXAbsDist").visibleWhen(() -> this.isMaxXAbsDistVisible());
      this.maxYAbsDistInRotationModule = new DoubleValue(this, "MaxYAbsDistInRotationModule", 20.0, 0.0, 20.0, 2).named("MaxYAbsDist").visibleWhen(() -> this.isMaxYAbsDistVisible());
      RangeSetting rangeSetting5 = new RangeSetting(this, "XRelDistInRotationModule", 0.5, 10.0, 0.0, 20.0, 2);
      String str29 = "MaxXRelDist";
      this.xRelDistInRotationModule = rangeSetting5.withBoundNames("MinXRelDist", str29).visibleWhen(() -> this.isXRelDistVisible());
      RangeSetting rangeSetting7 = new RangeSetting(this, "YRelDistInRotationModule", 0.5, 10.0, 0.0, 20.0, 2);
      String str31 = "MaxYRelDist";
      RangeSetting rangeSetting8 = rangeSetting7.withBoundNames("MinYRelDist", str31);
      SettingVisibilityCondition condition14 = () -> {
         return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
      };
      this.yRelDistInRotationModule = rangeSetting8.visibleWhen(condition14);
      this.maxRotationAngleInRotationModule = new DoubleValue(this, "MaxRotationAngleInRotationModule", 90.0, 0.0, 180.0, 0).named("MaxRotationAngle").visibleWhen(() -> this.isMaxRotationAngleVisible());
      this.randomStrengthInRotationModule = new DoubleValue(this, "RandomStrengthInRotationModule", 1.25, 0.01, 5.0, 2).named("RandomStrength").visibleWhen(() -> this.isRandomStrengthVisible());
      this.moveFixInRotationModule = (BooleanValue)new BooleanValue(this, "MoveFixInRotationModule", true).withDisplayName("MoveFix");
      this.silentMoveFix = (StringValue)new StringValue(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"}).withDisplayName("SilentMoveFix");
      this.silentMoveFixIntaveInRotationModule = (BooleanValue)new BooleanValue(this, "SilentMoveFixIntaveInRotationModule", true).withDisplayName("SilentMoveFixIntave");
      this.rotating = false;
      this.firstRotationTick = true;
      this.rotationUtil = new RotationUtil();
   }

   public Rotation calculateRotationToEntity(Entity entity) {
      Vec3 targetHitVec = this.getTargetHitVec(entity);
      if (targetHitVec != null) {
         double zCoord = targetHitVec.zCoord;
         double yCoord = targetHitVec.yCoord;
         return this.calculateRotationToPos(targetHitVec.xCoord, yCoord, zCoord);
      } else {
         return null;
      }
   }

   public Rotation calculateInterpolatedRotationToEntity(Entity entity, float f) {
      Vec3 vec = this.getInterpolatedTargetHitVec(entity, f, ClientManagers.rotationController.getPreviousRotation());
      if (vec != null) {
         double zCoord = vec.zCoord;
         double yCoord = vec.yCoord;
         return this.calculateInterpolatedRotationToPos(vec.xCoord, yCoord, zCoord, f);
      } else {
         return null;
      }
   }

   public Rotation calculateSmoothRotationToEntity(Entity entity, Rotation rotation, float f, float f2, float f3) {
      Vec3 vec = this.getInterpolatedTargetHitVec(entity, f, rotation);
      if (vec != null) {
         double zCoord = vec.zCoord;
         double yCoord = vec.yCoord;
         return this.calculateSmoothRotationToEntityPos(entity, vec.xCoord, yCoord, zCoord, rotation, f2, f3, f);
      } else {
         return null;
      }
   }

   private Vec3 getTargetHitVec(Entity entity) {
      Vec3 vec = null;
      switch (this.rotationModeInEntityRotationModule.getSelectedValue()) {
         case "Head":
            vec = new Vec3(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ);
            break;
         case "BestHitVec":
            vec = RotationUtil.getBestHitVec(entity, (int)this.hitBoxPercentageVerticalInEntityRotationModule.getValue(), (int)this.hitBoxPercentageHorizontalInEntityRotationModule.getValue());
            break;
         case "NearestHitVec":
            vec = RotationUtil.getNearestLookPointOnEntity(entity, (int)this.hitBoxPercentageVerticalInEntityRotationModule1.getValue(), (int)this.hitBoxPercentageHorizontalInEntityRotationModule1.getValue(), ClientManagers.rotationController.getPreviousRotation());
      }

      return vec;
   }

   private Vec3 getInterpolatedTargetHitVec(Entity entity, float f, Rotation rotation) {
      Vec3 vec = null;
      switch (this.rotationModeInEntityRotationModule.getSelectedValue()) {
         case "Head":
            vec = new Vec3(
               entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getTimer().renderPartialTicks,
               entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getTimer().renderPartialTicks + (double)entity.getEyeHeight(),
               entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks
            );
            break;
         case "BestHitVec":
            vec = RotationUtil.getInterpolatedBestHitVec(entity, f, (int)this.hitBoxPercentageVerticalInEntityRotationModule.getValue(), (int)this.hitBoxPercentageHorizontalInEntityRotationModule.getValue());
            break;
         case "NearestHitVec":
            vec = RotationUtil.getInterpolatedNearestLookPoint(entity, f, (int)this.hitBoxPercentageVerticalInEntityRotationModule1.getValue(), (int)this.hitBoxPercentageHorizontalInEntityRotationModule1.getValue(), rotation);
      }

      return vec;
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

   public Rotation calculateSmoothRotationToEntityPos(Entity entity, double d, double d2, double d3, Rotation rotation, float f, float f2, float f3) {
      double d4 = mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * (double)f3;
      double d5 = mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * (double)f3;
      double d6 = mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * (double)f3;
      double d7 = d - d4;
      double d8 = d2 - (d5 + (double)mc.thePlayer.getEyeHeight());
      double d9 = d3 - d6;
      return this.computeSmoothedRotation(entity, (float)(MathHelper.atan2(d9, d7) * 180.0 / Math.PI - 90.0), (float)(-(MathHelper.atan2(d8, (double)MathHelper.sqrt_double(d7 * d7 + d9 * d9)) * 180.0 / Math.PI)), rotation, f, f2);
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
      Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
      return this.applyRotationWithRandomSpeed((EntityLivingBase)null, f, f2, previousRotation);
   }

   public Rotation applyRotationWithRandomSpeed(EntityLivingBase entity, float f, float f2, Rotation rotation) {
      RandomNumberGenerator instance = DefaultRandomGenerator.instance;
      double minValue = this.yawSpeedInRotationModule.getMinValue();
      float f3 = instance.nextSecureFloat(minValue, this.yawSpeedInRotationModule.getMaxValue());
      instance = DefaultRandomGenerator.instance;
      minValue = this.pitchSpeedInRotationModule.getMinValue();
      return this.computeSmoothedRotation(entity, f, f2, rotation, f3, instance.nextSecureFloat(minValue, this.pitchSpeedInRotationModule.getMaxValue()));
   }

   public Rotation computeSmoothedRotation(Entity entity, float f, float f2, Rotation rotation, float f3, float f4) {
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
            break;
         case "Advanced":
            if (entity instanceof EntityLivingBase) {
               RotationUtil rotationUtil2 = this.rotationUtil;
               boolean firstRotationTick2 = this.firstRotationTick;
               EntityLivingBase entity2 = (EntityLivingBase)entity;
               float yaw = rotation.yaw;
               float pitch = rotation.pitch;
               String selectedValue2 = this.randomTypeInRotationModule.getSelectedValue();
               double value = this.reduceRotationPercentageInRotationModule.getValue();
               boolean value2 = this.reducedRotateInRotationModule.getValue();
               boolean value3 = this.pitchOutliersRotationModule.getValue();
               RandomizedRangeSettings randomizedRangeSettings = this.pitchOutliersAddRotationModule;
               RandomizedRangeSettings randomizedRangeSettings2 = this.pitchOutliersDelayRotationModule;
               double d11 = ClientManagers.moduleManager.range.isToggled() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0;
               boolean value4 = this.smartRotateInRotationModule.getValue();
               float value5 = (float)this.maxXAbsDistInRotationModule.getValue();
               float value6 = (float)this.maxYAbsDistInRotationModule.getValue();
               float value7 = (float)this.minAbsDistInRotationModule.getValue();
               float minValue = (float)this.xRelDistInRotationModule.getMinValue();
               Rotation rotation2 = rotationUtil2.computeAdvancedRotation(
                  firstRotationTick2,
                  entity2,
                  f5,
                  f6,
                  yaw,
                  pitch,
                  selectedValue2,
                  f,
                  f2,
                  value,
                  value2,
                  value3,
                  randomizedRangeSettings,
                  randomizedRangeSettings2,
                  d11,
                  value4,
                  value5,
                  value6,
                  value7,
                  minValue,
                  (float)this.yRelDistInRotationModule.getMinValue(),
                  (float)this.xRelDistInRotationModule.getMaxValue(),
                  (float)this.yRelDistInRotationModule.getMaxValue(),
                  (float)this.maxRotationAngleInRotationModule.getValue(),
                  (float)this.innerRadiusInRotationModule.getMinValue(),
                  (float)this.bestHitXMinDistInRotationModule.getValue(),
                  (float)this.bestHitYMInDistInRotationModule.getValue()
               );
               this.firstRotationTick = false;
               f5 = rotation2.yaw;
               f6 = rotation2.pitch;
            }
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
      DoubleValue doubleSetting = this.hitBoxPercentageHorizontalInEntityRotationModule;
      doubleSetting.visibleWhen(() -> this.isBestHitHorizontalVisible());
      doubleSetting = this.hitBoxPercentageVerticalInEntityRotationModule;
      doubleSetting.visibleWhen(() -> this.isBestHitVerticalVisible());
      doubleSetting = this.hitBoxPercentageHorizontalInEntityRotationModule1;
      doubleSetting.visibleWhen(() -> this.isNearestHitHorizontalVisible());
      doubleSetting = this.hitBoxPercentageVerticalInEntityRotationModule1;
      doubleSetting.visibleWhen(() -> this.isNearestHitVerticalVisible());
      this.silentMoveFix.withVisibility(() -> this.isSilentMoveFixVisible());
      BooleanValue booleanValue = this.silentMoveFixIntaveInRotationModule;
      SettingVisibilityCondition condition6 = () -> {
         return this.silentMoveFix.isVisible() && !this.silentMoveFix.getSelectedValue().equals("None");
      };
      booleanValue.withVisibility(condition6);
   }

   private boolean isSilentMoveFixVisible() {
      return this.moveFixInRotationModule.getValue() && this.moveFixInRotationModule.isVisible();
   }

   private boolean isNearestHitVerticalVisible() {
      return this.rotationModeInEntityRotationModule.getSelectedValue().equalsIgnoreCase("NearestHitVec");
   }

   private boolean isNearestHitHorizontalVisible() {
      return this.rotationModeInEntityRotationModule.getSelectedValue().equalsIgnoreCase("NearestHitVec");
   }

   private boolean isBestHitVerticalVisible() {
      return this.rotationModeInEntityRotationModule.getSelectedValue().equalsIgnoreCase("BestHitVec");
   }

   private boolean isBestHitHorizontalVisible() {
      return this.rotationModeInEntityRotationModule.getSelectedValue().equalsIgnoreCase("BestHitVec");
   }

   private boolean isRandomStrengthVisible() {
      return !this.randomizeInRotationModule.getSelectedValue().equals("Advanced") && !this.randomizeInRotationModule.getSelectedValue().equals("None");
   }

   private boolean isMaxRotationAngleVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isXRelDistVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isMaxYAbsDistVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isMaxXAbsDistVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isMinAbsDistVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isBestHitYMinDistVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isBestHitXMinDistVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isPitchOutliersDelayVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced") && this.pitchOutliersRotationModule.getValue();
   }

   private boolean isPitchOutliersAddVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced") && this.pitchOutliersRotationModule.getValue();
   }

   private boolean isPitchOutliersVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isSmartRotateVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }

   private boolean isReduceRotationPercentageVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced") && this.reducedRotateInRotationModule.getValue();
   }

   private boolean isReducedRotateVisible() {
      return this.randomizeInRotationModule.getSelectedValue().equals("Advanced");
   }


}

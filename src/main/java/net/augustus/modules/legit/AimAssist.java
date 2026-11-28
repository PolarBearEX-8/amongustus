package net.augustus.modules.legit;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.WeaponFilterSettings;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.LookDownDisableSettings;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.utils.rotation.HitboxAngleUtil;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.events.EventMouseMove;
import net.augustus.utils.TickCounter;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class AimAssist extends Module implements LoadWorldListener, TickListener, MouseMoveListener {
   private final RandomizedRangeSettings preAimRange;
   private StringValue mode;
   private SettingVisibilityCondition stableModeCondition;
   private SettingVisibilityCondition experimentalModeCondition;
   private DoubleValue strength;
   private DoubleValue fasterMouseMovement;
   private final SettingGroup improveYourAimInput;
   private final DoubleValue ownHorizontalRotationMultiplier;
   private final DoubleValue ownVerticalRotationMultiplier;
   private final DoubleValue horizontalAssistMultiplier;
   private final DoubleValue verticalAssistMultiplier;
   private final SettingGroup autoAim;
   private final RandomizedRangeSettings horizontalSpeed;
   private final RandomizedRangeSettings verticalSpeed;
   private final DoubleValue horizontalSmoothness;
   private final DoubleValue verticalSmoothness;
   private final BooleanValue smoothEarly;
   private final StringValue noRotationFovMode;
   private final DoubleValue noRotationHorizontalFov;
   private final DoubleValue noRotationVerticalFov;
   private final DoubleValue noRotationHorizontal;
   private final DoubleValue noRotationVertical;
   private final DoubleValue horizontalBoxSize;
   private final DoubleValue randomizationStrength;
   private final BooleanValue randomizeInNoRotationFovs;
   private final DoubleValue horizontalFov;
   private final DoubleValue verticalFov;
   private final BooleanValue click;
   private final BooleanValue diggingCheck;
   private final DoubleValue aimTickDelayAfterDigging;
   private final TargetSelectionSettings targetSelectionSettings;
   private final BooleanValue selectTargetThroughWalls;
   private final WeaponFilterSettings weaponFilterSettings;
   private final LookDownDisableSettings lookDownDisableSettings;
   private final TickCounter tickCounter;
   private float lastPartialTicks;
   public EntityLivingBase target;
   private final double[] assistDeltaRemainder;
   private final double[] ownDeltaRemainder;
   private int[] previousMouseDelta;
   private int[] targetMouseDelta;
   private double[] accumulatedMouseDelta;
   private int[] appliedMouseDelta;
   private long lastTickTimeMs;
   private int tickDurationMs;
   private long lastFrameTimeMs;

   public AimAssist() {
       super("AimAssist", "Ziehlhilfe", new Color(66, 135, 245), Categorys.LEGIT);
      this.preAimRange = (new RandomizedRangeSettings(this, "Pre aim range", 5.0, 5.0, 0.0, 10.0, 1)).withoutNotInARow();
      this.mode = new StringValue(this, "Mode", "Stable", new String[]{"Stable", "Experimental"});
      this.stableModeCondition = () -> this.isStableMode();
      this.experimentalModeCondition = () -> {
         return this.mode.getSelectedValue().equals("Experimental");
      };
      this.strength = new DoubleValue(this, "Strength", 2.0, 1.0, 5.0, 2).visibleWhen(this.experimentalModeCondition);
      this.fasterMouseMovement = new DoubleValue(this, "Faster mouse movement", 1.0, 1.0, 5.0, 2).visibleWhen(this.experimentalModeCondition);
      this.improveYourAimInput = new SettingGroup(this, "Improve your aim input").visibleWhen(this.stableModeCondition);
      this.ownHorizontalRotationMultiplier = new DoubleValue(this, "Own horizontal rotation multiplier", 1.0, 0.0, 1.0, 2, this.improveYourAimInput);
      this.ownVerticalRotationMultiplier = new DoubleValue(this, "Own vertical rotation multiplier", 1.0, 0.0, 1.0, 2, this.improveYourAimInput);
      this.horizontalAssistMultiplier = new DoubleValue(this, "Horizontal assist multiplier", 1.0, 0.0, 1.0, 2, this.improveYourAimInput);
      this.verticalAssistMultiplier = new DoubleValue(this, "Vertical assist multiplier", 1.0, 0.0, 1.0, 2, this.improveYourAimInput);
      this.autoAim = new SettingGroup(this, "Auto aim").visibleWhen(this.stableModeCondition);
      this.horizontalSpeed = (new RandomizedRangeSettings(this, " horizontal speed", 4.0, 6.0, 0.0, 15.0, 1, this.autoAim)).withoutNotInARow();
      this.verticalSpeed = (new RandomizedRangeSettings(this, " vertical speed", 4.0, 6.0, 0.0, 15.0, 1, this.autoAim)).withoutNotInARow();
      this.horizontalSmoothness = new DoubleValue(this, "Horizontal smoothness", 5.0, 1.0, 10.0, 0, this.autoAim);
      this.verticalSmoothness = new DoubleValue(this, "Vertical smoothness", 5.0, 1.0, 10.0, 0, this.autoAim);
      this.smoothEarly = new BooleanValue(this, "Smooth early", true, this.autoAim);
      this.noRotationFovMode = new StringValue(this, "NoRotationFovMode", "2D", new String[]{"2D", "3D"}, this.autoAim);
      this.noRotationHorizontalFov = new DoubleValue(this, "No rotation horizontal fov", 5.0, 0.0, 15.0, 0, this.autoAim).visibleWhen(() -> this.is2DNoRotationMode());
      DoubleValue noRotationVerticalFovSetting = new DoubleValue(this, "No rotation vertical fov", 5.0, 0.0, 15.0, 0, this.autoAim);
      SettingVisibilityCondition condition4 = () -> {
         return this.noRotationFovMode.getSelectedValue().equals("2D");
      };
      this.noRotationVerticalFov = noRotationVerticalFovSetting.visibleWhen(condition4);
      DoubleValue noRotationHorizontalSetting = new DoubleValue(this, "No rotation horizontal", 1.75, 0.0, 3.0, 2, this.autoAim);
      SettingVisibilityCondition condition5 = () -> {
         return this.noRotationFovMode.getSelectedValue().equals("3D");
      };
      this.noRotationHorizontal = noRotationHorizontalSetting.visibleWhen(condition5);
      DoubleValue noRotationVerticalSetting = new DoubleValue(this, "No rotation vertical", 1.0, 0.0, 3.0, 2, this.autoAim);
      SettingVisibilityCondition condition6 = () -> {
         return this.noRotationFovMode.getSelectedValue().equals("3D");
      };
      this.noRotationVertical = noRotationVerticalSetting.visibleWhen(condition6);
      this.horizontalBoxSize = new DoubleValue(this, "Horizontal box size", 20.0, 0.0, 100.0, 0, this.autoAim);
      this.randomizationStrength = new DoubleValue(this, "Randomization strength", 0.0, 0.0, 5.0, 0, this.autoAim);
      this.randomizeInNoRotationFovs = (BooleanValue)new BooleanValue(this, "Randomize in no rotation fovs", true, this.autoAim).withVisibility(() -> this.isRandomizationEnabled());
      this.horizontalFov = (DoubleValue)(new DoubleValue(this, "Horizontal fov", 90.0, 0.0, 180.0, 0)).markVisualOnly();
      this.verticalFov = (DoubleValue)(new DoubleValue(this, "Vertical fov", 90.0, 0.0, 180.0, 0)).markVisualOnly();
      this.click = (BooleanValue)(new BooleanValue(this, "Click", true)).markVisualOnly();
      this.diggingCheck = (BooleanValue)(new BooleanValue(this, "Digging check", true)).markVisualOnly();
      DoubleValue aimTickDelayAfterDigging2 = new DoubleValue(this, "Aim tick delay after digging", 0.0, 0.0, 20.0, 0);
      BooleanValue diggingCheck2 = this.diggingCheck;
      this.aimTickDelayAfterDigging = (DoubleValue)aimTickDelayAfterDigging2.visibleWhen(() -> diggingCheck2.getValue()).markVisualOnly();
      this.targetSelectionSettings = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter())).withSorting();
      this.selectTargetThroughWalls = new BooleanValue(this, "SelectTargetThroughWalls", true);
      this.weaponFilterSettings = new WeaponFilterSettings(this);
      this.lookDownDisableSettings = new LookDownDisableSettings(this);
      this.tickCounter = new TickCounter();
      this.lastPartialTicks = 0.0F;
      this.target = null;
      this.assistDeltaRemainder = new double[2];
      this.ownDeltaRemainder = new double[2];
      this.previousMouseDelta = new int[2];
      this.targetMouseDelta = new int[2];
      this.accumulatedMouseDelta = new double[2];
      this.appliedMouseDelta = new int[2];
      this.lastTickTimeMs = 0L;
      this.tickDurationMs = 0;
      this.lastFrameTimeMs = 0L;
   }

   @Override
   public void onEnable() {
      this.lastFrameTimeMs = System.currentTimeMillis();
      this.lastTickTimeMs = System.currentTimeMillis();
   }

   @Override
   public void onDisable() {
      this.target = null;
      this.targetSelectionSettings.setCurrentTarget((Entity)null);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(19);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onMouseMove(EventMouseMove event) {
      if (ClientManagers.moduleManager.freelook.isToggled()) {
         event.setDeltaX((byte)0);
         event.setDeltaY((byte)0);
      }

      if (this.canAssist()) {
         if (this.mode.getSelectedValue().equals("Stable")) {
            this.applyStableAimAssist(event);
         } else {
            this.applyExperimentalAimAssist(event);
         }
      }

      this.lastPartialTicks = event.getPartialTicks();
      this.lastFrameTimeMs = System.currentTimeMillis();
   }

   private void applyExperimentalAimAssist(EventMouseMove event) {
      Vec3 vec = RotationUtil.getInterpolatedNearestPoint(this.target, event.getPartialTicks());
      double xCoord = vec.xCoord;
      double yCoord = vec.yCoord;
      double zCoord = vec.zCoord;
      Rotation rotation = new Rotation(this.getYaw(), this.getPitch());
      float partialTicks2 = event.getPartialTicks();
      float f = 180.0F;
      Rotation rotation3 = RotationUtil.getInterpolatedRotationsToPos(xCoord, yCoord, zCoord, rotation, 180.0F, f, false, partialTicks2);
      double shortestSignedAngleDelta = EntityBoundsUtil.getShortestSignedAngleDelta(
         (double)MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw), MathHelper.wrapAngleTo180_double((double)rotation3.yaw)
      );
      double d2 = (double)(-(rotation3.pitch - mc.thePlayer.rotationPitch));
      if (mc.gameSettings.invertMouse && !event.isIgnoreInvertMouse()) {
         d2 *= -1.0;
      }

      double value = this.strength.getValue(true);
      double value2 = this.fasterMouseMovement.getValue(true);
      if ((double)Math.signum((float)event.getDeltaX()) == Math.signum(shortestSignedAngleDelta)
         && (!((double)Math.abs(event.getDeltaX()) > Math.abs(shortestSignedAngleDelta)) || !(Math.abs((double)event.getDeltaX() / value - shortestSignedAngleDelta) < Math.abs(shortestSignedAngleDelta - (double)event.getDeltaX())))) {
         if ((double)((int)((double)Math.abs(event.getDeltaX()) * value2)) < Math.abs(shortestSignedAngleDelta)) {
            event.setDeltaX((int)((double)event.getDeltaX() * value2));
         }
      } else {
         event.setDeltaX((int)((double)event.getDeltaX() / value));
      }

      if ((double)Math.signum((float)event.getDeltaY()) == Math.signum(d2)
         && (!((double)Math.abs(event.getDeltaY()) > Math.abs(d2)) || !(Math.abs((double)event.getDeltaY() / value - d2) < Math.abs(d2 - (double)event.getDeltaY())))) {
         if ((double)((int)((double)Math.abs(event.getDeltaY()) * value2)) < Math.abs(d2)) {
            event.setDeltaY((int)((double)event.getDeltaY() * value2));
         }
      } else {
         event.setDeltaY((int)((double)event.getDeltaY() / value));
      }
   }

   private void applyStableAimAssist(EventMouseMove event) {
      int n = (int)(System.currentTimeMillis() - this.lastFrameTimeMs);
      Vec3 vec = RotationUtil.getInterpolatedNearestPoint(this.target, event.getPartialTicks());
      double xCoord = vec.xCoord;
      double yCoord = vec.yCoord;
      double zCoord = vec.zCoord;
      Rotation rotation = new Rotation(this.getYaw(), this.getPitch());
      float partialTicks2 = event.getPartialTicks();
      boolean flag = false;
      float f = 180.0F;
      float f2 = 180.0F;
      Rotation rotation2 = rotation;
      Rotation rotation3 = RotationUtil.getInterpolatedRotationsToPos(xCoord, yCoord, zCoord, rotation2, f2, f, flag, partialTicks2);
      int[] ints = RotationUtil.computeMouseDeltas(rotation3.yaw, rotation3.pitch, this.getYaw(), this.getPitch());
      xCoord = vec.xCoord;
      yCoord = vec.yCoord;
      zCoord = vec.zCoord;
      rotation = new Rotation(this.getYaw(), this.getPitch());
      float f3 = 4.0F * (event.getPartialTicks() - this.lastPartialTicks);
      partialTicks2 = event.getPartialTicks();
      flag = false;
      f = 4.0F;
      f2 = f3;
      rotation2 = rotation;
      Rotation rotation4 = RotationUtil.getInterpolatedRotationsToPos(xCoord, yCoord, zCoord, rotation2, f2, f, flag, partialTicks2);
      int[] ints2 = RotationUtil.computeMouseDeltas(rotation4.yaw, rotation4.pitch, this.getYaw(), this.getPitch());
      double d = (double)this.targetMouseDelta[0] / (double)this.tickDurationMs * (double)n;
      double d2 = (double)this.targetMouseDelta[1] / (double)this.tickDurationMs * (double)n;
      this.accumulatedMouseDelta[0] = this.accumulatedMouseDelta[0] + d;
      this.accumulatedMouseDelta[1] = this.accumulatedMouseDelta[1] + d2;
      int n2 = (int)Math.round(this.accumulatedMouseDelta[0] - (double)this.appliedMouseDelta[0]);
      int n3 = (int)Math.round(this.accumulatedMouseDelta[1] - (double)this.appliedMouseDelta[1]);
      this.appliedMouseDelta[0] = this.appliedMouseDelta[0] + n2;
      this.appliedMouseDelta[1] = this.appliedMouseDelta[1] + n3;
      if (event.getDeltaX() != 0) {
         int n4 = ints[0];
         if (n4 < 0) {
            if (n4 - event.getDeltaX() <= n4) {
               double d4 = ((double)event.getDeltaX() * this.horizontalAssistMultiplier.getValue()) + this.assistDeltaRemainder[0];
               int n5 = (int)Math.round(d4);
               event.setDeltaX(n5);
               this.assistDeltaRemainder[0] = d4 - (double)n5;
            }
         } else if (n4 - event.getDeltaX() >= n4) {
            double d6 = ((double)event.getDeltaX() * this.horizontalAssistMultiplier.getValue()) + this.assistDeltaRemainder[0];
            int n6 = (int)Math.round(d6);
            event.setDeltaX(n6);
            this.assistDeltaRemainder[0] = d6 - (double)n6;
         }
      }

      if (event.getDeltaY() != 0) {
         int n7 = ints[1];
         if (n7 < 0) {
            if (n7 - event.getDeltaY() <= n7) {
               double d8 = ((double)event.getDeltaY() * this.verticalAssistMultiplier.getValue()) + this.assistDeltaRemainder[1];
               int n8 = (int)Math.round(d8);
               event.setDeltaY(n8);
               this.assistDeltaRemainder[1] = d8 - (double)n8;
            }
         } else if (n7 - event.getDeltaY() >= n7) {
            double d10 = ((double)event.getDeltaY() * this.verticalAssistMultiplier.getValue()) + this.assistDeltaRemainder[1];
            int n9 = (int)Math.round(d10);
            event.setDeltaY(n9);
            this.assistDeltaRemainder[1] = d10 - (double)n9;
         }
      }

      double d12 = ((double)event.getDeltaX() * this.ownHorizontalRotationMultiplier.getValue()) + this.ownDeltaRemainder[0];
      int n10 = (int)Math.round(d12);
      event.setDeltaX(n10);
      this.ownDeltaRemainder[0] = d12 - (double)n10;
      double d14 = ((double)event.getDeltaY() * this.ownVerticalRotationMultiplier.getValue()) + this.ownDeltaRemainder[1];
      int n11 = (int)Math.round(d14);
      event.setDeltaY(n11);
      this.ownDeltaRemainder[1] = d14 - (double)n11;
      event.setDeltaX(event.getDeltaX() + n2);
      event.setDeltaY(event.getDeltaY() + n3);
   }

   @Override
   public void onPreTick() {
      this.tickDurationMs = (int)Math.abs(this.lastTickTimeMs - System.currentTimeMillis());
      this.lastTickTimeMs = System.currentTimeMillis();
      this.appliedMouseDelta[0] = this.appliedMouseDelta[1] = 0;
      this.accumulatedMouseDelta[0] = this.accumulatedMouseDelta[1] = 0.0;
   }

   @Override
   public void onLateTick() {
      this.tickCounter.increment();
      this.lastPartialTicks = 0.0F;
      EntityLivingBase target2 = this.target;
      this.targetSelectionSettings.setCurrentTarget(target2);
      this.target = null;
      if (mc.isNotInGui() && (!this.click.getValue() || mc.gameSettings.keyBindAttack.isKeyDown()) && this.weaponFilterSettings.isHeldItemAllowed() && !this.lookDownDisableSettings.shouldDisable()) {
         this.target = this.findTarget();
         if (this.target != null) {
            EntityLivingBase target3 = this.target;
            Vec3 bestHitVec = RotationUtil.getBestHitVec(target3, (byte)100, (int)this.horizontalBoxSize.getValue());
            target3 = this.target;
            float[] floats = RotationUtil.getAngleDiffToEntityNearestPoint(target3, this.getYaw(), this.getPitch());
            float f2 = Math.abs(floats[0]);
            float f3 = Math.abs(floats[1]);
            target3 = this.target;
            float[] angleDiffToEntityEyes = RotationUtil.getAngleDiffToEntityEyes(target3, this.getYaw(), this.getPitch());
            float f5 = Math.abs(angleDiffToEntityEyes[0]);
            float f6 = Math.abs(angleDiffToEntityEyes[1]);
            if (this.smoothEarly.getValue()) {
               if (this.noRotationFovMode.getSelectedValue().equals("2D")) {
                  f5 = Math.max(f5 - (float)this.noRotationHorizontalFov.getValue(), 0.0F);
                  f6 = Math.max(f6 - (float)this.noRotationVerticalFov.getValue(), 0.0F);
               } else {
                  float f7 = (float)(this.noRotationHorizontal.getValue() / 2.0);
                  float f8 = (float)(this.noRotationVertical.getValue() / 2.0);
                  if (f7 > 0.0F && f8 > 0.0F) {
                     Vec3 center = this.target.getEntityBoundingBox().getCenter();
                     AxisAlignedBB box = new AxisAlignedBB(
                        center.xCoord - (double)f7,
                        bestHitVec.yCoord - (double)f8,
                        center.zCoord - (double)f7,
                        center.xCoord + (double)f7,
                        bestHitVec.yCoord + (double)f8,
                        center.zCoord + (double)f7
                     );
                     Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
                     target3 = this.target;
                     float f9 = (float)HitboxAngleUtil.lerpRange(HitboxAngleUtil.getExtremeYawAngles(positionEyes, box), 1.0);
                     double[] pitchRange = HitboxAngleUtil.getPitchRange(positionEyes, box);
                     float[] angleDiffToEntityEyes2 = RotationUtil.getAngleDiffToEntityEyes(target3, f9, (float)HitboxAngleUtil.lerpRange(pitchRange, 1.0));
                     target3 = this.target;
                     float f12 = (float)HitboxAngleUtil.lerpRange(HitboxAngleUtil.getExtremeYawAngles(positionEyes, box), 0.0);
                     pitchRange = HitboxAngleUtil.getPitchRange(positionEyes, box);
                     float[] angleDiffToEntityEyes3 = RotationUtil.getAngleDiffToEntityEyes(target3, f12, (float)HitboxAngleUtil.lerpRange(pitchRange, 0.0));
                     if (angleDiffToEntityEyes[0] < 0.0F) {
                        f5 = Math.max(f5 - Math.abs(angleDiffToEntityEyes2[0]), 0.0F);
                     } else {
                        f5 = Math.max(f5 - Math.abs(angleDiffToEntityEyes3[0]), 0.0F);
                     }

                     if (angleDiffToEntityEyes[1] < 0.0F) {
                        f6 = Math.max(f6 - Math.abs(angleDiffToEntityEyes2[1]), 0.0F);
                     } else {
                        f6 = Math.max(f6 - Math.abs(angleDiffToEntityEyes3[1]), 0.0F);
                     }
                  }
               }
            }

            float f15 = MathHelper.clamp_float((float)this.horizontalSpeed.nextValue(), 0.0F, 180.0F);
            float f16 = MathHelper.clamp_float((float)this.verticalSpeed.nextValue(), 0.0F, 180.0F);
            float f17 = MathHelper.clamp_float((float)this.horizontalSmoothness.getValue(), 1.0F, 10.0F);
            float f18 = MathHelper.clamp_float((float)this.verticalSmoothness.getValue(), 1.0F, 10.0F);
            float f19 = f15 == 0.0F ? 0.0F : f15 * (MathHelper.clamp_float(Math.abs(f5), f15, f15 * f17) / (f15 * f17));
            float f20 = f16 == 0.0F ? 0.0F : f16 * (MathHelper.clamp_float(Math.abs(f6), f16, f16 * f18) / (f16 * f18));
            Rotation rotationsToPos = RotationUtil.getRotationsToPos(bestHitVec.xCoord, bestHitVec.yCoord, bestHitVec.zCoord, new Rotation(this.getYaw(), this.getPitch()), f19, f20, false);
            this.targetMouseDelta = RotationUtil.computeMouseDeltas(rotationsToPos.yaw, rotationsToPos.pitch, this.getYaw(), this.getPitch());
            boolean flag2 = false;
            boolean flag3 = false;
            if (this.noRotationFovMode.getSelectedValue().equals("2D")) {
               if ((double)f2 < this.noRotationHorizontalFov.getValue()) {
                  this.targetMouseDelta[0] = 0;
                  flag2 = true;
               }

               if ((double)f3 < this.noRotationVerticalFov.getValue()) {
                  this.targetMouseDelta[1] = 0;
                  flag3 = true;
               }
            } else {
               float f21 = (float)(this.noRotationHorizontal.getValue() / 2.0);
               float f22 = (float)(this.noRotationVertical.getValue() / 2.0);
               if (f21 > 0.0F && f22 > 0.0F) {
                  bestHitVec = RotationUtil.getBestHitVec(this.target, (byte)100, (byte)100);
                  Vec3 center2 = this.target.getEntityBoundingBox().getCenter();
                  AxisAlignedBB box2 = new AxisAlignedBB(
                     center2.xCoord - (double)f21,
                     bestHitVec.yCoord - (double)f22,
                     center2.zCoord - (double)f21,
                     center2.xCoord + (double)f21,
                     bestHitVec.yCoord + (double)f22,
                     center2.zCoord + (double)f21
                  );
                  Vec3 positionEyes2 = mc.thePlayer.getPositionEyes(1.0F);
                  double normalizedYawInBox = HitboxAngleUtil.getNormalizedYawInBox(box2, positionEyes2, (double)(rotationsToPos.yaw % 360.0F));
                  double normalizedPitchInBox = HitboxAngleUtil.getNormalizedPitchInBox(box2, positionEyes2, (double)rotationsToPos.pitch);
                  if (normalizedYawInBox > 0.0 && normalizedYawInBox < 1.0) {
                     this.targetMouseDelta[0] = 0;
                     flag2 = true;
                  }

                  if (normalizedPitchInBox > 0.0 && normalizedPitchInBox < 1.0) {
                     this.targetMouseDelta[1] = 0;
                     flag3 = true;
                  }
               }
            }

            if (this.randomizationStrength.getValue() > 0.0) {
               if (this.randomizeInNoRotationFovs.getValue() || !flag2) {
                  this.targetMouseDelta[0] = this.targetMouseDelta[0] + DefaultRandomGenerator.instance.nextInt(-this.randomizationStrength.getValue(), this.randomizationStrength.getValue());
               }

               if (this.randomizeInNoRotationFovs.getValue() || !flag3) {
                  this.targetMouseDelta[1] = this.targetMouseDelta[1] + DefaultRandomGenerator.instance.nextInt(-this.randomizationStrength.getValue(), this.randomizationStrength.getValue());
               }
            }
         } else {
            this.preAimRange.nextValue();
         }
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.target = null;
      this.targetSelectionSettings.setCurrentTarget((Entity)null);
   }

   private boolean canAssist() {
      if (!mc.thePlayer.isDead && mc.thePlayer.ticksExisted > 20 && this.target != null && mc.isNotInGui() && (!this.click.getValue() || mc.gameSettings.keyBindAttack.isKeyDown())) {
         AutoRod autoRod = ClientManagers.moduleManager.autoRod;
         if (!ClientManagers.rotationController.isOwnedBy(autoRod) && this.passesDiggingCheck()) {
            return true;
         }
      }

      return false;
   }

   private boolean passesDiggingCheck() {
      boolean flag = this.diggingCheck.getValue() && mc.gameSettings.keyBindAttack.isKeyDown() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK;
      if (flag) {
         this.tickCounter.reset();
      }

      return this.tickCounter.hasReached(this.aimTickDelayAfterDigging.getValue()) && !flag;
   }

   private float getYaw() {
      return Augustus.getInstance().getYawPitchHelper().realYaw;
   }

   private float getPitch() {
      return Augustus.getInstance().getYawPitchHelper().realPitch;
   }

   private EntityLivingBase findTarget() {
      EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
      entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelectionSettings.getTargetFilter());
      entityTargetSelector = entityTargetSelector.throughWalls(this.selectTargetThroughWalls.getValue());
      entityTargetSelector = entityTargetSelector.maxDistance(this.preAimRange.getLastValue());
      entityTargetSelector = entityTargetSelector.withinFov(this.horizontalFov.getValue(), this.verticalFov.getValue()).filterFocus();
      ToDoubleFunction toDoubleFunction = this.targetSelectionSettings.createTargetComparatorKey(new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch), ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
      Optional optional = entityTargetSelector.sortBy(toDoubleFunction).findBest();
      return optional.isPresent() && optional.get() instanceof EntityLivingBase ? (EntityLivingBase)optional.get() : null;
   }

   private boolean isRandomizationEnabled() {
      return this.randomizationStrength.getValue() > 0.0;
   }

   private boolean is2DNoRotationMode() {
      return this.noRotationFovMode.getSelectedValue().equals("2D");
   }

   private boolean isStableMode() {
      return this.mode.getSelectedValue().equals("Stable");
   }

}

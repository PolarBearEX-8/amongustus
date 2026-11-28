package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.utils.interfaces.MC;

public class SettingVisibilityRegistry implements MC {

   public static void registerAll() {
      BooleanValue intave13 = ClientManagers.moduleManager.killAura.intave13;
      intave13.setValue(!ClientManagers.moduleManager.killAura.noRotation.getValue() && ClientManagers.moduleManager.killAura.intave13.getValue());
      RangeSetting rangeSetting = ClientManagers.moduleManager.antiFireBall.yawSpeedInRotationModule;
      SettingVisibilityCondition condition = () -> {
         return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
      };
      rangeSetting.visibleWhen(condition);
      ClientManagers.moduleManager.antiFireBall.pitchSpeedInRotationModule.visibleWhen(() -> isAntiFireBallPitchSpeedVisible());
      ClientManagers.moduleManager.antiFireBall.randomizeInRotationModule.withVisibility(() -> isAntiFireBallRandomizeVisible());
      StringValue setting = ClientManagers.moduleManager.antiFireBall.randomTypeInRotationModule;
      SettingVisibilityCondition condition4 = () -> {
         return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
      };
      setting.withVisibility(condition4);
      ClientManagers.moduleManager.antiFireBall.randomStrengthInRotationModule.visibleWhen(() -> isAntiFireBallRandomStrengthVisible());
      intave13 = ClientManagers.moduleManager.antiFireBall.advancedRotsInRotationModule;
      intave13.withVisibility(() -> isAntiFireBallAdvancedRotsVisible());
      intave13 = ClientManagers.moduleManager.antiFireBall.interpolationInRotationModule;
      intave13.withVisibility(() -> isAntiFireBallInterpolationVisible());
      intave13 = ClientManagers.moduleManager.antiFireBall.slowDown;
      intave13.withVisibility(() -> isAntiFireBallSlowDownVisible());
      intave13 = ClientManagers.moduleManager.antiFireBall.moveFixInRotationModule;
      intave13.withVisibility(() -> isAntiFireBallMoveFixVisible());
      ClientManagers.moduleManager.antiFireBall.rotationModeInEntityRotationModule.withVisibility(() -> isAntiFireBallRotationModeVisible());
      ClientManagers.moduleManager.antiFireBall.hitBoxPercentageVerticalInEntityRotationModule.visibleWhen(() -> isAntiFireBallHitBoxVerticalVisible());
      ClientManagers.moduleManager.antiFireBall.hitBoxPercentageHorizontalInEntityRotationModule.visibleWhen(() -> isAntiFireBallHitBoxHorizontalVisible());
      ClientManagers.moduleManager.antiFireBall.hitBoxPercentageVerticalInEntityRotationModule1.visibleWhen(() -> isAntiFireBallAltHitBoxVerticalVisible());
      ClientManagers.moduleManager.antiFireBall.hitBoxPercentageHorizontalInEntityRotationModule1.visibleWhen(() -> isAntiFireBallAltHitBoxHorizontalVisible());
      RangeSetting rangeSetting3 = ClientManagers.moduleManager.autoWalk.yawSpeedInRotationModule;
      SettingVisibilityCondition condition15 = () -> {
         return ClientManagers.moduleManager.autoWalk.pathFinding.getValue();
      };
      rangeSetting3.visibleWhen(condition15);
      ClientManagers.moduleManager.autoWalk.pitchSpeedInRotationModule.visibleWhen(() -> isAutoWalkPitchSpeedVisible());
      StringValue setting2 = ClientManagers.moduleManager.autoWalk.randomizeInRotationModule;
      SettingVisibilityCondition condition17 = () -> {
         return ClientManagers.moduleManager.autoWalk.pathFinding.getValue();
      };
      setting2.withVisibility(condition17);
      ClientManagers.moduleManager.autoWalk.randomTypeInRotationModule.withVisibility(() -> isAutoWalkRandomTypeVisible());
      ClientManagers.moduleManager.autoWalk.randomStrengthInRotationModule.visibleWhen(() -> isAutoWalkRandomStrengthVisible());
      intave13 = ClientManagers.moduleManager.autoWalk.advancedRotsInRotationModule;
      intave13.withVisibility(() -> isAutoWalkAdvancedRotsVisible());
      intave13 = ClientManagers.moduleManager.autoWalk.interpolationInRotationModule;
      SettingVisibilityCondition condition21 = () -> {
         return ClientManagers.moduleManager.autoWalk.pathFinding.getValue();
      };
      intave13.withVisibility(condition21);
      intave13 = ClientManagers.moduleManager.autoWalk.moveFixInRotationModule;
      intave13.withVisibility(() -> false);
      String str = "None";
      ClientManagers.moduleManager.autoWalk.silentMoveFix.setSelectedValue(str);
      ClientManagers.moduleManager.timerRange.timerTicksForward.setValue(Math.max(1.0, ClientManagers.moduleManager.timerRange.timerTicksForward.getValue()));
      intave13 = ClientManagers.moduleManager.aimBot.moveFixInRotationModule;
      intave13.withVisibility(() -> false);
   }

   private static boolean isAntiFireBallHitBoxHorizontalVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAntiFireBallMoveFixVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAntiFireBallPitchSpeedVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAntiFireBallRandomizeVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAntiFireBallAltHitBoxHorizontalVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAntiFireBallRandomStrengthVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAntiFireBallRotationModeVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAutoWalkRandomTypeVisible() {
      return ClientManagers.moduleManager.autoWalk.pathFinding.getValue();
   }

   private static boolean isAntiFireBallSlowDownVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAutoWalkAdvancedRotsVisible() {
      return ClientManagers.moduleManager.autoWalk.pathFinding.getValue();
   }

   private static boolean isAntiFireBallHitBoxVerticalVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAutoWalkPitchSpeedVisible() {
      return ClientManagers.moduleManager.autoWalk.pathFinding.getValue();
   }

   private static boolean isAntiFireBallAdvancedRotsVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAntiFireBallAltHitBoxVerticalVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }

   private static boolean isAutoWalkRandomStrengthVisible() {
      return ClientManagers.moduleManager.autoWalk.pathFinding.getValue();
   }

   private static boolean isAntiFireBallInterpolationVisible() {
      return !ClientManagers.moduleManager.antiFireBall.noRotate.getValue();
   }




}

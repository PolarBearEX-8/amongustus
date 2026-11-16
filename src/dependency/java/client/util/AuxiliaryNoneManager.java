package client.util;

import client.ClientGlobals;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;

public class AuxiliaryNoneManager implements MinecraftAccessor {
   private static boolean isActive9() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive19() {
      return ClientGlobals.moduleManager.autoWalk.settingPathFinding.isEnabled();
   }

   private static boolean isActive15() {
      return false;
   }

   private static boolean isActive14() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive17() {
      return ClientGlobals.moduleManager.autoWalk.settingPathFinding.isEnabled();
   }

   public static void updateState() {
      byte byteValue = 0;
      long value = 0x737833daa77eL;
      BooleanSetting settingIntave = ClientGlobals.moduleManager.killAura.settingIntave13;
      boolean flag = !ClientGlobals.moduleManager.killAura.settingNoRotation.isEnabled() && ClientGlobals.moduleManager.killAura.settingIntave13.isEnabled();
      settingIntave.setState3(flag);
      ActionSetting actionSetting = ClientGlobals.moduleManager.antiFireBall.actionSetting;
      BooleanSupplier booleanSupplier = AuxiliaryNoneManager::isActive5;
      actionSetting.getActionSetting(booleanSupplier);
      ActionSetting actionSetting2 = ClientGlobals.moduleManager.antiFireBall.actionSetting2;
      BooleanSupplier booleanSupplier2 = AuxiliaryNoneManager::isActive7;
      actionSetting2.getActionSetting(booleanSupplier2);
      ModeSetting settingRandomize = ClientGlobals.moduleManager.antiFireBall.settingRandomize;
      BooleanSupplier booleanSupplier3 = AuxiliaryNoneManager::isActive11;
      settingRandomize.getModeSetting(booleanSupplier3);
      ModeSetting settingRandomType = ClientGlobals.moduleManager.antiFireBall.settingRandomType;
      BooleanSupplier booleanSupplier4 = AuxiliaryNoneManager::isActive13;
      settingRandomType.getModeSetting(booleanSupplier4);
      NumberSetting settingRandomStrength = ClientGlobals.moduleManager.antiFireBall.settingRandomStrength;
      BooleanSupplier booleanSupplier5 = AuxiliaryNoneManager::isActive;
      settingRandomStrength.getNumberSetting(booleanSupplier5);
      settingIntave = ClientGlobals.moduleManager.antiFireBall.settingAdvancedRots;
      BooleanSupplier booleanSupplier6 = AuxiliaryNoneManager::isActive6;
      settingIntave.getSetting(booleanSupplier6);
      settingIntave = ClientGlobals.moduleManager.antiFireBall.settingInterpolation;
      BooleanSupplier booleanSupplier7 = AuxiliaryNoneManager::isActive9;
      settingIntave.getSetting(booleanSupplier7);
      settingIntave = ClientGlobals.moduleManager.antiFireBall.settingSlowDown;
      BooleanSupplier booleanSupplier8 = AuxiliaryNoneManager::isActive21;
      settingIntave.getSetting(booleanSupplier8);
      settingIntave = ClientGlobals.moduleManager.antiFireBall.settingDrops2;
      BooleanSupplier booleanSupplier9 = AuxiliaryNoneManager::isActive2;
      settingIntave.getSetting(booleanSupplier9);
      ModeSetting settingRotationMode = ClientGlobals.moduleManager.antiFireBall.settingRotationMode;
      BooleanSupplier booleanSupplier10 = AuxiliaryNoneManager::isActive14;
      settingRotationMode.getModeSetting(booleanSupplier10);
      NumberSetting settingHitBoxPercentageVertical = ClientGlobals.moduleManager.antiFireBall.settingHitBoxPercentageVertical;
      BooleanSupplier booleanSupplier11 = AuxiliaryNoneManager::isActive3;
      settingHitBoxPercentageVertical.getNumberSetting(booleanSupplier11);
      NumberSetting settingHitBoxPercentageHorizontal = ClientGlobals.moduleManager.antiFireBall.settingHitBoxPercentageHorizontal;
      BooleanSupplier booleanSupplier12 = AuxiliaryNoneManager::isActive4;
      settingHitBoxPercentageHorizontal.getNumberSetting(booleanSupplier12);
      NumberSetting settingHitBoxPercentageVertical2 = ClientGlobals.moduleManager.antiFireBall.settingHitBoxPercentageVertical2;
      BooleanSupplier booleanSupplier13 = AuxiliaryNoneManager::isActive18;
      settingHitBoxPercentageVertical2.getNumberSetting(booleanSupplier13);
      NumberSetting settingHitBoxPercentageHorizontal2 = ClientGlobals.moduleManager.antiFireBall.settingHitBoxPercentageHorizontal2;
      BooleanSupplier booleanSupplier14 = AuxiliaryNoneManager::isActive10;
      settingHitBoxPercentageHorizontal2.getNumberSetting(booleanSupplier14);
      ActionSetting actionSetting3 = ClientGlobals.moduleManager.autoWalk.actionSetting2;
      BooleanSupplier booleanSupplier15 = AuxiliaryNoneManager::isActive17;
      actionSetting3.getActionSetting(booleanSupplier15);
      ActionSetting actionSetting4 = ClientGlobals.moduleManager.autoWalk.actionSetting;
      BooleanSupplier booleanSupplier16 = AuxiliaryNoneManager::isActive22;
      actionSetting4.getActionSetting(booleanSupplier16);
      ModeSetting settingRandomize2 = ClientGlobals.moduleManager.autoWalk.settingRandomize;
      BooleanSupplier booleanSupplier17 = AuxiliaryNoneManager::isActive19;
      settingRandomize2.getModeSetting(booleanSupplier17);
      ModeSetting settingRandomType2 = ClientGlobals.moduleManager.autoWalk.settingRandomType;
      BooleanSupplier booleanSupplier18 = AuxiliaryNoneManager::isActive8;
      settingRandomType2.getModeSetting(booleanSupplier18);
      NumberSetting settingRandomStrength2 = ClientGlobals.moduleManager.autoWalk.settingRandomStrength;
      BooleanSupplier booleanSupplier19 = AuxiliaryNoneManager::isActive20;
      settingRandomStrength2.getNumberSetting(booleanSupplier19);
      settingIntave = ClientGlobals.moduleManager.autoWalk.settingAdvancedRots;
      BooleanSupplier booleanSupplier20 = AuxiliaryNoneManager::isActiveAsFlag;
      settingIntave.getSetting(booleanSupplier20);
      settingIntave = ClientGlobals.moduleManager.autoWalk.settingInterpolation;
      BooleanSupplier booleanSupplier21 = AuxiliaryNoneManager::isActive12;
      settingIntave.getSetting(booleanSupplier21);
      settingIntave = ClientGlobals.moduleManager.autoWalk.settingMoveFix;
      BooleanSupplier booleanSupplier22 = AuxiliaryNoneManager::isActive16;
      settingIntave.getSetting(booleanSupplier22);
      String text = "None";
      ClientGlobals.moduleManager.autoWalk.modeSetting3.setSelected(text);
      NumberSetting settingTimerTicksForward = ClientGlobals.moduleManager.timerRange.settingTimerTicksForward;
      double offset = Math.max(1.0, ClientGlobals.moduleManager.timerRange.settingTimerTicksForward.getValue());
      settingTimerTicksForward.applyDouble(offset);
      settingIntave = ClientGlobals.moduleManager.aimBot.settingDrops2;
      BooleanSupplier booleanSupplier23 = AuxiliaryNoneManager::isActive15;
      settingIntave.getSetting(booleanSupplier23);
   }

   private static boolean isActive21() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   private static boolean isActive22() {
      return ClientGlobals.moduleManager.autoWalk.settingPathFinding.isEnabled();
   }

   private static boolean isActive18() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive20() {
      return ClientGlobals.moduleManager.autoWalk.settingPathFinding.isEnabled();
   }

   private static boolean isActive16() {
      return false;
   }

   private static boolean isActive3() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive13() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive12() {
      return ClientGlobals.moduleManager.autoWalk.settingPathFinding.isEnabled();
   }

   private static boolean isActive8() {
      return ClientGlobals.moduleManager.autoWalk.settingPathFinding.isEnabled();
   }

   private static boolean isActive6() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive7() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive2() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive11() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActiveAsFlag() {
      return ClientGlobals.moduleManager.autoWalk.settingPathFinding.isEnabled();
   }

   private static boolean isActive4() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive10() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }

   private static boolean isActive5() {
      return !ClientGlobals.moduleManager.antiFireBall.settingNoRotate.isEnabled();
   }
}

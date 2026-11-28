package net.augustus.modules.modes;

import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;

public class MotionModifierRuleSettings {
   public DoubleValue yAddFinish;
   public RangeSetting staticXZ;
   public BooleanValue editAddY;
   public BooleanValue finishPacket;
   public BooleanValue onlyOnGround;
   public RangeSetting multiplyY;
   public RangeSetting timerSpeed;
   public RangeSetting multiplyXZ;
   public DoubleValue yAddInLoopSecond;
   public RangeSetting staticY;
   public RangeSetting speedInAir;
   public DoubleValue motion;
   public BooleanValue editMultiplyXZ;
   public RangeSetting addXZ;
   public BooleanValue blockMovementInput;
   public BooleanValue editTimerSpeed;
   public BooleanValue onlyAtLadder;
   public BooleanValue spoofGround;
   public BooleanValue resetAtOppositeState;
   public BooleanValue onlyInWeb;
   public BooleanValue editStaticY;
   public BooleanValue editSpeedInAir;
   public RangeSetting addY;
   public DoubleValue setPositionYAdd;
   public StringValue selfDamageMode;
   public BooleanValue onlyInWater;
   public BooleanValue editMultiplyY;
   public RangeSetting resetTicksAt;
   public BooleanValue notWhileKB;
   public BooleanValue editTick;
   public BooleanValue editAddXZ;
   public BooleanValue jump;
   public BooleanValue secondPacket;
   public DoubleValue yAddInLoopFirst;
   public BooleanValue editStaticXZ;
   public DoubleValue loops;
   public DoubleValue strafePercentage;

   private boolean isYAddInLoopFirstVisible() {
      return this.selfDamageMode.getSelectedValue().equals("Custom");
   }

   private boolean isEditAddXZVisible() {
      return this.editTick.getValue();
   }

   private boolean isAddXZRangeVisible() {
      return this.editAddXZ.getValue() && this.editTick.getValue();
   }

   private boolean isBlockMovementInputVisible() {
      return this.selfDamageMode.getSelectedValue().equals("3Jump");
   }

   private boolean isStrafePercentageVisible() {
      return this.editTick.getValue();
   }

   private boolean isYAddFinishVisible() {
      return this.selfDamageMode.getSelectedValue().equals("Custom") && this.finishPacket.getValue();
   }

   private boolean isEditSpeedInAirVisible() {
      return this.editTick.getValue();
   }

   private boolean isTickSelfDamageModeVisible() {
      return this.editTick.getValue();
   }

   private boolean isTickYAddFinishVisible() {
      return this.editTick.getValue() && this.selfDamageMode.getSelectedValue().equals("Custom") && this.finishPacket.getValue();
   }

   private boolean isEditTimerSpeedVisible() {
      return this.editTick.getValue();
   }

   private boolean isNotWhileKBVisible() {
      return this.editTick.getValue();
   }

   private boolean isSetPositionYAddVisible() {
      return this.selfDamageMode.getSelectedValue().equals("SetPosition");
   }

   private boolean isSecondPacketVisible() {
      return this.selfDamageMode.getSelectedValue().equals("Custom");
   }

   private boolean isAddYRangeVisible() {
      return this.editAddY.getValue() && this.editTick.getValue();
   }

   private boolean isOnlyAtLadderVisible() {
      return this.editTick.getValue();
   }

   public MotionModifierRuleSettings(Module module, String str, SettingGroup doubleValue, double d) {
       super();
      this.editSpeedInAir = null;
      this.selfDamageMode = (StringValue)new StringValue(
         module, str + "SelfDamageMode", "None", new String[]{"None", "Motion", "Packet1", "Packet2", "Custom", "Fake", "SetPosition", "3Jump"}, doubleValue
      ).withDisplayName("SelfDamageMode");
      this.onlyOnGround = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "OnlyOnGround", false, doubleValue).withDisplayName("OnlyOnGround")).withVisibility(() -> this.isOnlyOnGroundVisible());
      this.blockMovementInput = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "BlockMovementInput", true, doubleValue).withDisplayName("BlockMovementInput")).withVisibility(() -> this.isBlockMovementInputVisible());
      this.setPositionYAdd = new DoubleValue(module, str + "SetPositionYAdd", 3.1, -4.0, 4.0, 4, doubleValue).named("YAdd").visibleWhen(() -> this.isSetPositionYAddVisible());
      DoubleValue doubleSetting = new DoubleValue(module, str + "Motion", 5.0, 0.0, 10.0, 2, doubleValue).named("Motion");
      SettingVisibilityCondition condition4 = () -> {
         return this.selfDamageMode.getSelectedValue().equals("Motion");
      };
      this.motion = doubleSetting.visibleWhen(condition4);
      this.loops = new DoubleValue(module, str + "Loops", 51.0, 1.0, 100.0, 0, doubleValue).named("Loops").visibleWhen(() -> this.isSelfDamageLoopsVisible());
      this.yAddInLoopFirst = new DoubleValue(module, str + "YAddInLoopFirst", 0.02, 0.0, 1.0, 4, doubleValue).named("YAddInLoopFirst").visibleWhen(() -> this.isYAddInLoopFirstVisible());
      this.secondPacket = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "SecondPacket", true, doubleValue).withDisplayName("SecondPacket")).withVisibility(() -> this.isSecondPacketVisible());
      this.yAddInLoopSecond = new DoubleValue(module, str + "YAddInLoopSecond", 0.02, 0.0, 1.0, 4, doubleValue).named("YAddInLoopSecond").visibleWhen(() -> this.isYAddInLoopSecondVisible());
      this.finishPacket = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "FinishPacket", true, doubleValue).withDisplayName("FinishPacket")).withVisibility(() -> this.isFinishPacketVisible());
      this.yAddFinish = new DoubleValue(module, str + "YAddFinish", 0.0, 0.0, 1.0, 4, doubleValue).named("YAddFinish").visibleWhen(() -> this.isYAddFinishVisible());
   }

   private boolean isSpeedInAirRangeVisible() {
      return this.editTick.getValue() && this.editSpeedInAir.getValue();
   }

   private boolean isFinishPacketVisible() {
      return this.selfDamageMode.getSelectedValue().equals("Custom");
   }

   private boolean isEditStaticYVisible() {
      return this.editTick.getValue();
   }

   private boolean isEditAddYVisible() {
      return this.editTick.getValue();
   }

   private boolean isTickSelfDamageMotionVisible() {
      return this.editTick.getValue() && this.selfDamageMode.getSelectedValue().equals("Motion");
   }

   private boolean isEditMultiplyXZVisible() {
      return this.editTick.getValue();
   }

   private boolean isStaticXZRangeVisible() {
      return this.editStaticXZ.getValue() && this.editTick.getValue();
   }

   public MotionModifierRuleSettings(Module module, String str, SettingGroup doubleValue) {
       super();
      this.editSpeedInAir = null;
      RangeSetting rangeSetting = new RangeSetting(module, str + "ResetTicksAt", 0.0, 59.0, 0.0, 59.0, 0, doubleValue);
      String str2 = "ResetTicksTo";
      this.resetTicksAt = rangeSetting.withBoundNames("ResetTicksFrom", str2);
      this.resetAtOppositeState = (BooleanValue)new BooleanValue(module, str + "ResetAtOppositeState", true, doubleValue).withDisplayName("ResetAtOppositeState");
   }

   private boolean isEditStaticXZVisible() {
      return this.editTick.getValue();
   }

   public MotionModifierRuleSettings(Module module, String str, SettingGroup doubleValue, boolean flag) {
       super();
      this.editSpeedInAir = null;
      this.editTick = (BooleanValue)new BooleanValue(module, str + "EditTick", false, doubleValue).withDisplayName("EditTick");
      this.notWhileKB = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "NotWhileKB", false, doubleValue).withDisplayName("NotWhileKB")).withVisibility(() -> this.isNotWhileKBVisible());
      BooleanValue booleanValue5 = (BooleanValue)new BooleanValue(module, str + "OnlyInWater", false, doubleValue).withDisplayName("OnlyInWater");
      SettingVisibilityCondition condition2 = () -> {
         return this.editTick.getValue();
      };
      this.onlyInWater = (BooleanValue)booleanValue5.withVisibility(condition2);
      BooleanValue booleanValue7 = (BooleanValue)new BooleanValue(module, str + "OnlyInWeb", false, doubleValue).withDisplayName("OnlyInWeb");
      SettingVisibilityCondition condition3 = () -> {
         return this.editTick.getValue();
      };
      this.onlyInWeb = (BooleanValue)booleanValue7.withVisibility(condition3);
      this.onlyAtLadder = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "OnlyAtLadder", false, doubleValue).withDisplayName("OnlyAtLadder")).withVisibility(() -> this.isOnlyAtLadderVisible());
      BooleanValue booleanValue11 = (BooleanValue)new BooleanValue(module, str + "Jump", false, doubleValue).withDisplayName("Jump");
      SettingVisibilityCondition condition5 = () -> {
         return this.editTick.getValue();
      };
      this.jump = (BooleanValue)booleanValue11.withVisibility(condition5);
      BooleanValue booleanValue12 = new BooleanValue(module, str + "SpoofGround", false, doubleValue);
      booleanValue12 = (BooleanValue)booleanValue12.withDisplayName("SpoofGround");
      this.spoofGround = (BooleanValue)booleanValue12.withVisibility(() -> this.isSpoofGroundVisible());
      booleanValue12 = new BooleanValue(module, str + "EditStaticY", false, doubleValue);
      booleanValue12 = (BooleanValue)booleanValue12.withDisplayName("EditStaticY");
      this.editStaticY = (BooleanValue)booleanValue12.withVisibility(() -> this.isEditStaticYVisible());
      RangeSetting rangeSetting = new RangeSetting(module, str + "StaticY", 0.42, 0.42, 0.0, 1.0, 4, doubleValue);
      String str10 = "MaxStaticY";
      this.staticY = rangeSetting.withBoundNames("MinStaticY", str10).visibleWhen(() -> this.isStaticYRangeVisible());
      this.editStaticXZ = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "EditStaticXZ", false, doubleValue).withDisplayName("EditStaticXZ")).withVisibility(() -> this.isEditStaticXZVisible());
      RangeSetting rangeSetting3 = new RangeSetting(module, str + "StaticXZ", 0.2, 0.2, 0.0, 1.0, 4, doubleValue);
      String str13 = "MaxStaticXZ";
      this.staticXZ = rangeSetting3.withBoundNames("MinStaticXZ", str13).visibleWhen(() -> this.isStaticXZRangeVisible());
      this.editAddY = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "EditAddY", false, doubleValue).withDisplayName("EditAddY")).withVisibility(() -> this.isEditAddYVisible());
      RangeSetting rangeSetting5 = new RangeSetting(module, str + "AddY", 0.02, 0.02, -1.0, 1.0, 4, doubleValue);
      String str16 = "MaxAddY";
      this.addY = rangeSetting5.withBoundNames("MinAddY", str16).visibleWhen(() -> this.isAddYRangeVisible());
      this.editAddXZ = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "EditAddXZ", false, doubleValue).withDisplayName("EditAddXZ")).withVisibility(() -> this.isEditAddXZVisible());
      RangeSetting rangeSetting7 = new RangeSetting(module, str + "AddXZ", 0.02, 0.02, -1.0, 1.0, 4, doubleValue);
      String str19 = "MaxAddXZ";
      this.addXZ = rangeSetting7.withBoundNames("MinAddXZ", str19).visibleWhen(() -> this.isAddXZRangeVisible());
      BooleanValue booleanValue20 = (BooleanValue)new BooleanValue(module, str + "EditMultiplyY", false, doubleValue).withDisplayName("EditMultiplyY");
      SettingVisibilityCondition condition15 = () -> {
         return this.editTick.getValue();
      };
      this.editMultiplyY = (BooleanValue)booleanValue20.withVisibility(condition15);
      RangeSetting rangeSetting9 = new RangeSetting(module, str + "MultiplyY", 1.1, 1.1, 0.0, 2.0, 4, doubleValue);
      String str22 = "MaxMultiplyY";
      this.multiplyY = rangeSetting9.withBoundNames("MinMultiplyY", str22).visibleWhen(() -> this.isMultiplyYRangeVisible());
      this.editMultiplyXZ = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "EditMultiplyXZ", false, doubleValue).withDisplayName("EditMultiplyXZ")).withVisibility(() -> this.isEditMultiplyXZVisible());
      RangeSetting rangeSetting11 = new RangeSetting(module, str + "MultiplyXZ", 1.1, 1.1, 0.0, 2.0, 4, doubleValue);
      String str25 = "MaxMultiplyXZ";
      this.multiplyXZ = rangeSetting11.withBoundNames("MinMultiplyXZ", str25).visibleWhen(() -> this.isMultiplyXZRangeVisible());
      if (flag) {
         this.editSpeedInAir = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "EditSpeedInAir", false, doubleValue).withDisplayName("EditSpeedInAir")).withVisibility(() -> this.isEditSpeedInAirVisible());
         RangeSetting rangeSetting13 = new RangeSetting(module, str + "SpeedInAir", 0.02, 0.02, 0.0, 1.0, 6, doubleValue);
         String str28 = "MaxSpeedInAir";
         this.speedInAir = rangeSetting13.withBoundNames("MinSpeedInAir", str28).visibleWhen(() -> this.isSpeedInAirRangeVisible());
      }

      this.editTimerSpeed = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "EditTimerSpeed", false, doubleValue).withDisplayName("EditTimerSpeed")).withVisibility(() -> this.isEditTimerSpeedVisible());
      RangeSetting rangeSetting15 = new RangeSetting(module, str + "TimerSpeed", 1.0, 1.0, 0.1, 4.0, 4, doubleValue);
      String str31 = "MaxTimerSpeed";
      RangeSetting rangeSetting16 = rangeSetting15.withBoundNames("MinTimerSpeed", str31);
      SettingVisibilityCondition condition22 = () -> {
         return this.editTick.getValue() && this.editTimerSpeed.getValue();
      };
      this.timerSpeed = rangeSetting16.visibleWhen(condition22);
      this.strafePercentage = new DoubleValue(module, str + "StrafePercentage", 0.0, 0.0, 100.0, 0, doubleValue).named("StrafePercentage").visibleWhen(() -> this.isStrafePercentageVisible());
      StringValue setting = (StringValue)new StringValue(
         module, str + "SelfDamageMode", "None", new String[]{"None", "Motion", "Packet1", "Packet2", "Custom", "Fake", "SetPosition"}, doubleValue
      ).withDisplayName("SelfDamageMode");
      this.selfDamageMode = (StringValue)setting.withVisibility(() -> this.isTickSelfDamageModeVisible());
      this.setPositionYAdd = new DoubleValue(module, str + "SetPositionYAdd", 3.1, -4.0, 4.0, 4, doubleValue).named("YAdd").visibleWhen(() -> this.isTickSetPositionYAddVisible());
      this.motion = new DoubleValue(module, str + "Motion", 5.0, 0.0, 10.0, 2, doubleValue).named("Motion").visibleWhen(() -> this.isTickSelfDamageMotionVisible());
      DoubleValue doubleSetting = new DoubleValue(module, str + "Loops", 51.0, 1.0, 100.0, 0, doubleValue).named("Loops");
      SettingVisibilityCondition condition27 = () -> {
         return this.editTick.getValue() && this.selfDamageMode.getSelectedValue().equals("Custom");
      };
      this.loops = doubleSetting.visibleWhen(condition27);
      this.yAddInLoopFirst = new DoubleValue(module, str + "YAddInLoopFirst", 0.02, 0.0, 1.0, 4, doubleValue).named("YAddInLoopFirst").visibleWhen(() -> this.isTickYAddInLoopFirstVisible());
      this.secondPacket = (BooleanValue)((BooleanValue)new BooleanValue(module, str + "SecondPacket", true, doubleValue).withDisplayName("SecondPacket")).withVisibility(() -> this.isTickSecondPacketVisible());
      this.yAddInLoopSecond = new DoubleValue(module, str + "YAddInLoopSecond", 0.02, 0.0, 1.0, 4, doubleValue).named("YAddInLoopSecond").visibleWhen(() -> this.isTickYAddInLoopSecondVisible());
      BooleanValue booleanValue30 = (BooleanValue)new BooleanValue(module, str + "FinishPacket", true, doubleValue).withDisplayName("FinishPacket");
      SettingVisibilityCondition condition31 = () -> {
         return this.editTick.getValue() && this.selfDamageMode.getSelectedValue().equals("Custom");
      };
      this.finishPacket = (BooleanValue)booleanValue30.withVisibility(condition31);
      this.yAddFinish = new DoubleValue(module, str + "YAddFinish", 0.0, 0.0, 1.0, 4, doubleValue).named("YAddFinish").visibleWhen(() -> this.isTickYAddFinishVisible());
   }

   private boolean isSpoofGroundVisible() {
      return this.editTick.getValue();
   }

   private boolean isMultiplyXZRangeVisible() {
      return this.editMultiplyXZ.getValue() && this.editTick.getValue();
   }

   private boolean isSelfDamageLoopsVisible() {
      return this.selfDamageMode.getSelectedValue().equals("Custom");
   }

   private boolean isTickSecondPacketVisible() {
      return this.editTick.getValue() && this.selfDamageMode.getSelectedValue().equals("Custom");
   }

   private boolean isOnlyOnGroundVisible() {
      return !this.selfDamageMode.getSelectedValue().equals("3Jump") && !this.selfDamageMode.getSelectedValue().equals("None");
   }

   private boolean isTickYAddInLoopSecondVisible() {
      return this.editTick.getValue() && this.selfDamageMode.getSelectedValue().equals("Custom") && this.secondPacket.getValue();
   }

   private boolean isTickYAddInLoopFirstVisible() {
      return this.editTick.getValue() && this.selfDamageMode.getSelectedValue().equals("Custom");
   }

   private boolean isTickSetPositionYAddVisible() {
      return this.editTick.getValue() && this.selfDamageMode.getSelectedValue().equals("SetPosition");
   }

   private boolean isYAddInLoopSecondVisible() {
      return this.selfDamageMode.getSelectedValue().equals("Custom") && this.secondPacket.getValue();
   }

   private boolean isMultiplyYRangeVisible() {
      return this.editMultiplyY.getValue() && this.editTick.getValue();
   }

   private boolean isStaticYRangeVisible() {
      return this.editStaticY.getValue() && this.editTick.getValue();
   }

}

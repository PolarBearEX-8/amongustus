package client.util;

import client.module.Module;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import java.util.HashMap;

public class SetPositionManager {
   public BooleanSetting booleanSetting4;
   public BooleanSetting settingResetAtOppositeState;
   public ActionSetting actionSetting5;
   public NumberSetting numberSetting;
   public NumberSetting settingYAddFinish;
   public NumberSetting settingYAdd;
   public ActionSetting actionSetting;
   public BooleanSetting booleanSetting14;
   public ModeSetting settingSelfDamageMode;
   public BooleanSetting settingOnlyOnGround;
   public NumberSetting settingYAddInLoopFirst;
   public BooleanSetting booleanSetting10;
   public ActionSetting actionSetting2;
   public BooleanSetting booleanSetting13;
   public NumberSetting settingMotion;
   public BooleanSetting booleanSetting7;
   public BooleanSetting booleanSetting2;
   public ActionSetting actionSetting6;
   public BooleanSetting booleanSetting;
   public BooleanSetting booleanSetting5;
   public NumberSetting settingLoops;
   public BooleanSetting booleanSetting9;
   public NumberSetting settingYAddInLoopSecond;
   public ActionSetting actionSetting7;
   public BooleanSetting booleanSetting12;
   public BooleanSetting booleanSetting8;
   public BooleanSetting settingEditTick;
   public BooleanSetting settingBlockMovementInput;
   public BooleanSetting booleanSetting3;
   public BooleanSetting settingFinishPacket;
   public ActionSetting actionSetting4;
   public ActionSetting actionSetting9;
   public BooleanSetting booleanSetting11;
   public ActionSetting actionSetting3;
   public BooleanSetting booleanSetting6;
   public BooleanSetting settingSecondPacket;
   public ActionSetting actionSetting8;

   private boolean isActive38() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive2() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActiveAsFlag() {
      return this.settingSelfDamageMode.getSelected().equals("SetPosition");
   }

   private boolean isActive() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive4() {
      return this.settingEditTick.isEnabled() && this.settingSelfDamageMode.getSelected().equals("Custom");
   }

   private boolean isActive40() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive41() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive37() {
      return this.booleanSetting.isEnabled() && this.settingEditTick.isEnabled();
   }

   private boolean isActive39() {
      return this.booleanSetting4.isEnabled() && this.settingEditTick.isEnabled();
   }

   private void applyHashMapByHashMap(HashMap map, Setting setting) {
      map.put(setting.getString(), setting);
   }

   private boolean isActive3() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive32() {
      return this.booleanSetting13.isEnabled() && this.settingEditTick.isEnabled();
   }

   private boolean isActive35() {
      return this.settingEditTick.isEnabled() && this.booleanSetting3.isEnabled();
   }

   private boolean isActive33() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive30() {
      return this.settingSelfDamageMode.getSelected().equals("Custom") && this.settingSecondPacket.isEnabled();
   }

   private boolean isActive7() {
      return this.settingSelfDamageMode.getSelected().equals("3Jump");
   }

   private boolean isActive20() {
      return this.settingSelfDamageMode.getSelected().equals("Custom");
   }

   private boolean isActive24() {
      return this.settingSelfDamageMode.getSelected().equals("Custom");
   }

   private boolean isActive6() {
      return this.booleanSetting12.isEnabled() && this.settingEditTick.isEnabled();
   }

   private boolean isActive14() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive27() {
      return this.settingEditTick.isEnabled() && this.settingSelfDamageMode.getSelected().equals("Custom");
   }

   private boolean isActive5() {
      return this.booleanSetting2.isEnabled() && this.settingEditTick.isEnabled();
   }

   private boolean isActive34() {
      return this.settingEditTick.isEnabled() && this.settingSelfDamageMode.getSelected().equals("Custom") && this.settingFinishPacket.isEnabled();
   }

   private boolean isActive31() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive19() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive16() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive25() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive21() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive12() {
      return this.settingEditTick.isEnabled() && this.settingSelfDamageMode.getSelected().equals("SetPosition");
   }

   private boolean isActive8() {
      return this.settingEditTick.isEnabled() && this.booleanSetting10.isEnabled();
   }

   private boolean isActive22() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive36() {
      return this.settingEditTick.isEnabled() && this.settingSelfDamageMode.getSelected().equals("Custom") && this.settingSecondPacket.isEnabled();
   }

   private boolean isActive17() {
      return this.booleanSetting7.isEnabled() && this.settingEditTick.isEnabled();
   }

   private boolean isActive15() {
      return !this.settingSelfDamageMode.getSelected().equals("3Jump") && !this.settingSelfDamageMode.getSelected().equals("None");
   }

   private boolean isActive23() {
      return this.settingEditTick.isEnabled() && this.settingSelfDamageMode.getSelected().equals("Motion");
   }

   private boolean isActive13() {
      return this.settingSelfDamageMode.getSelected().equals("Motion");
   }

   private boolean isActive10() {
      return this.settingEditTick.isEnabled();
   }

   private boolean isActive18() {
      return this.settingSelfDamageMode.getSelected().equals("Custom");
   }

   private boolean isActive26() {
      return this.settingSelfDamageMode.getSelected().equals("Custom");
   }

   private boolean isActive29() {
      return this.settingEditTick.isEnabled() && this.settingSelfDamageMode.getSelected().equals("Custom");
   }

   private boolean isActive9() {
      return this.settingSelfDamageMode.getSelected().equals("Custom") && this.settingFinishPacket.isEnabled();
   }

   private boolean isActive28() {
      return this.settingEditTick.isEnabled() && this.settingSelfDamageMode.getSelected().equals("Custom");
   }

   private boolean isActive11() {
      return this.settingEditTick.isEnabled();
   }

   public SetPositionManager(Module module, String text, SettingGroup settingGroup, HashMap map) {
      this.booleanSetting10 = null;
      ActionSetting setting = new ActionSetting(module, text + "ResetTicksAt", 0.0, 59.0, 0.0, 59.0, 0, settingGroup);
      String text2 = "ResetTicksTo";
      String text3 = "ResetTicksFrom";
      this.actionSetting8 = setting.getActionSetting(text3, text2);
      BooleanSetting setting2 = new BooleanSetting(module, text + "ResetAtOppositeState", true, settingGroup);
      String text4 = "ResetAtOppositeState";
      this.settingResetAtOppositeState = (BooleanSetting)setting2.getSetting(text4);
      if (module instanceof AuxiliaryModuleAdapter) {
         ActionSetting actionSetting10 = this.actionSetting8;
         this.applyHashMapByHashMap(map, actionSetting10);
         BooleanSetting settingResetAtOppositeState2 = this.settingResetAtOppositeState;
         this.applyHashMapByHashMap(map, settingResetAtOppositeState2);
      }
   }

   public SetPositionManager(Module module, String text, SettingGroup settingGroup, boolean flag, HashMap map) {
      this.booleanSetting10 = null;
      BooleanSetting setting = new BooleanSetting(module, text + "EditTick", false, settingGroup);
      String text2 = "EditTick";
      this.settingEditTick = (BooleanSetting)setting.getSetting(text2);
      if (module instanceof AuxiliaryModuleAdapter) {
         ModeSetting settingSelfDamageMode2 = this.settingSelfDamageMode;
         this.applyHashMapByHashMap(map, settingSelfDamageMode2);
         NumberSetting settingYAdd2 = this.settingYAdd;
         this.applyHashMapByHashMap(map, settingYAdd2);
         NumberSetting settingMotion2 = this.settingMotion;
         this.applyHashMapByHashMap(map, settingMotion2);
         NumberSetting settingLoops2 = this.settingLoops;
         this.applyHashMapByHashMap(map, settingLoops2);
         NumberSetting settingYAddInLoopFirst2 = this.settingYAddInLoopFirst;
         this.applyHashMapByHashMap(map, settingYAddInLoopFirst2);
         BooleanSetting settingSecondPacket2 = this.settingSecondPacket;
         this.applyHashMapByHashMap(map, settingSecondPacket2);
         NumberSetting settingYAddInLoopSecond2 = this.settingYAddInLoopSecond;
         this.applyHashMapByHashMap(map, settingYAddInLoopSecond2);
         BooleanSetting settingFinishPacket2 = this.settingFinishPacket;
         this.applyHashMapByHashMap(map, settingFinishPacket2);
         NumberSetting settingYAddFinish2 = this.settingYAddFinish;
         this.applyHashMapByHashMap(map, settingYAddFinish2);
      }

      String[] textArray = new String[3];
   }

   public SetPositionManager(Module module, String text, SettingGroup settingGroup, double offset, HashMap map) {
        super();
      
      this.booleanSetting10 = null;
      ModeSetting setting = new ModeSetting(
         module, text + "SelfDamageMode", "None", new String[]{"None", "Motion", "Packet1", "Packet2", "Custom", "Fake", "SetPosition", "3Jump"}, settingGroup
      );
      String text2 = "SelfDamageMode";
      this.settingSelfDamageMode = setting.getModeSetting(text2);
      BooleanSetting setting2 = new BooleanSetting(module, text + "OnlyOnGround", false, settingGroup);
      String text3 = "OnlyOnGround";
      BooleanSetting setting3 = (BooleanSetting)setting2.getSetting(text3);
      BooleanSupplier booleanSupplier = this::isActive15;
      this.settingOnlyOnGround = (BooleanSetting)setting3.getSetting(booleanSupplier);
      BooleanSetting setting4 = new BooleanSetting(module, text + "BlockMovementInput", true, settingGroup);
      String text4 = "BlockMovementInput";
      BooleanSetting setting5 = (BooleanSetting)setting4.getSetting(text4);
      BooleanSupplier booleanSupplier2 = this::isActive7;
      this.settingBlockMovementInput = (BooleanSetting)setting5.getSetting(booleanSupplier2);
      NumberSetting setting6 = new NumberSetting(module, text + "SetPositionYAdd", 3.1, -4.0, 4.0, 4, settingGroup);
      String text5 = "YAdd";
      setting6 = setting6.getNumberSetting(text5);
      BooleanSupplier booleanSupplier3 = this::isActiveAsFlag;
      this.settingYAdd = setting6.getNumberSetting(booleanSupplier3);
      NumberSetting setting7 = new NumberSetting(module, text + "Motion", 5.0, 0.0, 10.0, 2, settingGroup);
      String text6 = "Motion";
      NumberSetting numberSetting2 = setting7.getNumberSetting(text6);
      BooleanSupplier booleanSupplier4 = this::isActive13;
      this.settingMotion = numberSetting2.getNumberSetting(booleanSupplier4);
      NumberSetting setting8 = new NumberSetting(module, text + "Loops", 51.0, 1.0, 100.0, 0, settingGroup);
      String text7 = "Loops";
      NumberSetting numberSetting3 = setting8.getNumberSetting(text7);
      BooleanSupplier booleanSupplier5 = this::isActive26;
      this.settingLoops = numberSetting3.getNumberSetting(booleanSupplier5);
      NumberSetting setting9 = new NumberSetting(module, text + "YAddInLoopFirst", 0.02, 0.0, 1.0, 4, settingGroup);
      String text8 = "YAddInLoopFirst";
      NumberSetting numberSetting4 = setting9.getNumberSetting(text8);
      BooleanSupplier booleanSupplier6 = this::isActive24;
      this.settingYAddInLoopFirst = numberSetting4.getNumberSetting(booleanSupplier6);
      BooleanSetting setting10 = new BooleanSetting(module, text + "SecondPacket", true, settingGroup);
      String text9 = "SecondPacket";
      BooleanSetting setting11 = (BooleanSetting)setting10.getSetting(text9);
      BooleanSupplier booleanSupplier7 = this::isActive18;
      this.settingSecondPacket = (BooleanSetting)setting11.getSetting(booleanSupplier7);
      NumberSetting setting12 = new NumberSetting(module, text + "YAddInLoopSecond", 0.02, 0.0, 1.0, 4, settingGroup);
      String text10 = "YAddInLoopSecond";
      NumberSetting numberSetting5 = setting12.getNumberSetting(text10);
      BooleanSupplier booleanSupplier8 = this::isActive30;
      this.settingYAddInLoopSecond = numberSetting5.getNumberSetting(booleanSupplier8);
      BooleanSetting setting13 = new BooleanSetting(module, text + "FinishPacket", true, settingGroup);
      String text11 = "FinishPacket";
      BooleanSetting setting14 = (BooleanSetting)setting13.getSetting(text11);
      BooleanSupplier booleanSupplier9 = this::isActive20;
      this.settingFinishPacket = (BooleanSetting)setting14.getSetting(booleanSupplier9);
      NumberSetting setting15 = new NumberSetting(module, text + "YAddFinish", 0.0, 0.0, 1.0, 4, settingGroup);
      String text12 = "YAddFinish";
      NumberSetting numberSetting6 = setting15.getNumberSetting(text12);
      BooleanSupplier booleanSupplier10 = this::isActive9;
      this.settingYAddFinish = numberSetting6.getNumberSetting(booleanSupplier10);
      if (module instanceof AuxiliaryModuleAdapter) {
         ModeSetting settingSelfDamageMode2 = this.settingSelfDamageMode;
         this.applyHashMapByHashMap(map, settingSelfDamageMode2);
         BooleanSetting settingOnlyOnGround2 = this.settingOnlyOnGround;
         this.applyHashMapByHashMap(map, settingOnlyOnGround2);
         BooleanSetting settingBlockMovementInput2 = this.settingBlockMovementInput;
         this.applyHashMapByHashMap(map, settingBlockMovementInput2);
         NumberSetting settingYAdd2 = this.settingYAdd;
         this.applyHashMapByHashMap(map, settingYAdd2);
         NumberSetting settingMotion2 = this.settingMotion;
         this.applyHashMapByHashMap(map, settingMotion2);
         NumberSetting settingLoops2 = this.settingLoops;
         this.applyHashMapByHashMap(map, settingLoops2);
         NumberSetting settingYAddInLoopFirst2 = this.settingYAddInLoopFirst;
         this.applyHashMapByHashMap(map, settingYAddInLoopFirst2);
         BooleanSetting settingSecondPacket2 = this.settingSecondPacket;
         this.applyHashMapByHashMap(map, settingSecondPacket2);
         NumberSetting settingYAddInLoopSecond2 = this.settingYAddInLoopSecond;
         this.applyHashMapByHashMap(map, settingYAddInLoopSecond2);
         BooleanSetting settingFinishPacket2 = this.settingFinishPacket;
         this.applyHashMapByHashMap(map, settingFinishPacket2);
         NumberSetting settingYAddFinish2 = this.settingYAddFinish;
         this.applyHashMapByHashMap(map, settingYAddFinish2);
      }
   }
}

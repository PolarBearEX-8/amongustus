package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.UnaryFunction;
import net.augustus.modules.Module;
import net.augustus.modules.combat.KillAura;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.interfaces.MC;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public enum MoreKbModeOption implements SelectableOption {
   S_TAP("STap", obj -> {
      MC.mc.thePlayer.sprintResetSTap = true;
      return false;
   }),
   LEGIT_SNEAK("LegitSneak", obj -> {
      if ((Boolean)obj) {
         MC.mc.thePlayer.sprintResetWTap = true;
      }
      return false;
   }),
   LEGIT("Legit", true, obj -> {
      MC.mc.thePlayer.sprintResetWTap = true;
      return false;
   }),
   LEGIT_FAST("LegitFast", obj -> {
      MC.mc.thePlayer.sprintResetSilent = true;
      return false;
   }),
   FAST("Fast", obj -> {
      MC.mc.thePlayer.setForceStopSprint(true);
      return false;
   }),
   LESS_PACKET("LessPacket", obj -> {
      if (MC.mc.thePlayer.isSprinting()) {
         MC.mc.thePlayer.setSprinting(false);
      }
      MC.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(MC.mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
      MC.mc.thePlayer.setServerSprintState(true);
      return true;
   }),
   PACKET("Packet", obj -> {
      MC.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(MC.mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
      MC.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(MC.mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
      MC.mc.thePlayer.setServerSprintState(true);
      return true;
   }),
   DOUBLE_PACKET("DoublePacket", obj -> {
      MC.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(MC.mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
      MC.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(MC.mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
      MC.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(MC.mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
      MC.mc.thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(MC.mc.thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
      MC.mc.thePlayer.setServerSprintState(true);
      return true;
   }),
   ONE("One", obj -> {
      MC.mc.thePlayer.setServerSprintState(false);
      return false;
   });
   private final String name;
   private boolean selected = false;
   private SettingGroup settingsGroup;
   private DoubleValue chanceSetting;
   private UnaryFunction sprintResetAction;

   private MoreKbModeOption(String str, UnaryFunction unaryFunction) {
      this.name = str;
      this.sprintResetAction = unaryFunction;
   }

   private MoreKbModeOption(String str, boolean flag, UnaryFunction unaryFunction) {
      this.name = str;
      this.selected = flag;
      this.sprintResetAction = unaryFunction;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   private void initSettings(Module module, SettingGroup doubleValue) {
      this.settingsGroup = new SettingGroup(module, this.name + "Settings", true, doubleValue).visibleWhen(this::isSelected);
      this.chanceSetting = new DoubleValue(module, this.name + "Chance", 100.0, 0.0, 100.0, 0, this.settingsGroup).named("Chance");
   }

   public static void initOptionSettings(MoreKbModeOption moreKbModeOption, Module module, SettingGroup doubleValue) {
      moreKbModeOption.initSettings(module, doubleValue);
   }

   public static SettingGroup getSettingsGroup(MoreKbModeOption moreKbModeOption) {
      return moreKbModeOption.settingsGroup;
   }

   public static UnaryFunction getSprintResetAction(MoreKbModeOption moreKbModeOption) {
      return moreKbModeOption.sprintResetAction;
   }

   public static DoubleValue getChanceSetting(MoreKbModeOption moreKbModeOption) {
      return moreKbModeOption.chanceSetting;
   }

}

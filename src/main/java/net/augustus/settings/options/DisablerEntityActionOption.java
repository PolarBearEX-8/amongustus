package net.augustus.settings.options;

import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public enum DisablerEntityActionOption implements SelectableOption {
   START_SPRINT("StartSprint", C0BPacketEntityAction.Action.START_SPRINTING),
   STOP_SPRINT("StopSprint", C0BPacketEntityAction.Action.STOP_SPRINTING),
   START_SNEAKING("StartSneaking", C0BPacketEntityAction.Action.START_SNEAKING),
   STOP_SNEAKING("StopSneaking", C0BPacketEntityAction.Action.STOP_SNEAKING),
   STOP_SLEEPING("StopSleeping", C0BPacketEntityAction.Action.STOP_SLEEPING),
   RIDING_JUMP("RidingJump", C0BPacketEntityAction.Action.RIDING_JUMP),
   OPEN_INVENTORY("OpenInventory", C0BPacketEntityAction.Action.OPEN_INVENTORY);

private String name;
   private C0BPacketEntityAction.Action action;
   private boolean selected;
   private DoubleValue intervalSetting;
   private BooleanValue invertIntervalSetting;
   private BooleanValue alwaysSetting;
   private StringValue cancelConditionSetting;
   private RangeSetting tickRangeSetting;
   private int packetCount = 0;
private DisablerEntityActionOption(String str, C0BPacketEntityAction.Action action2) {
      this.name = str;
      this.action = action2;
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

   public static String getOptionName(DisablerEntityActionOption option) {
      return option.name;
   }

   public static DoubleValue setIntervalSetting(DisablerEntityActionOption option, DoubleValue stringValue) {
      return option.intervalSetting = stringValue;
   }

   public static BooleanValue setInvertIntervalSetting(DisablerEntityActionOption option, BooleanValue booleanValue) {
      return option.invertIntervalSetting = booleanValue;
   }

   public static BooleanValue setAlwaysSetting(DisablerEntityActionOption option, BooleanValue booleanValue) {
      return option.alwaysSetting = booleanValue;
   }

   public static RangeSetting setTickRangeSetting(DisablerEntityActionOption option, RangeSetting rangeSetting) {
      return option.tickRangeSetting = rangeSetting;
   }

   public static StringValue setCancelConditionSetting(DisablerEntityActionOption option, StringValue booleansSetting) {
      return option.cancelConditionSetting = booleansSetting;
   }

   public static C0BPacketEntityAction.Action getAction(DisablerEntityActionOption option) {
      return option.action;
   }

   public static boolean isOptionSelected(DisablerEntityActionOption option) {
      return option.selected;
   }

   public static int incrementPacketCount(DisablerEntityActionOption option) {
      return option.packetCount++;
   }

   public static StringValue getCancelConditionSetting(DisablerEntityActionOption option) {
      return option.cancelConditionSetting;
   }

   public static DoubleValue getIntervalSetting(DisablerEntityActionOption option) {
      return option.intervalSetting;
   }

   public static BooleanValue getInvertIntervalSetting(DisablerEntityActionOption option) {
      return option.invertIntervalSetting;
   }

   public static int getPacketCount(DisablerEntityActionOption option) {
      return option.packetCount;
   }

   public static BooleanValue getAlwaysSetting(DisablerEntityActionOption option) {
      return option.alwaysSetting;
   }

   public static RangeSetting getTickRangeSetting(DisablerEntityActionOption option) {
      return option.tickRangeSetting;
   }
}

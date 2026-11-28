package net.augustus.settings.options;

import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public enum DisablerDiggingActionOption implements SelectableOption {
   START_DESTROY_BLOCK("StartDestroyBlock", C07PacketPlayerDigging.Action.START_DESTROY_BLOCK),
   ABORT_DESTROY_BLOCK("AbortDestroyBlock", C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK),
   STOP_DESTROY_BLOCK("StopDestroyBlock", C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK),
   DROP_ALL_ITEMS("DropAllItems", C07PacketPlayerDigging.Action.DROP_ALL_ITEMS),
   DROP_ITEM("DropItem", C07PacketPlayerDigging.Action.DROP_ITEM),
   RELEASE_USE_ITEM("ReleaseUseItem", C07PacketPlayerDigging.Action.RELEASE_USE_ITEM);

private String name;
   private C07PacketPlayerDigging.Action action;
   private boolean selected;
   private DoubleValue intervalSetting;
   private BooleanValue invertIntervalSetting;
   private BooleanValue alwaysSetting;
   private StringValue cancelConditionSetting;
   private RangeSetting tickRangeSetting;
   private int packetCount = 0;
private DisablerDiggingActionOption(String str, C07PacketPlayerDigging.Action action2) {
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

   public static String getOptionName(DisablerDiggingActionOption option) {
      return option.name;
   }

   public static DoubleValue setIntervalSetting(DisablerDiggingActionOption option, DoubleValue stringValue) {
      return option.intervalSetting = stringValue;
   }

   public static BooleanValue setInvertIntervalSetting(DisablerDiggingActionOption option, BooleanValue booleanValue) {
      return option.invertIntervalSetting = booleanValue;
   }

   public static BooleanValue setAlwaysSetting(DisablerDiggingActionOption option, BooleanValue booleanValue) {
      return option.alwaysSetting = booleanValue;
   }

   public static RangeSetting setTickRangeSetting(DisablerDiggingActionOption option, RangeSetting rangeSetting) {
      return option.tickRangeSetting = rangeSetting;
   }

   public static StringValue setCancelConditionSetting(DisablerDiggingActionOption option, StringValue booleansSetting) {
      return option.cancelConditionSetting = booleansSetting;
   }

   public static C07PacketPlayerDigging.Action getAction(DisablerDiggingActionOption option) {
      return option.action;
   }

   public static boolean isOptionSelected(DisablerDiggingActionOption option) {
      return option.selected;
   }

   public static int incrementPacketCount(DisablerDiggingActionOption option) {
      return option.packetCount++;
   }

   public static StringValue getCancelConditionSetting(DisablerDiggingActionOption option) {
      return option.cancelConditionSetting;
   }

   public static DoubleValue getIntervalSetting(DisablerDiggingActionOption option) {
      return option.intervalSetting;
   }

   public static BooleanValue getInvertIntervalSetting(DisablerDiggingActionOption option) {
      return option.invertIntervalSetting;
   }

   public static int getPacketCount(DisablerDiggingActionOption option) {
      return option.packetCount;
   }

   public static BooleanValue getAlwaysSetting(DisablerDiggingActionOption option) {
      return option.alwaysSetting;
   }

   public static RangeSetting getTickRangeSetting(DisablerDiggingActionOption option) {
      return option.tickRangeSetting;
   }
}

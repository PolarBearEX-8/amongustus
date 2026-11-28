package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S48PacketResourcePackSend;

public enum VelocityCancelPacketOption implements SelectableOption {
   S00PacketKeepAlive("S00PacketKeepAlive", S00PacketKeepAlive.class),
   S32PacketConfirmTransaction("S32PacketConfirmTransaction", S32PacketConfirmTransaction.class),
   S48PacketResourcePackSend("S48PacketResourcePackSend", S48PacketResourcePackSend.class);

private String name;
   private boolean selected;
   private Class packetClass;
   private StringValue cancelTypeSetting;
   private DoubleValue packetCountSetting;
   private int remainingCancelCount = 0;
private VelocityCancelPacketOption(String str, Class clazz) {
      this.name = str;
      this.packetClass = clazz;
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

   public static StringValue setCancelTypeSetting(VelocityCancelPacketOption option, StringValue booleansSetting) {
      return option.cancelTypeSetting = booleansSetting;
   }

   public static DoubleValue setPacketCountSetting(VelocityCancelPacketOption option, DoubleValue stringValue) {
      return option.packetCountSetting = stringValue;
   }

   public static int setRemainingCancelCount(VelocityCancelPacketOption option, int n) {
      return option.remainingCancelCount = n;
   }

   public static DoubleValue getPacketCountSetting(VelocityCancelPacketOption option) {
      return option.packetCountSetting;
   }

   public static Class getPacketClass(VelocityCancelPacketOption option) {
      return option.packetClass;
   }

   public static int getRemainingCancelCount(VelocityCancelPacketOption option) {
      return option.remainingCancelCount;
   }

   public static int decrementRemainingCancelCount(VelocityCancelPacketOption option) {
      return option.remainingCancelCount--;
   }

   public static StringValue getCancelTypeSetting(VelocityCancelPacketOption option) {
      return option.cancelTypeSetting;
   }
}

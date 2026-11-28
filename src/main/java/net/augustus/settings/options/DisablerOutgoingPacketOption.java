package net.augustus.settings.options;

import net.augustus.settings.RangeSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;

public enum DisablerOutgoingPacketOption implements SelectableOption {
   C00PacketKeepAlive("C00PacketKeepAlive", C00PacketKeepAlive.class),
   C01PacketChatMessage("C01PacketChatMessage", C01PacketChatMessage.class),
   C02PacketUseEntity("C02PacketUseEntity", C02PacketUseEntity.class),
   C03PacketPlayer("C03PacketPlayer", C03PacketPlayer.class),
   C03PacketPlayer$C04PacketPlayerPosition("C04PacketPlayerPosition", C03PacketPlayer.C04PacketPlayerPosition.class),
   C03PacketPlayer$C05PacketPlayerLook("C05PacketPlayerLook", C03PacketPlayer.C05PacketPlayerLook.class),
   C03PacketPlayer$C06PacketPlayerPosLook("C06PacketPlayerPosLook", C03PacketPlayer.C06PacketPlayerPosLook.class),
   C07PacketPlayerDigging("C07PacketPlayerDigging", C07PacketPlayerDigging.class),
   C08PacketPlayerBlockPlacement("C08PacketPlayerBlockPlacement", C08PacketPlayerBlockPlacement.class),
   C09PacketHeldItemChange("C09PacketHeldItemChange", C09PacketHeldItemChange.class),
   C0APacketAnimation("C0APacketAnimation", C0APacketAnimation.class),
   C0BPacketEntityAction("C0BPacketEntityAction", C0BPacketEntityAction.class),
   C0CPacketInput("C0CPacketInput", C0CPacketInput.class),
   C0DPacketCloseWindow("C0DPacketCloseWindow", C0DPacketCloseWindow.class),
   C0EPacketClickWindow("C0EPacketClickWindow", C0EPacketClickWindow.class),
   C0FPacketConfirmTransaction("C0FPacketConfirmTransaction", C0FPacketConfirmTransaction.class),
   C10PacketCreativeInventoryAction("C10PacketCreativeInventoryAction", C10PacketCreativeInventoryAction.class),
   C11PacketEnchantItem("C11PacketEnchantItem", C11PacketEnchantItem.class),
   C12PacketUpdateSign("C12PacketUpdateSign", C12PacketUpdateSign.class),
   C13PacketPlayerAbilities("C13PacketPlayerAbilities", C13PacketPlayerAbilities.class),
   C14PacketTabComplete("C14PacketTabComplete", C14PacketTabComplete.class),
   C15PacketClientSettings("C15PacketClientSettings", C15PacketClientSettings.class),
   C16PacketClientStatus("C16PacketClientStatus", C16PacketClientStatus.class),
   C17PacketCustomPayload("C17PacketCustomPayload", C17PacketCustomPayload.class),
   C18PacketSpectate("C18PacketSpectate", C18PacketSpectate.class),
   C19PacketResourcePackStatus("C19PacketResourcePackStatus", C19PacketResourcePackStatus.class),
   CAnimateHandPacket("CAnimateHandPacket", C0APacketSwingHand.class),
   CPacketPlayerTryUseItemOnBlock("CPacketPlayerTryUseItemOnBlock", net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock.class);

private String name;
   private boolean selected;
   private Class packetClass;
   private DoubleValue intervalSetting;
   private BooleanValue invertIntervalSetting;
   private BooleanValue alwaysSetting;
   private StringValue cancelConditionSetting;
   private RangeSetting tickRangeSetting;
   private int packetCount = 0;
private DisablerOutgoingPacketOption(String str, Class clazz) {
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

   public static String getOptionName(DisablerOutgoingPacketOption option) {
      return option.name;
   }

   public static DoubleValue setIntervalSetting(DisablerOutgoingPacketOption option, DoubleValue stringValue) {
      return option.intervalSetting = stringValue;
   }

   public static BooleanValue setInvertIntervalSetting(DisablerOutgoingPacketOption option, BooleanValue booleanValue) {
      return option.invertIntervalSetting = booleanValue;
   }

   public static BooleanValue setAlwaysSetting(DisablerOutgoingPacketOption option, BooleanValue booleanValue) {
      return option.alwaysSetting = booleanValue;
   }

   public static RangeSetting setTickRangeSetting(DisablerOutgoingPacketOption option, RangeSetting rangeSetting) {
      return option.tickRangeSetting = rangeSetting;
   }

   public static StringValue setCancelConditionSetting(DisablerOutgoingPacketOption option, StringValue booleansSetting) {
      return option.cancelConditionSetting = booleansSetting;
   }

   public static Class getPacketClass(DisablerOutgoingPacketOption option) {
      return option.packetClass;
   }

   public static int incrementPacketCount(DisablerOutgoingPacketOption option) {
      return option.packetCount++;
   }

   public static boolean isOptionSelected(DisablerOutgoingPacketOption option) {
      return option.selected;
   }

   public static StringValue getCancelConditionSetting(DisablerOutgoingPacketOption option) {
      return option.cancelConditionSetting;
   }

   public static DoubleValue getIntervalSetting(DisablerOutgoingPacketOption option) {
      return option.intervalSetting;
   }

   public static BooleanValue getInvertIntervalSetting(DisablerOutgoingPacketOption option) {
      return option.invertIntervalSetting;
   }

   public static int getPacketCount(DisablerOutgoingPacketOption option) {
      return option.packetCount;
   }

   public static BooleanValue getAlwaysSetting(DisablerOutgoingPacketOption option) {
      return option.alwaysSetting;
   }

   public static RangeSetting getTickRangeSetting(DisablerOutgoingPacketOption option) {
      return option.tickRangeSetting;
   }
}

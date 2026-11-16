package net.minecraft.network.play.client;

import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NamedOption;
import client.setting.NumberSetting;

public enum ClientPacketType implements NamedOption {
    C00PacketKeepAlive("C00PacketKeepAlive", C00PacketKeepAlive.class),
    C01PacketChatMessage("C01PacketChatMessage", C01PacketChatMessage.class),
    C02PacketUseEntity("C02PacketUseEntity", C02PacketUseEntity.class),
    C03PacketPlayer("C03PacketPlayer", C03PacketPlayer.class),
    C04PacketPlayerPosition("C04PacketPlayerPosition", C04PacketPlayerPosition.class),
    C05PacketPlayerLook("C05PacketPlayerLook", C05PacketPlayerLook.class),
    C06PacketPlayerPosLook("C06PacketPlayerPosLook", C06PacketPlayerPosLook.class),
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
    CAnimateHandPacket("CAnimateHandPacket", CAnimateHandPacket.class),
    CPacketPlayerTryUseItemOnBlock("CPacketPlayerTryUseItemOnBlock", CPacketPlayerTryUseItemOnBlock.class);

   public String labelText;
   public boolean enabled;
   public Class memberRef;
   public NumberSetting numberSetting;
   public BooleanSetting booleanSetting;
   public ModeSetting modeSetting;
   public ActionSetting actionSetting;
   public int count = 0;
   public static final ClientPacketType[] ENUM_VALUES = new ClientPacketType[]{
      C00PacketKeepAlive,
      C01PacketChatMessage,
      C02PacketUseEntity,
      C03PacketPlayer,
      C04PacketPlayerPosition,
      C05PacketPlayerLook,
      C06PacketPlayerPosLook,
      C07PacketPlayerDigging,
      C08PacketPlayerBlockPlacement,
      C09PacketHeldItemChange,
      C0APacketAnimation,
      C0BPacketEntityAction,
      C0CPacketInput,
      C0DPacketCloseWindow,
      C0EPacketClickWindow,
      C0FPacketConfirmTransaction,
      C10PacketCreativeInventoryAction,
      C11PacketEnchantItem,
      C12PacketUpdateSign,
      C13PacketPlayerAbilities,
      C14PacketTabComplete,
      C15PacketClientSettings,
      C16PacketClientStatus,
      C17PacketCustomPayload,
      C18PacketSpectate,
      C19PacketResourcePackStatus,
      CAnimateHandPacket,
      CPacketPlayerTryUseItemOnBlock
   };

   public static Class handlePacket(ClientPacketType packet) {
      return packet.memberRef;
   }

   public static NumberSetting handlePacket(ClientPacketType packet, NumberSetting setting) {
      return packet.numberSetting = setting;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static BooleanSetting handlePacket2(ClientPacketType packet) {
      return packet.booleanSetting;
   }

   public static boolean checkClientPacketType(ClientPacketType packet) {
      return packet.enabled;
   }

   public static int handlePacket3(ClientPacketType packet) {
      return packet.count++;
   }

   public static ActionSetting handlePacket4(ClientPacketType packet) {
      return packet.actionSetting;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static ActionSetting resolveActionSetting(ClientPacketType packet, ActionSetting setting) {
      return packet.actionSetting = setting;
   }

   public static BooleanSetting handlePacket(ClientPacketType packet, BooleanSetting setting) {
      return packet.booleanSetting = setting;
   }

   public static NumberSetting handlePacket5(ClientPacketType packet) {
      return packet.numberSetting;
   }

   public static int handlePacket6(ClientPacketType packet) {
      return packet.count;
   }

   public static ModeSetting handlePacket(ClientPacketType packet, ModeSetting setting) {
      return packet.modeSetting = setting;
   }

   public static String handlePacket7(ClientPacketType packet) {
      return packet.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   public static ModeSetting handlePacket8(ClientPacketType packet) {
      return packet.modeSetting;
   }

   private ClientPacketType(String text, Class clazz) {
      this.labelText = text;
      this.memberRef = clazz;
   }


}

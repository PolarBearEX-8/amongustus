package client.network;

import client.setting.ModeSetting;
import client.setting.NamedOption;
import client.setting.NumberSetting;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S48PacketResourcePackSend;

public enum PingSpoofPacketType implements NamedOption {
    S00PacketKeepAlive("S00PacketKeepAlive", S00PacketKeepAlive.class),
    S32PacketConfirmTransaction("S32PacketConfirmTransaction", S32PacketConfirmTransaction.class),
    S48PacketResourcePackSend("S48PacketResourcePackSend", S48PacketResourcePackSend.class);

   public String labelText;
   public boolean enabled;
   public Class memberRef;
   public ModeSetting modeSetting;
   public NumberSetting numberSetting;
   public int count = 0;
   public static final PingSpoofPacketType[] ENUM_VALUES = new PingSpoofPacketType[]{
      S00PacketKeepAlive, S32PacketConfirmTransaction, S48PacketResourcePackSend
   };

   public static NumberSetting handlePacket(PingSpoofPacketType packet) {
      return packet.numberSetting;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static int handlePacket2(PingSpoofPacketType packet) {
      return packet.count--;
   }

   public static int handlePacket3(PingSpoofPacketType packet) {
      return packet.count;
   }

   public static NumberSetting handlePacket(PingSpoofPacketType packet, NumberSetting setting) {
      return packet.numberSetting = setting;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static Class handlePacket4(PingSpoofPacketType packet) {
      return packet.memberRef;
   }

   public static int handlePacket(PingSpoofPacketType packet, int count2) {
      return packet.count = count2;
   }

   public static ModeSetting handlePacket(PingSpoofPacketType packet, ModeSetting setting) {
      return packet.modeSetting = setting;
   }

   public static ModeSetting handlePacket5(PingSpoofPacketType packet) {
      return packet.modeSetting;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private PingSpoofPacketType(String text, Class clazz) {
      this.labelText = text;
      this.memberRef = clazz;
   }


}
